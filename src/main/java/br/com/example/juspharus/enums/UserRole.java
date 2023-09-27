package br.com.example.juspharus.enums;



public enum UserRole {
    CLIENTE("CLIENTE"),
    ADVOGADO("ADVOGADO"),
    ADMIN("ADMIN");

    private final String value;

    private UserRole(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}