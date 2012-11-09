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

package devfest.android;

import java.util.ArrayList;
import java.util.List;

import playn.android.GameActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import devfest.core.SettingsDialog;

/**
 * Native Android implementation of the settings dialog.
 */
public class AndroidSettingsDialog implements SettingsDialog {

  /**
   * The activity in which the game runs.
   */
  private final GameActivity activity;

  /**
   * Construct a new {@link AndroidSettingsDialog}.
   * 
   * @param activity The activity in which the game runs.
   */
  public AndroidSettingsDialog(final GameActivity activity) {
    this.activity = activity;
  }

  @Override
  public void open(final Listener listener) {
    activity.runOnUiThread(new Runnable() {
      @Override
      public void run() {
        /*
         * Very basic hardcoded dialog. I think this should be moved to XMLs?
         */
        final Spinner numPlayersSpinner = buildSpinner(activity);
        AlertDialog.Builder dialog = buildDialog(activity, numPlayersSpinner);

        dialog.setPositiveButton("START", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(final DialogInterface dialog, final int which) {
            listener.onSettingsDialogClosed(new Settings(
                Integer.valueOf((String) numPlayersSpinner.getSelectedItem())));
          }
        });
        dialog.show();
      }
    });
  }

  private static Spinner buildSpinner(final Activity activity) {
    List<String> values = new ArrayList<String>();
    values.add("1");
    values.add("2");
    values.add("3");
    values.add("4");
    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(activity,
        android.R.layout.simple_spinner_item, values);
    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    Spinner spinner = new Spinner(activity);
    spinner.setAdapter(dataAdapter);

    return spinner;
  }

  private static AlertDialog.Builder buildDialog(final Activity activity, final Spinner numPlayersSpinner) {
    AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
    dialog.setMessage(TITLE);
    dialog.setView(numPlayersSpinner);

    return dialog;
  }

}
