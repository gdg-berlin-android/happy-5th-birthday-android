package org.cbase.happy5thbirthdayandroid;

import org.andengine.engine.options.EngineOptions;
import org.andengine.entity.scene.Scene;
import org.andengine.ui.activity.BaseGameActivity;

import android.os.Bundle;
import android.view.Menu;

public class GameActivity extends BaseGameActivity {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.activity_game, menu);
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
}
