package org.cbase.happy5thbirthdayandroid;

import org.andengine.engine.options.EngineOptions;
import org.andengine.entity.scene.Scene;
import org.andengine.ui.activity.BaseGameActivity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


public class GameActivity extends BaseGameActivity {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game);
    startDialog();
  }

  private void startDialog() {
    StartDialog dlg = new StartDialog();
    dlg.show(getFragmentManager(), "dialog_start");
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

  @Override
  public EngineOptions onCreateEngineOptions() {
    // TODO Auto-generated method stub
    return null;
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

  private static class StartDialog extends DialogFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.dialog_start_game, container);
      return view;
    }
  }
}
