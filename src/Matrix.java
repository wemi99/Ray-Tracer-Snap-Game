import java.util.Map;

public class Matrix {
    // A class representing a 3x3 matrix

    // In the form
    // |a b c|
    // |d e f|
    // |g h i|
    private double a;
    private double b;
    private double c;
    private double d;
    private double e;
    private double f;
    private double g;
    private double h;
    private double i;

    public Matrix(Vector col1, Vector col2, Vector col3) {
        this.a = col1.elements[0];
        this.d = col1.elements[1];
        this.g = col1.elements[2];
        this.b = col2.elements[0];
        this.e = col2.elements[1];
        this.h = col2.elements[2];
        this.c = col3.elements[0];
        this.f = col3.elements[1];
        this.i = col3.elements[2];
    }

    private void transpose() {
        // Transpose this matrix
        double[] tempElements = new double[]{this.a,this.b,this.c,this.d,this.e,this.f,this.g,this.h,this.i};

        this.a = tempElements[0];
        this.b = tempElements[3];
        this.c = tempElements[6];
        this.d = tempElements[1];
        this.e = tempElements[4];
        this.f = tempElements[7];
        this.g = tempElements[2];
        this.h = tempElements[5];
        this.i = tempElements[8];
    }

    private static double determinant(double a, double b, double c, double d) {
        // Get the determinant of 2x2 matrix
        // |a b|
        // |c d|
        return a*d - b*c;
    }

    private double determinant() {
        // Get the determinant of this matrix
        return a*Matrix.determinant(e,f,h,i) - b*Matrix.determinant(d,f,g,i) + c*Matrix.determinant(d,e,g,h);
    }

    private void adj() {
        // Turn this matrix into its adjugate
        double new_a = Matrix.determinant(e,f,h,i) * 1;
        double new_b = Matrix.determinant(d,f,g,i) * -1;
        double new_c = Matrix.determinant(d,e,g,h) * 1;
        double new_d = Matrix.determinant(b,c,h,i) * -1;
        double new_e = Matrix.determinant(a,c,g,i) * 1;
        double new_f = Matrix.determinant(a,b,g,h) * -1;
        double new_g = Matrix.determinant(b,c,e,f) * 1;
        double new_h = Matrix.determinant(a,c,d,f) * -1;
        double new_i = Matrix.determinant(a,b,d,e) * 1;

        this.a = new_a;
        this.b = new_b;
        this.c = new_c;
        this.d = new_d;
        this.e = new_e;
        this.f = new_f;
        this.g = new_g;
        this.h = new_h;
        this.i = new_i;
    }

    private void multiply(double other) {
        // Multiply all elements by scalar other
        this.a *= other;
        this.b *= other;
        this.c *= other;
        this.d *= other;
        this.e *= other;
        this.f *= other;
        this.g *= other;
        this.h *= other;
        this.i *= other;
    }

    public void inverse() {
        // Inverse this matrix
        double det = this.determinant();
        this.transpose();
        this.adj();
        this.multiply(1 / det);
    }

    public Vector dot(Vector other) {
        // Apply a left multiply of this matrix onto vector other
        double[] elements = other.elements;
        double x = a*elements[0] + b*elements[1] + c*elements[2];
        double y = d*elements[0] + e*elements[1] + f*elements[2];
        double z = g*elements[0] + h*elements[1] + i*elements[2];

        return new Vector(x,y,z);
    }
}