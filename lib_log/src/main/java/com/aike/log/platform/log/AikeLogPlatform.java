package com.aike.log.platform.log;

import android.text.TextUtils;
import android.util.Log;
import com.aike.log.AikeLog;
import com.aike.log.bean.LogHeaderInfo;
import com.aike.log.config.AikeLogConfig;
import com.aike.log.platform.APrintPlatform;
import com.aike.log.utils.AikeLogUtils;

/**
 * 创建时间: 2020/03/22 16:45 <br>
 * 作者: xiekongying001 <br>
 * 描述:
 */
public class AikeLogPlatform extends APrintPlatform {
  private static final int MAX_LENGTH = 4000;

  @Override
  public void printLog(int level, String contentType, String tag, String msg) {
    if (!AikeLogConfig.isShowLog){
      return;
    }
    String content = parsePrintContent(contentType, msg);
    LogHeaderInfo logHeaderInfo = AikeLogUtils.getLogHeaderInfo(AikeLogConfig.STACK_TRACE_INDEX_5);
    String headString = "[ ("
        + logHeaderInfo.className
        + ":"
        + logHeaderInfo.lineNum
        + ")#"
        + logHeaderInfo.methodName
        + " ] ";
    String realTag = getTag(tag);
    AikeLogUtils.printLine(realTag, true);
    print(level, realTag, headString);
    if (!TextUtils.isEmpty(content)) {
      String[] lines = content.split(AikeLogConfig.LINE_SEPARATOR);
      for (String line : lines) {
        printLine(level, realTag, line);
      }
    }
    AikeLogUtils.printLine(realTag, false);
  }

  private void printLine(int level, String tag, String line) {
    if (!TextUtils.isEmpty(line)){
      int index = 0;
      int length = line.length();
      int countOfSub = length / MAX_LENGTH;
      int countOfDivide = length%MAX_LENGTH;
      if (countOfSub>0){
        for (int i=0;i<countOfSub;i++){
          String sub = line.substring(index, index + MAX_LENGTH);
          print(level,tag,sub);
          index=index + MAX_LENGTH;
        }
        if (countOfDivide>0){
          print(level,tag,line.substring(index, length));
        }
      }else {
        print(level,tag,line);
      }
    }
  }

  private void print(int level,String tag,String msg) {
    switch (level) {
      case AikeLog.LEVEL_V:
        Log.v(tag, msg);
        break;
      case AikeLog.LEVEL_D:
        Log.d(tag, msg);
        break;
      case AikeLog.LEVEL_W:
        Log.w(tag, msg);
        break;
      case AikeLog.LEVEL_E:
        Log.e(tag, msg);
        break;
      case AikeLog.LEVEL_I:
        Log.i(tag, msg);
        break;
    }
  }
}
