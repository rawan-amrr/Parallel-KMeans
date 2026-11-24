
package com.parallelkmeans.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class KMeansResult {

    private final List<Centroid> centroids;
    private final int[] assignments;
    private final int iterations;
    private final double sse;

    private final List<List<Centroid>> centroidHistory;
    private final List<int[]> assignmentHistory;

    // Constructor without history.
    public KMeansResult(
            List<Centroid> centroids,
            int[] assignments,
            int iterations,
            double sse) {

        this(
            centroids,
            assignments,
            iterations,
            sse,
            Collections.emptyList(),
            Collections.emptyList()
        );
    }

    // Constructor with both history collections.
    public KMeansResult(
            List<Centroid> centroids,
            int[] assignments,
            int iterations,
            double sse,
            List<List<Centroid>> centroidHistory,
            List<int[]> assignmentHistory) {

        if (centroids == null || centroids.isEmpty()) {
            throw new IllegalArgumentException(
                "Centroids cannot be null or empty."
            );
        }

        if (assignments == null) {
            throw new IllegalArgumentException(
                "Assignments cannot be null."
            );
        }

        if (iterations < 0) {
            throw new IllegalArgumentException(
                "Iterations cannot be negative."
            );
        }

        if (sse < 0 || !Double.isFinite(sse)) {
            throw new IllegalArgumentException(
                "SSE must be finite and non-negative."
            );
        }

        if (centroidHistory == null || assignmentHistory == null) {
            throw new IllegalArgumentException(
                "History collections cannot be null."
            );
        }

        validateAssignments(assignments, centroids.size());

        this.centroids = copyCentroids(centroids);
        this.assignments = assignments.clone();
        this.iterations = iterations;
        this.sse = sse;

        if (centroidHistory.isEmpty() && assignmentHistory.isEmpty()) {
            this.centroidHistory = Collections.emptyList();
            this.assignmentHistory = Collections.emptyList();
        } else {
            if (centroidHistory.size() != assignmentHistory.size()) {
                throw new IllegalArgumentException(
                    "Centroid and assignment history sizes must match."
                );
            }

            if (centroidHistory.size() != iterations + 1) {
                throw new IllegalArgumentException(
                    "History must contain iterations + 1 snapshots."
                );
            }

            this.centroidHistory = copyHistory(centroidHistory);
            this.assignmentHistory = copyAssignmentHistory(
                assignmentHistory,
                assignments.length,
                centroids.size()
            );
        }
    }

    public List<Centroid> getCentroids() {
        return centroids;
    }

    public int[] getAssignments() {
        return assignments.clone();
    }

    public int getIterations() {
        return iterations;
    }

    public double getSse() {
        return sse;
    }

    public List<List<Centroid>> getCentroidHistory() {
        return centroidHistory;
    }

    public List<int[]> getAssignmentHistory() {
        return copyAssignmentHistory(
            assignmentHistory,
            assignments.length,
            centroids.size()
        );
    }

    public boolean hasHistory() {
        return !centroidHistory.isEmpty();
    }

    
private List<List<Centroid>> copyHistory(
        List<List<Centroid>> source) {

    List<List<Centroid>> historyCopies = new ArrayList<>();

    int expectedDimensions = centroids.get(0).getDimensions();

    for (List<Centroid> snapshot : source) {

        if (snapshot == null || snapshot.size() != centroids.size()) {
            throw new IllegalArgumentException(
                "Each history snapshot must contain all centroids."
            );
        }

        for (Centroid centroid : snapshot) {

            if (centroid == null ||
                centroid.getDimensions() != expectedDimensions) {

                throw new IllegalArgumentException(
                    "History centroids must have valid and consistent dimensions."
                );
            }
        }

        historyCopies.add(copyCentroids(snapshot));
    }

    return Collections.unmodifiableList(historyCopies);
}


    private List<int[]> copyAssignmentHistory(
            List<int[]> source,
            int expectedLength,
            int numberOfClusters) {

        List<int[]> historyCopies = new ArrayList<>();

        for (int[] snapshot : source) {

            if (snapshot == null || snapshot.length != expectedLength) {
                throw new IllegalArgumentException(
                    "Each assignment snapshot must match the number of points."
                );
            }

            validateAssignments(snapshot, numberOfClusters);
            historyCopies.add(snapshot.clone());
        }

        return Collections.unmodifiableList(historyCopies);
    }

    private void validateAssignments(
            int[] values,
            int numberOfClusters) {

        for (int assignment : values) {
            if (assignment < 0 || assignment >= numberOfClusters) {
                throw new IllegalArgumentException(
                    "Assignment contains an invalid cluster index."
                );
            }
        }
    }

    private List<Centroid> copyCentroids(List<Centroid> source) {

        if (source == null || source.isEmpty()) {
            throw new IllegalArgumentException(
                "Centroids cannot be null or empty."
            );
        }

        List<Centroid> copies = new ArrayList<>();
        int expectedDimensions = -1;

        for (Centroid centroid : source) {

            if (centroid == null) {
                throw new IllegalArgumentException(
                    "Centroid list cannot contain null elements."
                );
            }

            double[] coordinates = centroid.getCoordinates();

            if (expectedDimensions == -1) {
                expectedDimensions = coordinates.length;
            } else if (coordinates.length != expectedDimensions) {
                throw new IllegalArgumentException(
                    "All centroids must have the same dimensions."
                );
            }

            copies.add(new Centroid(coordinates));
        }

        return Collections.unmodifiableList(copies);
    }
}
