
## Start Mongo Server
```
brew services start mongodb-community
```

## Test server
```
mongosh
```

## Import json file into the db

```
mongoimport "mongodb://localhost:27017" -d shows -c tv --jsonArray --file tv-shows.json
```
```
mongoimport "mongodb://localhost:27017" --drop -d shows -c tv --jsonArray --file tv-shows.json
```
--drop -> drop table if exists
-d -> database
-c -> collections name (the 'table')
--jsonArray
--file to open

## SHow databases
```
show databases
```

## Select that database
```
use <database>
```
```
db.tv.findOne()
```
```
db.<db name>.findOne() -> find one record
```