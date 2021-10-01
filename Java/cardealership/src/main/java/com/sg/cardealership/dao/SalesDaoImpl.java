/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cardealership.dao;

import com.sg.cardealership.models.Sale;
import com.sg.cardealership.models.User;
import com.sg.cardealership.models.Vehicle;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author kavin
 */
@Repository
public class SalesDaoImpl implements SalesDao {

    @Autowired
    VehicleDao vehicleDao;

    @Autowired
    UserDao userDao;

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public List<Sale> getAllSales() {
       final String SELECT_ALL_EMAIL_FROM_SALES = "SELECT UserEmail FROM Sales ";
       Set<String> allSellerEmails = new HashSet<String>(jdbc.query(SELECT_ALL_EMAIL_FROM_SALES, new EmailMapper()));
       List<Sale> allSales = new ArrayList<>();
       for (String userEmail : allSellerEmails) {
           allSales.add(getSale(userDao.getUser(userEmail)));
       }
       return allSales;
    }

    // Takes advantage of vehicle dao to get Vehicle object with matching VIN

    @Override
    public Sale getSale(User user) {
        // Query all VIN with specific userEmail equals to user's

        final String SELECT_ALL_SALES_FROM_USER = "SELECT VIN FROM Sales "
                + "WHERE UserEmail = ?";

        List<String> VINs = jdbc.query(SELECT_ALL_SALES_FROM_USER, new VINMapper(), user.getEmail());
        List<Vehicle> allVehiclesSoldByUser = new ArrayList<>();

        for (String VIN : VINs){
            allVehiclesSoldByUser.add(vehicleDao.getVehicle(VIN));
        }

        return new Sale(user, allVehiclesSoldByUser);
    }

    @Override
    @Transactional
    public void addSale(User user, Vehicle vehicle) {
        final String INSERT_SALE = "INSERT INTO Sales "
                + "VALUES(?,?)";
        jdbc.update(INSERT_SALE,
                user.getEmail(),
                vehicle.getVIN());
    }

    // Only used in unit testing

    @Override
    @Transactional
    public void removeSale(Sale sale) {
        final String DELETE_SALE = "DELETE FROM Sales "
                + "WHERE UserEmail = ?";
        jdbc.update(DELETE_SALE, sale.getUser().getEmail());
    }

    public static final class EmailMapper implements RowMapper<String> {
        @Override
        public String mapRow(ResultSet rs, int index) throws SQLException {
            String userEmail = rs.getString("UserEmail");
            return userEmail;
        }
    }

    public static final class VINMapper implements RowMapper<String> {
        @Override
        public String mapRow(ResultSet rs, int index) throws SQLException {
            String VIN = rs.getString("VIN");
            return VIN;
        }
    }
}
