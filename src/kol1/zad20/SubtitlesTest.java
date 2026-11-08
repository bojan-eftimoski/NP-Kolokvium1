package kol1.zad20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class SubtitleElement {

    private int number;
    private int start;
    private int end;
    private List<String> lines;

    public SubtitleElement(int number, int start, int end, List<String> lines) {
        this.number = number;
        this.start = start;
        this.end = end;
        this.lines = lines;
    }

    // "00:01:53,468" -> milliseconds
    public static int parseTime(String time) {
        String[] parts = time.trim().split("[:,]");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        int seconds = Integer.parseInt(parts[2]);
        int millis = Integer.parseInt(parts[3]);
        return ((hours * 60 + minutes) * 60 + seconds) * 1000 + millis;
    }

    private static String formatTime(int time) {
        int millis = time % 1000;
        int seconds = time / 1000 % 60;
        int minutes = time / (60 * 1000) % 60;
        int hours = time / (60 * 60 * 1000);
        return String.format("%02d:%02d:%02d,%03d", hours, minutes, seconds, millis);
    }

    public void shift(int ms) {
        start += ms;
        end += ms;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(number).append("\n");
        sb.append(formatTime(start)).append(" --> ").append(formatTime(end)).append("\n");
        for (String line : lines) {
            sb.append(line).append("\n");
        }
        return sb.toString();
    }
}

class Subtitles {

    private List<SubtitleElement> elements;

    public Subtitles() {
        elements = new ArrayList<>();
    }

    public int loadSubtitles(InputStream inputStream) {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                int number = Integer.parseInt(line.trim());
                String[] times = br.readLine().split("-->");
                int start = SubtitleElement.parseTime(times[0]);
                int end = SubtitleElement.parseTime(times[1]);
                List<String> lines = new ArrayList<>();
                while ((line = br.readLine()) != null && !line.trim().isEmpty()) {
                    lines.add(line);
                }
                elements.add(new SubtitleElement(number, start, end, lines));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return elements.size();
    }

    public void print() {
        for (SubtitleElement element : elements) {
            System.out.println(element);
        }
    }

    public void shift(int ms) {
        for (SubtitleElement element : elements) {
            element.shift(ms);
        }
    }
}

public class SubtitlesTest {
	public static void main(String[] args) {
		Subtitles subtitles = new Subtitles();
		int n = subtitles.loadSubtitles(System.in);
		System.out.println("+++++ ORIGINIAL SUBTITLES +++++");
		subtitles.print();
		int shift = n * 37;
        shift = (shift % 2 == 1) ? -shift : shift;
		System.out.println(String.format("SHIFT FOR %d ms", shift));
		subtitles.shift(shift);
		System.out.println("+++++ SHIFTED SUBTITLES +++++");
		subtitles.print();
	}
}
