/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cardealership.models;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author kavin
 */
public class Sale {
    private User user;
    private List<Vehicle> vehicles;

    public Sale(User user, List<Vehicle> vehicles) {
        this.user = user;
        this.vehicles = vehicles;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.user);
        hash = 17 * hash + Objects.hashCode(this.vehicles);
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
        final Sale other = (Sale) obj;
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.vehicles, other.vehicles)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Sale{" + "user=" + user + ", vehicles=" + vehicles + '}';
    }

    
}
