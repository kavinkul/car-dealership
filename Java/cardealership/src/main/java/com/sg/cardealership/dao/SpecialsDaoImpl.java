/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.cardealership.dao;

import com.sg.cardealership.models.Special;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author agrah
 */
@Repository
public class SpecialsDaoImpl implements SpecialsDao{
    
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public List<Special> getAllSpecials() {
        final String SELECT_ALL_SPECIALS = "SELECT * FROM Specials";
        return jdbc.query(SELECT_ALL_SPECIALS, new SpecialMapper());
    }

    @Override
    public Special getSpecial(int id) {
         try {
            final String SELECT_SPECIAL_BY_ID = "SELECT * FROM Specials WHERE SpecialsId = ?";
            return jdbc.queryForObject(SELECT_SPECIAL_BY_ID, new SpecialMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public Special addSpecial(Special special) {
         final String INSERT_SPECIAL = "INSERT INTO specials(title, description) "
                + "VALUES(?,?)";
        jdbc.update(INSERT_SPECIAL,
                special.getTitle(),
                special.getDescription());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        special.setId(newId);
        return special;
    }

    @Override
    public void removeSpecial(int id) {
        final String DELETE_SPECIAL = "DELETE FROM Specials WHERE SpecialsId = ?";
        jdbc.update(DELETE_SPECIAL, id);
    }

    
    //edit out due to no edit function included in wireframe --Austin G. 09/27 10:45pm
    /*
    @Override
    public void editSpecial(Special special) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    */
    
    public static final class SpecialMapper implements RowMapper<Special>{
        
        @Override
        public Special mapRow(ResultSet rs, int index) throws SQLException {
            Special special = new Special(rs.getInt("SpecialsId"), 
                                    rs.getString("title"), 
                                rs.getString("description"));

            return special;
        }
    } 
    
}
