/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cardealership.service;

/**
 *
 * @author kavin
 */
public class AdminServiceInvalidDataException extends Exception {
    public AdminServiceInvalidDataException(String message) {
        super(message);
    }

    public AdminServiceInvalidDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
