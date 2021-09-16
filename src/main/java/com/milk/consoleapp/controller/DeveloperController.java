package com.milk.consoleapp.controller;

import com.milk.consoleapp.DBConnector;
import com.milk.consoleapp.model.dao.DeveloperDAO;
import com.milk.consoleapp.model.dao.SkillDAO;
import com.milk.consoleapp.model.dao.implementation.DeveloperDAOImpl;
import com.milk.consoleapp.model.dao.implementation.SkillDAOImpl;
import com.milk.consoleapp.model.entity.Developer;
import com.milk.consoleapp.model.entity.Skill;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jack Milk
 */
public class DeveloperController {

    private final DBConnector conn = DBConnector.getConnector();
    private final DeveloperDAO developer = new DeveloperDAOImpl(conn.getConnect());
    private final SkillDAO skillDAO = new SkillDAOImpl(conn.getConnect());

    public void viewAllDevelopers(){
        for (Developer dev: developer.getAll()){
            developerToConsole(dev);
        }
        System.out.println();
    }

    public void viewDeveloperByID(String id){
        try {
            if (isDigit(id)) {
                Developer dev = developer.getById(Integer.parseInt(id));
                developerToConsole(dev);
            } else {
                System.out.println("Enter a digital designation id");
            }
        } catch (NullPointerException e){
            System.out.println("This ID does not exist");
        }
    }

    public void save(String firstName, String lastName, List<String> skills){
        List<Skill> skillList = new ArrayList<>();
        for (String sk: skills){
            Skill skill = skillDAO.getById(Integer.parseInt(sk));
            skillList.add(skill);
        }
        Developer newDeveloper = developer.save(new Developer(firstName, lastName, skillList));
        viewAllDevelopers();
        System.out.println();
    }

    public void updateDeveloperForId(String id, String firstName, String lastName, List<String> skills){
        try {
            if (isDigit(id)) {
                List<Skill> skillList = new ArrayList<>();
                for (String sk: skills){
                    Skill skill = skillDAO.getById(Integer.parseInt(sk));
                    skillList.add(skill);
                }
                developer.update(new Developer(Integer.parseInt(id), firstName, lastName, skillList));
                viewAllDevelopers();
            } else {
                System.out.println("Enter a digital designation id");
            }
        } catch (NullPointerException e) {
            System.out.println("This ID does not exist");
        }
    }

    public void deleteDeveloperById(String id){
        try {
            if (isDigit(id)) {
                int devId = Integer.parseInt(id);
                developer.deleteById(devId);
            } else {
                System.out.println("Enter a digital designation id");
            }
        } catch (NullPointerException e) {
            System.out.println("This ID does not exist");
        }
    }

    private boolean isDigit(String enterString) {
        try {
            Integer.parseInt(enterString.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void developerToConsole(Developer dev){
        System.out.print("| Developer -> id:"+ dev.getId() + " " + dev.getFirstName() + " " + dev.getLastName());
        System.out.print(" | Skills -> ");
        dev.getSkills().forEach(s -> System.out.print(s.getId() + ":" + s.getName() + " "));
        System.out.println("|");
    }
}
