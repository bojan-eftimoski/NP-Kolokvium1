package kol1.zad06;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

enum Color {
	RED, GREEN, BLUE
}

interface Scalable {

    void scale(float scaleFactor);
}

interface Stackable {

    float weight();
}

abstract class Shape implements Scalable, Stackable {

    private String id;
    private Color color;

    public Shape(String id, Color color) {
        this.id = id;
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public Color getColor() {
        return color;
    }

    // 'C' for a circle, 'R' for a rectangle (each subclass says what it is, no type checks)
    public abstract char getTypeLabel();

    @Override
    public String toString() {
        return String.format("%c: %-5s%-10s%10.2f", getTypeLabel(), id, color, weight());
    }
}

class Circle extends Shape {

    private float radius;

    public Circle(String id, Color color, float radius) {
        super(id, color);
        this.radius = radius;
    }

    @Override
    public void scale(float scaleFactor) {
        radius *= scaleFactor;
    }

    @Override
    public float weight() {
        return (float) (Math.PI * radius * radius);
    }

    @Override
    public char getTypeLabel() {
        return 'C';
    }
}

class Rectangle extends Shape {

    private float width;
    private float height;

    public Rectangle(String id, Color color, float width, float height) {
        super(id, color);
        this.width = width;
        this.height = height;
    }

    @Override
    public void scale(float scaleFactor) {
        width *= scaleFactor;
        height *= scaleFactor;
    }

    @Override
    public float weight() {
        return width * height;
    }

    @Override
    public char getTypeLabel() {
        return 'R';
    }
}

class Canvas {

    private List<Shape> shapes;

    public Canvas() {
        this.shapes = new ArrayList<>();
    }

    public void add(String id, Color color, float radius) {
        insert(new Circle(id, color, radius));
    }

    public void add(String id, Color color, float width, float height) {
        insert(new Rectangle(id, color, width, height));
    }

    // keeps the list in descending order of weight without sorting;
    // a new shape goes after the shapes with the same weight
    private void insert(Shape shape) {
        int position = 0;
        while (position < shapes.size() && shapes.get(position).weight() >= shape.weight()) {
            position++;
        }
        shapes.add(position, shape);
    }

    public void scale(String id, float scaleFactor) {
        for (int i = 0; i < shapes.size(); i++) {
            if (shapes.get(i).getId().equals(id)) {
                Shape shape = shapes.remove(i);
                shape.scale(scaleFactor);
                insert(shape);
                return;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Shape shape : shapes) {
            sb.append(shape).append("\n");
        }
        return sb.toString();
    }
}

public class ShapesTest {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Canvas canvas = new Canvas();
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] parts = line.split(" ");
			int type = Integer.parseInt(parts[0]);
			String id = parts[1];
			if (type == 1) {
                Color color = Color.valueOf(parts[2]);
				float radius = Float.parseFloat(parts[3]);
				canvas.add(id, color, radius);
			} else if (type == 2) {
                Color color = Color.valueOf(parts[2]);
				float width = Float.parseFloat(parts[3]);
				float height = Float.parseFloat(parts[4]);
				canvas.add(id, color, width, height);
			} else if (type == 3) {
				float scaleFactor = Float.parseFloat(parts[2]);
                System.out.println("ORIGNAL:");
				System.out.print(canvas);
				canvas.scale(id, scaleFactor);
				System.out.printf("AFTER SCALING: %s %.2f\n", id, scaleFactor);
				System.out.print(canvas);
			}

		}
	}
}
