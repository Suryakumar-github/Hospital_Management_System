package doctor;

import user.User;
import java.time.LocalTime;

public class Doctor extends User {
    int doctor_Id;
    private String specialisation;
    private int hospital_Id;
    private String status;
    private LocalTime shiftStart;
    private LocalTime shiftEnd;

   
    public Doctor(String name, String specialisation, int hospital_Id, String userName, String password, long mobileNumber, String email_Id, String status, LocalTime shiftStart, LocalTime shiftEnd) {
        super(name, userName, password, mobileNumber, email_Id);
        this.specialisation = specialisation;
        this.hospital_Id = hospital_Id;
        this.status = status;
        this.shiftStart = shiftStart;
        this.shiftEnd = shiftEnd;
    }

    
    public Doctor(int doctor_Id, String doctor_Name, String specialisation, String status, LocalTime shiftStart, LocalTime shiftEnd) {
        super(doctor_Name, "", "", 0, "");
        this.doctor_Id = doctor_Id;
        this.specialisation = specialisation;
        this.status = status;
        this.shiftStart = shiftStart;
        this.shiftEnd = shiftEnd;
    }

    public String getSpecialisation() { return specialisation; }
    public int getHospital_Id() { return hospital_Id; }
    public String getStatus() { return status; }
    public LocalTime getShiftStart() { return shiftStart; }
    public LocalTime getShiftEnd() { return shiftEnd; }
    public int getDoctor_Id() { return doctor_Id; }
    
    public void displayUserInfo() {
        System.out.println("Doctor Name: " + name);
        System.out.println("Specialisation: " + specialisation);
        System.out.println("Hospital: " + hospital_Id);
        System.out.println("Mobile Number: " + mobileNumber);
        System.out.println("Shift: " + shiftStart + " - " + shiftEnd);
    }
}

