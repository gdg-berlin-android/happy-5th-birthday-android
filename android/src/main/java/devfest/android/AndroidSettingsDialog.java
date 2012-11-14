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
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Spinner;
import android.widget.TextView;
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
          LinearLayout linear = new LinearLayout(activity);
          linear.setOrientation(LinearLayout.VERTICAL);

          final TextView tv1 = new TextView(activity);
          final TextView tv2 = new TextView(activity);
          LayoutParams param = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
          int dimension = (int) activity.getResources().getDimension(R.dimen.margin_layout);
          param.setMargins(dimension, dimension, dimension, dimension);

          OnItemSelectedListener itemSelectListener = new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
              tv1.setText(activity.getString(R.string.setting_player, position + 1));
              tv2.setText(activity.getString(R.string.setting_computer, (((position + 1) == 4) ? "0" : "1")));
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
          };

          final Spinner numPlayersSpinner = buildSpinner(activity, itemSelectListener);
          linear.addView(numPlayersSpinner, param);
          linear.addView(tv1, param);
          linear.addView(tv2, param);

          AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
          dialog.setMessage(TITLE);
          dialog.setView(linear);

          dialog.setPositiveButton("START", new DialogInterface.OnClickListener() {
              @Override
              public void onClick(final DialogInterface dialog, final int which) {
                listener.onSettingsDialogClosed(
                  new Settings(
                    Integer.valueOf((String) numPlayersSpinner.getSelectedItem())));
              }
            });
          dialog.show();
        }
      });
  }

  Spinner buildSpinner(final Activity activity, OnItemSelectedListener listener) {
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
    spinner.setOnItemSelectedListener(listener);

    return spinner;
  }

}
