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
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class VehicleDaoImpl implements VehicleDao {
    @Autowired
    JdbcTemplate jdbc;
            
    @Override
    public List<Vehicle> getAllVehicles() {
        final String SELECT_ALL_VEHICLES 
                = "SELECT v.VIN, v.BodyStyle, v.Picture, v.`Description`, v.SalesPrice, v.MSRP, v.Featured, "
                    + "t.TrimID, t.`Name` TrimName, t.InteriorColor, t.ExteriorColor, t.Transmission, "
                    + "vc.VehicleConditionID, vc.Mileage, vc.MileageUnit, vc.`Type`, "
                    + "m.ModelId, m.`Name` ModelName, m.`Year` ModelYear, m.DateAdded ModelDateAdded, m.UserEmail ModelUserEmail, "
                    + "mk.MakeId, mk.`Name` MakeName, mk.DateAdded MakeDateAdded, mk.UserEmail MakeUserEmail "
                    + "FROM Vehicle v "
                    + "JOIN `Trim` t ON v.TrimID = t.TrimID "
                    + "JOIN VehicleCondition vc ON v.VehicleConditionID = vc.VehicleConditionID "
                    + "JOIN Model m ON v.ModelID = m.ModelID "
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
        final String SELECT_VEHICLE_BY_VIN 
                = "SELECT v.VIN, v.BodyStyle, v.Picture, v.`Description`, v.SalesPrice, v.MSRP, v.Featured, "
                    + "t.TrimID, t.`Name` TrimName, t.InteriorColor, t.ExteriorColor, t.Transmission, "
                    + "vc.VehicleConditionID, vc.Mileage, vc.MileageUnit, vc.`Type`, "
                    + "m.ModelId, m.`Name` ModelName, m.`Year` ModelYear, m.DateAdded ModelDateAdded, m.UserEmail ModelUserEmail, "
                    + "mk.MakeId, mk.`Name` MakeName, mk.DateAdded MakeDateAdded, mk.UserEmail MakeUserEmail "
                    + "FROM Vehicle v "
                    + "JOIN `Trim` t ON v.TrimID = t.TrimID "
                    + "JOIN VehicleCondition vc ON v.VehicleConditionID = vc.VehicleConditionID "
                    + "JOIN Model m ON v.ModelID = m.ModelID "
                    + "JOIN Make mk ON m.MakeId = mk.MakeId "
                    + "JOIN `User` mu ON m.UserEmail = mu.Email "
                    + "JOIN `User` mku ON mk.UserEmail = mku.Email "
                    + "WHERE v.VIN = ?";
        try {
            return jdbc.queryForObject(SELECT_VEHICLE_BY_VIN,
                                       new VehicleMapper(new VehicleConditionMapper(), 
                                                         new TrimMapper(), 
                                                         new ModelMapper(new MakeMapper())),
                                        vin);
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    @Transactional
    public void addVehicle(Vehicle vehicle) {
        final String INSERT_VEHICLE 
                = "INSERT INTO Vehicle VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        // Insert VehicleCondition
        Condition condition = vehicle.getVehicleCondition();
        addVehicleCondition(condition);
        
        // Insert Trim
        Trim trim = vehicle.getTrim();
        addTrim(trim);
        
        // Insert Vehicle
        jdbc.update(INSERT_VEHICLE,
                    vehicle.getVIN(),
                    vehicle.getModel().getId(),
                    condition.getId(),
                    vehicle.getBodyStyle(),
                    vehicle.getPicture(),
                    vehicle.getDescription(),
                    trim.getId(),
                    vehicle.getSalesPrice(),
                    vehicle.getMSRP(),
                    vehicle.isFeatured());
    }

    @Override
    public void removeVehicle(String vin) {
        final String DELETE_VEHICLE = "DELETE FROM Vehicle WHERE VIN = ?";
        jdbc.update(DELETE_VEHICLE, vin);
    }

    @Override
    public void editVehicle(Vehicle vehicle) {
        final String UPDATE_VEHICLE 
                = "UPDATE Vehicle SET ModelId = ?, VehicleConditionID = ?, "
                    + "BodyStyle = ?, Picture = ?, `Description` = ?, "
                    + "TrimId = ?, SalesPrice = ?, MSRP = ?, Featured = ? "
                    + "WHERE VIN = ?";
        
        jdbc.update(UPDATE_VEHICLE,
                    vehicle.getModel().getId(),
                    vehicle.getVehicleCondition().getId(),
                    vehicle.getBodyStyle(),
                    vehicle.getPicture(),
                    vehicle.getDescription(),
                    vehicle.getTrim().getId(),
                    vehicle.getSalesPrice(),
                    vehicle.getMSRP(),
                    vehicle.isFeatured(),
                    vehicle.getVIN());
    }

    @Override
    public List<Make> getAllMakes() {
        final String SELECT_MAKES = "SELECT MakeID, `Name` MakeName, DateAdded MakeDateAdded, UserEmail MakeUserEmail FROM Make";
        return jdbc.query(SELECT_MAKES,
                          new MakeMapper());
    }

    @Override
    public Make getMake(int id) {
        final String SELECT_MAKE_BY_ID 
                = "SELECT MakeID, `Name` MakeName, DateAdded MakeDateAdded, UserEmail MakeUserEmail FROM Make "
                    + "WHERE MakeID = ?";
        
        try {
            return jdbc.queryForObject(SELECT_MAKE_BY_ID,
                                       new MakeMapper(),
                                       id);
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    @Transactional
    public void addMake(Make make) {
        final String INSERT_MAKE
                = "INSERT INTO Make (`Name`, DateAdded, UserEmail) VALUES (?, ?, ?)";
        
        jdbc.update(INSERT_MAKE,
                    make.getName(),
                    make.getDateAdded(),
                    make.getUser());
        
        int makeId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        make.setId(makeId);
    }

    @Override
    public void removeMake(int id) {
        final String DELETE_MAKE = "DELETE FROM Make WHERE MakeID = ?";
        jdbc.update(DELETE_MAKE, id);
    }

    @Override
    public List<Model> getAllModels() {
        final String SELECT_MODELS
                = "SELECT m.ModelID, m.`Name` ModelName, m.`Year` ModelYear, m.DateAdded ModelDateAdded, m.UserEmail ModelUserEmail, "
                    + "mk.MakeID, mk.`Name` MakeName, mk.DateAdded MakeDateAdded, mk.UserEmail MakeUserEmail "
                    + "FROM Model m JOIN Make mk ON m.MakeId = mk.MakeId ";
        
        return jdbc.query(SELECT_MODELS, new ModelMapper(new MakeMapper()));
    }

    @Override
    public Model getModel(int id) {
        final String SELECT_MODEL_BY_ID 
                = "SELECT m.ModelID, m.`Name` ModelName, m.`Year` ModelYear, m.DateAdded ModelDateAdded, m.UserEmail ModelUserEmail, "
                    + "mk.MakeID, mk.`Name` MakeName, mk.DateAdded MakeDateAdded, mk.UserEmail MakeUserEmail "
                    + "FROM Model m JOIN Make mk ON m.MakeId = mk.MakeId "
                    + "WHERE m.ModelId = ?";
        try {
            return jdbc.queryForObject(SELECT_MODEL_BY_ID,
                                       new ModelMapper(new MakeMapper()),
                                       id);
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    @Transactional
    public void addModel(Model model) {
        final String INSERT_MODEL
                = "INSERT INTO Model (`Name`, `Year`, DateAdded, UserEmail, MakeID) VALUES (?, ?, ?, ?, ?)";
        
        jdbc.update(INSERT_MODEL,
                    model.getName(),
                    model.getYear(),
                    model.getDateAdded(),
                    model.getUser(),
                    model.getMake().getId());
        
        int modelId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        model.setId(modelId);
    }

    @Override
    public void removeModel(int id) {
        final String DELETE_MODEL = "DELETE FROM Model WHERE ModelID = ?";
        jdbc.update(DELETE_MODEL, id);
    }

    @Override
    public List<Trim> getAllTrims() {
        final String SELECT_TRIMS = "SELECT TrimID, `Name` TrimName, InteriorColor, ExteriorColor, Transmission FROM `Trim`";
        return jdbc.query(SELECT_TRIMS,
                          new TrimMapper());
    }

    @Override
    public Trim getTrim(int id) {
        final String SELECT_TRIM_BY_ID 
                = "SELECT TrimID, `Name` TrimName, InteriorColor, ExteriorColor, Transmission FROM `Trim` "
                    + "WHERE TrimID = ?";
        try {
            return jdbc.queryForObject(SELECT_TRIM_BY_ID,
                                       new TrimMapper(),
                                       id);
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    @Transactional
    public void addTrim(Trim trim) {
        final String INSERT_VEHICLE_TRIM 
                = "INSERT INTO `Trim` (`Name`, InteriorColor, ExteriorColor, Transmission) VALUES (?, ?, ?, ?)";
        
        jdbc.update(INSERT_VEHICLE_TRIM,
                    trim.getName(),
                    trim.getInteriorColor(),
                    trim.getExteriorColor(),
                    trim.getTransmission().toString());
        
        int trimId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        trim.setId(trimId);
    }

    @Override
    public void removeTrim(int id) {
        final String DELETE_TRIM = "DELETE FROM `Trim` WHERE TrimID = ?";
        jdbc.update(DELETE_TRIM, id);
    }

    @Override
    public List<Condition> getAllVehicleConditions() {
        final String SELECT_VEHICLECONDITIONS = "SELECT * FROM VehicleCondition";
        return jdbc.query(SELECT_VEHICLECONDITIONS,
                          new VehicleConditionMapper());
    }

    @Override
    public Condition getVehicleCondition(int id) {
        final String SELECT_CONDITION_BY_ID = "SELECT * FROM VehicleCondition WHERE VehicleConditionID = ?";
        
        try {
            return jdbc.queryForObject(SELECT_CONDITION_BY_ID,
                                       new VehicleConditionMapper(),
                                       id);
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    @Transactional
    public void addVehicleCondition(Condition condition) {
        final String INSERT_VEHICLE_CONDITION 
                = "INSERT INTO VehicleCondition (Mileage, MileageUnit, `Type`) VALUES (?, ?, ?)";
        
        jdbc.update(INSERT_VEHICLE_CONDITION,
                    condition.getMileage(),
                    condition.getUnit().toString(),
                    condition.getType().toString());
        
        int conditionId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        condition.setId(conditionId);
    }

    @Override
    public void removeVehicleCondition(int id) {
        final String DELETE_VehicleCondition = "DELETE FROM VehicleCondition WHERE VehicleConditionID = ?";
        jdbc.update(DELETE_VehicleCondition, id);
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
                                 rs.getString("InteriorColor"),
                                 rs.getString("ExteriorColor"),
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
            
            model.setId(rs.getInt("ModelID"));
            
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
