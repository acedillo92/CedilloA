Para hacer funcionar este servicio es necesario:
Maven
IDE de desarrollo con el complemento de springboot
java 8
Hacer el mvn clean install para las dependencias del proyecto.

Asigno el curl o en su defecto tambíen asigno la url con el json para probar el servicio.

curl --location --request POST 'http://localhost:8080/sendMessage' \
--header 'Content-Type: application/json' \
--data-raw '{
"username": "D964FCED0741B7998B1DBD8327B27397",
"password": "D38E7DC8828F983FEDA57E944130DEA4",
"number": "5529490552",
"message": "PRUEBA 34 EXAMEN AVON (ALEXIS ALBERTO CEDILLO VEGARA)"
}'



URL EN LOCAL : http://localhost:8080/sendMessage
JSON REQUERIDO: 
{

"username": "D964FCED0741B7998B1DBD8327B27397",

"password": "D38E7DC8828F983FEDA57E944130DEA4",

"number": "5529490552",

"message": "PRUEBA 34 EXAMEN AVON (ALEXIS ALBERTO CEDILLO VEGARA)"

}

Debe exitir la Base de datos, o bien utilizar la que ya esta en la conexión.