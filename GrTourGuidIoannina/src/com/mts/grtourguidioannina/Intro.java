package com.mts.grtourguidioannina;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Intro extends Activity{

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.intro);
		Thread timer=new Thread(){
			public void run(){
			try{
				sleep(3000);
			}catch(InterruptedException e){
				e.printStackTrace();
			}finally{
				Intent openmainactivity=new Intent("com.mts.grtourguidioannina.MainActivity");
				startActivity(openmainactivity);
			}
		}
	};
	timer.start();
	}

}
