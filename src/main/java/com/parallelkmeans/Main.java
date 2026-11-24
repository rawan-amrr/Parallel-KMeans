
package com.parallelkmeans;

import com.parallelkmeans.data.DatasetLoader;
import com.parallelkmeans.model.Point;
import com.parallelkmeans.model.Centroid;
import com.parallelkmeans.initialization.RandomInitialization;

import java.io.IOException;
import java.util.List;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {

        int dimensions = 2;
        int k = 4;
        long seed = 42L;

        DatasetLoader datasetLoader = new DatasetLoader();

        List<Point> points = datasetLoader.load(
            "data/visualization_dataset.csv"
        );

        // Generate initial centroids.
        RandomInitialization initialization =
            new RandomInitialization();

        List<Centroid> initialCentroids =
            initialization.initialize(points, k, seed);

        // Display dataset information.
        System.out.println("=== Parallel K-Means Project ===");

        System.out.println("Dataset loaded successfully.");
        System.out.println("Number of points: " + points.size());
        System.out.println("Dimensions: " + dimensions);
        System.out.println("Number of clusters (K): " + k);
        System.out.println("Seed: " + seed);

        // Display initial centroids.
        System.out.println("\nInitial centroids:");

        for (int i = 0; i < initialCentroids.size(); i++) {
            System.out.println(
                "Centroid " + i + ": "
                + Arrays.toString(
                    initialCentroids.get(i).getCoordinates()
                )
            );
        }

        System.out.println("\nProject setup completed successfully.");
    }
}
