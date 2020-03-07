package com.cqchat.android.k;

/**
 * Created by AiXin on 2019-10-14.
 */

/* renamed from: com.cqchat.android.k.d */
public class OutputByteBuffer {
    /* renamed from: a */
    private byte[] f828a;
    /* renamed from: b */
    private int f829b;

    public OutputByteBuffer() {
        this(256);
    }

    public OutputByteBuffer(int i) {
        this.f828a = new byte[i];
    }

    /* renamed from: a */
    public byte[] mo1851a() {
        return this.f828a;
    }

    /* renamed from: a */
    public void mo1847a(int i) {
        byte[] bArr = this.f828a;
        if (bArr.length - this.f829b <= i) {
            m1773e((bArr.length + i) | 255);
        }
    }

    /* renamed from: b */
    public byte[] mo1853b() {
        int i = this.f829b;
        byte[] bArr = new byte[i];
        System.arraycopy(this.f828a, 0, bArr, 0, i);
        return bArr;
    }

    /* renamed from: c */
    public int mo1854c() {
        return this.f829b;
    }

    /* renamed from: a */
    public void mo1848a(int i, int i2) {
        byte[] bArr = this.f828a;
        bArr[i] = (byte) (i2 >>> 16);
        bArr[i + 1] = (byte) (i2 >>> 8);
        bArr[i + 2] = (byte) i2;
    }

    /* renamed from: a */
    public void mo1846a(byte b) {
        m1772d(1);
        byte[] bArr = this.f828a;
        int i = this.f829b;
        this.f829b = i + 1;
        bArr[i] = b;
    }

    /* renamed from: a */
    public void mo1849a(byte[] bArr) {
        mo1850a(bArr, 0, bArr.length);
    }

    /* renamed from: a */
    public void mo1850a(byte[] bArr, int i, int i2) {
        m1772d(i2);
        System.arraycopy(bArr, i, this.f828a, this.f829b, i2);
        this.f829b += i2;
    }

    /* renamed from: b */
    public void mo1852b(int i) {
        m1772d(4);
        byte[] bArr = this.f828a;
        int i2 = this.f829b;
        this.f829b = i2 + 1;
        bArr[i2] = (byte) (i >>> 24);
        i2 = this.f829b;
        this.f829b = i2 + 1;
        bArr[i2] = (byte) (i >>> 16);
        i2 = this.f829b;
        this.f829b = i2 + 1;
        bArr[i2] = (byte) (i >>> 8);
        i2 = this.f829b;
        this.f829b = i2 + 1;
        bArr[i2] = (byte) i;
    }

    /* renamed from: c */
    public void mo1855c(int i) {
        m1772d(3);
        byte[] bArr = this.f828a;
        int i2 = this.f829b;
        this.f829b = i2 + 1;
        bArr[i2] = (byte) (i >>> 16);
        i2 = this.f829b;
        this.f829b = i2 + 1;
        bArr[i2] = (byte) (i >>> 8);
        i2 = this.f829b;
        this.f829b = i2 + 1;
        bArr[i2] = (byte) i;
    }

    /* renamed from: d */
    private void m1772d(int i) {
        while (this.f829b + i > this.f828a.length) {
            m1771d();
        }
    }

    /* renamed from: d */
    private void m1771d() {
        m1773e(this.f828a.length << 1);
    }

    /* renamed from: e */
    private void m1773e(int i) {
        byte[] bArr = new byte[i];
        System.arraycopy(this.f828a, 0, bArr, 0, this.f829b);
        this.f828a = bArr;
    }
}