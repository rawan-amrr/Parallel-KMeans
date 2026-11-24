
package com.parallelkmeans;

import com.parallelkmeans.data.DataGenerator;
import com.parallelkmeans.data.DatasetLoader;
import com.parallelkmeans.model.Point;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import com.parallelkmeans.data.BenchmarkDatasets;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataGeneratorDatasetLoaderTest {

    @TempDir
    Path tempDir;

    @Test
    void shouldGenerateSaveAndLoadDatasetCorrectly() throws IOException {

        // 1. Generate  dataset.
        DataGenerator generator = new DataGenerator();

        List<Point> originalPoints = generator.generate(
            100,   // Number of points
            3,     // Dimensions
            4,     // Number of clusters
            42L    // Fixed seed
        );

        assertEquals(100, originalPoints.size());

        // 2. Save dataset to CSV.
        DatasetLoader loader = new DatasetLoader();

        Path filePath = tempDir.resolve("test_dataset.csv");

        loader.save(originalPoints, filePath);

        assertTrue(java.nio.file.Files.exists(filePath));

        // 3. Load dataset
        List<Point> loadedPoints = loader.load(filePath.toString());

        // 4. Verify num of points
        assertEquals(
            originalPoints.size(),
            loadedPoints.size(),
            "Loaded dataset should contain the same number of points."
        );

        // 5. Verify dimensions
        for (int i = 0; i < originalPoints.size(); i++) {

            Point original = originalPoints.get(i);
            Point loaded = loadedPoints.get(i);

            assertEquals(
                original.getDimensions(),
                loaded.getDimensions(),
                "Dimensions should match for point " + i
            );

            assertArrayEquals(
                original.getCoordinates(),
                loaded.getCoordinates(),
                0.0,
                "Coordinates should match for point " + i
            );
        }
    }

    @Test
    void shouldGenerateSameDatasetWithSameSeed() {

        DataGenerator generator = new DataGenerator();

        List<Point> firstRun = generator.generate(100, 3, 4, 42L);
        List<Point> secondRun = generator.generate(100, 3, 4, 42L);

        assertEquals(firstRun.size(), secondRun.size());

        for (int i = 0; i < firstRun.size(); i++) {

            assertArrayEquals(
                firstRun.get(i).getCoordinates(),
                secondRun.get(i).getCoordinates(),
                0.0,
                "Same seed should produce identical points."
            );
        }
    }
    @Test
void shouldGenerateReproducibleBenchmarkDataset() {

    List<Point> firstRun = BenchmarkDatasets.create(100);
    List<Point> secondRun = BenchmarkDatasets.create(100);

    
    assertEquals(100, firstRun.size());

    for (Point point : firstRun) {
        assertEquals(
            BenchmarkDatasets.DIMENSIONS,
            point.getDimensions()
        );
    }

  
    for (int i = 0; i < firstRun.size(); i++) {

        assertArrayEquals(
            firstRun.get(i).getCoordinates(),
            secondRun.get(i).getCoordinates(),
            0.0,
            "Benchmark datasets should be identical with the same seed."
        );
    }
}
}
