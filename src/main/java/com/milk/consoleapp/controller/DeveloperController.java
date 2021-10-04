package com.milk.consoleapp.controller;

import com.milk.consoleapp.model.dao.DeveloperDAO;
import com.milk.consoleapp.model.dao.implementation.DeveloperDAOImpl;
import com.milk.consoleapp.model.entity.Developer;

import java.util.List;

/**
 * @author Jack Milk
 */
public class DeveloperController {

    private final DeveloperDAO developer;


    public DeveloperController(DeveloperDAO dao) {
        this.developer = dao;
    }

    public List<Developer> viewAllDevelopers(){
        return developer.getAll();
    }

    public Developer viewDeveloperByID(Integer id){
        return developer.getById(id);
    }

    public Developer save(Developer newDeveloper){
        return developer.save(newDeveloper);
    }

    public Developer updateDeveloperForId(Developer newDeveloper) {
        return developer.update(newDeveloper);
    }

    public void deleteDeveloperById(Integer id){
        developer.deleteById(id);
    }

}
