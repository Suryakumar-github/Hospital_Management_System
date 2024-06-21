package connector;

import admins.Admin;
import dataBase.DBConnection;
import dataBase.SQLQueries;
import doctor.Doctor;
import patient.Patient;
import appointments.Appointments;
import hospital.Hospital;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Connector {

    public Connector() {
       
    }

   public boolean addAdmin(Admin admin) {
    try (PreparedStatement stmt = DBConnection.getPreparedStatement(SQLQueries.INSERT_ADMIN)) {
        stmt.setString(1, admin.getName());
        stmt.setString(2, admin.getUserName());
        stmt.setString(3, admin.getPassword());
        stmt.executeUpdate();
        return true;
    } catch (SQLException e) {
        System.out.println(e);
       
    }
    return false;
}

public void addDoctor(Doctor doctor) {
    try (PreparedStatement stmt = DBConnection.getPreparedStatement(SQLQueries.INSERT_INTO_DOCTOR)) {
        stmt.setString(1, doctor.getName());
        stmt.setString(2, doctor.getSpecialisation());
        stmt.setLong(3, doctor.getMobileNumber());
        stmt.setString(4, doctor.getUserName());
        stmt.setString(5, doctor.getPassword());
        stmt.setString(6, doctor.getStatus());
        stmt.setInt(7, doctor.getHospital_Id());
        stmt.setTime(8, java.sql.Time.valueOf(doctor.getShiftStart()));
        stmt.setTime(9, java.sql.Time.valueOf(doctor.getShiftEnd()));
        stmt.executeUpdate();
    } catch (SQLException e) {
        System.out.println(e);
    }
}


public void addPatient(Patient patient) {
    try (PreparedStatement stmt = DBConnection.getPreparedStatement(SQLQueries.INSERT_INTO_PATIENT)) {
        stmt.setString(1, patient.getName());
        stmt.setInt(2, patient.getAge());
        stmt.setString(3, patient.getBloodGroup());
        stmt.setString(4, patient.getGender());
        stmt.setLong(5, patient.getMobileNumber());
        stmt.setString(6, patient.getEmail_id());
        stmt.setString(7, patient.getUserName());
        stmt.setString(8, patient.getPassword());
        stmt.executeUpdate();
    } catch (SQLException e) {
        System.out.println(e);
    }
}

public void addHospital(String hospitalName, String location, int hospitalAdminId) throws SQLException {
    try (PreparedStatement stmt = DBConnection.getPreparedStatement(SQLQueries.ADD_HOSPITAL)) {
        stmt.setString(1, hospitalName);
        stmt.setString(2, location);
        stmt.setInt(3, hospitalAdminId);
        stmt.executeUpdate();
    }
}

private void addAppointment(int doctorId, int patientId, LocalDateTime appointmentDate, String status) {
    try (PreparedStatement stmt = DBConnection.getPreparedStatement(SQLQueries.ADD_APPOINTMENT)) {
        stmt.setInt(1, doctorId);
        stmt.setInt(2, patientId);
        stmt.setTimestamp(3, Timestamp.valueOf(appointmentDate));
        stmt.setString(4, status);
        stmt.executeUpdate();
    } catch (SQLException e) {
        System.out.println(e);
    }
}

public void showDoctors_Appointments(int doctorId) {
    try (PreparedStatement stmt = DBConnection.getPreparedStatement(SQLQueries.SHOW_DOCOTORS_APPOINTMENTS)) {
        stmt.setInt(1, doctorId);
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_Id");
                String doctorName = rs.getString("Doctor_Name");
                String patientName = rs.getString("Patient_Name");
                String appointmentDate = rs.getString("Appointment_Date");
                String status = rs.getString("Status");

                System.out.println("Appointment ID: " + appointmentId);
                System.out.println("Doctor Name: " + doctorName);
                System.out.println("Patient Name: " + patientName);
                System.out.println("Appointment Date: " + appointmentDate);
                System.out.println("Status: " + status);
                System.out.println("-------------------------");
            }
        }
    } catch (SQLException e) {
        System.out.println(e);
    }
}

public void showPatients_Appointments(int patientId) {
    try (PreparedStatement stmt = DBConnection.getPreparedStatement(SQLQueries.SHOW_PATIENTS_APPOINTMENTS)) {
        stmt.setInt(1, patientId);
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_Id");
                String doctorName = rs.getString("Doctor_Name");
                String patientName = rs.getString("Patient_Name");
                String appointmentDate = rs.getString("Appointment_Date");
                String status = rs.getString("Status");

                System.out.println("Appointment ID: " + appointmentId);
                System.out.println("Doctor Name: " + doctorName);
                System.out.println("Patient Name: " + patientName);
                System.out.println("Appointment Date: " + appointmentDate);
                System.out.println("Status: " + status);
                System.out.println("-------------------------");
            }
        }
    } catch (SQLException e) {
        System.out.println(e);
    }
}


public void displayAllAppointments(int admin_id) {
    try (PreparedStatement stmt = DBConnection.getPreparedStatement(SQLQueries.SHOW_ALL_APPOINTMENTS)) {
        stmt.setInt(1, admin_id);
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int appointmentId = rs.getInt("Appointment_Id");
                String doctorName = rs.getString("Doctor_Name");
                String patientName = rs.getString("Patient_Name");
                String appointmentDate = rs.getString("Appointment_Date");
                String status = rs.getString("Status");

                System.out.println("Appointment ID: " + appointmentId);
                System.out.println("Doctor Name: " + doctorName);
                System.out.println("Patient Name: " + patientName);
                System.out.println("Appointment Date: " + appointmentDate);
                System.out.println("Status: " + status);
                System.out.println("-------------------------");
            }
        }
    } catch (SQLException e) {
        System.out.println(e);
    }
}


public void cancelAppointment(String user_Name) {
    try (PreparedStatement stmt = DBConnection.getPreparedStatement(SQLQueries.CANCEL_APPOINTMENT)) {
        stmt.setString(1, user_Name);
        int rowsUpdated = stmt.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Appointment with ID " + appointmentId + " has been cancelled.");
        } else {
            System.out.println("No appointment found with ID " + appointmentId);
        }
    } catch (SQLException e) {
        System.out.println(e);
    }
}

public List<LocalDateTime> getDoctorAppointmentsForDay(int doctorId, LocalDateTime date) {
    List<LocalDateTime> appointments = new ArrayList<>();
    String query = SQLQueries.GET_DOCTOR_APPOINTMENTS_FOR_DAY;

    try (PreparedStatement stmt =  DBConnection.getConnection().prepareStatement(query)) {
        stmt.setInt(1, doctorId);
        stmt.setDate(2, java.sql.Date.valueOf(date.toLocalDate()));
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                appointments.add(rs.getTimestamp("Appointment_Date").toLocalDateTime());
            }
        }
    } catch (SQLException e) {
        System.out.println(e);
    }
    return appointments;
}

public void bookAppointment(int patient_Id, int doctor_Id, LocalDateTime date) {
    List<LocalDateTime> appointments = getDoctorAppointmentsForDay(doctor_Id, date);

    Connector connector = new Connector();
    LocalTime shiftStart = connector.getDoctorShiftStartedTime(doctor_Id);
    LocalTime shiftEnd = connector.getDoctorShiftEndedTime(doctor_Id);

    LocalDateTime currentTime = LocalDateTime.of(date.toLocalDate(), shiftStart);
    boolean slotFound = false;

    while (currentTime.toLocalTime().isBefore(shiftEnd)) {
        if (!appointments.contains(currentTime)) {
            addAppointment(doctor_Id, patient_Id, currentTime, "Booked");
            slotFound = true;
            System.out.println("Appointment booked at " + currentTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            break;
        }
        currentTime = currentTime.plusMinutes(30);
    }

    if (!slotFound) {
        System.out.println("No available slots for the selected doctor on " + date.toLocalDate());
    }
}

public LocalTime getDoctorShiftStartedTime(int doctor_Id) {
    LocalTime startTime = null;
    try (PreparedStatement stmt = DBConnection.getPreparedStatement(SQLQueries.GET_DOCTOR_SHIFT_START)) {
        stmt.setInt(1, doctor_Id);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                Time startSqlTime = rs.getTime("Shift_Start");
                if (startSqlTime != null) {
                    startTime = startSqlTime.toLocalTime();
                }
            }
        }
    } catch (SQLException e) {
        System.out.println(e);
    }
    return startTime;
}

public LocalTime getDoctorShiftEndedTime(int doctor_Id) {
    LocalTime endTime = null;
    try (PreparedStatement stmt = DBConnection.getPreparedStatement(SQLQueries.GET_DOCTOR_SHIFT_END)) {
        stmt.setInt(1, doctor_Id);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                Time endSqlTime = rs.getTime("Shift_End");
                if (endSqlTime != null) {
                    endTime = endSqlTime.toLocalTime();
                }
            }
        }
    } catch (SQLException e) {
        System.out.println(e);
    }
    return endTime;
}

public void displayAllLocations() {
    try (PreparedStatement stmt = DBConnection.getPreparedStatement(SQLQueries.GET_ALL_LOCATIONS);
         ResultSet rs = stmt.executeQuery()) {

        System.out.println("Hospital Locations:");
        while (rs.next()) {
            String location = rs.getString("Location");
            System.out.println(location);
        }
    } catch (SQLException e) {
        System.out.println(e);
    }
}

public void displayHospitalsByLocation(String location) {
    try (PreparedStatement stmt = DBConnection.getPreparedStatement(SQLQueries.GET_HOSPITALS_BY_LOCATION)) {
        stmt.setString(1, location);
        try (ResultSet rs = stmt.executeQuery()) {
            System.out.println("Hospitals in " + location + ":");
            while (rs.next()) {
                String hospitalName = rs.getString("Hospital_Name");
                System.out.println(hospitalName);
            }
        }
    } catch (SQLException e) {
        System.out.println(e);
    }
}

public void displayDoctorsByHospital(int hospitalId) {
    try (PreparedStatement stmt = DBConnection.getPreparedStatement(SQLQueries.GET_DOCTORS_BY_HOSPITAL)) {
        stmt.setInt(1, hospitalId);
        try (ResultSet rs = stmt.executeQuery()) {
            System.out.println("Doctors in Hospital ID " + hospitalId + ":");
            while (rs.next()) {
                String name = rs.getString("Name");
                String specialisation = rs.getString("Specialisation");
                Time shiftStart = rs.getTime("Shift_Start");
                Time shiftEnd = rs.getTime("Shift_End");

                System.out.printf("Name: %s, Specialisation: %s, Shift: %s - %s%n", name, specialisation, shiftStart.toString(), shiftEnd.toString());
            }
        }
    } catch (SQLException e) {
        System.out.println(e);
    }
}

public String getPatientName(int patient_Id) {
    String name = null;
    try (PreparedStatement stmt = DBConnection.getPreparedStatement(SQLQueries.GET_PATIENT_NAME_BY_ID)) {
        stmt.setInt(1, patient_Id);
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                name = rs.getString("Name");
            }
        }
    } catch (SQLException e) {
        System.out.println(e);
    }
    return name;
}

public String getDoctorName(int doctor_Id) {
    String name = null;
    try (PreparedStatement stmt = DBConnection.getPreparedStatement(SQLQueries.GET_DOCTOR_NAME_BY_ID)) {
        stmt.setInt(1, doctor_Id);
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                name = rs.getString("Name");
            }
        }
    } catch (SQLException e) {
        System.out.println(e);
    }
    return name;
}

public boolean validateAdmin(String user_Name, String password) {
    try (PreparedStatement stmt = DBConnection.getPreparedStatement(SQLQueries.GET_ADMIN_USER_NAME_PASSWORD)) {
        stmt.setString(1, user_Name);
        stmt.setString(2, password);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return true;
            }
        }
    } catch (SQLException e) {
        System.out.println(e);
    }
    return false;
}

public boolean validateHospitalAdmin(String user_Name, String password) {
    try (PreparedStatement stmt = DBConnection.getPreparedStatement(SQLQueries.GET_HOSPITAL_ADMIN_USER_NAME_PASSWORD)) {
        stmt.setString(1, user_Name);
        stmt.setString(2, password);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return true;
            }
        }
    } catch (SQLException e) {
        System.out.println(e);
    }
    return false;
}

public boolean validateHospitalAdmin(String user_Name, String password, int hospital_Admin_Id) {
    try (PreparedStatement stmt = DBConnection.getPreparedStatement(SQLQueries.GET_HOSPITAL_ADMIN_USER_NAME_PASSWORD_BY_ID)) {
        stmt.setInt(1, hospital_Admin_Id);

        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                String fetchedUserName = rs.getString("User_Name");
                String fetchedPassword = rs.getString("Password");

                if (fetchedUserName.equals(user_Name) && fetchedPassword.equals(password)) {
                    return true;
                }
            }
        }
    } catch (SQLException e) {
        System.out.println(e);
    }
    return false;
}


public boolean validatePatient(String user_Name, String password) {
    try (PreparedStatement stmt = DBConnection.getPreparedStatement(SQLQueries.GET_PATIENT_USER_NAME_PASSWORD)) {
        stmt.setString(1, user_Name);
        stmt.setString(2, password);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return true;
            }
        }
    } catch (SQLException e) {
        System.out.println(e);
    }
    return false;
}

public boolean validateDoctor(String user_Name, String password) {
    try (PreparedStatement stmt = DBConnection.getPreparedStatement(SQLQueries.GET_DOCTOR_USER_NAME_PASSWORD)) {
        stmt.setString(1, user_Name);
        stmt.setString(2, password);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return true;
            }
        }
    } catch (SQLException e) {
        System.out.println(e);
    }
    return false;
}

public boolean isValidHospital_Admin(int hospital_Admin_Id) {
    try (PreparedStatement stmt = DBConnection.getPreparedStatement(SQLQueries.GET_HOSPITAL_ADMIN_ID)) {
        stmt.setInt(1, hospital_Admin_Id);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return true;
            }
        }
    } catch (SQLException e) {
        System.out.println(e);
    }
    return false;
}

public ArrayList<String> showHospitalLocations() {
    ArrayList<String> list = new ArrayList<>();
    try (Statement stmt = DBConnection.getConnection().createStatement();
         ResultSet rs = stmt.executeQuery(SQLQueries.GET_ALL_HOSPITAL_LOCATIONS)) {

        while (rs.next()) {
            String location = rs.getString("Location");
            list.add(location);
        }
    } catch (SQLException e) {
        System.out.println(e);
    }
    return list;
}

public ArrayList<String> showHospitals(String location) {
    ArrayList<String> list = new ArrayList<>();
    try (PreparedStatement stmt = DBConnection.getPreparedStatement(SQLQueries.GET_HOSPITALS_BY_LOCATION)) {
        stmt.setString(1, location);
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String hospitalName = rs.getString("Hospital_Name");
                list.add(hospitalName);
            }
        }
    } catch (SQLException e) {
        System.out.println(e);
    }
    return list;
}

public int getHospital_Id(String hospital_Name) {
    int hospital_Id = 0;
    try (PreparedStatement stmt = DBConnection.getPreparedStatement(SQLQueries.GET_HOSPITAL_ID)) {
        stmt.setString(1, hospital_Name);
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                hospital_Id = rs.getInt("Hospital_Id");
            }
        }
    } catch (SQLException e) {
        System.out.println(e);
    }
    return hospital_Id;
}
	
	public List<Hospital> getAllHospitals() {

    List<Hospital> hospitals = new ArrayList<>();

    String sqlQuery = SQLQueries.GET_ALL_HOSPITALS;

    try (Connection conn = DBConnection.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sqlQuery)) {

        while (rs.next()) {
            int hospitalId = rs.getInt("Hospital_Id");
            String hospitalName = rs.getString("Hospital_Name");
            String location = rs.getString("Location");
            String hospitalAdmin = rs.getString("Admin_Name");

            Hospital hospital = new Hospital(hospitalId, hospitalName, location, hospitalAdmin);
            hospitals.add(hospital);
        }

    } catch (SQLException e) {
        System.out.println(e);
    }

    return hospitals;
}

      public List<Doctor> getAllDoctors(int hospital_Id) throws SQLException {
    List<Doctor> list = new ArrayList<>();
    try (PreparedStatement statement = DBConnection.getPreparedStatement(SQLQueries.SHOW_ALL_DOCTORS)) {
        statement.setInt(1, hospital_Id);

        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            int doctorId = rs.getInt("Doctor_Id");
            String doctorName = rs.getString("Doctor_Name");
            String specialisation = rs.getString("Specialisation");
            String status = rs.getString("Status");
            LocalTime shiftStart = rs.getTime("Shift_Start").toLocalTime();
            LocalTime shiftEnd = rs.getTime("Shift_End").toLocalTime();

            Doctor doctor = new Doctor(doctorId, doctorName, specialisation, status, shiftStart, shiftEnd);
            list.add(doctor);
        }
    }
    return list;
}
       
}

