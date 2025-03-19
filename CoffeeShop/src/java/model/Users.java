package model;

public class Users {

    private int ID;
    private String username;
    private String password;
    private int role;
    private boolean active;

    // Constructors
    public Users() {
    }

    public Users(int ID, String username, String password, int role) {
        this.ID = ID;
        this.username = username;
        this.password = password;
        this.role = role;
    }
    
    

    public Users(String username, String password, int role, boolean active) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.active = active;

    }

    public Users(int ID, String username, String password, int role, boolean active) {
        this.ID = ID;
        this.username = username;
        this.password = password;
        this.role = role;
        this.active = active;
    }

    // Getters and setters
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean status) {
        this.active = status;
    }

    @Override
    public String toString() {
        return "Users{" + "ID=" + ID + ", username=" + username + ", password=" + password + ", role=" + role + ", active=" + active + '}';
    }

}
