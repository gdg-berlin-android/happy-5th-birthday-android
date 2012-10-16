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
import pythagoras.f.Vector;

public class HappyBirthday implements Game {

  Vector[] fields = {
      new Vector(100, 1265), new Vector(179, 1250), new Vector(246, 1234), new Vector(305, 1234),
      new Vector(365, 1209), new Vector(429, 1250), new Vector(511, 1262), new Vector(580, 1265),
      new Vector(653, 1259), new Vector(722, 1234), new Vector(791, 1206), new Vector(880, 1180)
  };

  static class Player {

    private final Vector[] world;
    private final Image playerImage;
    private final ImageLayer playerLayer;
    private int position = 0;

    public Player(final Vector[] world, final Image playerImage) {
      this.world = world;
      this.playerImage = playerImage;
      this.playerLayer = graphics().createImageLayer(playerImage);
      setPosition(0);
      graphics().rootLayer().add(playerLayer);
    }

    public void move(final int offset) {
      setPosition(position + offset);
    }

    private void setPosition(final int position) {
      this.position = position;
      playerLayer.setTranslation(world[position].x - playerImage.width() / 2,
          world[position].y - playerImage.height() + 50);
    }

  }

  boolean up = true;

  @Override
  public void init() {
    if (platformType() != Type.JAVA) {
      // Resize to the available resolution on the current device
      graphics().setSize(graphics().screenWidth(), graphics().screenHeight());
    } else {
      // Use 800x600 for the Java backend for now. Good for testing
      graphics().setSize(800, 600);
    }

    // Load assets
    final Image bgImage = assets().getImage("images/map_2000px.jpg");
    final Image bluePlayerImage = assets().getImage("images/player_blue_n.png");
    final Image purplePlayerImage = assets().getImage("images/player_purple_n.png");
    final Image redPlayerImage = assets().getImage("images/player_red_n.png");
    final Image yellowPlayerImage = assets().getImage("images/player_yellow_n.png");

    // Fit to the available screen without stretching
    graphics().rootLayer().setScale(
        Math.min(graphics().width() / bgImage.width(), graphics().height() / bgImage.height()));

    // Add the playing field as background
    ImageLayer bgLayer = graphics().createImageLayer(bgImage);
    graphics().rootLayer().add(bgLayer);

    // Initialize players
    final Player bluePlayer = new Player(fields, bluePlayerImage);
    final Player purplePlayer = new Player(fields, purplePlayerImage);
    final Player redPlayer = new Player(fields, redPlayerImage);
    final Player yellowPlayer = new Player(fields, yellowPlayerImage);

    PlayN.keyboard().setListener(new Keyboard.Adapter() {
      @Override
      public void onKeyDown(final Event event) {
        if (event.key() == Key.UP) {
          if (bluePlayer.position < fields.length - 1) {
            bluePlayer.move(1);
          }
        } else if (event.key() == Key.DOWN) {
          if (bluePlayer.position > 0) {
            bluePlayer.move(-1);
          }
        }
      }
    });

    PlayN.pointer().setListener(new Pointer.Adapter() {
      @Override
      public void onPointerEnd(final playn.core.Pointer.Event event) {
        if (up) {
          if (purplePlayer.position < fields.length - 1) {
            purplePlayer.move(1);
          } else {
            up = false;
          }
        } else {
          if (purplePlayer.position > 0) {
            purplePlayer.move(-1);
          } else {
            up = true;
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
