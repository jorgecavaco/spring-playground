package com.example.demo;

public class Greeting {

  private final long id;
  private final String content;

  public Greeting(long id, String content) {
    this.id = id;
    this.content = content;
  }

  public long getId() {
    return id;
  }

  public String getContent() {
    return content;
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("Greeting{");
    sb.append("id=").append(id);
    sb.append(", content='").append(content).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
