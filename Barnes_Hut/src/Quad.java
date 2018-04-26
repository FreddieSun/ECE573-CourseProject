public class Quad {
    private double xmid;
    private double ymid;
    private double length;

    public Quad(double xmid, double ymid, double length) {
        this.xmid = xmid;
        this.ymid = ymid;
        this.length = length;
    }


    public double length() {
        return length;
    }

    public boolean contains(double x, double y) {
        double halfLen = this.length / 2.0;
        return (x <= this.xmid + halfLen &&
                x >= this.xmid - halfLen &&
                y <= this.ymid + halfLen &&
                y >= this.ymid - halfLen);
    }

    public Quad NW() {
        double x = this.xmid - this.length / 4.0;
        double y = this.ymid + this.length / 4.0;
        double len = this.length / 2.0;
        Quad NW = new Quad(x, y, len);
        return NW;
    }

    public Quad NE() {
        double x = this.xmid + this.length / 4.0;
        double y = this.ymid + this.length / 4.0;
        double len = this.length / 2.0;
        Quad NE = new Quad(x, y, len);
        return NE;
    }

    public Quad SW() {
        double x = this.xmid - this.length / 4.0;
        double y = this.ymid - this.length / 4.0;
        double len = this.length / 2.0;
        Quad SW = new Quad(x, y, len);
        return SW;
    }

    public Quad SE() {
        double x = this.xmid + this.length / 4.0;
        double y = this.ymid - this.length / 4.0;
        double len = this.length / 2.0;
        Quad SE = new Quad(x, y, len);
        return SE;
    }

    @Override
    public String toString() {
        // todo
        return super.toString();
    }

    public void draw() {
        StdDraw.square(xmid, ymid, length/2.0);
    }
}
