package com.aike.log.platform;

/**
 * 创建时间: 2020/03/22 16:24 <br>
 * 作者: xiekongying001 <br>
 * 描述:输出到平台
 */
public interface IPrintPlatform {
  /**
   * 获取解析好的输入内容
   * @param msg
   * @return
   */
  String parsePrintContent(String msg);

  /**
   * 输出
   * @param level
   * @param msg
   */
  void printLog(int level,String tag,String msg);
}
