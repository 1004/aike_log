package com.aike.log.config;

import android.app.Application;

/**
 * 创建时间: 2020/03/22 16:48 <br>
 * 作者: xiekongying001 <br>
 * 描述:
 */
public class AikeLogConfig {
  public static final String LINE_SEPARATOR = System.getProperty("line.separator");
  public static final int STACK_TRACE_INDEX_5 = 4;

  public static boolean isShowLog=true;
  public static boolean isCanPushLog=true;

  public static String mGlobalTag;
  public static String AIKE_DEFAULT_TAG = "aikelog";
  public static Application application;
}
