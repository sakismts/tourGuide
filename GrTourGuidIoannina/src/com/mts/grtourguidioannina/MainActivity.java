package com.mts.grtourguidioannina;

import java.io.ByteArrayOutputStream;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.maps.model.LatLng;
import com.mts.grtourguidioannina.Hotels.LoadRecords;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.os.Build;

public class MainActivity extends Activity implements OnClickListener {
	Dialog dialog;
	File file;
	Locale myLocale;
	ImageView main_logo;
	Locale current;
	private ProgressDialog pDialog;
	private Boolean con=false;
	private static final String TAG_POSTS = "posts";
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_LINK = "link";
	private static final String READ_URL = "http://www.greek-tour-guides.eu/ioannina/ioannina_gr_tour_app/sitisi/sitisi.php";
	private JSONArray mRecords = null;	
	private ArrayList<String> mRecordsList;
	String url_sitisi;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ImageButton gr_flag=(ImageButton)findViewById(R.id.im_gr_flag);
		ImageButton en_flag=(ImageButton)findViewById(R.id.im_en_flag);
		main_logo=(ImageView)findViewById(R.id.main_logo);
		current = getResources().getConfiguration().locale;
		
		if(current.getLanguage().equals(("el")))
			 main_logo.setImageResource(R.drawable.main_logo);
		else
			 main_logo.setImageResource(R.drawable.main_logo_en);
		en_flag.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(),
                        "You have selected English", Toast.LENGTH_SHORT)
                        .show();
				
                setLocale("en");
                main_logo.setImageResource(R.drawable.main_logo_en);
			}
		});
		gr_flag.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(),
                        "Επιλέξατε Ελληνικά", Toast.LENGTH_SHORT)
                        .show();
                setLocale("el");
                main_logo.setImageResource(R.drawable.main_logo);
			}
		});
		Button events=(Button)findViewById(R.id.btn_events);
		Button students=(Button)findViewById(R.id.btn_students);
		Button info=(Button)findViewById(R.id.btn_info);
		LinearLayout ll_hotels=(LinearLayout)findViewById(R.id.ll_hotel);
		LinearLayout ll_destinations=(LinearLayout)findViewById(R.id.ll_proorismoi);
		LinearLayout ll_shites=(LinearLayout)findViewById(R.id.ll_axiotheata);
		LinearLayout ll_food=(LinearLayout)findViewById(R.id.ll_food);
		LinearLayout ll_fun=(LinearLayout)findViewById(R.id.ll_fun);
		LinearLayout ll_coffee=(LinearLayout)findViewById(R.id.ll_coffee);
		LinearLayout ll_topic=(LinearLayout)findViewById(R.id.ll_topic);
		LinearLayout ll_rent=(LinearLayout)findViewById(R.id.ll_enoikiaseis);
		LinearLayout ll_travel=(LinearLayout)findViewById(R.id.ll_tax_grafeia);
		LinearLayout ll_beauty=(LinearLayout)findViewById(R.id.ll_beauty);
		ll_beauty.setOnClickListener(this);	
		ll_travel.setOnClickListener(this);	
		ll_rent.setOnClickListener(this);	
		ll_topic.setOnClickListener(this);	
		LinearLayout ll_shoppingc=(LinearLayout)findViewById(R.id.ll_shopping);
		ll_shoppingc.setOnClickListener(this);	
		ll_coffee.setOnClickListener(this);	
		ll_fun.setOnClickListener(this);
		ll_food.setOnClickListener(this);	
		ll_hotels.setOnClickListener(this);		
		ll_destinations.setOnClickListener(this);
		ll_shites.setOnClickListener(this);
		events.setOnClickListener(this);
		students.setOnClickListener(this);
		info.setOnClickListener(this);
	}
	public void setLocale(String lang) {
	
		 myLocale = new Locale(lang);
		   
		    Locale.setDefault(myLocale);
		    android.content.res.Configuration config = new android.content.res.Configuration();
		    config.locale = myLocale;
		    getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
		    updateTexts();
    }
	
	private void updateTexts()
	{
	TextView hotel=(TextView)findViewById(R.id.tv_title_hotel);
	TextView food=(TextView)findViewById(R.id.tv_title_food);
	TextView fun=(TextView)findViewById(R.id.tv_title_fun);
	TextView coffee=(TextView)findViewById(R.id.tv_title_coffee);
	TextView topic=(TextView)findViewById(R.id.tv_title_topic);
	TextView rent_car=(TextView)findViewById(R.id.tv_title_enoikiaseis);
	TextView destinations=(TextView)findViewById(R.id.tv_title_destinations);
	TextView shites=(TextView)findViewById(R.id.tv_title_axiotheata);
	TextView travel_agencies=(TextView)findViewById(R.id.tv_title_travel_agencies);
	TextView shopping=(TextView)findViewById(R.id.tv_title_shopping);
	TextView beauty=(TextView)findViewById(R.id.tv_title_beauty);
	Button students=(Button)findViewById(R.id.btn_students);
	Button events=(Button)findViewById(R.id.btn_events);
	Button info=(Button)findViewById(R.id.btn_info);
	
	hotel.setText(R.string.hotels);
	food.setText(R.string.food);
	fun.setText(R.string.fun);
	coffee.setText(R.string.coffee);
	topic.setText(R.string.topic);
	rent_car.setText(R.string.rent_car);
	destinations.setText(R.string.destinations);	
	shites.setText(R.string.axiotheata);
	travel_agencies.setText(R.string.travel_agencies);
	shopping.setText(R.string.shopping);
	beauty.setText(R.string.Beauty);
	students.setText(R.string.students);
	events.setText(R.string.events);
	info.setText(R.string.info);

	}

	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.ll_hotel:
			Intent hotels=new Intent(this,Hotels.class);
			startActivity(hotels);
			
			break;
		case R.id.ll_proorismoi:
			Intent destinations=new Intent(this,Destinations.class);
			startActivity(destinations);
			
			break;
		case R.id.ll_axiotheata:
			Intent shites=new Intent(this,Shites.class);
			startActivity(shites);
			
			break;
		case R.id.ll_food:
			Intent food_int=new Intent(this,Food.class);
			startActivity(food_int);
			
			break;
		case R.id.ll_fun:
			Intent fun_int=new Intent(this,Fun.class);
			startActivity(fun_int);
			
			break;
		case R.id.ll_coffee:
			
			Intent cof_int=new Intent(this,Coffee_Sweets.class);
			startActivity(cof_int);
			
			break;
		case R.id.ll_topic:
			Intent top_int=new Intent(this,Local_products.class);
			startActivity(top_int);
			
			break;
		case R.id.ll_shopping:
			Intent shop_int=new Intent(this,Shopping.class);
			startActivity(shop_int);
			
			break;
		case R.id.ll_enoikiaseis:
			Intent rent_int=new Intent(this,Rent.class);
			startActivity(rent_int);
			
			break;
		case R.id.ll_tax_grafeia:
			Intent travel_int=new Intent(this,Travel_agenc.class);
			startActivity(travel_int);
			
			break;
		case R.id.ll_beauty:
			Intent beauty_int=new Intent(this,Beauty.class);
			startActivity(beauty_int);
			
			break;
		case R.id.btn_events:
			Intent events=new Intent(this,Events.class);
			startActivity(events);
			
			break;
		case R.id.btn_info:
			Intent info=new Intent(this,Info.class);
			startActivity(info);
			
			break;
		case R.id.btn_students:
			String party,Gram_thl,food,dialog_title,news,coffee;
			if(getResources().getConfiguration().locale.getLanguage().equals(("el"))) {
				 party="Εκδηλώσεις-Πάρτυ";
				 Gram_thl="Τηλέφωνα Γραμματείες";
				 food="Πρόγραμμα Σίτισης Φοιτητικού Εστιατορίου (Λέσχης)";
				 news="Τελευταίες Ανακοινώσεις";
				 coffee="Κυλικεία";
				 dialog_title="Επιλέξτε...";
			} else{
				 party="Events-Party";
				 Gram_thl="Secretaries Telephones";
				 food="Food schedule of student restaurant";
				 news="Latest News";	
				 coffee="Campus Coffee-shops";
				 dialog_title="Choose...";
				}
	        final String [] items={party,Gram_thl,food,news};
	     // custom dialog
	     			dialog = new Dialog(MainActivity.this);
	     			dialog.setContentView(R.layout.students_dialog);
	     			dialog.setTitle(dialog_title);
	      
	     			// set the custom dialog components - text, image and button
	     			TextView tv_party = (TextView) dialog.findViewById(R.id.tv_col_party);
	     			TextView tv_phones = (TextView) dialog.findViewById(R.id.tv_col_tel);
	     			TextView tv_food = (TextView) dialog.findViewById(R.id.tv_col_food);
	     			TextView tv_news = (TextView) dialog.findViewById(R.id.tv_col_news);
	     			TextView tv_coffee = (TextView) dialog.findViewById(R.id.tv_col_coffee);
	     			tv_coffee.setText(coffee);
	     			tv_party.setText(party);
	     			tv_phones.setText(Gram_thl);
	     			tv_food.setText(food);
	     			tv_news.setText(news);
	     			LinearLayout ll_party=(LinearLayout)dialog.findViewById(R.id.ll_party);
	     			ll_party.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							Intent party_in=new Intent(MainActivity.this,Events_college.class);
							startActivity(party_in);
							dialog.dismiss();
							
						}
					});
	     			LinearLayout ll_phones=(LinearLayout)dialog.findViewById(R.id.ll_sec_telephones);
	     			ll_phones.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							Intent phones=new Intent(MainActivity.this,Secretaries_phones.class);
							startActivity(phones);
							dialog.dismiss();
							
						}
					});
	     			LinearLayout ll_sitisi=(LinearLayout)dialog.findViewById(R.id.ll_sitisi);
	     			ll_sitisi.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							
							new LoadRecords().execute();
							dialog.dismiss();
						}
					});
	     			LinearLayout ll_news=(LinearLayout)dialog.findViewById(R.id.ll_news);
	     			ll_news.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							
							Intent news=new Intent(MainActivity.this,Uoi_News.class);
							startActivity(news);
							dialog.dismiss();
							
						}
					});
	     			LinearLayout ll_coffee=(LinearLayout)dialog.findViewById(R.id.ll_campus_coffee);
	     			ll_coffee.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							
							Intent coffee=new Intent(MainActivity.this,Campus_coffee.class);
							startActivity(coffee);
							dialog.dismiss();
							
						}
					});
	     			dialog.show();
			
			break;
		
		}
	}

	
public class LoadRecords extends AsyncTask<Void, Void, Boolean> {
		

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(MainActivity.this);
			pDialog.setMessage("Φόρτωση ...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		@Override
		protected Boolean doInBackground(Void... arg0) {
			updateJSONdata();
			
			return null;

		}

		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			pDialog.dismiss();
			if(con==false){
				Toast.makeText(getApplicationContext(), "Not available online", Toast.LENGTH_SHORT).show();
				String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
				 File folder = new File(extStorageDirectory, "UOI-sitisi");
				 //folder.mkdir();
				 file = new File(folder.getPath(), "programma_sitisis.pdf");
		        if(file.exists()==true)
		        {
		        	
		        	 Intent intent = new Intent(Intent.ACTION_VIEW);
		             intent.setDataAndType(Uri.fromFile(file), "application/pdf");
		             intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		             startActivity(intent);
		        	 
		        }else
		        {   
		        	Toast.makeText(getApplicationContext(), "Δεν υπάρχει κατεβασμένο το πρόγραμμα σίτισης στο κινητό σας!", Toast.LENGTH_SHORT).show();
		            
		        }
			}
			else{
				
			updateList();
				
				
				
			}
			
		}
	}
public void updateJSONdata() {
	// TODO Auto-generated method stub
	
	
	
	JSONParser jParser = new JSONParser();
	String url_to_parse=null;		
	url_to_parse=READ_URL;
	
	
	JSONObject json = jParser.getJSONFromUrl(url_to_parse);
	if (json!=null){			
	try {
		con=true;
	int success=json.getInt(TAG_SUCCESS);
	if(success==1){
	mRecords = json.getJSONArray(TAG_POSTS);	
	
		for (int i = 0; i < mRecords.length(); i++) {
			JSONObject c = mRecords.getJSONObject(i);
			
			String link = c.getString(TAG_LINK);
			/*DataSQL entry_delete= new DataSQL(Hotels.this);
			entry_delete.open();
			
			entry_delete.delete_Delivery("hotels");
			 entry_delete.close();*/
			
		
			url_sitisi=link;
		}

	} else{
		
		mRecordsList=null;
	}
	}catch (JSONException e) {
		e.printStackTrace();
		Toast mytoast=new Toast(MainActivity.this);
		
		
	}
	}else{
		con=false;
		
	}
	System.out.println("done");
	
}

public void updateList() {
	// TODO Auto-generated method stub
	if(url_sitisi.equals(""))
		Toast.makeText(getApplicationContext(), "Food schedule isn't available online", Toast.LENGTH_SHORT).show();
	else{
	downloadfile task=new downloadfile();
	
	task.execute(url_sitisi);}
		
			
}

//code for downloading th pdf file
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
				        folder.mkdir();
				        file = new File(folder, "programma_sitisis.pdf");
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
		pDialog = new ProgressDialog(MainActivity.this);
		pDialog.setMessage("downloading...");
		pDialog.setIndeterminate(false);
		pDialog.setCancelable(true);
	
		pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		pDialog.show();
		pDialog.getWindow().setGravity(Gravity.BOTTOM);
	}
	
}

 

}
