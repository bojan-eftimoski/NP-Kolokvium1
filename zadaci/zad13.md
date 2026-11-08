# Задача 13

Дадена е класата `Applicant` (кандидат за евалуација) со име, кредитен резултат (credit score), работен стаж (experience) и дали има криминално досие (criminal record). Исто така, даден е интерфејс за евалуација на кандидати `Evaluator` со еден метод `boolean evaluate(Applicant applicant)` и енумерација со следните видови на евалуатори:

Кандидатот ја поминува евалуацијата (методот враќа `true`) ако:

- NO\_CRIMINAL\_RECORD - нема криминално досие
- MORE\_EXPERIENCE - има барем 10 години работен стаж
- MORE\_CREDIT\_SCORE - има кредитен резултат од минимум 500
- NO\_CRIMINAL\_RECORD\_AND\_MORE\_EXPERIENCE - нема криминално досие и има барем 10 години работен стаж
- MORE\_EXPERIENCE\_AND\_MORE\_CREDIT\_SCORE - има барем 10 години работен стаж и има кредитен резултат од минимум 500
- NO\_CRIMINAL\_RECORD\_AND\_MORE\_CREDIT\_SCORE - нема криминално досие и има кредитен резултат од минимум 500.

Ваша задача е да го имплементирате методот `build(Evaluator.TYPE type)` во класата `EvaluatorBuilder`, кој ќе враќа објект од соодветна имплементација на интерфејсот `Evaluator` за соодветниот тип на евалуација. Ако типот на евалуација не е некој од наведените, методот треба да фрли исклучок од тип `InvalidEvaluation`.

За добро дизајнирано решение (ќе биде објавено по завршувањето на испитот) ќе се добиваат бонус 10 поени.

**For example:**

*Input 1:*

```text
Славе
600
12
true
1
```

*Result 1:*

```text
Applicant
Name: Славе
Credit score: 600
Experience: 12
Criminal record: Yes

Evaluation type: MORE_EXPERIENCE
Applicant is ACCEPTED
```

*Input 2:*

```text
Катарина
300
12
false
4
```

*Result 2:*

```text
Applicant
Name: Катарина
Credit score: 300
Experience: 12
Criminal record: No

Evaluation type: MORE_EXPERIENCE_AND_MORE_CREDIT_SCORE
Applicant is REJECTED
```


**Тест класа:** `ApplicantEvaluationTest` – решение: [`src/kol1/zad13/ApplicantEvaluationTest.java`](../src/kol1/zad13/ApplicantEvaluationTest.java)
