import static org.junit.Assert.*;
import org.junit.Test;

public class FlikTest {

    @Test
    public void TestIsSameNumber() {
        int a = 128;
        int b = 128;
        int c = 2;
        assertTrue(Flik.isSameNumber(a, b));
        //assertEquals(false, Flik.isSameNumber(c, a));
    }

    public static void main(String... args) {
        jh61b.junit.TestRunner.runTests("all", FlikTest.class);
    }
}

