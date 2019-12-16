package mailhog;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class Raw {

    @JsonProperty("Helo")
    private String Helo;
    @JsonProperty("Data")
    private String Data;
    @JsonProperty("From")
    private String From;
    @JsonProperty("To")
    private List<String> To;

    public String getHelo(){
        return this.Helo;
    }

    public void setHelo(String Helo){
        this.Helo = Helo;
    }

    public String getData(){
        return this.Data;
    }

    public void setData(String Data){
        this.Data = Data;
    }

    public String getFrom(){
        return this.From;
    }

    public void setFrom(String From){
        this.From = From;
    }

    public List<String> getTo(){
        return this.To;
    }

    public void setTo(List<String> To){
        this.To = To;
    }

}