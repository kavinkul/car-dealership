package com.sg.cardealership.dao;

import com.sg.cardealership.models.Make;
import java.util.List;

public interface MakeDao {
    List<Make> getAllMakes();

    Make getMake(int id);

    void addMake(Make make);

    void removeMake(int id);
}
