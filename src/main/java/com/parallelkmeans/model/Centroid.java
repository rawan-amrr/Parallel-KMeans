
package com.parallelkmeans.model;

import java.util.Arrays;

public final class Centroid {

    private final double[] coordinates;

    public Centroid(double... coordinates) {

        if (coordinates == null || coordinates.length == 0) {
            throw new IllegalArgumentException(
                "Centroid must have at least one coordinate."
            );
        }

        for (double value : coordinates) {
            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException(
                    "Centroid coordinates must be finite numbers."
                );
            }
        }

        this.coordinates = coordinates.clone();
    }

    public double[] getCoordinates() {
        return coordinates.clone();
    }

    public int getDimensions() {
        return coordinates.length;
    }

    public double getCoordinate(int dimension) {
        return coordinates[dimension];
    }

    @Override
    public String toString() {
        return Arrays.toString(coordinates);
    }
}
