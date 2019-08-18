package utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class UtilTest {

    @Test
    public void testIsPolindrome() {
        String s1 = "abc";
        String s2 = "a";
        String s3 = "abba";
        String s4 = "";

        assertFalse(Util.isPolindrome(s1));
        assertTrue(Util.isPolindrome(s2));
        assertTrue(Util.isPolindrome(s3));
        assertTrue(Util.isPolindrome(s4));
    }
}