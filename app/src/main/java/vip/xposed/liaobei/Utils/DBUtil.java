package vip.xposed.liaobei.Utils;

import android.util.Log;

/**
 * Created by AiXin on 2019-9-14.
 */
public class DBUtil {
    public static final boolean DEBUG = true;
    private static final String TAG = "调试";


    public static void log(String str) {
        if (DEBUG) {
            Log.i(TAG, str);
        }
    }

    public static void printParent(String tag) {
        try {
            throw new Exception("throwError");
        } catch (Exception e) {
            StringBuilder er = new StringBuilder();
            StackTraceElement[] ers = e.getStackTrace();
            int i = 0;
            for (StackTraceElement se : ers) {
                if (i > 3) {
                    er.append(se.toString()).append("\n");
                }
                i++;
                if (i >= 10) {
                    break;
                }
            }
            try {
                log(tag + " printParent=>\n" + er.toString());
            } catch (Exception ee) {
                System.out.println(er.toString());
            }
        }
    }

}
