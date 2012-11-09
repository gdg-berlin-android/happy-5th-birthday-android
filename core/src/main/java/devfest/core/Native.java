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

import playn.core.PlayN;

/**
 * Accesor to custom subsystems.
 * 
 * <p>
 * This works like the {@link PlayN} class. Static methods provide access to
 * the native components.
 * </p>
 */
public class Native {

  /**
   * Platform specific implementation of the settings dialog.
   */
  private static SettingsDialog settingsDialogImpl;

  /**
   * @return Platform specific implementation of the settings dialog.
   */
  public static SettingsDialog settingsDialog() {
    return settingsDialogImpl;
  }

  /**
   * @param settingsDialogImpl Platform specific implementation of the
   *          settings dialog.
   */
  public static void settingsDialogImpl(final SettingsDialog settingsDialogImpl) {
    Native.settingsDialogImpl = settingsDialogImpl;
  }

}
