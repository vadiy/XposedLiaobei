package vip.xposed.liaobei;

import org.junit.Test;

import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import io.netty.buffer.AbstractByteBuf;
import io.netty.handler.codec.compression.SnappyFramedDecoder;
import vip.xposed.liaobei.Utils.Base64Utils;
import vip.xposed.liaobei.Utils.ConfigUtils;
import vip.xposed.liaobei.Utils.RSAUtils;

/**
 * Created by AiXin on 2019-9-13.
 */
public class CryptoTest {

    private static final String PUBLICK_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCzwkQh+SVKtIjBOocQ15RmLzvnJ4TKQuM0+EWwVjC7UXPRp/B6ZGx5OUyPnhvXTIUsyHfVa3kixORDM1Bq6gtc5Bon1nZyCrEogsfEe3qK6RSM/hOogMdPnYmpBX72jm5VRyH7ApwtQirPWNihfakMpx73MJkC7ReCaaFpESGWhQIDAQAB";
    private static final String PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALPCRCH5JUq0iME6hxDXlGYvO+cnhMpC4zT4RbBWMLtRc9Gn8HpkbHk5TI+eG9dMhSzId9VreSLE5EMzUGrqC1zkGifWdnIKsSiCx8R7eorpFIz+E6iAx0+diakFfvaOblVHIfsCnC1CKs9Y2KF9qQynHvcwmQLtF4JpoWkRIZaFAgMBAAECgYEAh8Y7KiICfdvA05p+fEUo7MnOiDk8Pn2pGdBQ6vEfG81ZGsmrhPanTCTwZCX7R3KfwISmpsfnllB7TRrMKgUfkMFz6YtUsxqxncNf3tobRYOvUY+KWWUVLKkt/WjJHvEKeabLf/JOZylhq/l3M3qR5eG3yzEnfNwfINLHY30KED0CQQD5IY6LhagDQIN7Y1JCUlgltdkxULEh4Wgyrs1E0M/HjLNrZd8CA8CwYci10pVxwYDvZsJVncKcND8pJI/+QiRHAkEAuLcPGHwtakK5OVzFNQOQhlSLRUlXNCA5v2WmrsdRZTKjBpYEQVo/oRgWc22pHV9KyO1cWz/blyoztjFHabzQ0wJBAPgMNUUtmo9CWKctyOVH34QMf2fek77ME1cDPFXcIkTpDmtMTrJO0jfL5G9EcI+Gvr2ebreYEAA+9PQd91CMwlkCQBh4V82clb+SnappyException+z24Jn/xavIAvTp+jsjfBAdxBfXdfdD0NlinAwVNWWST9lVwT5kOiK+5kiScfxC1jIg0WwuM8fUCQDDKa5gtAPkEl/3JR/iBsURteaJsUyUh5s7kXMpkstN2h5nyjRBSRx8p8fsuP9RGWehW7/XoeouaYxC59yt7XqI=";

    @Test
    public void testRSAE() {
        try {
            PublicKey pk = RSAUtils.getPublicKey(PUBLICK_KEY);
            String str = RSAUtils.encrypt("我是谁", pk);
            System.out.println(str);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testRSAD() {
        try {
            String str = "XXoXZQfN0DxfnYD3Bp+wMdIurxKv3MKmneIBrpMEmGTtttAx+dfb/d945zY+QUn0NeSXdM3fkgz0mAYf1XRYuuZ+y0iVq1UkbllW548+iIFZfeCxmoawqV/9vkd0gzRobyKQplL6YQOR4Ez8GJtYvPGQLqMVZILH0Aqcnx1tifZqX8nQcljEvWvYS1Z2DOXQoK9kncQlyDbQ1dd0nTWhOH+5D4KQ/i03F5ANlzecTihHX5FkQZQWjPqKcNxjsEYsPTpxshOTWYUD/ax74mqxleTd7SNS2i/TRqvh3aSxw/oK6KddNu5kwqcuPd494IuEFf+NX0WOvJgv06fedO/nYhkaHTtm1YkVnodoumwu9O1gRB5Xn8lEapxgqtstlTWKc0g+UBxMaMbUnxn0K06vGWW/XLeQepPVLgpZDy7CijjSF8XaK511f9ZVLPd7063k5BVJ5mBir/2b110XmNNs1qyH4Mx5JW8aZz5OBWGnlRETD+ndEiyRaAqcqzEcr1pLCla2llTuUwHDpLmzTZb7+Q0oarImzcMA0XlnnCZRiciLwU7i/dtYffVKpgG48LPbI0iGvM05tEI3r2984bB4ndDEd+9thnX8Wi/SyAWqaPChq4BuAdergm/kn4j8Fa8hLnyqvlzxzUme8HRG4rUVAqpuO6HCDxtk2renBk4ycxESVk/3yjJO6dPHLUeyeSZAI2itnPvh+utwmLTMs13dgANhiRn6Ufj9eWcLNNJfAiU/2DHtItuXAk0lIca1nYBII2K2tmnv+729wt0dIyReL/k9MEoUOSGCqCrOgisWMWwuTICcqbuwP+QYuxEvz6CoycE+ae/ll5G1I9nsfwE7Vg==";
            PrivateKey pvk = RSAUtils.getPrivateKey(PRIVATE_KEY);
            str = RSAUtils.decrypt(str, pvk);
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetConfig() {
        String str = ConfigUtils.getConfig("F:\\__MyTools__\\易语言项目\\__引流\\聊呗APP相关\\___聊呗协议模块\\data.txt");
        System.out.println(str);
    }


    @Test
    public void testAes() {
        Cipher c;
        //AbstractByteBuf
        //SnappyFramedDecoder
        try {
            String data = "LWIqlDycW07huDx/ORad2SwrBv1JgNqETeMyGslK92griuRFwunWjiL9dY5A6fnv";
            byte[] ds = Base64Utils.decode(data);
            SecretKey sk = new SecretKeySpec("751f9a302c286e3bee01d6cc59661276".getBytes(), "AES");
            c = Cipher.getInstance("AES/ECB/PKCS5Padding");
            c.init(2, sk);
            ds=c.doFinal(ds, 0, ds.length);
            System.out.println(ds.length);
            System.out.println(new String(ds));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
