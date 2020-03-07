package com.cqchat.android.k;


import java.util.Arrays;

/* renamed from: com.cqchat.android.d.a.c */
public class EncryptedPayloadNioSocket {

    /* renamed from: d */
    private byte[] f654d;


    /* access modifiers changed from: protected */
    /* renamed from: b */
    public byte[] mo1513b(byte[] bArr) throws Exception {
             return  bArr;

    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public byte[] mo1516c(byte[] bArr) throws Exception {
            return m1976a(m1975a(bArr.length), bArr);

    }



    /* renamed from: a */
    public static byte[] m1976a(byte[] bArr, byte[] bArr2) {
        byte[] copyOf = Arrays.copyOf(bArr, bArr.length + bArr2.length);
        System.arraycopy(bArr2, 0, copyOf, bArr.length, bArr2.length);
        return copyOf;
    }

    /* renamed from: a */
    public static byte[] m1975a(int i) {
        return new byte[]{(byte) (i >>> 24), (byte) (i >>> 16), (byte) (i >>> 8), (byte) i};
    }

    /* renamed from: a */
    public static boolean m1974a(byte[] bArr) {
        return bArr == null || bArr.length == 0;
    }

}
