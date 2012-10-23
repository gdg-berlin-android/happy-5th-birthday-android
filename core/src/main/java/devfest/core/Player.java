package devfest.core;

import playn.core.Graphics;
import playn.core.Image;
import playn.core.SurfaceLayer;

public class Player {
  private final SurfaceLayer playerLayer;
  public final int playerNumber;
  public Field position;
  public Field nextPosition;
  final float playerWidth;
  final float playerHeight;

  public Player(final Image playerImage, Graphics graphics, Field startField, int playerNumber, int numPlayers) {
    playerWidth = playerImage.width() / numPlayers;
    playerHeight = playerImage.height();

    this.playerNumber = playerNumber;
    this.playerLayer = graphics.createSurfaceLayer(playerImage.width() / numPlayers, playerImage.height());
    graphics.rootLayer().add(playerLayer);
    this.playerLayer.surface().drawImage(playerImage, 0, 0, playerWidth, playerHeight,
        playerWidth * playerNumber, 0, playerWidth, playerHeight);
    
    System.out.println(playerWidth * playerNumber);
    
    moveTo(startField.x, startField.y);
    position = startField;
  }

  public void moveDelta(final float percentDelta) {
    float x = nextPosition.x - position.x;
    float y = nextPosition.y - position.y;
    moveTo((x * percentDelta) + position.x, (y * percentDelta) + position.y);
  }

  void moveTo(final float x, final float y) {
    playerLayer.setTranslation(x - (playerWidth / 2), y - playerHeight + 50);
  }

}
