
package com.parallelkmeans.model;

public class KMeansConfig {

    private final int k;
    private final int maxIterations;
    private final double tolerance;
    private final long seed;
    private final boolean recordHistory;


    public KMeansConfig(
            int k,
            int maxIterations,
            double tolerance,
            long seed) {

        this(k, maxIterations, tolerance, seed, false);
    }


    public KMeansConfig(
            int k,
            int maxIterations,
            double tolerance,
            long seed,
            boolean recordHistory) {

        if (k <= 0) {
            throw new IllegalArgumentException(
                "Number of clusters must be positive."
            );
        }

        if (maxIterations <= 0) {
            throw new IllegalArgumentException(
                "Maximum iterations must be positive."
            );
        }

        if (tolerance < 0 || !Double.isFinite(tolerance)) {
            throw new IllegalArgumentException(
                "Tolerance must be finite and non-negative."
            );
        }

        this.k = k;
        this.maxIterations = maxIterations;
        this.tolerance = tolerance;
        this.seed = seed;
        this.recordHistory = recordHistory;
    }

    public int getK() {
        return k;
    }

    public int getMaxIterations() {
        return maxIterations;
    }

    public double getTolerance() {
        return tolerance;
    }

    public long getSeed() {
        return seed;
    }

    public boolean isRecordHistory() {
        return recordHistory;
    }
}
