import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

public class Driver {
    // DEBUGGING
    public static boolean hasRealtime = false;

    // Each following measurement is the number of blocks
    private static int wallHeight = 10;
    private static int floorLength = 10;
    private static int floorWidth = 15;
    private static int ceilingLength = floorLength;
    private static int ceilingWidth = floorWidth;
    private static int lenCeilingTiles = 7;
    private static int ceilingHeight = 180;

    public static int screenWidth= 0;
    public static int screenLength= 0;

    // Scene measurements
    public static int charHeight= 50;
    public static int imageHeightRealtime = 50;
    public static int imageHeightPhoto = 512;

    // Aesthetics
    public static Vector zeroVec = new Vector(0,0,0);
    public static Map<Vector, Material> defaultMap = new HashMap<>();
    public static Hit defaultHit = new Hit(0, zeroVec, zeroVec, new Triangle(zeroVec, zeroVec, zeroVec, defaultMap));
    public static Material defaultMaterial = new Material(zeroVec, 0.0, 0.0, 0.0, zeroVec);

    // Lights
    private static Light[] lights= new Light[] { new PointLight(new Vector(400, 5, 100), 30000.0),
            new AmbientLight(0.5), new PointLight(new Vector(20, 5, 20), 30000.0) };
//    private static Light[] lights = new Light[] {new AmbientLight(0.5)};

    // Scene
    private static Scene scene= new Scene(false);

    // Key listener
    private static Keyboard key= new Keyboard();

    // Frame
    private static GameFrame frame;

    // Camera
    private static Camera camera= new Camera(0, new Vector(0, 1, 0),
        0.9 * Math.PI / 4, 1.5);

    public static JFrame pictureFrame;

    // Getters
    public static int getWallHeight() {
        return wallHeight;
    }

    public static int getFloorLength() {
        return floorLength;
    }

    public static int getFloorWidth() {
        return floorWidth;
    }

    public static int getCeilingLength() {
        return ceilingLength;
    }

    public static int getCeilingWidth() {
        return ceilingWidth;
    }
    
    public static int getLenCeilingTiles() {
        return lenCeilingTiles;
    }

    public static int getCeilingHeight() {
        return ceilingHeight;
    }
  
    public static int getScreenWidth() {
        return screenWidth;
    }

    public static int getScreenLength() {
        return screenLength;
    }

    public static Scene getScene() {
        return scene;
    }

    public static Keyboard getKey() {
        return key;
    }

    public static Camera getCamera() {
        return camera;
    }

    // Game logic
    private static void logicTick() {
        scene.character.updatePos();
        camera.updateCamera(lights, scene);
        for (Obstacle obstacle : scene.obstacles) {
            obstacle.updatePos();
        }
    }

    private static void renderFrame() {
        frame.updatePanel();
    }

    // Main driver of the program
    public static void main(String[] args) {
        defaultHit.exists = false;
        defaultMap.put(zeroVec, defaultMaterial);

        pictureFrame= new JFrame("Picture");
        pictureFrame.setSize(200, 100);
        if (hasRealtime) pictureFrame.setVisible(true);
        class TestPanel extends javax.swing.JPanel {
            // The panel to be used by the game frame

            public BufferedImage background;

            public TestPanel() {
                super();
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Scene scene= Driver.getScene();

                // Draw background
                g.drawImage(background, 0, 0, this);
            }
        }
        TestPanel picturePanel= new TestPanel();
        pictureFrame.add(picturePanel);

        screenWidth= scene.floorSize[0];
        screenLength= scene.floorSize[1];
        frame= new GameFrame();
        frame.addKeyListener(key);

        // Logic for keeping constant frame rate for physics
        long lastUpdate= 0;

        while (true) {
            // Game loop

            // Update logic w/ a constant 30 frames per second
            long currTime= System.currentTimeMillis();
            if (currTime >= lastUpdate + 33.33) {
                lastUpdate= currTime;
                logicTick();

                // Update frame
                renderFrame();
            }

            if (hasRealtime) {
                BufferedImage newImg = camera.takePhoto(lights, scene, false, imageHeightRealtime);
                picturePanel.background = newImg;
                picturePanel.repaint();
            }
        }
    }

    public static Light[] getLights() {
        return lights;
    }

    public static void setLights(Light[] lights2) {
        lights= lights2;
    }
}