
# Coding Test - TestVagrant Technologies

This is a script to extract the details of the movie (Pushpa: The Rise) in Wiki and IMDB sources.
The details we are extracting here are:
1) Movie's country of origin.
2) Movie's release date.
Then we compare the extracted values from both source and our test will fail if the details are not matching.


## Prerequisite

Please make sure the following tools and IDE are installed before proceeding.

```bash
 Java
 Eclipse
 TestNG
```
## Importing the project
1- Download the code from the given GitHub link: https://github.com/ajithtester/interview.git (branch - master)
2- Once downloaded extract the file
3- Open eclipse
4- Create a maven project
5- Import the extracted folder

    
## Project Structure
Folder --> src/main/java
Packges:
1- baseClass
<!-- contains class: Baseclass -->
Baseclass contains all the methods which are common across the projects. To access the methods from the Baseclass we have extended it wherever necessary.

2- moviePages
<!-- contains page objects for Wiki and IMDB pages -->


Folder --> src/main/java
Packges:
1- testCase
<!-- contains class: MovieInfo -->
MovieInfo contains test methods to be executed.

Folder --> src/main/resource
Folder: Properties
<!-- contains properties files: datas and page locators -->
1- App.properties
2- imdbPageLocators.properties
3- wikiPageLocators.properties

File: pom.xml
It contains all the dependencies needed for the project.



## Instructions to run the test
To change the values of browser, URL, and movie names
Go to App.properties and change the respective values.

```bash
To run the test:
Go to MovieInfo.java file 
1- Right click in the class.
2- Run as TestNG
```
## Output
We can check the output from console, all the inputs are printed.
