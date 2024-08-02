import java.util.Set;

importcom.model.Patient;

public class Diagnosis {

    private int id;
    private String name;
    private Set<Patient> patients;

    public Diagnosis(String name) {
        this.name = name
    }  
      
    public String getName() {
        return this.name;
    }
   
    public Set<Patients> getPatients() {
        return this.patients;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPatient(Set<Patient> patients) {
        this.patients = patients;
    } 

    public String displayPatients() {
        StringBuilder stringBuilder = new StringBuilder();
        for(Patient patient : patients) {
            stringBuilder.append(patient.getName() + ", ");    
        }
        return stringBuilder.toString();
    }
}