package devfest.core;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import playn.core.Game;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.Key;
import playn.core.Keyboard;
import playn.core.Keyboard.Event;
import playn.core.PlayN;

public class HappyBirthday implements Game {

  class Feld {
    final int x;
    final int y;

    public Feld(final int x, final int y) {
      this.x = x;
      this.y = y;
    }
  }

  Feld[] fields = {
      new Feld(100, 1265), new Feld(179, 1250), new Feld(246, 1234), new Feld(305, 1234),
      new Feld(365, 1209), new Feld(429, 1250), new Feld(511, 1262)
  };

  class Point {

    Image image;
    ImageLayer imageLayer;
    int currentField = 0;

    public Point() {
      Image image = assets().getImage("images/player_blue_n.png");
      imageLayer = graphics().createImageLayer(image);
      imageLayer.setScale(0.3f);
      move();
      graphics().rootLayer().add(imageLayer);
    }

    public void move() {
      imageLayer.setTranslation(fields[currentField].x * factorWidth, fields[currentField].y * factorHeight);
    }

  }

  float factorWidth;
  float factorHeight;

  @Override
  public void init() {
    // graphics().setSize(graphics().screenWidth(), graphics().screenHeight());
    graphics().setSize(800, 600);

    Image bgImage = assets().getImage("images/map_2000px.jpg");
    float imageWidth = bgImage.width();
    float imageHeight = bgImage.height();

    PlayN.log().info("iw" + imageWidth);
    PlayN.log().info("ih" + imageHeight);

    float screenWidth = graphics().width();
    float screenHeight = graphics().height();

    PlayN.log().info("sw" + screenWidth);
    PlayN.log().info("sh" + screenHeight);

    factorWidth = screenWidth / imageWidth;
    factorHeight = screenHeight / imageHeight;

    PlayN.log().info("fx" + factorWidth);
    PlayN.log().info("fy" + factorHeight);

    ImageLayer bgLayer = graphics().createImageLayer(bgImage);
    bgLayer.setScale(factorWidth, factorHeight);
    graphics().rootLayer().add(bgLayer);

    // PlayN.pointer().setListener(new Pointer.Adapter() {
    // @Override
    // public void onPointerEnd(final Pointer.Event event) {
    // Point point = new Point(event.x(), event.y());
    // }
    // });

    // for (Feld feld : fields) {
    // Point point = new Point(feld.x, feld.y);
    // }

    final Point player1 = new Point();

    PlayN.keyboard().setListener(new Keyboard.Adapter() {
      @Override
      public void onKeyDown(final Event event) {
        if (event.key() == Key.UP) {
          if (player1.currentField < fields.length - 1) {
            player1.currentField++;
            player1.move();
          }
        } else if (event.key() == Key.DOWN) {
          if (player1.currentField > 0) {
            player1.currentField--;
            player1.move();
          }

        }
      }
    });

  }

  @Override
  public void paint(final float alpha) {
  }

  @Override
  public void update(final float delta) {
  }

  @Override
  public int updateRate() {
    return 25;
  }

}
