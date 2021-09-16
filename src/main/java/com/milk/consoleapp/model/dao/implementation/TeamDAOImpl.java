package com.milk.consoleapp.model.dao.implementation;

import com.milk.consoleapp.model.dao.TeamDAO;
import com.milk.consoleapp.model.entity.Developer;
import com.milk.consoleapp.model.entity.Skill;
import com.milk.consoleapp.model.entity.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jack Milk
 */
public class TeamDAOImpl implements TeamDAO {

    private final Connection connection;

    public TeamDAOImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public List<Team> getAll() {

        List<Team> teams = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SQLTeam.GET_TEAM_ALL.QUERY)){
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Team team = new Team();

                team.setId(resultSet.getInt("id"));
                team.setName(resultSet.getString("name"));

                try (PreparedStatement stmt = connection.prepareStatement(SQLTeam.GET_TEAM_DEV_BY_ID.QUERY)){
                    stmt.setInt(1, team.getId());
                    ResultSet rs = stmt.executeQuery();

                    while (rs.next()) {
                        Developer dev = new Developer();
                        dev.setId(rs.getInt("id"));
                        dev.setFirstName(rs.getString("firstName"));
                        dev.setLastName(rs.getString("lastName"));

                        try (PreparedStatement stm = connection.prepareStatement(DeveloperDAOImpl.SQLDev.GET_DEV_SK_BY_ID.QUERY)){
                            stm.setInt(1, dev.getId());
                            ResultSet rset = stm.executeQuery();

                            while (rset.next()) {
                                Skill skill = new Skill();
                                skill.setId(rset.getInt("id"));
                                skill.setName(rset.getString("name"));
                                dev.addSkill(skill);
                            }

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        team.addDev(dev);
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }

                teams.add(team);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return teams;

    }

    @Override
    public Team getById(Integer id) {
        Team team = new Team();

        try (PreparedStatement statement = connection.prepareStatement(SQLTeam.GET_TEAM_BY_ID.QUERY)){

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                team.setId(resultSet.getInt("id"));
                team.setName(resultSet.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (PreparedStatement statement = connection.prepareStatement(SQLTeam.GET_TEAM_DEV_BY_ID.QUERY)){
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Developer developer = new Developer();
                developer.setId(resultSet.getInt("id"));
                developer.setFirstName(resultSet.getString("firstName"));
                developer.setLastName(resultSet.getString("lastName"));

                try (PreparedStatement stm = connection.prepareStatement(DeveloperDAOImpl.SQLDev.GET_DEV_SK_BY_ID.QUERY)){
                    stm.setInt(1, developer.getId());
                    ResultSet rset = stm.executeQuery();

                    while (rset.next()) {
                        Skill skill = new Skill();
                        skill.setId(rset.getInt("id"));
                        skill.setName(rset.getString("name"));
                        developer.addSkill(skill);
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                team.addDev(developer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return team;
    }

    @Override
    public Team save(Team team) {
        Team tm = null;
        int id = -1;

        try (PreparedStatement statement = connection.prepareStatement(SQLTeam.SAVE_TEAM.QUERY)){

            statement.setString(1, team.getName());
            statement.executeUpdate();

            try (ResultSet rs = statement.executeQuery("SELECT LAST_INSERT_ID()")){
                if (rs.next()) {
                    id = rs.getInt(1);
                } else {
                    throw new IllegalArgumentException("ID not found!");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Developer d : team.getDevelopers()) {
            try (PreparedStatement statement = connection.prepareStatement(SQLTeam.SAVE_TEAM_DEV.QUERY)) {

                statement.setInt(1, id);
                statement.setInt(2, d.getId());
                statement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        tm = getById(id);
        return tm;
    }

    @Override
    public Team update(Team team) {

        try (PreparedStatement statement = connection.prepareStatement(SQLTeam.UPDATE_TEAM.QUERY)){

            statement.setString(1, team.getName());
            statement.setInt(2, team.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (PreparedStatement stm = connection.prepareStatement(SQLTeam.DELETE_DEV.QUERY)){

            stm.setInt(1, team.getId());
            stm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Developer d : team.getDevelopers()) {
            try (PreparedStatement statement = connection.prepareStatement(SQLTeam.SAVE_TEAM_DEV.QUERY)) {

                statement.setInt(1, team.getId());
                statement.setInt(2, d.getId());
                statement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return getById(team.getId());
    }

    @Override
    public void deleteById(Integer id) {

        try (PreparedStatement statement = connection.prepareStatement(SQLTeam.DELETE_TEAM.QUERY)){
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    enum SQLTeam {
        GET_TEAM_ALL("SELECT * FROM team"),
        GET_TEAM_BY_ID("SELECT * FROM team WHERE id=(?)"),
        GET_TEAM_DEV_BY_ID("SELECT d.* " +
                "FROM team t " +
                "         JOIN tm_dev td ON t.id = td.tm_id " +
                "         JOIN developer d ON td.dev_id = d.id " +
                "WHERE t.id = (?)"),
        SAVE_TEAM("INSERT INTO team (id, name) VALUES (DEFAULT, (?))"),
        SAVE_TEAM_DEV("INSERT INTO tm_dev (tm_id, dev_id) SELECT t.id, d.id FROM team t, developer d WHERE t.id=(?) AND d.id=(?)"),
        DELETE_TEAM("DELETE FROM team WHERE id=(?)"),
        DELETE_DEV("DELETE FROM tm_dev WHERE tm_id=(?)"),
        UPDATE_TEAM("UPDATE team SET name=(?) WHERE id=(?)");

        String QUERY;

        SQLTeam(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}
