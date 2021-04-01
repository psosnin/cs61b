public class OffByOne implements CharacterComparator {
    /** Returns true if characters are numerically off by one*/
    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs(x - y) == 1;
    }
}
