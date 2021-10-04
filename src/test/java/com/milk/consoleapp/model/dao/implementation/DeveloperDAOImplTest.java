package com.milk.consoleapp.model.dao.implementation;

import com.milk.consoleapp.controller.DeveloperController;
import com.milk.consoleapp.model.entity.Developer;
import com.milk.consoleapp.model.entity.Skill;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;


/**
 * @author Jack Milk
 */

@RunWith(MockitoJUnitRunner.class)
public class DeveloperDAOImplTest {

    @Mock
    private DeveloperDAOImpl developerDAO;

    @InjectMocks
    private DeveloperController controller;

    private static final List<Skill> skills = new ArrayList<>();
    static {
        skills.add(new Skill(1, "Java"));
        skills.add(new Skill(1, "PHP"));
    }

    Developer developer = new Developer(1, "John", "Doe", skills);

    @Test
    public void testGetAllDeveloper() {

        when(developerDAO.getAll()).thenReturn(Lists.newArrayList(developer));
        assertEquals(1, controller.viewAllDevelopers().size());
        assertEquals("John", controller.viewAllDevelopers().get(0).getFirstName());
        assertEquals("Doe", controller.viewAllDevelopers().get(0).getLastName());
        assertEquals(2, controller.viewAllDevelopers().get(0).getSkills().size());

        verify(developerDAO, times(4)).getAll();
    }

    @Test
    public void testGetDeveloperById() {

        when(developerDAO.getById(1)).thenReturn(developer);
        assertEquals(1, (int) controller.viewDeveloperByID(1).getId());
        assertEquals("John", controller.viewDeveloperByID(1).getFirstName());
        assertEquals("Doe", controller.viewDeveloperByID(1).getLastName());
        assertEquals(2, controller.viewDeveloperByID(1).getSkills().size());

        verify(developerDAO, times(4)).getById(1);
    }

    @Test
    public void testSaveDeveloper() {

        when(developerDAO.save(developer)).thenReturn(developer);
        assertEquals(1, (int) controller.save(developer).getId());
        assertEquals("John", controller.save(developer).getFirstName());
        assertEquals("Doe", controller.save(developer).getLastName());
        assertEquals(2, controller.save(developer).getSkills().size());

        verify(developerDAO, times(4)).save(developer);
    }

    @Test
    public void testUpdateDeveloper(){

        when(developerDAO.update(developer)).thenReturn(new Developer(1, "Jack", "Milk", skills));
        assertEquals(1, (int) controller.updateDeveloperForId(developer).getId());
        assertEquals("Jack", controller.updateDeveloperForId(developer).getFirstName());
        assertEquals("Milk", controller.updateDeveloperForId(developer).getLastName());
        assertEquals(2, controller.updateDeveloperForId(developer).getSkills().size());

        verify(developerDAO, atMost(5)).update(developer);

    }

    @Test
    public void testDeleteDeveloper() {

        doNothing().when(developerDAO).deleteById(1);
        controller.deleteDeveloperById(1);
        verify(developerDAO).deleteById(1);

    }

}