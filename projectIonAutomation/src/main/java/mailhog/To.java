package mailhog;

import com.fasterxml.jackson.annotation.JsonProperty;

public class To {

    @JsonProperty("Mailbox")
    private String Mailbox;
    @JsonProperty("Params")
    private String Params;
    @JsonProperty("Domain")
    private String Domain;
    @JsonProperty("Relays")
    private Object Relays;

    public String getMailbox(){
        return this.Mailbox;
    }

    public void setMailbox(String Mailbox){
        this.Mailbox = Mailbox;
    }

    public String getParams(){
        return this.Params;
    }

    public void setParams(String Params){
        this.Params = Params;
    }

    public String getDomain(){
        return this.Domain;
    }

    public void setDomain(String Domain){
        this.Domain = Domain;
    }

    public Object getRelays(){
        return this.Relays;
    }

    public void setRelays(Object Relays){
        this.Relays = Relays;
    }

}