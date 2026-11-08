package kol1.zad01;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Canvas {

    private String id;
    private List<Integer> sizes;

    public Canvas(String id, List<Integer> sizes) {
        this.id = id;
        this.sizes = sizes;
    }

    public static Canvas createCanvas(String line) {
        String[] parts = line.trim().split("\\s+");
        String id = parts[0];
        List<Integer> sizes = new ArrayList<>();
        for (int i = 1; i < parts.length; i++) {
            sizes.add(Integer.parseInt(parts[i]));
        }
        return new Canvas(id, sizes);
    }

    public int getSquaresCount() {
        return sizes.size();
    }

    public int getTotalPerimeter() {
        return sizes.stream()
                .mapToInt(size -> 4 * size)
                .sum();
    }

    @Override
    public String toString() {
        return id + " " + getSquaresCount() + " " + getTotalPerimeter();
    }
}

class ShapesApplication {

    private List<Canvas> canvases;

    public ShapesApplication() {
        canvases = new ArrayList<>();
    }

    public int readCanvases(InputStream inputStream) {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        canvases = br.lines()
                .filter(line -> !line.isBlank())
                .map(Canvas::createCanvas)
                .collect(Collectors.toList());
        return canvases.stream()
                .mapToInt(Canvas::getSquaresCount)
                .sum();
    }

    public void printLargestCanvasTo(OutputStream outputStream) {
        PrintWriter pw = new PrintWriter(outputStream);
        canvases.stream()
                .max(Comparator.comparing(Canvas::getTotalPerimeter))
                .ifPresent(pw::println);
        pw.flush();
    }
}

public class Shapes1Test {

    public static void main(String[] args) {
        ShapesApplication shapesApplication = new ShapesApplication();

        System.out.println("===READING SQUARES FROM INPUT STREAM===");
        System.out.println(shapesApplication.readCanvases(System.in));
        System.out.println("===PRINTING LARGEST CANVAS TO OUTPUT STREAM===");
        shapesApplication.printLargestCanvasTo(System.out);

    }
}
