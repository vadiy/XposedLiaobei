package vip.xposed.liaobei;

import org.junit.Test;

import java.lang.reflect.Type;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;

import javax.crypto.KeyAgreement;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.DHPublicKeySpec;

import vip.xposed.liaobei.Utils.Base64Utils;
import vip.xposed.liaobei.Utils.MStringUtils;

/**
 * Created by AiXin on 2019-10-11.
 */
public class DHCryptoTest {

    //DH_GENERATOR_2 = 2
    private static final BigInteger a = new BigInteger("2");
    //共享质数,种子数
    private static final BigInteger b = new BigInteger("171718397966129586011229151993178480901904202533705695869569760169920539808075437788747086722975900425740754301098468647941395164593810074170462799608062493021989285837416815548721035874378548121236050948528229416139585571568998066586304075565145536350296006867635076744949977849997684222020336013226588207303");
    private static final DHParameterSpec c = new DHParameterSpec(b, a);
    private DHPrivateKey d;
    //加密生成器使用RSA

    public byte[] a(byte[] bArr) {
        try {
            DHPublicKey dHPublicKey = (DHPublicKey) KeyFactory.getInstance("DH").generatePublic(new DHPublicKeySpec(new BigInteger(1, bArr), b, a));
            KeyAgreement instance = KeyAgreement.getInstance("DH");
            instance.init(this.d);
            instance.doPhase(dHPublicKey, true);
            return Arrays.copyOf(new BigInteger(1, instance.generateSecret()).toByteArray(), 32);
        } catch (Exception e) {
            return null;
        }
    }

    public byte[] a() {
        try {
            KeyPairGenerator instance = KeyPairGenerator.getInstance("DH");

            instance.initialize(c);
            KeyPair generateKeyPair = instance.generateKeyPair();
            this.d = (DHPrivateKey) generateKeyPair.getPrivate();
            byte[] privateKey = ((DHPrivateKey) generateKeyPair.getPrivate()).getX().toByteArray();
            byte[] publicKey = ((DHPublicKey) generateKeyPair.getPublic()).getY().toByteArray();
            System.out.println("privateKey len=" + privateKey.length);
            System.out.println(MStringUtils.bytesToHex(privateKey, false));
            System.out.println("publicKey  len=" + publicKey.length);
            System.out.println(MStringUtils.bytesToHex(publicKey, false));
            //System.out.println(MStringUtils.bytesToHex(((Key) generateKeyPair.getPublic()).getEncoded(),false));
            return ((DHPublicKey) generateKeyPair.getPublic()).getY().toByteArray();
        } catch (Exception e) {
            return null;
        }
    }

    public void setD(DHPrivateKey d) {
        this.d = d;
    }

    @Test
    public void DHTest() {
        try {
            DHCryptoTest dh = new DHCryptoTest();

            //d = (DHPrivateKey) KeyPairGenerator.getInstance("DH").generatePrivate(new X509EncodedKeySpec(Base64Utils.decode("Q/dTSM/rJrjIM1HShGFjPAt/PYtKT91VeIlDl55zqOJOcjhrJhSRQWbHbCoScKOhc8P1om4NIeTMNIAZrHkn2f4tGzscHB8L0cGH0omjdolRMqM6tJ1zZDzhtjg4tUxM6xRBYQQ1pvNt1s3obtEaykVG3QIdOuAKOZV4ZaT1BSw=")));
            //System.out.println(Base64Utils.encode((d.getX().toByteArray())));
            //System.out.println(MStringUtils.bytesToHex(dh.a(),false));
            BigInteger bbb = new BigInteger("171718397966129586011229151993178480901904202533705695869569760169920539808075437788747086722975900425740754301098468647941395164593810074170462799608062493021989285837416815548721035874378548121236050948528229416139585571568998066586304075565145536350296006867635076744949977849997684222020336013226588207303");
            System.out.println(Base64Utils.encode(bbb.toByteArray()));
            dh.a();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void Test3() {
        char[] b = new char[]{'M', 'l', '1', 'A', '&', 'Y', 'x', '<'};
        char[] c = new char[]{'p', 'W', '\"', 'h', ':', 'J', 'N', ';'};
        char[] d = new char[]{'D', '5', 'Q', '8', '-', '5', 'g', 'Y'};
        char[] e = new char[]{'d', 't', '4', '/', 'P', '=', ':', '4'};
        char[] f = new char[]{'/', 'K', 'p', 'x', 'r', 'K', '@', 'z'};
        char[] g = new char[]{'4', 'c', 'y', '@', '`', 'C', 'f', 'n'};
        char[] h = new char[]{'^', ';', 'O', '+', 'n', '[', 'u', 'I'};
        char[] i = new char[]{')', 'z', '^', '8', '=', 'e', 'A', 't'};
        int[] j = new int[]{35205, 47303, 42289, 30860, 64185, 12681, 35428, 52721};

        StringBuffer stringBuffer = new StringBuffer();
        a(stringBuffer, b);
        a(stringBuffer, d);
        a(stringBuffer, f);
        a(stringBuffer, h);
        a(stringBuffer, c);
        a(stringBuffer, e);
        a(stringBuffer, g);
        a(stringBuffer, i);
        System.out.println(stringBuffer.toString());
        stringBuffer = new StringBuffer();
        for (int toHexString : j) {
            stringBuffer.append(Integer.toHexString(toHexString));
        }
        System.out.println(stringBuffer.toString());
    }

    private static void a(StringBuffer stringBuffer, char[] cArr) {
        for (char append : cArr) {
            stringBuffer.append(append);
        }
    }

}
