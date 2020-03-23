package com.aike.log.platform;

import android.text.TextUtils;
import com.aike.log.config.AikeLogConfig;
import com.aike.log.parse.LogParseManager;

/**
 * 创建时间: 2020/03/23 14:22 <br>
 * 作者: xiekongying001 <br>
 * 描述:
 */
public abstract class APrintPlatform implements IPrintPlatform{
  @Override
  public String parsePrintContent(String contentType, String msg) {
    return LogParseManager.getInstance().getContentParser(contentType).parseConent(msg);
  }

  @Override
  public String getTag(String tag) {
    if (!TextUtils.isEmpty(tag)){
      return tag;
    }else if (!TextUtils.isEmpty(AikeLogConfig.mGlobalTag)){
      return AikeLogConfig.mGlobalTag;
    }else {
      return AikeLogConfig.AIKE_DEFAULT_TAG;
    }
  }
}
