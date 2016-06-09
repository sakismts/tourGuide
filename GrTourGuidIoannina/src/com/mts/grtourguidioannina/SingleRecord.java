package com.mts.grtourguidioannina;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

public class SingleRecord extends Activity implements OnClickListener{
	Dialog dialog;
	File file;
	private ProgressDialog pDialog;
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	Locale current;
	String img1,img2,img3,summary;
	String tel1,tel2,tel3,mylat,mylng,current_lat,current_lng;
	Bitmap image_bmp;
	String stars="";
	String url_attach="";
	DisplayImageOptions defaultOptions;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.fadein, R.anim.fadeout);
		setContentView(R.layout.single_record);
		
		
		// UNIVERSAL IMAGE LOADER SETUP
				defaultOptions = new DisplayImageOptions.Builder()
						.cacheOnDisc(true).cacheInMemory(true)
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
		String content_en=getIntent().getStringExtra("content_en");	
		tel1=getIntent().getStringExtra("tel1");
		tel2=getIntent().getStringExtra("tel2");
		tel3=getIntent().getStringExtra("tel3");
		img1=getIntent().getStringExtra("img1");
		img2=getIntent().getStringExtra("img2");
		img3=getIntent().getStringExtra("img3");
		String email=getIntent().getStringExtra("email");
		String image=getIntent().getStringExtra("image");
		String distance=getIntent().getStringExtra("distance");
		if(getIntent().getStringExtra("distance")==null)
			distance="";
		String language=getIntent().getStringExtra("language");
		String web=getIntent().getStringExtra("web");
		mylat=getIntent().getStringExtra("mylat");
		mylng=getIntent().getStringExtra("mylng");
		current_lat=getIntent().getStringExtra("current_lat");
		current_lng=getIntent().getStringExtra("current_lng");
		Boolean con=getIntent().getBooleanExtra("connection",false);
		String date=getIntent().getStringExtra("date");
		
		if(getIntent().getStringExtra("stars")!=null)
			stars=getIntent().getStringExtra("stars");
		if(getIntent().getStringExtra("url_attach")!=null)
			url_attach=getIntent().getStringExtra("url_attach");
		
		
		//initialize views
		TextView tv_address=(TextView)findViewById(R.id.tv_address);
		TextView tv_distance=(TextView)findViewById(R.id.tv_distance);
		TextView tv_name=(TextView)findViewById(R.id.tv_name);
		TextView tv_tel1=(TextView)findViewById(R.id.tv_tel1);
		TextView tv_tel2=(TextView)findViewById(R.id.tv_tel2);
		TextView tv_tel3=(TextView)findViewById(R.id.tv_tel3);
		tv_tel1.setVisibility(View.INVISIBLE);
		tv_tel2.setVisibility(View.INVISIBLE);
		tv_tel3.setVisibility(View.INVISIBLE);
		
		WebView webview=(WebView)findViewById(R.id.webView1);		
		ImageButton share_btn=(ImageButton)findViewById(R.id.img_btn_share);
		ImageButton email_btn=(ImageButton)findViewById(R.id.img_btn_email);
		ImageButton web_btn=(ImageButton)findViewById(R.id.img_btn_web);
		ImageButton map_btn=(ImageButton)findViewById(R.id.img_btn_map);
		ImageButton direction_btn=(ImageButton)findViewById(R.id.img_btn_direction);
		ImageView main_image=(ImageView)findViewById(R.id.iv_main_image);
		TextView tv_date=(TextView)findViewById(R.id.tv_date);
		Button btn_url=(Button)findViewById(R.id.btn_menu);
		btn_url.setVisibility(View.GONE);
		btn_url.setOnClickListener(this);
		share_btn.setOnClickListener(this);		
		web_btn.setOnClickListener(this);
		email_btn.setOnClickListener(this);
		map_btn.setOnClickListener(this);
		direction_btn.setOnClickListener(this);
		tv_tel1.setOnClickListener(this);
		tv_tel2.setOnClickListener(this);
		tv_tel3.setOnClickListener(this);
		
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
		
		
		
		if(!stars.equals("")){
		  if(stars.equals("5")){
			  tv_name.setCompoundDrawablesWithIntrinsicBounds( 0, 0, 0,R.drawable.five_stars);
	        }else if(stars.equals("4")){
	        	tv_name.setCompoundDrawablesWithIntrinsicBounds( 0, 0, 0,R.drawable.four_stars);
	        }else if(stars.equals("3")){
	        	tv_name.setCompoundDrawablesWithIntrinsicBounds( 0, 0, 0,R.drawable.three_stars);
	        }else if(stars.equals("2")){
	        	tv_name.setCompoundDrawablesWithIntrinsicBounds( 0, 0, 0,R.drawable.two_stars);
	        }else if(stars.equals("1")){
	        	tv_name.setCompoundDrawablesWithIntrinsicBounds( 0, 0, 0,R.drawable.one_stars);
	        }
		  }
		
		if(!url_attach.equals("")){
			btn_url.setVisibility(View.VISIBLE);
			
			
		}
		
		if(!tel1.equals(""))
		{
			tv_tel1.setVisibility(View.VISIBLE);
			tv_tel1.setText(tel1);
			
			
		}
		if(!tel2.equals(""))
		{
			tv_tel2.setVisibility(View.VISIBLE);
			tv_tel2.setText(tel2);
			
		}
		if(!tel3.equals(""))
		{
			tv_tel3.setVisibility(View.VISIBLE);
			tv_tel3.setText(tel3);
			
		}
		if(con==true){
			 
		imageLoader.displayImage(image,main_image);			
			
		
		Gallery g = (Gallery) findViewById(R.id.gallery);
		g.setSpacing(2);
		 
			        // Set the adapter to our custom adapter (below)
		int images_counter=0;
		 if(!img1.equals(""))
			 images_counter++;
	            if(!img2.equals(""))
	            	 images_counter++;
	            if(!img3.equals(""))
	            	 images_counter++;
	            
		g.setAdapter(new ImageAdapter(this, images_counter));
		}else{
			byte[] bytes = getIntent().getByteArrayExtra("BMP");
	        image_bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
	        main_image.setImageBitmap(image_bmp);
	        }
	}
	
	public class ImageAdapter extends BaseAdapter {
		private Context mContext;
		private int images_count;
       

        public ImageAdapter(Context c,int counter) {
            mContext = c;
            images_count=counter;
        }

        public int getCount() {
            return images_count;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
        	//String[] mImageIds = {img1,img2,img3};
        	ImageView i = new ImageView(mContext);

            //i.setImageResource(mImageIds[position]);
            //i.setImageURI(Uri.parse(mImageIds[position]));
            //i.setScaleType(ImageView.ScaleType.FIT_XY);
            i.setLayoutParams(new Gallery.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
        	//String image=getIntent().getStringExtra("image");
            ArrayList<String> myimages=new ArrayList<String>();
            if(!img1.equals(" "))
            myimages.add(img1);
            if(!img2.equals(""))
                myimages.add(img2);
            if(!img3.equals(""))
                myimages.add(img3);
            System.out.println(img1);

          /*  if(!img1.equals("") && !img2.equals("") && !img3.equals("")){
            String[] mImageIds = {img1,img2,img3};
            }else if(img1.equals("") && !img2.equals("") && !img3.equals("")){
            	
            }else if(){
            	
            }*/
            
        	imageLoader.displayImage( myimages.get(position),i,defaultOptions);	
        	//ImageLoader.getInstance().displayImage(myimages.get(position), i, defaultOptions);
            return i;
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
			startActivity(intent);}
		
		else{
			if(current.getLanguage().equals(("el")))
			Toast.makeText(this, " Το email δεν είναι διαθέσιμο", Toast.LENGTH_SHORT).show();
			else
				Toast.makeText(this, "email is not available", Toast.LENGTH_SHORT).show();
		}
			break;
		case R.id.img_btn_map:
			Intent mymap=new Intent(SingleRecord.this,Map.class);
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
		case R.id.btn_menu:
			System.out.println(url_attach);
			downloadfile task=new downloadfile();
			
			task.execute(url_attach);
			
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
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		
		
		
	}
	
	@Override
	public void onBackPressed() {
	   // super.onBackPressed();
		Intent returnIntent = new Intent();
		returnIntent.putExtra("state","single_record");
		setResult(RESULT_OK,returnIntent);
		finish();
		
	}
	
	private class downloadfile extends AsyncTask<String, Integer, File >{

		@Override
		protected File doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			 URL url;
			 file = null;
			 int downloadedSize = 0 ;
			 long totalsize=0;
			 Float per = 0f;
			 String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
			 File folder = new File(extStorageDirectory, "Ioannina_GTG");
			 String fileName = url_attach.substring( url_attach.lastIndexOf('/')+1, url_attach.length() );
				System.out.println(fileName);
					        folder.mkdir();
					        file = new File(folder, fileName);
					        try {
					            file.createNewFile();
					        } catch (IOException e1) {
					            e1.printStackTrace();
					        }
			 
			try {
				url = new URL(arg0[0]);
				
			
				 HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		 
		            urlConnection.setRequestMethod("GET");
		           urlConnection.setDoOutput(true);
		 
		            // connect
		            urlConnection.connect();
		          //////////////////////////////////////  
		            HttpClient client=new DefaultHttpClient();
					URI website=new URI(arg0[0]);
					HttpGet request=new HttpGet();
					request.setURI(website);
					HttpResponse response=client.execute(request);
					HttpEntity entity=response.getEntity();
					InputStream inputStream=null;
					if(entity!=null){
						totalsize=entity.getContentLength();
						inputStream=entity.getContent();
					}
		         ///////////////////////////////////////////////////////////////////////////////   
		            FileOutputStream fileOutput = new FileOutputStream(file);
		          //  InputStream inputStream = urlConnection.getInputStream();
		          //  totalsize = urlConnection.getContentLength();
		            
		            byte[] buffer = new byte[1024 * 1024]; 
		            int bufferLength = 0;
		 
		            while ((bufferLength = inputStream.read(buffer)) > 0) {
		                fileOutput.write(buffer, 0, bufferLength);
		                downloadedSize += bufferLength;
		                System.out.println(" the total size is :"+totalsize );
		                System.out.println(" the buffer lenght  is :"+bufferLength );
		                System.out.println(" the downloaded size is :"+downloadedSize );
		               // per = ((float) downloadedSize / totalsize) * 100;
		                per=(((float)downloadedSize*100)/totalsize);
		                int intper=per.intValue();
		                System.out.println(intper);
		                pDialog.setProgress(intper);
		                //pDialog.incrementProgressBy(per.intValue());
		              //  setText("Total PDF  "
		               //         + (totalsize / 1024)
		               //         + " KB\n\nDownloading PDF " + (int) per
		               //         + "% complete");
		            }
		            // close the output stream when complete //
		            fileOutput.close();
		            
		            
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				 //TODO Auto-generated catch block
				e.printStackTrace();
			}
	           
			// for (int i = 0; i < 100; i++) {
				 //publishProgress(10);
				
				 

			// }

			return file;
		}



		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
			setProgress(values[0]);
			

		}



		@Override
		protected void onPostExecute(File result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			pDialog.dismiss();
			 Uri path = Uri.fromFile(file);
			try {
	            Intent intent = new Intent(Intent.ACTION_VIEW);
	            intent.setDataAndType(path, "application/pdf");
	            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	            startActivity(intent);
	            
	        } catch (ActivityNotFoundException e) {
	           
	        }
			
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(SingleRecord.this);
			pDialog.setMessage("downloading...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
		
			pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			pDialog.show();
			pDialog.getWindow().setGravity(Gravity.BOTTOM);
		}
		}

}
