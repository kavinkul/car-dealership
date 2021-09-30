package com.sg.cardealership.service;

import com.sg.cardealership.dao.SpecialsDao;
import com.sg.cardealership.dao.UserDao;
import com.sg.cardealership.dao.VehicleDao;
import com.sg.cardealership.models.Make;
import com.sg.cardealership.models.Model;
import com.sg.cardealership.models.Role;
import com.sg.cardealership.models.Special;
import com.sg.cardealership.models.User;
import com.sg.cardealership.models.Vehicle;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class AdminService {
    @Autowired
    VehicleDao vehicleDao;

    @Autowired
    UserDao userDao;

    @Autowired
    SpecialsDao specialsDao;

    public List<Vehicle> getAllVehicles() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Vehicle> searchVehicles() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void addMake(String name, String email) throws AdminServiceInvalidDataException, SQLException {
        Make make = new Make(name, LocalDate.now(), email);
        if (userDao.getUser(email).getRole() != Role.ADMIN)
            throw new AdminServiceInvalidDataException("User is not admin.");
        vehicleDao.addMake(make);
    }

    public List<Make> getAllMakes() {
        return vehicleDao.getAllMakes();
    }

    public void addModel(String makeName, String modelName, int year, String email) throws AdminServiceInvalidDataException, SQLException {
        Make make = vehicleDao.getAllMakes().stream().filter((m) -> m.getName().equalsIgnoreCase(makeName)).collect(Collectors.toList()).get(0);
        LocalDate todayDate = LocalDate.now();
        if (year < 2000 || year > todayDate.getYear() + 1)
            throw new AdminServiceInvalidDataException("Year is not in between 2000 and next year.");
        if (userDao.getUser(email).getRole() != Role.ADMIN)
            throw new AdminServiceInvalidDataException("User is not admin.");
        Model model = new Model(modelName, year, todayDate, email, make);
        vehicleDao.addModel(model);
    }

    public List<Model> getAllModels() {
        return vehicleDao.getAllModels();
    }

    public List<Special> getAllSpecials() {
        return specialsDao.getAllSpecials();
    }

    public void addSpecial(Special special){
        specialsDao.addSpecial(special);
    }
    
    public void removeSpecial(int id){
        specialsDao.removeSpecial(id);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public void addUser(String firstName, String lastName, String email, String roleString, String password, String confirmPassword) throws AdminServiceInvalidDataException, SQLException {
        if (!password.equals(confirmPassword))
            throw new AdminServiceInvalidDataException("Passwords do not match!");
        Role role;
        try {
            role = Role.valueOf(roleString.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new AdminServiceInvalidDataException("This role does not exist!");
        }
        User user = new User(email, firstName, lastName, password, role);
        userDao.addUser(user);
    }
}
