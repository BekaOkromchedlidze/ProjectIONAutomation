package mailhog;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Items {

    @JsonProperty("MIME")
    private MIME MIME;
    @JsonProperty("Content")
    private Content Content;
    @JsonProperty("Raw")
    private Raw Raw;
    @JsonProperty("ID")
    private String ID;
    @JsonProperty("From")
    private From From;
    @JsonProperty("To")
    private List<To> To;
    @JsonProperty("Created")
    private String Created;
    

    public MIME getMIME(){
        return this.MIME;
    }

    public void setMIME(MIME MIME){
        this.MIME = MIME;
    }

    public Content getContent(){
        return this.Content;
    }

    public void setContent(Content Content){
        this.Content = Content;
    }

    public Raw getRaw(){
        return this.Raw;
    }

    public void setRaw(Raw Raw){
        this.Raw = Raw;
    }

    public String getID(){
        return this.ID;
    }

    public void setID(String ID){
        this.ID = ID;
    }

    public From getFrom(){
        return this.From;
    }

    public void setFrom(From From){
        this.From = From;
    }

    public List<To> getTo(){
        return this.To;
    }

    public void setTo(List<To> To){
        this.To = To;
    }

    public String getCreated(){
        return this.Created;
    }

    public void setCreated(String Created){
        this.Created = Created;
    }

}