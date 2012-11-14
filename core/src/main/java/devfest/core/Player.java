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

/**
 * A controllable player.
 */
class Player {

  /**
   * The droid color the player uses.
   */
  private final Droid.Color color;

  /**
   * Flag indicating if the player is controlled by a human or the computer.
   */
  private final boolean human;

  /**
   * Construct a new {@link Player} as human.
   * 
   * @param color The droid color the player uses.
   */
  Player(final Droid.Color color) {
    this(color, true);
  }

  /**
   * Construct a new {@link Player} as human or computer.
   * 
   * @param color The droid color the player uses.
   * @param human Flag indicating if the player is controlled by a human or
   *          the computer.
   */
  Player(final Droid.Color color, final boolean human) {
    this.color = color;
    this.human = human;
  }

  /**
   * @return The droid color the player uses.
   */
  Droid.Color color() {
    return color;
  }

  /**
   * @return Flag indicating if the player is controlled by a human or the
   *         computer.
   */
  boolean human() {
    return human;
  }

}
