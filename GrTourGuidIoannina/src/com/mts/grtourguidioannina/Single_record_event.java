package com.mts.grtourguidioannina;

import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mts.grtourguidioannina.SingleRecord.ImageAdapter;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

public class Single_record_event extends Activity implements OnClickListener{
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	Locale current;
	String img1,img2,img3;
	String tel1,tel2,tel3,mylat,mylng,current_lat,current_lng,summary;
	Bitmap image_bmp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.single_record_event);
		
		
		// UNIVERSAL IMAGE LOADER SETUP
				DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
						.cacheOnDisc(true).cacheInMemory(false)
						.imageScaleType(ImageScaleType.EXACTLY)
						.showImageOnLoading(R.drawable.ic_launcher_large)
						.displayer(new FadeInBitmapDisplayer(300)).build();
		
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
		.defaultDisplayImageOptions(defaultOptions)
		.build();
		
	    ImageLoader.getInstance().init(config);
		
		//get the variables from the previous activity
		String name=getIntent().getStringExtra("name");
		String address=getIntent().getStringExtra("address");
		String content=getIntent().getStringExtra("content");	
		String name_en=getIntent().getStringExtra("name_en");
		String address_en=getIntent().getStringExtra("address_en");
		boolean iscoffee=getIntent().getBooleanExtra("iscoffee", false);
		String content_en=getIntent().getStringExtra("content_en");	
		System.out.println(content_en);
		tel1=getIntent().getStringExtra("tel1");
		
		String email=getIntent().getStringExtra("email");
		String image=getIntent().getStringExtra("image");
		String distance=getIntent().getStringExtra("distance");
		String language=getIntent().getStringExtra("language");
		String web=getIntent().getStringExtra("web");
		mylat=getIntent().getStringExtra("mylat");
		mylng=getIntent().getStringExtra("mylng");
		current_lat=getIntent().getStringExtra("current_lat");
		current_lng=getIntent().getStringExtra("current_lng");
		Boolean con=getIntent().getBooleanExtra("connection",false);
		String date=getIntent().getStringExtra("date");
		System.out.println("the date is "+date);
		
		//initialize views
		TextView tv_address=(TextView)findViewById(R.id.tv_address);
		TextView tv_distance=(TextView)findViewById(R.id.tv_distance);
		TextView tv_name=(TextView)findViewById(R.id.tv_name);
		TextView tv_tel1=(TextView)findViewById(R.id.tv_tel1);
	
		tv_tel1.setVisibility(View.INVISIBLE);
		
		
		WebView webview=(WebView)findViewById(R.id.webView1);		
		ImageButton share_btn=(ImageButton)findViewById(R.id.img_btn_share);
		ImageButton email_btn=(ImageButton)findViewById(R.id.img_btn_email);
		ImageButton web_btn=(ImageButton)findViewById(R.id.img_btn_web);
		ImageButton map_btn=(ImageButton)findViewById(R.id.img_btn_map);
		ImageButton direction_btn=(ImageButton)findViewById(R.id.img_btn_direction);
		ImageView main_image=(ImageView)findViewById(R.id.iv_main_image);
		TextView tv_date=(TextView)findViewById(R.id.tv_date);
		tv_date.setText(date);
		share_btn.setOnClickListener(this);		
		web_btn.setOnClickListener(this);
		email_btn.setOnClickListener(this);
		map_btn.setOnClickListener(this);
		direction_btn.setOnClickListener(this);
		tv_tel1.setOnClickListener(this);
	
		
		current = getResources().getConfiguration().locale;
		
    
		
		//put values to views
		if(current.getLanguage().equals(("el"))){
			tv_name.setText(name);
			tv_address.setText(address);
			WebSettings settings = webview.getSettings();
			settings.setDefaultTextEncodingName("utf-8");
			//webview.setBackgroundColor(0);
			if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == 
			        Configuration.SCREENLAYOUT_SIZE_XLARGE) {
			    // on a large screen device ...
		
			summary = "<html><head><title>Title of the document</title></head><body bgcolor=\"#f0f0f0\" style=\"background-color:#f0f0f0;font-size:20px;\">"+content+"</body></html>";
			}else{
				summary = "<html><head><title>Title of the document</title></head><body bgcolor=\"#f0f0f0\" style=\"background-color:#f0f0f0;\">"+content+"</body></html>";
			}
			 webview.loadData(summary,  "text/html; charset=utf-8", "utf-8");
			tv_distance.setText(distance+"Χλμ.");
		}else{
		
			tv_name.setText(name_en);
			tv_address.setText(address_en);
			WebSettings settings = webview.getSettings();
			settings.setDefaultTextEncodingName("utf-8");
			//webview.setBackgroundColor(0);
			if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == 
			        Configuration.SCREENLAYOUT_SIZE_XLARGE) {
			    // on a large screen device ...
			
			summary = "<html><head><title>Title of the document</title></head><body bgcolor=\"#f0f0f0\" style=\"background-color:#f0f0f0;font-size:20px;\">"+content_en+"</body></html>";
			}else{
				summary = "<html><head><title>Title of the document</title></head><body bgcolor=\"#f0f0f0\" style=\"background-color:#f0f0f0;\">"+content_en+"</body></html>";
			}
			 webview.loadData(summary,  "text/html; charset=utf-8", "utf-8");
			tv_distance.setText(distance+"Km");
		}
		
		
		if(iscoffee==true){
			LinearLayout ll_date=(LinearLayout)findViewById(R.id.ll_date);
			ll_date.setVisibility(View.GONE);
		}
		if(!tel1.equals(""))
		{
			tv_tel1.setVisibility(View.VISIBLE);
			tv_tel1.setText(tel1);
			
			
		}
		
		if(con==true){
			
		imageLoader.displayImage(image,main_image);			
	
		}else{
			byte[] bytes = getIntent().getByteArrayExtra("BMP");
	        image_bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
	        main_image.setImageBitmap(image_bmp);
	        }
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.img_btn_share:
			Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND); 
		    sharingIntent.setType("text/plain");
		    String shareBody =getIntent().getStringExtra("web");
		    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
		    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
		   

		startActivity(Intent.createChooser(sharingIntent, "Share via"));
			break;
		case R.id.img_btn_web:
			String web=getIntent().getStringExtra("web");
			if(web.equals("")==false){
			Intent webintent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(getIntent().getStringExtra("web")));
			startActivity(webintent);
			}else{
				if(current.getLanguage().equals(("el")))
				Toast.makeText(this, " Η ιστοσελίδα δεν είναι διαθέσιμη", Toast.LENGTH_SHORT).show();
				else
					Toast.makeText(this, "Webpage is not available", Toast.LENGTH_SHORT).show();
			}
			break;	
		case R.id.img_btn_email:
			if(getIntent().getStringExtra("email").equals("")==false){
			Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
			intent.setType("text/plain");		
			
			intent.setData(Uri.parse("mailto:"+getIntent().getStringExtra("email"))); // or just "mailto:" for blank
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
			startActivity(intent);
			}else{
				if(current.getLanguage().equals(("el")))
					Toast.makeText(this, " Το email δεν είναι διαθέσιμο", Toast.LENGTH_SHORT).show();
					else
						Toast.makeText(this, "email is not available", Toast.LENGTH_SHORT).show();
				}
			break;
		case R.id.img_btn_map:
			Intent mymap=new Intent(Single_record_event.this,Map.class);
			mymap.putExtra("lat", getIntent().getStringExtra("mylat"));
			mymap.putExtra("lng", getIntent().getStringExtra("mylng"));
			mymap.putExtra("name", getIntent().getStringExtra("name"));
			startActivity(mymap);
		break;
		case R.id.img_btn_direction:
			
			Intent i = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?saddr="+current_lat+","+current_lng+"&daddr="+mylat+","+mylng));
			startActivity(i);
			break;
		case R.id.tv_tel1:
			System.out.println(tel1);
			Intent callIntent1 = new Intent(Intent.ACTION_CALL);
			callIntent1.setData(Uri.parse("tel:"+getNumber(tel1)));
			startActivity(callIntent1);
			break;
		case R.id.tv_tel2:
			Intent callIntent2 = new Intent(Intent.ACTION_CALL);
			callIntent2.setData(Uri.parse("tel:"+getNumber(tel2)));
			startActivity(callIntent2);
					
			break;
		case R.id.tv_tel3:
			Intent callIntent3 = new Intent(Intent.ACTION_CALL);
			callIntent3.setData(Uri.parse("tel:"+getNumber(tel3)));
			startActivity(callIntent3);
			
			break;
		
		}
		
	}
	public String getNumber(String number){
		
		String[] parts = number.trim().split(" ");			
		String number_to_ret="";
		for (String item:parts){
			if(item.length()>1 &&(item.charAt(0)=='0' ||item.charAt(0)=='1' || item.charAt(0)=='2' || item.charAt(0)=='3' || item.charAt(0)=='4' || item.charAt(0)=='5' || item.charAt(0)=='6' || item.charAt(0)=='7' || item.charAt(0)=='8' || item.charAt(0)=='9')){
				number_to_ret=number_to_ret+item;
				
				
			}
			
		}		
		return number_to_ret;
	}

	@Override
	public void onBackPressed() {
	   // super.onBackPressed();
		Intent returnIntent = new Intent();
		returnIntent.putExtra("state","single_record");
		setResult(RESULT_OK,returnIntent);
		finish();
		
	}
}
