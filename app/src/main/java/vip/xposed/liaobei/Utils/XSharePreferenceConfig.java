package vip.xposed.liaobei.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

import de.robv.android.xposed.XSharedPreferences;

/**
 * Created by AiXin on 2019-9-12.
 */
public class XSharePreferenceConfig {
    private static final String PACKNAME = "vip.xposed.liaobei";
    private static final String configName = "config";

    //可写的XSharedPreferences对象，这个可以使用在插件自己的apk里面使用
    private SharedPreferences writeXSharedPreferences = null;
    //只读的XSharedPreferences,这个可以让插件在宿主Android应用里面访问
    private SharedPreferences readXSharedPreferences = null;

    public XSharePreferenceConfig(Context context) {
        initWriteSharedPreference(context);
    }

    public XSharePreferenceConfig() {
        initReadSharePreference();
    }


    private void initWriteSharedPreference(Context context) {
        writeXSharedPreferences = context.getSharedPreferences(configName, Context.MODE_WORLD_READABLE);
    }

    private void initReadSharePreference() {
        DBUtil.log("聊呗 包名 " + XSharePreferenceConfig.class.getPackage().getName());
        readXSharedPreferences = new XSharedPreferences(PACKNAME, configName);
    }

    private SharedPreferences getBindSharePreference() {
        if (writeXSharedPreferences != null) {
            return writeXSharedPreferences;
        }
        if (readXSharedPreferences != null) {
            return readXSharedPreferences;
        }
        throw new IllegalStateException("not inited");
    }

    public void putString(String key, String value) {
        SharedPreferences bindSharePreference = getBindSharePreference();
        bindSharePreference.edit().putString(key, value).apply();
    }

    public String getString(String key) {
        SharedPreferences bindSharePreference = getBindSharePreference();
        return bindSharePreference.getString(key, "");
    }

    public void putStringSet(String key, Set<String> values) {
        SharedPreferences bindSharePreference = getBindSharePreference();
        bindSharePreference.edit().putStringSet(key, values).apply();
    }

    public Set<String> getStringSet(String key) {
        SharedPreferences bindSharePreference = getBindSharePreference();
        return bindSharePreference.getStringSet(key, new HashSet<String>());
    }

    public void putInt(String key, int value) {
        SharedPreferences bindSharePreference = getBindSharePreference();
        bindSharePreference.edit().putInt(key, value).apply();
    }

    public int getInt(String key) {
        SharedPreferences bindSharePreference = getBindSharePreference();
        return bindSharePreference.getInt(key, 0);
    }

    public void putLong(String key, long value) {
        SharedPreferences bindSharePreference = getBindSharePreference();
        bindSharePreference.edit().putLong(key, value).apply();
    }

    public Long getLong(String key) {
        SharedPreferences bindSharePreference = getBindSharePreference();
        return bindSharePreference.getLong(key, 0);
    }

    public void putFloat(String key, float value) {
        SharedPreferences bindSharePreference = getBindSharePreference();
        bindSharePreference.edit().putFloat(key, value).apply();
    }

    public float getFloat(String key) {
        SharedPreferences bindSharePreference = getBindSharePreference();
        return bindSharePreference.getFloat(key, 0);
    }

    public void putBoolean(String key, boolean value) {
        SharedPreferences bindSharePreference = getBindSharePreference();
        bindSharePreference.edit().putBoolean(key, value).apply();
    }

    public boolean getBoolean(String key) {
        SharedPreferences bindSharePreference = getBindSharePreference();
        return bindSharePreference.getBoolean(key, false);
    }

    public void remove(String key) {
        SharedPreferences bindSharePreference = getBindSharePreference();
        bindSharePreference.edit().remove(key).apply();
    }

    public void clear() {
        SharedPreferences bindSharePreference = getBindSharePreference();
        bindSharePreference.edit().clear().apply();
    }
}
