package appointments;


import doctor.Doctor;
import patient.Patient;

import java.time.LocalDateTime;

public class Appointments {
    private int doctor_Id;
    private int patient_Id;
    private LocalDateTime dateAndTime;
    private String status;

    public Appointments(int doctor_Id, int patient_Id, LocalDateTime dateAndTime, String status) {
        this.doctor_Id = doctor_Id;
        this.patient_Id = patient_Id;
        this.dateAndTime = dateAndTime;
        this.status = status;
    }

    public int getDoctor_Id() {
        return doctor_Id;
    }

    public int getPatient_Id() {
        return patient_Id;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public String getStatus() {
        return status;
    }
}
