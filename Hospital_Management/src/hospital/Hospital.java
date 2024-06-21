package hospital;

import admins.Hospital_Admin;

public class Hospital {
    private String hospital_Name;
    private String hospital_Location;
    private int hospital_Admin_Id;
    private int hospital_Id;
    private String hospital_Admin_Name;

    public Hospital(String hospital_Name, String hospital_Location, int hospital_Admin_Id) {
        this.hospital_Name = hospital_Name;
        this.hospital_Location = hospital_Location;
        this.hospital_Admin_Id = hospital_Admin_Id;
    }

    public Hospital(int hospital_Id,String hospital_Name,String hospital_Location, String hospital_Admin_Name){
        this.hospital_Id = hospital_Id;
        this.hospital_Name = hospital_Name;
        this.hospital_Location = hospital_Location;
        this.hospital_Admin_Name = hospital_Admin_Name;
    }

    public String getHospital_Name() { return hospital_Name; }
    public String getHospital_Location() { return hospital_Location; }
    public int getHospital_Admin_Id() { return hospital_Admin_Id; }

    public int getHospital_Id(){
        return hospital_Id;
    }

    public String getHospital_Admin_Name(){
        return hospital_Admin_Name;
    }

    public void displayHospitalInfo() {
        System.out.println("Hospital Name: " + hospital_Name);
        System.out.println("Location: " + hospital_Location);
        //admin.displayUserInfo();
    }
}
