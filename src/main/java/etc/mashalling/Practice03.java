package etc.mashalling;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="practice")
public class Practice03 {
    private List<Item> items;

    @XmlElementWrapper(name="items")
    @XmlElement(name="item")
    public List<Item> getItems(){
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @XmlRootElement(name="item")
    @Data
    public static class Item {
        private String a;

    }
}



