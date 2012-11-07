/*
 * Copyright 2012 c-base e.V.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy
 * of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package devfest.core;

import static playn.core.PlayN.graphics;
import static playn.core.PlayN.random;
import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.Pointer;
import playn.core.Pointer.Event;

/**
 * Abstractation of the dice.
 */
class Dice {

  interface DiceListener {

    void onDiced(int value);

  }

  /**
   * Ring sprite.
   * 
   * 2 rings in one row.
   */
  private final Image ringSprite;

  /**
   * Width of a single ring.
   */
  private final float ringWidth;

  /**
   * Height of a single ring.
   */
  private final float ringHeight;

  /**
   * Scene node which contains the ring.
   */
  private final ImageLayer ringLayer;

  /**
   * Sprite dice.
   * 
   * The sprite has two rows. The first contains the colored dices and the
   * second row the values.
   */
  private final Image diceSprite;

  /**
   * Width of a single dice.
   */
  private final float diceWidth;

  /**
   * Height of a single dice.
   */
  private final float diceHeight;

  /**
   * Scene node which contains the colored dice without any value.
   */
  private final ImageLayer colorLayer;

  /**
   * Scene node which contains the value of the dice.
   */
  private final ImageLayer valueLayer;

  /**
   * Indicates if the dice is enabled and can be thrown.
   */
  private boolean enabled;

  /**
   * Construct a new {@link Dice}.
   */
  Dice(final Image ringSprite, final Image diceSprite) {
    this.ringSprite = ringSprite;
    this.ringWidth = ringSprite.width() / 2;
    this.ringHeight = ringSprite.height();
    this.ringLayer = graphics().createImageLayer();
    this.diceSprite = diceSprite;
    this.diceWidth = diceSprite.width() / 6;
    this.diceHeight = diceSprite.height() / 2;
    this.colorLayer = graphics().createImageLayer();
    this.valueLayer = graphics().createImageLayer();

    GroupLayer layer = graphics().createGroupLayer();
    layer.setScale(0.5f);
    layer.setTranslation(graphics().width() - graphics().width() / 4, graphics().height()
        - graphics().height() / 2);
    graphics().rootLayer().add(layer);

    layer.add(ringLayer);

    GroupLayer diceLayer = graphics().createGroupLayer();
    // TODO: Fix the -7
    diceLayer.setTranslation((ringWidth - diceWidth) / 2 - 7, (ringHeight - diceHeight) / 2);
    diceLayer.add(colorLayer);
    diceLayer.add(valueLayer);
    layer.add(diceLayer);

    // Set initial values
    enabled(true);
    color(Droid.Color.BLUE);
    randomValue();
  }

  /**
   * Add the given {@link Pointer.Listener} to the dice.
   */
  void addListener(final DiceListener diceListener) {
    colorLayer.addListener(new Pointer.Adapter() {
      @Override
      public void onPointerEnd(final Event event) {
        if (enabled) {
          diceListener.onDiced(randomValue());
        }
      }
    });
  }

  /**
   * Set the enabled state of the dice.
   */
  void enabled(final boolean enabled) {
    this.enabled = enabled;
    ringLayer.setImage(ringSprite.subImage(enabled ? ringWidth : 0, 0, ringWidth, ringHeight));
  }

  /**
   * Set the color of the dice.
   * 
   * @param color Dice color to display.
   */
  void color(final Droid.Color color) {
    colorLayer.setImage(diceSprite.subImage(diceWidth * color.id, 0, diceWidth, diceHeight));
  }

  /**
   * Set a random dice value between inclusive 1 and 6.
   */
  private int randomValue() {
    int value = (int) (random() * (6 - 1) + 1);
    valueLayer.setImage(diceSprite.subImage(diceWidth * (value - 1), diceHeight, diceWidth, diceHeight));
    return value;
  }

}
