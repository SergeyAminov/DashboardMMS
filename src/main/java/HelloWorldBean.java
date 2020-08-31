import java.io.Serializable;

import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named("hello")
@ViewScoped
public class HelloWorldBean implements Serializable {

    private String helloMessage = "Hello World from HelloWorldBean";

    public String getHelloMessage() {
        return helloMessage;
    }

    public void setHelloMessage(String helloMessage) {
        this.helloMessage = helloMessage;
    }
}