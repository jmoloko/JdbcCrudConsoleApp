package com.milk.consoleapp.view;

import com.milk.consoleapp.DBConnector;
import com.milk.consoleapp.controller.DeveloperController;
import com.milk.consoleapp.controller.TeamController;
import com.milk.consoleapp.model.entity.Developer;
import com.milk.consoleapp.model.entity.Skill;
import com.milk.consoleapp.model.entity.Team;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Jack Milk
 */
public class TeamView {

    private final TeamController controller = new TeamController();
    private final DeveloperController developerController = new DeveloperController();

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

        while(!(choice.equalsIgnoreCase("e"))) {
            System.out.print("          Select option: ");
            choice = new Scanner(System.in).next();
            switch (choice.toLowerCase()) {
                case "1":
                    showAllTeams();
                    break;
                case "2":
                    showAllTeams();
                    showTeamById();
                    break;
                case "3":
                    showNewTeam();
                    showAllTeams();
                    break;
                case "4":
                    showAllTeams();
                    showUpdatedTeam();
                    showAllTeams();
                    break;
                case "5":
                    showAllTeams();
                    showDeletedTeam();
                    showAllTeams();
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

        try {
            DBConnector.getConnector().getConnect().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showAllTeams(){
        System.out.println("All Teams: ");
        for (Team team: controller.viewAllTeams()){
            teamToConsole(team);
        }
        System.out.println();
    }

    private void showTeamById() {
        System.out.print("Team By Id (enter id): ");
        int id = checkId();
        Team team = controller.viewTeamByID(id);
        teamToConsole(team);
    }

    private void showNewTeam() {
        System.out.print("Enter new Team Name: ");
        String name = new Scanner(System.in).next();
        List<Developer> developers = getDevelopers();

        controller.save(new Team(name, developers));
    }

    private void showUpdatedTeam() {
        System.out.print("Enter Id Team for update: ");
        int id = checkId();
        System.out.print("Enter new Team Name: ");
        String newTeamName = new Scanner(System.in).next();
        List<Developer> developerIdForUpdate = getDevelopers();
        controller.updateTeamForId(new Team(id, newTeamName, developerIdForUpdate));
    }

    private void showDeletedTeam() {
        System.out.print("Delete Team By Id (enter id): ");
        int id = checkId();
        controller.deleteTeamById(id);
    }

    private List<Developer> getDevelopers(){
        List<Developer> developers = new ArrayList<>();
        new DeveloperView().showAllDevelopers();
        while (true){
            System.out.println("Enter Developer id: (Write 'S' to stop)");
            String developer = new Scanner(System.in).next();
            if (developer.equalsIgnoreCase("s")) {
                break;
            }
            boolean isContains = developerController.viewAllDevelopers().stream()
                    .map(Developer::getId)
                    .collect(Collectors.toList())
                    .contains(Integer.parseInt(developer));
            if(!isContains){
                System.out.println("This ID does not exist");
                continue;
            }
            developers.add(developerController.viewDeveloperByID(Integer.parseInt(developer)));
        }
        return developers;
    }

    private void teamToConsole(Team team){
        System.out.print("| Team -> id:"+ team.getId() + " " + team.getName());
        System.out.print(" | Developers -> ");
        team.getDevelopers().forEach(d -> System.out.print(d.getId() + ":" + d.getFirstName() + " " + d.getLastName() + " "));
        System.out.print(" | All Skills -> ");
        Set<String> skills = new HashSet<>();
        for (Developer dev: team.getDevelopers()){
            for (Skill skill: dev.getSkills()){
                skills.add(skill.getName());
            }
        }
        skills.forEach(s -> System.out.print(s + " "));
        System.out.println("|");
    }

    private Integer checkId() {
        int teamId;
        while (true) {
            System.out.println("Enter new ID: ");
            String id = new Scanner(System.in).next();
            try {
                if (isDigit(id)) {
                    boolean isContainsId = controller.viewAllTeams()
                            .stream().map(Team::getId)
                            .collect(Collectors.toList())
                            .contains(Integer.parseInt(id));
                    if (isContainsId){
                        teamId = Integer.parseInt(id);
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
        return teamId;
    }

    private static boolean isDigit(String enterString) {
        try {
            Integer.parseInt(enterString.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
