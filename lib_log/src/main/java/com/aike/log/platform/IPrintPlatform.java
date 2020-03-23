package com.aike.log.platform;

import com.aike.log.parse.ParseType;

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
  String parsePrintContent(@ParseType String contentType,String msg);

  /**
   * 输出
   */
  void printLog(int level, @ParseType String contentType, String tag, String msg);

  /**
   * 获取有效Tag
   * @param tag
   * @return
   */
  String getTag(String tag);
}
