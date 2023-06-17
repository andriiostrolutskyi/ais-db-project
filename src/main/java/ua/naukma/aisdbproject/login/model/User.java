package ua.naukma.aisdbproject.login.model;

public class User {
    private String usrUsername;
    private String usrPassword;
    private String usrRole;

    public User() {
    }

    public User(String usrUsername, String usrPassword, String usrRole) {
        this.usrUsername = usrUsername;
        this.usrPassword = usrPassword;
        this.usrRole = usrRole;
    }

    public String getUsrUsername() {
        return usrUsername;
    }

    public void setUsrUsername(String usrUsername) {
        this.usrUsername = usrUsername;
    }

    public String getUsrPassword() {
        return usrPassword;
    }

    public void setUsrPassword(String usrPassword) {
        this.usrPassword = usrPassword;
    }

    public String getUsrRole() {
        return usrRole;
    }

    public void setUsrRole(String usrRole) {
        this.usrRole = usrRole;
    }
}
