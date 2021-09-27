/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cardealership.models;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author kavin
 */
public class Make {
    private int id;
    private String name;
    private LocalDate dateAdded;
    private User user;

    public Make(int id, String name, LocalDate dateAdded, User user) {
        this.id = id;
        this.name = name;
        this.dateAdded = dateAdded;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public User getUser() {
        return user;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.dateAdded);
        hash = 97 * hash + Objects.hashCode(this.user);
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
        final Make other = (Make) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.dateAdded, other.dateAdded)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Make{" + "id=" + id + ", name=" + name + ", dateAdded=" + dateAdded + ", user=" + user + '}';
    }

    
}
