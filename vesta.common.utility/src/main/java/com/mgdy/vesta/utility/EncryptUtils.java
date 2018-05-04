package com.mgdy.vesta.utility;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by Jason on 2017/7/3
 * Describe:加密工具
 */
public class EncryptUtils {

    /**
     * 密码MD5加密
     */
    public static String encryptPassword(String password){
        return DigestUtils.md5Hex(password);
    }

}
