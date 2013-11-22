package com.jzplusplus.glassroll;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Random;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.speech.RecognizerIntent;
import android.view.KeyEvent;
import android.widget.TextView;

public class MainActivity extends Activity {
	private int dieValue = 20;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	@Override
	protected void onResume()
	{
		super.onResume();
		
		Bundle b = getIntent().getExtras();
		if(b != null)
		{
			ArrayList<String> voiceResults = getIntent().getExtras().getStringArrayList(RecognizerIntent.EXTRA_RESULTS);
			NumberFormat nf = NumberFormat.getIntegerInstance();
			nf.setParseIntegerOnly(true);
			try {
				dieValue = nf.parse(voiceResults.get(0)).intValue();
			} catch (ParseException e) {
				finish();
			}
		}
		
		rollIt();
	}
	
	void rollIt()
	{
		Random rand = new Random();
		
		int value = rand.nextInt(dieValue) + 1;
		
		((TextView)findViewById(R.id.result)).setText(Integer.toString(value));
		
		if(value == 1) findViewById(R.id.mainview).setBackgroundColor(Color.rgb(255, 0, 0));
		else if(value == dieValue) findViewById(R.id.mainview).setBackgroundColor(Color.rgb(0, 255, 0));
		else findViewById(R.id.mainview).setBackgroundColor(Color.rgb(0, 0, 0));
	}
	
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		super.onKeyUp(keyCode, event);
		
		if(keyCode == KeyEvent.KEYCODE_DPAD_CENTER)
		{
			rollIt();
			return true;
		}
		
		return false;
	}
}
