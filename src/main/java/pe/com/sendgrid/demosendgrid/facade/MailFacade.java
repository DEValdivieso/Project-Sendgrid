package pe.com.sendgrid.demosendgrid.facade;

import com.google.gson.Gson;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pe.com.sendgrid.demosendgrid.canonical.Product;
import pe.com.sendgrid.demosendgrid.util.SendgridProperties;

import java.util.ArrayList;
import java.util.List;

@Component
public class MailFacade {

    @Value("${email.from}")
    private String emailFrom;

    @Value("${email.to}")
    private String emailTo;

    @Value("${email.apiKey}")
    private String emailApiKey;

    @Value("${email.templateId}")
    private String emailTemplateId;

    public void sendEmail(){
        System.out.println("sendEmail");

        String apy_key=emailApiKey;
        String templateId=emailTemplateId;
        String fromS=emailFrom;
        String toS=emailTo;

        Email from = new Email(fromS);
        String subject = "Sending with SendGrid is Fun";
        Email to = new Email(toS);
        Content content = new Content("text/html", "and easy to do anywhere, even with Java");
        Mail mail = new Mail(from, subject, to, content);
        mail.setTemplateId(templateId);


        Personalization personalization=new Personalization();
        personalization.addDynamicTemplateData("name","Omar Jet");
        personalization.addDynamicTemplateData("orderEdited",true);

        Product product=new Product();
        product.setSku("014989");
        product.setName("Nombre 1");

        Product product2=new Product();
        product2.setSku("014989");
        product2.setName("Nombre 12");

        List<Product> products=new ArrayList<Product>();
        products.add(product);
        products.add(product2);
        personalization.addDynamicTemplateData("products",products);
       /* Product product=new Product();
        product.setSku("014989");

        Products listaProductos=new Products();
        listaProductos.getProducts().add(product);
        personalization.addDynamicTemplateData("products",listaProductos);*/

        personalization.addTo(to);
        mail.addPersonalization(personalization);

        Gson gson = new Gson();
        System.out.println(gson.toJson(mail));
        try{


            //
            SendGrid sg = new SendGrid(apy_key);
            Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        }catch(Exception e){

        }
    }
}
