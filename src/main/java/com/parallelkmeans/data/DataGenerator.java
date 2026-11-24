package com.parallelkmeans.data;

import com.parallelkmeans.model.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class DataGenerator {

    // Range used to generate random cluster centers.
    private static final double CENTER_RANGE = 100.0;

    // Default Gaussian noise around each cluster center.
    private static final double DEFAULT_STD_DEV = 2.0;

    public List<Point> generate(
            int numPoints,
            int dimensions,
            int numClusters,
            long seed) {

        return generate(
            numPoints,
            dimensions,
            numClusters,
            seed,
            DEFAULT_STD_DEV
        );
    }

    public List<Point> generate(
            int numPoints,
            int dimensions,
            int numClusters,
            long seed,
            double stdDev) {

        if (numPoints <= 0 || dimensions <= 0 || numClusters <= 0) {
            throw new IllegalArgumentException(
                "Number of points, dimensions, and clusters must be positive."
            );
        }

        if (numClusters > numPoints) {
            throw new IllegalArgumentException(
                "Number of clusters cannot exceed number of points."
            );
        }

        if (!Double.isFinite(stdDev) || stdDev < 0) {
            throw new IllegalArgumentException(
                "Standard deviation must be finite and non-negative."
            );
        }

        Random random = new Random(seed);

        double[][] centers = generateCenters(
            numClusters,
            dimensions,
            random
        );

        List<Point> points = new ArrayList<>(numPoints);

        for (int i = 0; i < numPoints; i++) {

            int clusterIndex = random.nextInt(numClusters);

            double[] coordinates = new double[dimensions];

            for (int d = 0; d < dimensions; d++) {

                double noise = random.nextGaussian() * stdDev;

                coordinates[d] = centers[clusterIndex][d] + noise;
            }

            points.add(new Point(coordinates));
        }

        return points;
    }

    private double[][] generateCenters(
            int numClusters,
            int dimensions,
            Random random) {

        double[][] centers = new double[numClusters][dimensions];

        for (int k = 0; k < numClusters; k++) {

            for (int d = 0; d < dimensions; d++) {

                centers[k][d] =
                    random.nextDouble() * CENTER_RANGE;
            }
        }

        return centers;
    }
}