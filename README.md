# Rebels

Rebels es un servicio diseñado para interceptar mensajes y triangular la posición de objetivos militares de la alianza rebelde

## Instalación

Clonar repositorio

```bash
git clone https://github.com/alejo1991/rebels.git
```

## Uso

Debe tener Java 8+ (OpenJDK / OracleJDK). El servicio cuenta con una base en memoria h2.

Ejecute los siguientes comandos:

```bash
mvn clean install
```

```bash
mvn generate-sources
```

```bash
mvn spring-boot:run
```

Para hacer uso de las API's expuestas puede utilizar:

### Local

http://localhost:8080/swagger-ui.html#

### HerokuApp

https://rebels-test.herokuapp.com/swagger-ui.html#


## Contribuir

Este proyecto cuenta con una configuración para asegurar un porcentaje de cobertura de pruebas unitarias. Agregue las pruebas que considere necesarias.



