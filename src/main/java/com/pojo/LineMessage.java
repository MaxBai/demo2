package com.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "lineMessage")
public class LineMessage {
  @Id
  private String messageId;
  private String userId;
  private String messageText;
  private String createTime;
  
  @DBRef
  private LineUserInfo userInfo;
  
  public LineMessage(String messageId, String userId, String messageText, String createTime) {
    super();
    this.messageId = messageId;
    this.userId = userId;
    this.messageText = messageText;
    this.createTime = createTime;
  }

  public LineMessage() {
    super();
  }

  public String getMessageId() {
    return messageId;
  }

  public void setMessageId(String messageId) {
    this.messageId = messageId;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getMessageText() {
    return messageText;
  }

  public void setMessageText(String messageText) {
    this.messageText = messageText;
  }

  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }

  public LineUserInfo getUserInfo() {
    return userInfo;
  }

  public void setUserInfo(LineUserInfo userInfo) {
    this.userInfo = userInfo;
  }
  
}
