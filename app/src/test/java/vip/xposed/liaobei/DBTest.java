package vip.xposed.liaobei;

import android.content.Context;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;

import org.junit.Test;

import vip.xposed.liaobei.Utils.DBUtil;

/**
 * Created by AiXin on 2019-10-22.
 */
public class DBTest extends SQLiteOpenHelper {

    private final String a = "CREATE TABLE IF NOT EXISTS DEFAULTSTORAGETABLE (ID INTEGER primary key AUTOINCREMENT,KEY TEXT,VALUE TEXT);";
    private SQLiteDatabase b;

    public static class a{
        public static final DBTest a = new DBTest(null);

    }
    public DBTest(Context context) {
        super(context, b(), null, 10);
        SQLiteDatabase.loadLibs(context);
    }

    private static String b() {
        return "chaoxin_storage.db";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    private void c() {
        if (this.b == null) {
            try {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("c500887d4c68c27f78580a8c98f33c0a");
                stringBuilder.append("f3335655-31a9-3535-b695-ac741dddcdd5");
                this.b = getWritableDatabase("03c6507e9f1e2daaee1ccbfb00009883");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



}
