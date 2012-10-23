package devfest.core;

import static playn.core.PlayN.graphics;
import playn.core.Image;
import playn.core.SurfaceLayer;

public class Dice {

  private final Image diceSprite;

  private final float diceWidth;

  private final float diceHeight;

  private final SurfaceLayer layer;

  public Dice(final Image diceSprite) {
    this.diceSprite = diceSprite;
    this.diceWidth = diceSprite.width() / 6;
    this.diceHeight = diceSprite.height() / 2;
    this.layer = graphics().createSurfaceLayer(diceWidth, diceHeight);

    graphics().rootLayer().add(layer);
  }

  public void paint(int colorNumber, int diceNumber) {
    assert (colorNumber >= 1 && colorNumber <= 4) : "'" + colorNumber + "'is not a valid color.";
    assert (diceNumber >= 1 && diceNumber <= 6) : "'" + diceNumber + "'is not valid for a dice.";

    layer.surface().clear();
    layer.surface().drawImage(diceSprite, 0, 0, diceWidth, diceHeight, diceWidth * (colorNumber - 1), 0,
        diceWidth, diceHeight);
    layer.surface().drawImage(diceSprite, 0, 0, diceWidth, diceHeight, diceWidth * (diceNumber - 1),
        diceHeight, diceWidth, diceHeight);
  }

}
