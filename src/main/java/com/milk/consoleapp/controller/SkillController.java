package com.milk.consoleapp.controller;

import com.milk.consoleapp.DBConnector;
import com.milk.consoleapp.model.dao.SkillDAO;
import com.milk.consoleapp.model.dao.implementation.SkillDAOImpl;
import com.milk.consoleapp.model.entity.Skill;

/**
 * @author Jack Milk
 */
public class SkillController {

    private final DBConnector conn = DBConnector.getConnector();
    private final SkillDAO skill = new SkillDAOImpl(conn.getConnect());

    public void viewAllSkills(){
        System.out.print("| Skills -> ");
        skill.getAll().forEach(s -> System.out.print(s.getId() + ":" + s.getName() + " "));
        System.out.println(" |");
    }

    public void viewSkillByID(String id){
        try {
            if (isDigit(id))
                System.out.println(skill.getById(Integer.parseInt(id)).getName());
            else
                System.out.println("Enter a digital designation id");
        } catch (NullPointerException e){
            System.out.println("This ID does not exist");
        }
    }

    public void save(String newSkillName){
        Skill newSkill = skill.save(new Skill(newSkillName));
        viewAllSkills();
        System.out.println();
    }

    public void updateSkillForId(String id, String newName){
        try {
            if (isDigit(id)) {
                int skillId = Integer.parseInt(id);
                skill.update(new Skill(skillId, newName));
                viewAllSkills();
            } else {
                System.out.println("Enter a digital designation id");
            }
        } catch (NullPointerException e) {
            System.out.println("This ID does not exist");
        }
    }

    public void deleteSkillById(String id){
        try {
            if (isDigit(id)) {
                int skillId = Integer.parseInt(id);
                skill.deleteById(skillId);
            } else {
                System.out.println("Enter a digital designation id");
            }
        } catch (NullPointerException e) {
            System.out.println("This ID does not exist");
        }
    }

    private boolean isDigit(String enterString) {
        try {
            Integer.parseInt(enterString.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
