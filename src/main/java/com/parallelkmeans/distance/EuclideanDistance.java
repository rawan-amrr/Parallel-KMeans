
package com.parallelkmeans.distance;

import com.parallelkmeans.model.Point;
import com.parallelkmeans.model.Centroid;

public class EuclideanDistance implements DistanceMetric {

    @Override
    public double calculate(Point point, Centroid centroid) {

        if (point == null || centroid == null) {
            throw new IllegalArgumentException(
                "Point and Centroid must not be null."
            );
        }

        if (point.getDimensions() != centroid.getDimensions()) {
            throw new IllegalArgumentException(
                "Point and Centroid dimensions must match."
            );
        }

        double sum = 0.0;

        for (int i = 0; i < point.getDimensions(); i++) {

            double difference =
                point.getCoordinate(i) - centroid.getCoordinate(i);

            sum += difference * difference;
        }

        return Math.sqrt(sum);
    }
}
