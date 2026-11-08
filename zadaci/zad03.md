# Задача 3

*Прв колоквиум (+ composite design pattern)*

Потребно е да се дефинира апликација за едноставен датотечен систем во којшто ќе се чуваат објекти коишто репрезентираат фајлови/датотеки (објекти коишто го имплементираат интерфејсот `IFile`).

Да се декларира интерфејсот `IFile` со соодветни методи, така што секој фајл/датотека ќе ги има следните карактеристики:

- може да се пристапи до неговото име (`String getFileName()`)
- може да се добие неговата големина во long (`long getFileSize()`)
- може да се добие String репрезентација на фајлот (`String getFileInfo(???)`)
- може да се сортира датотеката доколку е колекција од датотеки според големините на датотеките кои ги содржи (`void sortBySize()`)
- може да се пресмета големината на најголемата обична датотека во датотеката (`findLargestFile ()`)

Постојат два типа на фајлови: `File` (обична датотека) и `Folder` (директориум/фолдер). Потребно е овие две класи да го имплементираат интерфејсот `IFile`.

За еден `File` се чуваат информации за неговото име и големина (во long).

Во класата `Folder` се чуваат исти информации како и за `File`, a дополнително се чува и листа од фајлови (и обични и директориуми). За оваа класа да се имплементираат методите:

- `void addFile (IFile file)` - метод за додавање на било каква датотека во листата од датотеки.
- Доколку веќе постои датотека со исто име како името на датотеката што се додава како аргумент на методот, да се фрли исклучок од тип `FileNameExistsException` во којшто се проследува името кое веќе постои.

И во двете класи да се имплементираат методите коишто се декларирани во интерфејсот `IFile`. Да се запази на следните фактори:

- големината на еден `Folder` е сума од големините на сите датотеки (обични или директориуми) коишто се наоѓаат во него.
- при генерирање на String репрезентација на директориумите, датотеките и поддиректориумите во тој директориум да се вовлечени со таб (`"\t"`).
- String репрезентацијата на една обична датотека е `File name [името на фајлот со 10 места порамнето на десно] File size: [големината на фајлот со 10 места пораменета на десно ]`
- String репрезентацијата на еден директориум е `Folder name [името на директориумот со 10 места порамнето на десно] Folder size: [големината на директориумот со 10 места пораменета на десно ]`
- возможно е сортирање само во рамки на директориум, каде што сите датотеки во тој директориум потребно е да се сортираат според големина во растечки редослед.
- методот `getLargestFile()` треба да ја врати големината на најголемата обична датотека во рамки на датотеката каде што е повикан.
- кога се повикува методот `sortBySize()` кај директориум истиот треба да се повика и за сите негови подиректориуми

Да се дефинира класа `FileSystem` во која што ќе се чува само еден директориум (`rootDirectory`). За класата да се имплементираат:

- default конструктор `FileSystem()`
- `void addFile (IFile file)` - метод за додавање на било каква датотека во root директориумот.
- `long findLargestFile ()` - метод којшто ја враќа големината на најголемата (обична) датотека во root директориумот.
- `void sortBySize()` - метод којшто ги сортира датотеките во root директориумот ( и обични и директориуми) според нивната големина во root директориумот во растечки редослед.

**For example:**

*Input 1:*

```text
test
3
0
test 12000
0
test 123
0
test_1 1070
```

*Result 1:*

```text
===READING FILES FROM INPUT===
There is already a file named test in the folder test
===PRINTING FILE SYSTEM INFO===
Folder name:       root Folder size:      13070
    Folder name:       test Folder size:      13070
        File name:       test File size:      12000
        File name:     test_1 File size:       1070

===PRINTING FILE SYSTEM INFO AFTER SORTING===
Folder name:       root Folder size:      13070
    Folder name:       test Folder size:      13070
        File name:     test_1 File size:       1070
        File name:       test File size:      12000

===PRINTING THE SIZE OF THE LARGEST FILE IN THE FILE SYSTEM===
12000
```

*Input 2:*

```text
0
3
1
0_1
2
0
text 1222
0
text 127
1
0_2
2
1
0_3
0
0
0_3 16566
0
0_44 12341
```

*Result 2:*

```text
===READING FILES FROM INPUT===
There is already a file named text in the folder 0_1
There is already a file named 0_3 in the folder 0_2
===PRINTING FILE SYSTEM INFO===
Folder name:       root Folder size:      13563
    Folder name:          0 Folder size:      13563
        Folder name:        0_1 Folder size:       1222
            File name:       text File size:       1222
        Folder name:        0_2 Folder size:          0
            Folder name:        0_3 Folder size:          0
        File name:       0_44 File size:      12341

===PRINTING FILE SYSTEM INFO AFTER SORTING===
Folder name:       root Folder size:      13563
    Folder name:          0 Folder size:      13563
        Folder name:        0_2 Folder size:          0
            Folder name:        0_3 Folder size:          0
        Folder name:        0_1 Folder size:       1222
            File name:       text File size:       1222
        File name:       0_44 File size:      12341

===PRINTING THE SIZE OF THE LARGEST FILE IN THE FILE SYSTEM===
12341
```


**Тест класа:** `FileSystemTest` – решение: [`src/kol1/zad03/FileSystemTest.java`](../src/kol1/zad03/FileSystemTest.java)
