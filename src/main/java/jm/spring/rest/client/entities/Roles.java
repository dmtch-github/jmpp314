package jm.spring.rest.client.entities;

public enum Roles {
    ADMIN("ADMIN"),
    USER("USER");

    private String value;

    Roles(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}
