package com.parallelkmeans.distance;

import com.parallelkmeans.model.Point;
import com.parallelkmeans.model.Centroid;

public interface DistanceMetric {

    double calculate(Point point, Centroid centroid);

}