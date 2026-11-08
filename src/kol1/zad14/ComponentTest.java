package kol1.zad14;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

class Component { //Composite

    public static final String INDENT = "---";

    private String color;
    private int weight;
    private List<Component> components;

    public Component(String color, int weight) {
        this.color = color;
        this.weight = weight;
        this.components = new ArrayList<>();
    }

    public String getColor() {
        return color;
    }

    public int getWeight() {
        return weight;
    }

    // inner components are kept sorted by weight, then alphabetically by color
    public void addComponent(Component component) {
        components.add(component);
        components.sort(Comparator.comparingInt(Component::getWeight).thenComparing(Component::getColor));
    }

    // changes the color of this component and all inner components lighter than the given weight
    public void changeColor(int weight, String color) {
        if (this.weight < weight) {
            this.color = color;
        }
        for (Component component : components) {
            component.changeColor(weight, color);
        }
    }

    public String toString(int level) {
        StringBuilder sb = new StringBuilder();
        sb.append(INDENT.repeat(level)).append(weight).append(":").append(color).append("\n");
        for (Component component : components) {
            sb.append(component.toString(level + 1));
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return toString(0);
    }
}

class InvalidPositionException extends Exception {

    public InvalidPositionException(int position) {
        super(String.format("Invalid position %d, alredy taken!", position));
    }
}

class Window {

    private String name;
    private Map<Integer, Component> components;

    public Window(String name) {
        this.name = name;
        this.components = new TreeMap<>();
    }

    public void addComponent(int position, Component component) throws InvalidPositionException {
        if (components.containsKey(position)) {
            throw new InvalidPositionException(position);
        }
        components.put(position, component);
    }

    public void changeColor(int weight, String color) {
        for (Component component : components.values()) {
            component.changeColor(weight, color);
        }
    }

    public void swichComponents(int pos1, int pos2) {
        Component first = components.remove(pos1);
        Component second = components.remove(pos2);
        if (second != null) {
            components.put(pos1, second);
        }
        if (first != null) {
            components.put(pos2, first);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("WINDOW ").append(name).append("\n");
        for (Map.Entry<Integer, Component> entry : components.entrySet()) {
            sb.append(entry.getKey()).append(":").append(entry.getValue());
        }
        return sb.toString();
    }
}

public class ComponentTest {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String name = scanner.nextLine();
		Window window = new Window(name);
		Component prev = null;
		while (true) {
            try {
				int what = scanner.nextInt();
				scanner.nextLine();
				if (what == 0) {
					int position = scanner.nextInt();
					window.addComponent(position, prev);
				} else if (what == 1) {
					String color = scanner.nextLine();
					int weight = scanner.nextInt();
					Component component = new Component(color, weight);
					prev = component;
				} else if (what == 2) {
					String color = scanner.nextLine();
					int weight = scanner.nextInt();
					Component component = new Component(color, weight);
					prev.addComponent(component);
                    prev = component;
				} else if (what == 3) {
					String color = scanner.nextLine();
					int weight = scanner.nextInt();
					Component component = new Component(color, weight);
					prev.addComponent(component);
				} else if(what == 4) {
                	break;
                }
                
            } catch (InvalidPositionException e) {
				System.out.println(e.getMessage());
			}
            scanner.nextLine();			
		}
		
        System.out.println("=== ORIGINAL WINDOW ===");
		System.out.println(window);
		int weight = scanner.nextInt();
		scanner.nextLine();
		String color = scanner.nextLine();
		window.changeColor(weight, color);
        System.out.println(String.format("=== CHANGED COLOR (%d, %s) ===", weight, color));
		System.out.println(window);
		int pos1 = scanner.nextInt();
		int pos2 = scanner.nextInt();
        System.out.println(String.format("=== SWITCHED COMPONENTS %d <-> %d ===", pos1, pos2));
		window.swichComponents(pos1, pos2);
		System.out.println(window);
	}
}
