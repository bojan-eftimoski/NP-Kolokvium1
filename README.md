# NP – Задачи за вежбање за прв колоквиум

Solutions to the 20 practice tasks for the first midterm in Advanced Programming (Напредно програмирање), FINKI.

Each task is in its own package, `src/kol1/zadNN`, in a single file named after the test class. The given test class is left unchanged, and the solution classes are written above it. The text of each task, including both examples, is in [`zadaci/`](zadaci).

All solutions compile with Java 21. Each one was run on both example inputs from the task, and the output matched the expected output exactly.

| # | Task | Test class | Main topics |
|---|---|---|---|
| 01 | [ShapesApplication – squares](zadaci/zad01.md) | `Shapes1Test` | reading an `InputStream`, lists |
| 02 | [ShapesApplication – squares and circles](zadaci/zad02.md) | `Shapes2Test` | inheritance, custom exception, sorting |
| 03 | [File system](zadaci/zad03.md) | `FileSystemTest` | composite pattern, recursion, exceptions |
| 04 | [Log processing system](zadaci/zad04.md) | `LoggerTest` | bounded generics, generic interfaces, lambdas |
| 05 | [MinMax](zadaci/zad05.md) | `MinAndMax` | generic class, `Comparable` |
| 06 | [Canvas with shapes](zadaci/zad06.md) | `ShapesTest` | inheritance, enums, ordered insertion |
| 07 | [TimeTable](zadaci/zad07.md) | `TimesTest` | parsing, custom exceptions, enum |
| 08 | [ArchiveStore (`Date`)](zadaci/zad08.md) | `ArchiveStoreTest` | inheritance, exceptions, `java.util.Date` |
| 09 | [ArchiveStore (`LocalDate`)](zadaci/zad09.md) | `ArchiveStoreTest` | inheritance, exceptions, `java.time.LocalDate` |
| 10 | [Triple](zadaci/zad10.md) | `TripleTest` | generic class bounded by `Number` |
| 11 | [IEvaluator](zadaci/zad11.md) | `EvaluatorTest` | generic functional interface, lambdas |
| 12 | [GenericFraction](zadaci/zad12.md) | `GenericFractionTest` | generics, exceptions |
| 13 | [Applicant evaluation](zadaci/zad13.md) | `ApplicantEvaluationTest` | interfaces, builder, exceptions |
| 14 | [Component](zadaci/zad14.md) | `ComponentTest` | composite pattern, sorting, exceptions |
| 15 | [WeatherStation](zadaci/zad15.md) | `WeatherStationTest` | dates, filtering by time window |
| 16 | [MojDDV](zadaci/zad16.md) | `MojDDVTest` | reading streams, custom exception |
| 17 | [MojDDV with statistics](zadaci/zad17.md) | `MojDDVTest` | `DoubleSummaryStatistics`, formatting |
| 18 | [FrontPage (FINKI Onion)](zadaci/zad18.md) | `FrontPageTest` | inheritance, categories, exceptions |
| 19 | [LoadedCoin](zadaci/zad19.md) | `LoadedCoinTest` | inheritance, `Random` |
| 20 | [Subtitles](zadaci/zad20.md) | `SubtitlesTest` | parsing, time formatting |

## Opening and running

1. In IntelliJ IDEA, choose **File → Open** and select the `NP-Kolokvium1` folder.
2. If IntelliJ shows *"Project JDK is not defined"*, click **Setup SDK** and pick a JDK 21, for example Eclipse Temurin 21.
3. Open a task's file, for example `src/kol1/zad01/Shapes1Test.java`, and click the green ▶ next to `main`.
4. Paste the example input from `zadaci/zadNN.md` into the Run panel. Most tasks read until the end of the input. To signal the end of input, press **Ctrl+D** in the Run panel.

## Things to know

- **Time zones (zad04, zad08, zad15).** These tasks print dates and times in your computer's time zone. The expected outputs were produced on a UTC server (zad04, zad08) and a GMT server (zad15). On a computer set to Macedonian time, those lines show a different hour or zone name. To get the exact expected output locally, open **Run → Edit Configurations → Modify options → Add VM options** and add `-Duser.timezone=UTC`, or `-Duser.timezone=GMT` for zad15.
- **zad19 is random.** The test flips coins many times and checks that the results fall within a range. Very rarely, a correct solution prints `NO`. If that happens, run it again.
- **zad16 / zad17 rounding.** The total for each receipt is summed with a stream. Streams use compensated summation, and that reproduces the expected rounding exactly. A plain `for` loop can differ in the last digit.
- **Decimal separator.** If your Windows regional format uses a comma for decimals, `String.format` can print `10000,00` where the expected output has `10000.00`. The graders use a dot.

## Pushing this project to GitHub

1. On github.com, create a new **empty** repository, for example `np-prv-kolokvium`. Don't add a README or a `.gitignore`.
2. In a terminal opened in the `NP-Kolokvium1` folder, run:

```bash
git init
git add .
git commit -m "Solutions for the first midterm practice tasks"
git branch -M main
git remote add origin https://github.com/bojan-eftimoski/np-prv-kolokvium.git
git push -u origin main
```

Alternatively, on the new repository's page, click **uploading an existing file** and drag in the contents of this folder.
