package com.aike.log.parse.json;

import android.text.TextUtils;
import com.aike.log.parse.ILogContentParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 创建时间: 2020/03/22 16:47 <br>
 * 作者: xiekongying001 <br>
 * 描述:
 */
public final class JsonLogContentParser implements ILogContentParser {
  @Override
  public String parseConent(String msg) {
    String message ="";
    if (!TextUtils.isEmpty(msg)) {
      msg = msg.trim();
      try {
        if (msg.startsWith("{")){
          JSONObject jsonObject = new JSONObject(msg);
          message = jsonObject.toString();
        }else if (msg.startsWith("[")){
          JSONArray jsonArray = new JSONArray(msg);
          message = jsonArray.toString();
        }
      } catch (JSONException e) {
        message = msg;
      }
    }
    return message;
  }
}
