package com.milk.consoleapp.view;

import com.milk.consoleapp.DBConnector;
import com.milk.consoleapp.controller.DeveloperController;
import com.milk.consoleapp.controller.TeamController;
import com.milk.consoleapp.model.dao.DeveloperDAO;
import com.milk.consoleapp.model.dao.implementation.DeveloperDAOImpl;
import com.milk.consoleapp.model.entity.Developer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author Jack Milk
 */
public class TeamView {

    private final DBConnector conn = DBConnector.getConnector();
    private final DeveloperDAO developerRepo = new DeveloperDAOImpl(conn.getConnect());
    TeamController controller = new TeamController();
    DeveloperController developerController = new DeveloperController();

    public void teamMenu(){
        System.out.println();
        System.out.println("        *******************************       ");
        System.out.println("        |   Command Menu from Team    |       ");
        System.out.println("        *******************************       ");
        System.out.println("        | Options:                    |       ");
        System.out.println("        |     1. All Teams            |       ");
        System.out.println("        |     2. Get Team by Id       |       ");
        System.out.println("        |     3. Create Team          |       ");
        System.out.println("        |     4. Update Team by Id    |       ");
        System.out.println("        |     5. Delete Team by Id    |       ");
        System.out.println("        |     M. Main menu            |       ");
        System.out.println("        |     E. Exit                 |       ");
        System.out.println("        *******************************       ");


        String choice = "";

        while(!(choice.toLowerCase().equals("e"))) {
            System.out.print("          Select option: ");
            choice = new Scanner(System.in).next();
            switch (choice.toLowerCase()) {
                case "1":
                    System.out.println("All Teams: ");
                    controller.viewAllTeams();
                    break;
                case "2":
                    controller.viewAllTeams();
                    System.out.print("Team By Id (enter id): ");
                    String idTeamForView = new Scanner(System.in).next();
                    controller.viewTeamByID(idTeamForView);
                    break;
                case "3":
                    System.out.print("Enter new Team Name: ");
                    String name = new Scanner(System.in).next();
                    List<String> developersId = getDevelopers();
                    controller.save(name, developersId);
                    break;
                case "4":
                    controller.viewAllTeams();
                    System.out.print("Enter Id Team for update: ");
                    String idTeamForUpdate = new Scanner(System.in).next();
                    System.out.print("Enter new Team Name: ");
                    String newTeamName = new Scanner(System.in).next();
                    List<String> developerIdForUpdate = getDevelopers();
                    controller.updateTeamForId(idTeamForUpdate, newTeamName, developerIdForUpdate);
                    break;
                case "5":
                    controller.viewAllTeams();
                    System.out.print("Delete Team By Id (enter id): ");
                    String idTeamForDelete = new Scanner(System.in).next();
                    controller.deleteTeamById(idTeamForDelete);
                    controller.viewAllTeams();
                    break;
                case "m":
                    MainView.mainView();
                    break;
                case "e":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid selection");
                    teamMenu();
                    break;
            }
        }
    }

    public List<String> getDevelopers(){
        List<String> developersId = new ArrayList<>();
        developerController.viewAllDevelopers();
        while (true){
            System.out.println("Enter Developer id: (Write 'S' to stop)");
            String developer = new Scanner(System.in).next();
            if (developer.equalsIgnoreCase("s")) {
                break;
            }
            boolean isContains = developerRepo.getAll().stream()
                    .map(Developer::getId)
                    .collect(Collectors.toList())
                    .contains(Integer.parseInt(developer));
            if(!isContains){
                System.out.println("This ID does not exist");
                continue;
            }
            developersId.add(developer);
        }
        return developersId;
    }
}
