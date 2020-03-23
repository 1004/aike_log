package com.aike.log.platform.server;

import com.aike.log.bean.LogHeaderInfo;
import java.io.Serializable;

/**
 * 创建时间: 2020/03/23 15:51 <br>
 * 作者: xiekongying001 <br>
 * 描述:
 */
public class AikeServerLogBean implements Serializable {
  public String tag;
  public String content;
  public LogHeaderInfo headerInfo;
  public int level;
}
