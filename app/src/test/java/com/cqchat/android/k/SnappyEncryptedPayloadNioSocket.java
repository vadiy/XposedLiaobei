package com.cqchat.android.k;


/* renamed from: com.cqchat.android.d.a.f */
public class SnappyEncryptedPayloadNioSocket extends EncryptedPayloadNioSocket {

    /* renamed from: a */
    private SnappyFramedEncoder f655a = new SnappyFramedEncoder();
    /* renamed from: b */
    private SnappyFramedDecoder f656b = new SnappyFramedDecoder();



    //解密
    /* access modifiers changed from: protected */
    /* renamed from: b */
    public byte[] mo1513b(byte[] bArr) throws Exception {
        return this.f656b.mo1860a(super.mo1513b(bArr));
    }

    //加密
    /* access modifiers changed from: protected */
    /* renamed from: c */
    public byte[] mo1516c(byte[] bArr) throws Exception {
        byte[] a = this.f655a.mo1861a(bArr);
        return a;
        /*if (a != null && a.length != 0) {
            return super.mo1516c(a);
        }
        *//*StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("invalid send data after zipped, raw bytes: ");
        stringBuilder.append(Arrays.toString(bArr));
        SockLog.m2972c(stringBuilder.toString());*//*
        return super.mo1516c(a);*/
    }
}
