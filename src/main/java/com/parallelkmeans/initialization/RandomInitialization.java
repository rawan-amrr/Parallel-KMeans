
package com.parallelkmeans.initialization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.parallelkmeans.model.Point;
import com.parallelkmeans.model.Centroid;

public class RandomInitialization implements InitializationStrategy {

    @Override
    public List<Centroid> initialize(
            List<Point> points,
            int k,
            long seed) {

        if (points == null || points.isEmpty()) {
            throw new IllegalArgumentException(
                "Points must not be null or empty."
            );
        }

        if (k <= 0 || k > points.size()) {
            throw new IllegalArgumentException(
                "K must be positive and cannot exceed the number of points."
            );
        }

        if (points.get(0) == null) {
            throw new IllegalArgumentException(
                "Points must not contain null elements."
            );
        }

        int dimensions = points.get(0).getDimensions();

        for (Point point : points) {

            if (point == null) {
                throw new IllegalArgumentException(
                    "Points must not contain null elements."
                );
            }

            if (point.getDimensions() != dimensions) {
                throw new IllegalArgumentException(
                    "All points must have the same dimensions."
                );
            }
        }

        List<Point> shuffledPoints = new ArrayList<>(points);

        Collections.shuffle(shuffledPoints, new Random(seed));

        List<Centroid> centroids = new ArrayList<>(k);

        for (int i = 0; i < k; i++) {
            centroids.add(
                new Centroid(shuffledPoints.get(i).getCoordinates())
            );
        }

        return centroids;
    }
}
