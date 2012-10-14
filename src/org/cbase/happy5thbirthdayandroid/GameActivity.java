package org.cbase.happy5thbirthdayandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class GameActivity extends Activity {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuItem actionItem = menu.add(R.string.menu_start);
    actionItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    return true;
  }

}
