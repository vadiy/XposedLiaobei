package vip.xposed.liaobei.Utils;

import android.os.Environment;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import de.robv.android.xposed.XposedBridge;

/**
 * Created by AiXin on 2019-9-13.
 */
public class ConfigUtils {

    private static final String PackName = "vip.xposed.liaobei";
    private static final String mFileName = "config.json";
    private static File mFile = null;

    static {
        mFile = new File(Environment.getDataDirectory(), "data/" + PackName + "/shared_prefs");
        mFile.mkdirs();
    }


    private static void writeConfig(String str) {
        try {
            File file = new File(mFile, mFileName);
            FileOutputStream fout = new FileOutputStream(file);
            fout.write(str.getBytes("utf-8"));
            fout.flush();
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return json
     */
    public static String getConfig() {
        return getConfig(mFileName);
    }

    /**
     * @return json
     */
    public static String getConfig(String fileName) {
        File file = new File(mFile, fileName);
        DBUtil.log(file.getPath());
        if (file.exists() && file.canRead()) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                int num = 0;
                char[] buf = new char[1024];
                String dataStr = "";
                while ((num = br.read(buf)) != -1) {
                    dataStr += new String(buf, 0, num);
                }

                br.close();
                return dataStr;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
                if (!file.canRead()) {

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public static void putKV(String key, String value) {
        String dataStr = getConfig();
        JSONObject jsonObj = JSON.parseObject(dataStr);
        if (jsonObj == null) {
            jsonObj = new JSONObject();
        }
        jsonObj.put(key, value);
        dataStr = JSON.toJSONString(jsonObj);
        writeConfig(dataStr);
    }

    public static String getV(String key) {
        String dataStr = getConfig();
        JSONObject jsonObj = JSON.parseObject(dataStr);
        if (jsonObj != null) {
            return jsonObj.getString(key);
        } else {
            return "";
        }
    }

    public static void remove(String key) {
        String dataStr = getConfig();
        JSONObject jsonObj = JSON.parseObject(dataStr);
        if (jsonObj != null) {
            jsonObj.remove(key);
            dataStr = JSON.toJSONString(jsonObj);
            writeConfig(dataStr);
        }
    }

    public static void clear() {
        writeConfig("");
    }

}
