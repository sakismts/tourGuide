package com.mts.grtourguidioannina;

import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Secretaries_phones extends Activity{
	LinearLayout ll_thetikwn,ll_epist_texn,ll_filosofiki,ll_epist_agwghs,ll_iatriki,ll_anexart,ll_tei;
	Locale current;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		current=getResources().getConfiguration().locale;
		
		if(current.getLanguage().equals(("el"))){
			setTitle("Τηλέφωνα Γραμματειών");
			setContentView(R.layout.secr_telephones);
			}
		else{
			setTitle("Secretaries Phones");
			setContentView(R.layout.secr_telephones_en);
			}
		
		getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
		
		
		TextView thetikwn=(TextView)findViewById(R.id.tv_thetikwn);
		TextView epist_texn=(TextView)findViewById(R.id.tv_epistimis_texn);
		TextView filosofiki=(TextView)findViewById(R.id.tv_filosofiki);
		TextView epist_agw=(TextView)findViewById(R.id.tv_sxolis_agwgis);
		TextView iatrikis=(TextView)findViewById(R.id.tv_iatriki);
		TextView anexart=(TextView)findViewById(R.id.tv_anexart);
		TextView tei=(TextView)findViewById(R.id.tv_tei);
		
		ll_thetikwn=(LinearLayout)findViewById(R.id.ll_thetikwn);
		ll_epist_texn=(LinearLayout)findViewById(R.id.ll_epist_texn);
		ll_filosofiki=(LinearLayout)findViewById(R.id.ll_filosogiki);
		ll_epist_agwghs=(LinearLayout)findViewById(R.id.ll_epist_agwghs);
		ll_iatriki=(LinearLayout)findViewById(R.id.ll_iatrikis);
		ll_anexart=(LinearLayout)findViewById(R.id.ll_anexart);
		ll_tei=(LinearLayout)findViewById(R.id.ll_tei);
		
		ll_epist_texn.setVisibility(View.GONE);
		ll_thetikwn.setVisibility(View.GONE);
		ll_filosofiki.setVisibility(View.GONE);
		ll_epist_agwghs.setVisibility(View.GONE);
		ll_iatriki.setVisibility(View.GONE);
		ll_anexart.setVisibility(View.GONE);
		ll_tei.setVisibility(View.GONE);
		tei.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if(ll_tei.getVisibility()==View.GONE)
					ll_tei.setVisibility(View.VISIBLE);
				else
					ll_tei.setVisibility(View.GONE);
					
					
				
			}
		});
		thetikwn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if(ll_thetikwn.getVisibility()==View.GONE)
					ll_thetikwn.setVisibility(View.VISIBLE);
				else
					ll_thetikwn.setVisibility(View.GONE);
					
					
				
			}
		});
		epist_texn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if(ll_epist_texn.getVisibility()==View.GONE)
					ll_epist_texn.setVisibility(View.VISIBLE);
				else
					ll_epist_texn.setVisibility(View.GONE);
					
					
				
			}
		});
		filosofiki.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
				if(ll_filosofiki.getVisibility()==View.GONE)
					ll_filosofiki.setVisibility(View.VISIBLE);
				else
					ll_filosofiki.setVisibility(View.GONE);
					
					
				
			}
		});
		epist_agw.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
				if(ll_epist_agwghs.getVisibility()==View.GONE)
					ll_epist_agwghs.setVisibility(View.VISIBLE);
				else
					ll_epist_agwghs.setVisibility(View.GONE);
					
					
				
			}
		});
		iatrikis.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	
		if(ll_iatriki.getVisibility()==View.GONE)
			ll_iatriki.setVisibility(View.VISIBLE);
		else
			ll_iatriki.setVisibility(View.GONE);
			
			
		
	}
});
filosofiki.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	
		if(ll_filosofiki.getVisibility()==View.GONE)
			ll_filosofiki.setVisibility(View.VISIBLE);
		else
			ll_filosofiki.setVisibility(View.GONE);
			
			
		
	}
});
anexart.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	
		if(ll_anexart.getVisibility()==View.GONE)
			ll_anexart.setVisibility(View.VISIBLE);
		else
			ll_anexart.setVisibility(View.GONE);
			
			
		
	}
});		
		
	}

}
