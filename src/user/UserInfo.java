package User;

public class UserInfo {

    protected static final String userFilename = "User.txt";
    protected int userID;
    protected String username, password;

    
    public UserInfo(int userID, String username, String password) {
        this.userID = userID;
        this.username = username;
        this.password = password;
    }


    public int getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
    public String toString(){
        String joinLine = String.join(" // ", String.valueOf(userID), username, password);
        return joinLine;
    }
}
