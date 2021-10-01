package com.milk.consoleapp.model.dao.implementation;

import com.milk.consoleapp.DBConnector;
import com.milk.consoleapp.model.dao.SkillDAO;
import com.milk.consoleapp.model.entity.Skill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Jack Milk
 */
public class SkillDAOImpl implements SkillDAO {

    private final Connection connection = DBConnector.getConnector().getConnect();

    @Override
    public List<Skill> getAll() {
        List<Skill> skills = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(SQLSkill.GET_ALL.QUERY)){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Skill skill = new Skill(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                );
                skills.add(skill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return skills;
    }

    @Override
    public Skill getById(Integer id) {
        Skill skill = null;

        try (PreparedStatement statement = connection.prepareStatement(SQLSkill.GET_BY_ID.QUERY)){
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                skill = new Skill(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return skill;
    }

    @Override
    public Skill save(Skill skill) {

        Skill newSkill;
        int id = -1;

        try (PreparedStatement statement = connection.prepareStatement(SQLSkill.SAVE.QUERY)){

            statement.setString(1, skill.getName());
            statement.executeUpdate();

            try (ResultSet rs = statement.executeQuery("SELECT LAST_INSERT_ID()")) {
                if (rs.next()) {
                    id = rs.getInt(1);
                } else {
                    throw new IllegalArgumentException("ID not found!");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        newSkill = getById(id);
        return newSkill;
    }

    @Override
    public Skill update(Skill skill) {
        Skill updatedSkill;

        try (PreparedStatement statement = connection.prepareStatement(SQLSkill.UPDATE.QUERY)){

            statement.setString(1, skill.getName());
            statement.setInt(2, skill.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        updatedSkill = getById(skill.getId());
        return updatedSkill;
    }

    @Override
    public void deleteById(Integer id) {
        try (PreparedStatement statement = connection.prepareStatement(SQLSkill.DELETE.QUERY)){
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    enum SQLSkill {
        GET_ALL("SELECT * FROM skill"),
        GET_BY_ID("SELECT * FROM skill WHERE id=(?)"),
        SAVE("INSERT INTO skill (id, name) VALUES (DEFAULT, (?))"),
        DELETE("DELETE FROM skill WHERE id=(?)"),
        UPDATE("UPDATE skill SET name=(?) WHERE id=(?)");

        String QUERY;

        SQLSkill(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}
