package percolation;
/*
    CSIS 2420 Algorithms and Data Structures percolation assignment
    @author otatton
 */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][] grid;
    private int openSites = 0;
    private WeightedQuickUnionUF find;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        } else {
            this.grid = new int[n][n];
            this.find = new WeightedQuickUnionUF(n * n + 2);
        }
    }

    public void open(int i, int j) {
        if (i < this.grid.length && j < this.grid.length) {
            if (this.grid[i][j] == 0) {
                this.grid[i][j] = 1;
                ++this.openSites;
                if (i == 0) {
                    this.find.union(0, this.convert(i, j));
                }

                if (i != 0 && this.isOpen(i - 1, j)) {
                    this.find.union(this.convert(i, j), this.convert(i - 1, j));
                }

                if (j != 0 && this.isOpen(i, j - 1)) {
                    this.find.union(this.convert(i, j), this.convert(i, j - 1));
                }

                if (j != this.grid.length - 1 && this.isOpen(i, j + 1)) {
                    this.find.union(this.convert(i, j), this.convert(i, j + 1));
                }

                if (i != this.grid.length - 1 && this.isOpen(i + 1, j)) {
                    this.find.union(this.convert(i, j), this.convert(i + 1, j));
                }

                if (i == this.grid.length - 1 && this.isFull(i, j)) {
                    this.find.union(this.convert(i, j), this.grid.length * this.grid.length + 1);
                }
            }

        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public boolean isOpen(int i, int j) {
        if (i < this.grid.length && j < this.grid.length) {
            return this.grid[i][j] == 1;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public boolean isFull(int i, int j) {
        if (i < this.grid.length && j < this.grid.length) {
            return this.find.connected(this.convert(i, j), 0);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public boolean percolates() {
        boolean connected = false;
        if (this.find.connected(0, this.grid.length * this.grid.length + 1)) {
            connected = true;
        }

        return connected;
    }

    public double numberOfOpenSites() {
        return (double)this.openSites;
    }

    private int convert(int i, int j) {
        int site = i * this.grid.length + j + 1;
        return site;
    }
}

