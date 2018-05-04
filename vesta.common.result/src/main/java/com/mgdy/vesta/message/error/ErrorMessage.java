package com.mgdy.vesta.message.error;

/**
 * Created by Jason on 2017/7/7.
 */
public class ErrorMessage {
    /**
     * 本方法待优化
     * @param errorKey
     * @return
     */
    public static String getMessage(String errorKey) {

        return (String)CustomizedPropertyPlaceholderConfigurer.getContextProperty(errorKey);
//        return "发生了一个错误";
    }

    public static int getErrorCode(String errorCode){
        return ErrorResource.getErrorCode(errorCode);
    }
}
