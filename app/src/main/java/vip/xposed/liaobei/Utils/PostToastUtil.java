package vip.xposed.liaobei.Utils;

import android.app.AndroidAppHelper;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by AiXin on 2019-9-14.
 */
public class PostToastUtil {

    //在 xposed 中使用会出异常
    public static void showLong(String str) {
        Context context = AndroidAppHelper.currentApplication().getApplicationContext();
        if (context != null) {
            Toast.makeText(context, str, Toast.LENGTH_LONG).show();
        }

    }

    public static void showShort(String str) {
        Context context = AndroidAppHelper.currentApplication().getApplicationContext();
        if (context != null) {
            Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
        }
    }

}
