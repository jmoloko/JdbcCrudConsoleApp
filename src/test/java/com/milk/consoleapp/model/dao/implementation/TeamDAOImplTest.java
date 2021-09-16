package com.milk.consoleapp.model.dao.implementation;

import com.milk.consoleapp.DBConnector;
import com.milk.consoleapp.model.entity.Developer;
import com.milk.consoleapp.model.entity.Skill;
import com.milk.consoleapp.model.entity.Team;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Jack Milk
 */
public class TeamDAOImplTest {

//    DBConnector conn = DBConnector.getConnector();
//    TeamDAOImpl teamDAO = new TeamDAOImpl(conn.getConnect());
//
//    @Test
//    public void save() {
//        Developer dev = new Developer();
//        dev.setId(5);
//        dev.setFirstName("Test");
//        dev.setLastName("Developer");
//        dev.addSkill(new Skill(3, "C#"));
//
//        Team tm = new Team();
//        tm.setName("Banking");
//        tm.addDev(dev);
//        System.out.println(teamDAO.save(tm));
//    }
//
//    @Test
//    public void getById() {
//        System.out.println(teamDAO.getById(1));
//    }
//
//    @Test
//    public void getAll() {
//        for (Team tm : teamDAO.getAll()){
//            System.out.println(tm);
//        }
//    }
//
//    @Test
//    public void update() {
//        Developer dev = new Developer();
//        dev.setId(1);
//        dev.setFirstName("John");
//        dev.setLastName("Doe");
//        dev.addSkill(new Skill(1, "Java"));
//        dev.addSkill(new Skill(3, "C#"));
//
//        Team tm = new Team();
//        tm.setId(1);
//        tm.setName("UI Design");
//        tm.addDev(dev);
//
//        System.out.println(teamDAO.update(tm));
//    }
//
//    @Test
//    public void delete() {
//        teamDAO.deleteById(2);
//    }
}