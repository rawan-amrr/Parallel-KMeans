
package com.parallelkmeans.data;

import com.parallelkmeans.model.Point;
import java.util.List;

/**
 * Provides shared, reproducible datasets for benchmarking.
 */
public final class BenchmarkDatasets {

    public static final long SEED = 42L;
    public static final int DIMENSIONS = 10;
    public static final int TRUE_CLUSTERS = 10;

    public static final List<Integer> SIZES = List.of(
    10_000, 100_000, 1_000_000
);

public static final List<Integer> K_VALUES = List.of(
    3, 5, 10, 20
);

    private BenchmarkDatasets() {
      
    }

    public static List<Point> create(int numPoints) {

        DataGenerator generator = new DataGenerator();

        return generator.generate(
            numPoints,
            DIMENSIONS,
            TRUE_CLUSTERS,
            SEED
        );
    }
}
