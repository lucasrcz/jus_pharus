package br.com.example.juspharus.enums;



public enum UserRole {
    CLIENTE("CLIENTE"),
    ADVOGADO("ADVOGADO"),
    ADMIN("ADMIN");

    private final String role;

    private UserRole(String value) {
        this.role = value;
    }

    public String getRole() {
        return role;
    }
}