package com.mgdy.vesta.utility;

/**
 * Created by Jason on 2017/7/3
 * Describe:byte数组工具类
 */
public class ByteUtils {

    /**
     * Describe:合并两个byte数组
     * CreateBy:Tom
     * CreateOn:2016-02-17 09:06:23
     */
    public static byte[] merge(byte[] byte_1, byte[] byte_2){
        byte[] byte_3 = new byte[byte_1.length + byte_2.length];
        System.arraycopy(byte_1, 0, byte_3, 0, byte_1.length);
        System.arraycopy(byte_2, 0, byte_3, byte_1.length, byte_2.length);
        return byte_3;
    }

}
