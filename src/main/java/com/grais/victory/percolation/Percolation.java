package com.grais.victory.percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[][] data;
    private WeightedQuickUnionUF quickUnionUF;

    public Percolation(int size) {
        data = new boolean[size][size];
        quickUnionUF = new WeightedQuickUnionUF(size * size);
    }

    public void open(int row, int col) {
        data[row][col] = true;
        if (row > 0 && isOpen(row - 1, col)) {
            quickUnionUF.union(getIndex(row, col), getIndex(row - 1, col));
        }
        if (row < data.length - 1 && isOpen(row + 1, col)) {
            quickUnionUF.union(getIndex(row, col), getIndex(row + 1, col));
        }
        if (col > 0 && isOpen(row, col - 1)) {
            quickUnionUF.union(getIndex(row, col), getIndex(row, col - 1));
        }
        if (col < data.length - 1 && isOpen(row, col + 1)) {
            quickUnionUF.union(getIndex(row, col), getIndex(row, col + 1));
        }
    }

    public boolean isOpen(int row, int col) {
        return data[row][col];
    }

    public boolean isFull(int row, int col) {
        if (row == 0)
            return true;
        for (int i = 0; i < data.length; i++) {
            if (quickUnionUF.connected(getIndex(row, col), getIndex(0, i)))
                return true;
        }
        return false;
    }

    private int getIndex(int row, int col) {
        return row * data.length + col;
    }

    public int numberOfOpenSites() {
        int count = 0;
        for (boolean[] rows : data) {
            for (boolean site : rows) {
                count += site ? 1 : 0;
            }
        }
        return count;
    }

    public boolean percolates() {
        int topStartIndex = getIndex(0, 0);
        int topEndIndex = getIndex(0, data.length - 1);
        int bottomStartIndex = getIndex(data.length - 1, 0);
        int bottomEndIndex = getIndex(data.length - 1, data.length - 1);
        for (int i = bottomStartIndex; i <= bottomEndIndex; i++) {
            for (int j = topStartIndex; j <= topEndIndex; j++) {
                if (quickUnionUF.connected(i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Percolation percolation = new Percolation(10);
        percolation.open(0, 1);
        percolation.open(1, 1);
        System.out.println(percolation.numberOfOpenSites());
        System.out.println(percolation.percolates());
        System.out.println("------------------------------");
        percolation.open(2,2);
        System.out.println(percolation.isFull(2,2));
        System.out.println("------------------------------");
        percolation.open(2, 1);
        percolation.open(3, 1);
        percolation.open(4, 1);
        percolation.open(5, 1);
        System.out.println(percolation.isFull(5,1));
        percolation.open(6, 1);
        percolation.open(7, 1);
        percolation.open(8, 1);
        percolation.open(9, 1);
        System.out.println("------------------------------");
        System.out.println(percolation.percolates());
    }

}
