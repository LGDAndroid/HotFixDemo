package com.jackshao.testfixhot;

import android.app.Application;
import android.content.Context;
import com.taobao.sophix.PatchStatus;
import com.taobao.sophix.SophixManager;
import com.taobao.sophix.listener.PatchLoadStatusListener;


public class BaseApplication extends Application {

    private static BaseApplication myApplication = null;

    public static BaseApplication getApplication() {
        return myApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        SophixManager.getInstance().queryAndLoadNewPatch();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//         如果需要使用MultiDex，需要在此处调用。
//         MultiDex.install(this);
        initSophix();
    }

    private void initSophix() {
        String appVersion = "0.0.0";
        try {
            appVersion = this.getPackageManager()
              .getPackageInfo(this.getPackageName(), 0)
              .versionName;
        } catch (Exception e) {
        }
        final SophixManager instance = SophixManager.getInstance();
        instance.setContext(this)
          .setAppVersion(appVersion)
          .setSecretMetaData(null, null, null)
          .setEnableDebug(true)
          .setEnableFullLog()
          .setPatchLoadStatusStub(new PatchLoadStatusListener() {
              @Override
              public void onLoad(final int mode, final int code, final String info, final int handlePatchVersion) {
                  if (code == PatchStatus.CODE_LOAD_SUCCESS) {
                      System.out.println("jack------>补丁读取完毕了 sophix load patch success!");
                  } else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
                      // 如果需要在后台重启，建议此处用SharePreference保存状态。
                      //修复完毕后 可以做一些重启 或者提示的动作
                      System.out.println("jack------>补丁修复完毕了 sophix preload patch success. restart app to make effect.");
                  }else {
                      System.out.println("\n code= " + code);
                      System.out.println("\n mode= " + mode);
                      System.out.println("\n info= " + info);
                      System.out.println("\n handlePatchVersion= " + handlePatchVersion);
                  }
              }
          }).initialize();
    }
}
