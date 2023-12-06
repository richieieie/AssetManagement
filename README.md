
# **Asset-Management**

This console application, developed using Java 19, serves as a tool for overseeing asset management within a company. Its primary functions involve the management of assets and employees, incorporating elements like authentication, authorization, and the ability to perform CRUD operations on assets. Additionally, the program facilitates the handling of requests initiated by either employees or managers.



## **Run locally**

**Clone the project**

- Eclipse: 

`window -> Show view -> Other -> Git -> Git Repositories -> Clone a Git repository --> Clone URI -> URI: https://github.com/richietrung/Asset-Management`

- IntellJ:

`VCS -> Get from Version Control -> URL: https://github.com/richietrung/Asset-Management`

**Prepare for the first run**

1. Create a folder to store data
- Establish a directory with any given name within src/main.
- Examine the `src/utils/DataInitiator.java` file and update the paths for the initial four lists: `assets`, `borrows`, `requests`, `employees` to correspond to the location of your created folder. In my instance, the folder is named `resources`, so no modifications are necessary.

2. Add raw data
- Inside `DataInitiator.java`, there are four functions responsible for adding instances of four classes: `Asset`, `Borrow`, `Request`, and `Employee`. Ensure that during initialization, the fields of these instances are accurately configured.
- You can choose to add raw datas or not. But be sure that the program always has one Employee that has `role="MA"` to use the whole function of the program.

**Run**

- If it is the first time you run the project, the result below will be shown:
![Alt text](file:///Users/trung/Desktop/Screenshot%202023-12-06%20at%2010.49.27.png)



## **Using**
    1. Login:
- Request an employee to log in by providing their ID and password.

    2. Register (Only for manager):
- Allow a manager to create a new account.

    3. Create new asset (Only for manager):
- Enable a manager to add a new asset to the database.

    4. Updating asset's information (Only for manager):
- Prompt a manager to input an asset's ID and modify its information.

    5. Approve the request of employee (Only for manager):
- Display all requests from employees to borrow assets and allow the manager to choose which requests to approve.

    6. Show list of borrow asset (Only for manager):
- Display all assets currently borrowed by the employee.

    7. Search asset by name (DESCENDING):
- Prompt the employee to enter a string and display all assets containing that string, arranged in descending order.

    8. Borrow the assets:
- Allow employees to submit requests for borrowing assets.

    9. Cancel request:
- Enable employees to cancel a request before it is approved by a manager.

    10. Return assets:
- Allow employees to select assets for return.

    11. Log out:
- Logout the current employee from the system.

  12. Others - Quit:
- Shut down the system.





