package DCMSpack;

import java.io.Serializable;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.lang.String;
import java.time.format.DateTimeFormatter;

public class Doctor extends User implements Serializable {


    LocalDateTime[] date = new LocalDateTime[30]; //array to save all available dates of the doctor
    boolean[][] availability = new boolean[30][4]; // 2d array of months, each day of month has 4 working hours
    String[] Slots = {"9-11", "11-1", "1-3", "3-5"};
    private String Specialization;


    public void setSpecialization(String Specialization) {
        this.Specialization = Specialization;
    }

    public String getSpecialization() {
        return Specialization;
    }


    public Doctor(String firstName, String lastName, String username,
                  String email, String password, String mobileNumber, String Specialization) {
        super(firstName, lastName, username, email, password, mobileNumber);
        this.Specialization = Specialization;
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 4; j++) {
                availability[i][j] = false; //setting all month as unavailable until the doctor change it to available
            }
        }

    }

    public void ChangeAvailability() {
        Scanner input = new Scanner(System.in);
        System.out.println(" ** Hi doctor you're about to change your working days and hours ! **");
        System.out.println(" How many days your available ?");
        int NumberOfAvailableDays = input.nextInt();


        for (int i = 0; i < NumberOfAvailableDays; i++) {

            System.out.println("Entering your available date number " + "(" + (i + 1) + ")" + " :");
            System.out.println("First enter day : ");
            int day = input.nextInt();
            System.out.println("Second enter month : ");
            int month = input.nextInt();
            date[i] = LocalDateTime.of(2024, month, day, 0, 0); //save date entered as available date the array


            System.out.println("Here is  working hours of day : 1) 9-11  2) 11-1  3) 1-3  4) 3-5 ");
            System.out.println("Choose number of slots you want in this day");
            int NumberOfHours = input.nextInt();
            for (int j = 0; j < NumberOfHours; j++) {
                System.out.println("Enter slot number " + "(" + (j + 1) + ")" + " :");
                int hours = input.nextInt();
                availability[day-1][hours-1] = true; //set day and slot entered as available
            }

            System.out.println("\t Your new available days are added successfully! ");

        }
        System.out.println("\nHere are your available dates in this month and slots of each day : ");
        for (int j = 0; j < NumberOfAvailableDays; j++) {
            DateTimeFormatter DateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd"); //display dates in format of yyyy-MM-dd
            int day = date[j].getDayOfMonth();
            String dayOfWeek = date[j].getDayOfWeek().toString();
            StringBuilder Allslots = new StringBuilder(" ");

            for (int i = 0; i < 4; i++) {

                if (availability[day - 1][i]) {
                    Allslots.append(Slots[i]).append(" , "); //join all available slots for a doctor in one String

                }

            }

            if (!Allslots.isEmpty()) {  // Remove last comma and space
                Allslots.setLength(Allslots.length() - 2);
            }

            System.out.println(date[j].format(DateFormat) + " - " + dayOfWeek + " : " + Allslots); //display all new dates and their slots
        }
    }
}






