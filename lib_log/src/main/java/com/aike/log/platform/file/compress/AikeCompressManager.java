package com.aike.log.platform.file.compress;

import android.text.TextUtils;
import com.aike.log.platform.file.utils.AikeFileUtils;
import com.github.luben.zstd.ZstdOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 创建时间: 2020/03/22 16:45 <br>
 * 作者: xiekongying001 <br>
 * 描述:
 */
public class AikeCompressManager {
  public static final int COMPRESS_BYTE = 100 * 1024;
  private static final int MAX_CAPACITY = 5;
  private static AikeCompressManager compressManager;
  private ThreadPoolExecutor threadPoolExecutor =
      new ThreadPoolExecutor(1, 1, 1, TimeUnit.MILLISECONDS,
          new LinkedBlockingQueue<Runnable>(MAX_CAPACITY), new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

        }
      });

  public static AikeCompressManager getInstance() {
    if (compressManager == null) {
      compressManager = new AikeCompressManager();
    }
    return compressManager;
  }

  private AikeCompressManager() {
  }

  public void compress(final String path, final CompressResultCallBack resultCallBack) {
    threadPoolExecutor.execute(new Runnable() {
      @Override
      public void run() {
        if (TextUtils.isEmpty(path)) {
          return;
        }
        File file = new File(path);
        if (file == null || !file.exists()) {
          return;
        }
        File[] files = file.listFiles(new FileFilter() {
          @Override
          public boolean accept(File pathname) {
            if (pathname == null
                || !pathname.exists()
                || AikeFileUtils.getStartTime(pathname) <= 0
                || AikeFileUtils.getEndTime(pathname) < AikeFileUtils.getStartTime(pathname)
                || pathname.getName().endsWith(".log")) {
              return false;
            } else {
              return true;
            }
          }
        });
        if (files == null) {
          return;
        }
        for (File bean : files) {
          boolean hasCompress = compress(bean, path);
          if (hasCompress && bean != null && bean.exists()) {
            bean.delete();
          }
        }
        boolean hasCompress = true;
        for (File bean : files) {
          if (bean != null && bean.exists()) {
            hasCompress = false;
          }
        }
        if (resultCallBack != null) {
          resultCallBack.compressResult(hasCompress);
        }
      }
    });
  }

  //compress  压缩
  public boolean compress(File file, String path) {
    if (file == null || !file.exists()) {
      return false;
    }
    ZstdOutputStream zstdOutputStream = null;
    File file1 = null;
    FileInputStream fileInputStream = null;
    try {
      fileInputStream = new FileInputStream(file);
      int byteLengs = getAvailMemory(COMPRESS_BYTE);
      if (byteLengs == 0) {
        return false;
      }
      byte[] buf = new byte[byteLengs];
      int length = 0;
      file1 = new File(path, file.getName() + ".log");
      try {
        zstdOutputStream = new ZstdOutputStream(
            new FileOutputStream(file1));
      } catch (Throwable error) {
      }
      if (zstdOutputStream == null) {
        return false;
      }
      while ((length = fileInputStream.read(buf)) != -1) {
        zstdOutputStream.write(buf, 0, length);
        zstdOutputStream.flush();
      }
    } catch (IOException e) {
      if (file1 != null && file1.exists()) {
        file1.delete();
      }
      return false;
    } finally {
      try {
        if (fileInputStream != null) {
          fileInputStream.close();
        }
        if (zstdOutputStream != null) {
          zstdOutputStream.close();
        }
      } catch (IOException e) {
        if (file1 != null && file1.exists()) {
          file1.delete();
        }
        return false;
      }
    }
    return true;
  }

  private int getAvailMemory(int compressByte) {
    long byteLeng = AikeFileUtils.getAvailMemory();
    if (byteLeng == 0 || byteLeng < 1024) {
      return 0;
    }
    if (compressByte < byteLeng) {
      return compressByte;
    }
    if (compressByte / 10 < byteLeng) {
      return compressByte / 10;
    }
    return 0;
  }

  public static interface CompressResultCallBack {
    public void compressResult(boolean result);
  }
}
