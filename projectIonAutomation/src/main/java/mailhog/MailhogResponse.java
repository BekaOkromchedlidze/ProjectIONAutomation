package mailhog;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MailhogResponse {
	@JsonProperty("total")
    private int total;
	@JsonProperty("count")
    private int count;
	@JsonProperty("start")
    private int start;
    @JsonProperty("items")
    private List<Items> Items;
	
	
	public List<Items> getItems() {
		return Items;
	}
	public void setItems(List<Items> items) {
		Items = items;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}

	
}
