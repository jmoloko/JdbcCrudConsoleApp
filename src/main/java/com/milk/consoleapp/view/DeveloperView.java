package com.milk.consoleapp.view;

import com.milk.consoleapp.DBConnector;
import com.milk.consoleapp.controller.DeveloperController;
import com.milk.consoleapp.controller.SkillController;
import com.milk.consoleapp.model.dao.SkillDAO;
import com.milk.consoleapp.model.dao.implementation.SkillDAOImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author Jack Milk
 */
public class DeveloperView {

    private final DeveloperController controller = new DeveloperController();
    private final SkillController skillController = new SkillController();
    private final DBConnector conn = DBConnector.getConnector();
    private final SkillDAO skillRepo = new SkillDAOImpl(conn.getConnect());

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

        while(!(choice.toLowerCase().equals("e"))) {
            System.out.print("          Select option: ");
            choice = new Scanner(System.in).next();
            switch (choice.toLowerCase()) {
                case "1":
                    System.out.println("All Developers: ");
                    controller.viewAllDevelopers();
                    break;
                case "2":
                    controller.viewAllDevelopers();
                    System.out.print("Developer By Id (enter id): ");
                    String idDeveloperForView = new Scanner(System.in).next();
                    controller.viewDeveloperByID(idDeveloperForView);
                    break;
                case "3":
                    System.out.print("Enter new  First Name: ");
                    String firstName = new Scanner(System.in).next();
                    System.out.print("Enter new  Last Name: ");
                    String lastName = new Scanner(System.in).next();
                    List<String> skillId = getSkills();
                    controller.save(firstName, lastName, skillId);
                    break;
                case "4":
                    controller.viewAllDevelopers();
                    System.out.print("Enter Id Developer for update: ");
                    String idDeveloperForUpdate = new Scanner(System.in).next();
                    System.out.print("Enter new First Name: ");
                    String newFirstName = new Scanner(System.in).next();
                    System.out.print("Enter new Lat Name: ");
                    String newLastName = new Scanner(System.in).next();
                    List<String> skillIdForUpdate = getSkills();
                    controller.updateDeveloperForId(idDeveloperForUpdate, newFirstName, newLastName, skillIdForUpdate);
                    break;
                case "5":
                    controller.viewAllDevelopers();
                    System.out.print("Delete Developer By Id (enter id): ");
                    String idDeveloperForDelete = new Scanner(System.in).next();
                    controller.deleteDeveloperById(idDeveloperForDelete);
                    controller.viewAllDevelopers();
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
    }

    public List<String> getSkills(){
        List<String> skillId = new ArrayList<>();
        skillController.viewAllSkills();
        while (true){
            System.out.println("Enter Skill id: (Write 'S' to stop)");
            String skill = new Scanner(System.in).next();
            if (skill.equalsIgnoreCase("s")) {
                break;
            }
            boolean isContains = skillRepo.getAll().stream().map(s -> s.getId()).collect(Collectors.toList()).contains(Integer.parseInt(skill));
            if(!isContains){
                System.out.println("This ID does not exist");
                continue;
            }
            skillId.add(skill);
        }
        return skillId;
    }

}
