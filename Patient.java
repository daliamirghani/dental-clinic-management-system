package DCMSpack;
import java.io.Serializable;
import java.util.ArrayList;


public class Patient extends User implements Serializable {
    private String patientHistory;
    protected ArrayList<Appointment> appointments = new ArrayList<>();
    private int age;
    private String gender;
    private String bloodType;
    private float weight;
    private float height;
    private String prescription="No prescription";
    public String getPrescription() {
        return prescription;
    }
    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }


    public Patient(String firstName, String lastName, String patientHistory, int age, String gender,
                   String bloodType, float weight, float height, String username, String email, String password, String mobileNumber) {
        super(firstName, lastName, username, email, password, mobileNumber);
        this.patientHistory = patientHistory;
        this.age = age;
        this.gender = gender;
        this.bloodType = bloodType;
        this.weight = weight;
        this.height = height;
    }

    public String getPatientHistory() {
        return patientHistory;
    }


    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getBloodType() {
        return bloodType;
    }

    public float getWeight() {
        return weight;
    }

    public float getHeight() {
        return height;
    }

    public void setPatientHistory(String patientHistory) {
        this.patientHistory = patientHistory;
    }




    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setHeight(float height) {
        this.height = height;
    }


    public  void displayAllAppointment(){
        for(Appointment app: appointments ){
            System.out.println(app.serviceType.getName()+"\t"+app.serviceType.getPrice());
        }
    }
}






