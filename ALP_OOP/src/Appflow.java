
import java.util.*;
import java.text.*;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

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

        try {
            BufferedReader reader = new BufferedReader(new FileReader("users.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                User newUser = new User();
                newUser.setName(line);
                newUser.setUsername(reader.readLine());
                newUser.setPassword(reader.readLine());
                users.add(newUser);
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("");
        } catch (IOException ex) {
            Logger.getLogger(Appflow.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        User newUser = new User();
        System.out.print("Name: ");
        String name = s.next() + s.nextLine();
        newUser.setName(name);
        System.out.print("Username \n > ");
        newUser.setUsername(s.next() + s.nextLine());
        System.out.print("Password \n > ");
        newUser.setPassword(s.next() + s.nextLine());
        users.add(newUser);
        addUsertoFile();
        System.out.println("Register Successful, please login");
        System.out.println("(Press 'Enter' to continue)");
        s.nextLine();

    }

    public void addUsertoFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt"));
            for (User user : users) {
                writer.write(user.getName() + "\n");
                writer.write(user.getUsername() + "\n");
                writer.write(user.getPassword() + "\n");
            }
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(Appflow.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            try {
                BufferedReader reader = new BufferedReader(new FileReader(currentUser.getUsername() + "Tasks.txt"));
                String line;
                while ((line = reader.readLine()) != null) {
                    String title = line;
                    String desc = reader.readLine();
                    String category = reader.readLine();
                    String dateStr = reader.readLine();
                    Date date = null;
                    try {
                        date = dateFormat.parse(dateStr);
                    } catch (ParseException e) {
                        Logger.getLogger(Appflow.class.getName()).log(Level.SEVERE, "Invalid date format in file", e);
                        continue;
                    }
                    Task newTask = new Task(title, desc, category, date, dateStr);
                    String priority = reader.readLine();
                    if (priority.equalsIgnoreCase("GREEN")) {
                        newTask.setPriorityStatus(PriorityStatus.GREEN);
                    } else if (priority.equalsIgnoreCase("YELLOW")) {
                        newTask.setPriorityStatus(PriorityStatus.YELLOW);
                    } else if (priority.equalsIgnoreCase("RED")) {
                        newTask.setPriorityStatus(PriorityStatus.RED);
                    }
                    String progress = reader.readLine();
                    if (progress.equalsIgnoreCase("NOT_STARTED")) {
                        newTask.setProgressStatus(ProgressStatus.NOT_STARTED);
                    } else if (progress.equalsIgnoreCase("IN_PROGRESS")) {
                        newTask.setProgressStatus(ProgressStatus.IN_PROGRESS);
                    } else if (progress.equalsIgnoreCase("DONE")) {
                        newTask.setProgressStatus(ProgressStatus.DONE);
                    }
                    currentUser.getTasks().add(newTask);
                }
                reader.close();
            } catch (FileNotFoundException ex) {
                System.out.println("File not inserted");;
            } catch (IOException ex) {
                Logger.getLogger(Appflow.class.getName()).log(Level.SEVERE, null, ex);
            }

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
                    calendar(1);
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
        Collections.sort(currentUser.getTasks(), new Comparator<Item>() {
            @Override
            public int compare(Item one, Item two) {
                return one.getDate().compareTo(two.getDate());
            }
        });
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
                System.out.println("1. Sort by Date");
                System.out.println("2. Sort by Priority Status");
                System.out.println("3. Sort by Progress Status");
                int input = errorHandling(1, 3);
                if (input == 1) {
                    displayTask();
                } else if (input == 2) {
                    displayTaskbyPriority();
                } else if (input == 3) {
                    displayTaskbyProgress();
                }
                break;
        }
    }

    public void createTask() {
        System.out.print("Enter title: ");
        String title = s.next() + s.nextLine();
        System.out.print("Enter description: ");
        String description = s.next() + s.nextLine();
        System.out.println("Select category: ");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i).getName());
        }
        System.out.print("Choose: ");
        int categoryChoice = errorHandling(1, categories.size());
        Category selectedCategory = categories.get(categoryChoice - 1);
        System.out.print("Enter date (dd-MM-yyyy): ");
        String deadlineStr = s.next() + s.nextLine();
        try {
            Date deadline = dateFormat.parse(deadlineStr);
            currentUser.addTask(title, description, selectedCategory.getName(), deadline, deadlineStr);
            System.out.println("Task added successfully!");
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please try again.");
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(currentUser.getUsername() + "Tasks.txt"));
            for (Task task : currentUser.getTasks()) {
                writer.write(task.getTitle() + "\n");
                writer.write(task.getDesc() + "\n");
                writer.write(task.getCategory() + "\n");
                writer.write(task.getDateStr() + "\n");
                writer.write(task.getPriorityStatus() + "\n");
                writer.write(task.getProgressStatus() + "\n");
            }
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(Appflow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editTask() {
        displayTask();
        System.out.print("0. Back\nChoice: ");
        int choice = errorHandling(0, currentUser.getTasks().size());
        if (choice != 0) {
            choice--;
            Task chosen = currentUser.getTasks().get(choice);
            System.out.println("== Task details ==");
            System.out.println("1. Name: " + chosen.getTitle());
            System.out.println("2. Desc: " + chosen.getDesc());
            System.out.println("3. Category: " + chosen.getCategory());
            System.out.print("4. Priority: ");
            getPriorityStatusColour(chosen);
            System.out.println("5. Deadline: " + chosen.getDeadline());
            System.out.println("6. Status: " + chosen.getProgressStatus());
            System.out.println("What would you like to edit?");
            int editChoice = errorHandling(0, 6);
            switch (editChoice) {
                case 1:
                    //name
                    System.out.println("Current Name: " + chosen.getTitle());
                    System.out.print("New Name: ");
                    chosen.setTitle(s.next() + s.nextLine());
                    System.out.println("Successfully Changed Title!");
                    break;
                case 2:
                    //description
                    System.out.println("Current Description: " + chosen.getDesc());
                    System.out.print("New Description: ");
                    chosen.setDesc(s.next() + s.nextLine());
                    System.out.println("Successfully Changed Description!");
                    break;
                case 3:
                    //category
                    System.out.println("Current Category: " + chosen.getCategory());
                    System.out.println("New Category: ");
                    for (int i = 0; i < categories.size(); i++) {
                        System.out.println((i + 1) + ". " + categories.get(i).getName());
                    }
                    System.out.print("Input: ");
                    int input = errorHandling(1, categories.size());
                    System.out.println("Successfully Changed Category!");
                    break;
                case 4:
                    //priority
                    System.out.print("Current Priority Status: ");
                    getPriorityStatusColour(chosen);
                    System.out.println("New Priority Status: ");
                    System.out.println("1. \u001B[32m GREEN \u001B[0m");
                    System.out.println("2. \u001B[33m YELLOW \u001B[0m");
                    System.out.println("3. \u001B[31m RED \u001B[0m");
                    System.out.print("Input: ");
                    int inputPriority = errorHandling(1, 3);
                    if (inputPriority == 1) {
                        chosen.setPriorityStatus(PriorityStatus.GREEN);
                    } else if (inputPriority == 2) {
                        chosen.setPriorityStatus(PriorityStatus.YELLOW);
                    } else {
                        chosen.setPriorityStatus(PriorityStatus.RED);
                    }
                    System.out.println("Successfully Changed Priority Status!");
                    break;
                case 5:
                    //deadline
                    System.out.println("Current Deadline: " + chosen.getDeadline());
                    System.out.print("New Deadline: ");
                    System.out.print("Enter date (dd-MM-yyyy): ");
                    String deadlineStr = s.next() + s.nextLine();
                    try {
                        Date deadline = dateFormat.parse(deadlineStr);
                        chosen.setDate(deadline);
                        chosen.setDeadline(deadline);
                        System.out.println("Successfully Changed Deadline!");
                    } catch (ParseException e) {
                        System.out.println("Invalid date format. Please try again.");
                    }
                    break;
                case 6:
                    //status
                    System.out.println("Current Progress: " + chosen.getProgressStatus());
                    System.out.println("New Progress: ");
                    System.out.println("1. Not Started");
                    System.out.println("2. In Progress");
                    System.out.println("3. Done");
                    System.out.print("Input: ");
                    int inputProgress = errorHandling(1, 3);
                    if (inputProgress == 1) {
                        chosen.setProgressStatus(ProgressStatus.NOT_STARTED);
                    } else if (inputProgress == 2) {
                        chosen.setProgressStatus(ProgressStatus.IN_PROGRESS);
                    } else {
                        chosen.setProgressStatus(ProgressStatus.DONE);
                    }
                    System.out.println("Successfully Changed Progress Status!");
                    break;
            }

            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(currentUser.getUsername() + "Tasks.txt"));
                for (Task task : currentUser.getTasks()) {
                    writer.write(task.getTitle() + "\n");
                    writer.write(task.getDesc() + "\n");
                    writer.write(task.getCategory() + "\n");
                    writer.write(task.getDateStr() + "\n");
                    writer.write(task.getPriorityStatus() + "\n");
                    writer.write(task.getProgressStatus() + "\n");
                }
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(Appflow.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            return;
        }
    }

    public void delTask() {
        displayTask();
        System.out.println("0. Back");
        System.out.print("Choice: ");
        int choice = errorHandling(0, currentUser.getTasks().size());
        choice--;
        System.out.println("Deleting " + currentUser.getTasks().get(choice).getTitle());
        currentUser.getTasks().remove(currentUser.getTasks().get(choice));
        System.out.println("Deleted!");
        try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(currentUser.getUsername() + "Tasks.txt"));
                for (Task task : currentUser.getTasks()) {
                    writer.write(task.getTitle() + "\n");
                    writer.write(task.getDesc() + "\n");
                    writer.write(task.getCategory() + "\n");
                    writer.write(task.getDateStr() + "\n");
                    writer.write(task.getPriorityStatus() + "\n");
                    writer.write(task.getProgressStatus() + "\n");
                }
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(Appflow.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    public void viewTask() {
        displayTask();
        System.out.println("0. Back");
        System.out.println("View Details of..");
        System.out.print("Choice: ");
        int choice = errorHandling(0, currentUser.getTasks().size());
        if (choice != 0) {
            choice--;
            Task chosen = currentUser.getTasks().get(choice);
            System.out.println("== Task details ==");
            System.out.println("Name: " + chosen.getTitle());
            System.out.println("Desc: " + chosen.getDesc());
            System.out.println("Category: " + chosen.getCategory());
            System.out.print("Priority: ");
            getPriorityStatusColour(chosen);
            System.out.println("Deadline: " + chosen.getDeadline());
            System.out.println("Status: " + chosen.getProgressStatus());
        } else {
            return;
        }
    }

    public void displayTask() {
        System.out.println("== View All Tasks ==");
        int i = 1;
        for (Task task : currentUser.getTasks()) {
            System.out.println("===================");
            System.out.println("[" + i + "] " + task.getTitle());
            System.out.println("'" + task.getDesc() + "'");
            System.out.println("Categories : ");
            System.out.println(task.getCategory());
            System.out.println("* " + task.getDate());
            System.out.print("Priority: ");
            getPriorityStatusColour(task);
            System.out.println("** " + task.getProgressStatus() + " ** \n");
        }
    }

    public void displayTaskbyPriority() {
        System.out.println("== All Tasks by Priority ==");
        int i = 1;
        System.out.println("== \u001B[31m RED \u001B[0m ==");
        for (Task task : currentUser.getTasks()) {
            if (task.getPriorityStatus() == PriorityStatus.RED) {
                System.out.println("===================");
                System.out.println("[" + i + "] " + task.getTitle());
                System.out.println("'" + task.getDesc() + "'");
                System.out.println("Categories : \n");
                System.out.println("* " + task.getDate());
                System.out.print("Priority: ");
                getPriorityStatusColour(task);
                System.out.println("** " + task.getProgressStatus() + " ** \n");
            }
        }
        System.out.println("== \u001B[33m YELLOW \u001B[0m ==");
        for (Task task : currentUser.getTasks()) {
            if (task.getPriorityStatus() == PriorityStatus.YELLOW) {
                System.out.println("===================");
                System.out.println("[" + i + "] " + task.getTitle());
                System.out.println("'" + task.getDesc() + "'");
                System.out.println("Categories : \n");
                System.out.println("* " + task.getDate());
                System.out.print("Priority: ");
                getPriorityStatusColour(task);
                System.out.println("** " + task.getProgressStatus() + " ** \n");
            }
        }
        System.out.println("== \u001B[32m GREEN \u001B[0m ==");
        for (Task task : currentUser.getTasks()) {
            if (task.getPriorityStatus() == PriorityStatus.GREEN) {
                System.out.println("===================");
                System.out.println("[" + i + "] " + task.getTitle());
                System.out.println("'" + task.getDesc() + "'");
                System.out.println("Categories : \n");
                System.out.println("* " + task.getDate());
                System.out.print("Priority: ");
                getPriorityStatusColour(task);
                System.out.println("** " + task.getProgressStatus() + " ** \n");
            }
        }
    }

    public void displayTaskbyProgress() {
        System.out.println("== All Tasks by Progress ==");
        int i = 1;
        System.out.println("== NOT STARTED ==");
        for (Task task : currentUser.getTasks()) {
            if (task.getProgressStatus() == ProgressStatus.NOT_STARTED) {
                System.out.println("===================");
                System.out.println("[" + i + "] " + task.getTitle());
                System.out.println("'" + task.getDesc() + "'");
                System.out.println("Categories : \n");
                System.out.println("* " + task.getDate());
                System.out.print("Priority: ");
                getPriorityStatusColour(task);
                System.out.println("** " + task.getProgressStatus() + " ** \n");
            }
        }
        System.out.println("== IN PROGRESS ==");
        for (Task task : currentUser.getTasks()) {
            if (task.getProgressStatus() == ProgressStatus.IN_PROGRESS) {
                System.out.println("===================");
                System.out.println("[" + i + "] " + task.getTitle());
                System.out.println("'" + task.getDesc() + "'");
                System.out.println("Categories : \n");
                System.out.println("* " + task.getDate());
                System.out.print("Priority: ");
                getPriorityStatusColour(task);
                System.out.println("** " + task.getProgressStatus() + " ** \n");
            }
        }
        System.out.println("== DONE ==");
        for (Task task : currentUser.getTasks()) {
            if (task.getProgressStatus() == ProgressStatus.DONE) {
                System.out.println("===================");
                System.out.println("[" + i + "] " + task.getTitle());
                System.out.println("'" + task.getDesc() + "'");
                System.out.println("Categories : \n");
                System.out.println("* " + task.getDate());
                System.out.print("Priority: ");
                getPriorityStatusColour(task);
                System.out.println("** " + task.getProgressStatus() + " ** \n");
            }
        }
    }

    public void getPriorityStatusColour(Task task) {
        if (task.getPriorityStatus() == PriorityStatus.GREEN) {
            System.out.println("\u001B[32m" + task.getPriorityStatus() + "\u001B[0m");
        } else if (task.getPriorityStatus() == PriorityStatus.YELLOW) {
            System.out.println("\u001B[33m" + task.getPriorityStatus() + "\u001B[0m");
        } else if (task.getPriorityStatus() == PriorityStatus.RED) {
            System.out.println("\u001B[31m" + task.getPriorityStatus() + "\u001B[0m");
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
            currentUser.addSchedule(title, description, selectedCategory.getName(), dateObj, date, startTime, endTime);
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
        System.out.println("Deleting " + currentUser.getTasks().get(choice - 1).getTitle());
        currentUser.getTasks().remove(choice - 1);
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
    public void calendar(int pageNumber) {
        System.out.println("0. Back to Menu");
        System.out.println("========================");
        int itemsPerPage = 7; //how many items displayed per page
        int startIndex = (pageNumber - 1) * itemsPerPage;
        int endIndex = startIndex + itemsPerPage;

        List<Item> items = new ArrayList<>();
        items.addAll(currentUser.getTasks());
        items.addAll(currentUser.getSchedules());

        //sort using collection sort
        Collections.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item one, Item two) {
                return one.getDate().compareTo(two.getDate());
            }
        });

        //page
        for (int i = startIndex; i < endIndex && i < items.size(); i++) {
            Item item = items.get(i);
            System.out.println("== " + item.getDate() + " ==");
            if (item instanceof Task) {
                Task taskItem = (Task) item;
                System.out.println((i + 1) + ". " + taskItem.getTitle());
                System.out.println("Deadline: " + taskItem.getDeadline());
                System.out.println("Category: " + taskItem.getCategory());
                System.out.println("Priority: " + taskItem.getPriorityStatus());
                System.out.println("Progress: " + taskItem.getProgressStatus());
            } else if (item instanceof Schedule) {
                Schedule scheduleItem = (Schedule) item;
                System.out.println((i + 1) + ". " + scheduleItem.getTitle());
                System.out.println("Date: " + scheduleItem.getDate());
                System.out.println("Start Time: " + scheduleItem.getStartTime());
                System.out.println("End Time: " + scheduleItem.getEndTime());
                System.out.println("Category: " + scheduleItem.getCategory());
            }
        }
        while (true) {
            System.out.print("[N]ext page\n[P]rev page\n[Q]uit\nInput: ");
            String input = s.next() + s.nextLine();

            //scan for next action
            //can click 'q' to exit, or anything really
            // this regex checks if input is integer, if not then no
            if (input.equalsIgnoreCase("q")) {
                return;
            }

            if (input.equalsIgnoreCase("p")) {
                // prev page
                if (pageNumber != 1) {
                    calendar(pageNumber - 1);
                    return;
                } else {
                    System.out.println("Cannot go to page < 1");
                }
            } else if (input.equalsIgnoreCase("n")) {
                // choose nextPage
                calendar(pageNumber + 1);
                return;
            } else if (!input.matches("^[0-9]+$")) {
//            not an integer or any o them
                System.out.println("Incorrect !");
            } else {
//                insert task selection thing
                int inputNumber = Integer.parseInt(input) - 1;
                if (inputNumber == -1) {
                    return;
                }
                return;
            }
        }

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
