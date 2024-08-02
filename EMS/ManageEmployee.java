import java.util.Scanner;

import com.ems.department.controller.DepartmentController;
import com.ems.employee.controller.EmployeeController;
import com.ems.exception.EmployeeException;
import com.ems.project.controller.ProjectController;

/**
 * This class responsible for manage the employees 
 */
public class ManageEmployee {

    private EmployeeController employeeController = new EmployeeController();
    private DepartmentController departmentController = new DepartmentController();
    private ProjectController projectController = new ProjectController();
    private static Scanner scanner = new Scanner(System.in);
    
    static {
        System.out.println("Welcome");
    }

    public static void main(String[] args) {
        ManageEmployee manageEmployee = new ManageEmployee();
        manageEmployee.displayChoice();
    }
    
    /**
     * This method is used to display the choice
     *
     */
    public void displayChoice() {
        boolean repeatList = true;
        while (repeatList) {
            try {
                boolean isDepartmentActive = departmentController.isDepartmentActive();
                System.out.println("Select the choise ");
                System.out.println("1 ==> Employee page ");
                System.out.println("2 ==> Department page ");
                System.out.println("3 ==> Project Page");
                System.out.println("4 ==> Exit");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        if (isDepartmentActive) {
                            employeeController.displayChoice();
                        } else {
                            System.out.println("please add some department and try again");
                        }
                        break;
                    case 2:
                        departmentController.displayChoice();
                        break;
                    case 3:
                        projectController.displayChoice();
                        break;
                    case 4:
                        repeatList = false;
                        System.out.println("Thanks for using this application");
                        break;
                    default: System.out.println("Enter valid option between[1-4]");
                }
            } catch (EmployeeException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
                System.out.println("Please Enter Number between [1-4]");
            }
        }
    }

}