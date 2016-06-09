package com.mts.grtourguidioannina;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class DataSQL {
	public static final String KEY_ROWID="_id";
	public static final String KEY_NAME="_name";
	public static final String KEY_ADDRESS="_address";
	public static final String KEY_IMAGE="_image";
	public static final String KEY_CONTENT="_content";
	public static final String KEY_TEL1="_tel1";
	public static final String KEY_TEL2="_tel2";
	public static final String KEY_TEL3="_tel3";	
	public static final String KEY_WEB="_web";
	public static final String KEY_EMAIL="_email";
	public static final String KEY_LAT="_lat";
	public static final String KEY_LNG="_lng";
	public static final String KEY_CAT="_cat";
	public static final String KEY_NAME_EN="_name_en";
	public static final String KEY_ADDRESS_EN="_address_en";
	public static final String KEY_CONTENT_EN="_content_en";
	public static final String KEY_LOCATION="_location";
	public static final String KEY_LOCATION_EN="_location_en";
	public static final String KEY_TAGS="_tags";
	public static final String KEY_DATE="_date";
	public static final String KEY_STARS="_stars";
	public static final String KEY_URL_ATTACH="_url_attachs";
	public static final String KEY_SUBCATEGORY="_sub_category";
	public static final String KEY_SUBCATEGORY_EN="_sub_category_en";
	
	public static final String KEY_TITLE="_title";
	public static final String KEY_DESCRIPTION="_description";
	public static final String KEY_PUBDATE="_pubdate";
	
	
	
	private static final String DATABASE_NAME="DataDB";

	private static final String DATABASE_TABLE_HOTELS="Data_table_hotels";
	private static final String DATABASE_TABLE_FOOD="Data_table_food";
	private static final String DATABASE_TABLE_FUN="Data_table_fun";
	private static final String DATABASE_TABLE_COFFEE="Data_table_coffee";
	private static final String DATABASE_TABLE_LOCAL_PR="Data_table_local_pr";
	private static final String DATABASE_TABLE_SHOPPING="Data_table_shopping";
	private static final String DATABASE_TABLE_RENT="Data_table_rent";
	private static final String DATABASE_TABLE_TRAVEL_AGEN="Data_table_travel_agen";
	private static final String DATABASE_TABLE_BEAUTY="Data_table_travel_beauty";
	
	private static final String DATABASE_TABLE_DESTINATIONS="Data_table_destinations";
	private static final String DATABASE_TABLE_SHITES="Data_table_shites";
	private static final String DATABASE_TABLE_EVENTS="Data_table_events";
	private static final String DATABASE_TABLE_EVENTS_COL="Data_table_events_col";
	private static final String DATABASE_TABLE_COL_NEWS="Data_table_col_news";
	private static final String DATABASE_TABLE_COL_COFFEE="Data_table_col_coffee";
	
	
	
	private static final int DATABASE_VERSION=1;
	
	private DbHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;
	
	private class DbHelper extends SQLiteOpenHelper{

		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			
			db.execSQL(
					"CREATE TABLE " + DATABASE_TABLE_HOTELS + " ("+ 
							KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
							KEY_NAME + " TEXT NOT NULL, " + KEY_ADDRESS + " TEXT NOT NULL, "+ KEY_NAME_EN + " TEXT NOT NULL, "+ KEY_ADDRESS_EN + " TEXT NOT NULL, "+ KEY_CONTENT_EN + " TEXT NOT NULL, "+ KEY_IMAGE + " BLOB ," + KEY_CONTENT + " TEXT NOT NULL, "+
							KEY_TEL1+ " TEXT NOT NULL, " + KEY_TEL2 + " TEXT NOT NULL, "+ KEY_TEL3 + " TEXT NOT NULL ," + KEY_WEB + " TEXT NOT NULL , " + KEY_EMAIL + " TEXT NOT NULL, "+
							KEY_LAT+ " TEXT NOT NULL, " + KEY_LNG + " TEXT NOT NULL, "+ KEY_LOCATION + " TEXT NOT NULL, "+ KEY_LOCATION_EN + " TEXT NOT NULL, "+ KEY_TAGS + " TEXT NOT NULL, "+ KEY_CAT + " TEXT NOT NULL, " + KEY_STARS + " TEXT NOT NULL);"
						
						
						);	
			db.execSQL(
					"CREATE TABLE " + DATABASE_TABLE_FOOD + " ("+ 
							KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
							KEY_NAME + " TEXT NOT NULL, " + KEY_ADDRESS + " TEXT NOT NULL, "+ KEY_NAME_EN + " TEXT NOT NULL, "+ KEY_ADDRESS_EN + " TEXT NOT NULL, "+ KEY_CONTENT_EN + " TEXT NOT NULL, "+ KEY_IMAGE + " BLOB ," + KEY_CONTENT + " TEXT NOT NULL, "+
							KEY_TEL1+ " TEXT NOT NULL, " + KEY_TEL2 + " TEXT NOT NULL, "+ KEY_TEL3 + " TEXT NOT NULL ," + KEY_WEB + " TEXT NOT NULL , " + KEY_EMAIL + " TEXT NOT NULL, "+
							KEY_LAT+ " TEXT NOT NULL, " + KEY_LNG + " TEXT NOT NULL, "+ KEY_LOCATION + " TEXT NOT NULL, "+ KEY_LOCATION_EN + " TEXT NOT NULL, "+ KEY_TAGS + " TEXT NOT NULL, "+ KEY_CAT + " TEXT NOT NULL, "+ KEY_SUBCATEGORY + " TEXT NOT NULL, "+ KEY_SUBCATEGORY_EN + " TEXT NOT NULL, " + KEY_URL_ATTACH + " TEXT NOT NULL);"
						
						
						);	
			db.execSQL(
					"CREATE TABLE " + DATABASE_TABLE_FUN + " ("+ 
							KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
							KEY_NAME + " TEXT NOT NULL, " + KEY_ADDRESS + " TEXT NOT NULL, "+ KEY_NAME_EN + " TEXT NOT NULL, "+ KEY_ADDRESS_EN + " TEXT NOT NULL, "+ KEY_CONTENT_EN + " TEXT NOT NULL, "+ KEY_IMAGE + " BLOB ," + KEY_CONTENT + " TEXT NOT NULL, "+
							KEY_TEL1+ " TEXT NOT NULL, " + KEY_TEL2 + " TEXT NOT NULL, "+ KEY_TEL3 + " TEXT NOT NULL ," + KEY_WEB + " TEXT NOT NULL , " + KEY_EMAIL + " TEXT NOT NULL, "+
							KEY_LAT+ " TEXT NOT NULL, " + KEY_LNG + " TEXT NOT NULL, "+ KEY_LOCATION + " TEXT NOT NULL, "+ KEY_LOCATION_EN + " TEXT NOT NULL, "+ KEY_TAGS + " TEXT NOT NULL, "+ KEY_CAT + " TEXT NOT NULL, " + KEY_URL_ATTACH + " TEXT NOT NULL);"
						
						
						);	
			db.execSQL(
					"CREATE TABLE " + DATABASE_TABLE_COFFEE + " ("+ 
							KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
							KEY_NAME + " TEXT NOT NULL, " + KEY_ADDRESS + " TEXT NOT NULL, "+ KEY_NAME_EN + " TEXT NOT NULL, "+ KEY_ADDRESS_EN + " TEXT NOT NULL, "+ KEY_CONTENT_EN + " TEXT NOT NULL, "+ KEY_IMAGE + " BLOB ," + KEY_CONTENT + " TEXT NOT NULL, "+
							KEY_TEL1+ " TEXT NOT NULL, " + KEY_TEL2 + " TEXT NOT NULL, "+ KEY_TEL3 + " TEXT NOT NULL ," + KEY_WEB + " TEXT NOT NULL , " + KEY_EMAIL + " TEXT NOT NULL, "+
							KEY_LAT+ " TEXT NOT NULL, " + KEY_LNG + " TEXT NOT NULL, "+ KEY_LOCATION + " TEXT NOT NULL, "+ KEY_LOCATION_EN + " TEXT NOT NULL, "+ KEY_TAGS + " TEXT NOT NULL, "+ KEY_CAT + " TEXT NOT NULL, " + KEY_URL_ATTACH + " TEXT NOT NULL);"
						
						
						);	
			db.execSQL(
					"CREATE TABLE " + DATABASE_TABLE_LOCAL_PR + " ("+ 
							KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
							KEY_NAME + " TEXT NOT NULL, " + KEY_ADDRESS + " TEXT NOT NULL, "+ KEY_NAME_EN + " TEXT NOT NULL, "+ KEY_ADDRESS_EN + " TEXT NOT NULL, "+ KEY_CONTENT_EN + " TEXT NOT NULL, "+ KEY_IMAGE + " BLOB ," + KEY_CONTENT + " TEXT NOT NULL, "+
							KEY_TEL1+ " TEXT NOT NULL, " + KEY_TEL2 + " TEXT NOT NULL, "+ KEY_TEL3 + " TEXT NOT NULL ," + KEY_WEB + " TEXT NOT NULL , " + KEY_EMAIL + " TEXT NOT NULL, "+
							KEY_LAT+ " TEXT NOT NULL, " + KEY_LNG + " TEXT NOT NULL, "+ KEY_LOCATION + " TEXT NOT NULL, "+ KEY_LOCATION_EN + " TEXT NOT NULL, "+ KEY_TAGS + " TEXT NOT NULL, "+ KEY_CAT + " TEXT NOT NULL, " + KEY_URL_ATTACH + " TEXT NOT NULL);"
						
						
						);	
			db.execSQL(
					"CREATE TABLE " + DATABASE_TABLE_SHOPPING + " ("+ 
							KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
							KEY_NAME + " TEXT NOT NULL, " + KEY_ADDRESS + " TEXT NOT NULL, "+ KEY_NAME_EN + " TEXT NOT NULL, "+ KEY_ADDRESS_EN + " TEXT NOT NULL, "+ KEY_CONTENT_EN + " TEXT NOT NULL, "+ KEY_IMAGE + " BLOB ," + KEY_CONTENT + " TEXT NOT NULL, "+
							KEY_TEL1+ " TEXT NOT NULL, " + KEY_TEL2 + " TEXT NOT NULL, "+ KEY_TEL3 + " TEXT NOT NULL ," + KEY_WEB + " TEXT NOT NULL , " + KEY_EMAIL + " TEXT NOT NULL, "+
							KEY_LAT+ " TEXT NOT NULL, " + KEY_LNG + " TEXT NOT NULL, "+ KEY_LOCATION + " TEXT NOT NULL, "+ KEY_LOCATION_EN + " TEXT NOT NULL, "+ KEY_TAGS + " TEXT NOT NULL, "+ KEY_CAT + " TEXT NOT NULL, "+ KEY_SUBCATEGORY + " TEXT NOT NULL, "+ KEY_SUBCATEGORY_EN + " TEXT NOT NULL, " + KEY_URL_ATTACH + " TEXT NOT NULL);"
						
						
						);
			db.execSQL(
					"CREATE TABLE " + DATABASE_TABLE_RENT + " ("+ 
							KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
							KEY_NAME + " TEXT NOT NULL, " + KEY_ADDRESS + " TEXT NOT NULL, "+ KEY_NAME_EN + " TEXT NOT NULL, "+ KEY_ADDRESS_EN + " TEXT NOT NULL, "+ KEY_CONTENT_EN + " TEXT NOT NULL, "+ KEY_IMAGE + " BLOB ," + KEY_CONTENT + " TEXT NOT NULL, "+
							KEY_TEL1+ " TEXT NOT NULL, " + KEY_TEL2 + " TEXT NOT NULL, "+ KEY_TEL3 + " TEXT NOT NULL ," + KEY_WEB + " TEXT NOT NULL , " + KEY_EMAIL + " TEXT NOT NULL, "+
							KEY_LAT+ " TEXT NOT NULL, " + KEY_LNG + " TEXT NOT NULL, "+ KEY_LOCATION + " TEXT NOT NULL, "+ KEY_LOCATION_EN + " TEXT NOT NULL, "+ KEY_TAGS + " TEXT NOT NULL, "+ KEY_CAT + " TEXT NOT NULL, " + KEY_URL_ATTACH + " TEXT NOT NULL);"
						
						
						);
			db.execSQL(
					"CREATE TABLE " + DATABASE_TABLE_TRAVEL_AGEN + " ("+ 
							KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
							KEY_NAME + " TEXT NOT NULL, " + KEY_ADDRESS + " TEXT NOT NULL, "+ KEY_NAME_EN + " TEXT NOT NULL, "+ KEY_ADDRESS_EN + " TEXT NOT NULL, "+ KEY_CONTENT_EN + " TEXT NOT NULL, "+ KEY_IMAGE + " BLOB ," + KEY_CONTENT + " TEXT NOT NULL, "+
							KEY_TEL1+ " TEXT NOT NULL, " + KEY_TEL2 + " TEXT NOT NULL, "+ KEY_TEL3 + " TEXT NOT NULL ," + KEY_WEB + " TEXT NOT NULL , " + KEY_EMAIL + " TEXT NOT NULL, "+
							KEY_LAT+ " TEXT NOT NULL, " + KEY_LNG + " TEXT NOT NULL, "+ KEY_LOCATION + " TEXT NOT NULL, "+ KEY_LOCATION_EN + " TEXT NOT NULL, "+ KEY_TAGS + " TEXT NOT NULL, "+ KEY_CAT + " TEXT NOT NULL, " + KEY_URL_ATTACH + " TEXT NOT NULL);"
						
						
						);
			db.execSQL(
					"CREATE TABLE " + DATABASE_TABLE_BEAUTY + " ("+ 
							KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
							KEY_NAME + " TEXT NOT NULL, " + KEY_ADDRESS + " TEXT NOT NULL, "+ KEY_NAME_EN + " TEXT NOT NULL, "+ KEY_ADDRESS_EN + " TEXT NOT NULL, "+ KEY_CONTENT_EN + " TEXT NOT NULL, "+ KEY_IMAGE + " BLOB ," + KEY_CONTENT + " TEXT NOT NULL, "+
							KEY_TEL1+ " TEXT NOT NULL, " + KEY_TEL2 + " TEXT NOT NULL, "+ KEY_TEL3 + " TEXT NOT NULL ," + KEY_WEB + " TEXT NOT NULL , " + KEY_EMAIL + " TEXT NOT NULL, "+
							KEY_LAT+ " TEXT NOT NULL, " + KEY_LNG + " TEXT NOT NULL, "+ KEY_LOCATION + " TEXT NOT NULL, "+ KEY_LOCATION_EN + " TEXT NOT NULL, "+ KEY_TAGS + " TEXT NOT NULL, "+ KEY_CAT + " TEXT NOT NULL, " + KEY_SUBCATEGORY + " TEXT NOT NULL, "+ KEY_SUBCATEGORY_EN + " TEXT NOT NULL, " + KEY_URL_ATTACH + " TEXT NOT NULL);"
						
						
						);
			db.execSQL(
					"CREATE TABLE " + DATABASE_TABLE_DESTINATIONS + " ("+ 
							KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
							KEY_NAME + " TEXT NOT NULL, " + KEY_ADDRESS + " TEXT NOT NULL, "+ KEY_NAME_EN + " TEXT NOT NULL, "+ KEY_ADDRESS_EN + " TEXT NOT NULL, "+ KEY_CONTENT_EN + " TEXT NOT NULL, "+ KEY_IMAGE + " BLOB ," + KEY_CONTENT + " TEXT NOT NULL, "+
							KEY_TEL1+ " TEXT NOT NULL, " + KEY_TEL2 + " TEXT NOT NULL, "+ KEY_TEL3 + " TEXT NOT NULL ," + KEY_WEB + " TEXT NOT NULL , " + KEY_EMAIL + " TEXT NOT NULL, "+
							KEY_LAT+ " TEXT NOT NULL, " + KEY_LNG + " TEXT NOT NULL, "+ KEY_LOCATION + " TEXT NOT NULL, "+ KEY_LOCATION_EN + " TEXT NOT NULL, "+ KEY_TAGS + " TEXT NOT NULL, "+ KEY_CAT + " TEXT NOT NULL, " + KEY_URL_ATTACH + " TEXT NOT NULL);"
						
						
						);	
			db.execSQL(
					"CREATE TABLE " + DATABASE_TABLE_SHITES + " ("+ 
							KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
							KEY_NAME + " TEXT NOT NULL, " + KEY_ADDRESS + " TEXT NOT NULL, "+ KEY_NAME_EN + " TEXT NOT NULL, "+ KEY_ADDRESS_EN + " TEXT NOT NULL, "+ KEY_CONTENT_EN + " TEXT NOT NULL, "+ KEY_IMAGE + " BLOB ," + KEY_CONTENT + " TEXT NOT NULL, "+
							KEY_TEL1+ " TEXT NOT NULL, " + KEY_TEL2 + " TEXT NOT NULL, "+ KEY_TEL3 + " TEXT NOT NULL ," + KEY_WEB + " TEXT NOT NULL , " + KEY_EMAIL + " TEXT NOT NULL, "+
							KEY_LAT+ " TEXT NOT NULL, " + KEY_LNG + " TEXT NOT NULL, "+ KEY_LOCATION + " TEXT NOT NULL, "+ KEY_LOCATION_EN + " TEXT NOT NULL, "+ KEY_TAGS + " TEXT NOT NULL, "+ KEY_CAT + " TEXT NOT NULL, " + KEY_URL_ATTACH + " TEXT NOT NULL);"
						
						
						);
			db.execSQL(
					"CREATE TABLE " + DATABASE_TABLE_EVENTS + " ("+ 
							KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
							KEY_DATE + " TEXT NOT NULL, "  + KEY_NAME + " TEXT NOT NULL, "+ KEY_ADDRESS + " TEXT NOT NULL, "+ KEY_NAME_EN + " TEXT NOT NULL, "+ KEY_ADDRESS_EN + " TEXT NOT NULL, "+ KEY_CONTENT_EN + " TEXT NOT NULL, "+ KEY_IMAGE + " BLOB ," + KEY_CONTENT + " TEXT NOT NULL, "+
							KEY_TEL1+ " TEXT NOT NULL, " + KEY_TEL2 + " TEXT NOT NULL, "+ KEY_TEL3 + " TEXT NOT NULL ," + KEY_WEB + " TEXT NOT NULL , " + KEY_EMAIL + " TEXT NOT NULL, "+
							KEY_LAT+ " TEXT NOT NULL, " + KEY_LNG + " TEXT NOT NULL, "+ KEY_LOCATION + " TEXT NOT NULL, "+ KEY_LOCATION_EN + " TEXT NOT NULL, "+ KEY_TAGS + " TEXT NOT NULL, "+ KEY_CAT + " TEXT NOT NULL);"
						
						
						);
			db.execSQL(
					"CREATE TABLE " + DATABASE_TABLE_EVENTS_COL + " ("+ 
							KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
							KEY_DATE + " TEXT NOT NULL, "  + KEY_NAME + " TEXT NOT NULL, "+ KEY_ADDRESS + " TEXT NOT NULL, "+ KEY_NAME_EN + " TEXT NOT NULL, "+ KEY_ADDRESS_EN + " TEXT NOT NULL, "+ KEY_CONTENT_EN + " TEXT NOT NULL, "+ KEY_IMAGE + " BLOB ," + KEY_CONTENT + " TEXT NOT NULL, "+
							KEY_TEL1+ " TEXT NOT NULL, " + KEY_TEL2 + " TEXT NOT NULL, "+ KEY_TEL3 + " TEXT NOT NULL ," + KEY_WEB + " TEXT NOT NULL , " + KEY_EMAIL + " TEXT NOT NULL, "+
							KEY_LAT+ " TEXT NOT NULL, " + KEY_LNG + " TEXT NOT NULL, "+ KEY_LOCATION + " TEXT NOT NULL, "+ KEY_LOCATION_EN + " TEXT NOT NULL, "+ KEY_TAGS + " TEXT NOT NULL, "+ KEY_CAT + " TEXT NOT NULL);"
						
						
						);
			
			db.execSQL(
					"CREATE TABLE " + DATABASE_TABLE_COL_NEWS + " ("+ 
							KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
							KEY_TITLE + " TEXT NOT NULL, " + KEY_DESCRIPTION + " TEXT NOT NULL, "+ KEY_PUBDATE + " TEXT NOT NULL);"
						
						
						);
			db.execSQL(
					"CREATE TABLE " + DATABASE_TABLE_COL_COFFEE + " ("+ 
							KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME + " TEXT NOT NULL, "+ KEY_ADDRESS + " TEXT NOT NULL, "+ KEY_NAME_EN + " TEXT NOT NULL, "+ KEY_ADDRESS_EN + " TEXT NOT NULL, "+ KEY_CONTENT_EN + " TEXT NOT NULL, "+ KEY_IMAGE + " BLOB ," + KEY_CONTENT + " TEXT NOT NULL, "+
							KEY_TEL1+ " TEXT NOT NULL, " + KEY_TEL2 + " TEXT NOT NULL, "+ KEY_TEL3 + " TEXT NOT NULL ," + KEY_WEB + " TEXT NOT NULL , " + KEY_EMAIL + " TEXT NOT NULL, "+
							KEY_LAT+ " TEXT NOT NULL, " + KEY_LNG + " TEXT NOT NULL, "+ KEY_LOCATION + " TEXT NOT NULL, "+ KEY_LOCATION_EN + " TEXT NOT NULL, "+ KEY_TAGS + " TEXT NOT NULL, "+ KEY_CAT + " TEXT NOT NULL, " + KEY_URL_ATTACH + " TEXT NOT NULL);"
						
						
						);
		}
		
		
		
		
		
		
		
		
		

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			System.out.println(oldVersion);
			System.out.println(newVersion);
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE_HOTELS);
			db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE_FOOD);
			db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE_FUN);
			db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE_COFFEE);
			db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE_LOCAL_PR);
			db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE_SHOPPING);
			db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE_RENT);
			db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE_TRAVEL_AGEN);
			db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE_BEAUTY);
			db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE_DESTINATIONS);
			db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE_SHITES);
			db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE_EVENTS);
			db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE_EVENTS_COL);
			db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE_COL_NEWS);
			db.execSQL("DROP TABLE IF EXISTS "+ DATABASE_TABLE_COL_COFFEE);
							
			
			onCreate(db);
		}
		
	}
	
	public DataSQL(Context c){
		ourContext=c;
	}
	
	public DataSQL open() throws SQLException{
		ourHelper=new DbHelper(ourContext);
		ourDatabase=ourHelper.getWritableDatabase();
		return this;
	}
	public void close(){
		ourHelper.close();
		
	}
	
	public long createEntry_Col_News(String title, String description,String pubdate) {
		// TODO Auto-generated method stub
		ContentValues cv= new ContentValues();
		cv.put(KEY_TITLE, title);
		cv.put(KEY_DESCRIPTION, description);
		cv.put(KEY_PUBDATE, pubdate);				
		
		
		
		return ourDatabase.insert(DATABASE_TABLE_COL_NEWS, null, cv);
	}
	
	public List<RSSFeed> getItems_col_news(List<RSSFeed> mylist) {
		// TODO Auto-generated method stub
		String[] columns=new String[]{KEY_ROWID,KEY_TITLE,KEY_DESCRIPTION,KEY_PUBDATE};
		Cursor c=ourDatabase.query(DATABASE_TABLE_COL_NEWS, columns, null, null, null, null, null);
		String result="";
		
		int iTitle=c.getColumnIndex(KEY_TITLE);
		int iDescription=c.getColumnIndex(KEY_DESCRIPTION);
		int iPubDate=c.getColumnIndex(KEY_PUBDATE);
		

		for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
			RSSFeed myitem=new RSSFeed();
			myitem.setTitle(c.getString(iTitle));
			myitem.setDescription(c.getString(iDescription));
			myitem.setPubDate(c.getString(iPubDate));
			
			
			mylist.add(myitem);
			
		}
		
		
		return mylist;
	}
	
	public long createEntry_event(String date,String title, String address,String title_en, String address_en, String content_en, byte[] image, String content, String tel1,String tel2,String tel3,String web,String email,String lat,String lng,String location,String location_en,String tags,String cat) {
		// TODO Auto-generated method stub
		ContentValues cv= new ContentValues();
		cv.put(KEY_NAME, title);
		cv.put(KEY_ADDRESS, address);
		cv.put(KEY_NAME_EN, title_en);
		cv.put(KEY_ADDRESS_EN, address_en);
		cv.put(KEY_CONTENT_EN, content_en);
		cv.put(KEY_IMAGE, image);
		cv.put(KEY_CONTENT, content);
		cv.put(KEY_TEL1, tel1);
		cv.put(KEY_TEL2, tel2);
		cv.put(KEY_TEL3, tel3);	
		cv.put(KEY_WEB, web);
		cv.put(KEY_EMAIL, email);
		cv.put(KEY_LAT, lat);
		cv.put(KEY_LNG, lng);
		cv.put(KEY_LOCATION, location);
		cv.put(KEY_LOCATION_EN, location_en);
		cv.put(KEY_TAGS, tags);
		cv.put(KEY_CAT, cat);
		cv.put(KEY_DATE, date);
		
		String nameDB=DATABASE_TABLE_EVENTS;	
		
		
		
			return ourDatabase.insert(nameDB, null, cv);
		
	}	
	public long createEntry_event_col(String date,String title, String address,String title_en, String address_en, String content_en, byte[] image, String content, String tel1,String tel2,String tel3,String web,String email,String lat,String lng,String location,String location_en,String tags,String cat) {
		// TODO Auto-generated method stub
		ContentValues cv= new ContentValues();
		cv.put(KEY_NAME, title);
		cv.put(KEY_ADDRESS, address);
		cv.put(KEY_NAME_EN, title_en);
		cv.put(KEY_ADDRESS_EN, address_en);
		cv.put(KEY_CONTENT_EN, content_en);
		cv.put(KEY_IMAGE, image);
		cv.put(KEY_CONTENT, content);
		cv.put(KEY_TEL1, tel1);
		cv.put(KEY_TEL2, tel2);
		cv.put(KEY_TEL3, tel3);	
		cv.put(KEY_WEB, web);
		cv.put(KEY_EMAIL, email);
		cv.put(KEY_LAT, lat);
		cv.put(KEY_LNG, lng);
		cv.put(KEY_LOCATION, location);
		cv.put(KEY_LOCATION_EN, location_en);
		cv.put(KEY_TAGS, tags);
		cv.put(KEY_CAT, cat);
		cv.put(KEY_DATE, date);
		
		String nameDB=DATABASE_TABLE_EVENTS_COL;		
		
		
		
		
			return ourDatabase.insert(nameDB, null, cv);
		//return ourDatabase.insert(DATABASE_TABLE_FASTFOOD, null, cv);
	}	
	public long createEntry_coffee_col(String title, String address,String title_en, String address_en, String content_en, byte[] image, String content, String tel1,String tel2,String tel3,String web,String email,String lat,String lng,String location,String location_en,String tags,String cat,String url_attach) {
		// TODO Auto-generated method stub
		ContentValues cv= new ContentValues();
		cv.put(KEY_NAME, title);
		cv.put(KEY_ADDRESS, address);
		cv.put(KEY_NAME_EN, title_en);
		cv.put(KEY_ADDRESS_EN, address_en);
		cv.put(KEY_CONTENT_EN, content_en);
		cv.put(KEY_IMAGE, image);
		cv.put(KEY_CONTENT, content);
		cv.put(KEY_TEL1, tel1);
		cv.put(KEY_TEL2, tel2);
		cv.put(KEY_TEL3, tel3);	
		cv.put(KEY_WEB, web);
		cv.put(KEY_EMAIL, email);
		cv.put(KEY_LAT, lat);
		cv.put(KEY_LNG, lng);
		cv.put(KEY_LOCATION, location);
		cv.put(KEY_LOCATION_EN, location_en);
		cv.put(KEY_TAGS, tags);
		cv.put(KEY_CAT, cat);
		cv.put(KEY_URL_ATTACH, url_attach);
		
		String nameDB=DATABASE_TABLE_COL_COFFEE;		
		
		
		
		
			return ourDatabase.insert(nameDB, null, cv);
		//return ourDatabase.insert(DATABASE_TABLE_FASTFOOD, null, cv);
	}	
	public long createEntry(String title, String address,String title_en, String address_en, String content_en, byte[] image, String content, String tel1,String tel2,String tel3,String web,String email,String lat,String lng,String location,String location_en,String tags,String cat, String url_attach) {
		// TODO Auto-generated method stub
		ContentValues cv= new ContentValues();
		cv.put(KEY_NAME, title);
		cv.put(KEY_ADDRESS, address);
		cv.put(KEY_NAME_EN, title_en);
		cv.put(KEY_ADDRESS_EN, address_en);
		cv.put(KEY_CONTENT_EN, content_en);
		cv.put(KEY_IMAGE, image);
		cv.put(KEY_CONTENT, content);
		cv.put(KEY_TEL1, tel1);
		cv.put(KEY_TEL2, tel2);
		cv.put(KEY_TEL3, tel3);	
		cv.put(KEY_WEB, web);
		cv.put(KEY_EMAIL, email);
		cv.put(KEY_LAT, lat);
		cv.put(KEY_LNG, lng);
		cv.put(KEY_LOCATION, location);
		cv.put(KEY_LOCATION_EN, location_en);
		cv.put(KEY_TAGS, tags);
		cv.put(KEY_CAT, cat);
		cv.put(KEY_URL_ATTACH, url_attach);
		
		
		
	
		String nameDB=null;	
		if(cat.equals("destinations"))
			nameDB=DATABASE_TABLE_DESTINATIONS;
		else if(cat.equals("shites"))
			nameDB=DATABASE_TABLE_SHITES;
		else if(cat.equals("food"))
			nameDB=DATABASE_TABLE_FOOD;
		else if(cat.equals("fun"))
			nameDB=DATABASE_TABLE_FUN;
		else if(cat.equals("coffee"))
			nameDB=DATABASE_TABLE_COFFEE;
		else if(cat.equals("local"))
			nameDB=DATABASE_TABLE_LOCAL_PR;
		else if(cat.equals("shopping"))
			nameDB=DATABASE_TABLE_SHOPPING;
		else if(cat.equals("rent"))
			nameDB=DATABASE_TABLE_RENT;
		else if(cat.equals("travel_agencies"))
			nameDB=DATABASE_TABLE_TRAVEL_AGEN;
		else if(cat.equals("beauty"))
			nameDB=DATABASE_TABLE_BEAUTY;
		
		
		
			return ourDatabase.insert(nameDB, null, cv);
		//return ourDatabase.insert(DATABASE_TABLE_FASTFOOD, null, cv);
	}	
	
	public long createEntry_food(String title, String address,String title_en, String address_en, String content_en, byte[] image, String content, String tel1,String tel2,String tel3,String web,String email,String lat,String lng,String location,String location_en,String tags,String cat, String subcategory,String subcategory_en,String url_attach) {
		// TODO Auto-generated method stub
		ContentValues cv= new ContentValues();
		cv.put(KEY_NAME, title);
		cv.put(KEY_ADDRESS, address);
		cv.put(KEY_NAME_EN, title_en);
		cv.put(KEY_ADDRESS_EN, address_en);
		cv.put(KEY_CONTENT_EN, content_en);
		cv.put(KEY_IMAGE, image);
		cv.put(KEY_CONTENT, content);
		cv.put(KEY_TEL1, tel1);
		cv.put(KEY_TEL2, tel2);
		cv.put(KEY_TEL3, tel3);	
		cv.put(KEY_WEB, web);
		cv.put(KEY_EMAIL, email);
		cv.put(KEY_LAT, lat);
		cv.put(KEY_LNG, lng);
		cv.put(KEY_LOCATION, location);
		cv.put(KEY_LOCATION_EN, location_en);
		cv.put(KEY_TAGS, tags);
		cv.put(KEY_CAT, cat);
		cv.put(KEY_URL_ATTACH, url_attach);
		cv.put(KEY_SUBCATEGORY, subcategory);
		cv.put(KEY_SUBCATEGORY_EN, subcategory_en);
		
		
		
	
		String nameDB=null;	
		
		if(cat.equals("food"))
			nameDB=DATABASE_TABLE_FOOD;
		else if(cat.equals("beauty"))
			nameDB=DATABASE_TABLE_BEAUTY;
		else if(cat.equals("shopping"))
			nameDB=DATABASE_TABLE_SHOPPING;
		
		
		
			return ourDatabase.insert(nameDB, null, cv);
		//return ourDatabase.insert(DATABASE_TABLE_FASTFOOD, null, cv);
	}	

	public long createEntry_Hotels(String title, String address,String title_en, String address_en, String content_en, byte[] image, String content, String tel1,String tel2,String tel3,String web,String email,String lat,String lng,String location,String location_en,String tags,String cat,String stars) {
		// TODO Auto-generated method stub
		ContentValues cv= new ContentValues();
		cv.put(KEY_NAME, title);
		cv.put(KEY_ADDRESS, address);
		cv.put(KEY_NAME_EN, title_en);
		cv.put(KEY_ADDRESS_EN, address_en);
		cv.put(KEY_CONTENT_EN, content_en);
		cv.put(KEY_IMAGE, image);
		cv.put(KEY_CONTENT, content);
		cv.put(KEY_TEL1, tel1);
		cv.put(KEY_TEL2, tel2);
		cv.put(KEY_TEL3, tel3);	
		cv.put(KEY_WEB, web);
		cv.put(KEY_EMAIL, email);
		cv.put(KEY_LAT, lat);
		cv.put(KEY_LNG, lng);
		cv.put(KEY_LOCATION, location);
		cv.put(KEY_LOCATION_EN, location_en);
		cv.put(KEY_TAGS, tags);
		cv.put(KEY_CAT, cat);
		cv.put(KEY_STARS, stars);
		
		
		
	
		
		String	nameDB=DATABASE_TABLE_HOTELS;
		
		
		
		
			return ourDatabase.insert(nameDB, null, cv);
		//return ourDatabase.insert(DATABASE_TABLE_FASTFOOD, null, cv);
	}
	
	
	public ArrayList<ListViewItem> getItems_event(ArrayList<ListViewItem> mylist,String cat) {
		// TODO Auto-generated method stub
		String nameDB=null;
		if(cat.equals("events"))
			nameDB=DATABASE_TABLE_EVENTS;
		else if(cat.equals("college_events"))			
			nameDB=DATABASE_TABLE_EVENTS_COL;
		else
			nameDB=DATABASE_TABLE_COL_COFFEE;
		
		
		String[] columns=new String[]{KEY_ROWID,KEY_DATE,KEY_NAME,KEY_ADDRESS,KEY_NAME_EN,KEY_ADDRESS_EN,KEY_CONTENT_EN,KEY_IMAGE,KEY_CONTENT,KEY_TEL1,KEY_TEL2,KEY_TEL3,KEY_WEB,KEY_EMAIL,KEY_LAT,KEY_LNG,KEY_LOCATION,KEY_LOCATION_EN,KEY_TAGS,KEY_CAT};
		Cursor c=ourDatabase.query(nameDB, columns, null, null, null, null, null);
		String result="";
		//int iRow=c.getColumnIndex(KEY_ROWID);
		//int iName=c.getColumnIndex(KEY_NAME);
		int iDate=c.getColumnIndex(KEY_DATE);
		int iTitle=c.getColumnIndex(KEY_NAME);
		int iAddress=c.getColumnIndex(KEY_ADDRESS);
		int iImage=c.getColumnIndex(KEY_IMAGE);
		int iContent=c.getColumnIndex(KEY_CONTENT);
		int iTel1=c.getColumnIndex(KEY_TEL1);
		int iTel2=c.getColumnIndex(KEY_TEL2);
		int iTel3=c.getColumnIndex(KEY_TEL3);		
		int iWeb=c.getColumnIndex(KEY_WEB);
		int iEmail=c.getColumnIndex(KEY_EMAIL);
		int iLat=c.getColumnIndex(KEY_LAT);
		int iLng=c.getColumnIndex(KEY_LNG);
		int iCat=c.getColumnIndex(KEY_CAT);
		int iTitle_en=c.getColumnIndex(KEY_NAME_EN);
		int iAddress_en=c.getColumnIndex(KEY_ADDRESS_EN);
		int icontent_en=c.getColumnIndex(KEY_CONTENT_EN);
		int ilocation=c.getColumnIndex(KEY_LOCATION);
		int ilocation_en=c.getColumnIndex(KEY_LOCATION_EN);
		int itags=c.getColumnIndex(KEY_TAGS);
		
		
		
		for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
			ListViewItem myitem=new ListViewItem();
			myitem.name=c.getString(iTitle);
			myitem.address=c.getString(iAddress);
			byte[] photo=c.getBlob(iImage);
			ByteArrayInputStream imageStream = new ByteArrayInputStream(photo);
			Bitmap theImage= BitmapFactory.decodeStream(imageStream);
			myitem.image=theImage;
			myitem.content=c.getString(iContent);
			myitem.tel1=c.getString(iTel1);
			myitem.tel2=c.getString(iTel2);
			myitem.tel3=c.getString(iTel3);			
			myitem.web=c.getString(iWeb);
			myitem.email=c.getString(iEmail);
			myitem.lat=c.getString(iLat);
			myitem.lng=c.getString(iLng);
			myitem.category=c.getString(iCat);
			myitem.name_en=c.getString(iTitle_en);
			myitem.address_en=c.getString(iAddress_en);
			myitem.content_en=c.getString(icontent_en);
			myitem.location=c.getString(ilocation);
			myitem.location_en=c.getString(ilocation_en);
			myitem.tags=c.getString(itags);
			myitem.date=c.getString(iDate);
			mylist.add(myitem);
			
		}
		
		
		
		return mylist;
	}
	
	public ArrayList<ListViewItem> getItems_col_coffee(ArrayList<ListViewItem> mylist,String cat) {
		// TODO Auto-generated method stub
		String nameDB=null;
	
			nameDB=DATABASE_TABLE_COL_COFFEE;
		
		
		String[] columns=new String[]{KEY_ROWID,KEY_NAME,KEY_ADDRESS,KEY_NAME_EN,KEY_ADDRESS_EN,KEY_CONTENT_EN,KEY_IMAGE,KEY_CONTENT,KEY_TEL1,KEY_TEL2,KEY_TEL3,KEY_WEB,KEY_EMAIL,KEY_LAT,KEY_LNG,KEY_LOCATION,KEY_LOCATION_EN,KEY_TAGS,KEY_CAT,KEY_URL_ATTACH};
		Cursor c=ourDatabase.query(nameDB, columns, null, null, null, null, null);
		String result="";
		//int iRow=c.getColumnIndex(KEY_ROWID);
		//int iName=c.getColumnIndex(KEY_NAME);
		
		int iTitle=c.getColumnIndex(KEY_NAME);
		int iAddress=c.getColumnIndex(KEY_ADDRESS);
		int iImage=c.getColumnIndex(KEY_IMAGE);
		int iContent=c.getColumnIndex(KEY_CONTENT);
		int iTel1=c.getColumnIndex(KEY_TEL1);
		int iTel2=c.getColumnIndex(KEY_TEL2);
		int iTel3=c.getColumnIndex(KEY_TEL3);		
		int iWeb=c.getColumnIndex(KEY_WEB);
		int iEmail=c.getColumnIndex(KEY_EMAIL);
		int iLat=c.getColumnIndex(KEY_LAT);
		int iLng=c.getColumnIndex(KEY_LNG);
		int iCat=c.getColumnIndex(KEY_CAT);
		int iTitle_en=c.getColumnIndex(KEY_NAME_EN);
		int iAddress_en=c.getColumnIndex(KEY_ADDRESS_EN);
		int icontent_en=c.getColumnIndex(KEY_CONTENT_EN);
		int ilocation=c.getColumnIndex(KEY_LOCATION);
		int ilocation_en=c.getColumnIndex(KEY_LOCATION_EN);
		int itags=c.getColumnIndex(KEY_TAGS);
		int iurl_attach=c.getColumnIndex(KEY_URL_ATTACH);
		
		
		for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
			ListViewItem myitem=new ListViewItem();
			myitem.name=c.getString(iTitle);
			myitem.address=c.getString(iAddress);
			byte[] photo=c.getBlob(iImage);
			ByteArrayInputStream imageStream = new ByteArrayInputStream(photo);
			Bitmap theImage= BitmapFactory.decodeStream(imageStream);
			myitem.image=theImage;
			myitem.content=c.getString(iContent);
			myitem.tel1=c.getString(iTel1);
			myitem.tel2=c.getString(iTel2);
			myitem.tel3=c.getString(iTel3);			
			myitem.web=c.getString(iWeb);
			myitem.email=c.getString(iEmail);
			myitem.lat=c.getString(iLat);
			myitem.lng=c.getString(iLng);
			myitem.category=c.getString(iCat);
			myitem.name_en=c.getString(iTitle_en);
			myitem.address_en=c.getString(iAddress_en);
			myitem.content_en=c.getString(icontent_en);
			myitem.location=c.getString(ilocation);
			myitem.location_en=c.getString(ilocation_en);
			myitem.tags=c.getString(itags);
			myitem.url_attach=c.getString(iurl_attach);
			
			mylist.add(myitem);
			
		}
		
		
		
		return mylist;
	}
	
	public ArrayList<ListViewItem> getItems(ArrayList<ListViewItem> mylist,String cat) {
		// TODO Auto-generated method stub
		String nameDB=null;	
		 if(cat.equals("destinations"))
			nameDB=DATABASE_TABLE_DESTINATIONS;
		else if(cat.equals("shites"))
			nameDB=DATABASE_TABLE_SHITES;
		else if(cat.equals("food"))
			nameDB=DATABASE_TABLE_FOOD;
		else if(cat.equals("fun"))
			nameDB=DATABASE_TABLE_FUN;
		else if(cat.equals("coffee"))
			nameDB=DATABASE_TABLE_COFFEE;
		else if(cat.equals("local"))
			nameDB=DATABASE_TABLE_LOCAL_PR;
		else if(cat.equals("shopping"))
			nameDB=DATABASE_TABLE_SHOPPING;
		else if(cat.equals("rent"))
			nameDB=DATABASE_TABLE_RENT;
		else if(cat.equals("travel_agencies"))
			nameDB=DATABASE_TABLE_TRAVEL_AGEN;
		else if(cat.equals("beauty"))
			nameDB=DATABASE_TABLE_BEAUTY;
		
		
		
		
		String[] columns=new String[]{KEY_ROWID,KEY_NAME,KEY_ADDRESS,KEY_NAME_EN,KEY_ADDRESS_EN,KEY_CONTENT_EN,KEY_IMAGE,KEY_CONTENT,KEY_TEL1,KEY_TEL2,KEY_TEL3,KEY_WEB,KEY_EMAIL,KEY_LAT,KEY_LNG,KEY_LOCATION,KEY_LOCATION_EN,KEY_TAGS,KEY_CAT,KEY_URL_ATTACH};
		Cursor c=ourDatabase.query(nameDB, columns, null, null, null, null, null);
		String result="";
		//int iRow=c.getColumnIndex(KEY_ROWID);
		//int iName=c.getColumnIndex(KEY_NAME);
		
		int iTitle=c.getColumnIndex(KEY_NAME);
		int iAddress=c.getColumnIndex(KEY_ADDRESS);
		int iImage=c.getColumnIndex(KEY_IMAGE);
		int iContent=c.getColumnIndex(KEY_CONTENT);
		int iTel1=c.getColumnIndex(KEY_TEL1);
		int iTel2=c.getColumnIndex(KEY_TEL2);
		int iTel3=c.getColumnIndex(KEY_TEL3);		
		int iWeb=c.getColumnIndex(KEY_WEB);
		int iEmail=c.getColumnIndex(KEY_EMAIL);
		int iLat=c.getColumnIndex(KEY_LAT);
		int iLng=c.getColumnIndex(KEY_LNG);
		int iCat=c.getColumnIndex(KEY_CAT);
		int iTitle_en=c.getColumnIndex(KEY_NAME_EN);
		int iAddress_en=c.getColumnIndex(KEY_ADDRESS_EN);
		int icontent_en=c.getColumnIndex(KEY_CONTENT_EN);
		int ilocation=c.getColumnIndex(KEY_LOCATION);
		int ilocation_en=c.getColumnIndex(KEY_LOCATION_EN);
		int itags=c.getColumnIndex(KEY_TAGS);
		int iurl_attach=c.getColumnIndex(KEY_URL_ATTACH);
		
		
		
		for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
			ListViewItem myitem=new ListViewItem();
			myitem.name=c.getString(iTitle);
			myitem.address=c.getString(iAddress);
			byte[] photo=c.getBlob(iImage);
			ByteArrayInputStream imageStream = new ByteArrayInputStream(photo);
			Bitmap theImage= BitmapFactory.decodeStream(imageStream);
			myitem.image=theImage;
			myitem.content=c.getString(iContent);
			myitem.tel1=c.getString(iTel1);
			myitem.tel2=c.getString(iTel2);
			myitem.tel3=c.getString(iTel3);			
			myitem.web=c.getString(iWeb);
			myitem.email=c.getString(iEmail);
			myitem.lat=c.getString(iLat);
			myitem.lng=c.getString(iLng);
			myitem.category=c.getString(iCat);
			myitem.name_en=c.getString(iTitle_en);
			myitem.address_en=c.getString(iAddress_en);
			myitem.content_en=c.getString(icontent_en);
			myitem.location=c.getString(ilocation);
			myitem.location_en=c.getString(ilocation_en);
			myitem.tags=c.getString(itags);
			myitem.url_attach=c.getString(iurl_attach);
			mylist.add(myitem);
			
		}
		
		
		
		return mylist;
	}
	
	public ArrayList<ListViewItem> getItems_food(ArrayList<ListViewItem> mylist, String cat) {
		// TODO Auto-generated method stub
		String nameDB=null;	
		if(cat.equals("food"))
			nameDB=DATABASE_TABLE_FOOD;
		else if(cat.equals("beauty"))
			nameDB=DATABASE_TABLE_BEAUTY;
		else if(cat.equals("shopping"))
			nameDB=DATABASE_TABLE_SHOPPING;
		
	
		String[] columns=new String[]{KEY_ROWID,KEY_NAME,KEY_ADDRESS,KEY_NAME_EN,KEY_ADDRESS_EN,KEY_CONTENT_EN,KEY_IMAGE,KEY_CONTENT,KEY_TEL1,KEY_TEL2,KEY_TEL3,KEY_WEB,KEY_EMAIL,KEY_LAT,KEY_LNG,KEY_LOCATION,KEY_LOCATION_EN,KEY_TAGS,KEY_CAT,KEY_URL_ATTACH,KEY_SUBCATEGORY,KEY_SUBCATEGORY_EN};
		Cursor c=ourDatabase.query(nameDB, columns, null, null, null, null, null);
		String result="";
		//int iRow=c.getColumnIndex(KEY_ROWID);
		//int iName=c.getColumnIndex(KEY_NAME);
		
		int iTitle=c.getColumnIndex(KEY_NAME);
		int iAddress=c.getColumnIndex(KEY_ADDRESS);
		int iImage=c.getColumnIndex(KEY_IMAGE);
		int iContent=c.getColumnIndex(KEY_CONTENT);
		int iTel1=c.getColumnIndex(KEY_TEL1);
		int iTel2=c.getColumnIndex(KEY_TEL2);
		int iTel3=c.getColumnIndex(KEY_TEL3);		
		int iWeb=c.getColumnIndex(KEY_WEB);
		int iEmail=c.getColumnIndex(KEY_EMAIL);
		int iLat=c.getColumnIndex(KEY_LAT);
		int iLng=c.getColumnIndex(KEY_LNG);
		int iCat=c.getColumnIndex(KEY_CAT);
		int iTitle_en=c.getColumnIndex(KEY_NAME_EN);
		int iAddress_en=c.getColumnIndex(KEY_ADDRESS_EN);
		int icontent_en=c.getColumnIndex(KEY_CONTENT_EN);
		int ilocation=c.getColumnIndex(KEY_LOCATION);
		int ilocation_en=c.getColumnIndex(KEY_LOCATION_EN);
		int itags=c.getColumnIndex(KEY_TAGS);
		int iurl_attach=c.getColumnIndex(KEY_URL_ATTACH);
		int isub_category=c.getColumnIndex(KEY_SUBCATEGORY);
		int isub_category_en=c.getColumnIndex(KEY_SUBCATEGORY_EN);
		
		
		
		for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
			ListViewItem myitem=new ListViewItem();
			myitem.name=c.getString(iTitle);
			myitem.address=c.getString(iAddress);
			byte[] photo=c.getBlob(iImage);
			ByteArrayInputStream imageStream = new ByteArrayInputStream(photo);
			Bitmap theImage= BitmapFactory.decodeStream(imageStream);
			myitem.image=theImage;
			myitem.content=c.getString(iContent);
			myitem.tel1=c.getString(iTel1);
			myitem.tel2=c.getString(iTel2);
			myitem.tel3=c.getString(iTel3);			
			myitem.web=c.getString(iWeb);
			myitem.email=c.getString(iEmail);
			myitem.lat=c.getString(iLat);
			myitem.lng=c.getString(iLng);
			myitem.category=c.getString(iCat);
			myitem.name_en=c.getString(iTitle_en);
			myitem.address_en=c.getString(iAddress_en);
			myitem.content_en=c.getString(icontent_en);
			myitem.location=c.getString(ilocation);
			myitem.location_en=c.getString(ilocation_en);
			myitem.tags=c.getString(itags);
			myitem.url_attach=c.getString(iurl_attach);
			myitem.sub_category=c.getString(isub_category);
			myitem.sub_category_en=c.getString(isub_category_en);
			mylist.add(myitem);
			
		}
		
		
		
		return mylist;
	}
	
	public ArrayList<ListViewItem> getItems_Hotels(ArrayList<ListViewItem> mylist) {
		// TODO Auto-generated method stub
		String nameDB=null;	
		
			nameDB=DATABASE_TABLE_HOTELS;
		
		
		
		
		
		String[] columns=new String[]{KEY_ROWID,KEY_NAME,KEY_ADDRESS,KEY_NAME_EN,KEY_ADDRESS_EN,KEY_CONTENT_EN,KEY_IMAGE,KEY_CONTENT,KEY_TEL1,KEY_TEL2,KEY_TEL3,KEY_WEB,KEY_EMAIL,KEY_LAT,KEY_LNG,KEY_LOCATION,KEY_LOCATION_EN,KEY_TAGS,KEY_CAT,KEY_STARS};
		Cursor c=ourDatabase.query(nameDB, columns, null, null, null, null, null);
		String result="";
		//int iRow=c.getColumnIndex(KEY_ROWID);
		//int iName=c.getColumnIndex(KEY_NAME);
		
		int iTitle=c.getColumnIndex(KEY_NAME);
		int iAddress=c.getColumnIndex(KEY_ADDRESS);
		int iImage=c.getColumnIndex(KEY_IMAGE);
		int iContent=c.getColumnIndex(KEY_CONTENT);
		int iTel1=c.getColumnIndex(KEY_TEL1);
		int iTel2=c.getColumnIndex(KEY_TEL2);
		int iTel3=c.getColumnIndex(KEY_TEL3);		
		int iWeb=c.getColumnIndex(KEY_WEB);
		int iEmail=c.getColumnIndex(KEY_EMAIL);
		int iLat=c.getColumnIndex(KEY_LAT);
		int iLng=c.getColumnIndex(KEY_LNG);
		int iCat=c.getColumnIndex(KEY_CAT);
		int iTitle_en=c.getColumnIndex(KEY_NAME_EN);
		int iAddress_en=c.getColumnIndex(KEY_ADDRESS_EN);
		int icontent_en=c.getColumnIndex(KEY_CONTENT_EN);
		int ilocation=c.getColumnIndex(KEY_LOCATION);
		int ilocation_en=c.getColumnIndex(KEY_LOCATION_EN);
		int itags=c.getColumnIndex(KEY_TAGS);
		int istars=c.getColumnIndex(KEY_STARS);
		
		
		
		for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
			ListViewItem myitem=new ListViewItem();
			myitem.name=c.getString(iTitle);
			myitem.address=c.getString(iAddress);
			byte[] photo=c.getBlob(iImage);
			ByteArrayInputStream imageStream = new ByteArrayInputStream(photo);
			Bitmap theImage= BitmapFactory.decodeStream(imageStream);
			myitem.image=theImage;
			myitem.content=c.getString(iContent);
			myitem.tel1=c.getString(iTel1);
			myitem.tel2=c.getString(iTel2);
			myitem.tel3=c.getString(iTel3);			
			myitem.web=c.getString(iWeb);
			myitem.email=c.getString(iEmail);
			myitem.lat=c.getString(iLat);
			myitem.lng=c.getString(iLng);
			myitem.category=c.getString(iCat);
			myitem.name_en=c.getString(iTitle_en);
			myitem.address_en=c.getString(iAddress_en);
			myitem.content_en=c.getString(icontent_en);
			myitem.location=c.getString(ilocation);
			myitem.location_en=c.getString(ilocation_en);
			myitem.tags=c.getString(itags);
			myitem.stars=c.getString(istars);
			mylist.add(myitem);
			
		}
		
		
		
		return mylist;
	}
	
	
	
	public void delete_Delivery(String cat){
		String nameDB=null;	
		if(cat.equals("hotels"))
			nameDB=DATABASE_TABLE_HOTELS;
		else if(cat.equals("destinations"))
			nameDB=DATABASE_TABLE_DESTINATIONS;
		else if(cat.equals("shites"))
			nameDB=DATABASE_TABLE_SHITES;
		else if(cat.equals("events"))
			nameDB=DATABASE_TABLE_EVENTS;
		else if(cat.equals("college_events"))
			nameDB=DATABASE_TABLE_EVENTS_COL;
		else if(cat.equals("college_news"))
			nameDB=DATABASE_TABLE_COL_NEWS;
		else if(cat.equals("coffe_shop"))
			nameDB=DATABASE_TABLE_COL_COFFEE;
		else if(cat.equals("food"))
			nameDB=DATABASE_TABLE_FOOD;
		else if(cat.equals("fun"))
			nameDB=DATABASE_TABLE_FUN;
		else if(cat.equals("coffee"))
			nameDB=DATABASE_TABLE_COFFEE;
		else if(cat.equals("local"))
			nameDB=DATABASE_TABLE_LOCAL_PR;
		else if(cat.equals("shopping"))
			nameDB=DATABASE_TABLE_SHOPPING;
		else if(cat.equals("rent"))
			nameDB=DATABASE_TABLE_RENT;
		else if(cat.equals("travel_agencies"))
			nameDB=DATABASE_TABLE_TRAVEL_AGEN;
		else if(cat.equals("beauty"))
			nameDB=DATABASE_TABLE_BEAUTY;
		
		ourDatabase.delete(nameDB, null, null);
	}
	
	
}
