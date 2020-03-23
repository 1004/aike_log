package com.aike.log;

import com.aike.dig.DigApiClient;
import com.aike.log.bean.AikeLogParams;
import com.aike.log.config.AikeLogConfig;
import com.aike.log.parse.ParseType;
import com.aike.log.platform.PrintLogManager;
import com.aike.log.platform.PrintPlatformType;

/**
 * 创建时间: 2020/03/22 16:21 <br>
 * 作者: xiekongying001 <br>
 * 描述:
 */
public class AikeLog {
  public static final int LEVEL_V = 0x1;
  public static final int LEVEL_D = 0x2;
  public static final int LEVEL_I = 0x3;
  public static final int LEVEL_W = 0x4;
  public static final int LEVEL_E = 0x5;
  public static final int LEVEL_A = 0x6;

  public static void initAikeLog(AikeLogParams aikeLogParams){
    DigApiClient.setDigDebug(aikeLogParams.isDebug());
    DigApiClient.initDig("aikelog",aikeLogParams.getPluginApplication(),aikeLogParams.getPushUrl());
    AikeLogConfig.isCanPushLog=aikeLogParams.isCanPushLog();
    AikeLogConfig.isShowLog=aikeLogParams.isShowLog();
    AikeLogConfig.mGlobalTag=aikeLogParams.getGlobalTag();
  }

  public static void printLog(int level,String msg){
    printLog(level,null,msg);
  }

  public static void printLog(int level,String tag,String msg){
    printLog(level,PrintPlatformType.LOGTYPE,tag,msg);
  }

  public static void printLog(int level,@PrintPlatformType String platform, String tag,String msg){
    printLog(level,platform,ParseType.STRINGTYPE,tag,msg);
  }

  public static void printLog(int level,@PrintPlatformType String platform, @ParseType String type, String tag,String msg){
    PrintLogManager.getInstance().printLog(platform,type,level,tag,msg);
  }

}
