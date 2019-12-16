package mailhog;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class Headers {

    @JsonProperty("Content-Transfer-Encoding")
    private List<String> ContentTransferEncoding;
    @JsonProperty("Content-Type")
    private List<String> ContentType;
    @JsonProperty("Reply-To")
    private List<String> ReplyTo;
    @JsonProperty("Return-Path")
    private List<String> ReturnPath;
    @JsonProperty("Received")
    private List<String> Received;
    @JsonProperty("From")
    private List<String> From;
    @JsonProperty("Message-ID")
    private List<String> MessageID;
    @JsonProperty("To")
    private List<String> To;
    @JsonProperty("Date")
    private List<String> Date;
    @JsonProperty("MIME-Version")
    private List<String> MIMEVersion;
    @JsonProperty("Subject")
    private List<String> Subject;
    @JsonProperty("X-Priority")
    private List<String> XPriority;

    public List<String> getContentTransferEncoding(){
        return this.ContentTransferEncoding;
    }

    public void setContentTransferEncoding(List<String> ContentTransferEncoding){
        this.ContentTransferEncoding = ContentTransferEncoding;
    }

    public List<String> getContentType(){
        return this.ContentType;
    }

    public void setContentType(List<String> ContentType){
        this.ContentType = ContentType;
    }

    public List<String> getReplyTo(){
        return this.ReplyTo;
    }

    public void setReplyTo(List<String> ReplyTo){
        this.ReplyTo = ReplyTo;
    }

    public List<String> getReturnPath(){
        return this.ReturnPath;
    }

    public void setReturnPath(List<String> ReturnPath){
        this.ReturnPath = ReturnPath;
    }

    public List<String> getReceived(){
        return this.Received;
    }

    public void setReceived(List<String> Received){
        this.Received = Received;
    }

    public List<String> getFrom(){
        return this.From;
    }

    public void setFrom(List<String> From){
        this.From = From;
    }

    public List<String> getMessageID(){
        return this.MessageID;
    }

    public void setMessageID(List<String> MessageID){
        this.MessageID = MessageID;
    }

    public List<String> getTo(){
        return this.To;
    }

    public void setTo(List<String> To){
        this.To = To;
    }

    public List<String> getDate(){
        return this.Date;
    }

    public void setDate(List<String> Date){
        this.Date = Date;
    }

    public List<String> getMIMEVersion(){
        return this.MIMEVersion;
    }

    public void setMIMEVersion(List<String> MIMEVersion){
        this.MIMEVersion = MIMEVersion;
    }

    public List<String> getSubject(){
        return this.Subject;
    }

    public void setSubject(List<String> Subject){
        this.Subject = Subject;
    }

    public List<String> getXPriority(){
        return this.XPriority;
    }

    public void setXPriority(List<String> XPriority){
        this.XPriority = XPriority;
    }

}