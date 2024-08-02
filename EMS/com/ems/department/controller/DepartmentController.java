package com.ems.department.controller;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.ems.department.model.Department;
import com.ems.department.service.DepartmentService;
import com.ems.employee.model.Employee;
import com.ems.exception.EmployeeException;
import com.ems.util.Validator;

/**
 * This class handle the all operation related to department based on user request
 */
public class DepartmentController {

    private DepartmentService departmentService = new DepartmentService();
    private Scanner scanner = new Scanner(System.in);
    Validator validator = new Validator();
    static int departmentId = 1;

    /**
     * This method display the choice and handles all operation related to department 
     * based on user choice 
     */
    public void displayChoice() {
        try {
            boolean repeat = true;
            while (repeat) {
                System.out.println("Select the choice [1-5]");
                System.out.println("1 ==> Create Department");
                System.out.println("2 ==> List Department");
                System.out.println("3 ==> Update Department");
                System.out.println("4 ==> Delete Department");
                System.out.println("5 ==> Back");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        scanner.nextLine();
                        createDepartment();
                        break;
                    case 2:
                        if (departmentService.isDepartmentActive()) {
                            displayOperation();
                        } else {
                            System.out.println("No Data In DataBase");
                        }
                        break;
                    case 3:
                        if (departmentService.isDepartmentActive()) {
                            updateDepartment();   
                        } else {
                            System.out.println("No Data In DataBase");  
                        } 
                        break;
                    case 4:
                        if (departmentService.isDepartmentActive()) {
                            System.out.println("Enter the Department id to delete");
                            int id = getId();
                            deleteDepartment(id);
                        } else {
                            System.out.println("No Data In DataBase");
                        }  
                        break;
                    case 5:
                        repeat = false;
                } 
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            System.out.println("Please Enter Number between [1-6]");
        } catch (EmployeeException e) {
            System.out.println(e.getMessage());
        }    
    }

    /**
     * This method is used to get the data from user
     */
    public void createDepartment() throws EmployeeException {
        String name = getDepartmentName();
        departmentService.createDepartment(new Department(departmentId, name));
        departmentId++;
        System.out.println("Department Added ");
    }

    /**
     * This method is used to display the Department data
     */
    public void displayDepartments() throws EmployeeException {
        List<Department> departments = departmentService.retrieveDepartments();
        System.out.println("-------------------");
        String format = "| %-5s | %-10s |\n";
        System.out.format(format, "Id", "Department Name");
        for (Department department : departments) {
            System.out.format(format, department.getDepartmentId(),
                              department.getDepartmentName()); 
        }
        System.out.println("-------------------");
    }

    /**
     * This method is used to display the Department data by id  
     */
    public void displayDepartmentById() throws EmployeeException {
        System.out.println("Enter the id");
        int id = getId();
        Department department = departmentService.retrieveDepartmentById(id);
        if (department != null) {
            System.out.println("-------------------");
            String format = "| %-5s | %-10s |\n";
            System.out.format(format, "Id", "Name");
            System.out.format(format, department.getDepartmentId(),
                              department.getDepartmentName());
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
            System.out.println("1 ==> List Department");
            System.out.println("2 ==> List Department By Id");
            System.out.println("3==> List Employee By Department");
            System.out.println("3 ==> Back");
            try {
                int listChoice = scanner.nextInt();
                switch (listChoice) {
                    case 1: 
                        displayDepartments();
                        break;
                    case 2:
                        displayDepartmentById();
                        break;
                    case 3:
                        displayEmployeeBYDepartment();
                        break;
                    case 4:
                        repeatList = false;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Please Enter Number between [1-3]");
            }
        }
    }

    /**
     * This method is used update the employee
     */
    public void updateDepartment() throws EmployeeException {
        System.out.println("Enter the department id to be upddate");
        int id = getId();
        Department department = departmentService.retrieveDepartmentById(id);
        if (department != null) {
            Department updatedDepartment = updateOperation(department);
            departmentService.updateDepartment(id, updatedDepartment); 
        } else {
            System.out.println("Id Not Fount Enter Valid Id");
        }
    } 

    /**
     * This method is used to display the update option and perform 
     * operation
     */
    public Department updateOperation(Department department) throws EmployeeException {
        System.out.println("Select the option ");
        System.out.println("1 ==> Update Name");
        try {
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    String name = getDepartmentName();
                    department.setDepartmentName(name);  
                    break;
                default: System.out.println("Enter valid option between[1-3]");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Please Enter Number between [1-3]");
            updateOperation(department);
        } 
        return department;
    }

    /**
     * This method is used to delete the Department data by id  
     *
     * @param id
     *     - accept the integer value
     */
    public void deleteDepartment(int id) throws EmployeeException {
        Department department = departmentService.retrieveDepartmentById(id);
        if (department != null) {
            departmentService.deleteDepartment(id); 
        } else {
            System.out.println("Id Not Fount Enter Valid Id");
        }    
    }

    /**
     * This method is used to display the employee by id  
     */
    public void displayEmployeeBYDepartment() throws EmployeeException {
        System.out.println("Enter the Department id");
        int id = getId();
        Department department = departmentService.retrieveDepartmentById(id);
        if (department != null) {
            System.out.println("Department name : " + department.getDepartmentName());
            System.out.println("-------------------");
            String format = "| %-5s | %-10s |\n";
            System.out.format(format, "Id", "Name");
            for (Employee employee : department.getEmployees()) {
                System.out.format(format,employee.getEmployeeId(),
                                  employee.getEmployeeName());
            }
        } else {
            System.out.println("Id Not Fount Enter Valid Id");
        }
    }

    /**
     * This method is used to check the department is there or not
     *
     * @return boolean
     *     - true if condition satisfied  
     */
    public boolean isDepartmentActive() throws EmployeeException {
        return departmentService.isDepartmentActive();
    }

    /**
     * This method is used to get the department name from user until user enters proper name 
     * Ex ==> instead of name user gave some number or leave as empty or gave some 
     *        special character   
     */
    public String getDepartmentName() {
        String name = "";
        do {
            System.out.println("Enter the Department Name");
            name = scanner.nextLine();
        } while (validator.stringValidate(name));
        return name;
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
                id = scanner.nextInt();
                repeat = false;
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                System.out.println("Please Enter Number");
                repeat = false;
            }
        } while (repeat);
        return id;
    }

}