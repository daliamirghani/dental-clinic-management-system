package DCMSpack;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class DentalClinic  implements Serializable {
    ArrayList <Patient>patients = new ArrayList<>();
    ArrayList<Doctor>doctors = new ArrayList<>();
    ArrayList<Receptionist>receptionists =new ArrayList<>();
    Service[] Services= {new Service("Braces",200),new Service("Filling",300),
            new Service("Whitening",400),new Service("RCT",500)};
    private final String clinicName,location;

    public DentalClinic(String name, String location) {
        this.clinicName = name;
        this.location = location;
    }
    public void display(){
        System.out.println("Service name\t"+"Price");
        for(Service s:Services){
            System.out.println(s.getName() +"\t"+s.getPrice());
        }
    }
    public Doctor docSignUp() {
        Scanner userInput = new Scanner(System.in);
        System.out.println("            DOCTOR SIGNUP       ");
        System.out.println("  *******************************");
        //getting signup details from the user

        System.out.println(" ENTER FIRSTNAME ");
        String FIRSTName = userInput.nextLine();
        System.out.println(" ENTER LASTNAME ");
        String LASTName = userInput.nextLine();
        System.out.println(" ENTER USERNAME ");
        String username = userInput.nextLine();

        System.out.println(" ENTER YOUR EMAIL  ");
        String email = userInput.nextLine();
        int validation;;
        String password;
        do {
            validation =1;
            System.out.println(" ENTER YOUR PASSWORD ");
            password = userInput.nextLine();
            System.out.println(" ENTER YOUR PASSWORD AGAIN FOR VERIFICATION");
            String PasswordVerifier = userInput.nextLine();

            if (!password.equals(PasswordVerifier)){
                System.out.println("PASSWORD DO NOT MATCH ! PLEASE TRY AGAIN!");
                validation=0;

            }
        }while(validation==0);
        System.out.println(" ENTER YOUR MOBILE NUMBER ");
        String mobile = userInput.nextLine();
        System.out.println(" ENTER YOUR SPECIALIZATION ");
        String specialization = userInput.nextLine();
        Doctor doctor = new Doctor(FIRSTName,LASTName,username,email, password, mobile,specialization);
        return doctor;

// Add the new patient to the array
    }
    public Patient patientSignUp(){
        Scanner userInput = new Scanner(System.in);
        System.out.println("            PATIENT SIGNUP       ");
        System.out.println("  *******************************");
        //getting signup details from the user

        System.out.println(" ENTER FIRSTNAME ");
        String FIRSTName = userInput.nextLine();
        System.out.println(" ENTER LASTNAME ");
        String LASTName = userInput.nextLine();

        //getting patient history
        int validation; //to make sure user enters valid answer
        String patientMedicalHistory; //variable declaration and initialization for scope matters
        do {
            validation=1;
            System.out.println(" IS there any medical history?(Y/N) ");

            char answer=userInput.nextLine().trim().toLowerCase().charAt(0);
            if (answer=='y'){
                System.out.println("enter your medical history");
                patientMedicalHistory =userInput.nextLine();
            }
            else if (answer=='n'){
                patientMedicalHistory ="NO MEDICAL HISTORY";
            }
            else {
                patientMedicalHistory =null;
                System.out.println("invalid answer");
                validation =0;
            }
        }while(validation==0);


        System.out.println(" ENTER AGE ");
        int AgeSettingVariable = userInput.nextInt();
        userInput.nextLine();//consume new line

        System.out.println(" ENTER GENDER ");
        String GenderSettingVariable = userInput.nextLine();

        System.out.println(" ENTER BLOOD TYPE ");
        String BloodTypeSettingVariable = userInput.nextLine();

        System.out.println(" ENTER WEIGHT (in kg )");
        float weightSettingVariable = userInput.nextFloat();
        userInput.nextLine();//consume new line

        System.out.println(" ENTER HEIGHT (in meters )");
        float heightSettingVariable = userInput.nextFloat();
        userInput.nextLine();//consume new line

        System.out.println(" ENTER USERNAME ");
        String username = userInput.nextLine();

        System.out.println(" ENTER YOUR EMAIL  ");
        String EmailSettingVariable = userInput.nextLine();

        //getting password
        String PasswordSettingVariable;
        do {
            validation =1;
            System.out.println(" ENTER YOUR PASSWORD ");
            PasswordSettingVariable = userInput.nextLine();
            System.out.println(" ENTER YOUR PASSWORD AGAIN FOR VERIFICATION");
            String PasswordVerifier = userInput.nextLine();

            if (!PasswordSettingVariable.equals(PasswordVerifier)){
                System.out.println("PASSWORD DO NOT MATCH ! PLEASE TRY AGAIN!");
                validation=0;

            }
        }while(validation==0);


//getting mobileNumber
        System.out.println(" ENTER YOUR MOBILE NUMBER ");
        String mobile = userInput.nextLine();
        Patient patient = new Patient(FIRSTName, LASTName, patientMedicalHistory,AgeSettingVariable, GenderSettingVariable, BloodTypeSettingVariable,
                weightSettingVariable, heightSettingVariable,username, EmailSettingVariable,PasswordSettingVariable, mobile);
        return patient;
    };
    public Receptionist receptionistSignUp(){        Scanner userInput = new Scanner(System.in);
        System.out.println("            RECEPTIONIST SIGNUP       ");
        System.out.println("  *******************************");
        //getting signup details from the user

        System.out.println(" ENTER FIRSTNAME ");
        String FIRSTName = userInput.nextLine();
        System.out.println(" ENTER LASTNAME ");
        String LASTName = userInput.nextLine();
        System.out.println(" ENTER USERNAME ");
        String username = userInput.nextLine();

        System.out.println(" ENTER YOUR EMAIL  ");
        String email = userInput.nextLine();
        int validation;;
        String password;
        do {
            validation =1;
            System.out.println(" ENTER YOUR PASSWORD ");
            password = userInput.nextLine();
            System.out.println(" ENTER YOUR PASSWORD AGAIN FOR VERIFICATION");
            String PasswordVerifier = userInput.nextLine();

            if (!password.equals(PasswordVerifier)){
                System.out.println("PASSWORD DO NOT MATCH ! PLEASE TRY AGAIN!");
                validation=0;

            }
        }while(validation==0);
        System.out.println(" ENTER YOUR MOBILE NUMBER ");
        String mobile = userInput.nextLine();
        //int age, String gender
        System.out.println(" ENTER AGE ");
        int age = userInput.nextInt();
        userInput.nextLine();//consume new line

        System.out.println(" ENTER GENDER ");
        String gender = userInput.nextLine();
        Receptionist receptionist = new Receptionist(FIRSTName, LASTName, username, email, password, mobile, age, gender);
        return receptionist;
    }

    public int patientLogin(){
        Scanner input = new Scanner(System.in);
        boolean isLoggedIn=false;
        int index=-1;
        String userInput,passwordInput;

        do {
            System.out.println("          PATIENT LOGIN         ");
            System.out.println("  *******************************");

            System.out.print(" ENTER USERNAME OR EMAIL  : ");
            userInput = input.nextLine();

            System.out.print(" ENTER PASSWORD: ");
            passwordInput = input.nextLine();

            for (Patient patient : patients) {
                if (patient != null && ((patient.getEmail().equals(userInput)
                        || patient.getUsername().equals(userInput)))&&
                        patient.getPassword().equals(passwordInput)) {
                    System.out.println("Login successful! Welcome, " + patient.getFirstName());
                    isLoggedIn = true;
                    index=patients.indexOf(patient);
                   break;

                }
            }
            if(!isLoggedIn) System.out.println("Invalid email or password. Please try again.");
        }while(isLoggedIn==false);
        return index;
    }
    public int doctorLogin(){
        Scanner input = new Scanner(System.in);
        boolean isLoggedIn=false;
        int index=-1;
        String userInput,passwordInput;

        do {
            System.out.println("          DOCTOR LOGIN         ");
            System.out.println("  *******************************");

            System.out.print(" ENTER USERNAME OR EMAIL  : ");
            userInput = input.nextLine();

            System.out.print(" ENTER PASSWORD: ");
            passwordInput = input.nextLine();

            for (Doctor doctor : doctors) {
                if (doctor != null && ((doctor.getEmail().equals(userInput)
                        || doctor.getUsername().equals(userInput)))&&
                        doctor.getPassword().equals(passwordInput)) {
                    System.out.println("Login successful! Welcome, " + doctor.getFirstName());
                    isLoggedIn = true;
                    index=doctors.indexOf(doctor);
                    break;

                }

            }
            if (!isLoggedIn)  System.out.println("Invalid email or password. Please try again.");

        }while(isLoggedIn==false);
        return index;
    }
    public int receptionistLogin(){
        Scanner input = new Scanner(System.in);
        boolean isLoggedIn=false;
        int index=-1;
        String userInput,passwordInput;

        do {
            System.out.println("          RECEPTIONIST LOGIN         ");
            System.out.println("  *******************************");

            System.out.print(" ENTER USERNAME OR EMAIL  : ");
            userInput = input.nextLine();

            System.out.print(" ENTER PASSWORD: ");
            passwordInput = input.nextLine();

            for (Receptionist receptionist : receptionists) {
                if (receptionist != null && ((receptionist.getEmail().equals(userInput)
                        || receptionist.getUsername().equals(userInput)))&&
                        receptionist.getPassword().equals(passwordInput)) {
                    System.out.println("Login successful! Welcome, " + receptionist.getFirstName());
                    isLoggedIn = true;
                    index=receptionists.indexOf(receptionist);

                    break;

                }
            }
            if (!isLoggedIn) System.out.println("Invalid email or password. Please try again.");
        }
        while(isLoggedIn==false);
        return index;
    }
}


//end of the class

