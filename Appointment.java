package DCMSpack;



//package com.clinic.classes;
import java.io.Serializable;
import java.time.LocalDateTime;
public class Appointment implements Serializable {
    Service serviceType;
    LocalDateTime date ;
    String Slot;
    int doctorIndex;
    //setting default values for the default constructor

    public Appointment() {
        //serviceType = null;
        Slot=" ";
        date = LocalDateTime.of(2024,12,30,6,26) ;
    }

    public Appointment(LocalDateTime date, String slot, String servicename, int price) {
        this.date = date;
        this.Slot = slot;
        this.serviceType= new Service();
        serviceType.setName(servicename);
        serviceType.setPrice(price);
    }


    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date=date;
    }



}





