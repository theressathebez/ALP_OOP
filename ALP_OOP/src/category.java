
import java.util.ArrayList;


public class category {
    private String name;
    private ArrayList<Schedule> listSchedule = new ArrayList<>();
    private ArrayList<Task> listTask = new ArrayList<>();

    public category(String name_cat) {
        this.name = name_cat;
    }

    public String getName() {
        return name;
    }

    public void setName_cat(String name_cat) {
        this.name = name_cat;
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
