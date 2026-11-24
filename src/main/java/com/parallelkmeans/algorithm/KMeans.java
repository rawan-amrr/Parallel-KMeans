
package com.parallelkmeans.algorithm;

import java.util.List;

import com.parallelkmeans.model.Point;
import com.parallelkmeans.model.Centroid;
import com.parallelkmeans.model.KMeansConfig;
import com.parallelkmeans.model.KMeansResult;
import com.parallelkmeans.distance.DistanceMetric;

/**
 * Common contract for Sequential and Parallel K-Means implementations.
 *
 * Implementations must not modify the input points or initial centroids.
 * Each implementation must create its own working centroid copies.
 */
public interface KMeans {

    /**
     * Executes the K-Means algorithm.
     *
     * @param points input dataset
     * @param initialCentroids initial cluster centers
     * @param config shared algorithm configuration
     * @param distanceMetric distance calculation strategy
     * @return the final K-Means result
     */
    KMeansResult fit(
        List<Point> points,
        List<Centroid> initialCentroids,
        KMeansConfig config,
        DistanceMetric distanceMetric
    );
}
