
package com.parallelkmeans.data;

import com.parallelkmeans.model.Point;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class DataSetup {

    private static final long SEED = 42L;

    public static void main(String[] args) throws IOException {

        DataGenerator generator = new DataGenerator();
        DatasetLoader loader = new DatasetLoader();

        
        List<Point> visualizationData = generator.generate(
            2000,
            2,
            4,
            SEED
        );

        
        Path datasetPath = Path.of(
            "data",
            "visualization_dataset.csv"
        );

        loader.save(visualizationData, datasetPath);

        System.out.println(
            "Visualization dataset created successfully."
        );

        System.out.println(
            "Number of points: " + visualizationData.size()
        );

        System.out.println(
            "Dataset path: " + datasetPath.toAbsolutePath()
        );
    }
}
