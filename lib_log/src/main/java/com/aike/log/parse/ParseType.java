package com.aike.log.parse;

import androidx.annotation.StringDef;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 创建时间: 2020/03/22 16:49 <br>
 * 作者: xiekongying001 <br>
 * 描述:
 */
@Retention(RetentionPolicy.SOURCE)
@StringDef({ParseType.JSONTYPE,ParseType.XMLTYPE,ParseType.STRINGTYPE})
public @interface ParseType {
  String JSONTYPE="jsonTpye";
  String XMLTYPE="xmlTpye";
  String STRINGTYPE="stringTpye";
}
