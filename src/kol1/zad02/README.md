# Задача 2

*Прв колоквиум*

Да се дефинира класа `ShapesApplication` чување на податоци за повеќе прозорци на кои и се сцртуваат геометриски слики во различна форма (квадрати и кругови)..

За класата да се дефинира:

- `ShapesApplication(double maxArea)` - конструктор, каде maxArea е најголемата дозволена плоштина на секоја форма поединечно, која може да биде исцртана на прозорците.
- `void readCanvases (InputStream inputStream)` - метод којшто од влезен поток на податоци ќе прочита информации за повеќе прозорци на кои се исцртуваат различните геометриски слики. Во секој ред се наоѓа информација за еден прозорец во формат: `canvas_id type_1 size_1 type_2 size_2 type_3 size_3 …. type_n size_n` каде што canvas\_id е ИД-то на прозорецот, a после него следуваат информации за секоја форма во прозорецот. Секоја форма е означена со карактер што го означува типот на геометриската слика (`S` = square, `C` = circle) и со големината на страната на квадратот, односно радиусот на кругот.
- При додавањето на геометриските слики на прозорецот треба да се спречи креирање и додавање на прозорец во кој има форма што има плоштина поголема од максимално дозволената. Како механизам за спречување треба да се користи исклучок од тип `IrregularCanvasException` (фрлањето на исклучокот не треба да го попречи вчитувањето на останатите прозорци и геометриски слики. Да се испечати порака `Canvas [canvas_id] has a shape with area larger than [max_area]`.
- `void printCanvases (OutputStream os)` - метод којшто на излезен поток ќе ги испечати информациите за сите прозорци во апликацијата. Прозорците да се сортирани во опаѓачки редослед според сумата на плоштините на геометриските слики во нив. Секој прозорец да е испечатен во следниот формат: `ID total_shapes total_circles total_squares min_area max_area average_area`.

За вредноста на PI користете ja константата Math.PI. За постигнување на точност со тест примерите користете double за сите децимални променливи.

--

Define a class `ShapesApplication` whre you'll keep information about multiple windows on which geometric images (in different shape - square and circle) are drawn.

For the class you need to define and implement:

- `ShapesApplication(double maxArea)` - constructor with one argument which represents the maximum allowed area of a shape that can be drawn on the windows.
- `void readCanvases (InputStream inputStream)` - method that will read info about multiple windows from input stream. Each line of the data stream represents one window and it's in the format `canvas_id type_1 size_1 type_2 size_2 type_3 size_3 …. type_n size_n` where canvas\_id is the ID of the window and after the ID there are unknown number of pairs of data for the shapes. Each pair has its type (character `S` = square, `C` = circle) and the side of the side of the square or the size of the radius of the circle.
- When adding the geometric images on the window, the creation and addition of a window which contains a shape with area greater than the maximum area, should not be allowed. This should be done via exception of type `InvalidCanvasException`. Throwing an exception of this type should not stop the reading of the data from the input stream. When catching the exception, the following message should be printed: `Canvas [canvas_id] has a shape with area larger than [max_area]`.
- `void printCanvases(OutputStream os)` - method that will print to output stream the information for all the windows in the application. The windows should be sorted in descending order by the sum of the areas of the geometric shapes in them. Each window should be printed in the following format: `ID total_shapes total_circles total_squares min_area max_area average_area`.

For the value of PI use Math.PI. Use double for better precision of the decimal numbers.

**For example:**

*Input 1:*

```text
0cc31e47 C 27 C 13 C 29 C 15 C 22
5960017f C 30 S 15 S 588 C 25 C 14 S 14 S 17 C 19
8ed50a65 C 29 S 12 C 13 S 30 C 25 S 11
201c295e C 27 C 13 C 14 C 11 S 18 C 12
184ef1d4 S 28 S 26 S 2001 S 28 C 30 C 16 S 18
c4b48d9f S 26 C 18 C 18 S 16 S 12 C 29 S 19
5e28f402 C 24 C 28 C 14 C 25 S 11 S 22 S 10 S 19 S 20 S 11 C 29
91a5b09b C 30 S 10 S 28 S 10 S 18 C 28 S 14 S 10 S 30 C 21 C 24
36e77dad C 29 S 11 S 25 S 30 C 21 C 17 S 400 S 30 S 23
13343cb0 S 21 C 29 C 14 C 30 C 12
```

*Result 1:*

```text
===READING CANVASES AND SHAPES FROM INPUT STREAM===
Canvas 5960017f has a shape with area larger than 10000.00
Canvas 184ef1d4 has a shape with area larger than 10000.00
Canvas 36e77dad has a shape with area larger than 10000.00
===PRINTING SORTED CANVASES TO OUTPUT STREAM===
5e28f402 11 5 6 100.00 2642.08 1007.35
91a5b09b 11 4 7 100.00 2827.43 999.04
0cc31e47 5 5 0 530.93 2642.08 1538.12
13343cb0 5 4 1 441.00 2827.43 1395.73
8ed50a65 6 3 3 121.00 2642.08 1050.25
c4b48d9f 7 3 4 144.00 2642.08 873.55
201c295e 6 5 1 324.00 2290.22 765.57
```

*Input 2:*

```text
b0ee5b7c S 24 C 17 C 24 S 16 C 10 S 19
53b73e6d S 17 C 16 C 21 S 20 S 12 C 25 S 30
f37ff1d9 S 17 S 14 S 21 C 14 C 15 S 17 C 23 S 21 S 28 C 27 S 10 S 28 C 28 S 10
c42c098c C 10 S 17 C 11 C 20 S 24 S 16 C 10 S 20
2199cf86 S 12 C 14 S 13 C 30 S 29 S 26 C 15 S 30 C 21 C 500 S 22
```

*Result 2:*

```text
===READING CANVASES AND SHAPES FROM INPUT STREAM===
Canvas 2199cf86 has a shape with area larger than 10000.00
===PRINTING SORTED CANVASES TO OUTPUT STREAM===
f37ff1d9 14 5 9 100.00 2463.01 797.27
53b73e6d 7 3 4 144.00 1963.50 840.88
b0ee5b7c 6 3 3 256.00 1809.56 704.11
c42c098c 8 4 4 256.00 1256.64 473.26
```


**Тест класа:** `Shapes2Test` – решение: [`src/kol1/zad02/Shapes2Test.java`](../src/kol1/zad02/Shapes2Test.java)
