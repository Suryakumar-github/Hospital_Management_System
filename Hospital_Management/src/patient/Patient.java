package patient;

import user.User;

public class Patient extends User {
    private String bloodGroup;
    private int age;
    private String gender;

    public Patient(String name, String bloodGroup, int age, String gender, long mobileNumber, String email_Id, String userName, String password) {
        super(name, userName, password, mobileNumber,email_Id);
        this.bloodGroup = bloodGroup;
        this.age = age;
        this.gender = gender;
    }

    public String getBloodGroup() { return bloodGroup; }
    public int getAge() { return age; }
    public String getGender() { return gender; }

   
    public void displayUserInfo() {
        System.out.println("Patient Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Blood Group: " + bloodGroup);
        System.out.println("Gender: " + gender);
        System.out.println("Mobile Number: " + mobileNumber);
    }
}
