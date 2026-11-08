package kol1.zad05;

import java.util.Scanner;

class MinMax<T extends Comparable<T>> {

    private T min;
    private T max;
    private int total;
    // how many processed elements are equal to the current minimum / maximum
    private int minCount;
    private int maxCount;

    public MinMax() {
        min = null;
        max = null;
        total = 0;
        minCount = 0;
        maxCount = 0;
    }

    public void update(T element) {
        total++;
        if (min == null) {
            min = element;
            max = element;
            minCount = 1;
            maxCount = 1;
            return;
        }

        int compareMin = element.compareTo(min);
        if (compareMin < 0) {
            min = element;
            minCount = 1;
        } else if (compareMin == 0) {
            minCount++;
        }

        int compareMax = element.compareTo(max);
        if (compareMax > 0) {
            max = element;
            maxCount = 1;
        } else if (compareMax == 0) {
            maxCount++;
        }
    }

    public T max() {
        return max;
    }

    public T min() {
        return min;
    }

    private int getDifferentCount() {
        if (min == null) {
            return 0;
        }
        if (min.compareTo(max) == 0) {
            return total - minCount;
        }
        return total - minCount - maxCount;
    }

    @Override
    public String toString() {
        return min + " " + max + " " + getDifferentCount() + "\n";
    }
}

public class MinAndMax {
	public static void main(String[] args) throws ClassNotFoundException {
		Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        MinMax<String> strings = new MinMax<String>();
        for(int i = 0; i < n; ++i) {
            String s = scanner.next();
            strings.update(s);
        }
		System.out.println(strings);
		MinMax<Integer> ints = new MinMax<Integer>();
        for(int i = 0; i < n; ++i) {
           	int x = scanner.nextInt();
            ints.update(x);
        }
        System.out.println(ints);
	}
}
