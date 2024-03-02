# Prayful API 🙏
This project is an API for a prayer application.

You can create a user, or being anonymous. 
You can create a prayer request, and pray for other people's requests.

## Requirements 📋
- Java 21 :coffee:
- Maven :wrench:
- Docker :whale: (optional)
- Postgres :elephant:

# Setup 🔨
```make install``` or
```mvn clean install```

## Database Setup 💾

### Create Database Container 🐋
This API uses a postgres database. To set up the database, run the following command:

```make create_db container=<container> user=<user> password=<pwd> database=<db_name>```

    Where:
    - container:    the name of the docker container. If not provided, the default value is 'postgres'
    - user:         the user to create. If not provided, the default value is 'user'
    - password:     the password for the user. If not provided, the default value is '123123123'
    - database:     the name of the database to create. If not provided, the default value is 'prayful-db'

### Run Database Container 🏹
If you want to run or re-run the database container, you can use the following command:

```make run_db container=<container>```

    Where:
    - container:    the name of the docker container. If not provided, the default value is 'postgres'

### Stop Database Container 🛑
If you want to stop the database container, you can use the following command:

```make stop_db container=<container>```

    Where:
    - container:    the name of the docker container. If not provided, the default value is 'postgres'

Tests will run using an in-memory HSQL database, so there is no need to create a database for tests.

## Security Configuration 🔒
This API uses JWT authentication. 

To configure the security, you need to create a file named `jwk_set.json` in the `resources/jwk` directory.
This file must contain a JWK Set, with at least one key.

You can use the online [JSON Web Key generator](https://mkjwk.org/) to generate a JWK Set: .
Or using the [Command line JSON Web Key (JWK) generator](https://connect2id.com/products/nimbus-jose-jwt/generator).

The Jwk set must be in the following format:
```json
{
    "keys": [
        {
            "kty": "EC",
            "d": "HSveA5XkSWeaJeg79AsXlghcfFAqbBNG1Ep51QKptOY",
            "crv": "P-256",
            "x": "Dmh5T6s69NLhGKv1nE_-gl36h9s6hvGttn5m91G9jnY",
            "y": "l8C-AGgQDGR3MpRGKFQkch0GPEYliFAHTO6osrMQwZE",
            "alg": "ES256"
        }
    ]
}
```
⚠️ Make sure to do not use this key in production. It is only an example.

# Run 🏃
```make run``` 


```mvn spring-boot:run -pl webapp```

⚠️ `make run` will run the database container if it is not running.

# Test ✅
```make test```

```mvn test```

# Documentation 📖
The API documentation is available at [endpoints](endpoints.md).
