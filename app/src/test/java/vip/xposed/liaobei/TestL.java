package vip.xposed.liaobei;

import org.junit.Test;

/**
 * Created by AiXin on 2019-10-21.
 */
public class TestL {
    public String a(String str, String str2) {
        String str3 = "";
        int length = str2.length();
        int i = 0;
        for (String parseInt : str.split("l")) {
            i = (i % length) + 1;
            char parseInt2 = (char) (Integer.parseInt(parseInt) ^ str2.charAt(i - 1));
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str3);
            stringBuilder.append(parseInt2);
            str3 = stringBuilder.toString();
        }
        return str3;
    }

    public String b(String str, String str2) {
        String str3 = "";
        int length = str2.length();
        int length2 = str.length();
        int i = 0;
        for (int i2 = 0; i2 < length2; i2++) {
            if (i2 > 0) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(str3);
                stringBuilder.append("l");
                str3 = stringBuilder.toString();
            }
            i = (i % length) + 1;
            int charAt = str.charAt(i2) ^ str2.charAt(i - 1);
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(str3);
            stringBuilder2.append(charAt);
            str3 = stringBuilder2.toString();
        }
        return str3;
    }

    @Test
    public void test1() {
        String a = "Ml1A&Yx<D5Q8-5gY/KpxrK@z^;O+n[uIpW\"h:JN;dt4/P=:44cy@`Cfn)z^8=eAt";
        System.out.println(a("123l88l9l118l19l97",a));
        System.out.println(a("123l88l9l118l19l96",a));

        System.out.println(b("1",a));
    }
}
