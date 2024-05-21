# Dynamic Web Programming using Java Servlets Assignment

### Setting up the project in Visual Studio Code

1. Install Apache Tomcat Server v9.0 from [here](https://tomcat.apache.org/download-90.cgi)
2. Install Java of version 8 or above. [We used OpenJDK 21 and project is targeted for OpenJDK 21](https://adoptium.net/temurin/releases/?package=jdk)
3. Follow instructions from [RUNNING.txt](https://tomcat.apache.org/tomcat-9.0-doc/RUNNING.txt) file in the tomcat server folder to set up needed variables and start the server, and if needed, restart the computer (especially for setting Windows)
4. You can install [Community Server Connectors from Red Hat extension](https://marketplace.visualstudio.com/items?itemName=redhat.vscode-community-server-connector) in Visual Studio Code to run Tomcat Apache and add deplyments, or use `./bin/startup.sh` and `./bin/shutdown.sh` in the tomcat server folder to start and stop the server
5. Install [Java Extension Pack](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack) in Visual Studio Code
6. After pulling the project from the repository, open the project folder in Visual Studio Code
7. Run `mvn clean install` in the terminal to build the project
8. Now either using the Community Server Connectors extension or by manually copying the `vietnam.war` file from the target folder to the webapps folder in the tomcat server folder, deploy the project (or upload it using the Tomcat Manager)
9. Open the browser and go to `http://localhost:${PORT}/vietnam/` and `http://localhost:${PORT}/vietnam/TestServlet` to see the project where PORT is the port number of the server (default is 8080)
10. (Optional) You can also install [Tomcat Server Helper](https://marketplace.visualstudio.com/items?itemName=SamueleRadici.tomcatmavenhelper) extension in Visual Studio Code to enable Hot Reload feature
