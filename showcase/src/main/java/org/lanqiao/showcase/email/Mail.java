package org.lanqiao.showcase.email;

import java.io.Serializable;

public class Mail implements Serializable{
  private String from;
  private String to;
  private String title;
  private String content;

  public Mail() {
  }

  public Mail(String from, String to, String title, String content) {
    this.from = from;
    this.to = to;
    this.title = title;
    this.content = content;
  }

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  @Override
  public String toString() {
    return "Mail{" +
        "from='" + from + '\'' +
        ", to='" + to + '\'' +
        ", title='" + title + '\'' +
        ", content='" + content + '\'' +
        '}';
  }
}
