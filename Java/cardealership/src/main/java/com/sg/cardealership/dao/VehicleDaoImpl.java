package com.sg.cardealership.dao;

import com.sg.cardealership.models.Condition;
import com.sg.cardealership.models.Make;
import com.sg.cardealership.models.MileageUnit;
import com.sg.cardealership.models.Model;
import com.sg.cardealership.models.Transmission;
import com.sg.cardealership.models.Trim;
import com.sg.cardealership.models.Type;
import com.sg.cardealership.models.Vehicle;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class VehicleDaoImpl implements VehicleDao {
    @Autowired
    JdbcTemplate jdbc;
            
    @Override
    public List<Vehicle> getAllVehicles() {
        final String SELECT_ALL_VEHICLES 
                = "SELECT v.VIN, v.BodyStyle, v.Picture, v.`Description`, v.SalesPrice, v.MSRP, v.Featured, "
                    + "t.`Name` TrimName, ic.`Name` InteriorColorName, ec.`Name` ExteriorColorName, t.Transmission, "
                    + "m.`Name` ModelName, m.`Year`, m.DateAdded ModelDateAdded, m.UserEmail ModelUserEmail, "
                    + "mk.`Name` MakeName, mk.DateAdded MakeDateAdded, mk.UserEmail MakeUserEmail "
                    + "FROM Vehicle v "
                    + "JOIN `Trim` t ON v.TrimID = t.TrimID "
                    + "JOIN Color ic ON t.InteriorColorID = ic.ColorID "
                    + "JOIN Color ec ON t.ExteriorColorID = ec.ColorID "
                    + "JOIN Model m ON v.ModelID = m.ModelID "
                    + "JOIN ModelYear my ON m.`Year` = my.`Year` "
                    + "JOIN Make mk ON m.MakeId = mk.MakeId "
                    + "JOIN `User` mu ON m.UserEmail = mu.Email "
                    + "JOIN `User` mku ON mk.UserEmail = mku.Email";
        
        return jdbc.query(SELECT_ALL_VEHICLES,
                          new VehicleMapper(new VehicleConditionMapper(), 
                                            new TrimMapper(), 
                                            new ModelMapper(new MakeMapper())));
    }

    @Override
    public Vehicle getVehicle(String vin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addVehicle(Vehicle vehicle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeVehicle(String vin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void editVehicle(Vehicle vehicle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Make> getAllMakes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Make getMake(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addMake(Make make) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeMake(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Model> getAllModels() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Model getModel(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addModel(Model model) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeModel(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Trim> getAllTrims() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Trim getTrim(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addTrim(Trim trim) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeTrim(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Condition> getAllVehicleConditions() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Condition getVehicleCondition(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addVehicleCondition(Condition condition) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeVehicleCondition(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static final class VehicleConditionMapper implements RowMapper<Condition> {
        @Override
        public Condition mapRow(ResultSet rs, int rowNum) throws SQLException {
            
            MileageUnit mileageUnit = null;
            
            if (rs.getString("MileageUnit").equalsIgnoreCase("Kilometers")) {
                mileageUnit = MileageUnit.KILOMETERS;
            }
            
            if (rs.getString("MileageUnit").equalsIgnoreCase("Miles")) {
                mileageUnit = MileageUnit.MILES;
            }
            
            Type type = null;
            
            if (rs.getString("Type").equalsIgnoreCase("New")) {
                type = Type.NEW;
            }
            
            if (rs.getString("Type").equalsIgnoreCase("Used")) {
                type = Type.USED;
            }
            
            Condition condition = new Condition(rs.getInt("Mileage"),
                                                mileageUnit,
                                                type);
            condition.setId(rs.getInt("VehicleConditionID"));
            
            return condition;
        }
    
    }
    
    
    public static final class TrimMapper implements RowMapper<Trim> {
        @Override
        public Trim mapRow(ResultSet rs, int rowNum) throws SQLException {
            Transmission transmission = null;
            
            if (rs.getString("Transmission").equalsIgnoreCase("Automatic")) {
                transmission = Transmission.AUTOMATIC;
            }
            
            if (rs.getString("Transmission").equalsIgnoreCase("Manual")) {
                transmission = Transmission.MANUAL;
            }
            
            Trim trim = new Trim(rs.getString("TrimName"),
                                 rs.getString("InteriorColorName"),
                                 rs.getString("ExteriorColorName"),
                                 transmission);
            
            trim.setId(rs.getInt("TrimID"));
            
            return trim;
        }
    }
    
    public static final class MakeMapper implements RowMapper<Make> {
        @Override
        public Make mapRow(ResultSet rs, int rowNum) throws SQLException {
            Make make = new Make(rs.getString("MakeName"), rs.getDate("MakeDateAdded").toLocalDate(), rs.getString("MakeUserEmail"));
            make.setId(rs.getInt("MakeID"));
            
            return make;
        }
    }
    
    
    public static final class ModelMapper implements RowMapper<Model> {
        private final MakeMapper makeMapper;

        public ModelMapper(MakeMapper makeMapper) {
            this.makeMapper = makeMapper;
        }

        @Override
        public Model mapRow(ResultSet rs, int rowNum) throws SQLException {
            Make make = this.makeMapper.mapRow(rs, rowNum);
            
            Model model = new Model(rs.getString("ModelName"), 
                                    rs.getInt("ModelYear"), 
                                    rs.getDate("ModelDateAdded").toLocalDate(), 
                                    rs.getString("ModelUserEmail"),
                                    make);
            
            return model;
        }
    }
    
    public static final class VehicleMapper implements RowMapper<Vehicle> {
        private final VehicleConditionMapper vehicleConditionMapper;
        private final TrimMapper trimMapper;
        private final ModelMapper modelMapper;

        public VehicleMapper(VehicleConditionMapper vehicleConditionMapper, TrimMapper trimMapper, ModelMapper modelMapper) {
            this.vehicleConditionMapper = vehicleConditionMapper;
            this.trimMapper = trimMapper;
            this.modelMapper = modelMapper;
        }

        @Override
        public Vehicle mapRow(ResultSet rs, int rowNum) throws SQLException {
            Vehicle vehicle = new Vehicle(rs.getString("VIN"),
                                          rs.getString("BodyStyle"),
                                          rs.getBlob("Picture"),
                                          rs.getString("Description"),
                                          rs.getBigDecimal("SalesPrice"),
                                          rs.getBigDecimal("MSRP"),
                                          rs.getBoolean("Featured"));
            
            Condition condition = this.vehicleConditionMapper.mapRow(rs, rowNum);
            vehicle.setVehicleCondition(condition);
            
            Model model = this.modelMapper.mapRow(rs, rowNum);
            vehicle.setModel(model);
            
            Trim trim = this.trimMapper.mapRow(rs, rowNum);
            vehicle.setTrim(trim);
            
            return vehicle;
        }
    }
}
