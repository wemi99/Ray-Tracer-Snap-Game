public class Vector {
    // A class to represent a 3D vector

    public double[] elements;

    public Vector(double x, double y, double z) {
        elements= new double[] { x, y, z };
    }

    public Vector add(Vector other) {
        // Return this + other
        return new Vector(elements[0] + other.elements[0], elements[1] + other.elements[1],
            elements[2] + other.elements[2]);
    }

    public Vector subtract(Vector other) {
        // Return this - other
        return new Vector(elements[0] - other.elements[0], elements[1] - other.elements[1],
            elements[2] - other.elements[2]);
    }

    public Vector cross(Vector other) {
        // Return this cross other
        double a= elements[0];
        double b= elements[1];
        double c= elements[2];
        double d= other.elements[0];
        double e= other.elements[1];
        double f= other.elements[2];

        double x= b * f - c * e;
        double y= c * d - a * f;
        double z= a * e - b * d;

        return new Vector(x, y, z);
    }

    public double dot(Vector other) {
        // Return this dot other
        return elements[0]*other.elements[0] + elements[1]*other.elements[1] + elements[2]*other.elements[2];
    }

    public Vector multiply(double other) {
        // Return this * other
        return new Vector(elements[0] * other, elements[1] * other, elements[2] * other);
    }

    public double norm() {
        // Return the norm of this vector
        double disc= Math.pow(elements[0], 2) + Math.pow(elements[1], 2) + Math.pow(elements[2], 2);
        return Math.sqrt(disc);
    }

    public void normalize() {
        // Normalize this vector
        double[] newElements= multiply(1 / norm()).elements;
        elements[0]= newElements[0];
        elements[1]= newElements[1];
        elements[2]= newElements[2];
    }

    public void cap(double min, double max) {
        // Cap this vector so all the values are above min and below max
        elements[0] = elements[0] > max ? max : elements[0];
        elements[1] = elements[1] > max ? max : elements[1];
        elements[2] = elements[2] > max ? max : elements[2];

        elements[0] = elements[0] < min ? min : elements[0];
        elements[1] = elements[1] < min ? min : elements[1];
        elements[2] = elements[2] < min ? min : elements[2];
    }

    public Vector copy() {
        // Returns a copy of this vector
        double x = elements[0];
        double y = elements[1];
        double z = elements[2];
        Vector copy = new Vector(x,y,z);
        return copy;
    }

    public Vector homogenized() {
        // Returns a homogenius version of this vector
        if (elements[2] != 0) {
            return new Vector(elements[0] / elements[2], elements[1] / elements[2], 1);
        }
        return this.copy();
    }
}