package cn.wjdiankong.levideo.kuaishou.utils;

import java.nio.charset.Charset;

/* renamed from: j0.a.b.a.a.a.d */
/* compiled from: kSourceFile */
public class C2285d {

    /* renamed from: a */
    public static final char[] f5221a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /* renamed from: b */
    public static final char[] f5222b = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /* renamed from: a */
    public static String m3889a(byte[] bArr) {
        return new String(m3890a(bArr, true));
    }

    /* renamed from: a */
    public static char[] m3890a(byte[] bArr, boolean z) {
        char[] cArr;
        if (z) {
            cArr = f5221a;
        } else {
            cArr = f5222b;
        }
        int length = bArr.length;
        char[] cArr2 = new char[(length << 1)];
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i + 1;
            cArr2[i] = cArr[(bArr[i2] & 240) >>> 4];
            i = i3 + 1;
            cArr2[i3] = cArr[bArr[i2] & 15];
        }
        return cArr2;
    }
}