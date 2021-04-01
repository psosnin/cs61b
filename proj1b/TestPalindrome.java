import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    static CharacterComparator offByOne = new OffByOne();
    static CharacterComparator offByTen = new OffByN(10);

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        //Test isPalindrome()
        assertTrue(palindrome.isPalindrome("racecar"));
        assertTrue(palindrome.isPalindrome("r"));
        assertTrue(palindrome.isPalindrome(""));
        assertFalse(palindrome.isPalindrome("horse"));
        assertFalse(palindrome.isPalindrome("hi"));

        //Test isPalindrome() with offByOne CharacterComparator
        assertFalse(palindrome.isPalindrome("racecar", offByOne));
        assertTrue(palindrome.isPalindrome("r", offByOne));
        assertTrue(palindrome.isPalindrome("", offByOne));
        assertTrue(palindrome.isPalindrome("flake", offByOne));

        //Test isPalindrome() with offByTen CharacterComparator
        assertFalse(palindrome.isPalindrome("racecar", offByTen));
        assertTrue(palindrome.isPalindrome("r", offByTen));
        assertTrue(palindrome.isPalindrome("", offByTen));
    }
}