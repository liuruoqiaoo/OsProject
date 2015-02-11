package com.example.osblock;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ChoiceActivity extends Activity {
	Button bf, ff, cf, wf;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choice_view);
		bf = (Button) findViewById(R.id.bf);
		ff = (Button) findViewById(R.id.ff);
		cf = (Button) findViewById(R.id.cf);
		wf = (Button) findViewById(R.id.wf);
		bf.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(ChoiceActivity.this,MainActivity.class);
				intent.putExtra("choice", "bf");
				startActivity(intent);

			}
		});
		cf.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(ChoiceActivity.this,MainActivity.class);
				intent.putExtra("choice", "cf");
				startActivity(intent);

			}
		});
		ff.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(ChoiceActivity.this,MainActivity.class);
				intent.putExtra("choice", "ff");
				startActivity(intent);

			}
		});
		wf.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(ChoiceActivity.this,MainActivity.class);
				intent.putExtra("choice", "wf");
				startActivity(intent);

			}
		});

	}

}
