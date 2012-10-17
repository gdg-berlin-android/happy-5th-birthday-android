/*
 * Copyright 2012 c-base e.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package devfest.core;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import static playn.core.PlayN.platformType;
import java.util.Random;
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
  boolean up = true;
  Random rnd = new Random();

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
    final Player[] players = createPlayers(bluePlayerImage, purplePlayerImage, redPlayerImage, yellowPlayerImage);

    PlayN.keyboard().setListener(new Keyboard.Adapter() {
        @Override
        public void onKeyDown(final Event event) {
          Player player = null;
          if (event.key() == Key.UP) {
            player = players[0];
          } else if (event.key() == Key.DOWN) {
            player = players[1];
          } else if (event.key() == Key.LEFT) {
            player = players[2];
          } else if (event.key() == Key.RIGHT) {
            player = players[3];
          }
          if (player != null) {
            move(player, rnd.nextInt(6) + 1);
          }
        }
      });

    PlayN.pointer().setListener(new Pointer.Adapter() {
        int playerNumber = 0;

        @Override
        public void onPointerEnd(final playn.core.Pointer.Event event) {
          final Player player = players[playerNumber++];
          move(player, rnd.nextInt(6) + 1);
          if (playerNumber > 3) {
            playerNumber = 0;
          }
        }
      });

  }

  private Player[] createPlayers(Image... images) {
    final Player[] players = new Player[images.length];
    int i = 0;
    for (Image image : images) {
      players[i++] = new Player(image, graphics(), fields[0], i);
    }
    return players;
  }

  void move(Player p, int numberFields) {
    Field field = null;
    for (int i = 1; i <= numberFields; i++) {
      //      if (p.position < (fields.length - 1)) {
      //        p.position++;
      //      } else {
      //        p.position--;
      //      }
      //      field = fields[p.position];
      p.moveTo(field.x, field.y);
      // TODO sleeper for moving
    }
    if ((field != null) && (field.jumpTo != -1)) {
      //      p.position = field.jumpTo;
      field = fields[field.jumpTo];
      p.moveTo(field.x, field.y);
    }
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

  Field[] fields = {
    new Field(100, 1265), new Field(179, 1250), new Field(246, 1234), new Field(305, 1234, 10),
    new Field(365, 1209), new Field(429, 1250), new Field(511, 1262), new Field(580, 1265), new Field(653, 1259),
    new Field(722, 1234), new Field(791, 1206, 3), new Field(880, 1180), new Field(981, 1199), new Field(1059, 1215),
    new Field(1148, 1234, 27), new Field(1239, 1253), new Field(1321, 1256), new Field(1394, 1259),
    new Field(1492, 1269), new Field(1583, 1247), new Field(1640, 1180), new Field(1706, 1117),
    new Field(1697, 1000), new Field(1603, 940, 63), new Field(1528, 900), new Field(1436, 868, 30),
    new Field(1346, 920), new Field(1251, 913, 14), new Field(1147, 900), new Field(1069, 898), new Field(980, 908),
    new Field(902, 965), new Field(820, 955), new Field(568, 775), new Field(503, 720), new Field(456, 691),
    new Field(386, 643, 91), new Field(316, 626), new Field(262, 681), new Field(172, 743), new Field(162, 823),
    new Field(209, 900), new Field(241, 977), new Field(299, 1022), new Field(399, 997, 54), new Field(478, 972),
    new Field(548, 945), new Field(601, 870), new Field(650, 805), new Field(700, 765), new Field(755, 745),
    new Field(815, 720), new Field(870, 671, 89), new Field(952, 663), new Field(1029, 648, 44),
    new Field(1119, 618), new Field(1226, 638), new Field(1279, 666), new Field(1344, 691, 68), new Field(1426, 720),
    new Field(1486, 715), new Field(1548, 703), new Field(1593, 663), new Field(1630, 596, 23), new Field(1663, 526),
    new Field(1683, 441), new Field(1665, 391), new Field(1618, 351), new Field(1543, 306, 58), new Field(1463, 284),
    new Field(1384, 289), new Field(1326, 284), new Field(1269, 281), new Field(1216, 321), new Field(1229, 389),
    new Field(1236, 466), new Field(1204, 533), new Field(1122, 513), new Field(1052, 483), new Field(987, 456),
    new Field(955, 386), new Field(970, 316), new Field(982, 239), new Field(947, 162), new Field(877, 169),
    new Field(815, 189), new Field(758, 222), new Field(713, 269), new Field(658, 316), new Field(615, 379, 52),
    new Field(546, 414), new Field(486, 431, 36), new Field(421, 436), new Field(369, 409), new Field(321, 351),
    new Field(314, 276), new Field(306, 194)
  };
}
