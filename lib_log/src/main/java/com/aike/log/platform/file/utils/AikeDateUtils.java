package com.aike.log.platform.file.utils;

import java.text.SimpleDateFormat;

/**
 * 创建时间: 2020/10/14 18:18 <br>
 * 作者: xiekongying <br>
 * 描述:
 */
public class AikeDateUtils {
  public final static SimpleDateFormat DATAFORMAT_ALL = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  public static String getTimeStrBySeconds(long time) {
    return DATAFORMAT_ALL.format(time);
  }
}
