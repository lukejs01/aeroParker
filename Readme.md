## Setup

1. Check line 78, 79 & 107 in the pom.xml. The versions should match those on your local machine. 
2. Database setup - to set up the db you should create a schema in workbench called aeroparker. Then update the username and password to match that of the user you created the db with in application.properties.
3. You should then package the project using maven before running 
4. (optional) if there is an issue connecting to the mysql db then uncomment the 9-16 in applications.properties and comment 1-7

access the registration page with http://localhost:8080/registration