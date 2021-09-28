/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cardealership.models;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author kavin
 */
public class Model {
    private int id;
    private String name;
    private List<Integer> years;
    private LocalDate dateAdded;
    private User user;
    private Make make;

    public Model(String name, List<Integer> years, LocalDate dateAdded, User user, Make make) {
        this.name = name;
        this.years = years;
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

    public List<Integer> getYears() {
        return years;
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
        hash = 13 * hash + Objects.hashCode(this.years);
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
        if (!Objects.equals(this.years, other.years)) {
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
        return "Model{" + "id=" + id + ", name=" + name + ", years=" + years + ", dateAdded=" + dateAdded + ", user=" + user + ", make=" + make + '}';
    }


}
