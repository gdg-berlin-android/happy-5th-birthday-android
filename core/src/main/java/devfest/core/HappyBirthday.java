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

import static devfest.core.Native.settingsDialog;
import static playn.core.PlayN.graphics;
import static playn.core.PlayN.mouse;
import static playn.core.PlayN.platformType;
import static playn.core.PlayN.touch;
import java.util.LinkedList;
import java.util.List;
import playn.core.Game;
import playn.core.Platform.Type;
import devfest.core.Dice.DiceListener;
import devfest.core.SettingsDialog.Settings;


/**
 * Main game class.
 */
public class HappyBirthday implements Game {
  /**
   * All assets that we need.
   */
  private Assets assets;

  /**
   * Playing ground.
   */
  private Board board;

  /**
   * The dice :)
   */
  private Dice dice;

  /**
   * Active players.
   */
  private List<Player> players;

  /**
   * The current player.
   */
  private Player currentPlayer;

  @Override
  public void init() {
    if (platformType() != Type.JAVA) {
      // Resize to the available resolution on the current device
      graphics().setSize(graphics().screenWidth(), graphics().screenHeight());
    } else {
      // Use 800x600 for the Java backend for now. Good for testing
      graphics().setSize(800, 600);
    }

    assets = new Assets(new Assets.Adapter() {
        @Override
        public void done() {
          startGame();
        }
      });
    assets.load();
  }

  @Override
  public void paint(final float alpha) {
    if (board != null) {
      board.paint(alpha);
    }
  }

  @Override
  public void update(final float delta) {
    if (board != null) {
      board.update(delta);
    }
  }

  @Override
  public int updateRate() {
    return 25;
  }

  /**
   * Start the game.
   */
  private void startGame() {
    assert assets.loaded();

    board = new Board(updateRate(), assets.boardImage(), assets.droidSprite());

    BoardGestures boardGestures = new BoardGestures(board);
    touch().setListener(boardGestures);
    mouse().setListener(boardGestures);
    dice = new Dice(assets.diceRingSprite(), assets.diceSprite());
    players = new LinkedList<Player>();

    settingsDialog().open(new SettingsDialog.Listener() {
        @Override
        public void onSettingsDialogClosed(final Settings settings) {
          assert (settings.playerCount >= 1) && (settings.playerCount <= 4);
          players.clear();
          board.clear();
          for (int i = 0; (i < 4) && (i < settings.playerCount); i++) {
            Player player = new Player(Droid.Color.values()[i]);
            board.initDroid(player.color());
            players.add(player);
          }

          currentPlayer = players.get(0);
          dice.color(currentPlayer.color());
          dice.visible(true);
        }
      });

    dice.addListener(new DiceListener() {
        @Override
        public void onDiced(final int value) {
          dice.enabled(false);
          board.moveDroid(currentPlayer.color(), value, new Board.TurnListener() {
              @Override
              public void onTurnOver() {
                int nextDroidId = currentPlayer.color().id + 1;
                if ((nextDroidId >= Droid.Color.values().length) || (nextDroidId == players.size())) {
                  nextDroidId = 0;
                }

                currentPlayer = players.get(nextDroidId);
                dice.color(currentPlayer.color());
                if (currentPlayer.human()) {
                  dice.enabled(true);
                }
              }
            });
        }
      });
  }

}
