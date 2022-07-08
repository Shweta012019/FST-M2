package Activities;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Activity1 {
    // Test fixtures
    static ArrayList<String> list;

    // Initialize test fixtures before each test method
    @BeforeEach
    void setUp() throws Exception {
        list = new ArrayList<String>();
        list.add("alpha"); // at index 0
        list.add("beta"); // at index 1
    }
    @Test
    void InsertTest(){
        // Assert size of list
        assertEquals(2, list.size(), "wrong size");
        //insert new element
        list.add("new");
        assertEquals(3,list.size(),"wrong size");
       // Assert individual elements
        assertEquals("alpha", list.get(0), "Wrong element at first position");
        // Assert individual elements
        assertEquals("beta", list.get(1), "Wrong element at second position");
        // Assert individual elements
        assertEquals("new", list.get(2), "matching element at 3rd position");
    }

    // Test method to test the replace operation
    @Test
    public void replaceTest() {
        // Replace new element
        list.set(1, "charlie");
        // Assert size of list
        assertEquals(2, list.size(), "Wrong size");
        // Assert individual elements
        assertEquals("alpha", list.get(0), "wrong element");
        assertEquals("charlie", list.get(1), "Wrong element");

    }
}