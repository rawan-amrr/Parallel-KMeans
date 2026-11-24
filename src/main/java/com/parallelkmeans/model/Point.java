
package com.parallelkmeans.model;

import java.util.Arrays;

public final class Point {

    private final double[] coordinates;

    public Point(double... coordinates) {

        if (coordinates == null || coordinates.length == 0) {
            throw new IllegalArgumentException(
                "Point must have at least one coordinate."
            );
        }

        for (double coordinate : coordinates) {
            if (!Double.isFinite(coordinate)) {
                throw new IllegalArgumentException(
                    "Point coordinates must be finite numbers."
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
