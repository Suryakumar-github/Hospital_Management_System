package user;

public abstract class User {
    protected String name;
    protected String userName;
    protected String password;
    protected long mobileNumber;
    protected String emil_id;
    
    public User(String name, String userName, String password, long mobileNumber, String emil_id) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.emil_id = emil_id;
    }
    
    public String getName() { return name; }
    public String getUserName() { return userName; }
    public String getPassword() { return password; }
    public long getMobileNumber() { return mobileNumber; }
    public String getEmail_id() { return emil_id; }

    // method for display user information
    public abstract void displayUserInfo();
}

