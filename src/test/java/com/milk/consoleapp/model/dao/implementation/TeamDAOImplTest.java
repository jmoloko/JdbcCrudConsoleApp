package com.milk.consoleapp.model.dao.implementation;

import com.milk.consoleapp.controller.TeamController;
import com.milk.consoleapp.model.entity.Developer;
import com.milk.consoleapp.model.entity.Skill;
import com.milk.consoleapp.model.entity.Team;
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
public class TeamDAOImplTest {

    @Mock
    private TeamDAOImpl teamDAO;

    @InjectMocks
    private TeamController controller;

    private static final List<Skill> skills = new ArrayList<>();
    static {
        skills.add(new Skill(1, "Java"));
        skills.add(new Skill(1, "PHP"));
    }

    private static final List<Developer> developers = new ArrayList<>();
    static {
        developers.add(new Developer(1, "John", "Doe", skills.subList(0,1)));
        developers.add(new Developer(2, "Mike", "Bar", skills.subList(1,2)));
    }

    Team team = new Team(1, "TestTeam", developers);

    @Test
    public void testGetAllTeams(){

        when(teamDAO.getAll()).thenReturn(Lists.newArrayList(team));
        assertEquals(1, controller.viewAllTeams().size());
        assertEquals("TestTeam", controller.viewAllTeams().get(0).getName());
        assertEquals(2, controller.viewAllTeams().get(0).getDevelopers().size());
        assertEquals("John", controller.viewAllTeams().get(0).getDevelopers().get(0).getFirstName());

        verify(teamDAO, atMost(5)).getAll();

    }

    @Test
    public void testGetTeamById() {

        when(teamDAO.getById(1)).thenReturn(team);
        assertEquals(1, (int) controller.viewTeamByID(1).getId());
        assertEquals("TestTeam", controller.viewTeamByID(1).getName());
        assertEquals(2, controller.viewTeamByID(1).getDevelopers().size());

        verify(teamDAO, atMost(5)).getById(1);

    }

    @Test
    public void testSvaTeam() {

        when(teamDAO.save(team)).thenReturn(team);
        assertEquals(1, (int) controller.save(team).getId());
        assertEquals("TestTeam", controller.save(team).getName());
        assertEquals(2, controller.save(team).getDevelopers().size());
        assertEquals("Mike", controller.save(team).getDevelopers().get(1).getFirstName());

        verify(teamDAO, atMost(5)).save(team);

    }


    @Test
    public void testUpdateTeamById() {

        when(teamDAO.update(team)).thenReturn(new Team(1, "UpdatedTeam", developers));
        assertEquals(1, (int) controller.updateTeamForId(team).getId());
        assertEquals("UpdatedTeam", controller.updateTeamForId(team).getName());
        assertEquals(2, controller.updateTeamForId(team).getDevelopers().size());

        verify(teamDAO, atMost(3)).update(team);

    }

    @Test
    public void testDeleteTeam() {

        doNothing().when(teamDAO).deleteById(1);
        controller.deleteTeamById(1);
        verify(teamDAO).deleteById(1);

    }

}