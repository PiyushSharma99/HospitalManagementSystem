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

URL:
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
Once the user is registered then all the below APIs will be working.

### Important point :

Please add **Authorization** as **Basic Auth** before executing any url.

In Basic Auth , username and password will be asked.

username will be the registered user email id.

password will be same as entered while registering.


 ### Create Patient [POST]

URL:
  ```http://localhost:8080/hospital/users/patients ```

  #### Payload
  ```
  {
    "patientId":1,
    "patientName": "Patient One",
    "patientAge": 30,
    "patientStatus":"Admitted"
}
  ```




