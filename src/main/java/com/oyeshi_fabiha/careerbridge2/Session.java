package com.oyeshi_fabiha.careerbridge2;

public class Session {

    private static Session instance;

    public enum Role { STUDENT, ALUMNI, ADMIN }

    private String username;
    private String displayName;
    private Role   role;

    private Session() {}

    public static Session get() {
        if (instance == null) instance = new Session();
        return instance;
    }

    public void login(String username, String displayName, Role role) {
        this.username    = username;
        this.displayName = displayName;
        this.role        = role;
    }

    public void logout() {
        username = null;
        displayName = null;
        role = null;
    }

    public String getUsername()    { return username; }
    public String getDisplayName() { return displayName; }
    public Role   getRole()        { return role; }
    public boolean isLoggedIn()    { return username != null; }
}