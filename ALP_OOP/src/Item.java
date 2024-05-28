
import java.util.*;


public class Item {
    protected String title, desc, category;

    public Item(String title, String desc, String category) {
        this.title = title;
        this.desc = desc;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getCategory() {
        return category;
    }
    
}
