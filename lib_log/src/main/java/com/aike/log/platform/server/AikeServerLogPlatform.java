package com.aike.log.platform.server;

import com.aike.dig.DigApiClient;
import com.aike.dig.model.DigItemData;
import com.aike.dig.send.DigSendManager;
import com.aike.log.bean.LogHeaderInfo;
import com.aike.log.config.AikeLogConfig;
import com.aike.log.platform.APrintPlatform;
import com.aike.log.utils.AikeLogUtils;
import java.util.HashMap;
import java.util.Map;

/**
 * 创建时间: 2020/03/22 16:44 <br>
 * 作者: xiekongying001 <br>
 * 描述:日志推送到后端
 */
public class AikeServerLogPlatform extends APrintPlatform {

  @Override
  public void printLog(int level, String contentType, String tag, String msg) {
    if (!AikeLogConfig.isCanPushLog){
      return;
    }
    String realTag = getTag(tag);
    String content = parsePrintContent(contentType, msg);
    LogHeaderInfo logHeaderInfo = AikeLogUtils.getLogHeaderInfo(AikeLogConfig.STACK_TRACE_INDEX_5);
    DigItemData itemData = new DigItemData();
    Map<String, String> ext = new HashMap<>();
    ext.put("tag",realTag);
    ext.put("msg",content);
    ext.put("msg",content);
    ext.put("className",logHeaderInfo.className);
    ext.put("methodName",logHeaderInfo.methodName);
    ext.put("lineNum",logHeaderInfo.lineNum+"");
    itemData.setExt(ext);
    DigApiClient.sendDig(itemData);
  }
}
