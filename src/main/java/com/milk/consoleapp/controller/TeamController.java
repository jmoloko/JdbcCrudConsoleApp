package com.milk.consoleapp.controller;


import com.milk.consoleapp.model.dao.TeamDAO;
import com.milk.consoleapp.model.dao.implementation.TeamDAOImpl;
import com.milk.consoleapp.model.entity.Team;

import java.util.List;



/**
 * @author Jack Milk
 */
public class TeamController {

    private final TeamDAO team;

    public TeamController(TeamDAO dao) {
        this.team = dao;
    }

    public List<Team> viewAllTeams(){
        return team.getAll();
    }

    public Team viewTeamByID(Integer id){
        return team.getById(id);
    }

    public Team save(Team newTeam){
        return team.save(newTeam);
    }

    public Team updateTeamForId(Team newTeam) {
        return team.update(newTeam);
    }

    public void deleteTeamById(Integer id){
        team.deleteById(id);
    }

}
