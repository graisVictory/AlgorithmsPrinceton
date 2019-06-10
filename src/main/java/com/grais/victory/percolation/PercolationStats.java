package com.grais.victory.percolation;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private Percolation percolation;
    private double mean;
    private double stdDev;
    private double[] confidence = new double[2];

    public PercolationStats(int size, int expCount) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size should be positive non-zero number");
        }
        if (expCount <= 0) {
            throw new IllegalArgumentException("Experiments count should be positive non-zero number");
        }
        performExperiments(size, expCount);
    }

    private void performExperiments(int size, int expCount) {
        int gridSize = size * size;
        percolation = new Percolation(size);
        double[] fractions = new double[expCount];
        for (int i = 0; i < expCount; i++) {
            while (!percolation.percolates()) {
                int row = StdRandom.uniform(0, size);
                int col = StdRandom.uniform(0, size);
                percolation.open(row, col);
            }
            fractions[i] = (double) percolation.numberOfOpenSites() / gridSize;
        }
        mean = StdStats.mean(fractions);
        stdDev = StdStats.stddev(fractions);
        confidence[0] = mean - (1.96 * stdDev / Math.sqrt(expCount));
        confidence[1] = mean + (1.96 * stdDev / Math.sqrt(expCount));
    }

    public double mean() {
        return mean;
    }

    public double stdDev() {
        return stdDev;
    }

    public double confidenceHi() {
        return confidence[1];
    }

    public double confidenceLo() {
        return confidence[0];
    }

    public static void main(String[] args) {
        int n = Integer.valueOf(args[0]);
        int t = Integer.valueOf(args[1]);

        PercolationStats percolationStats = new PercolationStats(n, t);
        System.out.println("mean = " + percolationStats.mean());
        System.out.println("stddev = " + percolationStats.stdDev());
        System.out.println("95% confidence interval = ["
                + percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi() + "}");

    }

}
