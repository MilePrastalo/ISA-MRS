# ISA-MRS
Pokretanje projekta
Za pokretanje projekta potrebni su: eclipse ee, vue cli, mysql

Za instaliranje vue cli-a potreban je npm
vue cli se instalira komandom
npm install -g @vue/cli
Takodje, potrebno je instalirati axios i chart.js: npm install axios i npm install chart.js
Vise informacija na : https://cli.vuejs.org/guide/installation.html
Za eclipse su potrebni plugin-i za maven i spring boot.
Umesto eclipse-a moze se koristiti SpringTool Suite: https://spring.io/tools3/sts/all.

Mysql je potrebno da bude pokrenut na portu 3306 username:root password root
Potom se pokrece spring aplikacija na portu 8080.
Ako je ispostovan ovaj redosled vue server ce biti pokrenut na portu 8080
Pocetna strana projekta je dostupna na http://localhost:8080/

Korisnik na pocetku rada mora pokrenuti skriptu za bazu: script.sql kako bi uneo uloge, sistem admina i popuste.
