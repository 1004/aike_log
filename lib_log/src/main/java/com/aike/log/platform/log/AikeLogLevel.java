package com.aike.log.platform.log;


import com.aike.log.AikeLog;

public class AikeLogLevel {
  public static String getLevelName(int logLevel) {
    String levelName;
    switch (logLevel) {
      case AikeLog.LEVEL_V:
        levelName = "VERBOSE";
        break;
      case AikeLog.LEVEL_D:
        levelName = "DEBUG";
        break;
      case AikeLog.LEVEL_I:
        levelName = "INFO";
        break;
      case AikeLog.LEVEL_W:
        levelName = "WARN";
        break;
      case AikeLog.LEVEL_E:
        levelName = "ERROR";
        break;
      default:
        throw new IllegalArgumentException("Invalid log level: " + logLevel);
    }
    return levelName;
  }
}
