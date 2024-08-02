import java.util.Scanner;

import com.patient.controller.PatientController;
import com.diagnosis.controller.DiagnosisController;

/**
 * <p>
 * This is responsible for manage the Patient related data.
 * </p>
 */
public class ManagePatient {

    PatientController patientController = new PatientController();
    DiagnosisController diagnosisController = new DiagnosisController();
    Scanner scanner =  new Scanner(System.in);

    public static void main(String[] args) {
        ManagePatient managePatient = new ManagePatient();
        managePatient.displayChoice();
    }

    public void displayChoice() {
        boolean repeat = true;
        while (repeat) {
            System.out.println("Select the choice");
            System.out.println("1 ==> Patient page");
            System.out.println("2 ==> Diagnosis page");
            System.out.println("4 ==> Exit");
            switch (scanner.nextInt()) {
                case 1:
                    patientController.displayChoice();
                    break;
                case 2:
                    diagnosisController.displayChoice();
                    break;
                case 4:
                    repeat = false;
            }
        }
    }

}