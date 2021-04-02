package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import static org.junit.Assert.*;

public class Percolation {

    //holds connections between sites and the top virtual site
    private WeightedQuickUnionUF topConnections;

    //holds connections between sites and both virtual sites to prevent backwash
    private WeightedQuickUnionUF allConnections;

    private boolean[][] openArray;
    private final int top; //index for the top virtual site
    private final int bottom; //index for the bottom virtual site
    private final int size; // the side length of the percolation grid
    private int numOpen;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        size = N;
        top = N*N;
        bottom = N*N + 1;
        topConnections = new WeightedQuickUnionUF(N*N + 1);
        allConnections = new WeightedQuickUnionUF(N*N + 2);
        openArray = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            topConnections.union(top, i);
            allConnections.union(top, i);
            allConnections.union(bottom, oneToTwoD(N - 1, i));
        }
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        isValid(row, col);
        if (openArray[row][col]) {
            return;
        }
        if (col > 0 && openArray[row][col - 1]) {
            topConnections.union(oneToTwoD(row, col), oneToTwoD(row, col - 1));
            allConnections.union(oneToTwoD(row, col), oneToTwoD(row, col - 1));
        }
        if (col < (size - 1) && openArray[row][col + 1]) {
            topConnections.union(oneToTwoD(row, col), oneToTwoD(row, col + 1));
            allConnections.union(oneToTwoD(row, col), oneToTwoD(row, col + 1));
        }
        if (row > 0 && openArray[row - 1][col]) {
            topConnections.union(oneToTwoD(row, col), oneToTwoD(row - 1, col));
            allConnections.union(oneToTwoD(row, col), oneToTwoD(row - 1, col));
        }
        if (row < (size - 1) && openArray[row + 1][col]) {
            topConnections.union(oneToTwoD(row, col), oneToTwoD(row + 1, col));
            allConnections.union(oneToTwoD(row, col), oneToTwoD(row + 1, col));
        }
        openArray[row][col] = true;
        numOpen += 1;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        isValid(row, col);
        return openArray[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        isValid(row, col);
        return topConnections.connected(top, oneToTwoD(row, col)) && isOpen(row, col);
    }

    // number of open sites
    public int numberOfOpenSites() {
        return numOpen;
    }

    // does the system percolate?
    public boolean percolates() {
        return allConnections.connected(top, bottom);
    }

    //some tests for percolation
    public static void main(String[] args) {
    }

    //throws an exception is the given index is invalid
    private void isValid(int row, int col) {
       if (size <= col || col < 0) {
           throw new RuntimeException("Column index is out of bounds, should lie between 0 and " + (size - 1));
       } else if (size <= row || row < 0) {
            throw new RuntimeException("Row index is out of bounds, should lie between 0 and " + (size - 1));
        }
    }

    //converts the 2d index into a 1d index
    private int oneToTwoD(int row, int col) {
        return col + row*size;
    }
}
