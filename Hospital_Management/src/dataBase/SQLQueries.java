package dataBase;

public class SQLQueries {

    public static final String INSERT_HOSPITAL_ADMIN = "INSERT INTO Hospital_Admin (Admin_name, User_Name, Password, Mobile_No, Email_Id) VALUES (?, ?, ?, ?, ?)";
    
    public static final String INSERT_HOSPITAL = "INSERT INTO Hospital(Hospital_Name, Location, Hospital_Admin_Id) VALUES (?, ?, ?)";
    
    public static final String INSERT_INTO_DOCTOR = "INSERT INTO Doctor (Name, Specialisation, Mobile_No, User_Name, Password, Status, Hospital_id, Shift_Start, Shift_End) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    
    public static final String INSERT_INTO_PATIENT = "INSERT INTO Patient(Name, age, blood_Group,gender,mobile_No,user_Name,password) VALUES (?, ?, ?,?,?,?,?)";
    
    public static final String INSERT_ADMIN = "INSERT INTO Admin(Name, User_Name, Password) VALUES (?, ?, ?)";
    
    public static final String ADD_APPOINTMENT = "INSERT INTO Appointments(Doctor_Id,Patient_Id,Appointment_Date, Status) VALUES (?, ?, ?, ?)";

    public static final String SHOW_DOCOTORS_APPOINTMENTS = "SELECT a.Appointment_Id, d.Name AS Doctor_Name, p.Name AS Patient_Name, " +
                   "a.Appointment_Date, a.Status " +
                   "FROM Appointments a " +
                   "JOIN Doctor d ON a.Doctor_Id = d.Doctor_Id " +
                   "JOIN Patient p ON a.Patient_Id = p.Patient_Id " +
                   "WHERE d.Doctor_Id = ?";
    
    public static final String SHOW_PATIENTS_APPOINTMENTS = "SELECT a.Appointment_Id, d.Name AS Doctor_Name, p.Name AS Patient_Name, a.Appointment_Date, a.Status " +
                                    "FROM Appointments a " +
                                    "JOIN Doctor d ON a.Doctor_Id = d.Doctor_Id " +
                                    "JOIN Patient p ON a.Patient_Id = p.Patient_Id " +
                                    "WHERE p.Patient_Id = ?";

    
    public static final String CANCEL_APPOINTMENT = "UPDATE Appointments SET Status = 'Cancelled' WHERE Appointment_Id = ?";
    
    public static final String GET_ALL_LOCATIONS = "SELECT Location FROM Hospital";
    
    public static final String GET_HOSPITALS_BY_LOCATION = "SELECT Hospital_Name FROM Hospital WHERE Location = ?";
    
    public static final String GET_DOCTORS_BY_HOSPITAL = "SELECT Name, Specialisation, Shift_Start, Shift_End FROM Doctor WHERE Hospital_Id = ?";
    
    public static final String CREATE_ADMIN_TABLE = "CREATE TABLE IF NOT EXISTS Admin(Admin_id int primary key auto_increment, Name varchar(30) not null,User_Name varchar(20) unique not null,Password varchar(16) not null)";
    
    public static final String CREATE_DOCTOR_TABLE = "CREATE TABLE IF NOT EXISTS Doctor(Doctor_id int primary key auto_increment, Name varchar(30) not null,Specialisation varchar(30) not null,Mobile_No bigint unique not null,User_Name varchar(16) unique not null,Password varchar(16) not null,status varchar(30) not null,Hospital_Id int ,foreign key(Hospital_Id) references Hospital(Hospital_Id))";
    
    public static final String CREATE_PATIENT_TABLE = "CREATE TABLE IF NOT EXISTS Patient(Patient_Id int primary key auto_increment, Name varchar(20) not null,Age int not null,Blood_Group varchar(10) not null,Gender varchar(16) not null,Mobile_No bigint unique not null,User_Name varchar(20) unique not null,Password varchar(16) not null)";
    
    
    
    public static final String GET_DOCTOR_APPOINTMENTS_FOR_DAY =  "SELECT Appointment_Date FROM Appointments WHERE Doctor_Id = ? AND DATE(Appointment_Date) = ?";
    
    public static final String GET_PATIENT_NAME_BY_ID = "SELECT name from Patient WHERE Patient_Id = ?";
    
    public static final String GET_DOCTOR_NAME_BY_ID = "SELECT name from Doctor WHERE Doctor_Id = ?";
    
    public static final String GET_DOCTOR_SHIFT_START = "SELECT Shift_Start FROM Doctor WHERE Doctor_Id = ?";
    
    public static final String GET_DOCTOR_SHIFT_END = "SELECT Shift_End FROM Doctor WHERE Doctor_Id = ?";
    
    public static final String GET_ADMIN_USER_NAME_PASSWORD = "SELECT User_Name,Password FROM Admin WHERE User_Name = ? AND Password = ?";
    
    public static final String GET_DOCTOR_USER_NAME_PASSWORD = "SELECT User_Name,Password FROM Doctor WHERE User_Name = ? AND Password = ?";
    
    public static final String GET_HOSPITAL_ADMIN_USER_NAME_PASSWORD = "SELECT User_Name,Password FROM Hospital_Admin WHERE User_Name = ? AND Password = ?";
    
    public static final String GET_PATIENT_USER_NAME_PASSWORD = "SELECT User_Name,Password FROM Patient WHERE User_Name = ? AND Password = ?";
    
    
    public static final String GET_HOSPITAL_ADMIN_ID = "SELECT Hospital_Admin_Id FROM Hospital_Admin WHERE Hospital_Admin_Id = ?";
    
    public static final String GET_ALL_HOSPITAL_LOCATIONS = "SELECT Location FROM Hospital";
    
    public static final String GET_ALL_HOSPITALS = "SELECT h.Hospital_Id, h.Hospital_Name, h.Location, ha.Admin_Name " +
                      "FROM Hospital h " +
                      "INNER JOIN Hospital_Admin ha ON h.Hospital_Admin_Id = ha.Hospital_Admin_Id";
    public static final String GET_HOSPITAL_ID = "SELECT Hospital_Id FROM Hospital WHERE Hospital_Name = ?";
    
    public static final String ADD_HOSPITAL = "INSERT INTO Hospital(Hospital_Name,Location,Hospital_Admin_Id) values(?,?,?)";
    public static final String GET_HOSPITAL_ADMIN_USER_NAME_PASSWORD_BY_ID = "SELECT User_Name,Password FROM Hospital_Admin where Hospital_Admin_Id = ?";
    
    public static final String SHOW_ALL_APPOINTMENTS = "SELECT a.Appointment_Id, d.Name AS Doctor_Name, p.Name AS Patient_Name, " +
                   "a.Appointment_Date, a.Status " +
                   "FROM Appointments a " +
                   "JOIN Doctor d ON a.Doctor_Id = d.Doctor_Id " +
                   "JOIN Patient p ON a.Patient_Id = p.Patient_Id " +
                   "JOIN Hospital h ON d.Hospital_Id = h.Hospital_Id " +
                   "JOIN Hospital_Admin ha ON h.Hospital_Admin_Id = ha.Hospital_Admin_Id " +
                   "WHERE ha.Hospital_Admin_Id = ?";
                   
    public static final String SHOW_ALL_DOCTORS = "SELECT d.Doctor_Id,d.Name AS Doctor_Name, d.Specialisation,d.Status,d.Shift_Start,d.Shift_End FROM Hospital h JOIN Hospital_Admin ha ON h.Hospital_Admin_Id = ha.Hospital_Admin_Id JOIN Doctor d ON h.Hospital_Id = d.Hospital_Id WHERE ha.Hospital_Admin_Id = ?";  
}
