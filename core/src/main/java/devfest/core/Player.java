package devfest.core;

import playn.core.Graphics;
import playn.core.Image;
import playn.core.ImageLayer;


public class Player {
  private final Image playerImage;
  private final ImageLayer playerLayer;
  public final int playerNumber;
  public Field position;
  public Field nextPosition;

  public Player(final Image playerImage, Graphics graphics, Field startField, int playerNumber) {
    this.playerImage = playerImage;
    this.playerNumber = playerNumber;
    this.playerLayer = graphics.createImageLayer(playerImage);
    graphics.rootLayer().add(playerLayer);
    moveTo(startField.x, startField.y);
    position = startField;
  }

  public void moveTo(final float x, final float y) {
    playerLayer.setTranslation(x - (playerImage.width() / 2), y - playerImage.height() + 50);
  }

}
