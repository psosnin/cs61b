public class OffByN implements CharacterComparator {
    private static int num;

    public OffByN(int N) {
        num = N;
    }

    /** Returns true if characters are numerically off by N*/
    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs(x - y) == num;
    }
}
