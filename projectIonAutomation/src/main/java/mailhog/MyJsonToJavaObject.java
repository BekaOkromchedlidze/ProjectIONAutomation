package mailhog;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class MyJsonToJavaObject {

    @JsonProperty("total")
    private Integer total;
    @JsonProperty("count")
    private Integer count;
    @JsonProperty("start")
    private Integer start;
    @JsonProperty("items")
    private List<Items> items;

    public Integer getTotal(){
        return this.total;
    }

    public void setTotal(Integer total){
        this.total = total;
    }

    public Integer getCount(){
        return this.count;
    }

    public void setCount(Integer count){
        this.count = count;
    }

    public Integer getStart(){
        return this.start;
    }

    public void setStart(Integer start){
        this.start = start;
    }

    public List<Items> getItems(){
        return this.items;
    }

    public void setItems(List<Items> items){
        this.items = items;
    }

}