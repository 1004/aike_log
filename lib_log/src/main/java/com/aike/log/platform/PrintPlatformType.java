package com.aike.log.platform;

import androidx.annotation.StringDef;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 创建时间: 2020/03/22 16:49 <br>
 * 作者: xiekongying001 <br>
 * 描述:
 */
@Retention(RetentionPolicy.SOURCE)
@StringDef({ PrintPlatformType.UITYPE, PrintPlatformType.LOGTYPE, PrintPlatformType.SERVERTYPE})
public @interface PrintPlatformType {
  String UITYPE="uitype";
  String LOGTYPE="logtype";
  String SERVERTYPE="servertype";
}
