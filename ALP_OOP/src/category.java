
import java.util.ArrayList;


public class Category {
    private String name_cat;
    private ArrayList<Schedule> listSchedule = new ArrayList<>();
    private ArrayList<Task> listTask = new ArrayList<>();

    public Category(String name_cat) {
        this.name_cat = name_cat;
    }

    public String getName_cat() {
        return name_cat;
    }

    public void setName_cat(String name_cat) {
        this.name_cat = name_cat;
    }

    public ArrayList<Schedule> getListSchedule() {
        return listSchedule;
    }

    public void setListSchedule(ArrayList<Schedule> listSchedule) {
        this.listSchedule = listSchedule;
    }

    public ArrayList<Task> getListTask() {
        return listTask;
    }

    public void setListTask(ArrayList<Task> listTask) {
        this.listTask = listTask;
    }
    
}
