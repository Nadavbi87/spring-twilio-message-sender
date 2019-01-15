package com.bsmooth.springtwiliomessagesender.utils;

public class LoggerUtil {
    private static final String MESSAGE_SIGN_WRAPPER    = "######## ";
    private static final String ENTERING_PREFIX         = "Entering ";
    private static final String EXITING_PREFIX          = "Exiting ";
    private static final String METHOD_SUFFIX           = " method ";

    public static String getEnteringMethodMessage(String method){
        return wrappingMessage(ENTERING_PREFIX + method + METHOD_SUFFIX);
    }

    public static String getExitingMethodMessage(String method){
        return wrappingMessage(EXITING_PREFIX + method + METHOD_SUFFIX);
    }

    private static String wrappingMessage(String message){
        return MESSAGE_SIGN_WRAPPER + message + MESSAGE_SIGN_WRAPPER;
    }

}
