package mailhog;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Parts {

    @JsonProperty("MIME")
    private Object MIME;
    @JsonProperty("Headers")
    private Headers Headers;
    @JsonProperty("Size")
    private Integer Size;
    @JsonProperty("Body")
    private String Body;

    public Object getMIME(){
        return this.MIME;
    }

    public void setMIME(Object MIME){
        this.MIME = MIME;
    }

    public Headers getHeaders(){
        return this.Headers;
    }

    public void setHeaders(Headers Headers){
        this.Headers = Headers;
    }

    public Integer getSize(){
        return this.Size;
    }

    public void setSize(Integer Size){
        this.Size = Size;
    }

    public String getBody(){
        return this.Body;
    }

    public void setBody(String Body){
        this.Body = Body;
    }

}