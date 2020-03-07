package vip.xposed.liaobei;

/**
 * Created by AiXin on 2019-9-12.
 */
public class UserBean {
    private String un;
    private String unk;
    private String mid;
    private String uid;
    private String uuid;
    private String tk;
    private String rtk;
    private String ck;

    public String getUn() {
        return un;
    }

    public void setUn(String un) {
        this.un = un;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTk() {
        return tk;
    }

    public void setTk(String tk) {
        this.tk = tk;
    }

    public String getRtk() {
        return rtk;
    }

    public void setRtk(String rtk) {
        this.rtk = rtk;
    }

    public String getCk() {
        return ck;
    }

    public void setCk(String ck) {
        this.ck = ck;
    }

    public String getUnk() {
        return unk;
    }

    public void setUnk(String unk) {
        this.unk = unk;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "un='" + un + '\'' +
                ", unk='" + unk + '\'' +
                ", mid='" + mid + '\'' +
                ", uid='" + uid + '\'' +
                ", uuid='" + uuid + '\'' +
                ", tk='" + tk + '\'' +
                ", rtk='" + rtk + '\'' +
                ", ck='" + ck + '\'' +
                '}';
    }
}
