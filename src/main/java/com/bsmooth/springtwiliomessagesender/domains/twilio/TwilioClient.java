package com.bsmooth.springtwiliomessagesender.domains.twilio;

import com.twilio.http.TwilioRestClient;
import com.twilio.type.PhoneNumber;


public class TwilioClient {
    private TwilioRestClient twilioRestClient;
    private PhoneNumber fromPhoneNumber;
    private PhoneNumber whatsappPhoneNumber;
    private String twiMLBinUrl;

    public TwilioClient(TwilioRestClient twilioRestClient, PhoneNumber fromPhoneNumber, PhoneNumber whatsappPhoneNumber, String twiMLBinUrl) {
        this.twilioRestClient = twilioRestClient;
        this.fromPhoneNumber = fromPhoneNumber;
        this.whatsappPhoneNumber = whatsappPhoneNumber;
        this.twiMLBinUrl = twiMLBinUrl;
    }

    public TwilioRestClient getTwilioRestClient() {
        return twilioRestClient;
    }

    public void setTwilioRestClient(TwilioRestClient twilioRestClient) {
        this.twilioRestClient = twilioRestClient;
    }

    public PhoneNumber getFromPhoneNumber() {
        return fromPhoneNumber;
    }

    public void setFromPhoneNumber(PhoneNumber fromPhoneNumber) {
        this.fromPhoneNumber = fromPhoneNumber;
    }

    public PhoneNumber getWhatsappPhoneNumber() {
        return whatsappPhoneNumber;
    }

    public void setWhatsappPhoneNumber(PhoneNumber whatsappPhoneNumber) {
        this.whatsappPhoneNumber = whatsappPhoneNumber;
    }

    public String getTwiMLBinUrl() {
        return twiMLBinUrl;
    }
}
