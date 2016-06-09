package com.mts.grtourguidioannina;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class Map extends FragmentActivity {
	private GoogleMap mMap;
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.map);
		mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
		 mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
		 Double Lat=Double.valueOf(getIntent().getStringExtra("lat"));
		 Double Lng=Double.valueOf(getIntent().getStringExtra("lng"));
			LatLng mylocation=new LatLng(Lat,Lng);
		
		 mMap.addMarker(new MarkerOptions().position(mylocation).icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)).title(getIntent().getStringExtra("name"))).showInfoWindow(); 
		
	 mMap.setMyLocationEnabled(true);
	 
	 CameraUpdate update=CameraUpdateFactory.newLatLngZoom(mylocation, 16);
   	
  	mMap.animateCamera(update);
	}
	

}