# Oasis pawn system
# Details 
This project is a pawn system I developed for a pawn bussines located in my city, in this project I use some of the thecnologies of Java Web for the back-end such as
Jsp, Servlets, JDBC and for the front-end I use Boostrap, html, fontawesome, javascript and css. This system is not completed at all but makes the principals functions of the bussines.

# How to run
First of all you have to install glassFish server 5.0 in your disk drive closer to the operating sistems in a folder you should create as AppServers. After that you have to configure it, in the route folder C:\AppServers\glassfish5\glassfish\lib you have to download and add in this location the mysql-connector-j-8.0.31.jar that it going to allows you to conect to MySql, after that in ApacheNetbeans add the GlassFish Server version 5.0 or 5.0.1, going to services, servers and then add server, Netbeans is going to guide you througt the process.

# Database Schema
You have to have intalled MySQL and My SQL workbench and create a database called bdd_empeno with the following structure.

![bddemp](https://user-images.githubusercontent.com/94880683/210024542-138356bd-f8d6-4ab1-8c6d-5ae08c515ea1.png)

Remember that you should change the user and the password acording with the credentials of your MySql Workbench in the calss Conexion.java in the project.
In case of having problems to run this project try to search on google the exception that trows you in the glass fish server cosole. 
I made this project with ApacheNetbeans for this reason all this explanation was made according with this IDE.
