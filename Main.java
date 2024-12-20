package DCMSpack;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        FileManagement fileManager = new FileManagement();
        ManageClinic manager = new ManageClinic();
        fileManager.loadData(manager.OurClinic);
        Scanner scanner = new Scanner(System.in);
        String response;
        do
        {
            menu(manager);
            System.out.print("Do you want to exit program ? (y/n): ");
            response = scanner.nextLine();
        } while (response.equalsIgnoreCase("n")  ||response.equalsIgnoreCase("no"));
        fileManager.saveData(manager.OurClinic);
    }
    public static void menu(ManageClinic manager){
        Scanner userInput=new Scanner(System.in);
        int choice;
        int loggedinIndex;
        System.out.println("**************");
        System.out.println("Welcome to our dental clinic!");
        System.out.println("**************");
        System.out.println("1.Sign up\n2.Log in\n");//3 EXIT
        choice=userInput.nextInt();
        switch(choice){
            case 1:
            { User user = null;
                System.out.println("Sign Up as: 1. Doctor 2. Patient 3. Receptionist");
                int cho= userInput.nextInt();
                switch(cho){
                    case 1:
                    {
                        user = manager.OurClinic.docSignUp();

                        manager.OurClinic.doctors.add((Doctor) user);
                        System.out.println("Added successfully!");
                        return;
                    }
                    case 2:
                    {
                        user = manager.OurClinic.patientSignUp();

                        manager.OurClinic.patients.add((Patient) user);
                        System.out.println("Added successfully!");
                        return;
                    }

                    case 3: {
                        user = manager.OurClinic.receptionistSignUp();
                        manager.OurClinic.receptionists.add((Receptionist) user);
                        System.out.println("Added successfully!");

                        return;
                    }
                }
            };break;
            case 2: {System.out.println("Log in as: 1. Doctor 2. Patient 3. Receptionist");
                int ch= userInput.nextInt();
                switch(ch){
                    case 1:{loggedinIndex= manager.OurClinic.doctorLogin();
                        DoctorMenu(manager,loggedinIndex); }
                    break;
                    case 2:{loggedinIndex=manager.OurClinic.patientLogin();
                        PatientMenu(manager,loggedinIndex);
                    }
                    break;
                    case 3:{
                        loggedinIndex=manager.OurClinic.receptionistLogin();
                        ReceptionistMenu(manager,loggedinIndex);
                    }
                    break;
                }
            }
        }
    }

    public static void DoctorMenu(ManageClinic m ,int loginIndex) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("**************");
            System.out.println("\t\t Hi Doctor What Do You Want To Do?: ");
            System.out.println("**************");
            System.out.println("1) Create prescription for patients ? Press 1 to do so.");
            System.out.println("2) Set and Change availability ? Press 2 to do so.");
            System.out.println("3) Show all appointments for a specific day ? Press 3 to do so.");
            System.out.println("4) Get receptionist contact information ? Press 4 to do so.");
            System.out.println("5) Get patient information ? Press 5 to do so.");
            System.out.println("6) Exit the menu ? Press 6 to do so.");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            while ((choice>6) || (choice<1)){ //iterates if invalid input until user enters a valid input
                System.out.print("Invalid input. Please enter a number between 1 and 6: ");
                scanner.next();
            }

            switch (choice) {
                case 1:
                    m.createPrescription();
                    break;
                case 2:
                    m.OurClinic.doctors.get(loginIndex).ChangeAvailability();
                    break;
                case 3:
                    m.ShowAllMyAvailability(loginIndex);
                    break;
                case 4:
                    m.getReceptionistInfo();
                    break;
                case 5:
                    m.DisplayPatientInfo();  //not ready yet
                    break;
                case 6:
                    return ; //to exit the function

            }

        }while(true);
    }
    public static void PatientMenu(ManageClinic m, int loginIndex) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("**************");
            System.out.println("\t\t Hi Patient, What Do You Want To Do?");
            System.out.println("**************");
            System.out.println("1) Change personal information (email, mobile number, weight, height) - Press 1.");
            System.out.println("2) Reserve an appointment with a specific date and time - Press 2.");
            System.out.println("3) Cancel reservation - Press 3.");
            System.out.println("4) Check prices for appointments - Press 4.");
            System.out.println("5) Search for a doctor by name or mobile number - Press 5.");
            System.out.println("6) Display available appointments for reservation - Press 6.");
            System.out.println("7) Exit the menu - Press 7.");
            System.out.print("Enter your choice: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Please enter a number between 1 and 7: ");
                scanner.next();
            }

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            while (choice < 1 || choice > 7) {
                System.out.print("Invalid choice. Please enter a number between 1 and 7: ");
                choice = scanner.nextInt();
                scanner.nextLine();
            }

            switch (choice) {
                case 1:
                    m.changePatientsInfo(loginIndex);
                    break;
                case 2:
                    m.ReserveAppointment(loginIndex);
                    break;
                case 3:
                    m.cancelReservation(m.OurClinic.patients.get(loginIndex).appointments);
                    break;
                case 4:
                    m.checkPrices(loginIndex);
                    break;
                case 5:
                    m.searchDoctor();
                    break;
                case 6:
                    m.displayAvailableAppointments();;
                    break;
                case 7:
                    return; // Exit the function
            }

        } while (true);
    }
    public static void ReceptionistMenu(ManageClinic m, int loginIndex) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("**************");
            System.out.println("\t\tHi Receptionist, What Do You Want To Do?");
            System.out.println("**************");
            System.out.println("1) Change patient information - Press 1.");
            System.out.println("2) Cancel reservation for a patient - Press 2.");
            System.out.println("3) Reserve appointment for a patient - Press 3.");
            System.out.println("4) Exit the menu - Press 4.");
            System.out.print("Enter your choice: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Please enter a number between 1 and 3: ");
                scanner.next();
            }

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            while (choice < 1 || choice > 4) {
                System.out.print("Invalid choice. Please enter a number between 1 and 3: ");
                choice = scanner.nextInt();
                scanner.nextLine();
            }

            switch (choice) {
                case 1:
                {
                    System.out.println("Enter the patient name:");
                    String patientName = scanner.nextLine();
                    System.out.println("Enter the patient phone number:");
                    String phoneNumber = scanner.nextLine();

                    boolean found = false;
                    for (int i = 0; i < m.OurClinic.patients.size(); i++) {
                        if (patientName.equals(m.OurClinic.patients.get(i).getFirstName()) &&
                                phoneNumber.equals(m.OurClinic.patients.get(i).getMobileNumber())) {
                            m.changePatientsInfo(i);
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("Patient not found. Please check the name and phone number.");
                    }
                }
                break;
                case 2: {
                    System.out.println("Enter the patient name:");
                    String patientName2 = scanner.nextLine();
                    System.out.println("Enter the patient phone number:");
                    String phoneNumber2 = scanner.nextLine();

                    boolean reservationFound = false;
                    for (int i = 0; i < m.OurClinic.patients.size(); i++) {
                        if (patientName2.equals(m.OurClinic.patients.get(i).getFirstName()) &&
                                phoneNumber2.equals(m.OurClinic.patients.get(i).getMobileNumber())) {
                            m.cancelReservation(m.OurClinic.patients.get(i).appointments);
                            reservationFound = true;
                            break;
                        }
                    }

                    if (!reservationFound) {
                        System.out.println("Patient not found. Please check the name and phone number.");
                    }
                }
                break;
                case 3:
                { int PatientIndex=-1;
                    System.out.println("Enter the patient name:");
                    String patientName2 = scanner.nextLine();
                    System.out.println("Enter the patient phone number:");
                    String phoneNumber2 = scanner.nextLine();
                    for (int i = 0; i < m.OurClinic.patients.size(); i++) {
                        if (patientName2.equals(m.OurClinic.patients.get(i).getFirstName()) &&
                                phoneNumber2.equals(m.OurClinic.patients.get(i).getMobileNumber())) {
                            PatientIndex=i;
                            break;
                        }
                    }
                        if (PatientIndex!=-1)
                        {m.ReserveAppointment(PatientIndex);}
                        else
                            System.out.println("Patient not found. Please check the name and phone number.");

                }
                break;
                case 4:
                    return; // Exit the menu
            }

        } while (true);
    }


}

