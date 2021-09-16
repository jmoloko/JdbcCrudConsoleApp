package com.milk.consoleapp.model.dao;

import com.milk.consoleapp.model.entity.Skill;

import java.util.List;

/**
 * @author Jack Milk
 */
public interface SkillDAO extends GenericDAO<Skill, Integer>{
    List<Skill> getAll();
    void deleteById(Integer id);
    Skill getById(Integer id);
    Skill save(Skill skill);
    Skill update(Skill skill);
}
