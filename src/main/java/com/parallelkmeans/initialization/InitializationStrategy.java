package com.parallelkmeans.initialization;

import java.util.List;
import com.parallelkmeans.model.Point;
import com.parallelkmeans.model.Centroid;

public interface InitializationStrategy {

    List<Centroid> initialize(
        List<Point> points,
        int k,
        long seed
    );
}