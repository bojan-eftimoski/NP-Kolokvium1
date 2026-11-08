package kol1.zad15;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Measurement {

    private float temperature;
    private float wind;
    private float humidity;
    private float visibility;
    private Date date;

    public Measurement(float temperature, float wind, float humidity, float visibility, Date date) {
        this.temperature = temperature;
        this.wind = wind;
        this.humidity = humidity;
        this.visibility = visibility;
        this.date = date;
    }

    public float getTemperature() {
        return temperature;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return String.format("%.1f %.1f km/h %.1f%% %.1f km %s", temperature, wind, humidity, visibility, date);
    }
}

class WeatherStation {

    public static final long DAY_MS = 24L * 60 * 60 * 1000;
    public static final long MIN_DIFFERENCE_MS = 150 * 1000;

    private int days;
    private List<Measurement> measurements;

    public WeatherStation(int days) {
        this.days = days;
        this.measurements = new ArrayList<>();
    }

    public void addMeasurment(float temperature, float wind, float humidity, float visibility, Date date) {
        // ignore a measurement closer than 2.5 minutes to an existing one
        for (Measurement m : measurements) {
            if (Math.abs(m.getDate().getTime() - date.getTime()) < MIN_DIFFERENCE_MS) {
                return;
            }
        }
        // remove measurements older than x days compared to the new one
        long limit = date.getTime() - days * DAY_MS;
        measurements.removeIf(m -> m.getDate().getTime() < limit);
        measurements.add(new Measurement(temperature, wind, humidity, visibility, date));
    }

    public int total() {
        return measurements.size();
    }

    public void status(Date from, Date to) {
        List<Measurement> inPeriod = measurements.stream()
                .filter(m -> !m.getDate().before(from) && !m.getDate().after(to))
                .sorted(Comparator.comparing(Measurement::getDate))
                .collect(Collectors.toList());
        if (inPeriod.isEmpty()) {
            throw new RuntimeException();
        }
        inPeriod.forEach(System.out::println);
        double average = inPeriod.stream()
                .mapToDouble(Measurement::getTemperature)
                .average()
                .orElse(0);
        System.out.println(String.format("Average temperature: %.2f", average));
    }
}

public class WeatherStationTest {
	public static void main(String[] args) throws ParseException {
		Scanner scanner = new Scanner(System.in);
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        int n = scanner.nextInt();
        scanner.nextLine();
		WeatherStation ws = new WeatherStation(n);
		while (true) {
			String line = scanner.nextLine();
			if (line.equals("=====")) {
				break;
			}
			String[] parts = line.split(" ");
			float temp = Float.parseFloat(parts[0]);
			float wind = Float.parseFloat(parts[1]);
			float hum = Float.parseFloat(parts[2]);
			float vis = Float.parseFloat(parts[3]);
			line = scanner.nextLine();
			Date date = df.parse(line);
			ws.addMeasurment(temp, wind, hum, vis, date);
		}
		String line = scanner.nextLine();
		Date from = df.parse(line);
		line = scanner.nextLine();
		Date to = df.parse(line);
		scanner.close();
		System.out.println(ws.total());
		try {
			ws.status(from, to);
		} catch (RuntimeException e) {
			System.out.println(e);
		}
	}
}
