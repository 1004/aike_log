package com.aike.log.utils;

import android.text.TextUtils;
import android.util.Log;
import com.aike.log.bean.LogHeaderInfo;

/**
 * 创建时间: 2020/03/23 14:35 <br>
 * 作者: xiekongying001 <br>
 * 描述:
 */
public class AikeLogUtils {
  private static final String SUFFIX = ".java";

  public static LogHeaderInfo getLogHeaderInfo(int stackTraceIndex){
    LogHeaderInfo headerInfo = new LogHeaderInfo();
    StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
    if (stackTraceIndex>=stackTrace.length){
      return headerInfo;
    }
    StackTraceElement targetElement = stackTrace[stackTraceIndex];
    String className = targetElement.getClassName();
    String[] classNameInfo = className.split("\\.");
    if (classNameInfo.length > 0) {
      className = classNameInfo[classNameInfo.length - 1] + SUFFIX;
    }
    if (className.contains("$")) {
      className = className.split("\\$")[0] + SUFFIX;
    }
    String methodName = targetElement.getMethodName();
    int lineNumber = targetElement.getLineNumber();
    if (lineNumber < 0) {
      lineNumber = 0;
    }
    headerInfo.className= className;
    headerInfo.lineNum=lineNumber;
    headerInfo.methodName=methodName;
    return headerInfo;
  }

  public static boolean isEmpty(String line) {
    return TextUtils.isEmpty(line) || line.equals("\n") || line.equals("\t") || TextUtils.isEmpty(line.trim());
  }

  public static void printLine(String tag, boolean isTop) {
    if (isTop) {
      Log.d(tag, "╔═══════════════════════════════════════════════════════════════════════════════════════");
    } else {
      Log.d(tag, "╚═══════════════════════════════════════════════════════════════════════════════════════");
    }
  }
}
