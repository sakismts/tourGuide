package com.mts.grtourguidioannina;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Bitmap.CompressFormat;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.google.android.gms.maps.model.LatLng;
import com.mts.grtourguidioannina.Food.LoadRecords;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.utils.StorageUtils;

public class Fun extends Activity {
	String result_from_signel_record="none";
	boolean single_record_clicked=false;
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	//int item_counter;
	private static final String READ_URL = "http://www.greek-tour-guides.eu/ioannina/ioannina_gr_tour_app/fun/fun.php";
	String filter_location="all",filter_tag="all", filter_subcat="all";

	private static final String TAG_POSTS = "posts";
	private static final String TAG_SUCCESS = "success";
	
	private static final String TAG_NAME = "name";
	private static final String TAG_NAME_en = "name_en";
	private static final String TAG_ADDRESS = "address";
	private static final String TAG_ADDRESS_en = "address_en";
	private static final String TAG_CONTENT = "content";
	private static final String TAG_CONTENT_en = "content_en";
	private static final String TAG_IMAGE = "image";	
	private static final String TAG_TEL1 = "tel1";
	private static final String TAG_TEL2 = "tel2";
	private static final String TAG_TEL3 = "tel3";
	private static final String TAG_IMG1 = "img1";
	private static final String TAG_IMG2 = "img2";
	private static final String TAG_IMG3 = "img3";		
	private static final String TAG_WEB = "web";
	private static final String TAG_EMAIL = "email";
	private static final String TAG_LAT = "latitude";
	private static final String TAG_LNG = "longitude";
	private static final String TAG_CATEGORY = "category";
	private static final String TAG_TAGS = "tags";
	private static final String TAG_ID = "ID";
	private static final String TAG_LOCATION = "location";
	private static final String TAG_LOCATION_en = "location_en";
	private static final String TAG_URL_ATTACH = "url_attach";
	private static final String TAG_VISIBLE = "Visible";
	
	
	private JSONArray mRecords = null;	
	private ArrayList<ListViewItem> mRecordsList;
	private ArrayList<ListViewItem> mRecordsList_original;
	private ArrayList<String> LocationList_en;
	private ArrayList<String> LocationList_el;
	String [] items_location;
	private ProgressDialog pDialog;
	private Boolean con=false;
	EditText search;
	ListView recordlist;
	Locale current;
	Bitmap tmp_photo;
	
	//location 
	LatLng myLatLng = new LatLng(39.667292, 20.882581);
	Double mylatitude, myLongitude;
	LocationManager locationManager;
	Location mylocation;
	String provider;
	LocationListener locationListener;
	CustomListViewAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.category_list);
		//item_counter=0;
		// UNIVERSAL IMAGE LOADER SETUP
		File cacheDir = StorageUtils.getCacheDirectory(getApplicationContext());
		
		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
		.cacheOnDisc(true).cacheInMemory(false)				
		.showImageOnLoading(R.drawable.ic_launcher_large)
		.imageScaleType(ImageScaleType.IN_SAMPLE_INT)
		.displayer(new FadeInBitmapDisplayer(300)).build();

ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
.defaultDisplayImageOptions(defaultOptions)
.memoryCacheSize(2 * 1024 * 1024)
.diskCache(new UnlimitedDiscCache(cacheDir)) // default

.build();
		/*ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				getApplicationContext()).threadPoolSize(5)
				.defaultDisplayImageOptions(defaultOptions)
	            .threadPriority(Thread.MIN_PRIORITY + 3)
	            .denyCacheImageMultipleSizesInMemory()
	            // .memoryCache(new UsingFreqLimitedMemoryCache(2000000)) // You
	            // can pass your own memory cache implementation
	            .memoryCacheSize(1048576 * 10)
	            // 1MB=1048576 *declare 20 or more size if images are more than
	            // 200
	            .discCache(new UnlimitedDiscCache(cacheDir))
	            // You can pass your own disc cache implementation
	            // .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
	            .build();*/

		

ImageLoader.getInstance().init(config);

	TextView label_search=(TextView)findViewById(R.id.tv_filter_location);
		TextView Cat=(TextView)findViewById(R.id.tv_cat_label);
		current = getResources().getConfiguration().locale;
		search=(EditText)findViewById(R.id.edt_search);
		
		
		if(current.getLanguage().equals(("el"))){
			Cat.setText("Διασκέδαση");
			search.setHint("Αναζήτηση...");
			
			label_search.setText(">>"+"Όλες οι τοποθεσίες");
			}
		else{
			Cat.setText("Fun");
			search.setHint("type to search");
			
			label_search.setText(">>"+"All locations");
			}
		
		
		
		new LoadRecords().execute();
		
		//find the location//
		locationManager = (LocationManager) this
				.getSystemService(Context.LOCATION_SERVICE);
		// Define a listener that responds to location updates
		Criteria criteria = new Criteria();
		String provider = locationManager.getBestProvider(criteria, true);
		locationListener = new LocationListener() {
			public void onLocationChanged(Location location) {
				// Called when a new location is found by the network location
				// provider.
				System.out.println("location changed");
				
				mylocation = location;

				if (mylocation != null) {
					
					mylatitude = mylocation.getLatitude();
					myLongitude = mylocation.getLongitude();
					//setDataToList();
				}
				ChangetheDistances();
				
				locationManager.removeUpdates(locationListener);

				// }
			}
		
			public void onStatusChanged(String provider, int status,
					Bundle extras) {
			}

			public void onProviderEnabled(String provider) {
			}

			public void onProviderDisabled(String provider) {
			}
		};
 
		mylocation = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
		if(mylocation==null){
		mylatitude = myLatLng.latitude;
		myLongitude = myLatLng.longitude;
		}else{
			mylatitude=mylocation.getLatitude();
			myLongitude=mylocation.getLongitude();
			
		}
		
		ImageButton back_home=(ImageButton)findViewById(R.id.im_home);
		back_home.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Intent m_acticity=new Intent(Hotels.this, MainActivity.class);
				//startActivity(m_acticity);
				finish();
			}
		});
		
	}
	
	protected void ChangetheDistances() {
		// TODO Auto-generated method stub
		if(adapter!=null){
			
			
			for(int i=0;i <mRecordsList.size();i++){
				
				LatLng tmp_itemloc=new LatLng(Double.parseDouble(mRecordsList.get(i).lat), Double.parseDouble(mRecordsList.get(i).lng));	
			//	System.out.println(tmp_item.lat);
				//tmp_item.distance=returnDistance(tmp_itemloc).toString();
				mRecordsList.get(i).distance=returnDistance(tmp_itemloc).toString();
				System.out.println(mRecordsList.get(i).distance);
			}
			Collections.sort(mRecordsList, new Comparator<ListViewItem>() {

				public int compare(ListViewItem o1, ListViewItem o2) {
					Double one=0.0;
					Double two=0.0;					
					one=Double.parseDouble(o1.distance);
					two=Double.parseDouble(o2.distance);
					if (one.compareTo(two) < 0)
						return -1;
					else if (one.compareTo(two) > 0)
						return 1;
					else
						return 0;
				}

			});
			
			//adapter.clearItems();
			adapter.notifyDataSetChanged();
			recordlist.setAdapter(adapter);
			
		}
	}

	private Double returnDistance(LatLng newlocation) {
		// TODO Auto-generated method stub

		 float[] distanceResults=new float[3];
		    //if(mylocation!=null){
		    Location.distanceBetween(mylatitude, myLongitude,newlocation.latitude , newlocation.longitude, distanceResults);
		   // }else{
		    //	distanceResults[0]=0;	
		   // }
		    float myfloat=distanceResults[0]/1000;	  
		    Double s=(double)myfloat;
		    
		   // NumberFormat nf = NumberFormat.getInstance(); // get instance
		  //  nf.setMaximumFractionDigits(2); // set decimal places
		   // String s = nf.format(myfloat)+"Km";
		    DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
		    symbols.setDecimalSeparator('.');
		    DecimalFormat format = new DecimalFormat("#.##", symbols);
		   s= Double.valueOf(format.format(s));


		return s;
		}
	
public class LoadRecords extends AsyncTask<Void, Void, Boolean> {
		

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Fun.this);
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
				Toast.makeText(getApplicationContext(), "Δεν υπάρχει σύνδεση στο διαδίκτυο", Toast.LENGTH_SHORT).show();
				DataSQL info=new DataSQL(Fun.this);
				
				info.open();
				
				mRecordsList=info.getItems(mRecordsList,"fun");
				
				info.close();
				for(int i=0;i<mRecordsList.size();i++){
					LocationList_en.add(mRecordsList.get(i).location_en);
					LocationList_el.add(mRecordsList.get(i).location);
					
				}
				
				recordlist=(ListView)findViewById(R.id.listView1);
				adapter = new CustomListViewAdapter(Fun.this, mRecordsList,current,false);
				
				Collections.sort(mRecordsList, new Comparator<ListViewItem>() {

					public int compare(ListViewItem o1, ListViewItem o2) {
						
						
						 int res = String.CASE_INSENSITIVE_ORDER.compare(o1.name_en, o2.name_en); 
					        return res;
					}

				});
				
			
				adapter.notifyDataSetChanged();
				
				recordlist.setAdapter(adapter);
				
				recordlist.setTextFilterEnabled(true);
				search.setOnTouchListener(new OnTouchListener(){

						@Override
						public boolean onTouch(View arg0, MotionEvent arg1) {
							// TODO Auto-generated method stub
							search.setFocusable(true);
							search.setFocusableInTouchMode(true);
							return false;
						}});
				
				search.addTextChangedListener(new TextWatcher() {
					
					@Override
					public void onTextChanged(CharSequence s, int start, int before, int count) {
						// TODO Auto-generated method stub
						
						//recordlist.setFilterText(s.toString());
					}
					
					@Override
					public void beforeTextChanged(CharSequence s, int start, int count,
							int after) {
						// TODO Auto-generated method stub
						//recordlist.setFilterText(s.toString());
					}
					
					@Override
					public void afterTextChanged(Editable s) {
						// TODO Auto-generated method stub
					
						String tmp=s.toString().trim();						
						if(tmp.length()==0){						
						//adapter.getFilter().filter("all");						
						//search.setFocusable(false);
						//search.setFocusableInTouchMode(false);
							filter_tag="all";						
						adapter.general_Filter(filter_location).filter(filter_tag);
						
						}
						else{
						
							filter_tag=tmp;	
							//adapter.general_Filter(filter_location).filter(filter_tag);
							adapter.general_Filter(filter_location).filter(filter_tag);
						}
							
						
						
						
					}
				});
				ImageButton filter=(ImageButton)findViewById(R.id.img_btn_filter);
				filter.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						
						
						// TODO Auto-generated method stub
						
					    AlertDialog.Builder builder = new AlertDialog.Builder(Fun.this);
					    if(current.getLanguage().equals(("el"))){
				        builder.setTitle("Διάλεξε Περιοχή");
				        LocationList_el.add("Όλα");
				        
				        
						 LinkedHashSet<String> listToSet = new LinkedHashSet<String>(LocationList_el);
						 List<String> listWithoutDuplicates = new ArrayList<String>(listToSet);
						
				        
				        //final String [] items={"Όλα","Ιωάννινα","Νεοχωρόπουλο","Πέραμα","Επισκοπικό","Μέτσοβο","Ζαγοροχώρια","Πράμαντα"};
						items_location=new String[listWithoutDuplicates.size()];
						 items_location=listWithoutDuplicates.toArray(items_location);
				        builder.setItems(items_location, new DialogInterface.OnClickListener() {
				            public void onClick(DialogInterface dialog, int item) {
				                // Do something with the selection
				            	
				            	
				            	 if(items_location[item].equals("Όλα")){			            	
						            	
						              	filter_location="all";						            	
						            	adapter.general_Filter(filter_location).filter(filter_tag);
						            }
						            else{						            	
						            	
						            	//adapter.general_Filter(items_location[item]).filter(filter_tag);
						            	filter_location=items_location[item];
						            	adapter.general_Filter(filter_location).filter(filter_tag);
						            }
							TextView label_search=(TextView)findViewById(R.id.tv_filter_location);
							label_search.setText(">>"+items_location[item]);
				               
				            }
				        });
				        }else{
				        	builder.setTitle("Choose a location");
				        	LocationList_en.add("All"); 
				        	
				        	 
							 LinkedHashSet<String> listToSet = new LinkedHashSet<String>(LocationList_en);
							 List<String> listWithoutDuplicates = new ArrayList<String>(listToSet);
							 
							 items_location=new String[listWithoutDuplicates.size()];
							 items_location=listWithoutDuplicates.toArray(items_location);
				        	//final String [] items={"All","Ioannina","Metsovo","Perama","Episkopiko","Zagoroxwria","Pramanta"};	
				        	   builder.setItems(items_location, new DialogInterface.OnClickListener() {
						            public void onClick(DialogInterface dialog, int item) {
						                // Do something with the selection
						            	
						            	if(items_location[item].equals("All")){			            	
							            	//adapter.filter_location().filter("all");
						            	//	adapter.general_Filter("all").filter(filter_tag);
							            	filter_location="all";	
							            	adapter.general_Filter(filter_location).filter(filter_tag);
						            	}
							            else{
							            	//adapter.filter_location().filter(items_location[item]);
							            	//adapter.general_Filter(items_location[item]).filter(filter_tag);
							            	filter_location=items_location[item];	
							            	adapter.general_Filter(filter_location).filter(filter_tag);
							            }
										TextView label_search=(TextView)findViewById(R.id.tv_filter_location);
										label_search.setText(">>"+items_location[item]);
						            }
						        });
				        }
				     
				        AlertDialog alert = builder.create();
				        alert.show();
						
					}
				});
				
				recordlist.setOnItemClickListener(new OnItemClickListener() {
					
					

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						// TODO Auto-generated method stub
						// System.out.println(mNewsList.get(0).get(TAG_MESSAGE));
						Intent post=new Intent(Fun.this,SingleRecord.class);
						post.putExtra("name", adapter.items.get(position).name);
						post.putExtra("address", adapter.items.get(position).address);
						post.putExtra("content", adapter.items.get(position).content);
						post.putExtra("name_en", adapter.items.get(position).name_en);
						post.putExtra("address_en", adapter.items.get(position).address_en);
						post.putExtra("content_en", adapter.items.get(position).content_en);
						
						post.putExtra("tel1", adapter.items.get(position).tel1);
						post.putExtra("tel2", adapter.items.get(position).tel2);
						post.putExtra("tel3", adapter.items.get(position).tel3);
						
						post.putExtra("img1", adapter.items.get(position).img1);
						post.putExtra("img2", adapter.items.get(position).img2);
						post.putExtra("img3", adapter.items.get(position).img3);		
						post.putExtra("web", adapter.items.get(position).web);
						post.putExtra("email", adapter.items.get(position).email);
						post.putExtra("mylat",adapter.items.get(position).lat);
						post.putExtra("mylng",adapter.items.get(position).lng);
						post.putExtra("current_lat",mylatitude.toString());
						post.putExtra("current_lng",myLongitude.toString());
						post.putExtra("connection", false);
						post.putExtra("image", adapter.items.get(position).thumbnailResource);
						post.putExtra("language", current.toString());
						post.putExtra("url_attach", adapter.items.get(position).url_attach);
						
						ByteArrayOutputStream stream = new ByteArrayOutputStream();
						adapter.items.get(position).image.compress(Bitmap.CompressFormat.JPEG, 30, stream);
						
				        byte[] bytes = stream.toByteArray(); 
				       
				        post.putExtra("BMP",bytes);
						//post.putExtra("bmp_image", mNewsList.get(position).image);
						//post.putExtra("image", mNewsList.get(position).thumbnailResource);
						
						//startActivity(post);
				        single_record_clicked=true;
				        startActivityForResult(post, 1);
						
						
					}
				});
			}
			else{
			updateList();
				
				
				
			}
			
			
			
		}
		
		
	}

	public void updateJSONdata() {
		// TODO Auto-generated method stub
		
		//mNewsList = new ArrayList<HashMap<String, String>>();
		mRecordsList = new ArrayList<ListViewItem>();
		mRecordsList_original = new ArrayList<ListViewItem>();
		LocationList_en=new ArrayList<String>();
		LocationList_el=new ArrayList<String>();
		
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
			System.out.println(mRecords);
			
			for (int i = 0; i < mRecords.length(); i++) {
				JSONObject c = mRecords.getJSONObject(i);
				String id=c.getString(TAG_ID);
				String name = c.getString(TAG_NAME);
				String name_en = c.getString(TAG_NAME_en);
				String image = c.getString(TAG_IMAGE);
				String address = c.getString(TAG_ADDRESS);
				String address_en = c.getString(TAG_ADDRESS_en);
				String content = c.getString(TAG_CONTENT);
				String content_en = c.getString(TAG_CONTENT_en);	
				String tel1 = c.getString(TAG_TEL1);
				String tel2 = c.getString(TAG_TEL2);
				String tel3 = c.getString(TAG_TEL3);				
				String web = c.getString(TAG_WEB);
				String email = c.getString(TAG_EMAIL);
				String Cat = c.getString(TAG_CATEGORY);
				String lat = c.getString(TAG_LAT);
				String lng = c.getString(TAG_LNG);
				String img1 = c.getString(TAG_IMG1);
				String img2 = c.getString(TAG_IMG2);
				String img3 = c.getString(TAG_IMG3);
				String tags = c.getString(TAG_TAGS);
				String location = c.getString(TAG_LOCATION);
				String location_en = c.getString(TAG_LOCATION_en);
				String url_attach = c.getString(TAG_URL_ATTACH);
				
				
				String visible=c.getString(TAG_VISIBLE);
				
				
				
				if(Integer.parseInt(visible)>0){//check about the visibility of each record from online database
				
				
				ListViewItem item = new ListViewItem();
				
				
				item.thumbnailResource=image;
				item.id=id;
				item.name=name;
				item.address=address;
				item.location=location;
				item.location_en=location_en;
				item.content=content;
				item.category=Cat;
				item.tel1=tel1;
				item.tel2=tel2;
				item.tel3=tel3;
				item.img1=img1;
				item.img2=img2;
				item.img3=img3;
				item.web=web;
				item.email=email;
				item.lat=lat;
				item.lng=lng;
				item.tags=tags;
				item.name_en=name_en;
				item.address_en=address_en;
				item.content_en=content_en;
				item.url_attach=url_attach;
				Double tmp_latitude=Double.valueOf(lat);
				Double tmp_longitude=Double.valueOf(lng);
				LatLng tmp_itemloc=new LatLng(tmp_latitude, tmp_longitude);
				item.distance=returnDistance(tmp_itemloc).toString();
				LocationList_en.add(location_en);
				LocationList_el.add(location);
				
			
			
				mRecordsList.add(item);
				mRecordsList_original.add(item);

				}
			}
			
			

		} else{
			
			mRecordsList=null;
		}
		}catch (JSONException e) {
			e.printStackTrace();
			Toast mytoast=new Toast(Fun.this);
			
			
		}
		}else{
			con=false;
			
		}
		
	}

	public void updateList() {
		// TODO Auto-generated method stub
		recordlist=(ListView)findViewById(R.id.listView1);
		
	
		if(mRecordsList==null){
			recordlist.setAdapter(null);	
			Toast.makeText(getApplicationContext(), "Δεν υπάρχουν εγγραφές σε αυτήν την κατηγορία", Toast.LENGTH_SHORT).show();
		}else{
		
				adapter = new CustomListViewAdapter(this, mRecordsList,current,false);
		
			
				recordlist.setAdapter(adapter);
				DataSQL entry_delete= new DataSQL(Fun.this);
				entry_delete.open();				
				entry_delete.delete_Delivery("fun");			
				 entry_delete.close();
				 for (int i = 0; i < mRecordsList_original.size(); i++) {
				 
					 System.out.println(mRecordsList_original.get(i).name);
					 System.out.println(mRecordsList_original.get(i).thumbnailResource);
				 new DownloadImageTask(i).execute(mRecordsList_original.get(i).thumbnailResource);
				
				
				 mRecordsList_original.get(i).image=tmp_photo;
				 
				 }
				
				
				recordlist.setTextFilterEnabled(true);
				search.setOnTouchListener(new OnTouchListener(){

						@Override
						public boolean onTouch(View arg0, MotionEvent arg1) {
							// TODO Auto-generated method stub
							search.setFocusable(true);
							search.setFocusableInTouchMode(true);
							return false;
						}});
				
				search.addTextChangedListener(new TextWatcher() {
					
					@Override
					public void onTextChanged(CharSequence s, int start, int before, int count) {
						// TODO Auto-generated method stub
						
						//recordlist.setFilterText(s.toString());
					}
					
					@Override
					public void beforeTextChanged(CharSequence s, int start, int count,
							int after) {
						// TODO Auto-generated method stub
						//recordlist.setFilterText(s.toString());
					}
					
					@Override
					public void afterTextChanged(Editable s) {
						// TODO Auto-generated method stub
					
						String tmp=s.toString().trim();						
						if(tmp.length()==0){						
						//adapter.getFilter().filter("all");						
						//search.setFocusable(false);
						//search.setFocusableInTouchMode(false);
							filter_tag="all";						
						adapter.general_Filter(filter_location).filter(filter_tag);
						
						}
						else{
						
							filter_tag=tmp;	
							//adapter.general_Filter(filter_location).filter(filter_tag);
							adapter.general_Filter(filter_location).filter(filter_tag);
						}
							
						
						
						
					}
				});
				ImageButton filter=(ImageButton)findViewById(R.id.img_btn_filter);
				filter.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						
						
						// TODO Auto-generated method stub
						
					    AlertDialog.Builder builder = new AlertDialog.Builder(Fun.this);
					    if(current.getLanguage().equals(("el"))){
				        builder.setTitle("Διάλεξε Περιοχή");
				        LocationList_el.add("Όλα");
				        System.out.println(LocationList_el);
				        
						 LinkedHashSet<String> listToSet = new LinkedHashSet<String>(LocationList_el);
						 List<String> listWithoutDuplicates = new ArrayList<String>(listToSet);
						 System.out.println(listWithoutDuplicates);
				        
				        //final String [] items={"Όλα","Ιωάννινα","Νεοχωρόπουλο","Πέραμα","Επισκοπικό","Μέτσοβο","Ζαγοροχώρια","Πράμαντα"};
						items_location=new String[listWithoutDuplicates.size()];
						 items_location=listWithoutDuplicates.toArray(items_location);
				        builder.setItems(items_location, new DialogInterface.OnClickListener() {
				            public void onClick(DialogInterface dialog, int item) {
				                // Do something with the selection
				            	
				            	
				            	if(items_location[item].equals("Όλα")){			            	
					            	
					              	filter_location="all";						            	
					            	adapter.general_Filter(filter_location).filter(filter_tag);
					            }
					            else{						            	
					            	
					            	//adapter.general_Filter(items_location[item]).filter(filter_tag);
					            	filter_location=items_location[item];
					            	adapter.general_Filter(filter_location).filter(filter_tag);
					            }
							TextView label_search=(TextView)findViewById(R.id.tv_filter_location);
							label_search.setText(">>"+items_location[item]);
				               
				            }
				        });
				        }else{
				        	builder.setTitle("Choose a location");
				        	LocationList_en.add("All"); 
				        	System.out.println(LocationList_en);
				        	 
							 LinkedHashSet<String> listToSet = new LinkedHashSet<String>(LocationList_en);
							 List<String> listWithoutDuplicates = new ArrayList<String>(listToSet);
							 System.out.println(listWithoutDuplicates); 
							 items_location=new String[listWithoutDuplicates.size()];
							 items_location=listWithoutDuplicates.toArray(items_location);
				        	//final String [] items={"All","Ioannina","Metsovo","Perama","Episkopiko","Zagoroxwria","Pramanta"};	
				        	   builder.setItems(items_location, new DialogInterface.OnClickListener() {
						            public void onClick(DialogInterface dialog, int item) {
						                // Do something with the selection
						            	
						            	if(items_location[item].equals("All")){			            	
							            	//adapter.filter_location().filter("all");
						            	//	adapter.general_Filter("all").filter(filter_tag);
							            	filter_location="all";	
							            	adapter.general_Filter(filter_location).filter(filter_tag);
						            	}
							            else{
							            	//adapter.filter_location().filter(items_location[item]);
							            	//adapter.general_Filter(items_location[item]).filter(filter_tag);
							            	filter_location=items_location[item];	
							            	adapter.general_Filter(filter_location).filter(filter_tag);
							            }
										TextView label_search=(TextView)findViewById(R.id.tv_filter_location);
										label_search.setText(">>"+items_location[item]);
						            }
						        });
				        }
				     
				        AlertDialog alert = builder.create();
				        alert.show();
						
					}
				});
				
				recordlist.setOnItemClickListener(new OnItemClickListener() {			
			

			
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent post=new Intent(Fun.this,SingleRecord.class);
				
				post.putExtra("name", adapter.items.get(position).name);
				post.putExtra("address", adapter.items.get(position).address);
				post.putExtra("content", adapter.items.get(position).content);
				post.putExtra("name_en", adapter.items.get(position).name_en);
				post.putExtra("address_en", adapter.items.get(position).address_en);
				post.putExtra("content_en", adapter.items.get(position).content_en);
				
				post.putExtra("tel1", adapter.items.get(position).tel1);
				post.putExtra("tel2", adapter.items.get(position).tel2);
				post.putExtra("tel3", adapter.items.get(position).tel3);
				
				post.putExtra("img1", adapter.items.get(position).img1);
				post.putExtra("img2", adapter.items.get(position).img2);
				post.putExtra("img3", adapter.items.get(position).img3);		
				post.putExtra("web", adapter.items.get(position).web);
				post.putExtra("email", adapter.items.get(position).email);
				post.putExtra("mylat",adapter.items.get(position).lat);
				post.putExtra("mylng",adapter.items.get(position).lng);
				post.putExtra("current_lat",mylatitude.toString());
				post.putExtra("current_lng",myLongitude.toString());
				post.putExtra("connection", true);
				post.putExtra("image", adapter.items.get(position).thumbnailResource);
				post.putExtra("language", current.toString());
				post.putExtra("distance", adapter.items.get(position).distance);
				post.putExtra("url_attach", adapter.items.get(position).url_attach);
				startActivity(post);
				
			}
		});
		
				
		}
		//Location changes after the listview was displayed
		if (locationManager.isProviderEnabled(locationManager.NETWORK_PROVIDER) == true) {
			
			locationManager.requestLocationUpdates(
					locationManager.NETWORK_PROVIDER, 0, 0, locationListener);
			mylocation = locationManager
					.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
			if (mylocation == null) {
				
				mylatitude = myLatLng.latitude;
				myLongitude = myLatLng.longitude;

			} else {

				mylatitude = mylocation.getLatitude();
				myLongitude = mylocation.getLongitude();
			}
			

		} else {
			if (provider==null){
				   startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
				  }else{
					  Toast.makeText(getApplicationContext(),
								"The Network provider is off", Toast.LENGTH_LONG).show();
					  }
				  }
	}
	


	/*private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
		 int item_counter;
		 ByteArrayOutputStream stream;
	public DownloadImageTask(int a){
		this.item_counter=a;
		
	}
			  protected Bitmap doInBackground(String... urls) {
				
			      String urldisplay = urls[0];
			    
			     
			      Bitmap bmp = imageLoader.loadImageSync(mRecordsList.get(item_counter).thumbnailResource);
			      if(bmp==null)
			    	  bmp=BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_large);
			      stream=new ByteArrayOutputStream();
			      bmp= getResizedBitmap(bmp,300,450);
			     // bmp= getResizedBitmap(bmp,267,400);
			      bmp.compress(CompressFormat.JPEG,30, stream);	
			     // bmp=Bitmap.createScaledBitmap(bmp, 400, 267, false);	     
			      return bmp;
				
				  
			  }

			  protected void onPostExecute(Bitmap result) {
				  
				  tmp_photo=result;
				
			     
			     
					 DataSQL entry= new DataSQL(Hotels.this);
				        entry.open();	        
				        
				        
				        
				        
				        if(tmp_photo==null){
				        	tmp_photo = BitmapFactory.decodeResource(Hotels.this.getResources(),R.drawable.ic_launcher);	
				        }
				        
				        byte[] bArray = stream.toByteArray();
				        
				      
				       
				        
				       // entry.createEntry(mRecordsList.get(item_counter).name, mRecordsList.get(item_counter).address, mRecordsList.get(item_counter).name_en, mRecordsList.get(item_counter).address_en,mRecordsList.get(item_counter).content_en,bArray,mRecordsList.get(item_counter).content,mRecordsList.get(item_counter).tel1,mRecordsList.get(item_counter).tel2,mRecordsList.get(item_counter).tel3,mRecordsList.get(item_counter).web,mRecordsList.get(item_counter).email,mRecordsList.get(item_counter).lat,mRecordsList.get(item_counter).lng,mRecordsList.get(item_counter).location,mRecordsList.get(item_counter).location_en,mRecordsList.get(item_counter).tags,mRecordsList.get(item_counter).category);
				        entry.createEntry_Hotels(mRecordsList.get(item_counter).name, mRecordsList.get(item_counter).address, mRecordsList.get(item_counter).name_en, mRecordsList.get(item_counter).address_en,mRecordsList.get(item_counter).content_en,bArray,mRecordsList.get(item_counter).content,mRecordsList.get(item_counter).tel1,mRecordsList.get(item_counter).tel2,mRecordsList.get(item_counter).tel3,mRecordsList.get(item_counter).web,mRecordsList.get(item_counter).email,mRecordsList.get(item_counter).lat,mRecordsList.get(item_counter).lng,mRecordsList.get(item_counter).location,mRecordsList.get(item_counter).location_en,mRecordsList.get(item_counter).tags,mRecordsList.get(item_counter).category,mRecordsList.get(item_counter).stars);
				        
				       
				        
				        entry.close();
			  }
			}*/
	private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
		 int item_counter;
		 ByteArrayOutputStream stream;
		 Bitmap bmp;
	public DownloadImageTask(int a){
		this.item_counter=a;
		
	}
			  protected Bitmap doInBackground(String... urls) {
				
			      String urldisplay = urls[0];
			      bmp=null;
			      
			      System.out.println("the url is "+urldisplay);
			      Bitmap bmp = imageLoader.loadImageSync(urldisplay);
			      
			     /*imageLoader.loadImage(urls[0], new ImageLoadingListener() {
					
					@Override
					public void onLoadingStarted(String arg0, View arg1) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onLoadingComplete(String arg0, View arg1, Bitmap arg2) {
						// TODO Auto-generated method stub
						 bmp =arg2;
						 
						 if(bmp==null)
					    	  bmp=BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_large);
					      stream=new ByteArrayOutputStream();
					      bmp= getResizedBitmap(bmp,300,450);
					     // bmp= getResizedBitmap(bmp,267,400);
					      bmp.compress(CompressFormat.JPEG,30, stream);	
						 
						 
						 tmp_photo=bmp;
							
					     
					     
						 DataSQL entry= new DataSQL(Fun.this);
					        entry.open();	        
					        
					        
					        
					        
					        if(tmp_photo==null){
					        	tmp_photo = BitmapFactory.decodeResource(Fun.this.getResources(),R.drawable.ic_launcher);	
					        }
					        
					        byte[] bArray = stream.toByteArray();
					      
					       
					        
					        entry.createEntry(mRecordsList_original.get(item_counter).name, mRecordsList_original.get(item_counter).address, mRecordsList_original.get(item_counter).name_en, mRecordsList_original.get(item_counter).address_en,mRecordsList_original.get(item_counter).content_en,bArray,mRecordsList_original.get(item_counter).content,mRecordsList_original.get(item_counter).tel1,mRecordsList_original.get(item_counter).tel2,mRecordsList_original.get(item_counter).tel3,mRecordsList_original.get(item_counter).web,mRecordsList_original.get(item_counter).email,mRecordsList_original.get(item_counter).lat,mRecordsList_original.get(item_counter).lng,mRecordsList_original.get(item_counter).location,mRecordsList_original.get(item_counter).location_en,mRecordsList_original.get(item_counter).tags,mRecordsList_original.get(item_counter).category,mRecordsList_original.get(item_counter).url_attach);
					       
					        System.out.println("insert");
					       
					        
					        entry.close();
					}
					
					@Override
					public void onLoadingCancelled(String arg0, View arg1) {
						// TODO Auto-generated method stub
						
					}
				});*/
			   
			      if(bmp==null)
			    	  bmp=BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_large);
			      stream=new ByteArrayOutputStream();
			      bmp= getResizedBitmap(bmp,300,450);
			     // bmp= getResizedBitmap(bmp,267,400);
			      bmp.compress(CompressFormat.JPEG,30, stream);	
				 
				 
				 tmp_photo=bmp;
					
			     
			     
				 DataSQL entry= new DataSQL(Fun.this);
			        entry.open();	        
			        
			        
			        
			        
			        if(tmp_photo==null){
			        	tmp_photo = BitmapFactory.decodeResource(Fun.this.getResources(),R.drawable.ic_launcher);	
			        }
			        
			        byte[] bArray = stream.toByteArray();
			      
			       
			        
			        entry.createEntry(mRecordsList_original.get(item_counter).name, mRecordsList_original.get(item_counter).address, mRecordsList_original.get(item_counter).name_en, mRecordsList_original.get(item_counter).address_en,mRecordsList_original.get(item_counter).content_en,bArray,mRecordsList_original.get(item_counter).content,mRecordsList_original.get(item_counter).tel1,mRecordsList_original.get(item_counter).tel2,mRecordsList_original.get(item_counter).tel3,mRecordsList_original.get(item_counter).web,mRecordsList_original.get(item_counter).email,mRecordsList_original.get(item_counter).lat,mRecordsList_original.get(item_counter).lng,mRecordsList_original.get(item_counter).location,mRecordsList_original.get(item_counter).location_en,mRecordsList_original.get(item_counter).tags,mRecordsList_original.get(item_counter).category,mRecordsList_original.get(item_counter).url_attach);
			       
			        System.out.println("insert");
			       
			        
			        entry.close();
			      return bmp;
				
				  
			  }

			  protected void onPostExecute(Bitmap result) {			 
			       
			        
				
			  }
			}
	

	
	public static Bitmap getResizedBitmap(Bitmap image, int newHeight, int newWidth) {
	    int width = image.getWidth();
	    int height = image.getHeight();
	    float scaleWidth = ((float) newWidth) / width;
	   
	    float scaleHeight = ((float) newHeight) / height;
	    
	    // create a matrix for the manipulation
	    Matrix matrix = new Matrix();
	    // resize the bit map
	    matrix.postScale(scaleWidth, scaleHeight);///matrix.postScale(scaleWidth, scaleHeight); normally is this.But if you want to keep stable one from two dimensions yout put only one value 
	    // recreate the new Bitmap
	    Bitmap resizedBitmap = Bitmap.createBitmap(image, 0, 0, width, height,
	            matrix, false);
	    return resizedBitmap;
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(result_from_signel_record.equals("single_record")){
			recordlist.setAdapter(adapter);
			single_record_clicked=false;
		}
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1) {
	        if(resultCode == RESULT_OK){
	            result_from_signel_record=data.getStringExtra("state");
	            
	        }
	        if (resultCode == RESULT_CANCELED) {
	            //Write your code if there's no result
	        }
	    }
	}
	
	@Override
	public void onBackPressed() {
	   // super.onBackPressed();		
		finish();
		
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		System.out.println("isa finishing...");
		if(recordlist!=null)
			recordlist.setAdapter(null);
			if(adapter!=null)
			adapter.clearItems();
	}
}
