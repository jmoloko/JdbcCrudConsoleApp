package com.milk.consoleapp.model.dao.implementation;

import com.milk.consoleapp.DBConnector;
import com.milk.consoleapp.model.dao.DeveloperDAO;
import com.milk.consoleapp.model.entity.Developer;
import com.milk.consoleapp.model.entity.Skill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jack Milk
 */
public class DeveloperDAOImpl implements DeveloperDAO {

    private final Connection connection = DBConnector.getConnector().getConnect();


    @Override
    public List<Developer> getAll() {

        List<Developer> developers = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SQLDev.GET_DEV_ALL.QUERY)){
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Developer developer = new Developer();

                developer.setId(resultSet.getInt("id"));
                developer.setFirstName(resultSet.getString("firstName"));
                developer.setLastName(resultSet.getString("lastName"));

                try (PreparedStatement stmt = connection.prepareStatement(SQLDev.GET_DEV_SK_BY_ID.QUERY)){
                    stmt.setInt(1, developer.getId());
                    ResultSet rs = stmt.executeQuery();

                    while (rs.next()) {
                        Skill skill = new Skill();
                        skill.setId(rs.getInt("id"));
                        skill.setName(rs.getString("name"));
                        developer.addSkill(skill);
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }

                developers.add(developer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return developers;
    }

    @Override
    public Developer getById(Integer id) {
        Developer developer = new Developer();

        try (PreparedStatement statement = connection.prepareStatement(SQLDev.GET_DEV_BY_ID.QUERY)){

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                developer.setId(resultSet.getInt("id"));
                developer.setFirstName(resultSet.getString("firstName"));
                developer.setLastName(resultSet.getString("lastName"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (PreparedStatement statement = connection.prepareStatement(SQLDev.GET_DEV_SK_BY_ID.QUERY)){
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Skill skill = new Skill();
                skill.setId(resultSet.getInt("id"));
                skill.setName(resultSet.getString("name"));
                developer.addSkill(skill);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return developer;
    }

    @Override
    public Developer save(Developer developer) {

        Developer dev;
        int id = -1;

        try (PreparedStatement statement = connection.prepareStatement(SQLDev.SAVE_DEV.QUERY)){

            statement.setString(1, developer.getFirstName());
            statement.setString(2, developer.getLastName());
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

        for (Skill s : developer.getSkills()) {
            try (PreparedStatement statement = connection.prepareStatement(SQLDev.SAVE_DEV_SK.QUERY)) {

                statement.setInt(1, id);
                statement.setInt(2, s.getId());
                statement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        dev = getById(id);
        return dev;
    }

    @Override
    public Developer update(Developer developer) {

        try (PreparedStatement statement = connection.prepareStatement(SQLDev.UPDATE_DEV.QUERY)){

            statement.setString(1, developer.getFirstName());
            statement.setString(2, developer.getLastName());
            statement.setInt(3, developer.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (PreparedStatement stm = connection.prepareStatement(SQLDev.DELETE_SK.QUERY)){

            stm.setInt(1, developer.getId());
            stm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Skill s : developer.getSkills()) {
            try (PreparedStatement statement = connection.prepareStatement(SQLDev.SAVE_DEV_SK.QUERY)) {

                statement.setInt(1, developer.getId());
                statement.setInt(2, s.getId());
                statement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return getById(developer.getId());
    }

    @Override
    public void deleteById(Integer id) {

        try (PreparedStatement statement = connection.prepareStatement(SQLDev.DELETE_DEV.QUERY)){
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    enum SQLDev {
        GET_DEV_ALL("SELECT * FROM developer"),
        GET_DEV_BY_ID("SELECT * FROM developer WHERE id=(?)"),
        GET_DEV_SK_BY_ID("SELECT s.* " +
                "FROM developer d " +
                "JOIN dev_skill ds ON d.id = ds.dev_id " +
                "JOIN skill s ON ds.sk_id = s.id " +
                "WHERE d.id = (?)"),
        SAVE_DEV("INSERT INTO developer (id, firstName, lastName) VALUES (DEFAULT, (?), (?))"),
        SAVE_DEV_SK("INSERT INTO dev_skill (dev_id, sk_id) SELECT d.id, s.id FROM developer d, skill s WHERE d.id=(?) AND s.id=(?)"),
        DELETE_DEV("DELETE FROM developer WHERE id=(?)"),
        DELETE_SK("DELETE FROM dev_skill WHERE dev_id=(?)"),
        UPDATE_DEV("UPDATE developer SET firstName=(?), lastName=(?) WHERE id=(?)");

        String QUERY;

        SQLDev(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}
