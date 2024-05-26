
import java.util.Date;

public class Schedule extends Item {

    private Date startTime;
    private Date endTime;

    public Schedule(String title, String desc, String category, Date startTime, Date endTime) {
        super(title, desc, category);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }
}
