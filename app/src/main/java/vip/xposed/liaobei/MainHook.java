package vip.xposed.liaobei;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.security.Key;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.CRC32;

import javax.crypto.interfaces.DHPrivateKey;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import vip.xposed.liaobei.Utils.Base64Utils;
import vip.xposed.liaobei.Utils.DBUtil;
import vip.xposed.liaobei.Utils.MStringUtils;
import vip.xposed.liaobei.Utils.NetConfig;

/**
 * Created by AiXin on 2019-9-12.
 */
public class MainHook implements IXposedHookLoadPackage {


    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        hookQiHu360ClasLoader(lpparam);
    }

    public void hookQiHu360ClasLoader(final XC_LoadPackage.LoadPackageParam loadPackageParam) {

        if (loadPackageParam.packageName.equals("com.yunzhan.liaobei")) {
            //DBUtil.log("奇虎360 " + loadPackageParam.packageName);
            XposedHelpers.findAndHookMethod("com.stub.StubApp", loadPackageParam.classLoader, "ᵢˋ", Context.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    //获取到360的Context对象，通过这个对象来获取classloader
                    Context context = (Context) param.args[0];
                    //获取360的classloader，之后hook加固后的就使用这个classloader
                    ClassLoader classLoader = context.getClassLoader();
                    //下面就是强classloader修改成360的classloader就可以成功的hook了
                    //DBUtil.log("奇虎360 classloader ->" + classLoader.toString());
                    hookDefineClass(classLoader);
                    hookLoadClass(classLoader);

                }
            });
            //DBUtil.log("奇虎360 HOOK成功" + loadPackageParam.packageName);
        }


    }

    public void hookDefineClass(final ClassLoader cl) {
        try {
            /*get DexFile Class*/
            final Class clazz = cl.loadClass("dalvik.system.DexFile");
            Method[] methods = clazz.getDeclaredMethods();
            for (int i = 0; i < methods.length; i++) {
                String name = methods[i].getName();
                if (name.equalsIgnoreCase("defineClass")) {
                    XposedBridge.hookMethod(methods[i], new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            Class clz = (Class) param.getResult();
                            if (clz != null) {
                                liaobeiHook(cl, (Class) clz);//与下面的重复
                            }
                        }
                    });
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void hookLoadClass(final ClassLoader cl) {
        try {
            XposedHelpers.findAndHookMethod(ClassLoader.class, "loadClass", String.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    if (param.hasThrowable()) {
                        return;
                    }
                    Object clz = param.getResult();
                    if (clz != null) {
                        liaobeiHook(cl, (Class) clz);
                    }

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void liaobeiHook(final ClassLoader classLoader, Class clazz) {
        String className = clazz.getName();
        if (className.equals("com.cqchat.e.a.n.e.d")) {
            DBUtil.log("聊呗 com.cqchat.e.a.n.e.d MobileNumberImpl");

            XposedHelpers.findAndHookConstructor(clazz, int.class, long.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    DBUtil.log("聊呗 com.cqchat.e.a.n.e.d MobileNumberImpl");
                    DBUtil.log("聊呗 com.cqchat.e.a.n.e.d MobileNumberImpl 参数1=>" + param.args[0]);
                    DBUtil.log("聊呗 com.cqchat.e.a.n.e.d MobileNumberImpl 参数2=>" + param.args[1]);

                }
            });
        }
        if (className.equals("com.cqchat.android.cache.o")) {
            DBUtil.log("聊呗 chaoxin_storage.db");
            XposedHelpers.findAndHookMethod(clazz, "a", String.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    DBUtil.log("聊呗 chaoxin_storage.db a 参数1=>" + param.args[0]);
                    List<Map<String, String>> list = (List<Map<String, String>>) param.getResult();
                    for (Map<String, String> m : list) {
                        for (Map.Entry<String, String> map : m.entrySet()) {
                            DBUtil.log("聊呗 chaoxin_storage.db a 结果=>key:" + map.getKey());
                            DBUtil.log("聊呗 chaoxin_storage.db a 结果=>val:" + map.getValue());
                        }
                    }
                }
            });
            XposedHelpers.findAndHookMethod(clazz, "b", String.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    DBUtil.log("聊呗 chaoxin_storage.db b 参数1=>" + param.args[0]);
                    List<String> list = (List<String>) param.getResult();
                    for (String s : list) {
                        DBUtil.log("聊呗 chaoxin_storage.db b 结果=>" + s);
                    }
                    DBUtil.printParent("聊呗 chaoxin_storage.db b");

                }
            });
            XposedHelpers.findAndHookMethod(clazz, "c", String.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    DBUtil.log("聊呗 chaoxin_storage.db c 参数1=>" + param.args[0]);

                }
            });
            XposedHelpers.findAndHookMethod(clazz, "a", List.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    List<String> list = (List<String>) param.args[0];
                    for (String s : list) {
                        DBUtil.log("聊呗 chaoxin_storage.db a(list) 参数1=>" + s);
                    }

                }
            });
        }
        if (className.equals("com.cqchat.android.a.b.c")) {
            DBUtil.log("聊呗 握手加解密 DH");
            XposedHelpers.findAndHookMethod(clazz, "a", new XC_MethodHook() {

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    //目的 查看DHPublicKey.y DHPrivateKey.x 的值
                    DHPrivateKey d = (DHPrivateKey) XposedHelpers.getObjectField(param.thisObject, "d");
                    DBUtil.log("聊呗 握手加解密 DH私钥 DHPrivateKey.x=>" + Base64Utils.encode(d.getX().toByteArray()));
                    DBUtil.log("聊呗 握手加解密 DH公钥 DHPublicKey.y=>" + Base64Utils.encode((byte[]) param.getResult()));

                }
            });
            XposedHelpers.findAndHookMethod(clazz, "a", byte[].class, new XC_MethodHook() {

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    //目的 查看密文 和解密出来的数据

                    DBUtil.log("聊呗 握手加解密 DH解密 参数1=>" + Base64Utils.encode((byte[]) param.args[0]));
                    DBUtil.log("聊呗 握手加解密 DH解密 结果=>" + MStringUtils.bytesToHex((byte[]) param.getResult(), false));
                }
            });
        }

        if (className.equals("com.cqchat.android.o.m")) {
            DBUtil.log("聊呗 握手加解密 RSAUtil");
            XposedHelpers.findAndHookMethod(clazz, "a", Key.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    Key key = (Key) param.args[0];
                    DBUtil.log("聊呗 握手加解密 RSAUtil a getFormat=>" + key.getFormat());
                    DBUtil.log("聊呗 握手加解密 RSAUtil a getAlgorithm=>" + key.getAlgorithm());
                    DBUtil.log("聊呗 握手加解密 RSAUtil a getEncoded=>" + Base64Utils.encode(key.getEncoded()));
                }
            });
            XposedHelpers.findAndHookMethod(clazz, "a", byte[].class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    Key key = (Key) XposedHelpers.getObjectField(param.thisObject, "a");
                    DBUtil.log("聊呗 握手加解密 RSAUtil a getFormat=>" + key.getFormat());
                    DBUtil.log("聊呗 握手加解密 RSAUtil a getAlgorithm=>" + key.getAlgorithm());
                    DBUtil.log("聊呗 握手加解密 RSAUtil a getEncoded=>" + Base64Utils.encode(key.getEncoded()));
                    DBUtil.log("聊呗 握手加解密 RSAUtil a 解密 参数1=>" + Base64Utils.encode((byte[]) param.args[0]));
                    DBUtil.log("聊呗 握手加解密 RSAUtil a 解密 结果=>" + Base64Utils.encode((byte[]) param.getResult()));

                }
            });
            XposedHelpers.findAndHookMethod(clazz, "b", byte[].class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    Key key = (Key) XposedHelpers.getObjectField(param.thisObject, "a");
                    DBUtil.log("聊呗 握手加解密 RSAUtil a getFormat=>" + key.getFormat());
                    DBUtil.log("聊呗 握手加解密 RSAUtil a getAlgorithm=>" + key.getAlgorithm());
                    DBUtil.log("聊呗 握手加解密 RSAUtil a getEncoded=>" + Base64Utils.encode(key.getEncoded()));
                    DBUtil.log("聊呗 握手加解密 RSAUtil b 加密 参数1=>" + Base64Utils.encode((byte[]) param.args[0]));
                    DBUtil.log("聊呗 握手加解密 RSAUtil b 加密 结果=>" + Base64Utils.encode((byte[]) param.getResult()));

                }
            });
        }

        if (className.equals("com.cqchat.android.d.a.f")) {
            DBUtil.log("聊呗 SnappyEncryptedPayloadNioSocket");

            XposedHelpers.findAndHookMethod(clazz, "b", byte[].class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    DBUtil.log("聊呗 数据加解密 SnappyEncryptedPayloadNioSocket b 解密 参数1=> len=" + ((byte[]) param.args[0]).length);
                    DBUtil.log("聊呗 数据加解密 SnappyEncryptedPayloadNioSocket b 解密 参数1=>" + MStringUtils.bytesToHex(((byte[]) param.args[0]), false));
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    DBUtil.log("聊呗 数据加解密 SnappyEncryptedPayloadNioSocket b 解密 结果=>" + new String((byte[]) param.getResult()));
                }
            });
            XposedHelpers.findAndHookMethod(clazz, "c", byte[].class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    DBUtil.log("聊呗 数据加解密 SnappyEncryptedPayloadNioSocket c 加密 参数1=>" + new String((byte[]) param.args[0]));
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    DBUtil.log("聊呗 数据加解密 SnappyEncryptedPayloadNioSocket c 加密 结果=>len=" + ((byte[]) param.getResult()).length);
                    DBUtil.log("聊呗 数据加解密 SnappyEncryptedPayloadNioSocket c 加密 结果=>" + MStringUtils.bytesToHex((byte[]) param.getResult(), false));

                }
            });

        }
        if (className.equals("com.cqchat.android.d.a.c")) {
            DBUtil.log("聊呗 EncryptedPayloadNioSocket");

            XposedHelpers.findAndHookMethod(clazz, "b", byte[].class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    DBUtil.log("聊呗 数据加解密 EncryptedPayloadNioSocket b 解密 参数1=> len=" + ((byte[]) param.args[0]).length);
                    DBUtil.log("聊呗 数据加解密 EncryptedPayloadNioSocket b 解密 参数1=>" + MStringUtils.bytesToHex(((byte[]) param.args[0]), false));
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {

                    DBUtil.log("聊呗 数据加解密 EncryptedPayloadNioSocket b 解密 结果=>" + MStringUtils.bytesToHex((byte[]) param.getResult(), false));
                    DBUtil.log("聊呗 数据加解密 EncryptedPayloadNioSocket b 解密 结果=>" + new String((byte[]) param.getResult()));

                }
            });
            XposedHelpers.findAndHookMethod(clazz, "c", byte[].class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    DBUtil.log("聊呗 数据加解密 EncryptedPayloadNioSocket c 加密 参数1=>" + MStringUtils.bytesToHex((byte[]) param.args[0], false));
                    DBUtil.log("聊呗 数据加解密 EncryptedPayloadNioSocket c 加密 参数1=>" + new String((byte[]) param.args[0]));

                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    DBUtil.log("聊呗 数据加解密 EncryptedPayloadNioSocket c 加密 结果=>len=" + ((byte[]) param.getResult()).length);
                    DBUtil.log("聊呗 数据加解密 EncryptedPayloadNioSocket c 加密 结果=>" + MStringUtils.bytesToHex((byte[]) param.getResult(), false));

                }
            });
            XposedHelpers.findAndHookMethod(clazz, "d", byte[].class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    DBUtil.log("聊呗 数据加解密 EncryptedPayloadNioSocket d AES密钥解析 参数1=>" + new String((byte[]) param.args[0]));

                }
            });
        }
        if (className.equals("com.cqchat.android.a.b.a")) {
            DBUtil.log("聊呗 socket AES 加解密 AES/ECB/PKCS5Padding");
            XposedHelpers.findAndHookMethod(clazz, "a", byte[].class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    DBUtil.log("聊呗 数据加解密 socket AES 加解密 KEY=>" + MStringUtils.bytesToHex((byte[]) param.args[0], false));
                }
            });
            XposedHelpers.findAndHookMethod(clazz, "a", byte[].class, int.class, int.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    DBUtil.log("聊呗 数据加解密 socket AES a 解密 参数1=> len=" + ((byte[]) param.args[0]).length + "==");
                    DBUtil.log("聊呗 数据加解密 socket AES a 解密 参数1=>" + MStringUtils.bytesToHex(((byte[]) param.args[0]), false));
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {

                    DBUtil.log("聊呗 数据加解密 socket AES a 解密 结果=>" + MStringUtils.bytesToHex((byte[]) param.getResult(), false));
                    DBUtil.log("聊呗 数据加解密 socket AES a 解密 结果=>" + new String((byte[]) param.getResult()));

                }
            });
            XposedHelpers.findAndHookMethod(clazz, "b", byte[].class, int.class, int.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    DBUtil.log("聊呗 数据加解密 socket AES b 加密 参数1=>" + MStringUtils.bytesToHex((byte[]) param.args[0], false));
                    DBUtil.log("聊呗 数据加解密 socket AES b 加密 参数1=>" + new String((byte[]) param.args[0]));
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {

                    DBUtil.log("聊呗 数据加解密 socket AES b 加密 结果=>len=" + ((byte[]) param.getResult()).length);
                    DBUtil.log("聊呗 数据加解密 socket AES b 加密 结果=>" + MStringUtils.bytesToHex((byte[]) param.getResult(), false));

                }
            });
        }
        if (className.equals("com.cqchat.e.a.d.p")) {
            DBUtil.log("聊呗 SockLog String d(String str) Nio");
            //String d(String str)
            XposedHelpers.findAndHookMethod(clazz, "d", String.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    DBUtil.log("聊呗 SockLog String d(String str) Nio 参数1->" + param.args[0]);
                    DBUtil.log("聊呗 SockLog String d(String str) Nio 结果->" + param.getResult());

                }
            });
        }
        if (className.equals("e.w$a")) {
            //DBUtil.log("聊呗 找到设置代理的类");
            XposedHelpers.findAndHookMethod(clazz, "a", Proxy.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("192.168.1.111", 8880));
                    param.args[0] = proxy;
                    //DBUtil.log("聊呗 设置代理->" + "type=" + proxy.type().name() + "  address=" + proxy.address().toString());
                }
            });
        }
        ////////////sign 签名
        if (className.equals("com.cqchat.android.a.b.f")) {
            DBUtil.log("聊呗 com.cqchat.android.a.b.f String a(String str) MD5");
            XposedHelpers.findAndHookMethod(clazz, "a", String.class, String.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    DBUtil.log("聊呗 com.cqchat.android.a.b.f String a(String str) MD5 参数1->" + param.args[0]);
                    DBUtil.log("聊呗 com.cqchat.android.a.b.f String a(String str) MD5 参数2->" + param.args[1]);
                    DBUtil.log("聊呗 com.cqchat.android.a.b.f String a(String str) MD5 结果->" + param.getResult());

                }
            });
        }
        if (className.equals("com.cqchat.k.a.a.k.d")) {
            DBUtil.log("聊呗 com.cqchat.k.a.a.k.d String a(String str) MD5==");
            XposedHelpers.findAndHookMethod(clazz, "a", String.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    DBUtil.log("聊呗 com.cqchat.k.a.a.k.d String a(String str) MD5== 参数1->" + param.args[0]);
                    DBUtil.log("聊呗 com.cqchat.k.a.a.k.d String a(String str) MD5== 结果->" + param.getResult());

                }
            });
        }

        //CtUrlMetadataCache
        if (className.equals("com.cqchat.j.d.d.s")) {
            DBUtil.log("聊呗 com.cqchat.j.d.d.s String a(String str) SQL");
            // public void a(long j, String str, d dVar)
            try {
                Class cls = classLoader.loadClass("com.cqchat.k.a.a.d.c.d");
                if (cls != null) {
                    XposedHelpers.findAndHookMethod(clazz, "a", long.class, String.class, cls, new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            DBUtil.log("聊呗 com.cqchat.j.d.d SQL inser or replace 参数1->" + param.args[0]);
                            DBUtil.log("聊呗 com.cqchat.j.d.d SQL inser or replace 参数2->" + param.args[1]);
                            DBUtil.log("聊呗 com.cqchat.j.d.d SQL inser or replace 参数3->" + param.args[2]);
                        }
                    });

                    XposedHelpers.findAndHookMethod(clazz, "a", long.class, String.class, new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            DBUtil.log("聊呗 com.cqchat.j.d.d SQL SELECT 参数1->" + param.args[0]);
                            DBUtil.log("聊呗 com.cqchat.j.d.d SQL SELECT 参数2->" + param.args[1]);
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        ////////////sign 签名
        //AESUtils
        if (className.equals("com.cqchat.android.a.b.b")) {
            //String a(String str, String str2)
            DBUtil.log("聊呗 com.cqchat.android.a.b.b String a(String str) AESUtils");
            XposedHelpers.findAndHookMethod(clazz, "a", String.class, String.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    DBUtil.log("聊呗 com.cqchat.android.a.b.f String a(String str) AESUtils 解密 参数1->" + param.args[0]);
                    DBUtil.log("聊呗 com.cqchat.android.a.b.f String a(String str) AESUtils 解密 参数2->" + param.args[1]);
                    DBUtil.log("聊呗 com.cqchat.android.a.b.f String a(String str) AESUtils 解密 结果->" + param.getResult());
                }
            });

        }
        if (className.equals("com.cqchat.j.n.b.a.j.r")) {
            //DBUtil.log("聊呗 登录 " + className);
            //@a(a = "usr/lg")
            /* compiled from: CtLoginProtocolPacket */

            XposedHelpers.findAndHookMethod(clazz, "a", String.class, new XC_MethodHook() {//通过这个方法可以找到写入数据库
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    String uuid = getUUID(classLoader);
                    //DBUtil.log("聊呗 登录11 uuid ->" + uuid);
                    String responsed = (String) param.args[0];
                    //DBUtil.log("聊呗 登录11 responsed ->" + responsed);

                    JSONObject jsonObj = JSON.parseObject(responsed);
                    if (jsonObj != null) {
                        //DBUtil.log("聊呗 登录参数 解析JSON对象成功");
                        if (jsonObj.getInteger("r") == 0) {
                            //DBUtil.log("聊呗 登录参数 对象返回 r 值 为0");
                            JSONObject dataObj = jsonObj.getJSONObject("data");
                            if (dataObj != null) {
                                //DBUtil.log("聊呗 登录参数 data 对象 不为空");
                                UserBean ub = new UserBean();
                                ub.setMid(dataObj.getString("mid"));
                                ub.setUid(dataObj.getString("uid"));
                                ub.setUuid(uuid);
                                ub.setTk(dataObj.getString("tk"));
                                ub.setRtk(dataObj.getString("rtk"));
                                ub.setCk(dataObj.getString("ck"));
                                ub.setUn(dataObj.getString("un"));
                                ub.setUnk(dataObj.getString("unk"));
                                //DBUtil.log("聊呗 登录参数 toString() ->" + ub.toString());
                                String json = JSON.toJSONString(ub);
                                DBUtil.log("聊呗 登录参数 JSON ->" + json);
                                NetConfig.setLoginParam(json);
                            } else {
                                //DBUtil.log("聊呗 登录参数 data 对象为空");
                            }
                        } else {
                            //DBUtil.log("聊呗 登录参数 对象返回 r 值 不为0");
                        }
                    } else {
                        //DBUtil.log("聊呗 登录参数 解析JSON对象失败");
                    }
                }
            });
        }
        if (className.equals("com.cqchat.j.n.b.b.f.c")) {
            //DBUtil.log("聊呗 登录22 " + className);

            Class rClazz = XposedHelpers.findClass("im.a.a.b.b.a.i.r", classLoader);
            if (rClazz != null) {
                //DBUtil.log("聊呗 登录221 " + className + "--" + rClazz.getName());
                XposedHelpers.findAndHookMethod(clazz, "a", String.class, rClazz, new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        //DBUtil.log("聊呗 登录221 uuid ->" + getUUID(classLoader));
                        String responsed = (String) param.args[0];
                        DBUtil.log("聊呗 登录221 responsed ->" + responsed);
                    }
                });
            }
            Class oClazz = XposedHelpers.findClass("com.cqchat.e.a.k.o", classLoader);
            if (rClazz != null && oClazz != null) {
                //DBUtil.log("聊呗 登录222 " + className + "--" + rClazz.getName());
                XposedHelpers.findAndHookMethod(clazz, "a", oClazz, rClazz, new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        DBUtil.log("聊呗 登录222 uuid ->" + getUUID(classLoader));
                    }
                });
            }
        }
        if (className.equals("com.cqchat.e.a.k.b")) {
            //DBUtil.log("聊呗 HttpResponseDataParser " + className);
            Class oClazz = XposedHelpers.findClass("com.cqchat.e.a.k.o", classLoader);
            XposedHelpers.findAndHookMethod(clazz, "a", String.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    //DBUtil.log("聊呗 HttpResponseDataParser uuid ->" + getUUID(classLoader));
                    String responsed = (String) param.args[0];
                    DBUtil.log("聊呗 HttpResponseDataParser o a(String str) responsed ->" + responsed);
                }
            });
            XposedHelpers.findAndHookMethod(clazz, "b", String.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    String responsed = (String) param.args[0];
                    DBUtil.log("聊呗 HttpResponseDataParser o b(String str) responsed ->" + responsed);
                }
            });
            XposedHelpers.findAndHookMethod(clazz, "c", String.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    String responsed = (String) param.args[0];
                    DBUtil.log("聊呗 HttpResponseDataParser String c(String str) responsed ->" + responsed);
                }
            });
            if (oClazz != null) {
                XposedHelpers.findAndHookMethod(clazz, "a", oClazz, new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        //DBUtil.log("聊呗 HttpResponseDataParser o a(o oVar) ");
                    }
                });
                XposedHelpers.findAndHookMethod(clazz, "b", oClazz, new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        //DBUtil.log("聊呗 HttpResponseDataParser o b(o oVar) ");
                    }
                });
            }
        }
        if (className.equals("com.cqchat.k.a.a.k.d")) {
            DBUtil.log("聊呗 Md5Util " + className);
            XposedHelpers.findAndHookMethod(clazz, "a", String.class, new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    DBUtil.log("聊呗 Md5Util String a(String str) ->" + (String) param.args[0] + "=>" + (String) param.getResult());
                }
            });
        }
        if (className.equals("net.sqlcipher.database.SQLiteOpenHelper")) {
            //DBUtil.log("聊呗 sqlcipher " + className);
            XposedHelpers.findAndHookMethod(clazz, "getWritableDatabase", String.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    DBUtil.log("聊呗 sqlcipher String a(String str) ->" + (String) param.args[0]);
                }
            });
        }

    }

    private String getUUID(ClassLoader classLoader) {
        String uuid = "";
        Class AndroidNativeDeviceUuidGeneratorClass = XposedHelpers.findClass("com.cqchat.android.o.a", classLoader);

        if (AndroidNativeDeviceUuidGeneratorClass != null) {
            Object AndroidNativeDeviceUuidGeneratorObject = XposedHelpers.newInstance(AndroidNativeDeviceUuidGeneratorClass);
            if (AndroidNativeDeviceUuidGeneratorObject != null) {
                uuid = (String) XposedHelpers.callMethod(AndroidNativeDeviceUuidGeneratorObject, "a");
            }
        }
        return uuid;
    }
}
