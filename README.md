# dental-clinic
# Dental Clinic Management System

## Overview
A dental clinic management system team project made with Java for an OOP course in FCIS 2027. Our main focus was to implement Object-Oriented Programming concepts to manage a dental clinic. The system supports doctors, patients, and receptionists, providing distinct functionalities for each role.

## Classes

1. **User** *(Abstract)*  
   Base class with common attributes like name, email, and phone number.

2. **Doctor** *(Extends User)*  
   - **Extra Attributes**: Specialization, available days/hours.  
   - **Functions**: Manage availability, create prescriptions, view appointments, access patient/receptionist info.

3. **Patient** *(Extends User)*  
   - **Extra Attributes**: Height, weight, history, gender, blood type.  
   - **Functions**: Reserve/cancel appointments, check prices, search for doctors, update info.

4. **Receptionist** *(Extends User)*  
   - **Extra Attributes**: Age, gender.  
   - **Functions**: Manage patient appointments and info.

5. **Appointment**  
   - **Attributes**: Date, service type, time slot, and assigned doctor.

6. **Service**  
   - **Attributes**: Service details (e.g., braces, RCT, filling, whitening) and prices.

7. **DentalClinic**  
   - **Attributes**: Clinic details, user lists, services.  
   - **Functions**: Login and signup functionality.

8. **ManageClinic**  
   - **Functions**: Handles transactions and synchronizes changes across all users.

9. **FileManagement**  
   - **Functions**: Manages saving/loading user data via text files.

10. **Main**  
    - **Functions**: Main menu and includes menus for doctors, patients, and receptionists.

## User Functionalities

### Doctor
1. Create prescriptions for patients.  
2. Manage availability.  
3. View appointments by date.  
4. Display receptionist contact info.  
5. Access patient information.

### Patient
1. Update personal info.  
2. Reserve/cancel appointments.  
3. Check appointment prices.  
4. Search for doctors.  
5. View available appointments.

### Receptionist
1. Update patient info.  
2. Reserve/cancel patient appointments.

## Data Persistence
User data is saved to and loaded from text files using Object Input/Output Streams.

## How to Run
1. Compile the project using a Java IDE or command line.  
2. Run the `Main` class.  
3. Enjoy!

## Credits
Profound thanks to our course doctor Dr. Mohamed Mabrouk and my teammates!
Contributors: Dalia Ahmed, Sondos Khalid, Maram Salaheldin, Zainab Tarig, and Leena Yaqoub.

