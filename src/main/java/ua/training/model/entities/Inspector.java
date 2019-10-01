package ua.training.model.entities;

import java.util.Objects;

public class Inspector {

    private int id;
    private User user;
    private String name;
    private String surname;
    private String email;

    public Inspector(){}

    public Inspector(int id, User user, String name, String surname, String email) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public Inspector(User user, String name, String surname, String email) {
        this.user = user;
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
        Inspector inspector = (Inspector) o;
        return id == inspector.id &&
                user.equals(inspector.user) &&
                name.equals(inspector.name) &&
                surname.equals(inspector.surname) &&
                email.equals(inspector.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, name, surname, email);
    }

    @Override
    public String toString() {
        return "Inspector{" +
                "id=" + id +
                ", user=" + user +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
