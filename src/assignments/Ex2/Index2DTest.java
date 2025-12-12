package assignments.Ex2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Index2DTest {
    final double EPS = 0.001;
    @Test
    public void testGetX(){
        Index2D a = new Index2D(10,20);
        Index2D b = new Index2D(-5, 9);
        Index2D c = new Index2D(b);
        Index2D d = new Index2D();
        assertEquals(a.getX(),10);
        assertEquals(b.getX(),-5);
        assertEquals(c.getX(),-5);
        assertEquals(d.getX(),0);
    }
    @Test
    public void testGetY(){
        Index2D a = new Index2D(10,20);
        Index2D b = new Index2D(-5, 9);
        Index2D c = new Index2D(b);
        Index2D d = new Index2D();
        assertEquals(a.getY(),20);
        assertEquals(b.getY(),9);
        assertEquals(c.getY(),9);
        assertEquals(d.getX(),0);
    }
    @Test
    public void testDistance2D(){
        Index2D a = new Index2D(10,20);
        Index2D b = new Index2D(-5, 9);
        Index2D c = new Index2D(3,14);
        Index2D d = new Index2D(b);
        assertTrue(Math.abs(a.distance2D(b)-18.601075237738)<EPS);
        assertTrue(Math.abs(b.distance2D(c)-9.4339811320566)<EPS);
        assertTrue(Math.abs(c.distance2D(a)-9.2195444572929)<EPS);
        assertTrue(d.distance2D(b)<EPS);
        assertTrue(Math.abs(b.distance2D(a)-a.distance2D(b))<EPS);
    }
    @Test
    public void testToString(){
        Index2D a = new Index2D(10,20);
        Index2D b = new Index2D(-5, 9);
        Index2D c = new Index2D(3,14);
        Index2D d = new Index2D(b);
        assertEquals(a.toString(),"(10,20)");
    }
    @Test
    public void testEquals(){
        Index2D a = new Index2D(10,20);
        int[] b = {10,20};
        Index2D c = a;
        Index2D d = new Index2D(a);
        Index2D e = new Index2D(10,20);
        Index2D f = new Index2D(5,21);
        assertTrue(!(a.equals(b))); //different object types
        assertTrue(a.equals(c)); //pointer copy
        assertTrue(a.equals(d)); //deep copy
        assertTrue(a.equals(e)); //different object true
        assertTrue(!(a.equals(f))); //different object false
    }
}
