package DCMSpack;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public  class Receptionist extends User implements Serializable {
    private int age;
    private String gender;

    public void setAge(int age) {
        this.age = age;

    }

    public int getAge() {
        return age;

    }

    public void setGender(String gender) {
        this.gender = gender;

    }

    public String getGender() {
        return gender;

    }

    public Receptionist(String first_Name, String last_Name, String username, String email, String password, String mobile_Number, int age, String gender) {
        super(first_Name, last_Name, username, email, password, mobile_Number);
        this.age = age;
        this.gender = gender;
    }
}