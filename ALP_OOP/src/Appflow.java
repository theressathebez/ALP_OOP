
import java.util.*;
import java.text.*;

public class Appflow {

    Scanner s = new Scanner(System.in);
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    
    // list of all user registered
    ArrayList<User> users = new ArrayList<>();

    //user rn
    User currentUser = new User();
    
    //list category
    ArrayList<category> categories = new ArrayList<>();

    // boarding
    public void start() {
        System.out.println("### STUDY ASSISTANT ###");
        System.out.println("####### WELCOME #######");
        System.out.println(" ");
        loginRegister();
    }

    // login / register before going into the app
    public void loginRegister() {
        while (true) {
            System.out.println("=== LOGIN REGISTER ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("0. Exit");
            System.out.print("Choice: ");
            int choice = errorHandling(0, 2);
            System.out.println("======================");
            switch (choice) {
                case 1:
                    // register
                    register();
                    break;
                case 2:
                    // login
                    login();
                    break;
                case 0:
                    // exit app
                    System.out.println("Exiting app...");
                    System.exit(0);
            }
        }
    }

    // register account
    public void register() {
        System.out.print("Name: ");
        String name = s.next() + s.nextLine();
        currentUser.setName(name);
        System.out.print("Username \n > ");
        currentUser.setUsername(s.next() + s.nextLine());
        System.out.print("Password \n > ");
        currentUser.setPassword(s.next() + s.nextLine());
        users.add(currentUser);
        System.out.println("Register Successful, please login");
        System.out.println("(Press 'Enter' to continue)");
        s.nextLine();
    }

    // login to existing account
    public void login() {
        System.out.print("Username: ");
        String username = s.next() + s.nextLine();
        System.out.print("Password: ");
        String password = s.next() + s.nextLine();
        for (User user : users) {
            if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                System.out.println("Login Successful! Welcome back " + user.getName() + "~");
                System.out.println(" ");
                currentUser = user;
                menu();
                return;
            }
        }
        System.out.println("User not found!");
        System.out.println(" ");
    }

    // menu after logging in
    public void menu() {
        while (true) {
            System.out.println("== MY STUDY ASSISTANT ==");
            dashboard();
            System.out.println(" ");
            System.out.println("========= MENU =========");
            System.out.println("1. Task");
            System.out.println("2. Schedule");
            System.out.println("3. Calendar");
            System.out.println("0. Logout");
            System.out.print("Choice: ");
            int choice = errorHandling(0, 3);
            System.out.println("========================");
            switch (choice) {
                case 0:
                    loginRegister();
                    break;
                case 1:
                    task();
                    break;
                case 2:
                    schedule();
                    break;
                case 3:
                    calendar();
                    break;
            }
        }

    }

    // dashboard
    public void dashboard() {

    }

    // menu 1: task
    public void task() {
        System.out.println("========= TASK =========");
        System.out.println("1. Create Task");
        System.out.println("2. Edit Task");
        System.out.println("3. Delete Task");
        System.out.println("4. View All Task");
        System.out.println("0. Back");
        System.out.print("Choice: ");
        int choice = errorHandling(0, 4);
        System.out.println("========================");
        switch (choice) {
            case 0:
                menu();
                break;
            case 1:
                createTask();
                break;
            case 2:
                editTask();
                break;
            case 3:
                delTask();
                break;
            case 4:
                viewTask();
                break;
        }
    }

    public void createTask() {
        System.out.print("Enter title: ");
        String title = s.next() + s.nextLine();
        System.out.print("Enter description: ");
        String description = s.next() + s.nextLine();
        System.out.print("Enter category (e.g., School, Work, Home): ");
        String category = s.next() + s.nextLine();
        System.out.print("Enter deadline (yyyy-MM-dd): ");
        String deadlineStr = s.next() + s.nextLine();
        try {
            Date deadline = dateFormat.parse(deadlineStr);
            currentUser.addTask(title, description, category, deadline);
            System.out.println("Task added successfully!");
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please try again.");
        }
    }

    public void editTask() {
        
    }

    public void delTask() {

    }

    public void viewTask() {

    }

    // menu 2: schedule
    public void schedule() {
        System.out.println("======= SCHEDULE =======");
        System.out.println("1. Create Schedule");
        System.out.println("2. Edit Schedule");
        System.out.println("3. Delete Schedule");
        System.out.println("4. View All Schedule");
        System.out.println("0. Back");
        System.out.print("Choice: ");
        int choice = errorHandling(0, 4);
        System.out.println("========================");
        switch (choice) {
            case 0:
                menu();
                break;
            case 1:
                createSchedule();
                break;
            case 2:
                editSchedule();
                break;
            case 3:
                delSchedule();
                break;
            case 4:
                viewSchedule();
                break;
        }
    }

    public void createSchedule() {
        System.out.print("Enter title: ");
        String title = s.next() + s.nextLine();
        System.out.print("Enter description: ");
        String description = s.next() + s.nextLine();
        System.out.print("Enter category (e.g., School, Work, Home): ");
        String category = s.next() + s.nextLine();
        System.out.print("Enter deadline (yyyy-MM-dd): ");
        String deadlineStr = s.next() + s.nextLine();
        try {
            Date deadline = dateFormat.parse(deadlineStr);
            currentUser.addTask(title, description, category, deadline);
            System.out.println("Task added successfully!");
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please try again.");
        }
    }

    public void editSchedule() {
        
    }

    public void delSchedule() {
        
    }

    public void viewSchedule() {
        
    }

    // menu 3: calendar
    public void calendar() {
        
    }

    // error handling for integer with limit for choice limit
    public int errorHandling(int lower, int upper) {
        int num = 0;
        try {
            num = s.nextInt();
            while (num < lower || num > upper) {
                System.out.println("Invalid choice! please reinput");
                num = s.nextInt();
            }
        } catch (InputMismatchException e) {
            System.out.println("Please input a valid integer!");
            s.nextLine();
            num = errorHandling(lower, upper);
        }
        return num;
    }
}
