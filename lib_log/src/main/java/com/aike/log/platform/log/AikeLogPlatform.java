package com.aike.log.platform.log;

import com.aike.log.platform.IPrintPlatform;

/**
 * 创建时间: 2020/03/22 16:45 <br>
 * 作者: xiekongying001 <br>
 * 描述:
 */
public class AikeLogPlatform implements IPrintPlatform {
  @Override
  public String parsePrintContent(String msg) {
    return null;
  }

  @Override
  public void printLog(int level,String tag, String msg) {

  }
}
