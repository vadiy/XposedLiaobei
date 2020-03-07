package com.cqchat.android.k;

import org.junit.Test;

import vip.xposed.liaobei.Utils.MStringUtils;

/**
 * Created by AiXin on 2019-10-14.
 */
public class DBTest {
    @Test
    public void encoderTest() {
        String str = "";
        byte[] d = null;
        byte[] data = null;
        System.out.println(MStringUtils.bytesToHex(data, false));
        SnappyEncryptedPayloadNioSocket s = new SnappyEncryptedPayloadNioSocket();
        try {
           /* str = "{\"HSKN\":{\"pbk\":\"e8nNLaNslzQCWzBO2T1LmZLoKL01Fw4bOF6MKGZhNazLtaguMlsgqQgQG0jczkrW0Vbi8psZIs7IBeXmAy4+78Nh9otYymrRhQbatlFocawmDk82ceIKnLtk3VsaL6g32hAiQ86gdwGcuW+7e95uve9N6zVUaM51m8oAmSRTzuLqJ3NRWPNUEAJTJFJ52SqOTarCdu0RIcdRTvY6KZ\\/TWCYuQ6gK9JDO3sk61jpHultZp2RuM6KZioh6JDgqrD+u71j9c3P4IUbFarPUSu1ZTs1BSlriCqPThsu6kk\\/Y4di1BhcIaFFf0UHFZHBpCfplQ4cUOC\\/Kc46igy4kJWyaPA==\",\"fgprt\":\"a9a253cdb0097bf47152c7383ff9af8e5366f595\"}}";
            data = str.getBytes();
            d = s.mo1516c(data);
            System.out.println(MStringUtils.bytesToHex(d, false));*/
            /*str = "{\"AUTH\":{\"msuid\":756320041,\"tk\":\"RfI05bv9Cin1ch+0JcIrWK+0ul\\/bcxt8TgbxY+2Mtrq8aM6R6DuDrv97IrqvbcSJBXyGpwuKYqOV98EQ93nx5TZw3JraXwsN41FHBwSlun5SciFzjwFRFKi5vydcZ\\/0kliKT0m8QYG58jkGOrc0KiChChwc0aYHxgi9OQtuhkK4=\",\"dev\":1,\"devuuid\":\"f3335655-31a9-3535-b695-ac741dddcdd5\",\"mct\":1571010588077,\"msusig\":\"99c37312e077985cbf447025d080589d0152796d\",\"ver\":\"2.0.5.1\"}}";
            data = str.getBytes();
            d = s.mo1516c(data);
            System.out.println(MStringUtils.bytesToHex(d, false));*/
            data = MStringUtils.hexToBytes("FF060000734E6150705900FD0100C1B77F00F503F4D6017B2248534B4E223A7B226D7375636964223A2235323638663566662D306330662D343264302D396638632D313262376163623336616436222C2270626B223A22592F6F7533654D684C596D72386461456F6C41485748372B532B346A72417077773946527759447778305767474F4B2B75714B6A70766C6251526F6A707167724D496E34305844702B372B4B61662F3176687646345135574F776D4E63564767364D4A3356675262694D2B4E5A39377932466E416A48394270726C6A497A5A692F595A7A323531695A486F7165706C552F2B4B36656D61707A78774A556A7372345078424C6844714342657A5A55592B52716D30776564573868306E314B345A686E7876504A5853702F6D3859516B647471365335454B70522F2F5158366D576F6F6B386B535335687148774734496C59625A32495466516232492B6D326D5539433238327642554E78674F78343730787742425946716D49754939766D5469584E70314D584A70504E646D533146524464717A3341586370372F54575471424E4F4946524A37732B6E7A65636A7048414F47586D413D3D222C226667707274223A2261396132353363646230303937626634373135326337333833666639616638653533363666353935222C226D6E74223A7B22733021D164726576746D223A2231353731303130353837393733227D7D7D7D");
            System.out.println(MStringUtils.bytesToHex(data, false));
            d = s.mo1513b(data);
            System.out.println("11111");
            System.out.println(MStringUtils.bytesToHex(d, false));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
