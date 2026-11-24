
package com.parallelkmeans.data;

import com.parallelkmeans.model.Point;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class DatasetLoader {

    public List<Point> load(String filePath) throws IOException {

        if (filePath == null || filePath.isBlank()) {
            throw new IllegalArgumentException(
                "File path must not be null or empty."
            );
        }

        Path path = Path.of(filePath);

        if (!Files.exists(path) || !Files.isRegularFile(path)) {
            throw new IOException(
                "Dataset file does not exist: " + filePath
            );
        }

        List<Point> points = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(path)) {

            String line;
            int lineNumber = 0;
            int expectedDimensions = -1;
            boolean firstNonEmptyLine = true;

            while ((line = reader.readLine()) != null) {

                lineNumber++;

                if (line.isBlank()) {
                    continue;
                }

                String[] values = line.split(",", -1);

                // Detect and skip a valid text header.
                if (firstNonEmptyLine) {
                    firstNonEmptyLine = false;

                    if (isHeader(values)) {
                        continue;
                    }
                }

                if (!isNumericRow(values)) {
                    throw new IOException(
                        "Invalid numeric data at line " + lineNumber
                    );
                }

                if (expectedDimensions == -1) {
                    expectedDimensions = values.length;
                }

                if (values.length != expectedDimensions) {
                    throw new IOException(
                        "Inconsistent dimensions at line " + lineNumber
                    );
                }

                double[] coordinates = new double[values.length];

                for (int i = 0; i < values.length; i++) {

                    coordinates[i] = Double.parseDouble(
                        values[i].trim()
                    );

                    if (!Double.isFinite(coordinates[i])) {
                        throw new IOException(
                            "Non-finite value at line " + lineNumber
                        );
                    }
                }

                points.add(new Point(coordinates));
            }
        }

        if (points.isEmpty()) {
            throw new IOException(
                "Dataset contains no valid points."
            );
        }

        return points;
    }

    private boolean isHeader(String[] values) {

        if (values.length == 0) {
            return false;
        }

        for (String value : values) {

            if (value.trim().isEmpty()) {
                return false;
            }

            try {
                double number = Double.parseDouble(value.trim());

                if (Double.isFinite(number)) {
                    return false;
                }

            } catch (NumberFormatException e) {
                
            }
        }

        return true;
    }

    private boolean isNumericRow(String[] values) {

        if (values.length == 0) {
            return false;
        }

        try {
            for (String value : values) {

                double number = Double.parseDouble(value.trim());

                if (!Double.isFinite(number)) {
                    return false;
                }
            }

            return true;

        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void save(List<Point> points, Path filePath)
            throws IOException {

        if (points == null || points.isEmpty()) {
            throw new IllegalArgumentException(
                "Points must not be null or empty."
            );
        }

        if (filePath == null) {
            throw new IllegalArgumentException(
                "File path must not be null."
            );
        }

        if (points.get(0) == null) {
            throw new IllegalArgumentException(
                "Points must not contain null elements."
            );
        }

        int dimensions = points.get(0).getDimensions();

        for (Point point : points) {

            if (point == null || point.getDimensions() != dimensions) {
                throw new IllegalArgumentException(
                    "All points must be non-null and have the same dimensions."
                );
            }
        }

        Path absolutePath = filePath.toAbsolutePath();
        Path parent = absolutePath.getParent();

        if (parent != null) {
            Files.createDirectories(parent);
        }

        try (BufferedWriter writer = Files.newBufferedWriter(
                absolutePath,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING,
                StandardOpenOption.WRITE)) {

            // Write CSV header.
            for (int i = 0; i < dimensions; i++) {

                if (i > 0) {
                    writer.write(",");
                }

                writer.write("f" + i);
            }

            writer.newLine();

            // Write point coordinates.
            for (Point point : points) {

                for (int i = 0; i < dimensions; i++) {

                    if (i > 0) {
                        writer.write(",");
                    }

                    writer.write(
                        Double.toString(point.getCoordinate(i))
                    );
                }

                writer.newLine();
            }
        }
    }
}
