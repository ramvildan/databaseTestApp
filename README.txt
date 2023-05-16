1) Запуск приложения:

Если запускать приложение в IntelliIDEA:
Необходимо создать базу данных в Docker:

В терминале -> docker run --name db1 -p 5433:5432 -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=admin -e POSTGRES_DB=usersDb -d postgres:13.3

Далее можно запускать.

Если разворачивать приложение в Docker:
Необходимо запустить файл build.bat

2) Проверка запросов в Postman:

(Для проверки необходимо в  Postman во вкладке Collections импортировать приложенный файл Application.postman_collection и во вкладке
Environments импортировать приложенный файл Access token.postman_environment)

  1. Пройти регистрацию (User registration)
  2. Авторизоваться (User login)

Далее все запросы будут проходить проверку авторизации.

