package com.milk.consoleapp.view;

import com.milk.consoleapp.controller.SkillController;

import java.util.Scanner;


/**
 * @author Jack Milk
 */
public class SkillView {

    private final SkillController controller = new SkillController();

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

        while(!(choice.toLowerCase().equals("e"))) {
            System.out.print("          Select option: ");
            choice = new Scanner(System.in).next();
            switch (choice.toLowerCase()) {
                case "1":
                    System.out.println("All Skills: ");
                    controller.viewAllSkills();
                    break;
                case "2":
                    controller.viewAllSkills();
                    System.out.print("Skill By Id (enter id): ");
                    String idSkillForView = new Scanner(System.in).next();
                    controller.viewSkillByID(idSkillForView);
                    break;
                case "3":
                    System.out.print("Enter new Skill name: ");
                    String name = new Scanner(System.in).next();
                    controller.save(name);
                    break;
                case "4":
                    controller.viewAllSkills();
                    System.out.print("Enter Id Skill for update: ");
                    String idSkillForUpdate = new Scanner(System.in).next();
                    System.out.print("Enter new name: ");
                    String newName = new Scanner(System.in).next();
                    controller.updateSkillForId(idSkillForUpdate, newName);
                    break;
                case "5":
                    controller.viewAllSkills();
                    System.out.print("Delete Skill By Id (enter id): ");
                    String idSkillForDelete = new Scanner(System.in).next();
                    controller.deleteSkillById(idSkillForDelete);
                    controller.viewAllSkills();
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
    }
}
