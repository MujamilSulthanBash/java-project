package com.i2it.ems.employee.controller;

import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.i2it.ems.department.controller.DepartmentController;
import com.i2it.ems.employee.service.EmployeeService;
import com.i2it.ems.employee.service.EmployeeServiceImpl;
import com.i2it.ems.exception.DataBaseException;
import com.i2it.ems.model.Department;
import com.i2it.ems.model.Employee;
import com.i2it.ems.model.Project;
import com.i2it.ems.project.controller.ProjectController;
import com.i2it.ems.util.Validator;

/**
 * <p>
 * This class Manage the employee crud based on user choice
 * </p>
 */
public class EmployeeController {
     
    private EmployeeService employeeService = new EmployeeServiceImpl();
    private DepartmentController departmentController = new DepartmentController();
    private ProjectController projectController = new ProjectController();
    private Scanner scanner = new Scanner(System.in);   

    /**
     * <p>
     * This method is used to get the data from user and create object for it.
     * </p>
     *
     * @throws DataBaseException
     *     - When Exception occurs
     */
    public void createEmployee() throws DataBaseException {
        String name = getName();
        String address= getAddress();
        LocalDate dob = getDob();
        Employee employee = new Employee(name , dob, address);
        employeeService.createEmployee(employee);
        System.out.println("Employee created ");
    }
    
    /**
     * <p>
     * This method is used to display the Employees data.
     * </p>
     * 
     * @throws DataBaseException 
     *     - When Exception occurs
     */
    public void displayEmployees() throws DataBaseException {
        List<Employee> employees = employeeService.retrieveEmployees();
        if (employees != null) {
            System.out.println("-----------------------------------------------");
            String format = "| %-10s | %-15s | %-10s |\n";
            System.out.format(format, "Id","Name","Department");
            for (Employee employee : employees) {
                System.out.format(format, employee.getId(), 
                                  employee.getName(),
                                  employee.getDepartment() == null ? "Not Assinged" 
                                  : employee.getDepartment().getName()); 
            }
            System.out.println("-----------------------------------------------");
        } else {
            System.out.println("No employees");
        }
    }
    
    /**
     * <p>
     * This method is used to display the Employee data by id. 
     * </p>
     * 
     * @throws DataBaseException 
     *     - When Exception occurs
     */
    public void displayEmployeeById() throws DataBaseException {
        System.out.println("Enter the Employee id");
        int id = getId();
        Employee employee = employeeService.retrieveEmployeeById(id);
        if (employee != null) {
            System.out.println("----------------------------------------------------------"
                               + "-------------------------------------------------------");
            String format = "| %-10s | %-15s | %-10s | %-15s | %-15s | %-20s | \n";
            System.out.format(format, "Id", "Name", "Age", "Address","Department","Projects");
            System.out.format(format, employee.getId(), employee.getName(), 
                              employee.getAge(), employee.getLocation(),
                              employee.getDepartment() == null ? "Not Assinged" : 
                              employee.getDepartment().getName(), employee.displayProjects().length() == 0 ?
                              "Not Assigned" : employee.displayProjects());
            System.out.println("----------------------------------------------------------"
                               + "-------------------------------------------------------");
        } else {
            System.out.println("No such employee id : " +id);
        }
    }

    /**
     * <p>
     * This method is used update the employee.
     * </p>
     * 
     * @throws DataBaseException 
     *     - When Exception occurs
     */
    public void updateEmployee() throws DataBaseException {
        System.out.println("Enter the Employee Id to update");
        int id = getId();
        Employee employee = employeeService.retrieveEmployeeById(id);
        if (employee != null) {
            Employee updatedEmployee = updateOperation(employee);
            employeeService.createEmployee(updatedEmployee);
        } else {
            System.out.println("No such employee id : " +id);
        } 
    }

    /**
     * <p>
     * This method is used to delete the Employee data by id  
     * </p>
     * 
     * @param id
     *     - accept the integer value
     * @throws DataBaseException
     *     - When Exception occurs
     */
    public void deleteEmployee(int id) throws DataBaseException {
        Employee employee = employeeService.retrieveEmployeeById(id);
        if (employee != null) {
            employee.setIsActive(false);
            employeeService.deleteEmployee(employee);    
        } else {
            System.out.println("No such employee id : " +id);
        }
    }

    /**
     * <p>
     * This method is used to display the retrieve option 
     * </p>
     * 
     * @throws DataBaseException 
     *     - When Exception occurs
     * @throws NumberFormatException 
     *     - Exception occurs
     */
    public void displayOperation() throws DataBaseException, NumberFormatException {
        boolean repeatList = true;
        while (repeatList) {
            System.out.println("Select the choice [1-3]");
            System.out.println("1 ==> List Employees");
            System.out.println("2 ==> List Employee By Id");
            System.out.println("3 ==> Back");
            try {
                int listChoice = Integer.parseInt(scanner.nextLine());
                switch (listChoice) {
                    case 1: 
                        displayEmployees();
                        break;
                    case 2:
                        displayEmployeeById();
                        break;
                    case 3:
                        repeatList = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please Enter Number between [1-3]");
                throw new NumberFormatException("issue while display the list choice ");
            }
        }
    }

    /**
     * <p>
     * This method is used to display the update option and perform operation.
     * </p>
     * 
     * @throws DataBaseException 
     *     - When Exception occurs.
     * @throws NumberFormatException 
     *     - When Exception occurs.
     */
    public Employee updateOperation(Employee employee) throws DataBaseException, NumberFormatException {
        boolean repeat = true;
        while (repeat) {
            System.out.println("1 ==> Update Name");
            System.out.println("2 ==> Update Date Of Birth");
            System.out.println("3 ==> Update Address");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        String name = getName();
                        employee.setName(name);  
                        break;
                    case 2:
                        LocalDate dateOfBirth = employee.getDateOfBirth();
                        employee.setDateOfBirth(dateOfBirth);
                        break;
                    case 3:
                        String Location = employee.getLocation();
                        employee.setLocation(Location);
                        break;
                    default: System.out.println("Enter valid option between[1-3]");
                }
            } catch (NumberFormatException e) {
                System.out.println("Enter valid option between[1-3]");
                throw new NumberFormatException("issue while display the list choice ");
            }
            repeat = false;
        }
        return employee;
    }

    /**
     * <p>
     * This method is used get the name from user untill user enter proper name.
     * Ex ==> instead of name user gave some number or leave as empty or gave some 
     *        special character.  
     * </p> 
     */
    public String getName() {
        String name = "";
        do {
            System.out.println("Enter the name");
            name = scanner.nextLine();
        } while (Validator.stringValidate(name));
        return name;
    }

    /**
     * <p>
     * This method is used to get the address from user until user enters proper address.
     * Ex ==> instead of address user gave some number or leave as empty or gave some 
     *        special character.
     * </p>   
     */
    public String getAddress() {
        String address = "";
        do {
            System.out.println("Enter the Address");
            address = scanner.nextLine();
        } while (Validator.stringValidate(address));
        return address;
    }
    
    /**
     * <p>
     * This method is used to get the id from user until user entere proper id. 
     * Ex ==> instead of id user gives some String.
     * </p>  
     */
    public int getId() {
        boolean repeat = false;
        int id = 0;
        do {
            try {
                System.out.println("Entert the id");
                id = Integer.parseInt(scanner.nextLine());
                repeat = false;
            } catch (NumberFormatException e) {
                System.out.println("Please Enter Number");
                repeat = false;
            }
        } while (repeat);
        return id;
    }

    /**
     * <p>
     * This method is used to get the Date of birth from user untill user enters properly. 
     * Ex ==> Accepts the pattern ==> --yyyy-mm-dd--.
     * </p>  
     */
    public LocalDate getDob() {
        String dob = "";
        boolean repeat = false;
        do {
            try {
                System.out.println("Enter the Date of birth");
                System.out.println("------YYYY-MM-DD-------");
                dob = scanner.nextLine();
                repeat = Validator.dobValidate(dob);
            } catch (DateTimeParseException e) {
                System.out.println("Please enter date in YYYY-MM-DD format.");
            }
        } while (! repeat);
        return LocalDate.parse(dob);
    }

    /**
     * <p>
     * This method is used to assign department for particular employee.
     * </p>  
     */
    public void assignDepartment() throws DataBaseException {
        displayEmployees();
        int employeeId = getId();
        Employee employee = employeeService.retrieveEmployeeById(employeeId);
        departmentController.displayDepartments();
        int departmentId = getId();
        Department department = employeeService.retrieveDepartmentById(departmentId);
        employee.setDepartment(department);
        employeeService.createEmployee(employee);
    }

    /**
     * <p>
     * This method is used to assign project for particular employee.
     * </p>  
     */
    public void assignProject() throws DataBaseException {
        displayEmployees();
        int employeeId = getId();
        Employee employee = employeeService.retrieveEmployeeById(employeeId);
        projectController.displayProjects();
        int projectId = getId();
        Project project = employeeService.retrieveProjectById(projectId);
        Set<Project> projects = new HashSet<>();
        projects.add(project); 
        employee.setProjects(projects);
        employeeService.createEmployee(employee);
    }

    /**
     * <p>
     * This method is used to display employee for particular department.
     * </p>  
     */
    public void displayEmployeeByDepartment() throws DataBaseException {
        departmentController.displayDepartments();
        int departmentId = getId();
        Department department = employeeService.retrieveDepartmentById(departmentId);
        if (department != null) {
            if (! department.getEmployees().isEmpty()) {
                System.out.println("---------------------------------------");
                String format = "| %-10s | %-15s |\n";
                System.out.format(format, "Id","Name");
                for (Employee employee : department.getEmployees()) {
                    if (employee.getIsActive()) {
                        System.out.format(format, employee.getId(), employee.getName(),
                        employee.displayProjects().length() == 0 ? "Not Assigned" 
                        : employee.displayProjects());
                    }
                }
                System.out.println("----------------------------------------");
            } else {
                System.out.println("No Employedd assigned for the " + department.getName() + department.getId());
            }
        } else {
            System.out.println("No such department");
        }
    }

    /**
     * <p>
     * This method is used to display employee for particular project.
     * </p>  
     */
    public void displayEmployeeByProject() throws DataBaseException {
        projectController.displayProjects();
        int projectId = getId();
        Project project = employeeService.retrieveProjectById(projectId);
        if (project != null) {
            if (! project.getEmployees().isEmpty()) {
                System.out.println("---------------------------------------");
                String format = "| %-10s | %-15s |\n";
                System.out.format(format, "Id","Name");
                for (Employee employee : project.getEmployees()) {
                    if (employee.getIsActive()) {
                        System.out.format(format, employee.getId(), employee.getName(),
                        employee.getDepartment() == null ? "Not Assinged" 
                        : employee.getDepartment().getName());
                    }
                }
                System.out.println("----------------------------------------");
            } else {
                System.out.println("No Employedd assigned for the " + project.getName());
            }
        } else {
            System.out.println("No such project");
        }
    }
        
    /**
     * <p>
     * This method display the choice and handles all operation related to employee 
     * based on user choice.
     * </p>
     */
    public void displayChoice() {
        boolean repeat = true;
        while (repeat) {
            System.out.println("Select the choice [1-5]");
            System.out.println("1 ==> Create Employee");
            System.out.println("2 ==> List Employee");
            System.out.println("3 ==> Update Employee ");
            System.out.println("4 ==> Delete Employee ");
            System.out.println("5 ==> Assign Department");
            System.out.println("6 ==> Assign Project");
            System.out.println("7 ==> Display Employee By Department");
            System.out.println("8 ==> Display Employee By project");
            System.out.println("9 ==> Back");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        createEmployee();
                        break;
                    case 2:
                        displayOperation();
                        break;
                    case 3:
                        updateEmployee(); 
                        break;
                    case 4:
                        int id = getId();
                        deleteEmployee(id);  
                        break;
                    case 5:
                        assignDepartment();
                        break;
                    case 6:
                        assignProject();
                        break;
                    case 7:
                        displayEmployeeByDepartment();
                        break;
                    case 8:
                        displayEmployeeByProject();
                        break;
                    case 9:
                        repeat = false;
                        break;
                    default: System.out.println("Enter valid number");
                }
            } catch (DataBaseException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

}