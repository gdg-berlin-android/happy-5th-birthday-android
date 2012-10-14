package org.cbase.happy5thbirthdayandroid;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.IResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.view.RenderSurfaceView;
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
    return new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new IResolutionPolicy() {
      
      @Override
      public void onMeasure(RenderSurfaceView arg0, int arg1, int arg2) {
        //TODO implement
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
