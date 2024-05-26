
import java.util.ArrayList;
import java.util.Date;


public class Manage {
    //list of task
    private ArrayList<Task> tasks = new ArrayList<>();
    
    //list of schedule
    private ArrayList<Schedule> schedules = new ArrayList<>();

    public Manage() {
        tasks = new ArrayList<>();
        schedules = new ArrayList<>();
    }
    
    // create a new task
    public void addTask(String title, String description, String category, Date deadline) {
        Task newTask = new Task(title, description, category, deadline);
        tasks.add(newTask);
    }

    // create a new schedule
    public void addSchedule(String title, String description, String category, Date startTime, Date endTime) {
        Schedule newSchedule = new Schedule(title, description, category, startTime, endTime);
        schedules.add(newSchedule);
    }
    
    
}
