package assignments.Ex2;
import java.io.Serializable;
/**
 * This class represents a 2D map (int[w][h]) as a "screen" or a raster matrix or maze over integers.
 * This is the main class needed to be implemented.
 *
 * @author boaz.benmoshe
 *
 */
public class Map implements Map2D, Serializable{
    // edit this class below
    private int[][] _map;
	/**
	 * Constructs a w*h 2D raster map with an init value v.
	 * @param w
	 * @param h
	 * @param v
	 */
	public Map(int w, int h, int v) {init(w, h, v);}
	/**
	 * Constructs a square map (size*size).
	 * @param size
	 */
	public Map(int size,int v) {this(size,size, v);}
	
	/**
	 * Constructs a map from a given 2D array.
	 * @param data
	 */
	public Map(int[][] data) {
		init(data);
	}
	@Override
	public void init(int w, int h, int v) {
        _map = new int[w][h];
        for (int i = 0; i < _map.length; i++){
            for (int j = 0; j < _map[w].length; j++){
                _map[i][j] = v;
            }
        }
	}
	@Override
	public void init(int[][] arr) {
        if (validMap(arr)){
            _map = new int[arr.length][arr[0].length];
            for (int i = 0; i < _map.length; i++)
                 for (int j = 0; j < _map[i].length; j++)
                     _map[i][j] = arr[i][j];
            }
	}
	@Override
	public int[][] getMap() {
		return this._map;
	}
	@Override
	public int getWidth() {
        validMap(this._map);
        return this._map.length;
    }
	@Override
	public int getHeight() {
        validMap(this._map);
        return this._map[0].length;
    }
	@Override
	public int getPixel(int x, int y) {
        try{
            return this._map[x][y];
        }catch (NullPointerException error){
            throw new RuntimeException("The provided pixel is out of the map's scope");
        }
    }
	@Override
	public int getPixel(Pixel2D p) {
        return getPixel(p.getX(),p.getY());
	}
	@Override
	public void setPixel(int x, int y, int v) {
        validMap(this._map);
        try {
            this._map[x][y] = v;
        }catch (NullPointerException error) {
            throw new RuntimeException("The provided pixel is out of the map's scope");
        }
    }
	@Override
	public void setPixel(Pixel2D p, int v) {
        this.setPixel(p.getX(),p.getY(),v);
	}

    @Override
    public boolean isInside(Pixel2D p) {
        try {
            int temp = this._map[p.getX()][p.getY()];
        }catch (NullPointerException error){
            return false;
        }
        return true;
    }

    @Override
    public boolean sameDimensions(Map2D p) {
        return (this._map.length == p.getMap().length) && (this._map[0].length == p.getMap()[0].length);
    }

    @Override
    public void addMap2D(Map2D p) {
        if (this.sameDimensions(p)){
            for (int i = 0; i < _map.length; i++)
                for (int j = 0; j < _map[i].length; j++)
                    this._map[i][j] += p.getMap()[i][j];
        }
    }

    @Override
    public void mul(double scalar) {
        int mul = (int)scalar;
        for (int i = 0; i < _map.length; i++)
            for (int j = 0; j < _map[i].length; j++)
                this._map[i][j] *= mul;
    }

    @Override
    public void rescale(double sx, double sy) {
        int newW = (int)(sx*this.getWidth());
        int newH = (int)(sy*this.getHeight());
        int [][] New = new int[newW][newH];
        for (int i = 0; i < newW; i++){
            for (int j = 0; j < newH; j++){
                New[i][j] = _map[(int)(i/sx)][(int)(j/sy)];
            }
        }
        _map = New;
    }

    @Override
    public void drawCircle(Pixel2D center, double rad, int color) {
        for (int i = 0; i < _map.length; i++) {
            for (int j = 0; j < _map[0].length; j++) {
                Index2D temp = new Index2D(i,j);
                if (temp.distance2D(center)<rad)
                    _map[i][j] = color;
            }
        }
    }

    @Override
    public void drawLine(Pixel2D p1, Pixel2D p2, int color) {
        int x = p1.getX();
        int y = p1.getY();
        int stepX = -1;
        int stepY = -1;
        if (x<p2.getX())
            stepX = 1;
        if (y<p2.getY())
            stepY = 1;
        int dx = p2.getX() - x;
        int dy = -1*(p2.getY() - y);
        int error = dx+dy;

        while ((x != p2.getX())&&(y != p2.getY())){
            _map[x][y] = color;
            int error2 = 2*error;
            if (error2 >= dy){
                error += dy;
                x += stepX;
            }
            if (error2 <= dx){
                error += dx;
                y += stepY;
            }
        }
        _map[p2.getX()][p2.getY()] = color;
    }

    @Override
    public void drawRect(Pixel2D p1, Pixel2D p2, int color) {
        int x1 = Math.min(p1.getX(), p2.getX());
        int y1 = Math.min(p1.getY(), p2.getY());
        int x2 = Math.max(p1.getX(), p2.getX());
        int y2 = Math.max(p1.getY(), p2.getY());
        for (int i = x1; i <= x2; i++){
            for (int j = y1; j <= y2; j++){
                _map[i][j] = color;
            }
        }
    }

    @Override
    public boolean equals(Object ob) {
        if (ob.getClass() == this.getClass()){
            if ((this.getWidth() != ((Map) ob).getWidth())&&(this.getHeight() != (((Map)ob).getWidth())))
                return false; //not the same dimensions
            for (int i = 0; i <= _map.length; i++) {
                for (int j = 0; j <= _map[0].length; j++) {
                    if (((Map) ob)._map[i][j] != this._map[i][j])
                        return false; //not the same values
                }
            }
            return true;
        }
        return false; //not the same class
    }
	@Override
	/** 
	 * Fills this map with the new color (new_v) starting from p.
	 * https://en.wikipedia.org/wiki/Flood_fill
	 */
	public int fill(Pixel2D xy, int new_v,  boolean cyclic) {
		int ans = -1;

		return ans;
	}

	@Override
	/**
	 * BFS like shortest the computation based on iterative raster implementation of BFS, see:
	 * https://en.wikipedia.org/wiki/Breadth-first_search
	 */
	public Pixel2D[] shortestPath(Pixel2D p1, Pixel2D p2, int obsColor, boolean cyclic) {
		Pixel2D[] ans = null;  // the result.

		return ans;
	}
    @Override
    public Map2D allDistance(Pixel2D start, int obsColor, boolean cyclic) {
        Map2D ans = null;  // the result.

        return ans;
    }
    public static boolean validMap(int[][] map){
        try{
            int temp = map[0].length;
            for (int i = 1; i < map.length; i++){ //1x3 maps are allowed and in those cases the array cant be unequal in lengths
                if ((temp == 0)||(map[i].length == 0))
                    throw new RuntimeException("Array length is 0");
                if (map[i].length != temp)
                    throw new RuntimeException("Array length is inconsistent (map is not a rectangle)");
            }
            return true;
        }
        catch (NullPointerException error){
            throw new RuntimeException("Map contains an empty array");
        }
    }
	////////////////////// Private Methods ///////////////////////

}
