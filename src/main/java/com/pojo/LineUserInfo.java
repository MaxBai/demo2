package com.pojo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "lineUserInfo")
public class LineUserInfo {
  @Id
  private String userId;
  private String displayName;
  private String pictureUrl;
  private String statusMessage;
  private String language;
  
  @DBRef
  private List<LineMessage> messages;
  
  public LineUserInfo() {
    super();
  }
  
  public LineUserInfo(String userId, String displayName, String pictureUrl, String statusMessage, String language) {
    super();
    this.userId = userId;
    this.displayName = displayName;
    this.pictureUrl = pictureUrl;
    this.statusMessage = statusMessage;
    this.language = language;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public String getPictureUrl() {
    return pictureUrl;
  }

  public void setPictureUrl(String pictureUrl) {
    this.pictureUrl = pictureUrl;
  }

  public String getStatusMessage() {
    return statusMessage;
  }

  public void setStatusMessage(String statusMessage) {
    this.statusMessage = statusMessage;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public List<LineMessage> getMessages() {
    return messages;
  }

  public void setMessages(List<LineMessage> messages) {
    this.messages = messages;
  }
  
}
