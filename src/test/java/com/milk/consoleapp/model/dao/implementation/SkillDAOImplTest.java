package com.milk.consoleapp.model.dao.implementation;

import com.milk.consoleapp.DBConnector;
import com.milk.consoleapp.model.entity.Skill;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.assertj.core.api.BDDAssumptions.given;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @author Jack Milk
 */

@RunWith(MockitoJUnitRunner.class)
public class SkillDAOImplTest {

    @Mock
    private SkillDAOImpl skillDAO;

    @Test
    public void testGetByID() {
        skillDAO.getById(1);
        verify(skillDAO).getById(1);

    }


    @Test
    public void getById() {
        when(skillDAO.getById(1)).thenReturn(testSkill());
        String name = skillDAO.getById(1).getName();
        Integer id = skillDAO.getById(1).getId();
        assertEquals("Java", name);
        assertTrue(1 == id);
    }

    @Test
    public void update() {
        Skill newSkill = new Skill(2, "C#");
        when(skillDAO.update(newSkill)).thenReturn(newSkill);
        Skill sk = skillDAO.update(newSkill);
        assertEquals("C#", sk.getName());
    }

    private Skill testSkill() {
        Skill skill = new Skill();
        skill.setId(1);
        skill.setName("Java");
        return skill;
    }



//    DBConnector conn = DBConnector.getConnector();
//    SkillDAOImpl skillDAO = new SkillDAOImpl(conn.getConnect());
//
//    @Test
//    public void getAll() {
//        System.out.println(skillDAO.getAll());
//    }
//
//    @Test
//    public void  getById() {
//        System.out.println(skillDAO.getById(2));
//    }
//
//    @Test
//    public void update() {
//        System.out.println(skillDAO.update(new Skill(6, "PHP")));
//    }
//
//    @Test
//    public void  delete() {
//        skillDAO.deleteById(7);
//    }
}