package com.bsmooth.springtwiliomessagesender.services.impl;

import com.bsmooth.springtwiliomessagesender.constants.MessageConsts;
import com.bsmooth.springtwiliomessagesender.constants.TwilioConsts;
import com.bsmooth.springtwiliomessagesender.domains.twilio.TwilioClient;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.net.URI;
import java.net.URLEncoder;
import static org.apache.commons.codec.CharEncoding.UTF_8;

@Component
public class TwilioMessageSenderClient {

    private static Logger logger = LoggerFactory.getLogger(TwilioMessageSenderClient.class);

    private TwilioClient twilioClient;

    @Autowired
    public TwilioMessageSenderClient(TwilioClient twilioClient) {
        this.twilioClient = twilioClient;
    }


    public Message sendSMS(final String to, final String body) {
        final String METHOD = "sendSMS ";
        Message smsMessage = null;

        PhoneNumber toPhoneNumber = new PhoneNumber(to);
        smsMessage = sendMessage(toPhoneNumber, twilioClient.getFromPhoneNumber(), body);

        logger.info(METHOD + smsMessage.toString());
        return smsMessage;
    }


    public Message sendWhatsApp(final String to, final String body) {
        final String METHOD = "sendWhatsApp ";
        Message whatsAppMessage = null;

        PhoneNumber toPhoneNumber = new PhoneNumber(TwilioConsts.WHATSAPP_PREFIX + to);
        whatsAppMessage = sendMessage(toPhoneNumber, twilioClient.getWhatsappPhoneNumber(), buildWhatsAppMessage(body));

        logger.info(METHOD + whatsAppMessage.toString());
        return whatsAppMessage;
    }

    public Call makeVoiceCall(final String to, final String body) {
        final String METHOD = "makeVoiceCall ";
        Call voiceCall = null;
        PhoneNumber toPhoneNumber = new PhoneNumber(to);

        URI callInstructionsURI = buildCallInstructionURI(body);

        voiceCall = Call.creator(toPhoneNumber,twilioClient.getFromPhoneNumber(), callInstructionsURI)
                .create(twilioClient.getTwilioRestClient());

        logger.info(METHOD + voiceCall.toString());

        return voiceCall;
    }

    private Message sendMessage(final PhoneNumber to, final PhoneNumber from, final String body) {
        return new MessageCreator(to, from, body)
                .create(this.twilioClient.getTwilioRestClient());
    }

    private String buildWhatsAppMessage(String body){
        return MessageConsts.WHATSAPP_MESSAGE_PREFIX + body;
    }

    private URI buildCallInstructionURI(final String body) {
        final String METHOD = "buildCallInstructionURI ";
        URI callInstructionsURI = null;

        try {
            String fullTwiMlUrlPath = twilioClient.getTwiMLBinUrl()
                    + TwilioConsts.TWIML_BODY_QUERY_PARAM_KEY + URLEncoder.encode(body, UTF_8);
            callInstructionsURI = new URI(fullTwiMlUrlPath);
        } catch (Exception e) {
            logger.error(METHOD + MessageConsts.INVALID_URI_EXCEPTION_MESSAGE, e);
        }

        return callInstructionsURI;
    }


}
