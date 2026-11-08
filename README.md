# NP – Задачи за вежбање за прв колоквиум

Solutions to the 20 practice tasks for the first midterm in Advanced Programming (Напредно програмирање), FINKI.

Each task is in its own package, `src/kol1/zadNN`, in a single file named after the test class. The given test class is left unchanged, and the solution classes are written above it. The text of each task, including both examples, is in the `README.md` in the same folder.

All solutions compile with Java 21. Each one was run on both example inputs from the task, and the output matched the expected output exactly.

## Code style

The code follows the style of the course's auditory exercises, as written in the TA repo [np-code-2025](https://github.com/atodorovska/np-code-2025):

- **Input:** read with `br.lines()` and turned into objects by a static `createX(String line)` factory that splits each line with `line.split("\\s+")`.
- **Calculations:** sums, minimums, maximums and averages use streams (`mapToInt` / `mapToDouble`).
- **Sorting and printing:** sorting uses `Comparator.comparing(...)`, and output goes through a `PrintWriter` that is flushed but never closed.
- **Exceptions:** checked exceptions with a single constructor that builds the message with `String.format`.
- **Visibility:** fields are `private` and accessed through getters.

There are two exceptions. zad07 and zad20 read input with a `readLine` loop, because their input can't be processed line by line with a stream.

## Tasks

| # | Task | Test class | Main topics |
|---|---|---|---|
| 01 | [ShapesApplication – squares](src/kol1/zad01) | `Shapes1Test` | reading an `InputStream`, lists |
| 02 | [ShapesApplication – squares and circles](src/kol1/zad02) | `Shapes2Test` | inheritance, custom exception, sorting |
| 03 | [File system](src/kol1/zad03) | `FileSystemTest` | composite pattern, recursion, exceptions |
| 04 | [Log processing system](src/kol1/zad04) | `LoggerTest` | bounded generics, generic interfaces, lambdas |
| 05 | [MinMax](src/kol1/zad05) | `MinAndMax` | generic class, `Comparable` |
| 06 | [Canvas with shapes](src/kol1/zad06) | `ShapesTest` | inheritance, enums, ordered insertion |
| 07 | [TimeTable](src/kol1/zad07) | `TimesTest` | parsing, custom exceptions, enum |
| 08 | [ArchiveStore (`Date`)](src/kol1/zad08) | `ArchiveStoreTest` | inheritance, exceptions, `java.util.Date` |
| 09 | [ArchiveStore (`LocalDate`)](src/kol1/zad09) | `ArchiveStoreTest` | inheritance, exceptions, `java.time.LocalDate` |
| 10 | [Triple](src/kol1/zad10) | `TripleTest` | generic class bounded by `Number` |
| 11 | [IEvaluator](src/kol1/zad11) | `EvaluatorTest` | generic functional interface, lambdas |
| 12 | [GenericFraction](src/kol1/zad12) | `GenericFractionTest` | generics, exceptions |
| 13 | [Applicant evaluation](src/kol1/zad13) | `ApplicantEvaluationTest` | interfaces, builder, exceptions |
| 14 | [Component](src/kol1/zad14) | `ComponentTest` | composite pattern, sorting, exceptions |
| 15 | [WeatherStation](src/kol1/zad15) | `WeatherStationTest` | dates, filtering by time window |
| 16 | [MojDDV](src/kol1/zad16) | `MojDDVTest` | reading streams, custom exception |
| 17 | [MojDDV with statistics](src/kol1/zad17) | `MojDDVTest` | `DoubleSummaryStatistics`, formatting |
| 18 | [FrontPage (FINKI Onion)](src/kol1/zad18) | `FrontPageTest` | inheritance, categories, exceptions |
| 19 | [LoadedCoin](src/kol1/zad19) | `LoadedCoinTest` | inheritance, `Random` |
| 20 | [Subtitles](src/kol1/zad20) | `SubtitlesTest` | parsing, time formatting |

## Cloning into IntelliJ IDEA

1. In IntelliJ IDEA, choose **File → New → Project from Version Control**. On the welcome screen, this is the **Clone Repository** button.
2. In **URL**, paste `https://github.com/bojan-eftimoski/NP-Kolokvium1.git`, choose a folder, and click **Clone**.
3. When IntelliJ asks whether to trust the project, click **Trust Project**.
4. If IntelliJ shows *"Project JDK is not defined"*, click **Setup SDK** and pick a JDK 21 or newer. If no JDK is installed, choose **Download JDK** and pick, for example, Eclipse Temurin 21.

You can also clone from a terminal with `git clone https://github.com/bojan-eftimoski/NP-Kolokvium1.git`, then open the folder with **File → Open**.

## Running a task

1. Open a task's file, for example `src/kol1/zad01/Shapes1Test.java`, and click the green ▶ next to `main`.
2. Paste the example input from the `README.md` in that task's folder into the Run panel. Most tasks read until the end of the input. To signal the end of input, press **Ctrl+D** in the Run panel.

## Project files

| File | What it is |
|---|---|
| `NP-Kolokvium1.iml` | The IntelliJ **module file**. It tells IntelliJ that `src` holds the source code and that the module uses the project's JDK. Without it, IntelliJ wouldn't know which folder to compile. |
| `.idea/modules.xml` | Lists the project's modules. Here there is one, `NP-Kolokvium1.iml`. |
| `.idea/misc.xml` | Sets the project JDK (`temurin-21`), the Java language level (21), and the output folder (`out/`). |
| `.gitignore` | Keeps compiled files (`out/`, `*.class`) and personal IntelliJ settings (`.idea/workspace.xml`) out of git. |

These files are only for IntelliJ. To compile and run without IntelliJ, use the JDK directly:

```bash
javac -d out src/kol1/zad01/*.java
java -cp out kol1.zad01.Shapes1Test
```

## Things to know

- **Time zones (zad04, zad08, zad15).** These tasks print dates and times in your computer's time zone. The expected outputs were produced on a UTC server (zad04, zad08) and a GMT server (zad15). On a computer set to Macedonian time, those lines show a different hour or zone name. To get the exact expected output locally, open **Run → Edit Configurations → Modify options → Add VM options** and add `-Duser.timezone=UTC`, or `-Duser.timezone=GMT` for zad15.
- **zad19 is random.** The test flips coins many times and checks that the results fall within a range. Very rarely, a correct solution prints `NO`. If that happens, run it again.
- **zad16 / zad17 rounding.** The total for each receipt is summed with a stream. Streams use compensated summation, and that reproduces the expected rounding exactly. A plain `for` loop can differ in the last digit.
- **Decimal separator.** If your Windows regional format uses a comma for decimals, `String.format` can print `10000,00` where the expected output has `10000.00`. The graders use a dot.
