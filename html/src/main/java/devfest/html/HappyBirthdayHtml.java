package devfest.html;

import playn.core.PlayN;
import playn.html.HtmlGame;
import playn.html.HtmlPlatform;

import devfest.core.HappyBirthday;

public class HappyBirthdayHtml extends HtmlGame {

  @Override
  public void start() {
    HtmlPlatform platform = HtmlPlatform.register();
    platform.assets().setPathPrefix("happybirthday/");
    PlayN.run(new HappyBirthday());
  }
}
