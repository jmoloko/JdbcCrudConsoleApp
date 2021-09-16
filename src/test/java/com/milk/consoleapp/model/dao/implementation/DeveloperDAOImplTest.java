package com.milk.consoleapp.model.dao.implementation;

import com.milk.consoleapp.DBConnector;
import com.milk.consoleapp.model.entity.Developer;
import com.milk.consoleapp.model.entity.Skill;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Jack Milk
 */
public class DeveloperDAOImplTest {

//    DBConnector conn = DBConnector.getConnector();
//    DeveloperDAOImpl developerDAO = new DeveloperDAOImpl(conn.getConnect());
//
//    @Test
//    public void getById() {
//        Developer dev = developerDAO.getById(1);
//        System.out.println(dev);
//    }
//
//    @Test
//    public void save() {
//        Developer dev = new Developer();
//        dev.setFirstName("Test");
//        dev.setLastName("Developer");
//        dev.addSkill(new Skill(6, "PHP"));
//        System.out.println(developerDAO.save(dev));
//    }
//
//    @Test
//    public void update() {
//        Developer dev = new Developer();
//        dev.setId(5);
//        dev.setFirstName("Test");
//        dev.setLastName("Developer");
//        dev.addSkill(new Skill(3, "C#"));
//        System.out.println(developerDAO.update(dev));
//    }
//
//    @Test
//    public void getAll() {
//        for (Developer dev : developerDAO.getAll()){
//            System.out.println(dev);
//        }
//    }
//
//    @Test
//    public void delete(){
//        developerDAO.deleteById(4);
//    }
}