
package com.parallelkmeans.model;

/**
 * Configuration for parallel execution of K-Means.
 * Contains settings specific to Fork/Join processing.
 */
public class ParallelConfig {

    private final int numThreads;
    private final int chunkThreshold;

    public ParallelConfig(int numThreads, int chunkThreshold) {

        if (numThreads < 0) {
            throw new IllegalArgumentException(
                "Number of threads must be >= 0 (0 = all available processors)."
            );
        }

        if (chunkThreshold <= 0) {
            throw new IllegalArgumentException(
                "Chunk threshold must be positive."
            );
        }

        this.numThreads = numThreads;
        this.chunkThreshold = chunkThreshold;
    }

    public int getNumThreads() {
        return numThreads;
    }

    public int getChunkThreshold() {
        return chunkThreshold;
    }
}
