package hw2;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private double[] results;
    Percolation percolation;
    StdRandom random;
    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        results = new double[T];
        for (int i = 0; i < T; i++) {
            percolation = pf.make(N);
            while (!percolation.percolates()) {
                percolation.open(StdRandom.uniform(N), StdRandom.uniform(N));
            }
            results[i] = (double) percolation.numberOfOpenSites() / (N*N);
        }
    }

    public static void main(String[] args) {
        PercolationFactory pf = new PercolationFactory();
        PercolationStats pStats = new PercolationStats(30, 100, pf);
        System.out.println(pStats.mean());
        System.out.println(pStats.stddev());
        System.out.println(pStats.confidenceHigh());
        System.out.println(pStats.confidenceLow());

    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(results);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(results);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(results.length);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(results.length);
    }

}
