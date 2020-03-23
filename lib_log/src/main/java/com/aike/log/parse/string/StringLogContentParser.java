package com.aike.log.parse.string;

import android.text.TextUtils;
import com.aike.log.parse.ILogContentParser;

/**
 * 创建时间: 2020/03/22 16:47 <br>
 * 作者: xiekongying001 <br>
 * 描述:
 */
public final class StringLogContentParser implements ILogContentParser{
  @Override
  public String parseConent(String msg) {
    if (TextUtils.isEmpty(msg)){
      return "";
    }else {
      return msg.trim();
    }
  }
}
