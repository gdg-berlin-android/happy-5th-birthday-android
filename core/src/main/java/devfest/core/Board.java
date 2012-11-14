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

import static playn.core.Layer.Util.screenToLayer;
import static playn.core.PlayN.graphics;
import java.util.HashMap;
import java.util.Map;
import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ImageLayer;
import pythagoras.f.Point;
import tripleplay.anim.Animator;
import devfest.core.Droid.Color;


/**
 * The playing board where epic battles are fought.
 */
class Board {
  /**
   * Callback which gets executed when a turn/animation is over.
   */
  interface TurnListener {
    /**
     * The turn/animation is over.
     */
    void onTurnOver();

  }

  /**
   * All fields of the board.
   *
   * TODO: Move this out of the code into an asset, e.g. json.
   */
  private static final Field[] FIELDS = {
    new Field(100, 1265), new Field(179, 1250), new Field(246, 1234), new Field(305, 1234, 10),
    new Field(365, 1209), new Field(429, 1250), new Field(511, 1262), new Field(580, 1265),
    new Field(653, 1259), new Field(722, 1234), new Field(791, 1206, 3), new Field(880, 1180),
    new Field(981, 1199), new Field(1059, 1215), new Field(1148, 1234, 27), new Field(1239, 1253),
    new Field(1321, 1256), new Field(1394, 1259), new Field(1492, 1269), new Field(1583, 1247),
    new Field(1640, 1180), new Field(1706, 1117), new Field(1697, 1000), new Field(1603, 940, 63),
    new Field(1528, 900), new Field(1436, 868, 30), new Field(1346, 920), new Field(1251, 913, 14),
    new Field(1147, 900), new Field(1069, 898), new Field(980, 908), new Field(902, 965),
    new Field(820, 955), new Field(568, 775), new Field(503, 720), new Field(456, 691),
    new Field(386, 643, 91), new Field(316, 626), new Field(262, 681), new Field(172, 743),
    new Field(162, 823), new Field(209, 900), new Field(241, 977), new Field(299, 1022),
    new Field(399, 997, 54), new Field(478, 972), new Field(548, 945), new Field(601, 870),
    new Field(650, 805), new Field(700, 765), new Field(755, 745), new Field(815, 720),
    new Field(870, 671, 89), new Field(952, 663), new Field(1029, 648, 44), new Field(1119, 618),
    new Field(1226, 638), new Field(1279, 666), new Field(1344, 691, 68), new Field(1426, 720),
    new Field(1486, 715), new Field(1548, 703), new Field(1593, 663), new Field(1630, 596, 23),
    new Field(1663, 526), new Field(1683, 441), new Field(1665, 391), new Field(1618, 351),
    new Field(1543, 306, 58), new Field(1463, 284), new Field(1384, 289), new Field(1326, 284),
    new Field(1269, 281), new Field(1216, 321), new Field(1229, 389), new Field(1236, 466),
    new Field(1204, 533), new Field(1122, 513), new Field(1052, 483), new Field(987, 456),
    new Field(955, 386), new Field(970, 316), new Field(982, 239), new Field(947, 162),
    new Field(877, 169), new Field(815, 189), new Field(758, 222), new Field(713, 269),
    new Field(658, 316), new Field(615, 379, 52), new Field(546, 414), new Field(486, 431, 36),
    new Field(421, 436), new Field(369, 409), new Field(321, 351), new Field(314, 276), new Field(306, 194)
  };

  /**
   * The update rate the game targets.
   *
   * <p>
   * Necessary for smooth animations.
   * <p>
   */
  private final int updateRate;

  /**
   * Moves the droids around.
   */
  private final Animator animator;

  /**
   * Image of the board.
   */
  private final Image boardImage;

  /**
   * Scene node of the board.
   */
  private final GroupLayer layer;

  /**
   * Scene node of the board image.
   */
  private final ImageLayer imageLayer;

  /**
   * All droids.
   */
  private final Map<Droid.Color, Droid> droids;

  /**
   * Milliseconds passed in the game.
   */
  protected float elapsed;

  /**
   * Construct a new {@link Board}.
   *
   * @param updateRate The update rate the game targets.
   * @param boardImage Image of the board.
   * @param droidSprite Sprite which contains all colored droids.
   */
  Board(final int updateRate, final Image boardImage, final Image droidSprite) {
    this.updateRate = updateRate;
    this.animator = Animator.create();
    this.boardImage = boardImage;
    this.layer = graphics().createGroupLayer();
    this.imageLayer = graphics().createImageLayer(boardImage);
    this.droids = new HashMap<Droid.Color, Droid>();
    this.elapsed = 0;

    graphics().rootLayer().add(layer);
    layer.add(imageLayer);
    layer.setScale(graphics().width() / boardImage.width() * maxScale());
    layer.setTranslation(left(), bottom());

    for (Droid.Color color : Droid.Color.values()) {
      Droid droid = new Droid(droidSprite, color, FIELDS[0]);
      droids.put(color, droid);
      droid.layer().setVisible(false);
      layer.add(droid.layer());
    }
    // this.currentDroid = droids.get(Droid.Color.BLUE);
  }

  /**
   * @return Scene node of the board.
   */
  GroupLayer layer() {
    return layer;
  }

  /**
   * @return Scene node of the board image.
   */
  ImageLayer imageLayer() {
    return imageLayer;
  }

  /**
   * @return The top border.
   */
  float top() {
    return 0;
  }

  /**
   * @return The bottom border.
   */
  float bottom() {
    return -1 * ((boardImage.height() * layer.transform().scaleY()) - graphics().height());
  }

  /**
   * @return The left border.
   */
  float left() {
    return 0;
  }

  /**
   * @return The right border.
   */
  float right() {
    return -1 * ((boardImage.width() * layer.transform().scaleX()) - graphics().width());
  }

  /**
   * @return The minimum allowed scale of the board.
   */
  float minScale() {
    return graphics().width() / boardImage.width();
  }

  /**
   * @return The maximum allowed scale of the board.
   */
  float maxScale() {
    return 1.5f;
  }

  /**
   * Translate the board layer to the given coordinates.
   *
   * <p>
   * The coordinates are capped by {@link #top()}, {@link #right()},
   * {@link #bottom()} and {@link #left()}.
   * </p>
   */
  void translate(final float x, final float y) {
    float cappedX = x;
    float cappedY = y;

    if (cappedX > left()) {
      cappedX = left();
    } else if (cappedX < right()) {
      cappedX = right();
    }

    if (cappedY > top()) {
      cappedY = top();
    } else if (cappedY < bottom()) {
      cappedY = bottom();
    }

    layer.setTranslation(cappedX, cappedY);
  }

  /**
   * Scale the board layer around the given origin.
   *
   * <p>
   * The scale is capped to {@link #minScale()} and {@link #maxScale()}.
   * </p>
   */
  void scale(final float screenOriginX, final float screenOriginY, final float scale) {
    Point worldOrigin = screenToLayer(layer, screenOriginX, screenOriginY);
    float cappedScaleX = layer.transform().scaleX() * scale;
    float cappedScaleY = layer.transform().scaleY() * scale;

    assert cappedScaleX == cappedScaleY;

    if (cappedScaleX < minScale()) {
      cappedScaleX = minScale();
      cappedScaleY = minScale();
    } else if (cappedScaleX > maxScale()) {
      cappedScaleX = maxScale();
      cappedScaleY = maxScale();
    }

    layer.setScale(cappedScaleX, cappedScaleY);
    translate(-1 * ((worldOrigin.x * cappedScaleX) - screenOriginX),
      -1 *
      ((worldOrigin.y * cappedScaleY) - screenOriginY));
  }

  /**
   * Update the animation timer.
   */
  void update(final float delta) {
    elapsed += delta;
  }

  /**
   * Update/draw animation.
   */
  public void paint(final float alpha) {
    animator.update((elapsed + (alpha * updateRate)) / 1000);
  }

  /**
   * Initialize the droid related to the given color.
   *
   * <p>
   * The droid will be made visible and positioned at the start.
   * </p>
   */
  void initDroid(final Droid.Color color) {
    Droid droid = droids.get(color);
    droid.position(FIELDS[0]);
    droid.layer().setVisible(true);
  }

  /**
   * Execute a turn on the current droid with the given value.
   *
   * @param value The number of fields the droid moves.
   * @param gameplayListener Callback which gets executed when a turn is done.
   */
  void moveDroid(final Droid.Color color, final int value, final TurnListener gameplayListener) {
    final Droid droid = droids.get(color);
    Animator animation = animator.delay(0.5f).then();

    // Build the animation for the fields.
    for (int i = value; i != 0; i--) {
      droid.position(FIELDS[droid.position().id() + 1]);
      animation = animation.tweenTranslation(droid.layer()).in(0.5f).to(FIELDS[droid.position().id()]).easeInOut()
        .then();
    }

    // Add the shortcut/trap if needed
    if (droid.position().link() != -1) {
      droid.position(FIELDS[droid.position().link()]);
      animation = animation.tweenTranslation(droid.layer()).in(0.5f).to(FIELDS[droid.position().id()]).easeInOut()
        .then();
    }

    // Execute the callback
    animation.delay(0.5f).then().action(new Runnable() {
        @Override
        public void run() {
          gameplayListener.onTurnOver();
        }
      });
  }

  public void clear() {
    for (Color color : Color.values()) {
      Droid droid = droids.get(color);
      droid.translateToPosition(FIELDS[0]);
      droid.layer().setVisible(false);
    }
  }

}
