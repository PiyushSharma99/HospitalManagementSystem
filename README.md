# Hospital Management System

Please follow below steps to run the project:

+ Clone the git repository.
+ Open it in Eclipse/Intellij
+ Make sure Mysql server is running. (Mysql Workbench should be started)
+ Configure the ```application.properties``` file accordingly
+ Make a database schema with name ```hospital``` in Mysql Workbench.
+ Run the project by selecting ```Run as Java Application```.

  After the project is successfully run. Open Postman and test the following apis.

  ### Register User (Staff) [POST]

  ```http://localhost:8080/register ```

  #### Payload
  ```
  {
    "firstName":"Sam",
    "lastName" :"Karan", 
    "email" :"sam@gmail.com",
    "password": "12345"
}
  ```
