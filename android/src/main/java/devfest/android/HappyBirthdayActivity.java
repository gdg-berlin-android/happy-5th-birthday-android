package devfest.android;

import playn.android.GameActivity;
import playn.core.PlayN;

import devfest.core.HappyBirthday;

public class HappyBirthdayActivity extends GameActivity {

  @Override
  public void main(){
    platform().assets().setPathPrefix("devfest/resources");
    PlayN.run(new HappyBirthday());
  }
}
