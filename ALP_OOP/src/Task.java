import java.util.*;

public class Task extends Item {

    private Date deadline;
    private PriorityStatus priorityStatus;
    private ProgressStatus progressStatus;

    public Task(String title, String desc, String category, Date deadline) {
        super(title, desc, category);
        this.deadline = deadline;
        this.priorityStatus = PriorityStatus.GREEN;
        this.progressStatus = ProgressStatus.NOT_STARTED;
    }

    public Date getDeadline() {
        return deadline;
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