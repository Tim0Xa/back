# back
Back application for Lastik tests

App generated with Java 11 Spring boot
Connected to H2 Data Base
Wired to localhost:8080

API (GET):
/                   - version
/images             - get all images
/images/{id}        - get image by id
/groups             - get all groups
/groups/{id}        - get group by id
/users              - list all users (security is not configured!!!)
/lastiks            - get all lastiks
/lastiks/{id}       - get lastik by id

befoure you begin:  (on bash linux)

# check java version, 11 is needed
java --version

# check the resources folders 
ls resources/
> Groups
> Images
> Lastiks 
> application.properties 
> static 
> templates
# put groups in to Groups folder.

# check maven version
mvn --version
> Apache Maven 3.8.4

# run instalation
mvn clean install --DskipTests

# to start application:
java -jar back-0.0.1-SNAPSHOT.jar
