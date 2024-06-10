
import java.util.*;


public class Item {
    protected String title, desc, category, dateStr;
    protected Date date;

    public Item(String title, String desc, String category, Date date, String dateStr) {
        this.title = title;
        this.desc = desc;
        this.category = category;
        this.date = date;
        this.dateStr = dateStr;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    
}
