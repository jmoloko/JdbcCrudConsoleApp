package com.milk.consoleapp.model.dao.implementation;

import com.milk.consoleapp.controller.SkillController;
import com.milk.consoleapp.model.entity.Skill;
import org.assertj.core.util.Lists;
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

    Skill skill = new Skill(1, "Test");

    @Test
    public void testGetSkillByID() {

        when(skillDAO.getById(10)).thenReturn(new Skill(10, "Test"));
        assertEquals("Test", controller.viewSkillByID(10).getName());
        verify(skillDAO).getById(10);

    }

    @Test
    public void testGetAllSkills() {

        when(skillDAO.getAll()).thenReturn(Lists.newArrayList(new Skill(1, "Java"), new Skill(2, "Python")));
        assertEquals(2, controller.viewAllSkills().size());
        verify(skillDAO).getAll();

    }

    @Test
    public void testSaveSkill() {

        when(skillDAO.save(skill)).thenReturn(skill);
        assertEquals("Test", controller.save(skill).getName());
        assertTrue(1 == skill.getId());
        verify(skillDAO).save(skill);

    }

    @Test
    public void testUpdateSkill(){

        when(skillDAO.update(skill)).thenReturn(new Skill(1, "UpdatedTest"));
        assertEquals("UpdatedTest", controller.updateSkill(skill).getName());
        verify(skillDAO).update(skill);

    }

    @Test
    public void testDeleteSkill() {

        doNothing().when(skillDAO).deleteById(1);
        controller.deleteSkillById(1);
        verify(skillDAO).deleteById(1);

    }

}