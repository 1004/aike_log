package com.aike.log.bean;

import android.app.Application;

/**
 * 创建时间: 2020/03/23 15:30 <br>
 * 作者: xiekongying001 <br>
 * 描述:
 */
public class AikeLogParams {
  private String globalTag;//全局Tag
  private Application pluginApplication;//毕传，没有插件化，app的上下文，否则为插件上下文
  private boolean isShowLog;//毕传
  private boolean isDebug;
  private boolean isCanPushLog;
  private String  pushUrl;//毕传：dig上传地址

  public AikeLogParams(String globalTag,
      Application pluginApplication, boolean isShowLog, boolean isCanPushLog,
      String pushUrl,boolean isDebug) {
    this.globalTag = globalTag;
    this.pluginApplication = pluginApplication;
    this.isShowLog = isShowLog;
    this.isCanPushLog = isCanPushLog;
    this.pushUrl = pushUrl;
    this.isDebug = isDebug;
  }

  public String getGlobalTag() {
    return globalTag;
  }

  public Application getPluginApplication() {
    return pluginApplication;
  }

  public boolean isShowLog() {
    return isShowLog;
  }

  public boolean isCanPushLog() {
    return isCanPushLog;
  }
  public boolean isDebug() {
    return isDebug;
  }

  public String getPushUrl() {
    return pushUrl;
  }

  public static class Builder{
    private String globalTag;//毕传，没有插件化，可以写app名字
    private Application pluginApplication;//毕传，没有插件化，app的上下文，否则为插件上下文
    private boolean isShowLog;//毕传
    private boolean isCanPushLog;
    private String  pushUrl;//毕传：dig上传地址
    private boolean isDebug;

    public Builder globalTag(String pluginName){
      this.globalTag =pluginName;
      return this;
    }

    public Builder pushUrl(String pushUrl){
      this.pushUrl=pushUrl;
      return this;
    }

    public Builder pluginApplication(Application pluginApplication){
      this.pluginApplication=pluginApplication;
      return this;
    }

    public Builder isShowLog(boolean isShowLog){
      this.isShowLog=isShowLog;
      return this;
    }
    public Builder isDebug(boolean isDebug){
      this.isDebug=isDebug;
      return this;
    }

    public Builder isCanPushLog(boolean isCanPushLog){
      this.isCanPushLog=isCanPushLog;
      return this;
    }

    public AikeLogParams build(){
      return new AikeLogParams(globalTag,pluginApplication,isShowLog,isCanPushLog,pushUrl,isDebug);
    }
  }
}
