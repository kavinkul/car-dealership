package com.sg.cardealership.models;

import java.util.Objects;

public class Condition {
    private int id;
    private int mileage;
    private MileageUnit unit;
    private Type type;

    public Condition(int mileage, MileageUnit unit, Type type) {
        this.mileage = mileage;
        this.unit = unit;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public MileageUnit getUnit() {
        return unit;
    }

    public void setUnit(MileageUnit unit) {
        this.unit = unit;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.id;
        hash = 37 * hash + this.mileage;
        hash = 37 * hash + Objects.hashCode(this.unit);
        hash = 37 * hash + Objects.hashCode(this.type);
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
        final Condition other = (Condition) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.mileage != other.mileage) {
            return false;
        }
        if (this.unit != other.unit) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Condition{" + "id=" + id + ", mileage=" + mileage + ", unit=" + unit + ", type=" + type + '}';
    }
}