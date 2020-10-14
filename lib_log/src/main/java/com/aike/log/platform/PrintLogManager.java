package com.aike.log.platform;

import com.aike.log.parse.ParseType;
import com.aike.log.platform.file.AikeFilePlatform;
import com.aike.log.platform.log.AikeLogPlatform;
import com.aike.log.platform.server.AikeServerLogPlatform;
import com.aike.log.platform.ui.AikeUiLogPlatform;
import java.util.HashMap;
import java.util.Map;

/**
 * 创建时间: 2020/03/22 17:15 <br>
 * 作者: xiekongying001 <br>
 * 描述:
 */
public class PrintLogManager {
  private PrintLogManager(){initPlatform();}
  private static PrintLogManager instance;
  private Map<String,IPrintPlatform> printPlatformMap = new HashMap<>();
  public static PrintLogManager getInstance(){
    if (instance == null) {
      synchronized (PrintLogManager.class){
        if (instance == null){
          instance = new PrintLogManager();
        }
      }
    }
    return instance;
  }
  private void initPlatform(){
    printPlatformMap.put(PrintPlatformType.LOGTYPE,new AikeLogPlatform());
    printPlatformMap.put(PrintPlatformType.SERVERTYPE,new AikeServerLogPlatform());
    printPlatformMap.put(PrintPlatformType.UITYPE,new AikeUiLogPlatform());
    printPlatformMap.put(PrintPlatformType.FILETYPE,new AikeFilePlatform());
  }

  public void printLog(@PrintPlatformType String platform,@ParseType String type, int level,String tag,String meg){
    printPlatformMap.get(platform).printLog(level,type,tag, meg);
  }
}
