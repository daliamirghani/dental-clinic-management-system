package DCMSpack;

import java.io.*;
import java.util.ArrayList;

public class FileManagement{

    public void saveData(DentalClinic clinic) {
        ObjectOutputStream doctorFileSave = null;
        ObjectOutputStream patientFileSave = null;
        ObjectOutputStream receptionistFileSave = null;

        try {

            File doctorFile = new File("doctors.txt");
            File patientFile = new File("patients.txt");
            File receptionistFile = new File("receptionists.txt");


            if (doctorFile.exists()) {
                doctorFile.delete();
            }
            if (patientFile.exists()) {
                patientFile.delete();
            }
            if (receptionistFile.exists()) {
                receptionistFile.delete();
            }


            doctorFileSave = new ObjectOutputStream(new FileOutputStream(doctorFile));
            patientFileSave = new ObjectOutputStream(new FileOutputStream(patientFile));
            receptionistFileSave = new ObjectOutputStream(new FileOutputStream(receptionistFile));

            // Save Doctors
            for (Doctor doctor : clinic.doctors) {


                doctorFileSave.writeObject(doctor);

            }

            // Save Patients
            for (Patient patient : clinic.patients) {
                patientFileSave.writeObject(patient);

            }

            // Save Receptionists
            for (Receptionist receptionist : clinic.receptionists) {
                receptionistFileSave.writeObject(receptionist);

            }

        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        } finally {
            try {
                if (doctorFileSave != null) {
                    doctorFileSave.close();
                }
                if (patientFileSave != null) {
                    patientFileSave.close();
                }
                if (receptionistFileSave != null) {
                    receptionistFileSave.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing streams: " + e.getMessage());
            }
        }
    }


    public void loadData(DentalClinic clinic) {
        ObjectInputStream doctorFileLoad = null;
        ObjectInputStream patientFileLoad = null;
        ObjectInputStream receptionistFileLoad = null;

        File doctorFile = new File("doctors.txt");
        File patientFile = new File("patients.txt");
        File receptionistFile = new File("receptionists.txt");

        try {
            // Create files if they don't exist
            if (!doctorFile.exists()) {
                doctorFile.createNewFile();
            }
            if (!patientFile.exists()) {
                patientFile.createNewFile();
            }
            if (!receptionistFile.exists()) {
                receptionistFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error creating files: " + e.getMessage());
            return;
        }

        try {
            if (doctorFile.length() > 0) {
                doctorFileLoad = new ObjectInputStream(new FileInputStream(doctorFile));
                while (true) {
                    try {
                        Doctor doctor = (Doctor) doctorFileLoad.readObject();
                        clinic.doctors.add(doctor);
                    } catch (IOException e) {
                        break;
                    }
                }
                doctorFileLoad.close();
            } else {
                System.out.println("Doctor file is empty.");
            }

            if (patientFile.length() > 0) {
                patientFileLoad = new ObjectInputStream(new FileInputStream(patientFile));
                while (true) {
                    try {
                        Patient patient = (Patient) patientFileLoad.readObject();
                        clinic.patients.add(patient);
                    } catch (IOException e) {
                        break;
                    }
                }
                patientFileLoad.close();
            } else {
                System.out.println("Patient file is empty.");
            }


            if (receptionistFile.length() > 0) {
                receptionistFileLoad = new ObjectInputStream(new FileInputStream(receptionistFile));
                while (true) {
                    try {
                        Receptionist receptionist = (Receptionist) receptionistFileLoad.readObject();
                        clinic.receptionists.add(receptionist);
                    } catch (IOException e) {
                        break;
                    }
                }
                receptionistFileLoad.close();
            } else {
                System.out.println("Receptionist file is empty.");
            }


        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading files: " + e.getMessage());
        } finally {
            try {
                // Close the streams
                if (doctorFileLoad != null) {
                    doctorFileLoad.close();
                }
                if (patientFileLoad != null) {
                    patientFileLoad.close();
                }
                if (receptionistFileLoad != null) {
                    receptionistFileLoad.close();
                }
            } catch (IOException exp) {
                System.out.println("Error closing files!");
            }
        }
    }}
