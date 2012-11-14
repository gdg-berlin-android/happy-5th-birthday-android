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
import playn.core.Image;
import playn.core.ImageLayer;


/**
 * A colored game figure which each player controls.
 */
class Droid {
  /**
   * Possible colors for each droid.
   *
   * <p>
   * Must match the droids in the sprite.
   * </p>
   */
  enum Color {
    BLUE(0), PURPLE(1), RED(2), YELLOW(3);

    /**
     * Number of the droid in the sprite.
     */
    final int id;

    /**
     * Construct a new {@link Color}.
     *
     * @param id Number of the droid in the sprite
     */
    private Color(final int id) {
      this.id = id;
    }

  }

  /**
   * Color of the droid.
   */
  private final Color color;

  /**
   * Scene node holding the droid.
   */
  private final ImageLayer layer;

  /**
   * Current position of the droid.
   */
  private Field position;

  /**
   * Construct a new {@link Droid}.
   *
   * @param droidSprite Sprite which contains all colored droids.
   * @param color Color of the droid.
   * @param startPosition Where the droid starts.
   */
  Droid(final Image droidSprite, final Color color, final Field startPosition) {
    this.color = color;
    this.layer = graphics().createImageLayer();
    this.position = startPosition;

    float droidWidth = droidSprite.width() / Color.values().length;
    float droidHeight = droidSprite.height();

    layer.setImage(droidSprite.subImage(droidWidth * color.id, 0, droidWidth, droidHeight));
    layer.setOrigin(droidWidth / 2, droidHeight - (droidHeight / 6));
    translateToPosition();
  }

  public void translateToPosition(Field field) {
    position = field;
    translateToPosition();
  }

  private void translateToPosition() {
    layer.setTranslation(position.x(), position.y());
  }

  /**
   * @return Color of the droid.
   */
  Color color() {
    return color;
  }

  /**
   * @return Scene node holding the droid.
   */
  ImageLayer layer() {
    return layer;
  }

  /**
   * @return Current position of the droid.
   */
  Field position() {
    return position;
  }

  /**
   * @param position Current position of the droid.
   */
  void position(final Field position) {
    this.position = position;
  }

}
