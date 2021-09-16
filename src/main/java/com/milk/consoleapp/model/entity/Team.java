package com.milk.consoleapp.model.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Jack Milk
 */
public class Team {
    private Integer id;
    private String name;
    private List<Developer> developers = new ArrayList<>();

    public Team(Integer id, String name, List<Developer> developers) {
        this.id = id;
        this.name = name;
        this.developers = developers;
    }

    public Team(String name, List<Developer> developers) {
        this(null, name, developers);
    }

    public Team() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Developer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<Developer> developers) {
        this.developers = developers;
    }

    public void addDev(Developer developer) {
        this.developers.add(developer);
    }

    @Override
    public String toString() {
        return "Team => |" + " id:" + id + " " + name + " | Developers => " + developers;
    }
}
