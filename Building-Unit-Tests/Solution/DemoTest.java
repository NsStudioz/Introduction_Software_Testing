import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

/*
 * The class containing your tests for the {@link Demo} class.  Make sure you
 * test all methods in this class (including both 
 * {@link Demo#main(String[])} and 
 * {@link Demo#isTriangle(double, double, double)}).
 */
public class DemoTest {
	
    @Test
    public void test_is_triangle1(){
        assertTrue(Demo.isTriangle(3,4,5));
    }

    @Test
    public void test_is_triangle2(){
        assertTrue(Demo.isTriangle(5,12,13));
    }

    @Test
    public void test_is_triangle3(){
        assertTrue(Demo.isTriangle(5,13,12));
    }

    @Test
    public void test_is_triangle4(){
        assertTrue(Demo.isTriangle(12,5,13));
    }
    
    @Test    
    public void test_is_triangle_5()
    {       
        assertTrue(Demo.isTriangle(12,13,5));    
    }  

    @Test
    public void test_is_NOT_triangle1(){
        assertFalse(Demo.isTriangle(5,7,13));
    }

    @Test
    public void test_is_NOT_triangle2(){
        assertFalse(Demo.isTriangle(13,5,7));
    }

    @Test
    public void test_is_NOT_triangle3(){
        assertFalse(Demo.isTriangle(13,5,7));
    }

    @Test
    public void test_is_NOT_triangle4(){
        assertFalse(Demo.isTriangle(13,7,5));
    }

    @Test
    public void test_is_NOT_triangle5(){
        assertFalse(Demo.isTriangle(13,7,5));
    }

    @Test
    public void test_is_NOT_triangle6(){
        assertFalse(Demo.isTriangle(5,9,3));
    }

    @Test
    public void test_is_NOT_triangle7(){
        assertFalse(Demo.isTriangle(1,2,-1));
    }

    //Testing main method:

    @Test
    public void test_main1() {

        String input = 3 + "\n"+ 4 + "\n" +  5 + "\n";


        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());

        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        System.setOut(new PrintStream(out));



        String[] args = {};

        Demo.main(args);


        String consoleOutput = "Enter side 1: " + System.getProperty("line.separator");

        consoleOutput += "Enter side 2: " + System.getProperty("line.separator");

        consoleOutput += "Enter side 3: " + System.getProperty("line.separator");

        consoleOutput += "This is a triangle." + System.getProperty("line.separator");


        assertEquals(consoleOutput, out.toString());
    }


    @Test
    public void test_main2() {

        String input = 1 + "\n"+ 2 + "\n" +  3 + "\n";


        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());

        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        System.setOut(new PrintStream(out));



        String[] args = {};

        Demo.main(args);


        String consoleOutput = "Enter side 1: " + System.getProperty("line.separator");

        consoleOutput += "Enter side 2: " + System.getProperty("line.separator");

        consoleOutput += "Enter side 3: " + System.getProperty("line.separator");

        consoleOutput += "This is not a triangle." + System.getProperty("line.separator");


        assertEquals(consoleOutput, out.toString());
    }
}
