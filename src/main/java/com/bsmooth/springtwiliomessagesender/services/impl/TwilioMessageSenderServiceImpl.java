package com.bsmooth.springtwiliomessagesender.services.impl;

import com.bsmooth.springtwiliomessagesender.constants.MessageConsts;
import com.bsmooth.springtwiliomessagesender.services.MessageSenderService;
import com.bsmooth.springtwiliomessagesender.services.responses.MessageSenderResponse;
import com.twilio.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TwilioMessageSenderServiceImpl implements MessageSenderService {

    private TwilioMessageSenderClient twilioMessageSenderClient;

    @Autowired
    public TwilioMessageSenderServiceImpl(TwilioMessageSenderClient twilioMessageSenderClient) {
        this.twilioMessageSenderClient = twilioMessageSenderClient;
    }

    @Override
    public MessageSenderResponse sendSMS(String to, String body) {
        MessageSenderResponse messageSenderResponse = null;
        try {
            twilioMessageSenderClient.sendSMS(to, body);
            messageSenderResponse = buildSuccessResponse();
        }catch (ApiException ex){
            messageSenderResponse = buildResponseFromException(ex);
        }catch (Exception e){
            messageSenderResponse = buildGeneralErrorResponse();
        }
        return messageSenderResponse;
    }

    @Override
    public MessageSenderResponse sendWhatsApp(String to, String body) {
        MessageSenderResponse messageSenderResponse = null;

        try {
            twilioMessageSenderClient.sendWhatsApp(to, body);
            messageSenderResponse = buildSuccessResponse();
        }catch (ApiException ex){
            messageSenderResponse = buildResponseFromException(ex);
        }catch (Exception e){
            messageSenderResponse = buildGeneralErrorResponse();
        }

        return messageSenderResponse;
    }

    @Override
    public MessageSenderResponse makeVoiceCall(String to, String body) {
        MessageSenderResponse messageSenderResponse = null;

        try {
            twilioMessageSenderClient.makeVoiceCall(to, body);
            messageSenderResponse = buildSuccessResponse();
        }catch (ApiException ex){
            messageSenderResponse = buildResponseFromException(ex);
        }catch (Exception e){
            messageSenderResponse = buildGeneralErrorResponse();
        }

        return messageSenderResponse;
    }

    private MessageSenderResponse buildResponseFromException(ApiException apiException){
        MessageSenderResponse messageSenderResponse = null;

        if(apiException != null && apiException.getStatusCode() != null){
            messageSenderResponse = new MessageSenderResponse(
                    false,
                    apiException.getMessage(),
                    apiException.getStatusCode());
        }else{
            messageSenderResponse = buildGeneralErrorResponse();
        }
        return messageSenderResponse;
    }

    private MessageSenderResponse buildGeneralErrorResponse(){
        return new MessageSenderResponse(false,
                MessageConsts.GENERAL_ERROR_MESSAGE,
                500);
    }

    private MessageSenderResponse buildSuccessResponse(){
        return new MessageSenderResponse(true,
               null,
                201);
    }
}
