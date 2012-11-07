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

import static playn.core.PlayN.assets;
import playn.core.AssetWatcher;
import playn.core.Image;

/**
 * Encapsulates all assets that we need.
 */
class Assets {

  /**
   * {@link AssetWatcher.Listener} which implements {@link #error(Throwable)}
   * for simpler usage.
   */
  abstract static class Adapter implements AssetWatcher.Listener {
    @Override
    public void error(final Throwable e) {
      throw new RuntimeException("Error loading asset", e);
    }
  }

  /**
   * Watches the assets if thier're ready.
   */
  private final AssetWatcher watcher;

  /**
   * Image of the playing board.
   */
  private final Image boardImage;

  /**
   * Sprite of all colored droids.
   */
  private final Image droidSprite;

  /**
   * Sprite of all colored dices and their value.
   */
  private final Image diceSprite;

  /**
   * Sprite with the enclosing visual rings of the dice.
   */
  private final Image diceRingSprite;

  /**
   * Construct a new {@link Assets}.
   * 
   * @param assetlistener Listener which gets executed when loading is done.
   */
  Assets(final AssetWatcher.Listener assetlistener) {
    this.watcher = new AssetWatcher(assetlistener);
    this.boardImage = assets().getImage("images/board.jpg");
    this.droidSprite = assets().getImage("images/droid.png");
    this.diceSprite = assets().getImage("images/dice.png");
    this.diceRingSprite = assets().getImage("images/dice_ring.png");

    watcher.add(boardImage);
    watcher.add(droidSprite);
    watcher.add(diceSprite);
    watcher.add(diceRingSprite);
  }

  void load() {
    watcher.start();
  }

  /**
   * @return If all assets are loaded.
   */
  boolean loaded() {
    return watcher.isDone();
  }

  /**
   * @return Image of the playing board.
   */
  public Image boardImage() {
    return boardImage;
  }

  /**
   * @return Sprite of all colored droids.
   */
  public Image droidSprite() {
    return droidSprite;
  }

  /**
   * @return Sprite of all colored dices and their value.
   */
  public Image diceSprite() {
    return diceSprite;
  }

  /**
   * @return Sprite with the enclosing visual rings of the dice.
   */
  public Image diceRingSprite() {
    return diceRingSprite;
  }

}
