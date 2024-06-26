
import java.time.LocalTime;
import java.util.Date;

public class Schedule extends Item {

    private LocalTime startTime, endTime;
    private Date date;

    public Schedule(String title, String desc, String category,Date date, String dateStr, LocalTime startTime, LocalTime endTime) {
        super(title, desc, category, date, dateStr);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }


    
}
