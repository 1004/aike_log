package com.aike.log.parse.xml;

import android.text.TextUtils;
import com.aike.log.parse.ILogContentParser;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * 创建时间: 2020/03/22 16:48 <br>
 * 作者: xiekongying001 <br>
 * 描述:
 */
public class XmlLogContentParser implements ILogContentParser {
  @Override
  public String parseConent(String msg) {
    String message = "";
    if (!TextUtils.isEmpty(msg)){
      msg = msg.trim();
      message = formatXML(msg);
    }
    return message;
  }

  private String formatXML(String inputXML) {
    try {
      Source xmlInput = new StreamSource(new StringReader(inputXML));
      StreamResult xmlOutput = new StreamResult(new StringWriter());
      Transformer transformer = TransformerFactory.newInstance().newTransformer();
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
      transformer.transform(xmlInput, xmlOutput);
      return xmlOutput.getWriter().toString().replaceFirst(">", ">\n");
    } catch (Exception e) {
      e.printStackTrace();
      return inputXML;
    }
  }
}
