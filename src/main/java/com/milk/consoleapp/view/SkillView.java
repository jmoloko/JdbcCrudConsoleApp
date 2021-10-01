package com.milk.consoleapp.view;

import com.milk.consoleapp.DBConnector;
import com.milk.consoleapp.controller.SkillController;
import com.milk.consoleapp.model.dao.implementation.SkillDAOImpl;
import com.milk.consoleapp.model.entity.Skill;

import java.sql.SQLException;
import java.util.Scanner;


/**
 * @author Jack Milk
 */
public class SkillView {

    private final SkillController controller;

    public SkillView() {
        this.controller  = new SkillController();
    }

    public void skillMenu(){
        System.out.println();
        System.out.println("        *****************************       ");
        System.out.println("        |  Command Menu from Skills |       ");
        System.out.println("        *****************************       ");
        System.out.println("        | Options:                  |       ");
        System.out.println("        |     1. All Skills         |       ");
        System.out.println("        |     2. Get Skill by Id    |       ");
        System.out.println("        |     3. Create Skill       |       ");
        System.out.println("        |     4. Update Skill by Id |       ");
        System.out.println("        |     5. Delete Skill by Id |       ");
        System.out.println("        |     M. Main menu          |       ");
        System.out.println("        |     E. Exit               |       ");
        System.out.println("        *****************************       ");


        String choice = "";

        while(!(choice.equalsIgnoreCase("e"))) {
            System.out.print("          Select option: ");
            choice = new Scanner(System.in).next();
            switch (choice.toLowerCase()) {
                case "1":
                    showAllSkills();
                    break;
                case "2":
                    showAllSkills();
                    showSkillById();
                    break;
                case "3":
                    showNewSkill();
                    break;
                case "4":
                    showAllSkills();
                    showUpdatedSkill();
                    showAllSkills();
                    break;
                case "5":
                    showAllSkills();
                    showDeletedSkill();
                    showAllSkills();
                    break;
                case "m":
                    MainView.mainView();
                    break;
                case "e":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid selection");
                    skillMenu();
                    break;
            }
        }
        try {
            DBConnector.getConnector().getConnect().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showAllSkills(){
        System.out.println("All Skills: ");
        System.out.print("| Skills -> ");
        controller.viewAllSkills().forEach(s -> System.out.print(s.getId() + ":" + s.getName() + " "));
        System.out.println(" |");
    }

    private void showSkillById(){
        System.out.print("Skill By Id (enter id): ");
        String id = new Scanner(System.in).next();

        try {
            if (isDigit(id))
                System.out.println(controller.viewSkillByID(Integer.parseInt(id)).getName());
            else
                System.out.println("Enter a digital designation id");
        } catch (NullPointerException e) {
            System.out.println("This ID does not exist");
        }

    }

    private void showNewSkill(){
        System.out.print("Enter new Skill name: ");
        String name = new Scanner(System.in).next();
        controller.save(new Skill(name));
        showAllSkills();
        System.out.println();
    }

    private void showUpdatedSkill() {
        System.out.print("Enter Id Skill for update: ");
        String id = new Scanner(System.in).next();
        System.out.print("Enter new name: ");
        String newName = new Scanner(System.in).next();

        try {
            if (isDigit(id)) {
                int skillId = Integer.parseInt(id);
                controller.updateSkill(new Skill(skillId, newName));
            } else {
                System.out.println("Enter a digital designation id");
            }
        } catch (NullPointerException e) {
            System.out.println("This ID does not exist");
        }

    }

    private void showDeletedSkill(){
        System.out.print("Delete Skill By Id (enter id): ");
        String id = new Scanner(System.in).next();

        try {
            if (isDigit(id)) {
                int skillId = Integer.parseInt(id);
                controller.deleteSkillById(skillId);
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
