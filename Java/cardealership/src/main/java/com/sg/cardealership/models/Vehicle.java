/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cardealership.models;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.Objects;

/**
 *
 * @author kavin
 */
public class Vehicle {
    private String VIN;
    private Model model;
    private Condition vehicleCondition;
    private String bodyStyle;
    private Blob picture;
    private String description;
    private Trim trim;
    private BigDecimal salesPrice;
    private BigDecimal MSRP;
    private boolean featured;

    public Vehicle(String VIN, String bodyStyle, Blob picture, String description, BigDecimal salesPrice, BigDecimal MSRP, boolean featured) {
        this.VIN = VIN;
        this.bodyStyle = bodyStyle;
        this.picture = picture;
        this.description = description;
        this.salesPrice = salesPrice;
        this.MSRP = MSRP;
        this.featured = featured;
    }

    public Vehicle(String VIN, Model model, Condition vehicleCondition, String bodyStyle, Blob picture, String description, Trim trim, BigDecimal salesPrice, BigDecimal MSRP, boolean featured) {
        this.VIN = VIN;
        this.model = model;
        this.vehicleCondition = vehicleCondition;
        this.bodyStyle = bodyStyle;
        this.picture = picture;
        this.description = description;
        this.trim = trim;
        this.salesPrice = salesPrice;
        this.MSRP = MSRP;
        this.featured = featured;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Condition getVehicleCondition() {
        return vehicleCondition;
    }

    public void setVehicleCondition(Condition vehicleCondition) {
        this.vehicleCondition = vehicleCondition;
    }

    public String getBodyStyle() {
        return bodyStyle;
    }

    public void setBodyStyle(String bodyStyle) {
        this.bodyStyle = bodyStyle;
    }

    public Blob getPicture() {
        return picture;
    }

    public void setPicture(Blob picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Trim getTrim() {
        return trim;
    }

    public void setTrim(Trim trim) {
        this.trim = trim;
    }

    public BigDecimal getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(BigDecimal salesPrice) {
        this.salesPrice = salesPrice;
    }

    public BigDecimal getMSRP() {
        return MSRP;
    }

    public void setMSRP(BigDecimal MSRP) {
        this.MSRP = MSRP;
    }

    public boolean isFeatured() {
        return featured;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.VIN);
        hash = 83 * hash + Objects.hashCode(this.model);
        hash = 83 * hash + Objects.hashCode(this.vehicleCondition);
        hash = 83 * hash + Objects.hashCode(this.bodyStyle);
        hash = 83 * hash + Objects.hashCode(this.picture);
        hash = 83 * hash + Objects.hashCode(this.description);
        hash = 83 * hash + Objects.hashCode(this.trim);
        hash = 83 * hash + Objects.hashCode(this.salesPrice);
        hash = 83 * hash + Objects.hashCode(this.MSRP);
        hash = 83 * hash + (this.featured ? 1 : 0);
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
        final Vehicle other = (Vehicle) obj;
        if (this.featured != other.featured) {
            return false;
        }
        if (!Objects.equals(this.VIN, other.VIN)) {
            return false;
        }
        if (!Objects.equals(this.bodyStyle, other.bodyStyle)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.model, other.model)) {
            return false;
        }
        if (!Objects.equals(this.vehicleCondition, other.vehicleCondition)) {
            return false;
        }
        if (!Objects.equals(this.picture, other.picture)) {
            return false;
        }
        if (!Objects.equals(this.trim, other.trim)) {
            return false;
        }
        if (!Objects.equals(this.salesPrice, other.salesPrice)) {
            return false;
        }
        if (!Objects.equals(this.MSRP, other.MSRP)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Vehicle{" + "VIN=" + VIN + ", model=" + model + ", vehicleCondition=" + vehicleCondition + ", bodyStyle=" + bodyStyle + ", picture=" + picture + ", description=" + description + ", trim=" + trim + ", salesPrice=" + salesPrice + ", MSRP=" + MSRP + ", featured=" + featured + '}';
    }
}
