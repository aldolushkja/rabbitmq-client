# RabbitMQ - Client

This project contains an example of how to use RabbitMQ Client dependencies in a JSE Environment. This is build with:
* Java11
* Maven
* RabbitMQ Client dependencies
* Maven shade plugin to build an executable jar

## Build
```shell
mvn clean package
```

## Run
```shell
java -jar target/rabbitmq-0.0.1-SNAPSHOT-shaded.jar
```

## Configuration
* Run RabbitMQ Docker image before launching app: 

```shell
docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management
```

* Change factory host with your rabbitmq host 

* Change factory port with your rabbitmq port

### Source ref

https://www.rabbitmq.com/tutorials/tutorial-one-python.html