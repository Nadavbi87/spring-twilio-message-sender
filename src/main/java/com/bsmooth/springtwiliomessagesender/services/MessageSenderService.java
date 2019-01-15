package com.bsmooth.springtwiliomessagesender.services;

import com.bsmooth.springtwiliomessagesender.services.responses.MessageSenderResponse;

public interface MessageSenderService {
    MessageSenderResponse sendSMS(final String to, final String body);
    MessageSenderResponse sendWhatsApp(final String to, final String body);
    MessageSenderResponse makeVoiceCall(final String to, final String body);
}
