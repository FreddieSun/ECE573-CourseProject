public class BHTree {
    private Body body;
    private Quad quad;
    private BHTree NW;
    private BHTree NE;
    private BHTree SW;
    private BHTree SE;

    private final double Theta = 0.5;


    public BHTree(Quad q) {
        this.quad = q;
        this.body = null;
        this.NW = null;
        this.NE = null;
        this.SW = null;
        this.SE = null;
    }

    public boolean isExternal() {
        return (NW == null
                && NE == null
                && SW == null
                && SE ==null
        );
    }

    public void insert(Body b) {
        if (body == null) {
            body = b;
            return;
        }

        // if is an internal node,
        if (isExternal()) {
            NW = new BHTree(quad.NW());
            NE = new BHTree(quad.NE());
            SW = new BHTree(quad.SW());
            SE = new BHTree(quad.SE());

            putBody(body);
            putBody(b);

            // update the center-of mass and total mass
            body = body.plus(b);

        } else {
            // if is internal node
            body = body.plus(b);

            // recursively insert Body b into the appropriate quadrant
            putBody(b);

        }

    }

    // put the body into the corresponding quadrant
    public void putBody(Body b) {
        if (b.in(quad.NW()))
            NW.insert(b);
        else if (b.in(quad.NE()))
            NE.insert(b);
        else if (b.in(quad.SE()))
            SE.insert(b);
        else if (b.in(quad.SW()))
            SW.insert(b);

    }


    public void updateForce(Body b) {

        if (body == null || b.equals(body))
            return;

        // if the current node is external, update net force acting on b
        if (isExternal())
            b.addForce(body);

            // for internal nodes
        else {

            // width of region represented by internal node
            double s = quad.length();

            // distance between Body b and this node's center-of-mass
            double d = body.distanceTo(b);

            // compare ratio (s / d) to threshold value Theta
            if ((s / d) < Theta)
                b.addForce(body);   // b is far away

                // recurse on each of current node's children
            else {
                NW.updateForce(b);
                NE.updateForce(b);
                SW.updateForce(b);
                SE.updateForce(b);
            }
        }
    }



    public void draw() {

    }
}
