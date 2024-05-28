
import java.time.LocalTime;
import java.util.*;

public class User {

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

}
