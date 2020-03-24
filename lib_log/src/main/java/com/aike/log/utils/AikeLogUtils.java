package com.aike.log.utils;

import android.text.TextUtils;
import android.util.Log;
import com.aike.log.bean.LogHeaderInfo;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 创建时间: 2020/03/23 14:35 <br>
 * 作者: xiekongying001 <br>
 * 描述:
 */
public class AikeLogUtils {
  private static final String SUFFIX = ".java";

  public static LogHeaderInfo getLogHeaderInfo(int stackTraceIndex){
    LogHeaderInfo headerInfo = new LogHeaderInfo();
    StackTraceElement[] stackTrace = new Throwable().getStackTrace();

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

  public static String getStatckTrace(){
    Throwable tr = new Throwable();
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    tr.printStackTrace(pw);
    pw.flush();
    String message = sw.toString();
    String[] traceString = message.split("\\n\\t");
    StringBuilder sb = new StringBuilder();
    sb.append("\n");
    String trace;
    for(int var8 = 0; var8 < traceString.length; ++var8) {
      trace = traceString[var8];
      if (!trace.contains("at com.aike.log")) {
        sb.append(trace).append("\n");
      }
    }
    sb.append("\n");
    return sb.toString();
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
