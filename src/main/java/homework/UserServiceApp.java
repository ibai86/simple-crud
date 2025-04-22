package homework;

import homework.dto.UserDto;
import homework.entity.User;
import homework.repository.UserDaoImpl;
import homework.service.UserService;
import homework.utility.HibernateUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Scanner;

@Slf4j
public class UserServiceApp {
    private static final UserService userService = new UserService(new UserDaoImpl());
    private static final Scanner scanner = new Scanner(System.in);

    private static final Map<Integer, Runnable> menuActions = new HashMap<>();

    static {
        menuActions.put(1, UserServiceApp::addUser);
        menuActions.put(2, UserServiceApp::getAllUsers);
        menuActions.put(3, UserServiceApp::getUser);
        menuActions.put(4, UserServiceApp::updateUser);
        menuActions.put(5, UserServiceApp::deleteUser);
    }

    public static void main(String[] args) {
        log.info("Hibernate SessionFactory initialization.....");
        if (HibernateUtil.getSessionFactory() == null) {
            log.error("Failed to initialize Hibernate. Application shutdown");
        }

        boolean isRunning = true;

        while (isRunning) {
            printMenu();
            int choice = readIntInput("Choose options: ");

            if (choice == 0) {
                isRunning = false;
                System.out.println("Shutting down the program.");
                continue;
            }

            Runnable action = menuActions.get(choice);

            if (action != null) {
                action.run();
            } else {
                System.out.println("Incorrect selection. Please try again.");
            }

            System.out.println("------------------------------------");
        }

        scanner.close();
        HibernateUtil.shutdown();
    }

    private static void printMenu() {
        System.out.println("\n--- Main menu ---");
        System.out.println("1. Add new user");
        System.out.println("2. Find and print all users");
        System.out.println("3. Find and print user by ID");
        System.out.println("4. Update user's data");
        System.out.println("5. Delete user by ID");
        System.out.println("0. Exit");
        System.out.println("--------------------------------");
    }

    private static void addUser() {
        System.out.println("\n--- Saving new user ---");
        String name = readStringInput("Input username: ");
        String email = readStringInput("Input user email: ");
        Integer age = readIntInput("Input user age: ");
        try {
            UserDto dto = new UserDto(name, email, age);
            System.out.println(userService.saveUser(dto));
        } catch (RuntimeException e) {
            log.error("Failed to create new user {}", e.getMessage());
        }
    }

    private static void getAllUsers() {
        System.out.println("\n--- List of all users ---");
        try {
            List<User> users = userService.getAllUsers();
            if (users.isEmpty()) {
                System.out.println("No one user saved.");
            } else {
                users.forEach(System.out::println);
            }
        } catch (Exception e) {
            log.error("Failed to get list of users {}", e.getMessage());
        }
    }

    private static void getUser() {
        System.out.println("\n--- Finding user by ID ---");
        int id = readIntInput("Input user ID: ");
        try {
            System.out.println(userService.getUserById(id));
        } catch (RuntimeException e) {
            log.error(e.getMessage());
        }
    }

    private static void updateUser() {
        System.out.println("\n--- Updating user ---");
        int id = readIntInput("Input user ID: ");
        User userToUpdate = null;
        try {
            userToUpdate = userService.getUserById(id);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
        }

        String newName = readStringInput("Input new username: ");
        String newEmail = readStringInput("Input new user email: ");
        Integer newAge = readIntInput("Input new user age: ");

        if (userToUpdate != null) {
            userToUpdate.setUsername(newName);
            userToUpdate.setEmail(newEmail);
            userToUpdate.setAge(newAge);
        }

        try {
            System.out.println(userService.updateUser(userToUpdate));
        } catch (Exception e) {
            log.error("Failed to update user {}", e.getMessage());
        }
    }

    private static void deleteUser() {
        System.out.println("\n--- Deleting user ---");
        int id = readIntInput("Input user ID for deleting: ");
        try {
            userService.deleteUser(id);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
        }

    }

    private static String readStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private static int readIntInput(String prompt) {
        int value = -1;
        boolean validInput = false;
        while (!validInput) {
            System.out.print(prompt);
            try {
                value = Integer.parseInt(scanner.nextLine());
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Please, input integer number");
            }
        }
        return value;
    }
}
