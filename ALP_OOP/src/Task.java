
import java.util.*;

public class Task extends Item {

    private Date deadline;
    private PriorityStatus priorityStatus;
    private ProgressStatus progressStatus;
    private String dateStr;

    public Task(String title, String desc, String category, Date date, String dateStr) {
        super(title, desc, category, date, dateStr);
        this.deadline = date;
        this.priorityStatus = PriorityStatus.GREEN;
        this.progressStatus = ProgressStatus.NOT_STARTED;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public PriorityStatus getPriorityStatus() {
        return priorityStatus;
    }

    public void setPriorityStatus(PriorityStatus priorityStatus) {
        this.priorityStatus = priorityStatus;
    }

    public ProgressStatus getProgressStatus() {
        return progressStatus;
    }

    public void setProgressStatus(ProgressStatus progressStatus) {
        this.progressStatus = progressStatus;
    }
}
