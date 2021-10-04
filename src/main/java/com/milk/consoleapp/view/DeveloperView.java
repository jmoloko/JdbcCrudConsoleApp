package com.milk.consoleapp.view;


import com.milk.consoleapp.DBConnector;
import com.milk.consoleapp.controller.DeveloperController;
import com.milk.consoleapp.controller.SkillController;
import com.milk.consoleapp.model.dao.implementation.DeveloperDAOImpl;
import com.milk.consoleapp.model.dao.implementation.SkillDAOImpl;
import com.milk.consoleapp.model.entity.Developer;
import com.milk.consoleapp.model.entity.Skill;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author Jack Milk
 */
public class DeveloperView {

    private final DeveloperController controller = new DeveloperController(new DeveloperDAOImpl());
    private final SkillController skillController = new SkillController(new SkillDAOImpl());

    public void developerMenu(){
        System.out.println();
        System.out.println("        *******************************       ");
        System.out.println("        | Command Menu from Developer |       ");
        System.out.println("        *******************************       ");
        System.out.println("        | Options:                    |       ");
        System.out.println("        |   1. All Developers         |       ");
        System.out.println("        |   2. Get Developer by Id    |       ");
        System.out.println("        |   3. Create Developer       |       ");
        System.out.println("        |   4. Update Developer by Id |       ");
        System.out.println("        |   5. Delete Developer by Id |       ");
        System.out.println("        |   M. Main menu              |       ");
        System.out.println("        |   E. Exit                   |       ");
        System.out.println("        *******************************       ");


        String choice = "";

        while(!(choice.equalsIgnoreCase("e"))) {
            System.out.print("          Select option: ");
            choice = new Scanner(System.in).next();
            switch (choice.toLowerCase()) {
                case "1":
                    showAllDevelopers();
                    break;
                case "2":
                    showAllDevelopers();
                    showDevById();
                    break;
                case "3":
                    showNewDeveloper();
                    showAllDevelopers();
                    break;
                case "4":
                    showAllDevelopers();
                    showUpdatedDev();
                    showAllDevelopers();
                    break;
                case "5":
                    showAllDevelopers();
                    showDeletedDev();
                    showAllDevelopers();
                    break;
                case "m":
                    MainView.mainView();
                    break;
                case "e":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid selection");
                    developerMenu();
                    break;
            }
        }

        try {
            DBConnector.getConnector().getConnect().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showAllDevelopers(){
        System.out.println("All Developers: ");
        for (Developer developer: controller.viewAllDevelopers()){
            developerToConsole(developer);
        }
        System.out.println();
    }

    private void developerToConsole(Developer dev){
        System.out.print("| Developer -> id:"+ dev.getId() + " " + dev.getFirstName() + " " + dev.getLastName());
        System.out.print(" | Skills -> ");
        dev.getSkills().forEach(s -> System.out.print(s.getId() + ":" + s.getName() + " "));
        System.out.println("|");
    }

    private void showDevById(){
        System.out.print("Developer By Id (enter id): ");
        int id = checkId();
        Developer dev = controller.viewDeveloperByID(id);
        developerToConsole(dev);
    }

    private void showNewDeveloper(){
        System.out.print("Enter new  First Name: ");
        String firstName = new Scanner(System.in).next();
        System.out.print("Enter new  Last Name: ");
        String lastName = new Scanner(System.in).next();
        List<Skill> skills = getSkills();
        controller.save(new Developer(firstName, lastName, skills));
        System.out.println();
    }

    private void showUpdatedDev() {
        System.out.print("Enter Id Developer for update: ");
        int devId = checkId();
        System.out.print("Enter new First Name: ");
        String newFirstName = new Scanner(System.in).next();
        System.out.print("Enter new Lat Name: ");
        String newLastName = new Scanner(System.in).next();
        List<Skill> skillsForUpdate = getSkills();
        controller.updateDeveloperForId(new Developer(devId, newFirstName, newLastName, skillsForUpdate));
    }

    private void showDeletedDev(){
        System.out.print("Delete Developer By Id (enter id): ");
        int devId = checkId();
        controller.deleteDeveloperById(devId);
    }

    private List<Skill> getSkills(){
        List<Skill> skills = new ArrayList<>();
        new SkillView().showAllSkills();
        while (true){
            System.out.println("Enter Skill id: (Write 'S' to stop)");
            String skill = new Scanner(System.in).next();
            if (skill.equalsIgnoreCase("s")) {
                break;
            }
            boolean isContains = skillController.viewAllSkills()
                    .stream().map(Skill::getId)
                    .collect(Collectors.toList())
                    .contains(Integer.parseInt(skill));
            if(!isContains){
                System.out.println("This ID does not exist");
                continue;
            }
            skills.add(skillController.viewSkillByID(Integer.parseInt(skill)));
        }
        return skills;
    }

    private Integer checkId() {
        int devId;
        while (true) {
            System.out.println("Enter new ID: ");
            String id = new Scanner(System.in).next();
            try {
                if (isDigit(id)) {
                    boolean isContainsId = controller.viewAllDevelopers()
                            .stream().map(Developer::getId)
                            .collect(Collectors.toList())
                            .contains(Integer.parseInt(id));
                    if (isContainsId){
                        devId = Integer.parseInt(id);
                        break;
                    } else {
                        System.out.println("Does not exist developer with this ID");
                        continue;
                    }
                } else {
                    throw new NullPointerException();
                }
            } catch (NullPointerException e) {
                System.out.println("This ID does not exist");
                continue;
            }
        }
        return devId;
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
