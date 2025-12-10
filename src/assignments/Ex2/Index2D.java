package assignments.Ex2;

public class Index2D implements Pixel2D {
    private int _x, _y;


    public Index2D(int w, int h) {
        _x = w;
        _y = h;
    }
    public Index2D(Pixel2D other) {

    }
    @Override
    public int getX() {
        return this._x;
    }

    @Override
    public int getY() {
        return this._y;
    }

    @Override
    public double distance2D(Pixel2D p2) {
        if (p2 == null){
            throw new RuntimeException("Pixel2D given is null");
        }
        return Math.sqrt(Math.pow((_y -p2.getY()),2) + Math.pow((_x -p2.getX()),2));
    }

    @Override
    public String toString() {
        return ("("+ _x +","+ _y +")");
    }

    @Override
    public boolean equals(Object p) {
        boolean ans = true;
            if (this == p) //if they point to the same object
                return true;
            if (!(p instanceof Index2D)) //if p is not an Index2D its always false
                return false;
            Index2D temp = (Index2D) p;
            if ((temp._x == this._x)&&(temp._y == this._y)) //the actual comparison
                return true;
        return ans;
    }
}
