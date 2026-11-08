package kol1.zad07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

class UnsupportedFormatException extends Exception {
    public UnsupportedFormatException(String message) {
        super(message);
    }
}

class InvalidTimeException extends Exception {
    public InvalidTimeException(String message) {
        super(message);
    }
}

class Time implements Comparable<Time> {

    private int hours;
    private int minutes;

    public Time(int hours, int minutes) {
        this.hours = hours;
        this.minutes = minutes;
    }

    public static Time createTime(String text) throws UnsupportedFormatException, InvalidTimeException {
        String[] parts;
        if (text.contains(":")) {
            parts = text.split(":");
        } else if (text.contains(".")) {
            parts = text.split("\\.");
        } else {
            throw new UnsupportedFormatException(text);
        }
        if (parts.length != 2) {
            throw new UnsupportedFormatException(text);
        }

        int hours;
        int minutes;
        try {
            hours = Integer.parseInt(parts[0]);
            minutes = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            throw new UnsupportedFormatException(text);
        }

        if (hours < 0 || hours > 23 || minutes < 0 || minutes > 59) {
            throw new InvalidTimeException(text);
        }
        return new Time(hours, minutes);
    }

    public int getTotalMinutes() {
        return hours * 60 + minutes;
    }

    public String toAmPmString() {
        int displayHours = hours;
        String suffix;
        if (hours == 0) {
            displayHours = hours + 12;
            suffix = "AM";
        } else if (hours < 12) {
            suffix = "AM";
        } else if (hours == 12) {
            suffix = "PM";
        } else {
            displayHours = hours - 12;
            suffix = "PM";
        }
        return String.format("%2d:%02d %s", displayHours, minutes, suffix);
    }

    @Override
    public int compareTo(Time o) {
        return Integer.compare(getTotalMinutes(), o.getTotalMinutes());
    }

    @Override
    public String toString() {
        return String.format("%2d:%02d", hours, minutes);
    }
}

enum TimeFormat {
	FORMAT_24, FORMAT_AMPM
}

class TimeTable {

    private List<Time> times;

    public TimeTable() {
        times = new ArrayList<>();
    }

    public void readTimes(InputStream inputStream) throws UnsupportedFormatException, InvalidTimeException {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                String[] parts = line.split("\\s+");
                for (String part : parts) {
                    // times read before an exception stay in the list
                    times.add(Time.createTime(part));
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void writeTimes(OutputStream outputStream, TimeFormat format) {
        PrintWriter pw = new PrintWriter(outputStream);
        times.stream()
                .sorted()
                .forEach(time -> {
                    if (format == TimeFormat.FORMAT_24) {
                        pw.println(time);
                    } else {
                        pw.println(time.toAmPmString());
                    }
                });
        pw.flush();
    }
}

public class TimesTest {

	public static void main(String[] args) {
		TimeTable timeTable = new TimeTable();
		try {
			timeTable.readTimes(System.in);
		} catch (UnsupportedFormatException e) {
			System.out.println("UnsupportedFormatException: " + e.getMessage());
		} catch (InvalidTimeException e) {
			System.out.println("InvalidTimeException: " + e.getMessage());
		}
		System.out.println("24 HOUR FORMAT");
		timeTable.writeTimes(System.out, TimeFormat.FORMAT_24);
		System.out.println("AM/PM FORMAT");
		timeTable.writeTimes(System.out, TimeFormat.FORMAT_AMPM);
	}

}
