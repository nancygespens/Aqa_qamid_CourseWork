# Курсовой проект по модулю «Автоматизация тестирования» для профессии «Инженер по тестированию»

***[Планирование автоматизации](https://github.com/nancygespens/Aqa_qamid_CourseWork/blob/main/Plan.md)***

***[Отчёт о проведенном тестировании](https://github.com/nancygespens/Aqa_qamid_CourseWork/blob/main/documents/Report.md)***

***[Отчёт по итогам автоматизации](https://github.com/nancygespens/Aqa_qamid_CourseWork/blob/main/documents/Summary.md)***

## Шаги для локального запуска проекта

1. **Склонировать проект**
* открыть терминал GIT для целевой папки проекта
* склонировать репозиторий командой *git clone https://github.com/nancygespens/Aqa_qamid_CourseWork.git*

2. **Открыть Docker Desktop**

3. **Открыть проект в IntelliJ IDEA**
* в терминале подключить базу данных MySQL командой `docker-compose up`
* открыть второй терминал и запустить SUT командой `java -jar artifacts/aqa-shop.jar`

4. **Запустить тесты и отчет**
* открыть второй терминал и запустить тесты командой `./gradlew clean test`
* после прохождения тестов создать отчет командой `./gradlew allureserve`
