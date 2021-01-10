###1) Generate default webapp-project through Maven:
mvn archetype:generate -DgroupId=com.apress.todo -DartifactId=todo -Dversion=0.0.1-SNAPSHOT -DinteractiveMode=false -DarchetypeArtifactId=maven-archetype-webapp

###2) Deploy
An application deploing to installed Tomcat 9. 
You are need using startup.bat / shutdown.bat (<install_tomcat_location>/bin/) to manage the server. Before starting, you are need to put todo.war in this path: <install_tomcat_location>/webapps/, and then start server.

###3) Get ToDos with curl:
curl -H "Accept: application/json" http://localhost:8080/todo/toDos
curl -H "Accept: application/xml" http://localhost:8080/todo/toDos

