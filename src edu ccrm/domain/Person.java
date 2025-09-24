package edu.ccrm.domain;

public abstract class Person {
    private final String id;
    private String fullName;
    private String email;

    protected Person(String id, String fullName, String email) {
        assert id != null && !id.isBlank() : "id must be non-empty";
        this.id = id;
        this.fullName = fullName;
        this.email = email;
    }

    public String getId() { return id; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public abstract String profile();

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}


