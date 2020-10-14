package com.aike.log.platform.file.utils;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import com.aike.log.config.AikeLogConfig;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Description
 * @Author zhidachang
 * @Time 2020/7/9 2:41 PM
 */
public class AikeFileUtils {
  public static long getStartTime(File file) {
    if (file == null || !file.exists() || file.getName().length() < 13) {
      return 0;
    }
    long time;
    try {
      time = Long.parseLong(file.getName().substring(0, 13));
    } catch (NumberFormatException e) {
      return 0;
    }

    return time;
  }

  public static long getEndTime(File file) {
    if (file == null || !file.exists() || file.getName().length() < 27) {
      return 0;
    }
    long time;
    try {
      time = Long.parseLong(file.getName().substring(14, 27));
    } catch (NumberFormatException e) {
      return 0;
    }
    return time;
  }

  public static long getAvailMemory() {
    if (AikeLogConfig.application == null) {
      return 0;
    }
    ActivityManager am = (ActivityManager) AikeLogConfig.application.getSystemService(Context.ACTIVITY_SERVICE);
    ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
    am.getMemoryInfo(mi);
    // mi.availMem; 当前系统的可用内存
    return mi.availMem;// 将获取的内存大小规格化
  }

  public static List<File> getSortListFile(File file) {
    List<File> files = new ArrayList<>();
    if (file == null || !file.exists() || !file.isDirectory()) {
      return files;
    }
    for (File bean : file.listFiles(new FileFilter() {
      @Override
      public boolean accept(File pathname) {
        if (pathname == null || !pathname.exists()) {
          return false;
        }
        return pathname.getName().length() >= 13;
      }
    })) {
      if (bean == null || !bean.exists()) {
        continue;
      }
      files.add(bean);
    }
    if (files.size() <= 1) {
      return files;
    }
    Collections.sort(files, new Comparator<File>() {
      @Override
      public int compare(File o1, File o2) {
        return getStartTime(o1) - getStartTime(o2) > 0 ? 1 : -1;
      }
    });
    return files;
  }

  public static void clearFiles(String folderPath) {
    if (folderPath == null) {
      return;
    }
    File file = new File(folderPath);
    if (file == null || !file.exists()) {
      return;
    }
    File[] files = file.listFiles();
    if (files == null || files.length == 0) {
      return;
    }
    for (File item : files) {
      if (item == null || !item.exists()) {
        continue;
      }
      if (item.getName().length() == 13 || item.getName().endsWith(".log")) {
        item.delete();
      }
    }
  }

  public static void createFile(String folderPath) {
    if (TextUtils.isEmpty(folderPath)) {
      return;
    }
    File file = new File(folderPath);
    if (!file.exists()) {
      file.mkdirs();
    }
  }
}
