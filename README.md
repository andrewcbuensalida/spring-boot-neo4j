https://www.youtube.com/watch?v=_IgbB24scLI&t=222s
https://lucid.app/lucidchart/invitations/accept/inv_6ed0d88f-5680-4ec3-8aa4-7349e5cd2325

scripts to create dummy data
https://github.com/GavinLonDigital/Neo4jCourseScripts

install sql server and sql server management studio to connect to that server

=============================================

cypher cheatsheet https://neo4j.com/docs/cypher-cheat-sheet/5/auradb-enterprise/

=============================================

To initialize spring boot, https://start.spring.io/
In dependencies, add spring web, and spring boot dev tools

=============================================

spring-boot-neo4j-generated-from-initializr is the one I created.
spring-neo4j is the repo they created. But should be more or less the same.

==============================================

To build jar file https://www.tutorialworks.com/intellij-maven-create-jar/#:~:text=To%20create%20a%20JAR%20file%20from%20a%20Maven%20project%20in,written%20to%20the%20target%2F%20directory.
View > Tool Windows > Maven > Expand <Project Folder> > Lifecycle > package
This will generate a .jar file in the Target folder.
To run .jar, 
  java -jar path/to/jar


============================================
To deploy to ec2
Create an ec2
Change the advanced security settings of the pem
https://www.youtube.com/watch?v=kzLRxVgos2M
Make sure owner is you, and only you have full control
  ssh -i "neo4j-university-pem.pem" ec2-user@ec2-18-144-53-10.us-west-1.compute.amazonaws.com

Install java on ec2
sudo yum update
https://docs.aws.amazon.com/corretto/latest/corretto-20-ug/generic-linux-install.html#rpm-linux-install-instruct
  sudo rpm --import https://yum.corretto.aws/corretto.key
  sudo curl -L -o /etc/yum.repos.d/corretto.repo https://yum.corretto.aws/corretto.repo
  sudo yum install -y java-20-amazon-corretto-devel
To check if it installed
  java -version

Copy jar from local to ec2
  scp -i "neo4j-university-pem.pem" C:\swe\code\neo4j-springboot-react\spring-boot-neo4j-generated-from-initializr\spring-boot-neo4j\target\spring-boot-neo4j-0.0.1-SNAPSHOT.jar ec2-user@18.144.53.10:/home/ec2-user

To run app in background
  nohup java -jar spring-boot-neo4j-0.0.1-SNAPSHOT.jar &
Point front-end to ip address of ec2

==============================================

service is for business logic
repositories is for database logic
controller is for http request logic