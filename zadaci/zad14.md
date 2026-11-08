# Задача 14

Да се дефинира класа `Component` во која се чуваат:

- бојата
- тежината
- колекција од внатрешни компоненти (референци од класата `Component`).

Во оваа класа да се дефинираат методите:

- `Component(String color, int weight)` - конструктор со аргументи боја и тежина
- `void addComponent(Component component)` - за додавање нова компонента во внатрешната колекција (во оваа колекција компонентите секогаш се подредени според тежината во растечки редослед, ако имаат иста тежина подредени се алфабетски според бојата).

Да се дефинира класа `Window` во која се чуваат:

- име
- компоненти.

Во оваа класа да се дефинираат следните методи:

- `Window(String)` - конструктор
- `void addComponent(int position, Component component)` - додава нова компонента на дадена позиција (цел број). На секоја позиција може да има само една компонента, ако се обидеме да додадеме компонента на зафатена позиција треба да се фрли исклучок од класата `InvalidPositionException` со порака `Invalid position [pos], alredy taken!`. Компонентите се подредени во растечки редослед според позицијата.
- `String toString()` - враќа стринг репрезентација на објектот (дадена во пример излезот)
- `void changeColor(int weight, String color)` - ја менува бојата на сите компоненти со тежина помала од проследената
- `void swichComponents(int pos1, int pos2)` - ги заменува компонените од проследените позиции.

**For example:**

*Input 1:*

```text
FIREFOX
1
RED
30
3
MAGENTA
90
0
1
2
GREEN
40
3
RED
50
2
BLUE
50
2
CYAN
60
1
YELLOW
80
3
WHITE
35
0
2
4
60
BLACK
1 2
```

*Result 1:*

```text
=== ORIGINAL WINDOW ===
WINDOW FIREFOX
1:30:RED
---40:GREEN
------50:BLUE
---------60:CYAN
------50:RED
---90:MAGENTA
2:80:YELLOW
---35:WHITE

=== CHANGED COLOR (60, BLACK) ===
WINDOW FIREFOX
1:30:BLACK
---40:BLACK
------50:BLACK
---------60:CYAN
------50:BLACK
---90:MAGENTA
2:80:YELLOW
---35:BLACK

=== SWITCHED COMPONENTS 1 <-> 2 ===
WINDOW FIREFOX
1:80:YELLOW
---35:BLACK
2:30:BLACK
---40:BLACK
------50:BLACK
---------60:CYAN
------50:BLACK
---90:MAGENTA
```

*Input 2:*

```text
CHROME
1
WHITE
10
3
GRAY
20
0
1
0
1
1
BLACK
30
3
YELLOW
50
3
PINK
30
0
2
1
RED
60
0
3
3
PINK
50
3
GREEN
5
2
BLUE
30
3
WHITE
60
3
GRAY
10
2
GREEN
15
2
RED
45
3
YELLOW
40
1
CYAN
15
0
4
4 changecolor
20 the color
ORANGE
1
2
```

*Result 2:*

```text
Invalid position 1, alredy taken!
=== ORIGINAL WINDOW ===
WINDOW CHROME
1:10:WHITE
---20:GRAY
2:30:BLACK
---30:PINK
---50:YELLOW
3:60:RED
---5:GREEN
---30:BLUE
------10:GRAY
------15:GREEN
---------45:RED
------------40:YELLOW
------60:WHITE
---50:PINK
4:15:CYAN

=== CHANGED COLOR (20, ORANGE) ===
WINDOW CHROME
1:10:ORANGE
---20:GRAY
2:30:BLACK
---30:PINK
---50:YELLOW
3:60:RED
---5:ORANGE
---30:BLUE
------10:ORANGE
------15:ORANGE
---------45:RED
------------40:YELLOW
------60:WHITE
---50:PINK
4:15:ORANGE

=== SWITCHED COMPONENTS 1 <-> 2 ===
WINDOW CHROME
1:30:BLACK
---30:PINK
---50:YELLOW
2:10:ORANGE
---20:GRAY
3:60:RED
---5:ORANGE
---30:BLUE
------10:ORANGE
------15:ORANGE
---------45:RED
------------40:YELLOW
------60:WHITE
---50:PINK
4:15:ORANGE
```


**Тест класа:** `ComponentTest` – решение: [`src/kol1/zad14/ComponentTest.java`](../src/kol1/zad14/ComponentTest.java)
