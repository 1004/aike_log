package com.aike.log.platform.file.backup;

import java.io.File;

/**
 * 创建时间: 2020/10/14 16:22 <br>
 * 作者: xiekongying <br>
 * 描述: 根据文件大小进行备份
 */
public class AikeFileSizeBackupStrategy implements IFileBackUpStrategy{
  private long maxSize;

  public AikeFileSizeBackupStrategy(long maxSize) {
    this.maxSize = maxSize;
  }

  @Override
  public boolean shouldBackup(File file) {
    if (file == null || !file.exists()){
      return false;
    }
    return file.length()>maxSize;
  }
}
