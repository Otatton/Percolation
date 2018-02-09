package percolation;

import edu.princeton.cs.algs4.StdRandom;

public class PercTest {
    public PercTest() {
    }

    public static void main(String[] args) {
        int number = 50;
        Percolation perc = new Percolation(number);

        while(!perc.percolates()) {
            int x = StdRandom.uniform(number);
            int y = StdRandom.uniform(number);
            perc.open(x, y);
        }

        PercolationVisualizer.draw(perc, number);
        PercolationStats p = new PercolationStats(200, 100);
        System.out.println(p.mean());
        System.out.println(p.stddev());
        System.out.println(p.confidenceLow());
        System.out.println(p.confidenceHigh());
    }
}

