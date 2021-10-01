package com.milk.consoleapp.model.dao.implementation;

import com.milk.consoleapp.controller.SkillController;
import com.milk.consoleapp.model.dao.SkillDAO;
import com.milk.consoleapp.model.entity.Skill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

/**
 * @author Jack Milk
 */

@RunWith(MockitoJUnitRunner.class)
public class SkillDAOImplTest {

    @Mock
    private SkillDAOImpl skillDAO;

    @InjectMocks
    private SkillController controller;

    @Test
    public void testGetByID() {

        when(skillDAO.getById(10)).thenReturn(new Skill(10, "Test"));
        assertEquals("Test", controller.viewSkillByID(10).getName());
        controller.viewSkillByID(1);
        verify(skillDAO).getById(1);

    }
//
//
//    @Test
//    public void getById() {
//        when(skillDAO.getById(1)).thenReturn(testSkill());
//        String name = skillDAO.getById(1).getName();
//        Integer id = skillDAO.getById(1).getId();
//        assertEquals("Java", name);
//        assertTrue(1 == id);
//    }
//
//    @Test
//    public void update() {
//        Skill newSkill = new Skill(2, "C#");
//        when(skillDAO.update(newSkill)).thenReturn(newSkill);
//        Skill sk = skillDAO.update(newSkill);
//        assertEquals("C#", sk.getName());
//    }
//
//    private Skill testSkill() {
//        Skill skill = new Skill();
//        skill.setId(1);
//        skill.setName("Java");
//        return skill;
//    }


//    DBConnector conn = DBConnector.getConnector();
//    SkillDAOImpl skillDAO = new SkillDAOImpl();
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