package com.aike.log.platform.file.delete;

import com.aike.log.platform.file.utils.AikeFileUtils;
import java.io.File;
import java.util.List;

/**
 * 创建时间: 2020/10/14 16:25 <br>
 * 作者: xiekongying <br>
 * 描述:
 */
public class AikeFileTimeDeleteStrategy implements IFileDeleteStrategy{
  private long fileSaveTime;//超时时间

  public AikeFileTimeDeleteStrategy(long fileSaveTime) {
    this.fileSaveTime = fileSaveTime;
  }

  @Override
  public void deleteFile(String path) {
    if (path == null || path.length() == 0) {
      return;
    }
    File file = new File(path);

    if (file == null || !file.exists()) {
      return;
    }
    List<File> files = AikeFileUtils.getSortListFile(file);
    if (files == null) return;
    long startTime;
    for (File bean : files) {
      if (bean == null || !bean.exists()) {
        continue;
      }
      startTime = AikeFileUtils.getStartTime(bean);
      if (startTime <= 0) {
        continue;
      }
      if (System.currentTimeMillis() - startTime > fileSaveTime) {
        bean.delete();
      }
    }
  }
}
