package org.jeecgframework.core.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * Created by Clintonfang on 2017-05-13.
 */
public class SafeUrlBase64 {

    public static String encode(byte[] data) {
        String encodeBase64 = new BASE64Encoder().encode(data);
        String safeBase64Str = encodeBase64.replace('+', '-');
        safeBase64Str = safeBase64Str.replace('/', '_');
        return safeBase64Str;
    }

    public static byte[] decode(final String safeBase64Str) throws IOException {
        String base64Str = safeBase64Str.replace('-', '+');
        base64Str = base64Str.replace('_', '/');
        int mod4 = base64Str.length() % 4;
        if (mod4 > 0) {
            base64Str = base64Str + "====".substring(mod4);
        }
        return new BASE64Decoder().decodeBuffer(base64Str);
    }
}
