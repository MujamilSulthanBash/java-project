package com.patient.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.exception.DataBaseException;
import com.patient.service.PatientService;
import com.patient.service.PatientServiceImpl;
import com.model.Patient;
import com.util.Validator;

/**
 * <p>
 * This class Manage the Patient crud based on user choice.
 * </p>
 */
public class PatientController {
    
    private PatientService patientService = new PatientServiceImpl();
    private Scanner scanner = new Scanner(System.in);   

    /**
     * <p>
     * This method is used to get the data from user and create object for it.
     * </p>
     *
     * @throws DataBaseException 
     *     - When Exception occurs
     */
    public void createPatient() throws DataBaseException {
        String firstName = getName();
        String lastName = getName();
        String type = getName();
        patientService.create(new Patient(firstName, lastName, type));
    }
    
    /**
     * <p>
     * This method is used to display all patient data.
     * </p>
     * 
     * @throws DataBaseException 
     *     - When Exception occurs
     */
    public void displayPatients() throws DataBaseException {
        List<Patient> patients = patientService.retrievePatients();
        if (patients != null) {
            System.out.println("-------------------");
            String format = "| %-5s | %-10s | %-5s |\n";
            System.out.format(format, "Id","Name","op/ip");
            for (Patient patient : patients) {
                System.out.format(format, patient.getId(), 
                                  patient.getName(), patient.getType()); 
            }
            System.out.println("-------------------");
        } else {
            System.out.println("No patient  ");
        }
    }
    
    /**
     * <p>
     * This method is used to display the patient data by id. 
     * </p>
     *
     * @throws DataBaseException 
     *     - When Exception occurs
     */
    public void displayPatientById() throws DataBaseException {
        System.out.println("Enter the patient id");
        int id = getId();
        Patient patient = patientService.retrievePatientById(id);
        if (patient != null ) {
            System.out.println("-------------------");
            String format = "| %-5s | %-10s | %-5s |\n";
            System.out.format(format, "Id", "Name","op/ip");
            System.out.format(format, patient.getId(), 
                                  patient.getName(), patient.getType());
            System.out.println("-------------------");
        } else {
            System.out.println("No such patient id : " +id);
        }
    }

    /**
     * <p>
     * This method is used update the Patient.
     * </p>
     * 
     * @throws DataBaseException 
     *     - When Exception occurs
     */
    public void updatePatient() throws DataBaseException {
        System.out.println("Enter the Patient Id to update");
        int id = getId();
        Patient patient = patientService.retrievePatientById(id);
        if (patient != null) {
            Patient updatedPatient = updateOperation(patient);
            patientService.updatePatient(updatedPatient); 
        } else {
            System.out.println("No such patient id : " +id);
        }
    }

    /**
     * <p>
     * This method is used to delete the patient data by id.
     * </p>  
     *
     * @param id
     *     - accept the integer value
     * @throws DataBaseException 
     *     - When Exception occurs
     */
    public void deletePatient(int id) throws DataBaseException {
        Patient patient = patientService.retrievePatientById(id);
        if (patient != null) {
            patientService.deletePatient(patient);
        } else {
            System.out.println("No such Patient id : " +id);
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
            System.out.println("1 ==> List Patient");
            System.out.println("2 ==> List Patient By Id");
            System.out.println("3 ==> Back");
            try {
                int listChoice = Integer.parseInt(scanner.nextLine());;
                switch (listChoice) {
                    case 1: 
                        displayPatients();
                        break;
                    case 2:
                        displayPatientById();
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
     * @param Patient 
     *     - patient details   
     * @return Patient 
     *     - patient details
     * @throws DataBaseException 
     *     - When Exception occurs
     * @throws NumberFormatException 
     *     - When Exception occurs
     */
    public Department updateOperation(Patient patient) throws DataBaseException, 
                                      NumberFormatException {
        boolean repeat = true;
        while (repeat) {
            System.out.println("1 ==> Update Patient firstName");
            System.out.println("2 ==> Update Patient lastName");
            System.out.println("3 ==> Update Patient Type");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        String firstname = getName();
                        patient.setFirstName(firstname);  
                        break;
                    case 2:
                        String lastName = getName();
                        patient.setLastName(lastName);  
                        break;
                    case 3:
                        String type = getName();
                        patient.setType(type);  
                        break;
                    default: System.out.println("Enter valid option");
                }
            } catch (NumberFormatException e) {
                System.out.println("Enter valid option");
                throw new NumberFormatException("issue while display the list choice ");
            }
            repeat = false;
        }
        return patient;
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
            System.out.println("1 ==> Create Patient");
            System.out.println("2 ==> List Patient");
            System.out.println("3 ==> Update Patient");
            System.out.println("4 ==> Delete Patient");
            System.out.println("5 ==> Back");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        createPatient();
                        break;
                    case 2:
                        displayOperation();
                        break;
                    case 3:
                        updatePatient(); 
                        break;
                    case 4:
                        int id = getId();
                        deletePatient(id);  
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