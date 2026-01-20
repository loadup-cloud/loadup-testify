package com.github.loadup.testify.data.engine.helper;

import java.util.Random;
import java.util.UUID;

/** Custom functions accessible via ${fn.XXX()} */
public class FunctionHelper {
  public String uuid() {
    return UUID.randomUUID().toString();
  }

  public int random(int min, int max) {
    return (int) (Math.random() * (max - min) + min);
  }

  public String randomString(int length) {
    String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    StringBuilder sb = new StringBuilder(length);
    Random random = new Random();
    for (int i = 0; i < length; i++) {
      sb.append(chars.charAt(random.nextInt(chars.length())));
    }
    return sb.toString();
  }
}
