/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cardealership.models;

/**
 *
 * @author kavin
 */
public enum Role {
    ADMIN("Admin"), SALES("Sales"), DISABLED("Disabled");

    private String value;

    Role(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
