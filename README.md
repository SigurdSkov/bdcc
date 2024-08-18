Starting the application



You start the application by running 

```
/src/main/java/dk.BdCC.bankaccountsystem/BASApp
```

This can be done simply by right clicking the file and left clicking the run

Alternatively run the command for the development system

```
./mvnw quarkus:dev
``` 
or to create a native executable
```
./mvnw install -Dnative
```


cURL commands

Creating a bank account

```
curl --location 'http://localhost:8080/api/bankaccount' \
--header 'Content-Type: application/json' \
--data '{
    "accountNumber": "69420",
    "fName": "Lars",
    "lName": "Larsen"
}'
```

Add money to the balance

```
curl --location --request PUT 'http://localhost:8080/api/bankaccount/addbalance' \
--header 'Content-Type: application/json' \
--data '{
    "incomingMoney": 222.22,
    "id": 51
}'
```

Transfer money from one account to another

```
curl --location --request PUT 'http://localhost:8080/api/bankaccount/transferbalance' \
--header 'Content-Type: application/json' \
--data '{
    "sender":
    {
        "id": 2,
        "incomingMoney": 444.44
    },
    "receiver":
    {
        "id": 3
    }
}'
```

Retrieve an account. This is based on the Id given as the last parameter

```
curl --location 'http://localhost:8080/api/bankaccount/2'
```

I can not access the historical data using a free key. I have still created the supposed cURL commands. They remain untested, insofar as I receive an error 403.

```
curl --location 'http://localhost:8080/api/bankaccount/conversions/2008-01-05'
```

Again for the year 2012

```
curl --location 'http://localhost:8080/api/bankaccount/conversions/2012-05-08'
```

Lastly for today (19-08-2024. If you are testing this at a later date, please change the cURL command accordingly)

```
curl --location 'http://localhost:8080/api/bankaccount/conversions/2024-08-19'
```

I would have made the project public, however I am a lazy bastard who didn't hide my API like I should have