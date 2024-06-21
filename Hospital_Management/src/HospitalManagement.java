import connector.Connector;
import admins.Admin;
import admins.Hospital_Admin;
import doctor.Doctor;
import patient.Patient;
import appointments.Appointments;
import hospital.Hospital;
import validation.Validation;


import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.sql.SQLException;


public class HospitalManagement {

    static Scanner sc = new Scanner(System.in);
    static Connector connector = new Connector();

    public static void main(String[] args) {
        while (true) {
            displayMainMenu();

            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                sc.next();
            }

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                	if(isValidAdmin()){
             			adminMenu();
             		}
                    break;
                case 2:
                    System.out.println("Enter Hospital  Admin User_Name ");
    		    String user_Name = sc.next();
    	
    		   System.out.println("Enter Hospital Admin Password ");
    		   String password = sc.next();
    		   
                    if(isValidHospitalAdmin(user_Name,password)){
                    	 hospitalAdminMenu(user_Name,password);
                    }
                   
                    break;
                case 3:
                    patientLogin();
                    
                    break;
                    
                 case 4:
                    if(isValidDoctor()){
                    	doctorMenu();
                    }
                    
                    break;
                case 5:
                    exitMenu();
                    break;
                default:
                    System.out.println("Invalid choice, Please Select Valid choice: " + choice);
            }
        }
    }
	
    public static boolean isValidAdmin(){
    	System.out.println("Enter Admin User_Name ");
    	String user_Name = sc.next();
    	
    	System.out.println("Enter Admin Password ");
    	String password = sc.next();
    	
    	 if(connector.validateAdmin(user_Name,password)){
    	 	System.out.println("Wecome :"+user_Name);
    	 	return true;
    	 }
    	 	System.out.println("Invalid Login Credentials User_Name (or) Password");
    	 	return false;
    }
    
    public static boolean isValidHospitalAdmin(String user_Name, String password){
    	
    	
    	 if(connector.validateHospitalAdmin(user_Name,password)){
    	 	System.out.println("Wecome :"+user_Name);
    	 	return true;
    	 }
    	 	System.out.println("Invalid Login Credentials User_Name (or) Password");
    	 	return false;
    }
    
     public static void patientLogin(){
     System.out.println("--------------------------------------------");
        System.out.println("| Option |         Login Menu               |");
        System.out.println("|--------+---------------------------------|");
        System.out.println("|   1    |Login                            |");
        System.out.println("|   2    |SignUp                 |");
        System.out.println("|   3    |Back to Main Menu                   |");
    	System.out.println("--------------------------------------------");
    	 
    	 System.out.println("Enter your choice:");
    	 
    	 int choice = sc.nextInt();
    	 
    	 switch(choice){
    	 	case 1:
    	 		System.out.println("Enter Patient User_Name ");
    			String user_Name = sc.next();
    	
    			System.out.println("Enter Patient Password ");
    			String password = sc.next();
    			
    	 		if(isValidPatient(user_Name,password)){
    	 			patientMenu(user_Name,password);
    	 		}
    	 		break;
    	 		
    	 	case 2 :
    	 		addPatient();
    	 		break;
    	 	case 3 :
    	 		return;
    	 		
    	 	default :
    	 		 System.out.println("Invalid choice, Please Select Valid choice: " + choice);
    	 }
    	
    }
    
    public static boolean isValidPatient(String user_Name,String password){
   
    	 if(connector.validatePatient(user_Name,password)){
    	 	System.out.println("Wecome :"+user_Name);
    	 	return true;
    	 }
    	 	System.out.println("Invalid Login Credentials User_Name (or) Password");
    	 	return false;
    	 		
    }
    
    public static boolean isValidDoctor(){
     
    	System.out.println("Enter Doctor User_Name ");
    	String user_Name = sc.next();
    	
    	System.out.println("Enter Doctor Password ");
    	String password = sc.next();
    	
    	 if(connector.validateDoctor(user_Name,password)){
    	 	System.out.println("Wecome :"+user_Name);
    	 	return true;
    	 }
    	 	System.out.println("Invalid Login Credentials User_Name (or) Password");
    	 	return false;
    }
    
    public static void displayMainMenu() {
        System.out.println("--------------------------------------------");
        System.out.println("| Option |         Main Menu               |");
        System.out.println("|--------+---------------------------------|");
        System.out.println("|   1    |Admin                            |");
        System.out.println("|   2    |Hospital Admin                   |");
        System.out.println("|   3    |Patient                          |");
        System.out.println("|   4    |Doctor                          |");
        System.out.println("|   5    |Exit                               |");
        System.out.println("--------------------------------------------");
        System.out.println("Enter your choice:");
    }

    public static void adminMenu() {
        while (true) {
            System.out.println("--------------------------------------------");
            System.out.println("| Option |         Admin Menu              |");
            System.out.println("|--------+---------------------------------|");
            System.out.println("|   1    |Add Admin                        |");
            System.out.println("|   2    |Add Hospital                     |");
            System.out.println("|   3    |Show All Hospital                     |");
            System.out.println("|   4    |Back to Main Menu                |");
            System.out.println("--------------------------------------------");
            System.out.println("Enter your choice:");

            int adminChoice = sc.nextInt();

            switch (adminChoice) {
                case 1:
                    addAdmin();
                    break;
                case 2:
                    addHospital();
                    break;
                case 3:
                    showAllHospitals();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice, Please Select Valid choice: " + adminChoice);
            }
        }
    }
    
     public static void doctorMenu() {
        while (true) {
            System.out.println("--------------------------------------------");
            System.out.println("| Option |         Doctor Menu              |");
            System.out.println("|--------+---------------------------------|");
            System.out.println("|   1    |See Appointments                        |");
            System.out.println("|   2    |Back to Main Menu                |");
            System.out.println("--------------------------------------------");
            System.out.println("Enter your choice:");

            int doctorChoice = sc.nextInt();

            switch (doctorChoice) {
                case 1:
                	System.out.println("Enter the Doctor Id");
                	int doctor_Id = sc.nextInt();
                    connector.showDoctors_Appointments(doctor_Id);
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Invalid choice, Please Select Valid choice: " + doctorChoice);
            }
        }
    }

    public static void hospitalAdminMenu(String user_Name, String password) {
        while (true) {
            System.out.println("--------------------------------------------");
            System.out.println("| Option |    Hospital Admin Menu          |");
            System.out.println("|--------+---------------------------------|");
            System.out.println("|   1    |Add Doctor                       |");
            System.out.println("|   2    |Show All Appointments              |");
            System.out.println("|   3    |Show All Doctors              |");
            System.out.println("|   4    |Back to Main Menu                |");
            System.out.println("--------------------------------------------");
            System.out.println("Enter your choice:");

            int hospitalAdminChoice = sc.nextInt();

            switch (hospitalAdminChoice) {
                case 1:
                    addDoctor();
                    break;
                case 2:
                    showAppointments(user_Name,password);
                    break;
                case 3:
                    showAllDoctors();
                    break;
                case 4:
                	return;
                    
                default:
                    System.out.println("Invalid choice, Please Select Valid choice: " + hospitalAdminChoice);
            }
        }
    }

    public static void patientMenu(String user_Name,String password) {
        while (true) {
            System.out.println("--------------------------------------------");
            System.out.println("| Option |        Patient Menu             |");
            System.out.println("|--------+---------------------------------|");
            System.out.println("|   1    |Book Appointment                 |");
            System.out.println("|   2    |Cancel Appointment               |");
            System.out.println("|   3    |Show Patient Appointments               |");
            System.out.println("|   4    |Back to Main Menu                |");
            System.out.println("--------------------------------------------");
            System.out.println("Enter your choice:");

            int patientChoice = sc.nextInt();

            switch (patientChoice) {
                case 1:
                    bookAppointment();
                    break;
                    
                case 2:
                    cancelAppointment(user_Name);
                    break;
                case 3:
                    Show_Patient_Appointments(user_Name);
                    break;
                    
                case 4:
                    return;
                    
                default:
                    System.out.println("Invalid choice, Please Select Valid choice: " + patientChoice);
            }
        }
    }
    

   
    	public static void showAllHospitals(){
        List<Hospital> hospitals = connector.getAllHospitals();
        System.out.println("...Hospitals...");
        System.out.println("............................................................................");
         for(Hospital hospital : hospitals){
            System.out.println("............................................................................");
            System.out.println("Hospital_Id : "+hospital.getHospital_Id());
            System.out.println("Hospital_Name : "+hospital.getHospital_Name());
            System.out.println("Hospital_Location : "+hospital.getHospital_Location());
            System.out.println("Hospital_Admin_Name : "+hospital.getHospital_Admin_Name());
            
            System.out.println("............................................................................");
          }
    	}

    public static void showAppointments(String user_Name, String password){
    	System.out.println("Enter the Hospital Admin Id");
    	int admin_id = sc.nextInt();
    	if(connector.validateHospitalAdmin(user_Name,password,admin_id)){
    		connector.displayAllAppointments(admin_id);
    	}else{
    		System.out.println("Invalid Admin Id");
    		return;
    	}
    	
    }
    public static void Show_Patient_Appointments(String user_Name){
    	System.out.println("Enter patient Id");
    	int patient_id = sc.nextInt();
    	
    	
    	connector.showPatients_Appointments(patient_id);
    } 

    public static void exitMenu() {
        System.out.println("Are you sure you want to exit? (yes/no)");
        String choice = sc.next();
        if (choice.equalsIgnoreCase("yes")) {
            System.exit(0);
        }
    }
    
    public static void showAllDoctors(){
    
    	System.out.println("Enter the Hospital Id");
    	int hospital_id = sc.nextInt();
    	
    	try{
    	List<Doctor> doctors = connector.getAllDoctors(hospital_id);
	System.out.println("............Doctors.............");
	for(Doctor doctor : doctors){
	    System.out.println("............................................................................");
            System.out.println("Doctor_Id : "+  doctor.getDoctor_Id());
            System.out.println("Doctor_Name : "+doctor.getName());
            System.out.println("Specialisation : "+doctor.getSpecialisation());
            System.out.println("Status : "+doctor.getStatus());
            System.out.println("Shift_Start : "+doctor.getShiftStart());
            System.out.println("Shift_End : "+doctor.getShiftEnd());
            
            System.out.println("............................................................................");
        }
    }catch(SQLException e){
    	System.out.println(e);
    }
   }
		
	
    public static void addAdmin() {
    
        System.out.println("Enter admin name:");
        String name = sc.next();
        if(!Validation.validateName(name)){
        	System.out.println("Enter the name only in alphabets");
        	return;
        }
        System.out.println("Enter admin username:");
        String userName = sc.next();
        if(!Validation.validateUsername(userName)){
        	System.out.println("Enter the Valid User Name");
        	return;
        }
        System.out.println("Enter admin password:");
        String password = sc.next();
        if(!Validation.validatePassword(password)){
        	System.out.println("Enter the Valid Password");
        	return;
        }
        System.out.println("Enter the admin mobile_No");
        long mobile_No = sc.nextLong();
        if(!Validation.validateMobile(mobile_No+"")){
        	System.out.println("Enter mobile_no that contain 10 numbers");
        	return;
        }
        System.out.println("Enter the admin email_Id");
        String email_Id = sc.next();
        if(!Validation.validateEmail(email_Id)){
        	System.out.println("Enter the Valid Email_id");
        	return;
        }

        Admin admin = new Admin(name, userName, password,mobile_No,email_Id);
        if(connector.addAdmin(admin)){
        	System.out.println("Admin added successfully.");
        }else{
        	System.out.println("Something Went Wrong.");
        }
    }

    public static void addHospital() {
    	
    	addAdmin();
        System.out.println("Enter hospital name:");
        String hospitalName = sc.next();
        if(!Validation.validateName(hospitalName)){
        	System.out.println("Enter the name only in alphabets");
        	return;
        }
        System.out.println("Enter hospital location:");
        String location = sc.next();
        
        System.out.println("Enter the Hospital Admin Id:");
        int hospitalAdminId = sc.nextInt();
        try{
        if(connector.isValidHospital_Admin(hospitalAdminId)){
        	connector.addHospital(hospitalName, location, hospitalAdminId);
        	System.out.println("Hospital added successfully.");
        }else{
        	System.out.println("Invalid Hospital Admin Id");
        }
        }catch(SQLException e){
        	System.out.println(e);
        }

        
    }
    
    public static void addPatient(){
    
    	System.out.println("Enter patient Name");
    	String patient_Name = sc.next();
    	if(!Validation.validateName(patient_Name)){
        	System.out.println("Enter the name only in alphabets");
        	return;
        }
    	System.out.println("Enter patient Age");
    	int patient_Age = sc.nextInt();
    	if(patient_Age < 0){
    		System.out.println("Enter the Greater than 0 ");
    		return;
    	}
    	sc.nextLine();
    	
    	System.out.println("Enter patient Blood Group");
    	String patient_Blood_Group = sc.next();
    	
    	System.out.println("Enter patient Gender");
    	String patient_Gender= sc.next();
    	if(!Validation.validateName(patient_Gender)){
        	System.out.println("Enter the Gender only in alphabets");
        	return;
        }
    	
    	System.out.println("Enter patient Mobile_No");
    	long patient_Mobile_No = sc.nextLong();
    	sc.nextLine();
    	if(!Validation.validateMobile(patient_Mobile_No+"")){
        	System.out.println("Enter mobile_no that contain 10 numbers");
        	return;
        }
    	
    	System.out.println("Enter patient Email_id");
    	String patient_Email = sc.next();
    	if(!Validation.validateEmail(patient_Email)){
        	System.out.println("Enter the Valid Email_id");
        	return;
        }
    	
    	System.out.println("Enter patient User_Name");
    	String patient_User_Name = sc.next();
    	if(!Validation.validateUsername(patient_User_Name)){
        	System.out.println("Enter the Valid User Name");
        	return;
        }
    	
    	System.out.println("Enter patient password");
    	String patient_Password = sc.next();
    	if(!Validation.validatePassword(patient_Password)){
        	System.out.println("Enter the Valid Password");
        	return;
        }
    	
    	Patient patient = new Patient(patient_Name,patient_Blood_Group,patient_Age,patient_Gender,patient_Mobile_No,patient_Email,patient_User_Name,patient_Password);
    	
    }

    public static void addDoctor() {
        System.out.println("Enter doctor name:");
        String name = sc.next();
        if(!Validation.validateName(name)){
        	System.out.println("Enter the name only in alphabets");
        	return;
        }
        System.out.println("Enter doctor specialisation:");
        String specialisation = sc.next();
        if(!Validation.validateName(specialisation)){
        	System.out.println("Enter the specialisation only in alphabets");
        	return;
        }
        System.out.println("Enter doctors Hospital Id:");
        int hospitalId = sc.nextInt();
        if(hospitalId < 0){
        	System.out.println("Enter the Hospital_id Greater than 0 ");
        	return;
        }
        
        System.out.println("Enter doctor mobile number:");
        long mobileNumber = sc.nextLong();
        if(!Validation.validateMobile(mobileNumber+"")){
        	System.out.println("Enter mobile_no that contain 10 numbers");
        	return;
        }
        
        System.out.println("Enter doctor email_Id:");
        String emailId = sc.next();
        if(!Validation.validateEmail(emailId)){
        	System.out.println("Enter the Valid Email_id");
        	return;
        }
        
        System.out.println("Enter doctor username:");
        String userName = sc.next();
        if(!Validation.validateUsername(userName)){
        	System.out.println("Enter the Valid User Name");
        	return;
        }
    	
        System.out.println("Enter doctor password:");
        String password = sc.next();
        if(!Validation.validatePassword(password)){
        	System.out.println("Enter the Valid Password");
        	return;
        }
        System.out.println("Enter doctor status ( available or unavailable):");
        String status = sc.next();
        
        System.out.println("Enter doctor shift start time (HH:mm):");
	String shiftStartInput = sc.next();
	LocalTime shiftStart = LocalTime.parse(shiftStartInput, DateTimeFormatter.ofPattern("HH:mm"));

	System.out.println("Enter doctor shift end time (HH:mm):");
	String shiftEndInput = sc.next();
	LocalTime shiftEnd = LocalTime.parse(shiftEndInput, DateTimeFormatter.ofPattern("HH:mm"));

        Doctor doctor = new Doctor(name, specialisation, hospitalId, userName, password, mobileNumber, emailId, status, shiftStart, shiftEnd);
        connector.addDoctor(doctor);
        System.out.println("Doctor added successfully.");
    }

    public static void bookAppointment() {
    
    	ArrayList<String> hospital_Locations = connector.showHospitalLocations();
    	
    	System.out.println("Hospital Locations ");
    	for(String hospital_Location : hospital_Locations){
    		System.out.println(" ~~~> "+hospital_Location);
    	}
    	
    	System.out.println("Enter the Location");
    	String location = sc.next().toLowerCase();
    	
    	ArrayList<String> hospitals= connector.showHospitals(location);
    	System.out.println("Hospitals ");
    	for(String hospital : hospitals){
    		System.out.println(" ~~~> "+hospital);
    	}
    	
    	System.out.println("Enter the Hospital_Name");
    	String hospital = sc.next().toLowerCase();
    	int hospital_Id = connector.getHospital_Id(hospital);
    	
    	connector.displayDoctorsByHospital(hospital_Id);
    	
        System.out.println("Enter patient Id");
        int patient_Id= sc.nextInt();
        System.out.println("Enter doctor ID:");
        int doctor_Id = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter appointment date and time (yyyy-MM-dd HH:mm):");
        String dateTime = sc.nextLine();
        
        LocalDateTime appointmentDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        String patient_name = connector.getPatientName(patient_Id);
        String doctor_Name = connector.getDoctorName(doctor_Id);

        if (patient_name != null && doctor_Name != null) {
            connector.bookAppointment(patient_Id, doctor_Id, appointmentDateTime);
            System.out.println("Appointment booked successfully.");
        } else {
            System.out.println("Invalid patient ID or doctor ID.");
     	 }
    }

    public static void cancelAppointment(String user_Name) {
        //System.out.println("Enter appointment ID:");
        int appointmentId = sc.nextInt();
        connector.cancelAppointment(user_Name);
        System.out.println("Appointment canceled successfully.");
    }

    public static void viewDoctorsAppointments() {
        System.out.println("Enter doctor ID:");
        int doctorId = sc.nextInt();
        connector.showDoctors_Appointments(doctorId);
    }

    public static void viewHospitalsByLocation() {
        System.out.println("Enter location:");
        String location = sc.next();
        connector.displayHospitalsByLocation(location);
    }

    public static void viewDoctorsByHospital() {
        System.out.println("Enter hospital ID:");
        int hospitalId = sc.nextInt();
        connector.displayDoctorsByHospital(hospitalId);
    }
}

