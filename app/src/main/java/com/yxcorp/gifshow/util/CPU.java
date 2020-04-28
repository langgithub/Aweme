package com.yxcorp.gifshow.util;

import android.content.Context;

/**
 * 快手so对应的native类
 * @author jiangwei1-g
 *
 */
public abstract class CPU {
	
    public static native String getClock(Context context, byte[] bArr, int i);
    
    public static native String getMagic(Context context, int i);
    
}
