package kol1.zad02;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

abstract class Shape {

    private double size;

    public Shape(double size) {
        this.size = size;
    }

    public double getSize() {
        return size;
    }

    public abstract double getArea();

    public abstract boolean isCircle();
}

class Square extends Shape {

    public Square(double side) {
        super(side);
    }

    @Override
    public double getArea() {
        return getSize() * getSize();
    }

    @Override
    public boolean isCircle() {
        return false;
    }
}

class Circle extends Shape {

    public Circle(double radius) {
        super(radius);
    }

    @Override
    public double getArea() {
        return Math.PI * getSize() * getSize();
    }

    @Override
    public boolean isCircle() {
        return true;
    }
}

class IrregularCanvasException extends Exception {

    public IrregularCanvasException(String canvasId, double maxArea) {
        super(String.format("Canvas %s has a shape with area larger than %.2f", canvasId, maxArea));
    }
}

class Canvas {

    private String id;
    private List<Shape> shapes;

    public Canvas(String id, List<Shape> shapes) {
        this.id = id;
        this.shapes = shapes;
    }

    // line format: canvas_id type_1 size_1 type_2 size_2 ...
    public static Canvas createCanvas(String line, double maxArea) throws IrregularCanvasException {
        String[] parts = line.trim().split("\\s+");
        String id = parts[0];
        List<Shape> shapes = new ArrayList<>();
        for (int i = 1; i + 1 < parts.length; i += 2) {
            double size = Double.parseDouble(parts[i + 1]);
            Shape shape;
            if (parts[i].equals("C")) {
                shape = new Circle(size);
            } else {
                shape = new Square(size);
            }
            if (shape.getArea() > maxArea) {
                throw new IrregularCanvasException(id, maxArea);
            }
            shapes.add(shape);
        }
        return new Canvas(id, shapes);
    }

    public int getTotalShapes() {
        return shapes.size();
    }

    public int getTotalCircles() {
        return (int) shapes.stream()
                .filter(Shape::isCircle)
                .count();
    }

    public int getTotalSquares() {
        return getTotalShapes() - getTotalCircles();
    }

    public double getTotalArea() {
        return shapes.stream()
                .mapToDouble(Shape::getArea)
                .sum();
    }

    public double getMinArea() {
        return shapes.stream()
                .mapToDouble(Shape::getArea)
                .min()
                .orElse(0);
    }

    public double getMaxArea() {
        return shapes.stream()
                .mapToDouble(Shape::getArea)
                .max()
                .orElse(0);
    }

    public double getAverageArea() {
        return shapes.stream()
                .mapToDouble(Shape::getArea)
                .average()
                .orElse(0);
    }

    @Override
    public String toString() {
        return String.format("%s %d %d %d %.2f %.2f %.2f", id, getTotalShapes(), getTotalCircles(),
                getTotalSquares(), getMinArea(), getMaxArea(), getAverageArea());
    }
}

class ShapesApplication {

    private double maxArea;
    private List<Canvas> canvases;

    public ShapesApplication(double maxArea) {
        this.maxArea = maxArea;
        this.canvases = new ArrayList<>();
    }

    public void readCanvases(InputStream inputStream) {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        br.lines()
                .filter(line -> !line.isBlank())
                .forEach(line -> {
                    try {
                        canvases.add(Canvas.createCanvas(line, maxArea));
                    } catch (IrregularCanvasException e) {
                        System.out.println(e.getMessage());
                    }
                });
    }

    public void printCanvases(OutputStream os) {
        PrintWriter pw = new PrintWriter(os);
        canvases.stream()
                .sorted(Comparator.comparing(Canvas::getTotalArea).reversed())
                .forEach(pw::println);
        pw.flush();
    }
}

public class Shapes2Test {

    public static void main(String[] args) {

        ShapesApplication shapesApplication = new ShapesApplication(10000);

        System.out.println("===READING CANVASES AND SHAPES FROM INPUT STREAM===");
        shapesApplication.readCanvases(System.in);

        System.out.println("===PRINTING SORTED CANVASES TO OUTPUT STREAM===");
        shapesApplication.printCanvases(System.out);

    }
}
