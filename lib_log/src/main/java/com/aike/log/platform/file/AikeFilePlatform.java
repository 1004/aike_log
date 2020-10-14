package com.aike.log.platform.file;

import android.text.TextUtils;
import com.aike.log.config.AikeLogConfig;
import com.aike.log.platform.APrintPlatform;
import com.aike.log.platform.file.backup.AikeFileSizeBackupStrategy;
import com.aike.log.platform.file.backup.IFileBackUpStrategy;
import com.aike.log.platform.file.compress.AikeCompressManager;
import com.aike.log.platform.file.config.FilePlatformConfig;
import com.aike.log.platform.file.delete.AikeFileTimeDeleteStrategy;
import com.aike.log.platform.file.delete.IFileDeleteStrategy;
import com.aike.log.platform.file.utils.AikeDateUtils;
import com.aike.log.platform.file.utils.AikeFileUtils;
import com.aike.log.platform.log.AikeLogLevel;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import me.pqpo.librarylog4a.LogBuffer;

/**
 * 创建时间: 2020/09/25 12:11 <br>
 * 作者: xiekongying <br>
 * 描述: 输出到文件
 */
public class AikeFilePlatform extends APrintPlatform {

  private IFileDeleteStrategy deleteStrategy;
  private IFileBackUpStrategy backupStrategy;
  private String bufferPath;
  private String folderPath;
  private LogBuffer logBuffer;
  private File file;

  public AikeFilePlatform() {
    deleteStrategy = new AikeFileTimeDeleteStrategy(60*1000);
    backupStrategy = new AikeFileSizeBackupStrategy(FilePlatformConfig.ONE_MB*10);
    folderPath = AikeLogConfig.application.getExternalFilesDir("").getAbsolutePath()+FilePlatformConfig.FOLDPATH_SUFFIX;
    bufferPath = folderPath+FilePlatformConfig.BUFFER_SUFFIX;
    AikeFileUtils.createFile(folderPath);
    try {
      logBuffer = new LogBuffer(bufferPath,40*1024,getlastFileName(),false);
    }catch (Throwable throwable){
      throwable.printStackTrace();
    }
    createFile();
    deleteStrategy.deleteFile(folderPath);
  }

  @Override
  public void printLog(int level, String contentType, String tag, String msg) {
    if (logBuffer == null) {
      return;
    }
    if (checkShouldBackup()) {
      changeFile();
    }
    String realTag = getTag(tag);
    String content = parsePrintContent(contentType, msg);
    logBuffer.write(flatten(level, realTag, content).toString());
  }

  //是否可以备份了
  private boolean checkShouldBackup() {
    if (file == null || !file.exists() || backupStrategy.shouldBackup(file)) {
      return true;
    }
    return false;
  }

  public synchronized void changeFile() {
    if (logBuffer == null) {
      return;
    }
    if (file == null || !file.exists()) {
      createFile();
      return;
    }

    if (!checkShouldBackup()) {
      return;
    }

    logBuffer.flushAsync();
    changeFileName();
    logBuffer.changeLogPath(
        folderPath + "/" + generateFileName(System.currentTimeMillis()));
    createFile();
    AikeCompressManager.getInstance().compress(folderPath, null);
  }

  private String generateFileName(long currentTimeMillis) {
    return currentTimeMillis+"";
  }

  private void changeFileName() {
    if (file == null || !file.exists()) {
      return;
    }
    File lastFile = new File(folderPath + "/" + finalFileName(file));
    file.renameTo(lastFile);
  }

  public String finalFileName(File file) {
    return file.getName() + "_" + System.currentTimeMillis();
  }

  public CharSequence flatten(int logLevel, String tag, String message) {
    return AikeDateUtils.getTimeStrBySeconds(System.currentTimeMillis())
        + '|' + AikeLogLevel.getLevelName(logLevel)
        + '|' + tag
        + '|' + message + "\n\n";
  }

  private String getlastFileName() {
    String newFileName = generateFileName(System.currentTimeMillis());
    File file = new File(folderPath);
    if (!file.exists()) {
      file.mkdirs();
      return folderPath + "/" + newFileName;
    }
    File[] files = file.listFiles(new FileFilter() {
      @Override
      public boolean accept(File pathname) {
        if (pathname == null || pathname.getName() == null) {
          return false;
        }
        return !pathname.getName().endsWith(".log") || pathname.getName().length() == 13;
      }
    });
    if (files == null || files.length == 0) {
      return folderPath + "/" + newFileName;
    }
    long startTime = 0;
    File lastFile = null;
    long beanTime;
    for (File bean : files) {
      beanTime = AikeFileUtils.getStartTime(bean);
      startTime = Math.max(startTime, beanTime);
      lastFile = startTime == beanTime ? bean : lastFile;
    }
    if (file == null || !file.exists()) {
      return folderPath + "/" + newFileName;
    }
    return lastFile.getAbsolutePath();
  }

  private void createFile() {
    if (logBuffer == null) {
      return;
    }
    if (!TextUtils.isEmpty(logBuffer.getLogPath())) {
      file = new File(logBuffer.getLogPath());
      if (!file.exists()) {
        try {
          file.createNewFile();
        } catch (IOException e) {
        }
      }
    }
  }

}
