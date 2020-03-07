package com.cqchat.android.k;

/**
 * Created by AiXin on 2019-10-14.
 */
/* renamed from: com.cqchat.android.k.a */
public class Bytes {
    /* renamed from: b */
    public static int m1748b(int i) {
        i = ((i >>> 16) & 255) | (((i << 16) & 16711680) | (65280 & i));
        return (8388608 & i) != 0 ? i | -16777216 : i;
    }

    /* renamed from: a */
    public static int m1746a(int i) {
        return Integer.reverseBytes(i);
    }

    /* renamed from: a */
    public static short m1747a(short s) {
        return Short.reverseBytes(s);
    }
}