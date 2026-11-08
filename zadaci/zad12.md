# Задача 12

Треба да се развие генеричка класа за работа со дропки. Класата `GenericFraction` има два генерички параметри `T` и `U` кои мора да бидат од некоја класа која наследува од класата `Number`. `GenericFraction` има две променливи:

- numerator - броител
- denominator - именител.

Треба да се имплементираат следните методи:

- `GenericFraction(T numerator, U denominator)` - конструктор кој ги иницијализира броителот и именителот на дропката. Ако се обидиме да иницијализираме дропка со 0 вредност за именителот треба да се фрли исклучок од тип `ZeroDenominatorException`
- `GenericFraction<Double, Double> add(GenericFraction<? extends Number, ? extends Number> gf)` - собирање на две дропки
- `double toDouble()` - враќа вредност на дропката како реален број
- `toString():String` - ја печати дропката во следниот формат `[numerator] / [denominator]`, скратена (нормализирана) и секој со две децимални места.

**For example:**

*Input 1:*

```text
2 5
1 7
4 9
```

*Result 1:*

```text
0.40
19.00 / 35.00
37.00 / 63.00
38.00 / 45.00
Denominator cannot be zero
```

*Input 2:*

```text
2 8
4 8
1 5
```

*Result 2:*

```text
0.25
3.00 / 4.00
7.00 / 10.00
9.00 / 20.00
Denominator cannot be zero
```


**Тест класа:** `GenericFractionTest` – решение: [`src/kol1/zad12/GenericFractionTest.java`](../src/kol1/zad12/GenericFractionTest.java)
