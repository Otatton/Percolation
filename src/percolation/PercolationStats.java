package percolation;
/*
    CSIS 2420 Algorithms and Data Structures percolation assignment
    @author otatton
 */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] stats;

    public PercolationStats(int n, int t) {
        if (n > 0 && t > 0) {
            this.stats = new double[t];

            for(int i = 0; i < t; ++i) {
                Percolation p = new Percolation(n);

                while(!p.percolates()) {
                    int x = StdRandom.uniform(n);
                    int y = StdRandom.uniform(n);
                    p.open(x, y);
                }

                this.stats[i] = p.numberOfOpenSites() / (double)(n * n);
            }

        } else {
            throw new IllegalArgumentException();
        }
    }

    public double mean() {
        return StdStats.mean(this.stats);
    }

    public double stddev() {
        return StdStats.stddev(this.stats);
    }

    public double confidenceLow() {
        return this.mean() - this.moe();
    }

    private double moe() {
        double sample = (double)this.stats.length;
        double moe = 2.0D * (this.stddev() / Math.sqrt(sample));
        return moe;
    }

    public double confidenceHigh() {
        return this.mean() + this.moe();
    }
}

