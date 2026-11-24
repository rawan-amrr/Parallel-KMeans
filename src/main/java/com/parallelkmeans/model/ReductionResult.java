
package com.parallelkmeans.model;

public class ReductionResult {

    private final int[] assignments;
    private final double[][] clusterSums;
    private final int[] clusterCounts;

    public ReductionResult(
            int[] assignments,
            double[][] clusterSums,
            int[] clusterCounts) {

        if (assignments == null ||
            clusterSums == null ||
            clusterCounts == null) {
            throw new IllegalArgumentException(
                "Reduction result data cannot be null."
            );
        }

        if (clusterSums.length == 0 ||
            clusterSums.length != clusterCounts.length) {
            throw new IllegalArgumentException(
                "Invalid number of clusters."
            );
        }

        int dimensions = -1;

        for (double[] clusterSum : clusterSums) {

            if (clusterSum == null || clusterSum.length == 0) {
                throw new IllegalArgumentException(
                    "Cluster sums cannot contain empty or null rows."
                );
            }

            if (dimensions == -1) {
                dimensions = clusterSum.length;
            } else if (clusterSum.length != dimensions) {
                throw new IllegalArgumentException(
                    "All cluster sums must have the same dimensions."
                );
            }

            for (double value : clusterSum) {
                if (!Double.isFinite(value)) {
                    throw new IllegalArgumentException(
                        "Cluster sums must contain finite numbers."
                    );
                }
            }
        }

        int[] actualCounts = new int[clusterCounts.length];

        for (int assignment : assignments) {

            if (assignment < 0 ||
                assignment >= clusterCounts.length) {
                throw new IllegalArgumentException(
                    "Invalid cluster assignment."
                );
            }

            actualCounts[assignment]++;
        }

        for (int i = 0; i < clusterCounts.length; i++) {

            if (clusterCounts[i] < 0) {
                throw new IllegalArgumentException(
                    "Cluster counts cannot be negative."
                );
            }

            if (clusterCounts[i] != actualCounts[i]) {
                throw new IllegalArgumentException(
                    "Cluster counts do not match assignments."
                );
            }
        }

        for (int i = 0; i < clusterCounts.length; i++) {
    if (clusterCounts[i] == 0) {
        for (double sum : clusterSums[i]) {
            if (sum != 0.0) {
                throw new IllegalArgumentException(
                    "Empty clusters must have zero coordinate sums."
                );
            }
        }
    }
}


        this.assignments = assignments.clone();
        this.clusterSums = deepCopy(clusterSums);
        this.clusterCounts = clusterCounts.clone();
    }

    public int[] getAssignments() {
        return assignments.clone();
    }

    public double[][] getClusterSums() {
        return deepCopy(clusterSums);
    }

    public int[] getClusterCounts() {
        return clusterCounts.clone();
    }

    private double[][] deepCopy(double[][] array) {

        double[][] copy = new double[array.length][];

        for (int i = 0; i < array.length; i++) {
            copy[i] = array[i].clone();
        }

        return copy;
    }
}
