package com.aike.log.parse;

import com.aike.log.parse.json.JsonLogContentParser;
import com.aike.log.parse.string.StringLogContentParser;
import com.aike.log.parse.xml.XmlLogContentParser;
import java.util.HashMap;
import java.util.Map;

/**
 * 创建时间: 2020/03/22 16:57 <br>
 * 作者: xiekongying001 <br>
 * 描述:
 */
public class LogParseManager {
  private LogParseManager(){initParser();}
  private static LogParseManager instance;
  private Map<String,ILogContentParser> parserMap = new HashMap<>();
  public static LogParseManager getInstance(){
    if (instance == null){
      synchronized (LogParseManager.class){
        if (instance == null){
          instance = new LogParseManager();
        }
      }
    }
    return instance;
  }
  private void initParser(){
    parserMap.put(ParseType.JSONTYPE,new JsonLogContentParser());
    parserMap.put(ParseType.XMLTYPE,new XmlLogContentParser());
    parserMap.put(ParseType.STRINGTYPE,new StringLogContentParser());
  }

  public ILogContentParser getContentParser(@ParseType String parseType){
    return parserMap.get(parseType);
  }

}
