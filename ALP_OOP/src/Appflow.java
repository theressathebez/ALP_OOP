
import java.util.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Felicia
 */
public class Appflow {
    
    Scanner s = new Scanner(System.in);
    
    // list of all user registered
    ArrayList<User> users = new ArrayList<>();
    
    //user rn
    User currentUser = new User();
    
    // boarding
    public void start() {
        System.out.println("STUDY ASSISTANT");
        System.out.println("Welcome!");
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
        for(User user : users){
            if (username.equals(user.getUsername()) && password.equals(user.getPassword())){
                System.out.println("Login Successful! Welcome back " + user.getName());
                currentUser = user;
                menu();
                return;
            }
        }
        System.out.println("User not found!");
    }
    
    // menu after logging in
    public void menu() {
        System.out.println("0. Logout");
        int choice = errorHandling(0, 1);
        switch(choice){
            case 0:
        }
    }

    // dashboard
    public void dashboard(){
        
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
