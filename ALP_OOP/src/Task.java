
import java.util.Date;

public class Task extends Item {

    private Date deadline;

    public Task(String title, String desc, String category, Date deadline) {
        super(title, desc, category);
        this.deadline = deadline;
    }

    public Date getDeadline() {
        return deadline;
    }

}
