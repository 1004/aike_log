package com.aike.example.log;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.aike.example.aike.log.R;
import com.aike.fjh.log.LogActivity;
import com.aike.log.AikeLog;
import com.aike.log.bean.AikeLogParams;
import com.aike.log.parse.ParseType;
import com.aike.log.platform.PrintPlatformType;

/**
 * 创建时间: 2020/03/24 14:49 <br>
 * 作者: xiekongying001 <br>
 * 描述:
 */
public class MainActivity extends Activity {
  private static String JSON="{\"menu\":[\"泰式柠檬肉片\",\"鸡柳汉堡\",\"蒸桂鱼卷 \"],\"tag\":\"其他\"}";
  private static String JSON_LONG;
  private static final String LOG_MSG = "aike log is 好";
  private static String XML = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><!--  Copyright w3school.com.cn --><note><to>George</to><from>John</from><heading>Reminder</heading><body>Don't forget the meeting!</body></note>";
  private static String TAG = "mainactivity";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    AikeLog.initAikeLog(new AikeLogParams.Builder().isCanPushLog(true).isShowLog(true).globalTag("ikelog").pluginApplication(getApplication()).isDebug(true).pushUrl("http:www.baiduc.com").build());
    JSON_LONG = getResources().getString(R.string.json_long);
  }

  public void commonLog(View view){
    Intent intent = new Intent(this, LogActivity.class);
    startActivity(intent);
    //AikeLog.printLog(AikeLog.LEVEL_E, PrintPlatformType.LOGTYPE, ParseType.STRINGTYPE,TAG,LOG_MSG);
  }

  public void commonJson(View view){
    AikeLog.printLog(AikeLog.LEVEL_E, PrintPlatformType.LOGTYPE, ParseType.JSONTYPE,TAG,JSON);
  }

  public void commonLongJson(View view){
    AikeLog.printLog(AikeLog.LEVEL_E, PrintPlatformType.LOGTYPE, ParseType.JSONTYPE,TAG,JSON_LONG);
  }

  public void commonXml(View view){
    AikeLog.printLog(AikeLog.LEVEL_E, PrintPlatformType.LOGTYPE, ParseType.XMLTYPE,TAG,XML);
  }

  public void serverLog(View view){
    AikeLog.printLog(AikeLog.LEVEL_E, PrintPlatformType.SERVERTYPE, ParseType.STRINGTYPE,TAG,LOG_MSG);
  }

  public void serverJson(View view){
    AikeLog.printLog(AikeLog.LEVEL_E, PrintPlatformType.SERVERTYPE, ParseType.JSONTYPE,TAG,JSON);
  }


  public void serverXml(View view){
    AikeLog.printLog(AikeLog.LEVEL_E, PrintPlatformType.SERVERTYPE, ParseType.XMLTYPE,TAG,XML);
  }

  public void threableServerTest(View view){
    try {
      int i=10/0;
    }catch (Throwable throwable){
      AikeLog.printThreowableLog(AikeLog.LEVEL_E, PrintPlatformType.SERVERTYPE,TAG,"这个位置出错了",throwable);
    }
  }

  public void CommonServerTest(View view){
    try {
      String test = null;
      String i=test.toString();
    }catch (Throwable throwable){
      AikeLog.printThreowableLog(AikeLog.LEVEL_E, PrintPlatformType.LOGTYPE,TAG,"这个位置出错了",throwable);
    }
  }

  public void FileLog(View view) {
    AikeLog.printLog(AikeLog.LEVEL_I, PrintPlatformType.FILETYPE, ParseType.STRINGTYPE,TAG,LOG_MSG);
  }

  public void FileJson(View view) {
    AikeLog.printLog(AikeLog.LEVEL_I, PrintPlatformType.FILETYPE, ParseType.JSONTYPE,TAG,JSON);
  }

  public void FileXml(View view) {
    AikeLog.printLog(AikeLog.LEVEL_I, PrintPlatformType.FILETYPE, ParseType.XMLTYPE,TAG,XML);
  }

  public void threableFileTest(View view) {
    try {
      String test = null;
      String i=test.toString();
    }catch (Throwable throwable){
      AikeLog.printThreowableLog(AikeLog.LEVEL_E, PrintPlatformType.FILETYPE,TAG,"这个位置出错了",throwable);
    }
  }
}
