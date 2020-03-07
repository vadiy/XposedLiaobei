package vip.xposed.liaobei;

import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.security.PublicKey;

import io.netty.handler.codec.rtsp.RtspHeaders;
import vip.xposed.liaobei.Utils.Base64Utils;
import vip.xposed.liaobei.Utils.DBUtil;
import vip.xposed.liaobei.Utils.MStringUtils;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <Bytes href="http://d.android.com/tools/testing">Testing documentation</Bytes>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    static int maskChecksum(int checksum) {
        System.out.println(checksum);
        System.out.println((checksum >> 15 | checksum << 17));
        return (checksum >> 15 | checksum << 17) + 0xa282ead8;
    }

    @Test
    public void crc32cTest() {
        CRC32CTest crc32c = new CRC32CTest();
        byte[] data = MStringUtils.hexToBytes("7B2248534B4E223A7B2270626B223A224C624C425749795733754768736859546F4C796F2B6C7A62534A7431665541557976784752593054726F6C337159762B575473574566787A53454250347373685566544B4B467878415C2F6C444875526D7A374177424938617467432B744A6C6D2B48304661506C423154315A385668655C2F6979506F464E625C2F493743505A6C5173414B6D573534494755647447757371474C4E7451437545544F71524F644553463462386136415C2F4E6566306137576347736E436B38447074446D4151454A436A50692B736F3530703941535A4D344A7632445A7570555969704132636656315A666E714F75584B436E366C442B526D556E4A587559764B534F616F6C7A72674359746273324977333464537864687935756132426D633156597A6B4D37716246712B4F4E426E7A416E646139675761595038304C512B366F737056765C2F6D785C2F4E6A6F705963655047686D6D5537484169503279413D3D222C226667707274223A2261396132353363646230303937626634373135326337333833666639616638653533363666353935227D7D");
        crc32c.update(data, 0, data.length);
        long cr = crc32c.getValue();
        System.out.println("" + cr + "=>" + maskChecksum((int) cr));
    }

    public static int swapMedium(int value) {
        int swapped = value << 16 & 0xff0000 | value & 0xff00 | value >>> 16 & 0xff;
        if ((swapped & 0x800000) != 0) {
            swapped |= 0xff000000;
        }
        return swapped;
    }

    @Test
    public void swapMediumTest() {
        System.out.println(swapMedium(420));
        System.out.println(swapMedium(419));
    }

    @Test
    public void test1() {
        int length = 420;
        for (int i = 0; ; i++) {
            int b = length >>> i * 7;
            if ((b & 0xFFFFFF80) != 0) {
                System.out.println(b & 0x7f | 0x80);
            } else {
                System.out.println(b);
                break;
            }
        }
    }

    private static int bitsToEncode(int value) {
        int highestOneBit = Integer.highestOneBit(value);
        int bitLength = 0;
        while ((highestOneBit >>= 1) != 0) {
            bitLength++;
        }

        return bitLength;
    }

    @Test
    public void test2() {
        short d = 12345;
        System.out.println(bitsToEncode(d));
        System.out.println(Integer.numberOfLeadingZeros(1 << 4));


    }

    @Test
    public void test3() {
        try {
           /* PublicKey publicKey = RSAUtil.m2017a("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoJs43q6JkgIHN/+n7Mu2dgkp0FfZ83O9WYTffXqt7Mr/wO+GCXsJfXeQgPd6DWMxnLzn8MGAIf2sNoQlMKKLIPmXy63YtAF/DR8ln248TaP4XOGr8RWyTOYI/+VC6fzl8IWbOwZAmB4z3PfrYLZVeUvIj35vvGKGR+3f6BDaDG82kgYQa08BhY28epkLruZ7CxnBSa2JKYBam4axf+VviQvdTR6w10p1GX53dy4dKvnC3HkPrFQ0i6QQ/Uk7EckOOGfzHKMLHoORDEkrod4bXDtrImcHoaofqZtu9/zK/sVkYF4hubXhCC7oHSlKnIUOgfmDVfW7KUKupRhELeARJQIDAQAB");
            System.out.println(Base64Utils.encode(publicKey.getEncoded()));
            RSAUtil rsa = new RSAUtil(publicKey);
            System.out.println(Base64Utils.encode(rsa.mo1991a(Base64Utils.decode("bd5dbOslcVEj94+WevI0fRzo4guhTkNGR3p+eDnAfsCRLagqH823uf3ZL5++1Jkv/hd6CmqvpEOPGH9PpNVAD6SSnpE82I3hOrW6yYQ4tAJOvLFFPrQ+wWbKWf9UZzSPAmr2qL4QnfJdOzgddXkACDkcGFsoxuYVv3XvkRwbrAv98Lfu5wDM1ndKXRgfzeSaYgT4IL9wRhmNYO12dYkKcnvqcoI5rSIVp/cPNS944o09h/MDDw6JHbdpNAMJyvtKJctAcj/9jUx0LcczhRKDRrRmTrvhYekrjXaCoTRgLDtqTFua9t9SsTFWi486cDlZAvbA8boxFfbNhHNDfgIndg=="))));
            System.out.println(Base64Utils.encode(rsa.mo1993b("1231dfsdf435".getBytes())));*/


            PublicKey publicKey = RSAUtil.m2017a("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkoyNFn/9EZWzO9s23v90OB3JxzHo9LVJYZm7KnPAz3P2/MqUS4gNvBqzxqMBUSapUKB46QvL6f9c2SUrgeD2Ke5tR0n6PWVk0EkFkIMb65XPcq6/Id9ozdYVNAukex8C+Qus9A+nk+w+RyuphZHal6rgagxLuURX8L6uOW5sBefnkK43O/N9wExPeGrwIFEBWSJsj0tZl4D/UZtBnDbZIBTeFXNFdnDcVSwnwVPXLLzSM+3PfAZJZOyUF1NW8E9BML/HBtnSEAmku6sdZR2/yNcREkLQ+m6ZPghHjtBibMGCu4A1etDOpzlclmH5DDF9l0l/BuDOg6VR2Ggt5GITrwIDAQAB");
            RSAUtil rsa = new RSAUtil(publicKey);

            System.out.println(Base64Utils.encode(rsa.mo1991a(Base64Utils.decode("LQcCaWrZJNj29eJoWNgaDXkH988SpAfZ3k4LE6NCO+miAuWDZZSfdDuw6Drn2TeoBNrLMBSZehKEpwQV0as+7480G0z+05voqQE6PE+ASu8PddcbqXpMiD1Eu+MtimgA/N73ecXy7862a4Zl5SIWlFRKY5m0QRkXZcrFlC+0irBuG/cWxZIJeYQDMFNn8/tiqLCIoI6NuVMQtGHujzn4cruEkCRlboB30N3CxJsLTuHS7/wlWHXk+wTD3nfwRaGjJ09WSMKyk4E/hxnPVVSdR9BgJUQiD26R/an0HC+BI/APuhXAa9sHC8dbF/iVud5cDgT1dQ11wnggWr6dPyAhuQ=="))));


            System.out.println(Base64Utils.encode(rsa.mo1993b(Base64Utils.decode("NLxJqvv+g1Ik2B9VA6dE/VnomSlhcji67RKdBMEgjQ5wqHHU6+smWpDVc9OQnIgOiUYu3OUHljWevPRRBWiu66GEYhvIuWt/ij3mKLSFt8p+lNcCKFj1w6TXeAGclM61pZQ/sfHGqBNlQsKy5BJnGsqOYi6VIIKXknNDdordwRM="))));
            System.out.println(Base64Utils.encode(rsa.mo1991a(Base64Utils.decode("dqqQtyMOWEFMIvQG3RVmvJjQsbOWRC9m69tK5ygqi64odRD6c0LtC/lO6SMNJKoZxwxPm4qcCk0jJtb6I71wHcX0jrWqQ/nwm8WOs0KuqV067hfAgdYIF1qtSs9R5PRd13vs2JkgKXVXoVceJcDctE90Bu9l+GOHF9Jc7df1qf3lC5a5g3PnER+Ym2a1X95ioSvcZqEkXSokQo/IgiHuJQ9PApXM6xS1fwK5ZFa4XpdbJUCrUeDodMXqvivaKQVj7YTNYUMjBTtOoZT4JOpRpVJA4XfmfaH/xchh+FFC41j6w9X+ewz2sRe2dT+i5RlFOWDoVgDVa5oUm7fd1VofAw=="))));
            //"AJmJLxpSON2ye9xkgzt8ENlMj81+2L90VNwdx36DudOpO5e7LU3hZi0V2rcS2Pdi9iA61WnO8jSt87Q8EY12KpLbi0Gap7v8Ep2mnpKwxXhmHY2JBp7ZUfrJhx3FTWuYEs9CCkIKKB4qZ9HHSpg7rMNpOjyDgkXPKZOQHk8HAegD"
            //"XzMMTTPcwVLGvUo3Ycg8edvQZNaoDAtNLZyb1f8UxnpPP6xL4CEl5YSoDim+lz19X7VobZ3DvH7ZshUJ2cTMyI8K4nfK3/ksXrwyugBXjkU62l5qtcsjCjPTQy5wSB3CPnXszasnLrq8SX2o4PngO0rw1ILDJAgcQTdOIApYH4mj3v+LYT5v4tj8WbLQgUyARO9l07tQ4Fj28OmYcu8UOOhxozQ7nrj08WssjwdTlelKpgFmnS9+DzFmPcxDMmfWyNlxF93PFMWr0KhCcVMovTxFes3CTP4NnjIPKEjwwu003pMIn5mXR6k8RsToSvRTsAiWo/CyEIlkZrlTd8CV/w=="
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //AKUbqStXLKZvl/2bHUTXPJgNwTtpyHCQPxECWzG1zHPrTnMai9R9CoCPUQ0eOCJL8KQ5RXG56UlWgmWm6Ffd9DrNE1Mi1gSt+gMV7M8La2qmtSaIuPbZmriDrJv+XlhsBn7aQod6ITKn7YC0oQhcJ50NAjSENVvfseRFdjLv5iaS
    public static byte[] getBytes(char[] data) {
        if (data == null || data.length == 0)
            return null;
        CharBuffer charBuffer = CharBuffer.wrap(data);
        ByteBuffer byteBuffer = Charset.forName("utf-8").encode(charBuffer);
        byte[] result = new byte[byteBuffer.limit()];
        byteBuffer.get(result);
        return result;
    }
    @Test
    public void test4(){
        System.out.println(MStringUtils.bytesToHex(getBytes("03c6507e9f1e2daaee1ccbfb00009883".toCharArray()),false));
    }

    @Test
    public void test85(){
        DBUtil.printParent("ADD");
    }
}