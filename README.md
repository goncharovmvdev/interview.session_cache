<h1>Время выполнения ~50 мин</h1>

Запросы для проверки:
* curl -X POST localhost:8080/api/auth --header "Content-Type: application/json" --data "{\"username\": \"user\", \"password\": \"password\"}"
* curl --location --request GET localhost:8080/api/auth?username=user --header "Content-Type: application/json"