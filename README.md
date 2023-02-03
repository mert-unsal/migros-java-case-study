## [migros-java-case-study](https://github.com/mert-unsal/migros-java-case-study)
Java Developer Migros Case Study

You need to run dockerized application container via ``docker-compose up --build`` on the root directory.

### DOCKER CONTAINER
If you need to containerize manually, you can build image via ``Dockerfile`` run this script ``docker build . -t migros-case-study-backend``
Then You can run application via `docker-compose up` on the root directory.

### HOW TO USE

There is static json files under resource folder.

The data is driven via these example 

```Radius: 100.30 m | 0.10 km | 0.06 mi | 329 ft | 0.05 nm
Circle Area: 31602.51 m2 | 0.03 km2
Lat,Lon: 37.85250, 27.82242

OUT CIRCLE

40.99200,29.12028			--> 1675346667000    --> , 2 Şubat 2023 17:04:27
40.99206,29.12125			--> 1675346735000    --> , 2 Şubat 2023 17:05:35
40.99214,29.12211			--> 1675346760000    --> , 2 Şubat 2023 17:06:00
40.99219,29.12266			--> 1675346820000    --> , 2 Şubat 2023 17:07:00

IN CIRCLE

40.99222,29.12327			--> 1675346900000    --> , 2 Şubat 2023 17:08:20
40.99231,29.12368			--> 1675346910000    --> , 2 Şubat 2023 17:08:30
40.99227,29.12411			--> 1675346925000    --> , 2 Şubat 2023 17:08:45
40.99227,29.12441			--> 1675357760000    --> , 2 Şubat 2023 17:09:20
40.99226,29.12523			--> 1675346941000    --> , 2 Şubat 2023 17:09:21
```


For this case, It saves and logs only

``40.99222,29.12327			--> 1675346900000    --> , 2 Şubat 2023 17:08:20``

``40.99227,29.12441			--> 1675357760000    --> , 2 Şubat 2023 17:09:20``

for the same Courier


### SWAGGER
There is swagger page for this application.
You can achieve it here [swagger](http://localhost:8080/swagger-ui/index.html)