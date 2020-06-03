package etc.mashalling;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="practice")
@Data
public class Practice02 {
    private Item item;


    @Data
    public static class Item {
        private String a;

    }
}
