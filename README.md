# Foro Alura
Este es un proyecto de desarrollo de una API REST en Java Spring Boot en cual puede ser implementado para el uso de un foro

A continuación, se detallan los pasos para configurar, construir y ejecutar el proyecto.

## Requisitos

- Java: JDK 17 o superior
- Maven: 4.0.0
- Spring Boot: 3.3.1
- MySQL: 8.0.38

## Configuración de Versiones

### Java

Asegúrate de que tu entorno tenga configurado el JDK adecuado. Puedes verificar tu versión de Java con el siguiente comando:
```
java -version
```
## Construcción del Proyecto
Para construir el proyecto, navega hasta el directorio raíz del proyecto y ejecuta el siguiente comando:
```
mvn clean install
```

## Ejecución del Proyecto
Después de construir el proyecto, puedes ejecutarlo utilizando el siguiente comando:
```
mvn spring-boot:run
```

Alternativamente, puedes ejecutar el archivo JAR generado en el directorio target:
```
java -jar target/foro-0.0.1-SNAPSHOT.jar
```

## Estructura del Proyecto
La estructura básica del proyecto es la siguiente
```
foro
├── src
│   ├── main
│   │   ├── java
│   │   │   └── alura
│   │   │       └── api
│   │   │           └── foro
│   │   │               └── ForoApplication.java
│   │   └── resources
│   │       ├── db
│   │       │   └─migration
│   │       ├── application.properties
│   │       ├── static
│   │       └── templates
├── target
├── .gitignore
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```

# Arranque del la base de datos inicial
En este proyecto se optó el uso de FlywayDB para la gestión de versiones de la base de datos que permite aplicar y rastrear cambios en la estructura de manera sencilla y automática. Los scripts de migración se encuentrán en el directorio src/main/resources/db/migration Los scripts deben seguir el formato:
V<version>__<description>.sql, por ejemplo:
```
V1__create_table.sql
```
Los scripts 8 y 9 tienen los valores iniciales para realizar pruebas se pueden omitir ya que son instrucciónes de inserción.

## Configuración de Propiedades
Puedes configurar las propiedades de la aplicación en el archivo src/main/resources/application.properties. Por ejemplo:
```
#conexion con data base
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/foro_alura
spring.datasource.username=user
spring.datasource.password=password
```

# Documentación de la API

Este proyecto utiliza OpenAPI para la documentación de la API, facilitando así la comprensión y el uso de los diferentes endpoints disponibles.
Puedes consultar la documentación a través de Swagger UI, una vez que la aplicación esté en ejecución es accesible desde
```
http://localhost:8084/foro/swagger-ui/index.html
```
