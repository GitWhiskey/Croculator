#Croculator

*Простой* консольный калькулятор *простых* дробей! Программа позволяет пользователю работать в двух режимах:

1. Интерактивный. Программа работает с выражениеями, введенными пользователем в консоли.
2. "Из файла". Программа считывает выражения из файла, путь к которому указан пользователем. Все результаты записываются в XML-файл.

##Использование

Пользоваться данным калькулятором очень просто. Чтобы запустить калькулятор в интерактивном режиме, достаточно написать две строчки:
```
Calculator calculator = new Calculator();
calculator.run();
```
Для того, чтобы калькулятор начал считывать выражения из файла нужно написать:
```
calculator.runFromFile([путь_к_файлу]);
```

##Jar-файл

Ссылка появится чуть позже.

Автор: Хабрат Максим
