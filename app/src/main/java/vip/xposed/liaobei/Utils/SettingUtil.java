package vip.xposed.liaobei.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

/**
 * Created by AiXin on 2019-9-14.
 */
public class SettingUtil {
    private XSharePreferenceConfig xc = null;

    public SettingUtil() {
        this.xc = new XSharePreferenceConfig();
    }

    public SettingUtil(Context context) {
        this.xc = new XSharePreferenceConfig(context);
    }

    public void setToken(String token) {
        xc.putString("token", token);
    }

    public String getToken() {
        return xc.getString("token");
    }


}
