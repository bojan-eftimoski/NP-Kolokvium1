# Задача 9

Да се имплементира класа `ArchiveStore` во која се чува листа на архиви (елементи за архивирање).

Секој елемент за архивирање `Archive` има:

- id - цел број
- dateArchived - датум на архивирање.

Постојат два видови на елементи за архивирање, `LockedArchive` за кој дополнително се чува датум до кој не смее да се отвори `dateToOpen` и `SpecialArchive` за кој се чуваат максимален број на дозволени отварања `maxOpen`. За елементите за архивирање треба да се обезбедат следните методи:

- `LockedArchive(int id, LocalDate dateToOpen)` - конструктор за заклучена архива
- `SpecialArchive(int id, int maxOpen)` - конструктор за специјална архива

За класата `ArchiveStore` да се обезбедат следните методи:

- `ArchiveStore()` - default конструктор
- `void archiveItem(Archive item, LocalDate date)` - метод за архивирање елемент item на одреден датум date
- `void openItem(int id, LocalDate date)` - метод за отварање елемент од архивата со зададен id и одреден датум date. Ако не постои елемент со даденото id треба да се фрли исклучок од тип `NonExistingItemException` со порака `Item with id [id] doesn't exist`.
- `String getLog()` - враќа стринг со сите пораки запишани при архивирањето и отварањето архиви во посебен ред.

За секоја акција на архивирање во текст треба да се додаде следната порака `Item [id] archived at [date]`, додека за секоја акција на отварање архива треба да се додаде `Item [id] opened at [date]`. При отварање ако се работи за `LockedArhive` и датумот на отварање е пред датумот кога може да се отвори, да се додаде порака `Item [id] cannot be opened before [date]`. Ако се работи за `SpecialArhive` и се обидиеме да ја отвориме повеќе пати од дозволениот број (maxOpen) да се додаде порака `Item [id] cannot be opened more than [maxOpen] times`.

**For example:**

*Input 1:*

```text
Locked Archive Count
3
Id Date (Days in future)
1 50
2 -25
3 45
Special Archive Count
2
Id MaxOpen
4 2
5 3
Opening
2 3 1 8 4 5 4 4 5 9 5 5
```

*Result 1:*

```text
Item with id 8 doesn't exist
Item with id 9 doesn't exist
Item 1 archived at 2013-10-07
Item 2 archived at 2013-10-07
Item 3 archived at 2013-10-07
Item 4 archived at 2013-10-07
Item 5 archived at 2013-10-07
Item 2 opened at 2013-10-07
Item 3 cannot be opened before 2013-11-21
Item 1 cannot be opened before 2013-11-26
Item 4 opened at 2013-10-07
Item 5 opened at 2013-10-07
Item 4 opened at 2013-10-07
Item 4 cannot be opened more than 2 times
Item 5 opened at 2013-10-07
Item 5 opened at 2013-10-07
Item 5 cannot be opened more than 3 times
```

*Input 2:*

```text
Locked Archive Count
2
Id Date (Days in future)
1 -10
2 -15
Special Archive Count
3
Id MaxOpen
4 1
5 5
6 7
Opening
4 4 5 5 5 1 2 6 8 12
```

*Result 2:*

```text
Item with id 8 doesn't exist
Item with id 12 doesn't exist
Item 1 archived at 2013-10-07
Item 2 archived at 2013-10-07
Item 4 archived at 2013-10-07
Item 5 archived at 2013-10-07
Item 6 archived at 2013-10-07
Item 4 opened at 2013-10-07
Item 4 cannot be opened more than 1 times
Item 5 opened at 2013-10-07
Item 5 opened at 2013-10-07
Item 5 opened at 2013-10-07
Item 1 opened at 2013-10-07
Item 2 opened at 2013-10-07
Item 6 opened at 2013-10-07
```


**Тест класа:** `ArchiveStoreTest` – решение: [`src/kol1/zad09/ArchiveStoreTest.java`](../src/kol1/zad09/ArchiveStoreTest.java)
