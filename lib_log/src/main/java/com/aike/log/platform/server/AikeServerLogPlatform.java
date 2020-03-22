package com.aike.log.platform.server;

import com.aike.log.platform.IPrintPlatform;

/**
 * 创建时间: 2020/03/22 16:44 <br>
 * 作者: xiekongying001 <br>
 * 描述:
 */
public class AikeServerLogPlatform implements IPrintPlatform {
  @Override
  public String parsePrintContent(String msg) {
    return null;
  }

  @Override
  public void printLog(int level,String tag, String msg) {

  }
}
