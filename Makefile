# Database variables
container = postgres
user = user
password = 123123123
database = prayful-db


# Application Commands
install:
	mvn clean install

test:
	mvn test

run: start_db
	mvn spring-boot:run -pl webapp

create_properties:
	@cp webapp/src/main/resources/application.example.properties webapp/src/main/resources/application.properties
	@echo "Created application.properties file. Please edit it with your credentials."


# Database Commands
start_db:
	@if [ "$(container)" = "postgres" ]; then echo "Using default container name: postgres."; fi
	docker start $(container)

stop_db:
	@if [ "$(container)" = "postgres" ]; then echo "Using default container name: postgres."; fi
	docker stop $(container)

create_db:
	@if [ "$(container)" = "postgres" ]; then echo "Using default container name: 'postgres'."; fi
	@if [ "$(user)" = "user" ]; then echo "Using default user name: 'user'."; fi
	@if [ "$(password)" = "123123123" ]; then echo "Using default container name: '123123123'."; fi
	@if [ "$(database)" = "prayful-db" ]; then echo "Using default container name: 'prayful-db'."; fi

	docker run --name $(container) -e POSTGRES_PASSWORD=$(password) -e POSTGRES_USER=$(user) -p 5432:5432 -d postgres
	@sleep 5  # Wait for the PostgreSQL container to start (you can adjust this as needed)
	docker exec -it $(container) psql -U $(user) -c 'CREATE DATABASE "$(database)" WITH ENCODING "UTF-8";'


