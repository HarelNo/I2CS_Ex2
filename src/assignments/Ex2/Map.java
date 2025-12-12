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
		int[][] ans = null;

		return ans;
	}
	@Override
	public int getWidth() {
        int ans = -1;

        return ans;
    }
	@Override
	public int getHeight() {
        int ans = -1;

        return ans;
    }
	@Override
	public int getPixel(int x, int y) {
        int ans = -1;

        return ans;
    }
	@Override
	public int getPixel(Pixel2D p) {
        int ans = -1;

        return ans;
	}
	@Override
	public void setPixel(int x, int y, int v) {

    }
	@Override
	public void setPixel(Pixel2D p, int v) {

	}

    @Override
    public boolean isInside(Pixel2D p) {
        boolean ans = true;

        return ans;
    }

    @Override
    public boolean sameDimensions(Map2D p) {
        boolean ans = false;

        return ans;
    }

    @Override
    public void addMap2D(Map2D p) {

    }

    @Override
    public void mul(double scalar) {

    }

    @Override
    public void rescale(double sx, double sy) {

    }

    @Override
    public void drawCircle(Pixel2D center, double rad, int color) {

    }

    @Override
    public void drawLine(Pixel2D p1, Pixel2D p2, int color) {

    }

    @Override
    public void drawRect(Pixel2D p1, Pixel2D p2, int color) {

    }

    @Override
    public boolean equals(Object ob) {
        boolean ans = false;

        return ans;
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
