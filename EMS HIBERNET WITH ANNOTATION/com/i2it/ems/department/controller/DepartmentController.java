package com.i2it.ems.department.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.i2it.ems.exception.DataBaseException;
import com.i2it.ems.department.service.DepartmentService;
import com.i2it.ems.department.service.DepartmentServiceImpl;
import com.i2it.ems.model.Department;
import com.i2it.ems.util.Validator;

/**
 * <p>
 * This class Manage the Department crud based on user choice.
 * </p>
 */
public class DepartmentController {
    
    private DepartmentService departmentService = new DepartmentServiceImpl();
    private Scanner scanner = new Scanner(System.in);   

    /**
     * <p>
     * This method is used to get the data from user and create object for it.
     * </p>
     *
     * @throws DataBaseException 
     *     - When Exception occurs
     */
    public void createDepartment() throws DataBaseException {
        String name = getName();
        departmentService.createDepartment(new Department(name));
    }
    
    /**
     * <p>
     * This method is used to display the Department data.
     * </p>
     * 
     * @throws DataBaseException 
     *     - When Exception occurs
     */
    public void displayDepartments() throws DataBaseException {
        List<Department> departments = departmentService.retrieveDepartments();
        if (departments != null) {
            System.out.println("-------------------");
            String format = "| %-5s | %-10s |\n";
            System.out.format(format, "Id","Name");
            for (Department department : departments) {
                System.out.format(format, department.getId(), 
                                  department.getName()); 
            }
            System.out.println("-------------------");
        } else {
            System.out.println("No departments ");
        }
    }
    
    /**
     * <p>
     * This method is used to display the Department data by id. 
     * </p>
     *
     * @throws DataBaseException 
     *     - When Exception occurs
     */
    public void displayDepartmentById() throws DataBaseException {
        System.out.println("Enter the Employee id");
        int id = getId();
        Department department = departmentService.retrieveDepartmentById(id);
        if (department != null ) {
            System.out.println("-------------------");
            String format = "| %-5s | %-10s |\n";
            System.out.format(format, "Id", "Name");
            System.out.format(format, department.getId(), 
                              department.getName());
            System.out.println("-------------------");
        } else {
            System.out.println("No such department id : " +id);
        }
    }

    /**
     * <p>
     * This method is used update the department.
     * </p>
     * 
     * @throws DataBaseException 
     *     - When Exception occurs
     */
    public void updateDepartment() throws DataBaseException {
        System.out.println("Enter the Department Id to update");
        int id = getId();
        Department department = departmentService.retrieveDepartmentById(id);
        if (department != null) {
            Department updatedDepartment = updateOperation(department);
            departmentService.updateDepartment(updatedDepartment); 
        } else {
            System.out.println("No such department id : " +id);
        }
    }

    /**
     * <p>
     * This method is used to delete the Department data by id.
     * </p>  
     *
     * @param id
     *     - accept the integer value
     * @throws DataBaseException 
     *     - When Exception occurs
     */
    public void deleteDepartment(int id) throws DataBaseException {
        Department department = departmentService.retrieveDepartmentById(id);
        if (department != null) {
            department.setIsActive(false);
            departmentService.deleteDepartment(department);
        } else {
            System.out.println("No such department id : " +id);
        }    
    }

    /**
     * <p>
     * This method is used to display the retrieve option.
     * </p> 
     *
     * @throws DataBaseException 
     *     - When Exception occurs
     * @throws NumberFormatException 
     *     - When Exception occurs
     */
    public void displayOperation() throws DataBaseException, NumberFormatException {
        boolean repeatList = true;
        while (repeatList) {
            System.out.println("Select the choice [1-3]");
            System.out.println("1 ==> List Departments");
            System.out.println("2 ==> List department By Id");
            System.out.println("3 ==> Back");
            try {
                int listChoice = Integer.parseInt(scanner.nextLine());;
                switch (listChoice) {
                    case 1: 
                        displayDepartments();
                        break;
                    case 2:
                        displayDepartmentById();
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
     * This method is used to display the update option and perform 
     * operation.
     * </p>
     *
     * @param Department 
     *     - Department details   
     * @return Department 
     *     - Department details
     * @throws DataBaseException 
     *     - When Exception occurs
     * @throws NumberFormatException 
     *     - When Exception occurs
     */
    public Department updateOperation(Department department) throws DataBaseException, 
                                      NumberFormatException {
        boolean repeat = true;
        while (repeat) {
            System.out.println("1 ==> Update Department Name");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        String name = getName();
                        department.setName(name);  
                        break;
                    default: System.out.println("Enter valid option");
                }
            } catch (NumberFormatException e) {
                System.out.println("Enter valid option");
                throw new NumberFormatException("issue while display the list choice ");
            }
            repeat = false;
        }
        return department;
    }

    /**
     * <p>
     * This method is used get the name from user untill user enter proper name 
     * Ex ==> instead of name user gave some number or leave as empty or gave some 
     *        special character.
     * </p> 
     * 
     * @return String 
     *     - Name of the department   
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
     * This method is used to get the id from user until user entere proper id 
     * Ex ==> instead of id user gives some String  
     * </p>
     * 
     * @return int 
     *     - Id of the department
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
     * This method display the choice and handles all operation related to department 
     * based on user choice
     * </p>
     */
    public void displayChoice() {
        boolean repeat = true;
        while (repeat) {
            System.out.println("Select the choice [1-5]");
            System.out.println("1 ==> Create Department");
            System.out.println("2 ==> List Department");
            System.out.println("3 ==> Update Department");
            System.out.println("4 ==> Delete Department");
            System.out.println("5 ==> Back");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        createDepartment();
                        break;
                    case 2:
                        displayOperation();
                        break;
                    case 3:
                        updateDepartment(); 
                        break;
                    case 4:
                        int id = getId();
                        deleteDepartment(id);  
                        break;
                    case 5:
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