# Parallel K-Means Clustering

A Java implementation of the K-Means clustering algorithm, designed to explore parallel computing using the **Fork/Join Framework**.

## Project Overview

This project aims to implement and compare two versions of K-Means clustering:

- **Sequential K-Means:** A standard single-threaded implementation.
- **Parallel K-Means:** A parallel implementation using Java's Fork/Join Framework.

The project focuses on evaluating correctness, parallel performance, scalability, and clustering quality across different dataset sizes and numbers of clusters.

## Project Objectives

- Implement K-Means clustering using object-oriented design.
- Parallelize the point-to-centroid assignment step.
- Use local accumulators and a safe reduction strategy.
- Compare sequential and parallel execution times.
- Evaluate clustering quality using metrics such as Sum of Squared Errors (SSE).
- Visualize cluster assignments and centroid movement across iterations.

## Technologies

- Java 17
- Maven
- JUnit 5
- Fork/Join Framework
- Git & GitHub

## Dataset

The project uses reproducible synthetic datasets generated with a fixed random seed.

**Visualization dataset:**
- 2,000 points
- 2 dimensions
- 4 generated clusters
- Seed: 42

**Benchmark datasets:**
- 10,000 points
- 100,000 points
- 1,000,000 points
- 10 dimensions
- 10 underlying generated clusters

## Project Structure

```text
Parallel-KMeans/
├── data/
├── src/
│   ├── main/
│   │   └── java/com/parallelkmeans/
│   │       ├── algorithm/
│   │       ├── data/
│   │       ├── distance/
│   │       ├── initialization/
│   │       ├── model/
│   │       ├── parallel/
│   │       ├── experiments/
│   │       └── visualization/
│   └── test/
├── pom.xml
└── README.md
```

## Requirements

- JDK 17 or later
- Apache Maven

## Getting Started

Clone the repository:

```bash
git clone https://github.com/rawan-amrr/Parallel-KMeans.git
cd Parallel-KMeans
```

Run the tests:

```bash
mvn clean test
```

Run the current application entry point:

```bash
java -cp target/classes com.parallelkmeans.Main
```

## Benchmarking

The benchmark experiments are designed to compare sequential and parallel implementations using different dataset sizes and K values.

The evaluation will include:
- Execution time per iteration
- Total execution time
- Speedup
- Number of iterations
- Sum of Squared Errors (SSE)

## Visualization

The project includes a planned 2D visualization component to display cluster assignments and centroid movement throughout the K-Means iterations.

## Team Contributions

This project is developed collaboratively by a six-member team. Responsibilities include shared components, sequential implementation, parallel assignment, parallel reduction, benchmarking, and visualization.

## Project Status

**In Progress**

The shared project structure, core models, configuration classes, distance calculation, initialization strategy, and synthetic data generation have been prepared. The sequential and parallel algorithm implementations, benchmarking, and visualization are being developed.

## Academic Context

Developed as a university project for the Parallel Processing course.
