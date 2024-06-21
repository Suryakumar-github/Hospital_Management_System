package admins;

import user.User;

public class Admin extends User {

    public Admin(String name, String userName, String password ,long mobileNumber,String email_Id) {
        super(name,userName,password,mobileNumber,email_Id);
    }

    
    public void displayUserInfo() {

    }
}
