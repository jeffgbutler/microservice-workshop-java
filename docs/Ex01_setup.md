# Setup

The following instructions will help you install a Java development environment from scratch. If you already have a Java/Maven/Git environment installed that you are familiar with, then you can simply clone the repository and work in your existing environment.

Note: If you experience difficulties building the projects after following these instructions, see the Maven Notes section at the bottom of this page.

## For all Environments
Install and configure Java and Git. Some of these steps are optional depending on the IDE you choose and your preferred workflow. If you have experience with these tools then you may feel free to alter the instructions to fit your preferences.

### Download/Install 
1. Install JDK 21 or higher. I use Temurin distributions, but you can use whatever you are comfortable with.
   Temurin is available here: [https://adoptium.net/temurin/](https://adoptium.net/temurin/). Note, if you are running MacOS I strongly recommend using SDKMAN!
   to manage JDK installations [https://sdkman.io/](https://sdkman.io/)
1. Install Git from [https://git-scm.com/](https://git-scm.com/)

### Configure
If you are using VS Code, your life will be easier if you set the JAVA_HOME environment variable. There are a variety of ways to do this. If you are on Windows, then the easiest way to do this is to use the control panel applet to edit environment variables. We suggest you make these changes to the "user" environment variables and not the "system" environment variables.

| Environment Variable | Setting |
|----------------------|---------|
| JAVA_HOME| Set to the home directory of your JDK installation (for example, \Program Files\Java\jdk-11.0.1 |

## Setup for Eclipse
1. Install Eclipse from [http://www.eclipse.org/downloads/](http://www.eclipse.org/downloads/). For these exercises the "Eclipse IDE for Java Developers" package is sufficient.
1. Clone the repository from [https://github.com/jeffgbutler/microservice-workshop-java.git](https://github.com/jeffgbutler/microservice-workshop-java.git), or download the zip from [https://github.com/jeffgbutler/microservice-workshop-java/archive/master.zip](https://github.com/jeffgbutler/microservice-workshop-java/archive/master.zip)
1. Open Eclipse, make a new workspace
1. Import 5 projects from the "/exercises" directory in the cloned code repo (File->Import...->Existing Maven Project)
1. Run the unit tests in the `movie-award-service` - they should all pass
1. If you want to experiment with the Swagger UI, follow these steps:
    1. Start the web service in the `movie-award-service` project by navigating to `microservice.workshop.movieawardservice.MovieAwardServiceApplication.java` and running the class as a Java application
    1. Once the web service starts, open a browser and navigate to [http://localhost:8093](http://localhost:8093). You should see the Swagger UI for this web service. You can run a few queries to return some sample data (ids from 1 to 20 will return data)
    1. After you have shown that the service works, terminate the program
 
## Setup for IntelliJ
1. Install IntelliJ Community Edition from [https://www.jetbrains.com/idea/download](https://www.jetbrains.com/idea/download)
1. Clone the repository from [https://github.com/jeffgbutler/microservice-workshop-java.git](https://github.com/jeffgbutler/microservice-workshop-java.git), or download the zip from [https://github.com/jeffgbutler/microservice-workshop-java/archive/master.zip](https://github.com/jeffgbutler/microservice-workshop-java/archive/master.zip)
1. Open IntelliJ, make a new empty project
1. Import 5 Maven projects from the "/exercises" directory in the cloned repo (File->New->Module From Existing Sources...)
1. Run the unit tests in the `movie-award-service` - they should all pass
1. If you want to experiment with the Swagger UI, follow these steps:
    1. Start the web service in the `movie-award-service` project by navigating to `microservice.workshop.movieawardservice.MovieAwardServiceApplication.java` and running the main method in the class
    1. Once the web service starts, open a browser and navigate to [http://localhost:8093](http://localhost:8093). You should see the Swagger UI for this web service. You can run a few queries to return some sample data (ids from 1 to 20 will return data)
    1. After you have shown that the service works, terminate the program

## Setup for Visual Studio Code
Note: the following instructions setup a VS Code workspace with the four exercise projects. I prefer this method of setting up VS Code as it seems to work seemlessly with the different Java based extensions in VS Code. If you have a different preference, please feel free to use it.

1. Install VS code from [https://code.visualstudio.com/](https://code.visualstudio.com/)
1. Clone the repository from [https://github.com/jeffgbutler/microservice-workshop-java.git](https://github.com/jeffgbutler/microservice-workshop-java.git), or download the zip from [https://github.com/jeffgbutler/microservice-workshop-java/archive/master.zip](https://github.com/jeffgbutler/microservice-workshop-java/archive/master.zip)
1. Open VS code in the `/exercise` directory either with the `code` command, or through the UI
1. Open the extensions page (ctrl-shift-X), install the Java Extension Pack (from Microsoft) and the Spring Boot 
extension pack (VMware) if you haven't already installed those extension
1. Run the tests in the `movie-award-service` project with the VS Code testing pane - they should all pass
1. If you want to experiment with the Swagger UI, follow these steps:
    1. Open the Spring Boot pane
    1. Start the movie-award-service application
    1. Once the web service starts, open a browser and navigate to [http://localhost:8093](http://localhost:8093). You should see the Swagger UI for this web service. You can run a few queries to return some sample data (ids from 1 to 20 will return data)
    1. After you have shown that the service works, you can end the service in the VSCode Spring Boot pane

## Maven Notes
If you experience difficulties in building the project, it might be that your workstation is configured to communicate with an internal Maven repository that cannot be reached when you are off-site. If this is true in your case, you can temporarily disable your connection to the internal Maven repository by renaming your Maven `settings.xml` file:

1. (Windows) Navigate to `\Users\<your id>\.m2`
2. (Linux/Mac) Navigate to `~\.m2`
3. Rename `settings.xml` to `settings.xml.save`

**Important** Remember to rename the file back to `settings.xml` once you are finished with this workshop. 
