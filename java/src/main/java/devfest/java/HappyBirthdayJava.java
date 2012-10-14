package devfest.java;

import playn.core.PlayN;
import playn.java.JavaPlatform;

import devfest.core.HappyBirthday;

public class HappyBirthdayJava {

  public static void main(String[] args) {
    JavaPlatform platform = JavaPlatform.register();
    platform.assets().setPathPrefix("devfest/resources");
    PlayN.run(new HappyBirthday());
  }
}
