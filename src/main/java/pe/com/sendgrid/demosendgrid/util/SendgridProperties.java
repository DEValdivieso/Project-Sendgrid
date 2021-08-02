package pe.com.sendgrid.demosendgrid.util;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties
public class SendgridProperties {

    @Value("${email.from}")
    private String emailFrom;

    @Value("${email.to}")
    private String emailTo;

    @Value("${email.apiKey}")
    private String emailApiKey;

    @Value("${email.templateId}")
    private String emailTemplateId;
}
