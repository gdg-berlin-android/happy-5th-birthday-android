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
 * Interface of the settings dialog where the game can be configured.
 */
public interface SettingsDialog {
  /**
   * will be removed
   */
  @Deprecated
  String TITLE = "Settings";

  /**
   * Stores settings.
   */
  class Settings {
    /**
     * The number of active players.
     */
    final int playerCount;

    /**
     * Construct a new {@link Settings}.
     *
     * @param playerCount The number of active players.
     */
    public Settings(final int playerCount) {
      this.playerCount = playerCount;
    }

    @Override
    public String toString() {
      return "Settings [playerCount=" + playerCount + "]";
    }

  }

  /**
   * Listener for settings dialog events.
   */
  interface Listener {
    /**
     * Gets executed when the settings dialog is closed.
     *
     * @param settings New settings
     */
    void onSettingsDialogClosed(Settings settings);

  }

  /**
   * Open the settings dialog.
   */
  void open(Listener listener);

}
