package com.jzplusplus.glassroll;

import java.util.Random;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.KeyEvent;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	//private static String TAG = "GlassRoll";
	
	// The die value construed as user voice input
	private int dieValue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Get which voice trigger launched the app
		String className = getComponentName().getClassName();
		String number = className.substring(className.lastIndexOf(".")+1);
		try
		{
			dieValue = Integer.parseInt(number);
		}
		catch(NumberFormatException nfe)
		{
			dieValue = 20;
		}
		
	}

	@Override
	protected void onResume() {
		super.onResume();
		rollIt();
	}

	/*
	 * Does the RNG for the die roll 
	 */
	void rollIt() {
		Random rand = new Random();
		int value = rand.nextInt(dieValue) + 1;
		((TextView) findViewById(R.id.result)).setText(Integer.toString(value));
		if (value == 1)
			findViewById(R.id.mainview).setBackgroundColor(Color.rgb(255, 0, 0));
		else if (value == dieValue)
			findViewById(R.id.mainview).setBackgroundColor(Color.rgb(0, 255, 0));
		else
			findViewById(R.id.mainview).setBackgroundColor(Color.rgb(0, 0, 0));
	}

	/*
	 * On key pad tap, the app rolls again
	 */
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		super.onKeyUp(keyCode, event);
		if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
			rollIt();
			return true;
		}
		return false;
	}
	
}
