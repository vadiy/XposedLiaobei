package vip.xposed.liaobei;

/**
 * Created by AiXin on 2019-10-15.
 */
import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.interfaces.RSAKey;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

import vip.xposed.liaobei.Utils.Base64Utils;

/* renamed from: com.cqchat.android.o.m */
public class RSAUtil {
    /* renamed from: a */
    private final Key f970a;
    /* renamed from: b */
    private Cipher f971b;
    /* renamed from: c */
    private Cipher f972c;

    /* renamed from: a */
    public static PublicKey m2017a(String str) throws Exception {
        return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64Utils.decode(str)));
    }


    public RSAUtil(Key key) throws Exception {
        this.f970a = key;
        mo1988a(key);
    }

    /* JADX INFO: unreachable blocks removed: 1, instructions: 1 */
    /* renamed from: a */
    public byte[] mo1991a(byte[] bArr) throws Exception {
        try {
            int c = m2019c(bArr);
            int a = m2016a(bArr, c);
            if (a <= 1) {
                return mo1989a(this.f972c, bArr);
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int i = 0;
            while (i < a) {
                int i2 = c * i;
                i++;
                int i3 = c * i;
                if (i3 >= bArr.length) {
                    i3 = bArr.length;
                }
                byteArrayOutputStream.write(mo1990a(this.f972c, bArr, i2, i3 - i2));
            }
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            m2018b(this.f970a);
            throw e;
        }
    }

    /* renamed from: b */
    public byte[] mo1992b(String str) throws Exception {
        return mo1991a(Base64Utils.decode(str));
    }

    /* renamed from: a */
    public byte[] mo1989a(Cipher cipher, byte[] bArr) throws Exception {
        return mo1990a(cipher, bArr, 0, bArr.length);
    }

    /* renamed from: a */
    public byte[] mo1990a(Cipher cipher, byte[] bArr, int i, int i2) throws Exception {
        return cipher.doFinal(bArr, i, i2);
    }

    /* JADX INFO: unreachable blocks removed: 1, instructions: 1 */
    /* renamed from: b */
    public byte[] mo1993b(byte[] bArr) throws Exception {
        try {
            int d = m2021d(bArr);
            int a = m2016a(bArr, d);
            if (a <= 1) {
                return mo1989a(this.f971b, bArr);
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int i = 0;
            while (i < a) {
                int i2 = d * i;
                i++;
                int i3 = d * i;
                if (i3 >= bArr.length) {
                    i3 = bArr.length;
                }
                byteArrayOutputStream.write(mo1990a(this.f971b, bArr, i2, i3 - i2));
            }
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            m2020c(this.f970a);
            throw e;
        }
    }

    /* renamed from: a */
    public void mo1988a(Key key) throws Exception {
        m2020c(key);
        m2018b(key);
    }

    /* renamed from: c */
    private int m2019c(byte[] bArr) {
        return ((RSAKey) this.f970a).getModulus().bitLength() / 8;
    }

    /* renamed from: d */
    private int m2021d(byte[] bArr) {
        return (((RSAKey) this.f970a).getModulus().bitLength() / 8) - 17;
    }

    /* renamed from: a */
    private int m2016a(byte[] bArr, int i) {
        double length = (double) bArr.length;
        double d = (double) i;
        Double.isNaN(length);
        Double.isNaN(d);
        return (int) Math.ceil(length / d);
    }

    /* renamed from: b */
    private void m2018b(Key key) throws Exception {
        if (this.f972c == null) {
            this.f972c = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        }
        this.f972c.init(2, key);
    }

    /* renamed from: c */
    private void m2020c(Key key) throws Exception {
        if (this.f971b == null) {
            this.f971b = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        }
        this.f971b.init(1, key);
    }
}