package org.cbase.happy5thbirthdayandroid;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.IResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.view.RenderSurfaceView;
import org.andengine.ui.activity.BaseGameActivity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


public class GameActivity extends BaseGameActivity {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game);
    startDialog();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuItem actionItem = menu.add(R.string.menu_start);
    actionItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    startDialog();
    return true;
  }

  private void startDialog() {
    StartDialog dlg = new StartDialog();
    dlg.show(getFragmentManager(), "dialog_start");
  }

  private static class StartDialog extends DialogFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.dialog_start_game, container);
      Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
      ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.number_of_users,
        android.R.layout.simple_spinner_item);
      adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      spinner.setAdapter(adapter);
      spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
          public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(getActivity(), "selected: " + position, Toast.LENGTH_SHORT).show();
          }

          public void onNothingSelected(AdapterView<?> parent) {
          }
        });
      spinner.requestFocus();
      getDialog().setTitle("Choose the number of players");

      return view;
    }
  }

  @Override
  public EngineOptions onCreateEngineOptions() {
    // TODO Auto-generated method stub
    return new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new IResolutionPolicy() {
        @Override
        public void onMeasure(RenderSurfaceView arg0, int arg1, int arg2) {
          // TODO implement
        }
      }, new Camera(0, 0, 2000, 1427));
  }

  @Override
  public void onCreateResources(OnCreateResourcesCallback arg0) throws Exception {
    // TODO Auto-generated method stub

  }

  @Override
  public void onCreateScene(OnCreateSceneCallback arg0) throws Exception {
    // TODO Auto-generated method stub

  }

  @Override
  public void onPopulateScene(Scene arg0, OnPopulateSceneCallback arg1) throws Exception {
    // TODO Auto-generated method stub

  }
}
