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
  Field[] fields = {
    new Field(100, 1265), new Field(179, 1250), new Field(246, 1234), new Field(305, 1234, 10),
    new Field(365, 1209), new Field(429, 1250), new Field(511, 1262), new Field(580, 1265),
    new Field(653, 1259), new Field(722, 1234), new Field(791, 1206, 3), new Field(880, 1180)
  };

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
        @Override
        public void onPointerEnd(final playn.core.Pointer.Event event) {
          if (up) {
            //            if (purplePlayer.position < (fields.length - 1)) {
            //              purplePlayer.move(1);
            //            } else {
            //              up = false;
            //            }
          } else {
            //            if (purplePlayer.position > 0) {
            //              purplePlayer.move(-1);
            //            } else {
            //              up = true;
            //            }
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
    System.out.println("move " + p.playerNumber + " for " + numberFields);

    Field field = null;
    for (int i = 1; i <= numberFields; i++) {
      if (p.position < (fields.length - 1)) {
        p.position++;
      } else {
        p.position--;
      }
      System.out.println("position " + p.position);
      field = fields[p.position];
      p.moveTo(field.x, field.y);
      //TODO sleeper for moving
    }
    if ((field != null) && (field.jumpTo != -1)) {
      p.position = field.jumpTo;
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

}
