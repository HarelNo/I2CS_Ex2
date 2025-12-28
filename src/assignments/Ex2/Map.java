package assignments.Ex2;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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

    /**
     * This function initializes _map and fills its value with the parameter v.
     * @param w the width of the underlying 2D array.
     * @param h the height of the underlying 2D array.
     * @param v the init value of all the entries in the 2D array.
     */
	@Override
	public void init(int w, int h, int v) {
        _map = new int[w][h];
        for (int i = 0; i < _map.length; i++){
            for (int j = 0; j < _map[w].length; j++){
                _map[i][j] = v;
            }
        }
	}

    /**
     * This function checks if the 2D array can be used as a map and if able, initializes the _map value with a deep copt of arr
     * If the parameter arr cannot be used as a map the function will throw a RuntimeException error.
     * @param arr a 2D int array.
     */
	@Override
	public void init(int[][] arr) {
        if (validMap(arr)){
            _map = new int[arr.length][arr[0].length];
            for (int i = 0; i < _map.length; i++)
                 for (int j = 0; j < _map[i].length; j++)
                     _map[i][j] = arr[i][j];
            }
	}

    /**
     * Simple get function retuning the _map value as a 2D integer array.
     * @return the map represented as a 2D array.
     */
	@Override
	public int[][] getMap() {
		return this._map;
	}

    /**
     * Simple function that returns the Width of the map (being represented by the X axis).
     * @return int representing the width of the map.
     */
	@Override
	public int getWidth() {
        return this._map.length;
    }

    /**
     * Simple function that returns the Height of the map (being represented by the Y axis).
     * @return int representing the height of the map.
     */
	@Override
	public int getHeight() {
        return this._map[0].length;
    }

    /**
     * This functions returns the int value at the given coordinate on the map represented by its x and y values ([x][y).
     * @param x the x coordinate
     * @param y the y coordinate
     * @return int value at a specific coordinate in the map.
     */
	@Override
	public int getPixel(int x, int y) {
        try{
            return this._map[x][y];
        }catch (NullPointerException error){
            throw new RuntimeException("The provided pixel is out of the map's scope");
        }
    }

    /**
     * This function returns the int value at the given coordinate on the map  represented by a pixel containing its x and y values ([x][y])
     * @param p the x,y coordinate
     * @return int value at a specific coordinate in the map.
     */
	@Override
	public int getPixel(Pixel2D p) {
        return getPixel(p.getX(),p.getY());
	}

    /**
     * This function changes the color of a specific coordinate represented by x and y to the value v.
     * @param x the x coordinate
     * @param y the y coordinate
     * @param v the value that the entry at the coordinate [x][y] is set to.
     */
	@Override
	public void setPixel(int x, int y, int v) {
        validMap(this._map);
        try {
            this._map[x][y] = v;
        }catch (NullPointerException error) {
            throw new RuntimeException("The provided pixel is out of the map's scope");
        }
    }

    /**
     * This function changes the color of a specific coordinate represented by a pixel to the value v.
     * @param p the coordinate in the map.
     * @param v the value that the entry at the coordinate [p.x][p.y] is set to.
     */
	@Override
	public void setPixel(Pixel2D p, int v) {
        this.setPixel(p.getX(),p.getY(),v);
	}

    /**
     * This function returns true if the given pixel's coordinates are withing the map.
     * @param p the 2D coordinate.
     * @return true if the pixel is in the map and false if not.
     */
    @Override
    public boolean isInside(Pixel2D p) {
        try {
            int temp = this._map[p.getX()][p.getY()];
        }catch (NullPointerException error){
            return false;
        }
        return true;
    }

    /**
     * This function returns true if the dimensions of the Map2D are identical to the dimension of the map p.
     * @param p
     * @return
     */
    @Override
    public boolean sameDimensions(Map2D p) {
        return (this._map.length == p.getMap().length) && (this._map[0].length == p.getMap()[0].length);
    }

    /**
     * This function adds the int value of p to the map if p has the same dimensions as the map.
     * If not this function does nothing.
     * @param p - the map that should be added to this map (just in case they have the same dimensions).
     */
    @Override
    public void addMap2D(Map2D p) {
        if (this.sameDimensions(p)){
            for (int i = 0; i < _map.length; i++)
                for (int j = 0; j < _map[i].length; j++)
                    this._map[i][j] += p.getMap()[i][j];
        }
    }

    /**
     * This function multiplies every single pixel's value with a given scalar
     * @param scalar
     */
    @Override
    public void mul(double scalar) {
        int mul = (int)scalar;
        for (int i = 0; i < _map.length; i++)
            for (int j = 0; j < _map[i].length; j++)
                this._map[i][j] *= mul;
    }

    /**
     * This function rescales the map using a multiplier for the x-axis (sx) and a multiplier for the y-axis (xy).
     * @param sx
     * @param sy
     */
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

    /**
     * this function draws a circle on the map around a given point with a given radius.
     * Currently inefficient as it scans the whole map for each circle.
     * @param center
     * @param rad
     * @param color - the (new) color to be used in the drawing.
     */
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

    /**
     * This function draws a line between two given coordinates with a given color represented as an int value.
     * I used the Bresenham's Line Algorithm. (https://en.wikipedia.org/wiki/Bresenham%27s_line_algorithm)
     * @param p1
     * @param p2
     * @param newColor - the (new) color to be used in the drawing.
     */
    @Override
    public void drawLine(Pixel2D p1, Pixel2D p2, int newColor) {
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
            _map[x][y] = newColor;
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
        _map[p2.getX()][p2.getY()] = newColor;
    }

    /**
     * This function draws a rectangle with two given pixels as the limiting corners.
     * The function first creates the lowest and highest corner (minimum x and y values) and then draws a rectangle limited by those to cover all positioning cases)
     * @param p1
     * @param p2
     * @param newColor - the (new) color to be used in the drawing.
     */
    @Override
    public void drawRect(Pixel2D p1, Pixel2D p2, int newColor) {
        int x1 = Math.min(p1.getX(), p2.getX());
        int y1 = Math.min(p1.getY(), p2.getY());
        int x2 = Math.max(p1.getX(), p2.getX());
        int y2 = Math.max(p1.getY(), p2.getY());
        for (int i = x1; i <= x2; i++){
            for (int j = y1; j <= y2; j++){
                _map[i][j] = newColor;
            }
        }
    }

    /**
     * This function returns True if the given object is identical to the Map2D.
     * step 1) Check if the Object is a Map2D.
     * step 2) Check if the Map2D (formerly Object) has the same dimensions as the original Map2D.
     * step 3) Check if every single pixel in the map is identical to its counterpart.
     * @param ob the reference object with which to compare.
     * @return true or false
     */
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
     * This functions uses the queue method given in the wiki page.
	 * https://en.wikipedia.org/wiki/Flood_fill
     *
     * the queue takes the starting point, colors it and then checks for its neighbors for pixels sharing the same color.
     * once it finds such neighbors it adds them to the queue.
     * then it repeats the process for each pixel in the queue until there are no more pixels connected with the same color.
	 */
	public int fill(Pixel2D xy, int new_v,  boolean cyclic) { //for this I assume connected means 4 cardinal directions and not 8 (diagonals)
		int ans = 0;
        int oldColor = this._map[xy.getX()][xy.getY()];
        if (oldColor == new_v)
            return 0;
        Queue<Pixel2D> queue = new LinkedList<Pixel2D>();
        Pixel2D start = new Index2D(xy);
        this._map[start.getX()][start.getY()] = new_v;
        queue.add(start);
        while (queue.peek() != null){
            ans++;
            if (queue.peek().getX() != 0){
                if (this._map[queue.peek().getX()-1][queue.peek().getY()] == oldColor){
                    Pixel2D temp = new Index2D(queue.peek().getX()-1,queue.peek().getY());
                    this._map[temp.getX()][temp.getY()] = new_v;
                    queue.add(temp);
                }
            } else if (cyclic) {
                if (this._map[this._map.length-1][queue.peek().getY()] == oldColor){
                    Pixel2D temp = new Index2D(this._map.length-1,queue.peek().getY());
                    this._map[temp.getX()][temp.getY()] = new_v;
                    queue.add(temp);
                }
            }
            if (queue.peek().getX() != this._map.length-1){
                if (this._map[queue.peek().getX()+1][queue.peek().getY()] == oldColor){
                    Pixel2D temp = new Index2D(queue.peek().getX()+1,queue.peek().getY());
                    this._map[temp.getX()][temp.getY()] = new_v;
                    queue.add(temp);
                }
            } else if (cyclic) {
                if (this._map[0][queue.peek().getY()] == oldColor){
                    Pixel2D temp = new Index2D(0,queue.peek().getY());
                    this._map[temp.getX()][temp.getY()] = new_v;
                    queue.add(temp);
                }
            }
            if (queue.peek().getY() != 0){
                if (this._map[queue.peek().getX()][queue.peek().getY()-1] == oldColor){
                    Pixel2D temp = new Index2D(queue.peek().getX(),queue.peek().getY()-1);
                    this._map[temp.getX()][temp.getY()] = new_v;
                    queue.add(temp);
                }
            } else if (cyclic) {
                if (this._map[queue.peek().getX()][this._map[0].length-1] == oldColor){
                    Pixel2D temp = new Index2D(queue.peek().getX(),this._map[0].length-1);
                    this._map[temp.getX()][temp.getY()] = new_v;
                    queue.add(temp);
                }
            }
            if (queue.peek().getY() != this._map[0].length-1){
                if (this._map[queue.peek().getX()][queue.peek().getY()+1] == oldColor){
                    Pixel2D temp = new Index2D(queue.peek().getX(),queue.peek().getY()+1);
                    this._map[temp.getX()][temp.getY()] = new_v;
                    queue.add(temp);
                }
            } else if (cyclic) {
                if (this._map[queue.peek().getX()][0] == oldColor){
                    Pixel2D temp = new Index2D(queue.peek().getX(),0);
                    this._map[temp.getX()][temp.getY()] = new_v;
                    queue.add(temp);
                }
            }
            queue.remove();
        }
        return ans;
	}

	@Override
	/**
     * This function uses a map generated by the allDistance function, then finds a path by following ever decreasing numbers until it reaches the starting point while documenting its path.
	 * BFS like shortest the computation based on iterative raster implementation of BFS, see:
	 * https://en.wikipedia.org/wiki/Breadth-first_search
	 */
	public Pixel2D[] shortestPath(Pixel2D p1, Pixel2D p2, int obsColor, boolean cyclic) {
        int[][] pathMap = this.allDistance(p1, obsColor, cyclic).getMap();
        if (pathMap[p1.getX()][p1.getY()] == -1)
            return null;
        int p = pathMap[p2.getX()][p2.getY()]+1;
        Pixel2D temp = new Index2D(p2);
        Pixel2D[] ans = new Pixel2D[p];
        ans[p-1] = new Index2D(p2);
        while (ans[0] == null){
            if (temp.getX() != 0){
                if (pathMap[temp.getX()-1][temp.getY()] == pathMap[temp.getX()][temp.getY()]-1){
                    ans[p-1] = new Index2D(temp.getX()-1, temp.getY());
                    p--;
                    temp = ans[p-1];
                    continue;
                }
            } else if (cyclic) {
                if (pathMap[pathMap.length-1][temp.getY()] == pathMap[temp.getX()][temp.getY()]-1){
                    ans[p-1] = new Index2D(pathMap.length-1, temp.getY());
                    p--;
                    temp = ans[p-1];
                    continue;
                }
            }
            if (temp.getX() != pathMap.length-1){
                if (pathMap[temp.getX()+1][temp.getY()] == pathMap[temp.getX()][temp.getY()]-1) {
                    ans[p-1] = new Index2D(temp.getX() + 1, temp.getY());
                    p--;
                    temp = ans[p-1];
                    continue;
                }
            } else if (cyclic) {
                if (pathMap[0][temp.getY()] == pathMap[temp.getX()][temp.getY()]-1){
                    ans[p-1] = new Index2D(0, temp.getY());
                    p--;
                    temp = ans[p-1];
                    continue;
                }
            }
            if (temp.getY() != 0){
                if (pathMap[temp.getX()][temp.getY()-1] == pathMap[temp.getX()][temp.getY()]-1) {
                    ans[p-1] = new Index2D(temp.getX(),temp.getY()-1);
                    p--;
                    temp = ans[p-1];
                    continue;
                }
            } else if (cyclic) {
                if (pathMap[temp.getX()][pathMap[0].length-1] == pathMap[temp.getX()][temp.getY()]-1){
                    ans[p-1] = new Index2D(temp.getX(),pathMap[0].length-1);
                    p--;
                    temp = ans[p-1];
                    continue;
                }
            }
            if (temp.getY() != pathMap[0].length-1){
                if (pathMap[temp.getX()][temp.getY()+1] == pathMap[temp.getX()][temp.getY()]-1){
                    ans[p-1] = new Index2D(temp.getX(),temp.getY()+1);
                    p--;
                    temp = ans[p-1];
                    continue;
                }
            } else if (cyclic) {
                if (pathMap[temp.getX()][0] == pathMap[temp.getX()][temp.getY()]-1){
                    ans[p-1] = new Index2D(temp.getX(),0);
                    p--;
                    temp = ans[p-1];
                    continue;
                }
            }
        }
		return ans;
	}

    /**
     * This function draws a map containing only obstacles
     * and then uses an algorithm identical to the fill function except instead of filling with a specific color
     * it fills each tile with its distance to the starting position (how many steps the algorithm took to get to said pixel).
     * Then the function returns the newly drawn map with -1 representing unreachable pixels and obstacles and a number representing the number of steps it took to get to the pixel from the start.
     * @param start the source (starting) point
     * @param obsColor the color representing obstacles
     * @param cyclic
     * @return
     */
    @Override
    public Map2D allDistance(Pixel2D start, int obsColor, boolean cyclic) { //in here I assume the starting pixel does not start at an obstacle
        int[][] pathMap = this.getMap();
        for (int i = 0; i < pathMap.length; i++) {
            for (int j = 0; j < pathMap[0].length; j++) {
                if (pathMap[i][j] == obsColor)
                    pathMap[i][j] = -1;
                else
                    pathMap[i][j] = -2;
            } //making a new empty map with -1 representing obstacles
        }
            Queue<Pixel2D> queue = new LinkedList<Pixel2D>();
            queue.add(start);
            pathMap[start.getX()][start.getY()] = 0;
            while (queue.peek() != null){
                if (queue.peek().getX() != 0){
                    if (pathMap[queue.peek().getX()-1][queue.peek().getY()] == -2){
                        Pixel2D temp = new Index2D(queue.peek().getX()-1,queue.peek().getY());
                        pathMap[temp.getX()][temp.getY()] = pathMap[queue.peek().getX()][queue.peek().getY()]+1;
                        queue.add(temp);
                    }
                } else if (cyclic) {
                    if (pathMap[pathMap.length-1][queue.peek().getY()] == -2){
                        Pixel2D temp = new Index2D(pathMap.length-1,queue.peek().getY());
                        pathMap[temp.getX()][temp.getY()] = pathMap[queue.peek().getX()][queue.peek().getY()]+1;
                        queue.add(temp);
                    }
                }
                if (queue.peek().getX() != pathMap.length-1){
                    if (pathMap[queue.peek().getX()+1][queue.peek().getY()] == -2){
                        Pixel2D temp = new Index2D(queue.peek().getX()+1,queue.peek().getY());
                        pathMap[temp.getX()][temp.getY()] = pathMap[queue.peek().getX()][queue.peek().getY()]+1;
                        queue.add(temp);
                    }
                } else if (cyclic) {
                    if (pathMap[0][queue.peek().getY()] == -2){
                        Pixel2D temp = new Index2D(0,queue.peek().getY());
                        pathMap[temp.getX()][temp.getY()] = pathMap[queue.peek().getX()][queue.peek().getY()]+1;
                        queue.add(temp);
                    }
                }
                if (queue.peek().getY() != 0){
                    if (pathMap[queue.peek().getX()][queue.peek().getY()-1] == -2){
                        Pixel2D temp = new Index2D(queue.peek().getX(),queue.peek().getY()-1);
                        pathMap[temp.getX()][temp.getY()] = pathMap[queue.peek().getX()][queue.peek().getY()]+1;
                        queue.add(temp);
                    }
                } else if (cyclic) {
                    if (pathMap[queue.peek().getX()][pathMap[0].length-1] == -2){
                        Pixel2D temp = new Index2D(queue.peek().getX(),pathMap[0].length-1);
                        pathMap[temp.getX()][temp.getY()] = pathMap[queue.peek().getX()][queue.peek().getY()]+1;
                        queue.add(temp);
                    }
                }
                if (queue.peek().getY() != pathMap[0].length-1){
                    if (pathMap[queue.peek().getX()][queue.peek().getY()+1] == -2){
                        Pixel2D temp = new Index2D(queue.peek().getX(),queue.peek().getY()+1);
                        pathMap[temp.getX()][temp.getY()] = pathMap[queue.peek().getX()][queue.peek().getY()]+1;
                        queue.add(temp);
                    }
                } else if (cyclic) {
                    if (pathMap[queue.peek().getX()][0] == -2){
                        Pixel2D temp = new Index2D(queue.peek().getX(),0);
                        pathMap[temp.getX()][temp.getY()] = pathMap[queue.peek().getX()][queue.peek().getY()]+1;
                        queue.add(temp);
                    }
                }
                queue.remove();
            }
        for (int i = 0; i < pathMap.length; i++) {
            for (int j = 0; j < pathMap[0].length; j++) {
                if (pathMap[i][j] == -2)
                    pathMap[i][j] = -1;
            }
        }
        return new Map(pathMap);
    }
	////////////////////// Private Methods ///////////////////////
    /**
     * This function checks if a given 2D array can be used as a map with 2 criteria:
     * 1) the width and height have to be at least 1.
     * 2) all rows have to be the same length and all columns have to be the same length
     * @param map
     * @return true if the 2D array is valid to be a map.
     */
    private static boolean validMap(int[][] map){
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

}
