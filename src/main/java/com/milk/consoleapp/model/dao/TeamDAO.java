package com.milk.consoleapp.model.dao;

import com.milk.consoleapp.model.entity.Team;

import java.util.List;

/**
 * @author Jack Milk
 */
public interface TeamDAO extends GenericDAO<Team, Integer>{
    List<Team> getAll();
    void deleteById(Integer id);
    Team getById(Integer id);
    Team save(Team team);
    Team update(Team team);
}
