package com.milk.consoleapp.controller;

import com.milk.consoleapp.DBConnector;
import com.milk.consoleapp.model.dao.DeveloperDAO;
import com.milk.consoleapp.model.dao.TeamDAO;
import com.milk.consoleapp.model.dao.implementation.DeveloperDAOImpl;
import com.milk.consoleapp.model.dao.implementation.TeamDAOImpl;
import com.milk.consoleapp.model.entity.Developer;
import com.milk.consoleapp.model.entity.Skill;
import com.milk.consoleapp.model.entity.Team;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Jack Milk
 */
public class TeamController {

    private final DBConnector conn = DBConnector.getConnector();
    private final DeveloperDAO developer = new DeveloperDAOImpl(conn.getConnect());
    private final TeamDAO team = new TeamDAOImpl(conn.getConnect());

    public void viewAllTeams(){
        for (Team team: team.getAll()){
            teamToConsole(team);
        }
        System.out.println();
    }

    public void viewTeamByID(String id){
        try {
            if (isDigit(id)) {
                Team teamById = team.getById(Integer.parseInt(id));
                teamToConsole(teamById);
            } else {
                System.out.println("Enter a digital designation id");
            }
        } catch (NullPointerException e){
            System.out.println("This ID does not exist");
        }
    }

    public void save(String name, List<String> developers){
        List<Developer> developerList = new ArrayList<>();
        for (String dev: developers){
            Developer newDeveloper = developer.getById(Integer.parseInt(dev));
            developerList.add(newDeveloper);
        }
        Team newTeam = team.save(new Team(name, developerList));
        viewAllTeams();
        System.out.println();
    }

    public void updateTeamForId(String id, String name, List<String> developers){
        try {
            if (isDigit(id)) {
                List<Developer> developerList = new ArrayList<>();
                for (String dev: developers){
                    Developer developerUpd = developer.getById(Integer.parseInt(dev));
                    developerList.add(developerUpd);
                }
                team.update(new Team(Integer.parseInt(id), name, developerList));
                viewAllTeams();
            } else {
                System.out.println("Enter a digital designation id");
            }
        } catch (NullPointerException e) {
            System.out.println("This ID does not exist");
        }
    }

    public void deleteTeamById(String id){
        try {
            if (isDigit(id)) {
                int teamId = Integer.parseInt(id);
                team.deleteById(teamId);
            } else {
                System.out.println("Enter a digital designation id");
            }
        } catch (NullPointerException e) {
            System.out.println("This ID does not exist");
        }
    }

    private static boolean isDigit(String enterString) {
        try {
            Integer.parseInt(enterString.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void teamToConsole(Team team){
        System.out.print("| Team -> id:"+ team.getId() + " " + team.getName());
        System.out.print(" | Developers -> ");
        team.getDevelopers().forEach(d -> System.out.print(d.getId() + ":" + d.getFirstName() + " " + d.getLastName() + " "));
        System.out.print(" | All Skills -> ");
        Set<String> skills = new HashSet<>();
        for (Developer dev: team.getDevelopers()){
            for (Skill skill: dev.getSkills()){
                skills.add(skill.getName());
            }
        }
        skills.forEach(s -> System.out.print(s + " "));
        System.out.println("|");
    }
}
