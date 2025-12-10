package assignments.Ex2;

public class Index2D implements Pixel2D {
    private int _w, _h;


    public Index2D(int w, int h) {
        _w = w;
        _h = h;
    }
    public Index2D(Pixel2D other) {

    }
    @Override
    public int getX() {
        return this._w;
    }

    @Override
    public int getY() {
        return this._h;
    }

    @Override
    public double distance2D(Pixel2D p2) {

        return 0;
    }

    @Override
    public String toString() {
        String ans = null;

        return ans;
    }

    @Override
    public boolean equals(Object p) {
        boolean ans = true;

        return ans;
    }
}
