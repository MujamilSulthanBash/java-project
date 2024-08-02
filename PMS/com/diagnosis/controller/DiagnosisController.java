package com.diagnosis.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.exception.DataBaseException;
import com.diagnosis.service.DiagnosisService;
import com.diagnosis.service.DiagnosisServiceImpl;
import com.model.Diagnosis;
import com.util.Validator;

/**
 * <p>
 * This class Manage the Diagnosis crud based on user choice.
 * </p>
 */
public class DiagnosisController {
    
    private DiagnosisService diagnosisService = new DiagnosisServiceImpl();
    private Scanner scanner = new Scanner(System.in);   

    /**
     * <p>
     * This method is used to get the data from user and create object for it.
     * </p>
     *
     * @throws DataBaseException 
     *     - When Exception occurs
     */
    public void createDiagnosis() throws DataBaseException {
        String name = getName();
        diagnosisService.create(new Diagnosis(name));
    }
    
    /**
     * <p>
     * This method is used to display all Diagnosis data.
     * </p>
     * 
     * @throws DataBaseException 
     *     - When Exception occurs
     */
    public void displayDiagnosis() throws DataBaseException {
        List<Diagnosis> diagnosis = diagnosisService.retrieveDiagnosis();
        if (diagnosis != null) {
            System.out.println("-------------------");
            String format = "| %-5s | %-10s | %-5s |\n";
            System.out.format(format, "Id","Name");
            for (Diagnosis diagnosi : diagnosis) {
                System.out.format(format, diagnosis.getId(), 
                                  diagnosis.getName()); 
            }
            System.out.println("-------------------");
        } else {
            System.out.println("No Diagnosis  ");
        }
    }
    
    /**
     * <p>
     * This method is used to display the Diagnosis data by id. 
     * </p>
     *
     * @throws DataBaseException 
     *     - When Exception occurs
     */
    public void displayDiagnosisById() throws DataBaseException {
        System.out.println("Enter the patient id");
        int id = getId();
        Diagnosis diagnosis = diagnosisService.retrievediagnosisById(id);
        if (patient != null ) {
            System.out.println("-------------------");
            String format = "| %-5s | %-10s | %-5s |\n";
            System.out.format(format, "Id", "Name","op/ip");
            System.out.format(format, diagnosis.getId(), 
                                  diagnosis.getName());
            System.out.println("-------------------");
        } else {
            System.out.println("No such Diagnosis id : " +id);
        }
    }

    /**
     * <p>
     * This method is used update the Diagnosis.
     * </p>
     * 
     * @throws DataBaseException 
     *     - When Exception occurs
     */
    public void updateDiagnosis() throws DataBaseException {
        System.out.println("Enter the Diagnosis Id to update");
        int id = getId();
        Diagnosis Diagnosis = diagnosisService.retrieveDiagnosisById(id);
        if (diagnosis != null) {
            Diagnosis updatedDiagnosis = updateOperation(diagnosis);
            diagnosisService.updateDiagnosis(updatedDiagnosis); 
        } else {
            System.out.println("No such Diagnosis id : " +id);
        }
    }

    /**
     * <p>
     * This method is used to delete the Diagnosis data by id.
     * </p>  
     *
     * @param id
     *     - accept the integer value
     * @throws DataBaseException 
     *     - When Exception occurs
     */
    public void deleteDiagnosis(int id) throws DataBaseException {
        Diagnosis diagnosis = diagnosisService.retrieveDiagnosisById(id);
        if (diagnosis != null) {
            diagnosisService.deleteDiagnosis(diagnosis);
        } else {
            System.out.println("No such Diagnosis id : " +id);
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
            System.out.println("1 ==> List Diagnosis");
            System.out.println("2 ==> List Diagnosis By Id");
            System.out.println("3 ==> Back");
            try {
                int listChoice = Integer.parseInt(scanner.nextLine());;
                switch (listChoice) {
                    case 1: 
                        displayDiagnosis();
                        break;
                    case 2:
                        displayDiagnosisById();
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
     * @param Diagnosis 
     *     - Diagnosis details   
     * @return Diagnosis 
     *     - Diagnosis details
     * @throws DataBaseException 
     *     - When Exception occurs
     * @throws NumberFormatException 
     *     - When Exception occurs
     */
    public Diagnosis updateOperation(Diagnosis diagnosis) throws DataBaseException, 
                                      NumberFormatException {
        boolean repeat = true;
        while (repeat) {
            System.out.println("1 ==> Update Diagnosis Name");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        String name = getName();
                        patient.setName(name);  
                        break;
                    default: System.out.println("Enter valid option");
                }
            } catch (NumberFormatException e) {
                System.out.println("Enter valid option");
                throw new NumberFormatException("issue while display the list choice ");
            }
            repeat = false;
        }
        return Diagnosis;
    }

    /**
     * <p>
     * This method is used get the name from user untill user enter proper name 
     * Ex ==> instead of name user gave some number or leave as empty or gave some 
     *        special character.
     * </p> 
     * 
     * @return String 
     *     - Name of the Diagnosis   
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
            System.out.println("1 ==> Create Diagnosis");
            System.out.println("2 ==> List Diagnosis");
            System.out.println("3 ==> Update Diagnosis");
            System.out.println("4 ==> Delete Diagnosis");
            System.out.println("5 ==> Back");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        createDiagnosis();
                        break;
                    case 2:
                        displayOperation();
                        break;
                    case 3:
                        updateDiagnosis(); 
                        break;
                    case 4:
                        int id = getId();
                        deleteDiagnosis(id);  
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