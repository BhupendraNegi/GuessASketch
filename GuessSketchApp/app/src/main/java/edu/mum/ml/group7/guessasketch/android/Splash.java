
package edu.mum.ml.group7.guessasketch.android;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import edu.mum.ml.group7.guessasketch.android.R;

public class Splash extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.main);

		Thread t = new Thread() {

			public void run() {
				try {
					Thread.sleep(1000);

					startActivity(new Intent().setClassName("edu.mum.ml.group7.guessasketch.android",
							"edu.mum.ml.group7.guessasketch.android.EasyPaint"));
					finish();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		t.start();
	}
}
