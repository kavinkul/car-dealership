package com.sg.cardealership.models;

import java.util.Objects;

public class Trim {
    private int id;
    private String name;
    private String interiorColor;
    private String exteriorColor;
    private Transmission transmission;

    public Trim(String name, String interiorColor, String exteriorColor, Transmission transmission) {
        this.name = name;
        this.interiorColor = interiorColor;
        this.exteriorColor = exteriorColor;
        this.transmission = transmission;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getInteriorColor() {
        return interiorColor;
    }

    public void setInteriorColor(String interiorColor) {
        this.interiorColor = interiorColor;
    }

    public String getExteriorColor() {
        return exteriorColor;
    }

    public void setExteriorColor(String exteriorColor) {
        this.exteriorColor = exteriorColor;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + this.id;
        hash = 13 * hash + Objects.hashCode(this.name);
        hash = 13 * hash + Objects.hashCode(this.interiorColor);
        hash = 13 * hash + Objects.hashCode(this.exteriorColor);
        hash = 13 * hash + Objects.hashCode(this.transmission);
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
        final Trim other = (Trim) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.interiorColor, other.interiorColor)) {
            return false;
        }
        if (!Objects.equals(this.exteriorColor, other.exteriorColor)) {
            return false;
        }
        if (this.transmission != other.transmission) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Trim{" + "id=" + id + ", name=" + name + ", interiorColor=" + interiorColor + ", exteriorColor=" + exteriorColor + ", transmission=" + transmission + '}';
    }


}