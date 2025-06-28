# webProgramming
Web Programming Assignment

## Assignment 2

### Setting up the project in Visual Studio Code

1. Install Apache Tomcat Server v9.0 from [here](https://tomcat.apache.org/download-90.cgi)
2. Install Java of version 8 or above (We used OpenJDK 21)
3. Follow instructions from RUNNING.txt file in the tomcat server folder to set up needed variables and start the server
4. You can install [Community Server Connectors from Red Hat extension](https://marketplace.visualstudio.com/items?itemName=redhat.vscode-community-server-connector) in Visual Studio Code to run Tomcat Apache and add deplyments, or use ./bin/startup.sh and ./bin/shutdown.sh in the tomcat server folder to start and stop the server
5. Install [Java Extension Pack](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack) in Visual Studio Code
6. After pulling the project from the repository, open the project folder in Visual Studio Code
7. Install latest Maven version from: https://maven.apache.org/download.cgi
8. Add Maven's bin directory to path
9. Read RUNNING.TXT of your Tomcat and add all the necessary environment variables
10. Run `mvn clean install` in the terminal to build the project
11. Now either using the Community Server Connectors extension or by manually copying the `vietnam.war` file from the target folder to the webapps folder in the tomcat server folder, deploy the project (or upload it using the Tomcat Manager)
12. Restart your system...
13. Now either using the Community Server Connectors extension or by manually copying the .war file from the target folder to the webapps folder in the tomcat server folder, deploy the project (or upload it using the Tomcat Manager)
14. Open the browser and go to `http://localhost:${PORT}/vietnam/` and `http://localhost:${PORT}/vietnam/TestServlet` to see the project where PORT is the port number of the server (default is 8080)
15. (Optional) You can also install [Tomcat Server Helper](https://marketplace.visualstudio.com/items?itemName=SamueleRadici.tomcatmavenhelper) extension in Visual Studio Code to enable Hot Reload feature

### Running the project in Docker
1. Make sure you have Docker installed and running on your machine
2. Build the Docker image using the provided Dockerfile:
   ```bash
   docker build -t vietnam-app .
   ```
3. Run the Docker container:
   ```bash
   docker run -d --name vietnam-app -p 8888:8888 vietnam-app
    ```
