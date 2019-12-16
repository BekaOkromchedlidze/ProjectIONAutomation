package mailhog;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class MIME {

    @JsonProperty("Parts")
    private List<Parts> Parts;

    public List<Parts> getParts(){
        return this.Parts;
    }

    public void setParts(List<Parts> Parts){
        this.Parts = Parts;
    }

}