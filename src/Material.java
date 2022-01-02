import java.awt.Color;

public class Material {
    Vector k_d;
    Double k_s;
    Double p;
    Double k_m;
    Vector k_a;
    Color color;

    public Material(Vector k_d,
        Double k_s,
        Double p,
        Double k_m,
        Vector k_a) {
        this.k_d= k_d;
        this.k_s= k_s;
        this.p= p;
        this.k_m= k_m;
        this.k_a= k_a;
    }

    public Material(Color color) {
        this.color= color;

    }

}
