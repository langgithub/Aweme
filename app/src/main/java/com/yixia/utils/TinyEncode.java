package com.yixia.utils;

/**
 * 秒拍so对应的native类
 * @author jiangwei1-g
 *
 */
public class TinyEncode {
	
    private static native String Decode(byte[] bArr);

    public static native String Sign(String str, String str2, String str3, String str4);

    public static String DecodeResult(byte[] bArr) {
        String Decode = Decode(bArr);
        if (Decode.length() == 0) {
            return new String(bArr);
        }
        return Decode;
    }
}
