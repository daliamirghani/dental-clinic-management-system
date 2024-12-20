package DCMSpack;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;


public class ManageClinic  implements Serializable {
    public DentalClinic OurClinic = new DentalClinic("Our Clinic", "Sudan");

    public void ReserveAppointment(int loginIndex) {
Scanner input = new Scanner(System.in);
        LocalDateTime dateEntered ;
        boolean is_set = false;
        String answer="y";
        int slotnumber;

        outerLoop:
        do {
            System.out.println("\t****** YOU'RE ABOUT TO RESERVE AN APPOINTMENT ******");
            OurClinic.display();               //display services
            System.out.println("Enter service number you want ");
            int servicenumber = input.nextInt();
            input.nextLine();
            String servicename = OurClinic.Services[servicenumber - 1].getName(); //set name of service entered into appointment service
            int serviceprice = OurClinic.Services[servicenumber - 1].getPrice(); //name of price


            System.out.println("Enter a date of appointment you want in format of dd-mm : ");
            //System.out.println("first enter day : ");
            String inputdate = input.nextLine();
            String [] date=inputdate.split("-");
            int day=Integer.parseInt(date[0]);
            int month=Integer.parseInt(date[1]);
            dateEntered = LocalDateTime.of(2024, month,day, 0, 0);


            System.out.println("Enter appointment slot you want ");
            String inputslot ;

            do {
                inputslot = input.nextLine();
                slotnumber = (inputslot.equalsIgnoreCase("9-11")) ? 0 :
                        (inputslot.equalsIgnoreCase("11-1")) ? 1 :
                                (inputslot.equalsIgnoreCase("1-3")) ? 2 :
                                        (inputslot.equalsIgnoreCase("3-5"))? 3 : -1;

                if (slotnumber != -1) //check if slot entered is valid
                {
                    break;
                }

                System.out.println("This slot is unavailable Enter another one : ");
            }
            while (true);


            for (int i = 0; i < OurClinic.doctors.size(); i++) {
                innerloop:
                for (int j = 0; j < 30; j++) {
                    if ((OurClinic.doctors.get(i).date[j]==null)) {
                        break innerloop;
                    }

                    else if ((OurClinic.doctors.get(i).getSpecialization().equalsIgnoreCase(servicename))
                            && (OurClinic.doctors.get(i).date[j].toLocalDate().equals(dateEntered.toLocalDate()))
                            &&(OurClinic.doctors.get(i).availability[day - 1][slotnumber]))
                    {

                        Appointment p= new Appointment(dateEntered, inputslot,servicename,serviceprice);
                        OurClinic.patients.get(loginIndex).appointments.add(p); //add a vew appointment with date time service entered

                        int appointmentIndex = OurClinic.patients.get(loginIndex).appointments.indexOf(p);
                        p.doctorIndex=i;
                        OurClinic.doctors.get(i).availability[day - 1][slotnumber] = false; //set doctor as unavailable in this date and time anymore

                        System.out.println("     \t   RESERVATION CONFIRMED SUCCESSFULLY!     ");
                        System.out.println("APPOINTMENT DETAILS : ");
                        DateTimeFormatter DateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        System.out.println(     //display appointment details
                                "Doctor name : "+OurClinic.doctors.get(i).getFirstName()+ " "+ OurClinic.doctors.get(i).getLastName() +
                                        "\nService : "+OurClinic.patients.get(loginIndex).appointments.get(appointmentIndex).serviceType.getName()+
                                        "\nService price : "+OurClinic.patients.get(loginIndex).appointments.get(appointmentIndex).serviceType.getPrice()+
                                        "\nDate : "+OurClinic.patients.get(loginIndex).appointments.get(appointmentIndex).date.format(DateFormat)+
                                        " - " + dateEntered.getDayOfWeek().toString() +
                                        "\nTime : "+OurClinic.patients.get(loginIndex).appointments.get(appointmentIndex).Slot
                        );
                        is_set=true;
                        break outerLoop;

                    }

                }

            }


            System.out.println("NO AVAILABLE DOCTOR IN THIS DATE AND MONTH !");
            System.out.println("Do you want to search for a new available appointment ? press 'y' or 'n'  ");
            answer = input.nextLine();

        } while (!is_set && (answer.equalsIgnoreCase("y")
                || answer.equalsIgnoreCase("Yes")));


    }

    public void searchDoctor() {
        String name;
        String number;
        int index = -1;
        Scanner scanner = new Scanner(System.in);
        boolean done = false;
        boolean found = false;

        do {
            System.out.println("Do you want to search with name (1) or phone number (2)? \nPlease enter number");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1: {
                    System.out.println("Enter first name of doctor");
                    name = scanner.nextLine();
                    for (int i = 0; i < OurClinic.doctors.size(); i++) {
                        if (name.equals(OurClinic.doctors.get(i).getFirstName())) {
                            index = i;
                            found = true;
                            break;
                        }
                    }

                    done = true;
                    break;
                }
                case 2: {
                    System.out.println("Enter phone number of doctor");
                    number = scanner.nextLine();
                    for (int i = 0; i < OurClinic.doctors.size(); i++) {
                        if (number.equals(OurClinic.doctors.get(i).getMobileNumber())) {
                            index = i;
                            found = true;
                            break;
                        }
                    }

                    done = true;
                    break;
                }
                default: {
                    System.out.println("Invalid input! Try again");
                }
            }
        } while (!done);

        if (found) {
            System.out.println("Name: " + OurClinic.doctors.get(index).getFirstName() + " " + OurClinic.doctors.get(index).getLastName());
            System.out.println("Email: " + OurClinic.doctors.get(index).getEmail());
            System.out.println("Phone Number: " + OurClinic.doctors.get(index).getMobileNumber());
            System.out.println("Specialization: " + OurClinic.doctors.get(index).getSpecialization());
        } else {
            System.out.println("Doctor not found");
        }
    }

    public void cancelReservation(ArrayList<Appointment> appt) {
        Scanner scanner = new Scanner(System.in); //in logged in menu Patients.get(loggedInIndex).appointmentS)
        String dateInput;
        String enteredSlot;
        boolean done = false;
        boolean found = false;

        System.out.println("Please enter date of appointment you want to cancel (in format:DD-MM)");
        dateInput = scanner.nextLine();
       String []date= dateInput.split("-");
       int day = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        LocalDateTime enteredDate= LocalDateTime.of(2024, month, day, 0,0);
        System.out.println("Please enter time of appointment you want to cancel(in format:H-H)");
        enteredSlot = scanner.nextLine();

        for (int i = 0; i < appt.size(); i++) {
            if ((enteredDate.equals(appt.get(i).date)) && (enteredSlot.equals(appt.get(i).Slot))) {
             found= true;

                int slotnumber = (enteredSlot.equalsIgnoreCase("9-11")) ? 0 :
                        (enteredSlot.equalsIgnoreCase("11-1")) ? 1 :
                                (enteredSlot.equalsIgnoreCase("1-3")) ? 2 :
                                        (enteredSlot.equalsIgnoreCase("3-5"))? 3 : -1;
                 OurClinic.doctors.get(appt.get(i).doctorIndex).availability[day - 1][slotnumber] = true;// return availability
                 appt.remove(i);
                 done = true;
                 break;
            }
        }

        if (done) {
            System.out.println("Deleted successfully!");

        }
        else if (!found){
            System.out.println("An appointment with this date doesn't exist");
        }
        else
        System.out.println("There was an error deleting your appointment. Please try again.");

    }

    public void displayAvailableAppointments() {
        Scanner input = new Scanner(System.in);
        System.out.println("\t****** DISPLAYING AVAILABLE APPOINTMENTS ******");
        String answer="n";
        int choice1;
        int choice;
        loop1:
        do {

            for (int i = 0; i < OurClinic.doctors.size(); i++) { // Display all doctors with specializations
                System.out.println((i + 1) + ") " + OurClinic.doctors.get(i).getFirstName() +
                        " - " +OurClinic.doctors.get(i).getSpecialization());
            }



            System.out.println("Enter the number of the doctor to view their available hours and dates :");
            choice1 = input.nextInt();
            choice=choice1 -1;
            System.out.println("\nAvailable hours and dates for Dr." + OurClinic.doctors.get(choice).getFirstName() +" - "+  OurClinic.doctors.get(choice).getSpecialization());


            boolean hasAvailableSlots = false;
            DateTimeFormatter DateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            for (int j = 0; j < OurClinic.doctors.get(choice).date.length; j++) {
                if (OurClinic.doctors.get(choice).date[j] != null) {
                    for (int k = 0; k < 4; k++) {
                        int day1 = OurClinic.doctors.get(choice).date[j].getDayOfMonth();
                        if (OurClinic.doctors.get(choice).availability[day1-1][k]==true) {

                            System.out.println("Date: " + OurClinic.doctors.get(choice).date[j].format(DateFormat) +
                                    " | Slots: " + OurClinic.doctors.get(choice).Slots[k]);
                            hasAvailableSlots = true;
                        }

                    }
                }
            }


            if (!hasAvailableSlots) {
                System.out.println("No available appointments for this doctor.");
            }

            System.out.println("Do you want to check doctors available date and hours again? press 'y' or 'n'");
            answer = input.nextLine();
            input.nextLine();

        } while (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("Yes"));

    }

    public void createPrescription() {
        boolean check = false;

        Scanner input = new Scanner(System.in);
        System.out.println("enter the name of patient: ");
        String Patient_name = input.nextLine();

        for (int i = 0; i < OurClinic.patients.size(); i++) {
            if (Patient_name.equalsIgnoreCase(OurClinic.patients.get(i).getFirstName()))
            {
                System.out.println("Write prescription for patient" + OurClinic.patients.get(i).getFirstName());
                String doctor_prescription = input.nextLine();
                OurClinic.patients.get(i).setPrescription(doctor_prescription);
                check = true;
                break;
            }


        }

        if (!check) {
            System.out.println("patient not found");
        }
    }

    public void getReceptionistInfo() {
        Scanner input = new Scanner(System.in);
        System.out.println("enter the name of receptionist: ");
        String name = input.nextLine();
        System.out.println("enter the email of receptionist: ");
        String email = input.nextLine();
        boolean check = false;
        for (int i = 0; i < OurClinic.receptionists.size(); i++) {
            if (name.equals(OurClinic.receptionists.get(i).getFirstName()) && (email.equals(OurClinic.receptionists.get(i).getEmail()))) {
                System.out.println("Name: " + OurClinic.receptionists.get(i).getFirstName() + " " + OurClinic.receptionists.get(i).getLastName());
                System.out.println("Email: " + OurClinic.receptionists.get(i).getEmail());
                System.out.println("Phone Number: " + OurClinic.receptionists.get(i).getMobileNumber());
                check = true;
            }
        }
        if (!check) {
            System.out.println("No receptionist found");

        }

    }

    public void ShowAllMyAvailability(int loginIndex) {
        String answer = "n";
        StringBuilder Allslots = new StringBuilder();
        String dayOfWeek;
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("Doctor " + OurClinic.doctors.get(loginIndex).getFirstName() + " "
                    + OurClinic.doctors.get(loginIndex).getLastName() +
                    "Enter a day to show all your appointment , in format of 'dd-mm' : ");
            String inputdate = input.nextLine();

            String[] date = inputdate.split("-"); //split the entered date with sign - then save it in array of strings
            int day = Integer.parseInt(date[0]); //convert the day into integer and then store it
            int month = Integer.parseInt(date[1]);
            LocalDateTime dateEntered = LocalDateTime.of(2024, month, day, 0, 0); //save the day and months entered as a date
            //DateTimeFormatter DateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            boolean found = false;


            loop:
            for (int j = 0; j < OurClinic.doctors.get(loginIndex).date.length; j++) {
                if ((OurClinic.doctors.get(loginIndex).date[j] != null) &&
                        (OurClinic.doctors.get(loginIndex).date[j].equals(dateEntered)))
                {
                    DateTimeFormatter DateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd"); //display dates in format of yyyy-MM-dd
                    int day1 = OurClinic.doctors.get(loginIndex).date[j].getDayOfMonth();
                    dayOfWeek = OurClinic.doctors.get(loginIndex).date[j].getDayOfWeek().toString();
                    Allslots = new StringBuilder(" ");

                    for (int i = 0; i < 4; i++) {

                        if (OurClinic.doctors.get(loginIndex).availability[day1 - 1][i]) {
                            Allslots.append(OurClinic.doctors.get(loginIndex).Slots[i]).append(" , "); //join all available slots for a doctor in one String

                        }

                    }
                    dayOfWeek = OurClinic.doctors.get(loginIndex).date[j].getDayOfWeek().toString(); //convert the date into day of week
                    System.out.println(" Your appointments in  "+OurClinic.doctors.get(loginIndex).date[j].format(DateFormat) + " - " +dayOfWeek + " : \n "
                            + Allslots);
                    found =true;
                    break loop; // break the loop once you displayed available slots of the entered day if found

                }

            }
            if ((!Allslots.isEmpty())){  // Remove last comma and space
                Allslots.setLength(Allslots.length() - 2);
            }

            if (!found) {
                System.out.println("No date similar to the entered date");
            }
            System.out.println("Do you want to continue ? press 'y' or 'n' ");
            answer = input.nextLine();
            input.nextLine();

        } while (answer.equalsIgnoreCase("y")  ||answer.equalsIgnoreCase("Yes"));

    }

    public void DisplayPatientInfo(){

        Scanner scanner = new Scanner(System.in);
        int validation;
        do {
            validation = 0;
            System.out.println("ENTER PATIENT'S FIRST NAME PLEASE!");
            String PatientFirstName = scanner.nextLine().trim().toLowerCase();
            System.out.println("ENTER PATIENT'S LAST NAME PLEASE!");
            String PatientLastName = scanner.nextLine().trim().toLowerCase();

            boolean patientFound = false;

            for (Patient patient : OurClinic.patients) {
                if (patient.getFirstName().equals(PatientFirstName) && patient.getLastName().equals(PatientLastName)) {
                    System.out.println("PATIENT: " + patient.getFirstName() + " " + patient.getLastName() + "'S INFO :");
                    System.out.println("Age: " + patient.getAge());
                    System.out.println("Gender: " + patient.getGender());
                    System.out.println("Blood Type: " + patient.getBloodType());
                    System.out.println("Weight: " + patient.getWeight());
                    System.out.println("Height: " + patient.getHeight());
                    System.out.println("MEDICAL HISTORY: " + patient.getPatientHistory());
                    System.out.println("Email: " + patient.getEmail());
                    System.out.println("Mobile Number: " + patient.getMobileNumber());
                    System.out.println("Prescription: " + patient.getPrescription());
                    patientFound = true;
                    break;
                }
            }

            if (!patientFound) {
                System.out.println("NON-EXISTENT PATIENT, LET'S TRY AGAIN");
                validation = 1;
            }

            if (validation == 1 ) {
                System.out.println("Do you want to try again? (y/n)");
                String choice = scanner.nextLine().trim().toLowerCase();
                if (!choice.equals("y")) {
                    break;
                }
            }
        } while (validation == 1);
    }

    public void changePatientsInfo(int patientIndex){

        Scanner sc=new Scanner(System.in);
        int validation;
        do {
            validation=1;
            System.out.println(" WHAT INFO  WOULD YOU LIKE TO CHANGE(press a number please )");
            System.out.println("1- EMAIL");
            System.out.println("2- MOBILE NUMBER");
            System.out.println("3- HEIGHT");
            System.out.println("4- WEIGHT");
            System.out.println("5- EXIT ");
            int choice= sc.nextInt();
            sc.nextLine();//consume new line
            switch (choice){
                case 1: {
                    System.out.println("enter EMAIL");
                    String NewEmail=sc.nextLine();
                    OurClinic.patients.get(patientIndex).setEmail(NewEmail);
                    break;
                }
                case 2:{
                    System.out.println("enter MOBILE NUMBER");
                    String NewMobileNumber=sc.nextLine();
                    OurClinic.patients.get(patientIndex).setMobileNumber( NewMobileNumber);
                    break;
                }
                case 3:{
                    System.out.println("enter HEIGHT ");
                    float NewHeight  =sc.nextFloat();
                    sc.nextLine();//consume new line
                    OurClinic.patients.get(patientIndex).setHeight(NewHeight );
                    break;
                }
                case 4:{
                    System.out.println("enter the new WEIGHT");
                    float NewWeight =sc.nextFloat();
                    sc.nextLine();//consume new line
                    OurClinic.patients.get(patientIndex).setWeight(NewWeight);
                    break;
                }
                case 5:{
                    return;
                }
                default:{
                    System.out.println("YOUR CHOICE "+choice+" IS NOT AVAILABLE : PLEASE TRY AGAIN");
                    validation=0;
                }
            }

        }while (validation==0);

    }

    public  void checkPrices(int indexOfPatient){
        System.out.println("Service name\tPrice");
        for(int i=0;i<OurClinic.patients.size();i++){
            if(i==indexOfPatient)
                OurClinic.patients.get(indexOfPatient).displayAllAppointment();
        }
    }



}
