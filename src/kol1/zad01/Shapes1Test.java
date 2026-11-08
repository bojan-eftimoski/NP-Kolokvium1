package kol1.zad01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

class Canvas {

    private String id;
    private List<Integer> sizes;

    public Canvas(String id, List<Integer> sizes) {
        this.id = id;
        this.sizes = sizes;
    }

    public static Canvas createCanvas(String line) {
        String[] parts = line.split("\\s+");
        List<Integer> sizes = new ArrayList<>();
        for (int i = 1; i < parts.length; i++) {
            sizes.add(Integer.parseInt(parts[i]));
        }
        return new Canvas(parts[0], sizes);
    }

    public int getSquaresCount() {
        return sizes.size();
    }

    public int getTotalPerimeter() {
        int sum = 0;
        for (int size : sizes) {
            sum += 4 * size;
        }
        return sum;
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
        int squares = 0;
        try {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                Canvas canvas = Canvas.createCanvas(line);
                canvases.add(canvas);
                squares += canvas.getSquaresCount();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return squares;
    }

    public void printLargestCanvasTo(OutputStream outputStream) {
        PrintWriter pw = new PrintWriter(outputStream);
        Canvas largest = null;
        for (Canvas canvas : canvases) {
            if (largest == null || canvas.getTotalPerimeter() > largest.getTotalPerimeter()) {
                largest = canvas;
            }
        }
        if (largest != null) {
            pw.println(largest);
        }
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
