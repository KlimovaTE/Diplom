# Запуск автотестов и просмотр отчетов о тестировании
### Предустановленные программы:
1. Docker
1. IntelliJ IDEA
### Подготовка к запуску автотестов
- склонировать проект https://github.com/KlimovaTE/Diplom
- перейти в католог проекта и открыть терминал
- в командной строке терминала выполнить команду *docker-compose up -d*
### Запуск автотестов для SUT с поддержкой MySQL 
- в командной строке терминала выполнить команду *`java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -Dspring.datasource.username=app -Dspring.datasource.password=pass -jar aqa-shop.jar`*
- для запуска всех тестов: в командной строке терминала выполнить команду *`gradlew clean test -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -Dspring.datasource.username=app -Dspring.datasource.password=pass`*
- для запуска одного тестового класса: в командной строке терминала выполнить команду *`gradlew clean test --tests <имя тестового класса> -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -Dspring.datasource.username=app -Dspring.datasource.password=pass`*
### Запуск автотестов для SUT с поддержкой PostgreSQL 
- в командной строке терминала выполнить команду *`java -Dspring.datasource.url=jdbc:postgresql://localhost:5432/app -Dspring.datasource.username=app -Dspring.datasource.password=pass -jar aqa-shop.jar`*
- для запуска всех тестов: в командной строке терминала выполнить команду *`gradlew clean test -Dspring.datasource.url=jdbc:postgresql://localhost:5432/app -Dspring.datasource.username=app -Dspring.datasource.password=pass`*
- для запуска одного тестового класса: в командной строке терминала выполнить команду *`gradlew clean test --tests <имя тестового класса> -Dspring.datasource.url=jdbc:postgresql://localhost:5432/app -Dspring.datasource.username=app -Dspring.datasource.password=pass`*
### Просмотр отчетов о тестировании
1. Отчет о тестирование формируется автоматически после запуска автотестов и доступен для просмотра в файле *<имя проекта>/build/reports/tests/test/index.html*
2. Скриншоты и html-страницы с ошибками доступны для просмотра в папке *<имя проекта>/build/reports/tests*

### Окончание работы
- в командной строке терминала нажать *`Ctrl+C`*
- в командной строке терминала выполнить команду *`docker-compose down`*
