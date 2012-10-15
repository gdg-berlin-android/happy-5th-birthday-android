package devfest.core;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import static playn.core.PlayN.platformType;
import playn.core.Game;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.Key;
import playn.core.Keyboard;
import playn.core.Keyboard.Event;
import playn.core.Platform.Type;
import playn.core.PlayN;
import playn.core.Pointer;

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

    ImageLayer imageLayer;
    int currentField = 0;

    public Point() {

      imageLayer = graphics().createImageLayer(playerImage);
      move();
      graphics().rootLayer().add(imageLayer);
    }

    public void move() {
      imageLayer.setTranslation(fields[currentField].x - playerImage.width() / 2, fields[currentField].y
          - playerImage.height() + 50);
    }

  }

  boolean up = true;
  Image playerImage;

  @Override
  public void init() {
    if (platformType() != Type.JAVA) {
      // Resize to the available resolution on the current device
      graphics().setSize(graphics().screenWidth(), graphics().screenHeight());
    } else {
      // Use 800x600 for the Java backend for now. Good for testing
      graphics().setSize(800, 600);
    }

    Image bgImage = assets().getImage("images/map_2000px.jpg");
    playerImage = assets().getImage("images/player_blue_n.png");

    // Fit to the available screen without stretching
    graphics().rootLayer().setScale(
        Math.min(graphics().width() / bgImage.width(), graphics().height() / bgImage.height()));

    // Add the playing field as background
    ImageLayer bgLayer = graphics().createImageLayer(bgImage);
    graphics().rootLayer().add(bgLayer);

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

    PlayN.pointer().setListener(new Pointer.Adapter() {
      @Override
      public void onPointerEnd(final playn.core.Pointer.Event event) {
        if (up) {
          if (player1.currentField < fields.length - 1) {
            player1.currentField++;
          } else {
            up = false;
          }
        } else {
          if (player1.currentField > 0) {
            player1.currentField--;
          } else {
            up = true;
          }
        }

        player1.move();
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
