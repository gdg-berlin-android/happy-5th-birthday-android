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

package devfest.android;

import android.view.KeyEvent;
import playn.android.GameActivity;
import playn.core.PlayN;
import devfest.core.HappyBirthday;

public class HappyBirthdayActivity extends GameActivity {

  @Override
  public void main() {
    platform().assets().setPathPrefix("devfest/resources");
    PlayN.run(new HappyBirthday());
  }

  @Override
  public boolean onKeyDown(int keyCode, KeyEvent event) {
    if (keyCode == KeyEvent.KEYCODE_BACK) {
      finish();
    }

    return super.onKeyDown(keyCode, event);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();

    System.runFinalizersOnExit(true);
    System.exit(0);
  }

}
