package com.ems.employee.controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.ems.department.model.Department;
import com.ems.employee.model.Employee;
import com.ems.employee.service.EmployeeService;
import com.ems.exception.EmployeeException;
import com.ems.project.model.Project;
import com.ems.util.Validator;

/**
 * This class handle the all operation related to Employee based on user request
 */
public class EmployeeController {

    private EmployeeService employeeService = new EmployeeService();
    private Scanner scanner = new Scanner(System.in);
    Validator validator = new Validator();
    static int employeeId = 1;

    /**
     * This method display the choice and handles all operation related to employee 
     * based on user choice
     */
    public void displayChoice() {
        boolean repeat = true;
        while (repeat) {
            System.out.println("Select the choice [1-5]");
            System.out.println("1 ==> Create Employee");
            System.out.println("2 ==> List Employee");
            System.out.println("3 ==> Update Employee ");
            System.out.println("4 ==> Delete Employee ");
            System.out.println("5 ==> Add Projects");
            System.out.println("6 ==> Back");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        createEmployee();
                        break;
                    case 2:
                        if (employeeService.isEmployeeActive()) {
                            displayOperation();
                        } else {
                            System.out.println("No Data In DataBase");
                        }
                        break;
                    case 3:
                        if (employeeService.isEmployeeActive()) {
                            updateEmployee();   
                        } else {
                            System.out.println("No Data In DataBase");  
                        } 
                        break;
                    case 4:
                        if (employeeService.isEmployeeActive()) {
                            int id = getId();
                            deleteEmployee(id);
                        } else {
                            System.out.println("No Data In DataBase");
                        }  
                        break;
                    case 5:
                        if (employeeService.isEmployeeActive()) {
                            addProjects();   
                        } else {
                            System.out.println("No Data In DataBase");  
                        } 
                        break;
                    case 6:
                        repeat = false;
                        break;
                    default: System.out.println("Enter valid number");
                }
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
                System.out.println("Please Enter Number between [1-6]");
            } catch (EmployeeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * This method is used to get the data from user and create object for it
     */
    public void createEmployee() throws EmployeeException {
        String name = getName();
        String address= getAddress();
        String dob = getDob();
        displayDepartments();
        int id = getId();
        Department department = employeeService.retrieveDepartmentById(id);
        employeeService.createEmployee(new Employee(employeeId, name , dob, address, department));
        employeeId++;
        System.out.println("Employee Added ");
    }

    /**
     * This method is used to display the Employees data
     */
    public void displayEmployees() throws EmployeeException {
        List<Employee> employees = employeeService.retrieveEmployees();
        System.out.println("-------------------");
        String format = "| %-5s | %-10s | %-15s |\n";
        System.out.format(format, "Id","Name","Department");
        for (Employee employee : employees) {
            System.out.format(format, employee.getEmployeeId(), 
                              employee.getEmployeeName(),
                              employee.getDepartment().getDepartmentName()); 
        }
        System.out.println("-------------------");
    }

    /**
     * This method is used to display the Employee data by id  
     *
     * @param id
     *     - accept the integer value
     */
    public void displayEmployeeById() throws EmployeeException {
        System.out.println("Enter the Employee id");
        int id = getId();
        Employee employee = employeeService.retrieveEmployeeById(id);
        if (employee != null) {
            System.out.println("-------------------");
            String format = "| %-5s | %-10s | %-5s | %-10s | %-15s |\n";
            System.out.format(format, "Id", "Name", "Age", "Address","Department");
            System.out.format(format, employee.getEmployeeId(), 
                              employee.getEmployeeName(), 
                              employee.getEmployeeAge(),
                              employee.getEmployeeAddress(),
                              employee.getDepartment().getDepartmentName());
            System.out.println("-------------------");
        } else {
            System.out.println("Id Not Fount Enter Valid Id");
        }
    }

    /**
     * This method is used to display the retrieve option 
     *
     */
    public void displayOperation() throws EmployeeException {
        boolean repeatList = true;
        while (repeatList) {
            System.out.println("Select the choice [1-3]");
            System.out.println("1 ==> List Employees");
            System.out.println("2 ==> List Employee By Id");
            System.out.println("3 ==> Back");
            try {
                int listChoice = scanner.nextInt();
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
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                System.out.println("Please Enter Number between [1-3]");
            }
        }
    }

    /**
     * This method is used update the employee
     */
    public void updateEmployee() throws EmployeeException {
        System.out.println("Enter the Employee Id to update");
        int id = getId();
        Employee employee = employeeService.retrieveEmployeeById(id);
        if (employee != null) {
            Employee updatedEmployee = updateOperation(employee);
            employeeService.updateEmployee(id, updatedEmployee); 
        } else {
            System.out.println("Id Not Fount Enter Valid Id");
        }
    } 

    /**
     * This method is used to display the update option and perform 
     * operation
     */
    public Employee updateOperation(Employee employee) throws EmployeeException {
        System.out.println("1 ==> Update Name");
        System.out.println("2 ==> Update Date Of Birth");
        System.out.println("3 ==> Update Address");
        try {
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    String name = getName();
                    employee.setEmployeeName(name);  
                    break;
                case 2:
                    String dob = getDob();
                    employee.setEmployeeDob(dob);
                    break;
                case 3:
                    String address = getAddress();
                    employee.setEmployeeAddress(address);
                    break;
                default: System.out.println("Enter valid option between[1-3]");
            }
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
            System.out.println("Enter valid option between[1-3]");
            updateOperation(employee);
        }
        return employee;
    }

    /**
     * This method is used to delete the Employee data by id  
     *
     * @param id
     *     - accept the integer value
     */
    public void deleteEmployee(int id) throws EmployeeException {
        Employee employee = employeeService.retrieveEmployeeById(id);
        if (employee != null) {
            employeeService.deleteEmployee(id); 
        } else {
            System.out.println("Id Not Fount Enter Valid Id");
        }    
    }

    /**
     * This method is used to display the department  
     */
    public void displayDepartments() throws EmployeeException {
        List<Department> departments = employeeService.retrieveDepartments();
        System.out.println("-------------------");
        String format = "| %-5s | %-10s |\n";
        System.out.format(format, "Id","Department Name");
        for (Department department : departments) {
            System.out.format(format, department.getDepartmentId(),
                              department.getDepartmentName()); 
        }
        System.out.println("-------------------");
    }

    /**
     * This method is used to display the project  
     */
    public void displayProjects() {
        List<Project> projects = employeeService.retrieveProjects();
        System.out.println("-------------------");
        String format = "| %-5s | %-10s |\n";
        System.out.format(format, "Id","Project Name");
        for (Project project : projects) {
            System.out.format(format, project.getProjectId(),
                              project.getProjectName()); 
        }
        System.out.println("-------------------");
    }

    /**
     * This method is used to assign the project to employee  
     */
    public void assignProject() throws EmployeeException {
        System.out.println("--Employee Id--");
        scanner.nextLine();
        int employeeId = getId();
        Employee employee = employeeService.retrieveEmployeeById(employeeId);
        System.out.println("--Project Id--");
        int projectId = getId();
        Project project = employeeService.retrieveProjectById(projectId);
        employee.addProject(project);
        project.addEmployee(employee);
    }

    /**
     * This method is used to display the employee project  
     */
    public void displayEmployeeProject() throws EmployeeException {
        System.out.println("--Employee Id--");
        scanner.nextLine();
        int employeeId = getId();
        Employee employee = employeeService.retrieveEmployeeById(employeeId);
        List<Project> projects = employee.getProjects();
        System.out.println("-------------------");
        String format = "| %-5s | %-10s |\n";
        System.out.format(format, "Id","Name");
        for (Project project : projects) {
            System.out.format(format, project.getProjectId(), 
                              project.getProjectName()); 
        }
        System.out.println("-------------------");
    }

    /**
     * This method is used to dispaly the add project operations  
     */
    public void addProjects() throws EmployeeException {
        boolean repeatProject = true;
        while (repeatProject) {
            System.out.println("Enter the choice");
            System.out.println("1 ==> Display Projects");
            System.out.println("2 ==> Assign Projects");
            System.out.println("3 ==> Emplyee Projects");
            System.out.println("4 ==> Back");
            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        displayProjects();
                        break;
                    case 2:
                        assignProject();
                        break;
                    case 3:
                        displayEmployeeProject();
                        break;
                    case 4:
                        repeatProject = false;
                        break;
                    default: System.out.println("Enter valid option between[1-4]");
                }
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                System.out.println("Enter valid option between[1-4]");
            }
        }
    }

    /**
     * This method is used get the name from user untill user enter proper name 
     * Ex ==> instead of name user gave some number or leave as empty or gave some 
     *        special character   
     */
    public String getName() {
        String name = "";
        do {
            System.out.println("Enter the name");
            name = scanner.nextLine();
        } while (validator.stringValidate(name));
        return name;
    }

    /**
     * This method is used to get the address from user until user enters proper address 
     * Ex ==> instead of address user gave some number or leave as empty or gave some 
     *        special character   
     */
    public String getAddress() {
        String address = "";
        do {
            System.out.println("Enter the Address");
            address = scanner.nextLine();
        } while (validator.stringValidate(address));
        return address;
    }
    
    /**
     * This method is used to get the id from user until user entere proper id 
     * Ex ==> instead of id user gives some String  
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
                System.out.println(e.getMessage());
                System.out.println("Please Enter Number");
                repeat = false;
            }
        } while (repeat);
        return id;
    }

    /**
     * This method is used to get the Date of birth from user untill user enters properly 
     * Ex ==> Accepts the pattern ==> --yyyy-mm-dd--  
     */
    public String getDob() {
        String dob = "";
        boolean repeat = false;
        do {
            try {
                System.out.println("Enter the Date of birth");
                System.out.println("------YYYY-MM-DD-------");
                dob = scanner.nextLine();
                repeat = validator.dobValidate(dob);
            } catch (DateTimeParseException e) {
                System.out.println(e.getMessage());
                System.out.println("Please enter date in YYYY-MM-DD format.");
            }
        } while (! repeat);
        return dob;
    }

}