package com.grais.victory.percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[][] data;
    private WeightedQuickUnionUF quickUnionUF;
    private int top = 0;
    private int bottom;
    private int openSitesCount = 0;

    public Percolation(int size) {
        data = new boolean[size][size];
        quickUnionUF = new WeightedQuickUnionUF(size * size + 2);
        bottom = size * size + 1;
    }

    public void open(int row, int col) {
        data[row][col] = true;
        ++openSitesCount;
        int lastIndex = data.length - 1;
        if (row == 0) {
            quickUnionUF.union(getIndex(row, col), top);
        }
        if (row == lastIndex) {
            quickUnionUF.union(getIndex(row, col), bottom);
        }
        if (row > 0 && isOpen(row - 1, col)) {
            quickUnionUF.union(getIndex(row, col), getIndex(row - 1, col));
        }
        if (row < lastIndex && isOpen(row + 1, col)) {
            quickUnionUF.union(getIndex(row, col), getIndex(row + 1, col));
        }
        if (col > 0 && isOpen(row, col - 1)) {
            quickUnionUF.union(getIndex(row, col), getIndex(row, col - 1));
        }
        if (col < lastIndex && isOpen(row, col + 1)) {
            quickUnionUF.union(getIndex(row, col), getIndex(row, col + 1));
        }
    }

    public boolean isOpen(int row, int col) {
        return data[row][col];
    }

    public boolean isFull(int row, int col) {
        if (row == 0)
            return true;
        return quickUnionUF.connected(getIndex(row, col), top);
    }

    private int getIndex(int row, int col) {
        return row * data.length + col + 1;
    }

    public int numberOfOpenSites() {
        return openSitesCount;
    }

    public boolean percolates() {
        return quickUnionUF.connected(top, bottom);
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
