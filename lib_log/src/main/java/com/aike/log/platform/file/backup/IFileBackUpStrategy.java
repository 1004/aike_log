package com.aike.log.platform.file.backup;

import java.io.File;

/**
 * 创建时间: 2020/10/14 16:17 <br>
 * 作者: xiekongying <br>
 * 描述: 备份策略
 */
public interface IFileBackUpStrategy {
  boolean shouldBackup(File file);
}
