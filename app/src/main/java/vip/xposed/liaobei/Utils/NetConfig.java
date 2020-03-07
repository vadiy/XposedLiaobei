package vip.xposed.liaobei.Utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by AiXin on 2019-9-14.
 */
public class NetConfig {

    public static boolean setLoginParam(String json) {
        String token = new SettingUtil().getToken();
        if (token != "" && token.length() == 32) {
            String loginParam = RSAUtils.encrypt(json);
            JSONObject jobj = new JSONObject();
            jobj.put("loginParam", loginParam);
            String newJson = JSON.toJSONString(jobj);

            OKHttp3Utils.postAsync(OKHttp3Utils.getUrl() + "/LoginParam?token=" + token, newJson, new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    // PostToastUtil.showLong("提交失败");
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    String result = response.body().string();
                    DBUtil.log(result);
                    String toastStr = "提交失败";
                    try {
                        JSONObject jsonObj = JSON.parseObject(result);
                        if (jsonObj != null) {
                            if (jsonObj.getInteger("result") == 0) {
                                toastStr = "提交成功";
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    // PostToastUtil.showLong(toastStr);
                }
            });
        }
        return false;
    }
}
