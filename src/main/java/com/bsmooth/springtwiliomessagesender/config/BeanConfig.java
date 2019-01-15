package com.bsmooth.springtwiliomessagesender.config;

import com.bsmooth.springtwiliomessagesender.constants.TwilioConsts;
import com.bsmooth.springtwiliomessagesender.domains.twilio.TwilioClient;
import com.bsmooth.springtwiliomessagesender.properties.TwilioProps;
import com.twilio.http.TwilioRestClient;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    @Autowired
    public TwilioClient createTwilioRestClient(TwilioProps twilioProps){
        TwilioRestClient twilioRestClient =  new TwilioRestClient.Builder(twilioProps.getAccountSid(),
                twilioProps.getAuthToken()).build();

        PhoneNumber fromPhoneNumber = new PhoneNumber(twilioProps.getFromNumber());
        PhoneNumber whatsappPhoneNumber = new PhoneNumber(TwilioConsts.WHATSAPP_PREFIX + twilioProps.getWhatsappNumber());

        TwilioClient twilioClient = new TwilioClient(twilioRestClient,
                fromPhoneNumber,
                whatsappPhoneNumber,
                twilioProps.getTwimlBinUrl());
        return twilioClient;
    }

}
