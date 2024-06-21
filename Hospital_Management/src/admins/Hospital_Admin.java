package admins;
import user.User;
public class Hospital_Admin extends User {

    public Hospital_Admin(String name, String userName, String password, long mobileNumber, String emailId) {
        super(name, userName, password, mobileNumber,emailId);
        
    }

    public void displayUserInfo() {
        System.out.println("Hospital Admin Name: " + name);
        //System.out.println("Email ID: " + emailId);
        System.out.println("Mobile Number: " + mobileNumber);
    }
}
