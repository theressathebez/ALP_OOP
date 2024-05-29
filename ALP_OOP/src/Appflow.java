
import java.util.*;
import java.text.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Appflow {

    Scanner s = new Scanner(System.in);
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    // list of all user registered
    ArrayList<User> users = new ArrayList<>();

    //user rn
    User currentUser = new User();

    //list Category
    ArrayList<Category> categories = new ArrayList<>();

    // boarding
    public void start() {
        System.out.println("### STUDY ASSISTANT ###");
        System.out.println("####### WELCOME #######");
        System.out.println(" ");

        // Add default categories
        categories.add(new Category("Work"));
        categories.add(new Category("School"));
        categories.add(new Category("Home"));

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
            System.out.println(" ");
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

        
//        System.out.println(" ");
//        System.out.println("== CREATE NEW CATEGORY ==");
//        System.out.print("Enter Category name: ");
//        String name = s.next() + s.nextLine();
//        categories.add(new Category(name));
//        System.out.println("========================");
//        System.out.println(" ");
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
        System.out.println(" ");
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
        System.out.print("Choice: ");
        int choice = errorHandling(1, currentUser.getTasks().size());

    }

    public void delTask() {
        System.out.print("Choice: ");
        int choice = errorHandling(1, currentUser.getTasks().size());
        System.out.println("Deleting " + currentUser.getTasks().get(choice).getTitle());
        currentUser.getTasks().remove(choice);
        System.out.println("Deleted!");
    }

    public void viewTask() {
        System.out.println("== View All Tasks ==");
        int i = 1;
        for (Task task : currentUser.getTasks()) {
            System.out.println("===================");
            System.out.println("[" + i + "] " + task.getTitle());
            System.out.println("'" + task.getDesc() + "'");
            System.out.println("Categories : \n");
            System.out.println("* " + task.getDeadline());
            System.out.print("Priority: ");
            if (task.getPriorityStatus() == PriorityStatus.GREEN) {
                System.out.println("\u001B[32m" + task.getPriorityStatus() + "\u001B[0m");
            } else if (task.getPriorityStatus() == PriorityStatus.YELLOW) {
                System.out.println("\u001B[33m" + task.getPriorityStatus() + "\u001B[0m");
            } else if (task.getPriorityStatus() == PriorityStatus.RED) {
                System.out.println("\u001B[31m" + task.getPriorityStatus() + "\u001B[0m");
            }
            System.out.println("** " + task.getProgressStatus() + " ** \n");
        }
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
        System.out.println("Select category: ");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i).getName());
        }
        System.out.print("Choose: ");
        int categoryChoice = errorHandling(0, categories.size());
        Category selectedCategory = categories.get(categoryChoice - 1);
        System.out.print("Enter date (dd-MM-yyyy): ");
        String date = s.next() + s.nextLine();
        System.out.print("Enter start time (HH:mm): ");
        String start = s.next() + s.nextLine();
        System.out.print("Enter end time (HH:mm): ");
        String end = s.next() + s.nextLine();
        try {
            Date dateObj = dateFormat.parse(date);
            LocalTime startTime = LocalTime.parse(start, timeFormatter);
            LocalTime endTime = LocalTime.parse(end, timeFormatter);
            currentUser.addSchedule(title, description, selectedCategory.getName(), dateObj, startTime, endTime);
            System.out.println("Schedule added successfully!");
            System.out.println(" ");
            schedule();
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please try again.");
            System.out.println(" ");
        } catch (DateTimeParseException e) {
            System.out.println("Invalid time format. Please try again.");
            System.out.println(" ");
        }

    }

    public void editSchedule() {

    }

    public void delSchedule() {
        int i = 1;
        for (Schedule schedule : currentUser.getSchedules()) {
            System.out.println("[" + i + "] " + schedule.getTitle());
        }
        System.out.print("Choice: ");
        int choice = errorHandling(1, currentUser.getTasks().size());
        System.out.println("Deleting " + currentUser.getTasks().get(choice-1).getTitle());
        currentUser.getTasks().remove(choice-1);
        System.out.println("Deleted!");
    }

    public void viewSchedule() {
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("EEEE, dd MMM yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        System.out.println("== View All Schedules ==");
        int i = 1;
        for (Schedule schedule : currentUser.getSchedules()) {
            String formattedDate = outputDateFormat.format(schedule.getDate());
            String formattedStartTime = schedule.getStartTime().format(timeFormatter);
            String formattedEndTime = schedule.getEndTime().format(timeFormatter);

            System.out.println("[" + i + "] " + schedule.getTitle());
            System.out.println("'" + schedule.getDesc() + "'");
            System.out.println("Categories: " + schedule.getCategory());
            System.out.println("Date: " + formattedDate);
            System.out.println("Time: " + formattedStartTime + " - " + formattedEndTime);
            System.out.println(" ");
        }

        System.out.print("Is there any schedule that is finished [Y/N]? ");
        String a = s.next() + s.nextLine();
        if (a.equalsIgnoreCase("Y")) {
            System.out.print("Which schedule is finished? ");
            int noSch = errorHandling(1, currentUser.getSchedules().size());
            currentUser.getSchedules().remove(noSch - 1);
            System.out.println("Schedule completed!");
        } else if (a.equalsIgnoreCase("N")) {
            schedule();
        } else {
            System.out.println("Please input Y/N!");
        }
    }

    // menu 3: calendar
    public void calendar() {

        System.out.println("========================");
        System.out.println("========================");
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
