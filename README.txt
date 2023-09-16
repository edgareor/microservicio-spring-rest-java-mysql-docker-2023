Es un servicio REST con Java 17, Spring Boot, Spring Security, Spring MVC, Spring JPA (Hibernate) y MySQL Online. Tiene manejo transaccional, inyeccion de dependencias, validaciones de data de entrada y seguridad. Tambien posee documentacion Swagger implementada.

El puerto configurado es el 8081, el servicio debe ser clonado, y ya esta preparado para ser compilado, no se necesita configurar BD ya que apunta a una BD online, solo ubicar las credenciales en Correo.

Para ocuparlo seguir los siguientes pasos:
    
1. Realizar un clone del proyecto desde GitHub.

2. Verificar que la BD en FreeSqlDatabase este operativa, obtener el user y password enviadas al correo y agregarlas al archivo 
/spring-rest-java-mysql-docker-2023/src/main/resources/application.properties, ya que por seguridad se borraron de este archivo.

3. Acceder al directorio donde se ubica el archivo POM.xml, tener instalado Java 17 (No funciona con Java 8) y Maven y ejecutar el siguiente comando.

    $ mvn clean package
    $ java -jar target/crud-rest-java-mysql-docker-2023-0.0.1-SNAPSHOT.jar

4. El API tiene seguridad Spring Security Basic Auth, solo para efectos de prueba. Username: user y Password: userPass

5. Utilizar los servicios configurados en el puerto 8081:

Doc Swagger - http://localhost:8081/swagger-ui/index.html - Si redirige al login y pide credenciales son: Username: user y Password: {Token}.

GET - http://localhost:8081/api/fechas/actual
GET - http://localhost:8081/api/hola-mundo?name=queryParamsOpcional
GET - http://localhost:8081/api/path-param-query-string/informacion?param1=valor1
GET - http://localhost:8081/api/headers
GET - http://localhost:8081/api/all
POST - http://localhost:8081/api/personas
{
    	"pkPersona": 8,  
	"pnombre": "Nombre",
    	"papellido": "Apellido",
	"page": 50
}

PUT - http://localhost:8081/api/personas
{
	"pkPersona": 6,
    	"pnombre": "Edyireth",
    	"papellido": "Ojeda",
    	"page": 30
}

DELETE - http://localhost:3000/api/personas/1
GET - http://localhost:8081/api/store-procedure?start=1&end=3
GET - http://localhost:8081/api/function?radio=10
GET - http://localhost:8081/api/all-desc-by-age

6. Incluye Dockerfile para construir nuestra imagen Docker. Posicionarnos al nivel del archivo Dockerfile y ejecutar el comando:

    $ docker build -t crud-rest-java-mysql-docker-2023 .
    
 Una vez construida la imagen levantar el contenedor con el comando:
 
    $ docker run -p 8081:8081 crud-rest-java-mysql-docker-2023

7. Incluye Kubernetes.yml para desplegar nuestra imagen sobre Kubernetes, tiene HPA(Autoscaling), Service y Deployment. Descargar archivo posicionarse a nivel donde se encuentre y ejecutar el comando:

    $ kubectl apply -f Kubernetes.yml

  Luego el se debe consumir el servicio por el puerto 5153, se modifico para pruebas de diferenciacion.

  NOTA: Si se quiere destruir los recursos creados es con: $ kubectl delete -f Kubernetes.yml
   
   
