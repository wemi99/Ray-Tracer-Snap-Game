import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class GameFrame extends javax.swing.JFrame {
    // The frame to hold the game

    // Pixel measurements
    private int width;
    private int length;

    // Drawing screen
    private BufferedImage background;

    private GamePanel panel;

    public GameFrame() {
        super("Picture Game");

        this.width = Driver.getScreenWidth();
        this.length = Driver.getScreenLength();

        this.background = new BufferedImage(this.width, this.length, BufferedImage.TYPE_INT_RGB);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setPreferredSize(new Dimension(this.width, this.length));
        this.pack();
        this.setVisible(true);

        this.panel = new GamePanel(this.background);
        this.panel.setSize(this.getSize());
        this.add(this.panel);
    }

    public void updatePanel() {
        // Repaint the panel
        panel.repaint();
    }

    private class GamePanel extends javax.swing.JPanel {
        // The panel to be used by the game frame

        private BufferedImage background;

        public GamePanel(BufferedImage background) {
            super();

            this.background = background;
        }

        private void drawObject(Graphics2D g2, Renderable render) {
            // Rotate image
            // Help from https://stackoverflow.com/a/37758533 for using AffineTransform
            AffineTransform rotate = new AffineTransform();
            rotate.scale(2, 2);
            rotate.rotate(render.rotation, render.pos.elements[0] + render.getWidth() / 2,
                    render.pos.elements[2] + render.getLength() / 2);
            g2.setTransform(rotate);
            g2.drawImage(render.getTwoDimRendering(), (int)render.pos.elements[0], (int)render.pos.elements[2], this);

            // Reset rotation
            g2.setTransform(new AffineTransform());
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            Scene scene = Driver.getScene();

            // Draw background
            g2.drawImage(this.background, 0, 0, this);

            // Draw floor
            for (int y = 0; y < scene.floorArr.length; y++) {
                for (int x = 0; x < scene.floorArr[0].length; x++) {
                    Block currTile = scene.floorArr[y][x];
                    g2.drawImage(currTile.getTwoDimRendering(), (int)currTile.pos.elements[0],
                            (int)currTile.pos.elements[2], this);
                }
                
            }
            
            for (int y = 0; y < scene.boundaryWallArr.length; y++) {
                for (int x = 0; x < scene.boundaryWallArr[0].length; x++) {
                    Block currTile = scene.boundaryWallArr[y][x];
                    if (currTile != null) {
	                    g2.drawImage(currTile.getTwoDimRendering(), (int)currTile.pos.elements[0],
	                            (int)currTile.pos.elements[2], this);
                    }
                }
            }

            // Draw ceiling (will probably cover up the floor so might want to have
            // two versions with different viewing perspective) 
            // for (int y = 0; y < scene.ceilingArr.length; y++) {
            //     for (int x = 0; x < scene.ceilingArr[0].length; x++) {
            //         Block currTile = scene.ceilingArr[y][x];
            //         g.drawImage(currTile.getTwoDimRendering(), (int)currTile.pos.elements[0],
            //                 (int)currTile.pos.elements[2], this);
            //     }
            // }

            // Draw camera GUI
            g2.setColor(new Color(255, 255, 255, 100));
            int[][] triangleVerts =
                    Driver.getCamera().getTriangleVerts((int)scene.character.pos.elements[0] + scene.character.getWidth() / 2,
                            (int)scene.character.pos.elements[2] + scene.character.getLength() / 2);
            g2.fillPolygon(triangleVerts[0], triangleVerts[1], 3);

            // Draw character
            drawObject(g2, scene.character);

            // Draw obstacles
            for (Obstacle obstacle : scene.obstacles) {
                drawObject(g2, obstacle);
            }
        }
    }
}