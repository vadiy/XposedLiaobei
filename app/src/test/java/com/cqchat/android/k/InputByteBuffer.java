package com.cqchat.android.k;

/**
 * Created by AiXin on 2019-10-14.
 */

import java.util.Arrays;

/* renamed from: com.cqchat.android.k.c */
public class InputByteBuffer {
    /* renamed from: a */
    private static final InputByteBuffer f824a = new InputByteBuffer(new byte[0]);
    /* renamed from: b */
    private final byte[] f825b;
    /* renamed from: c */
    private int f826c = 0;
    /* renamed from: d */
    private int f827d = 0;

    public InputByteBuffer(byte[] bArr) {
        this.f825b = bArr;
    }

    /* renamed from: a */
    public byte[] mo1829a() {
        return this.f825b;
    }

    /* renamed from: a */
    public byte mo1827a(int i) {
        m1750a(i, 1);
        return this.f825b[i];
    }

    /* renamed from: b */
    public int mo1830b() {
        return this.f826c;
    }

    /* renamed from: b */
    public int mo1831b(int i) {
        m1750a(i, 4);
        byte[] bArr = this.f825b;
        return (bArr[i + 3] & 255) | ((((bArr[i] & 255) << 24) | ((bArr[i + 1] & 255) << 16)) | ((bArr[i + 2] & 255) << 8));
    }

    /* renamed from: c */
    public short mo1832c(int i) {
        return (short) (mo1827a(i) & 255);
    }

    /* renamed from: d */
    public int mo1834d(int i) {
        byte[] bArr = this.f825b;
        return (bArr[i + 2] & 255) | (((bArr[i] & 255) << 16) | ((bArr[i + 1] & 255) << 8));
    }

    /* renamed from: c */
    public boolean mo1833c() {
        return this.f826c < this.f825b.length;
    }

    /* renamed from: d */
    public void mo1835d() {
        this.f827d = this.f826c;
    }

    /* renamed from: e */
    public byte mo1836e() {
        m1751h(1);
        byte[] bArr = this.f825b;
        int i = this.f826c;
        this.f826c = i + 1;
        return bArr[i];
    }

    /* renamed from: a */
    public InputByteBuffer mo1828a(byte[] bArr) {
        m1751h(bArr.length);
        System.arraycopy(this.f825b, this.f826c, bArr, 0, bArr.length);
        mo1839f(this.f826c + bArr.length);
        return this;
    }

    /* renamed from: f */
    public int mo1838f() {
        m1751h(4);
        int b = mo1831b(this.f826c);
        this.f826c += 4;
        return b;
    }

    /* renamed from: g */
    public short mo1841g() {
        m1751h(2);
        byte[] bArr = this.f825b;
        int i = this.f826c;
        short s = (short) ((bArr[i + 1] & 255) | (bArr[i] << 8));
        this.f826c = i + 2;
        return s;
    }

    /* renamed from: e */
    public InputByteBuffer mo1837e(int i) {
        if (i == 0) {
            return f824a;
        }
        int i2 = this.f826c;
        int i3 = i2 + i;
        byte[] bArr = this.f825b;
        if (i3 <= bArr.length) {
            InputByteBuffer inputByteBuffer = new InputByteBuffer(Arrays.copyOfRange(bArr, i2, i2 + i));
            this.f826c += i;
            return inputByteBuffer;
        }
        throw new IndexOutOfBoundsException();
    }

    /* renamed from: h */
    public short mo1842h() {
        return (short) (mo1836e() & 255);
    }

    /* renamed from: i */
    public int mo1843i() {
        m1751h(3);
        byte[] bArr = this.f825b;
        int i = this.f826c;
        int i2 = (bArr[i + 2] & 255) | (((bArr[i] & 255) << 16) | ((bArr[i + 1] & 255) << 8));
        this.f826c = i + 3;
        return i2;
    }

    /* renamed from: j */
    public int mo1844j() {
        return this.f825b.length - this.f826c;
    }

    /* renamed from: k */
    public void mo1845k() {
        this.f826c = this.f827d;
    }

    /* renamed from: f */
    public void mo1839f(int i) {
        this.f826c = i;
    }

    /* renamed from: g */
    public InputByteBuffer mo1840g(int i) {
        m1751h(i);
        mo1839f(this.f826c + i);
        return this;
    }

    /* renamed from: a */
    private void m1750a(int i, int i2) {
        if (i + i2 > this.f825b.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    /* renamed from: h */
    private void m1751h(int i) {
        m1750a(this.f826c, i);
    }
}