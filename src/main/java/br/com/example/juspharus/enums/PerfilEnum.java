package br.com.example.juspharus.enums;



public enum PerfilEnum {
    CLIENTE("CLIENTE"),
    ADVOGADO("ADVOGADO"),
    ADMIN("ADMIN");

    private final String value;

    private PerfilEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}