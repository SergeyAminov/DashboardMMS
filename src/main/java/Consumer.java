import dto.ProductDto;
import service.MessageService;

import javax.annotation.PostConstruct;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.enterprise.inject.Produces;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.inject.Inject;

import javax.inject.Named;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

@Named
@MessageDriven(name = "Consumer",
activationConfig = {
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:jboss/exported/jms/queue/myQueue")
})
public class Consumer implements MessageListener {

    @Inject
    @Push(channel = "messageChannel")
    private PushContext messageChannel;

    @Inject
    private MessageService messageService;

    final private Client client = ClientBuilder.newClient();

    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        ProductDto[] productDtoArray = this.retrieveProductArray();
        try{
            List<ProductDto> productDtoList = Arrays.asList(productDtoArray);
            for (ProductDto productDto : productDtoList)
                System.out.println(productDto.toString());
            this.messageService.setProductDtoList(productDtoList);
            this.messageChannel.send(textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public ProductDto[] retrieveProductArray() {
        Response response =
                this.client.target("http://localhost:9090/api/message")
                        .request()
                        .get();
        return response.readEntity(ProductDto[].class);
    }

}
