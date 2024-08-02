package com.ems.project.controller;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import com.ems.exception.EmployeeException;
import com.ems.project.model.Project;
import com.ems.project.service.ProjectService;
import com.ems.util.Validator;

/**
 * This class handle the all operation related to project based on user request
 */
public class ProjectController {

    private ProjectService projectService = new ProjectService();
    private Validator validator = new Validator(); 
    private Scanner scanner = new Scanner(System.in);
    static int projectId = 1;

    /**
     * This method display the choice and handles all operation related to project 
     * based on user choice
     */
    public void displayChoice() {
        try {
            boolean repeat = true;
            while (repeat) {
                System.out.println("Select the choice [1-5]");
                System.out.println("1 ==> Create Project");
                System.out.println("2 ==> List Project");
                System.out.println("3 ==> Update Project");
                System.out.println("4 ==> Delete Project");
                System.out.println("5 ==> Back");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        createProject();
                        break;
                    case 2:
                        if (projectService.isProjectThere()) {
                            displayOperation();
                        } else {
                            System.out.println("No Data In DataBase");
                        }
                        break;
                    case 3:
                        if (projectService.isProjectThere()) {
                            updateProject();   
                        } else {
                            System.out.println("No Data In DataBase");  
                        } 
                        break;
                    case 4:
                        if (projectService.isProjectThere()) {
                            System.out.println("Enter the id to delete");
                            int id = getId();
                            deleteProject(id);
                        } else {
                            System.out.println("No Data In DataBase");
                        }  
                        break;
                    case 5:
                        repeat = false;
                } 
            }
        } catch (EmployeeException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            System.out.println("Please Enter Number between [1-6]");
        }
    }

    /**
     * This method is used to get the data from user
     */
    public void createProject() throws EmployeeException {
        String name = getProjectName();
        projectService.createProject(new Project(projectId, name));
        projectId++;
        System.out.println("Project Added ");
    }

    /**
     * This method is used to display the project data
     */
    public void displayProjects() throws EmployeeException {
        List<Project> projects = projectService.retrieveProjects();
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
     * This method is used to display the Project data by id  
     */
    public void displayProjectById() throws EmployeeException {
        System.out.println("Enter the id");
        int id = getId();
        Project project = projectService.retrieveProjectById(id);
        if (project != null) {
            System.out.println("-------------------");
            String format = "| %-5s | %-10s |\n";
            System.out.format(format, "Id", "Project Name");
            System.out.format(format, project.getProjectId(),
                              project.getProjectName());
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
            System.out.println("1 ==> List Project");
            System.out.println("2 ==> List Project By Id");
            System.out.println("3 ==> Back");
            try {
                int listChoice = scanner.nextInt();
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
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Please Enter Number between [1-3]");
            }
        }
    }

    /**
     * This method is used update the Project
     */
    public void updateProject() throws EmployeeException {
        System.out.println("Enter the project id to be update");
        int id = getId();
        Project project = projectService.retrieveProjectById(id);
        if (project != null) {
            Project updatedProject = updateOperation(project);
            projectService.updateProject(id, updatedProject); 
        } else {
            System.out.println("Id Not Fount Enter Valid Id");
        }
    } 

    /**
     * This method is used to display the update option and perform 
     * operation
     */
    public Project updateOperation(Project project) throws EmployeeException {
        System.out.println("Select the option ");
        System.out.println("1 ==> Update Name");
        try {
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    String name = getProjectName();
                    project.setProjectName(name);  
                    break;
                default: System.out.println("Enter valid option between[1-3]");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Please Enter Number between [1-3]");
            updateOperation(project);
        } 
        return project;
    }

    /**
     * This method is used to delete the project data by id  
     *
     * @param id
     *     - accept the integer value
     */
    public void deleteProject(int id) throws EmployeeException {
        Project project = projectService.retrieveProjectById(id);
        if (project != null) {
            projectService.deleteProject(id); 
        } else {
            System.out.println("Id Not Fount Enter Valid Id");
        }    
    }

    /**
     * This method is used get the project name from user untill user enter proper name 
     * Ex ==> instead of name user gave some number or leave as empty or gave some 
     *        special character   
     */
    public String getProjectName() {
        String name = "";
        do {
            System.out.println("Enter the project Name");
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