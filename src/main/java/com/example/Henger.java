package com.example;

import javafx.beans.property.SimpleDoubleProperty;

public class Henger {
    private final SimpleDoubleProperty radius;
    private final SimpleDoubleProperty height;
    private final SimpleDoubleProperty surface;

    public Henger(double radius, double height, double surface) {
        this.radius = new SimpleDoubleProperty(radius);
        this.height = new SimpleDoubleProperty(height);
        this.surface = new SimpleDoubleProperty(surface);
    }

    public double getRadius() { return radius.get(); }
    public double getHeight() { return height.get(); }
    public double getSurface() { return surface.get(); }

    public SimpleDoubleProperty radiusProperty() { return radius; }
    public SimpleDoubleProperty heightProperty() { return height; }
    public SimpleDoubleProperty surfaceProperty() { return surface; }
}

