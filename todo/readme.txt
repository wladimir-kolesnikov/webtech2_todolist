Was ich (bisher) benutzt habe:

- (Apache Tomcat 9 --- geändert) Wildfly als Server für Eclipse (siehe unten)
- Hibernate für JPA Management
- (Jersey für JAX-RS Implementation) -- Erstmal entfernt
- JDBC MySQL als Driver
- MySQL 5 Datenbank
- XAMPP für locale MySQL Datenbank


Packages:
-----Conf-----
- Für die Konfiguration, beispielsweise Application Path setzen, oder Startup Bean zum befüllen der Datenbank zum Testen


-----Entity-----
- Werden mit Hilfe von JPA in der Datenbank verwaltet


-----Resource-----
- Braucht man in Verbindung mit REST Api



-----Sonstiges-----
- Datasource muss im Wildfly Server konfiguriert werden unter Configuration / Subsystems / Datasources / Non-XA
- Wird nirgendwo auf den Folien erklärt...
- Der neuste MySQL JDBC Driver funktioniert nicht (v8?)
- Habe einen alten benutzt: mysql-connector-java-5.1.46-bin.jar und der funktioniert


-----Ideen-----
- User können sich gegenseitig untereinander Nachrichten schreiben (zuviel Arbeit?)
