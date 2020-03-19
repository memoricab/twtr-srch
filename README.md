#### Environment variables
```
ENV= //dev or test
APP_PORT=
DATA_SOURCE_URL=
DATA_SOURCE_USERNAME= 
DATA_SOURCE_PASSWORD= 
```

<i>Data source driver is H2 driver for default. For further environment variable changes, change the variables and build it with maven again.</i>


#### Build with Maven
``` 
./mvnw clean package -DskipTests=true ## Pass env variables here
```

#### Run with Docker
Example docker run command,
``` 
docker run --env ENV=dev --env APP_PORT=8080 --env "DATA_SOURCE_URL=jdbc:h2:mem:testdb;TRACE_LEVEL_FILE=4" --env DATA_SOURCE_USERNAME=sa --env DATA_SOURCE_PASSWORD= -p 8080:8080  -t celenmeh/twtr-srch
```


#### Testing
Tests run only in test environment.  




