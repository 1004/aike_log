package com.aike.log.bean;

import java.io.Serializable;

/**
 * 创建时间: 2020/03/23 14:38 <br>
 * 作者: xiekongying001 <br>
 * 描述:
 */
public class LogHeaderInfo implements Serializable {
  public String className;
  public String methodName;
  public int lineNum;
}
