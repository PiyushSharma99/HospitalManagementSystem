# Hospital Management Staff System

Hospital Management Staff System is equipped with functionality to add Patient and their admission details to the system. Along with the functionality of authentication with Spring Security for staffs. In the database their password are secured in an encrypted format with BCryptPasswordEncoder.

Also a close attempt is made to fully cover the edge case scenarios for the problem statement.

I hope you like it ! 

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


 ### Get All Patients [GET]

URL:
  ```http://localhost:8080/hospital/users/patients ```

 ### Get Patient By Id  [GET]
 
URL:
  ```http://localhost:8080/hospital/users/patients/2 ```

### Edit Patient Details [PUT]
URL:
  ```http://localhost:8080/hospital/users/patients/1 ```
Payload:
```
{
    "patientName": "One Doe",
    "patientAge": 20
}
```

### Delete Patient [DELETE]
URL:
```http://localhost:8080/hospital/users/patients/4```

### All Admitted Patients [GET]
URL:
```http://localhost:8080/hospital/users/patients/admit```

### Discharge Patient [PUT]
URL:
``` http://localhost:8080/hospital/users/patients/2/discharge ```

### Create Patients Admissions [POST]
URL:
```http://localhost:8080/hospital/users/patients/3/admissions```

Payload:
```
{
    "assignedDoctor":"DoctorTestB",
    "patientRoomId":"R2",
    "expenses":1850000
}
```

### Get All Admissions By Patient Id [GET]
URL:
```http://localhost:8080/hospital/users/patients/3/admissions```

### Edit Patient Admission Details [PUT]
URL:
``` http://localhost:8080/hospital/users/patients/3/admissions/4 ```

Payload:
```
{
    "assignedDoctor":"DoctorTestB",
    "patientRoomId":"R2",
    "expenses":1850000
}
```

### Delete Patient Admission Details [DELETE]

URL:
```http://localhost:8080/hospital/users/patients/3/admissions/3```







