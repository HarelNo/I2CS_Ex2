package assignments.Ex2;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Intro2CS_2026A
 * This class represents a Graphical User Interface (GUI) for Map2D.
 * The class has save and load functions, and a GUI draw function.
 * You should implement this class, it is recommender to use the StdDraw class, as in:
 * https://introcs.cs.princeton.edu/java/stdlib/javadoc/StdDraw.html
 *
 *
 */
public class Ex2_GUI {
    public static void drawMap(Map2D map) {
        int[][] mat = loadMap("example.txt").getMap();
        StdDraw.setScale(0, 1);
        StdDraw.clear();
        for(int y=0;y<mat.length;y++) {
            for(int x=0;x<mat[0].length;x++) {
                int v = mat[y][x];
                if(v==0) {StdDraw.setPenColor(StdDraw.BLACK);}
                else {StdDraw.setPenColor(StdDraw.BOOK_RED);}
                double r = 1/15.0;
                double x1 = 0.1+2.2*r*x;
                double y1 = 1-(0.1+2.2*r*y);
                StdDraw.filledSquare(x1,y1,r);
            }
        }
        StdDraw.show();
        StdDraw.pause(2);
    }

    /**
     * The map will be stored with the first row containing the width value
     * from then on each line will represent an array containing one column from the 2D array which combine to form the map
     * @param mapFileName
     * @return
     */
    public static Map2D loadMap(String mapFileName) {
       try {
           File mapText = new File(mapFileName);
           Scanner reader = new Scanner(mapText);
           int x = Integer.parseInt(reader.nextLine());
           int[][] map = new int[x][];
           int i = 0;
           while (reader.hasNextLine()){
               String current = reader.nextLine();
               String[] input = current.split(",");
               map[i] = new int[input.length];
               for (int j = 0; j < input.length; j++){
                   map[i][j] = Integer.parseInt(input[j].trim());
               }
               i++;
           }
           return new Map(map);
       }
       catch (FileNotFoundException error){
           throw new RuntimeException("could not load the file");
       }
    }

    /**
     *
     * @param map
     * @param mapFileName
     */
    public static void saveMap(Map2D map, String mapFileName) {
        try {
            FileWriter writer = new FileWriter(mapFileName);
            writer.write(map.getWidth()+"\n"+map.getHeight()+"\n");
            for (int i = 0; i < map.getMap().length; i++){
                for (int j = 0; j < map.getMap()[0].length; j++){
                    writer.write(map.getMap()[i][j]);
                    if (j < map.getMap()[0].length-1){
                        writer.write(",");
                    } else {
                        writer.write("\n");
                    }
                }
            }
        }catch (IOException error){
            throw new RuntimeException("File exists but is not a text file, cannot be created or cannot be opened");
        }
    }
    public static void main(String[] a) {
        String mapFile = "example.txt";
        Map2D map = loadMap(mapFile);
        drawMap(map);
    }
    /// ///////////// Private functions ///////////////
}
