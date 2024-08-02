package com.i2it.ems.project.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.i2it.ems.exception.DataBaseException;
import com.i2it.ems.model.Project;
import com.i2it.ems.project.service.ProjectService;
import com.i2it.ems.project.service.ProjectServiceImpl;
import com.i2it.ems.util.Validator;

/**
 * <p>
 * This class Manage the Project crud based on user choice
 * </p>
 */
public class ProjectController {
    
    private ProjectService projectService = new ProjectServiceImpl();
    private Scanner scanner = new Scanner(System.in);   

    /**
     * <p>
     * This method is used to get the data from user and create object for it.
     * </p>
     *
     * @throws DataBaseException 
     *     - When Exception occurs
     */
    public void createProject() throws DataBaseException {
        String name = getName();
        projectService.createProject(new Project(name));
    }
    
    /**
     * <p>
     * This method is used to display the projects data.
     * </p>
     * 
     * @throws DataBaseException 
     *     - When Exception occurs
     */
    public void displayProjects() throws DataBaseException {
        List<Project> projects = projectService.retrieveProjects();
        if (projects != null) {
            System.out.println("-------------------");
            String format = "| %-5s | %-10s |\n";
            System.out.format(format, "Id","Name");
            for (Project project : projects) {
                System.out.format(format, project.getId(), 
                                  project.getName()); 
            }
            System.out.println("-------------------");
        } else {
            System.out.println("No projects");
        }
    }
    
    /**
     * <p>
     * This method is used to display the Project data by id. 
     * </p>
     *
     * @throws DataBaseException 
     *     - When Exception occurs
     */
    public void displayProjectById() throws DataBaseException {
        System.out.println("Enter the Project id");
        int id = getId();
        Project project = projectService.retrieveProjectById(id);
        if (project != null) {
            System.out.println("-------------------");
            String format = "| %-5s | %-10s |\n";
            System.out.format(format, "Id", "Name");
            System.out.format(format, project.getId(), 
                              project.getName());
            System.out.println("-------------------");
        } else { 
            System.out.println("No such project id : " +id);
        }
    }

    /**
     * <p>
     * This method is used update the project.
     * </p>
     * 
     * @throws DataBaseException 
     *     - When Exception occurs
     */
    public void updateProject() throws DataBaseException {
        System.out.println("Enter the Project Id to update");
        int id = getId();
        Project project = projectService.retrieveProjectById(id);
        if (project != null) {
            Project updatedProject = updateOperation(project);
            projectService.updateProject(updatedProject); 
        } else {
            System.out.println("No such project id : " +id);
        }
    }

    /**
     * <p>
     * This method is used to delete the Project data by id.
     * </p> 
     *
     * @param id
     *     - accept the integer value
     * @throws DataBaseException 
     *     - When Exception occurs
     */
    public void deleteProject(int id) throws DataBaseException {
        Project project = projectService.retrieveProjectById(id);
        if (project != null) {
            project.setIsActive(false);
            projectService.deleteProject(project);
        } else {
            System.out.println("No such project id : " +id);
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
            System.out.println("1 ==> List Projects");
            System.out.println("2 ==> List Project By Id");
            System.out.println("3 ==> Back");
            try {
                int listChoice = Integer.parseInt(scanner.nextLine());;
                switch (listChoice) {
                    case 1: 
                        displayProjects();
                        break;
                    case 2:
                        displayProjectById();
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
     * operation
     * </p>
     * 
     * @throws DataBaseException 
     *     - When Exception occurs
     * @throws NumberFormatException 
     *     - When Exception occurs
     */
    public Project updateOperation(Project project) throws DataBaseException, NumberFormatException {
        boolean repeat = true;
        while (repeat) {
            System.out.println("1 ==> Update Project Name");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        String name = getName();
                        project.setName(name);  
                        break;
                    default: System.out.println("Enter valid option");
                }
            } catch (NumberFormatException e) {
                System.out.println("Enter valid option");
                throw new NumberFormatException("issue while display the list choice ");
            }
            repeat = false;
        }
        return project;
    }

    /**
     * <p>
     * This method is used get the name from user untill user enter proper name 
     * Ex ==> instead of name user gave some number or leave as empty or gave some 
     *        special character 
     * </p>
     * 
     * @return String
     *     - Project name   
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
     * Ex ==> instead of id user gives some String.
     * </p>
     * 
     * @return int 
     *     - Project id   
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
     * This method display the choice and handles all operation related to project 
     * based on user choice
     * </p>
     */
    public void displayChoice() {
        boolean repeat = true;
        while (repeat) {
            System.out.println("Select the choice [1-5]");
            System.out.println("1 ==> Create Project");
            System.out.println("2 ==> List Project");
            System.out.println("3 ==> Update Project");
            System.out.println("4 ==> Delete Project");
            System.out.println("5 ==> Back");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        createProject();
                        break;
                    case 2:
                        displayOperation();
                        break;
                    case 3:
                        updateProject(); 
                        break;
                    case 4:
                        int id = getId();
                        deleteProject(id);  
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