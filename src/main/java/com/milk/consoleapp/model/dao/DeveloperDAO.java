package com.milk.consoleapp.model.dao;

import com.milk.consoleapp.model.entity.Developer;

import java.util.List;

/**
 * @author Jack Milk
 */
public interface DeveloperDAO extends GenericDAO<Developer, Integer>{
    List<Developer> getAll();
    void deleteById(Integer id);
    Developer getById(Integer id);
    Developer save(Developer developer);
    Developer update(Developer developer);
}
