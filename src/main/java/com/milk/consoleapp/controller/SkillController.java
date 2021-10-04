package com.milk.consoleapp.controller;


import com.milk.consoleapp.model.dao.SkillDAO;
import com.milk.consoleapp.model.dao.implementation.SkillDAOImpl;
import com.milk.consoleapp.model.entity.Skill;

import java.util.List;

/**
 * @author Jack Milk
 */
public class SkillController {

    private final SkillDAO skill;

    public SkillController(SkillDAO dao) {
        this.skill = dao;
    }

    public List<Skill> viewAllSkills() {
        return skill.getAll();
    }

    public Skill viewSkillByID(Integer id) {
        return skill.getById(id);
    }

    public Skill save(Skill newSkill) {
        return skill.save(newSkill);
    }

    public Skill updateSkill(Skill newSkill) {
       return skill.update(newSkill);
    }

    public void deleteSkillById(Integer id) {
        skill.deleteById(id);
    }

}
