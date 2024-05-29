
import java.time.LocalTime;
import java.util.*;

public class User {
    Scanner s = new Scanner(System.in);

    private String Name, Username, Password;
    //list of task
    private ArrayList<Task> tasks = new ArrayList<>();

    //list of schedule
    private ArrayList<Schedule> schedules = new ArrayList<>();

    public User() {
        this.Name = " ";
        this.Username = " ";
        this.Password = " ";
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(ArrayList<Schedule> schedules) {
        this.schedules = schedules;
    }

    // create a new task
    public void addTask(String title, String description, String category, Date deadline) {
        Task newTask = new Task(title, description, category, deadline);
        tasks.add(newTask);
    }

    // create a new schedule
    public void addSchedule(String title, String desc, String category, Date date, LocalTime startTime, LocalTime endTime) {
        Schedule newSchedule = new Schedule(title, desc, category, date, startTime, endTime);
        schedules.add(newSchedule);
    }
    
    // fdisplays tasks but paged
    public void displayTasksPage(int pageNumber){
        int tasksPerPage = 4; //change this to change how much is displayed per page
        int startIndex = (pageNumber-1) * tasksPerPage;
        int endIndex = startIndex + tasksPerPage;
        
        for(int i = startIndex; i < endIndex; i++){
            //display task based on i
            System.out.println("===================");
            Task task = this.getTasks().get(i);
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
        
        String input = s.next() + s.nextLine();
        
        //scan for next action
        //can click 'q' to exit, or anything really
        
        // this regex checks if input is integer, if not then no
        if(input.matches("^[0-9]+$")){
            
        }else if(input.equalsIgnoreCase("p")){
            // prev page
        if(pageNumber != 1){
           displayTasksPage(pageNumber-1); 
        }else{
            System.out.println("Cannot go to page < 1");
        }
        }else if(input.equalsIgnoreCase("n")){
            // choose nextPage
            displayTasksPage(pageNumber + 1);
        }

        
        
    }

}
