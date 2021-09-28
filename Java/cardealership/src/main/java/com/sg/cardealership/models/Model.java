package com.sg.cardealership.models;

import java.time.LocalDate;
import java.util.Objects;

public class Model {
    private int id;
    private String name;
    private int year;
    private LocalDate dateAdded;
    private User user;
    private Make make;

    public Model(String name, int year, LocalDate dateAdded, User user, Make make) {
        this.name = name;
        this.year = year;
        this.dateAdded = dateAdded;
        this.user = user;
        this.make = make;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public User getUser() {
        return user;
    }

    public Make getMake() {
        return make;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + this.id;
        hash = 13 * hash + Objects.hashCode(this.name);
        hash = 13 * hash + Objects.hashCode(this.year);
        hash = 13 * hash + Objects.hashCode(this.dateAdded);
        hash = 13 * hash + Objects.hashCode(this.user);
        hash = 13 * hash + Objects.hashCode(this.make);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Model other = (Model) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.year, other.year)) {
            return false;
        }
        if (!Objects.equals(this.dateAdded, other.dateAdded)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.make, other.make)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model{" + "id=" + id + ", name=" + name + ", year=" + year + ", dateAdded=" + dateAdded + ", user=" + user + ", make=" + make + '}';
    }
}
