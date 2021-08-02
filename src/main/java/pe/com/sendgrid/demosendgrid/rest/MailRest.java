package pe.com.sendgrid.demosendgrid.rest;

import org.springframework.web.bind.annotation.*;
import pe.com.sendgrid.demosendgrid.facade.MailFacade;


@RestController
public class MailRest {

    private final MailFacade mailFacade;

    public MailRest(MailFacade mailFacade) {
        this.mailFacade = mailFacade;
    }

    @GetMapping("sendEmail")
    public String getPurchaseId() {
        mailFacade.sendEmail();
        System.out.println("enviado");
        return "Email enviado";
    }
}
