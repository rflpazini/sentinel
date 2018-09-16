# Sentinel :space_invader:

## What am I using?
On this project I'm using [Spring Boot](https://spring.io/projects/spring-boot) and [MySql](https://www.mysql.com) as database.

## Local install

> First things first

Clone this project to a local folder:
```
$ https://github.com/rflpazini/sentinel.git
```

Before start development you should create a new Database and change properties of `application.properties`. Here's an example of a local properties:

```java
spring.datasource.url = jdbc:mysql://0.0.0.0:3306/xgroup
spring.datasource.username = root
spring.datasource.password = P@nD0ra

spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5InnoDBDialect
spring.jpa.hibernate.ddl-auto = update
```

Then access the application folder and run it with the following command:

```
$ mvn spring-boot:run
```

Sentinel will be available at `localhost:8080`

## Endpoints

**`GET -> /api/v0.1/mutant`**

Here you'll get a list of all mutants stored on DataBase

Response example:
```curl
HTTP/1.1 200 OK
Content-Type: application/json
Content-Length: 247
```

```json
[
    {
        "id": 1,
        "dna": [
            "ATGCGA",
            "CAGTGC",
            "TTATGT",
            "AGAAGG",
            "CCCCTA",
            "TCACTG"
        ],
        "type": 1,
        "createdAt": "2018-09-16T02:36:11.000+0000"
    },
    {
        "id": 2,
        "dna": [
            "ATGCGA",
            "CAGTGC",
            "TTATTT",
            "AGACGG",
            "GCGTCA",
            "TCACTG"
        ],
        "type": 0,
        "createdAt": "2018-09-16T02:45:33.000+0000"
    }
]
```

**`POST -> /api/v0.1/mutant`**

This will check if a DNA belongs to a Human or a Mutant. You should pass a DNA sequence on request body like this:
```json
// mutant DNA

{ 
	"dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"] 
}
```

You'll receive a response **`HTTP/1.1 200 OK`**

But if you're checking a Human DNA, it'll return a **`HTTP/1.1 403 Forbidden`**

```json
// human DNA

{ 
	"dna":["ATGCGA","CAGTGC","TTATTT","AGACGG","GCGTCA","TCACTG"] 
}
```

**`GET -> /api/v0.1/stats`**

Response example:
```
HTTP/1.1 200
Content-Type: application/json
Content-Length: 54
```

```json
{
    "count_mutant_dna": 1,
    "count_human_dna": 1,
    "ratio": 1
}
```

## License

[MIT License](http://rflpazini.mit-license.org) :copyright: Rafael Pazini
