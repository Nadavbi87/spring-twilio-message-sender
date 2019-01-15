package com.bsmooth.springtwiliomessagesender.controllers;

import com.bsmooth.springtwiliomessagesender.utils.LoggerUtil;
import com.bsmooth.springtwiliomessagesender.constants.MessageConsts;
import com.bsmooth.springtwiliomessagesender.constants.PathConsts;
import com.bsmooth.springtwiliomessagesender.services.MessageSenderService;
import com.bsmooth.springtwiliomessagesender.services.responses.MessageSenderResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(PathConsts.BASE_API_PATH)
public class MessageSenderController {

    private static Logger logger = LoggerFactory.getLogger(MessageSenderController.class);

    @Autowired
    private MessageSenderService messageSenderService;

    @PostMapping(PathConsts.SMS_API_ENDPOINT)
    public ResponseEntity<MessageSenderResponse> sendSMS(@RequestParam final String to, @RequestParam final String body){
        final String METHOD = "send sms ";
        logger.info(LoggerUtil.getEnteringMethodMessage(METHOD));
        MessageSenderResponse messageSenderResponse = messageSenderService.sendSMS(to, body);
        logger.info(METHOD + MessageConsts.RESPONSE_PREFIX_MESSAGE + messageSenderResponse.toString());
        logger.info(LoggerUtil.getExitingMethodMessage(METHOD));
        return new ResponseEntity<>(messageSenderResponse,
                HttpStatus.valueOf(messageSenderResponse.getStatus()));
    }

    @PostMapping(PathConsts.WHATSAPP_API_ENDPOINT)
    public ResponseEntity<MessageSenderResponse> sendWhatsApp(@RequestParam final String to, @RequestParam final String body){
        final String METHOD = "send whatsapp ";
        logger.info(LoggerUtil.getEnteringMethodMessage(METHOD));
        MessageSenderResponse messageSenderResponse = messageSenderService.sendWhatsApp(to, body);
        logger.info(METHOD + MessageConsts.RESPONSE_PREFIX_MESSAGE + messageSenderResponse.toString());
        logger.info(LoggerUtil.getExitingMethodMessage(METHOD));
        return new ResponseEntity<>(messageSenderResponse,
                HttpStatus.valueOf(messageSenderResponse.getStatus()));
    }

    @PostMapping(PathConsts.VOICE_API_ENDPOINT)
    public ResponseEntity<MessageSenderResponse> makeCall(@RequestParam final String to, @RequestParam final String body){
        final String METHOD = "make call ";
        logger.info(LoggerUtil.getEnteringMethodMessage(METHOD));
        MessageSenderResponse messageSenderResponse = messageSenderService.makeVoiceCall(to, body);
        logger.info(METHOD + MessageConsts.RESPONSE_PREFIX_MESSAGE + messageSenderResponse.toString());
        logger.info(LoggerUtil.getExitingMethodMessage(METHOD));
        return new ResponseEntity<>(messageSenderResponse,
                HttpStatus.valueOf(messageSenderResponse.getStatus()));
    }

}
