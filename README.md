# Validacion_conocimientos_backend
validacion de conocimientos


END POINTS

/////////////////////Create team////////////////

url= http://localhost:8080/team/create

Body
{
    "name" : "Escarabajos de oro",
    "partnerCountry" : "Colombia"
}



/////////////////////crear team////////////////

POST /team/create HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Content-Length: 75

{
    "name" : "Escarabajos de oro",
    "partnerCountry" : "Colombia"
}




/////////////obtener todos los equipos
GET /team HTTP/1.1
Host: localhost:8080



///////////obtener equipo por id

GET /team/paa HTTP/1.1
Host: localhost:8080



///////////eliminar team

DELETE /team/2HS HTTP/1.1
Host: localhost:8080




///////////edit team

PUT /team HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Content-Length: 118

{
    "id": "5Eg",
    "name": "Escarabajos amarillos",
    "partnerCountry": "Venezuela",
    "cyclists": []
}




/////////////////////Add ciclist to team

PUT /cyclist/45q HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Content-Length: 66

{
    "name": "Nairo Quintana",
    "nationality": "Colombia"
}



//////////////////////obtener ciclista por nacionalidad

GET /cyclist/nationality/inglaterra HTTP/1.1
Host: localhost:8080

///////////////obtener team por pais

GET /team/country/venezuela HTTP/1.1
Host: localhost:8080




/////////obtener todos los ciclistas

GET /cyclist HTTP/1.1
Host: localhost:8080




//////////////////////////////ibtener ciclistas por el id del equipo

GET /cyclist/paa HTTP/1.1
Host: localhost:8080




//////////////////////////obtener cilista por id

GET /cyclist/id/utd HTTP/1.1
Host: localhost:8080



///////////////////eliminar ciclista por id del equipo y id del ciclista

DELETE /cyclist/delete/paa/Mim HTTP/1.1
Host: localhost:8080

















