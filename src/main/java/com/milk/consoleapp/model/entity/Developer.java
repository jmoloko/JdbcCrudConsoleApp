package com.milk.consoleapp.model.entity;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Jack Milk
 */

public class Developer {
    private Integer id;
    private String firstName;
    private String lastName;
    private List<Skill> skills = new ArrayList<>();

    public Developer(Integer id, String firstName, String lastName, List<Skill> skills) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.skills = skills;
    }

    public Developer(String firstName, String lastName, List<Skill> skills) {
        this(null, firstName, lastName, skills);
    }

    public Developer() {    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return firstName + lastName;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public void addSkill(Skill skill) {
        this.skills.add(skill);
    }


    @Override
    public String toString() {
        return " id:" + id + " " + firstName + " " + lastName + " | Skills => " + skills;
    }


}