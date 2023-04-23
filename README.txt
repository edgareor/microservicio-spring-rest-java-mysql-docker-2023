Es un servicio REST con Java 17, Spring Boot, Spring Security, Spring MVC, Spring JPA (Hibernate) y MySQL Cloud. Tiene manejo transaccional, inyeccion de dependencias, validaciones de data de entrada y seguridad.

El puerto configurado es el 8081, el servicio debe ser clonado, y ya esta preparado para ser compilado, no se necesita configurar BD ya que apunta a una BD en la nube, solo ubicar las credenciales en PlanetScale en caso que hayan caducado.

Para ocuparlo seguir los siguientes pasos:

1. Verificar que la BD en PlanetScale este operativa, y generar nuevo usuario y password para conexion a BD, ya que las credenciales antiguas al quedar versionadas en githuhb se deshabilitan en planetscale, se deben sustituir en el archivo application.properties.

    URL:https://app.planetscale.com
    
2. Realizar un clone del proyecto desde GitHub.

3. Acceder al directorio donde se ubica el archivo POM.xml, tener instalado Java 17 y Maven y ejecutar el siguiente comando.

    $ mvn clean package
    $ java -jar target/crud-rest-java-mysql-docker-2023-0.0.1-SNAPSHOT.jar

4. El API tiene seguridad Spring Security Basic Auth, solo para efectos de prueba.

El log del levantamiento del server arroja un token para agregarlo como password del usuario en la seguridad Basic Auth.

5. Utilizar los servicios configurados en el puerto 8081:

GET - http://localhost:8081/api/hola-mundo?name=queryParamsOpcional
GET - http://localhost:8081/api/all
POST - http://localhost:8081/api/personas
{
    	"pkPersona": 8,  
	"pnombre": "Nombre",
    	"papellido": "Apellido",
	"page": 50
}

6. Incluye Dockerfile para construir nuestra imagen Docker. Posicionarnos al nivel del archivo Dockerfile y ejecutar el comando:

    $ docker build -t crud-rest-java-mysql-docker-2023 .

PUT - http://localhost:8081/api/personas
{
	"pkPersona": 6,
    	"pnombre": "Edyireth",
    	"papellido": "Ojeda",
    	"page": 30
}

DELETE - http://localhost:3000/api/personas/1
