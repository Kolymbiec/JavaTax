package ua.training.model.entities;

import java.util.Objects;

public class Taxpayer {

    private int id;
    private  User user;
    private TaxpayerType type;
    private Inspector inspector;
    private String name;
    private String surname;
    private String email;

    public Taxpayer(){}

    public Taxpayer(int id, User user, TaxpayerType type, Inspector inspector, String name, String surname, String email) {
        this.id = id;
        this.user = user;
        this.type = type;
        this.inspector = inspector;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public Taxpayer(User user, TaxpayerType type, Inspector inspector, String name, String surname, String email) {
        this.user = user;
        this.type = type;
        this.inspector = inspector;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TaxpayerType getType() {
        return type;
    }

    public void setType(TaxpayerType type) {
        this.type = type;
    }

    public Inspector getInspector() {
        return inspector;
    }

    public void setInspector(Inspector inspector) {
        this.inspector = inspector;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Taxpayer taxpayer = (Taxpayer) o;
        return id == taxpayer.id &&
                user.equals(taxpayer.user) &&
                type.equals(taxpayer.type) &&
                inspector.equals(taxpayer.inspector) &&
                name.equals(taxpayer.name) &&
                surname.equals(taxpayer.surname) &&
                email.equals(taxpayer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, type, inspector, name, surname, email);
    }

    @Override
    public String toString() {
        return "Taxpayer{" +
                "id=" + id +
                ", user=" + user +
                ", type=" + type +
                ", inspector=" + inspector +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
