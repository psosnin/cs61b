public class Palindrome {

    /* Given a string, return a Deque where the characters appear in the same order as in the string */
    public Deque<Character> wordToDeque(String word) {
        if (word == null) {
            return null;
        }
        Deque<Character> deque = new LinkedListDeque<Character>();
        for (char ch: word.toCharArray()) {
            deque.addLast(ch);
        }
        return deque;
    }

    /* Returns true if the given word is a palindrome, false otherwise */
    public boolean isPalindrome(String word) {
        Deque<Character> deque = wordToDeque(word);
        return isPalindromeHelper(deque);
    }

    /* Returns true if the given deque contains a word that is a palindrome, false otherwise */
    private static boolean isPalindromeHelper(Deque<Character> deque) {
        if (deque.size() < 2) {
            return true;
        } else if (deque.removeFirst() != deque.removeLast()) {
            return false;
        } else {
            return isPalindromeHelper(deque);
        }
    }

    /* Returns true if the given string is a palindrome according
    to the given character comparator, false otherwise */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> deque = wordToDeque(word);
        return isPalindromeHelper(deque, cc);
    }

    /* Returns true if the given deque contains a word that is a palindrome
    according to the given character comparator, false otherwise */
    private static boolean isPalindromeHelper(Deque<Character> deque, CharacterComparator cc) {
        if (deque.size() < 2) {
            return true;
        } else if (!cc.equalChars(deque.removeFirst(), deque.removeLast())) {
            return false;
        } else {
            return isPalindromeHelper(deque, cc);
        }
    }
}
