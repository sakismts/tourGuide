package com.mts.grtourguidioannina;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import java.util.Locale;







import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.utils.MemoryCacheUtils;

import android.R.drawable;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.opengl.Visibility;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

 


public class CustomListViewAdapter extends BaseAdapter implements Filterable
{  
	///protected ImageLoader imageLoader = ImageLoader.getInstance();
	LayoutInflater inflater;
	public ArrayList<ListViewItem> items;
	 public ArrayList<ListViewItem> orig;
	 public ArrayList<ListViewItem> orig_location;
	 public ArrayList<ListViewItem> started_list;
	public Locale lang;
	public boolean isevent;

	protected ImageLoader imageLoader = ImageLoader.getInstance();
	DisplayImageOptions options;

	
    
    public CustomListViewAdapter(Activity context,
    		ArrayList<ListViewItem> items, Locale language,boolean event) {
		// TODO Auto-generated constructor stub
    	this.items = items;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.lang=language;
        this.orig_location=items;
        this.started_list=items;
        this.isevent=event;
       
        options = new DisplayImageOptions.Builder()
        .showImageOnLoading(R.drawable.ic_launcher_large)
        .showImageForEmptyUri(R.drawable.ic_launcher_large)
        .showImageOnFail(R.drawable.ic_launcher_large)
        .cacheInMemory(true)
        .cacheOnDisk(true)       
        .considerExifParams(true)
        .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
        .displayer(new RoundedBitmapDisplayer(20))
        .build();
	}
    
    public void clearItems(){
    	
    	items.clear();
    }

	@Override  
    public int getCount() {  
        // TODO Auto-generated method stub  
        return items.size();  
    }  
  
    @Override  
    public Object getItem(int position) {  
        // TODO Auto-generated method stub  
        return null;  
    }  
  
    @Override  
    public long getItemId(int position) {  
        // TODO Auto-generated method stub  
        return 0;  
    }
    private class ViewHolder {
	    ImageView imgthumbnail;
	    TextView txtTilte;
	    TextView txtAddresse;
	    TextView distance;
	    TextView date;
	     
	}
      
   @Override  
    public View getView(final int position, View convertView, ViewGroup parent) {  
        // TODO Auto-generated method stub  
    	
    	 View vi = convertView;
         ViewHolder holder = null;

         if (vi == null) {
             vi = inflater.inflate(R.layout.category_list_item, parent, false);
             holder = new ViewHolder();

             holder.imgthumbnail=(ImageView)vi.findViewById(R.id.imageView1);
             
             holder.txtTilte=(TextView)vi.findViewById(R.id.tv_title);
            
             holder.txtAddresse=(TextView)vi.findViewById(R.id.tv_address);
             holder.distance=(TextView)vi.findViewById(R.id.tv_distance);
             holder.date=(TextView)vi.findViewById(R.id.tv_date);
             vi.setTag(holder);
         } else {
             holder = (ViewHolder) vi.getTag();
         }

         ListViewItem item=items.get(position);

         String ext="";
         if(item.thumbnailResource!="")
          ext=getFileExt(item.thumbnailResource);
         
          
         
          if(ext.contentEquals("jpg") || ext.contentEquals("png") || ext.contentEquals("jpeg") || ext.contentEquals("JPG") || ext.contentEquals("PNG") || ext.contentEquals("JPEG")){
      
          	holder.imgthumbnail.setScaleType(ScaleType.FIT_XY);
        //UrlImageViewHelper.setUrlDrawable(imgthumbnail,item.thumbnailResource,R.drawable.ic_launcher_large);
         
        
         ImageLoader.getInstance().displayImage(item.thumbnailResource, holder.imgthumbnail, options);
         

          
          
          }
          else if(item.image!=null){
          	//Bitmap thumb=Bitmap.createScaledBitmap(item.image, 100, 100, false); 
        	  
          	holder.imgthumbnail.setImageBitmap(item.image);
          	
          	
          	
          	}
          else
          	holder.imgthumbnail.setImageResource(R.drawable.ic_launcher);
         
          if(lang.getLanguage().equals(("el"))){
          	holder.txtTilte.setText(item.name);
          	holder.txtAddresse.setText(item.address);
          	holder.distance.setText(item.distance +"Χλμ.");
          	}
  		else{
  			holder.txtTilte.setText(item.name_en);
  			
  			holder.txtAddresse.setText(item.address_en);
  			holder.distance.setText(item.distance +"Km");
  			}
          
         
          if(item.stars.equals("5")){
          	holder.txtTilte.setCompoundDrawablesWithIntrinsicBounds( 0, 0, 0,R.drawable.five_stars);
          }else if(item.stars.equals("4")){
          	holder.txtTilte.setCompoundDrawablesWithIntrinsicBounds( 0, 0, 0,R.drawable.four_stars);
          }else if(item.stars.equals("3")){
          	holder.txtTilte.setCompoundDrawablesWithIntrinsicBounds( 0, 0, 0,R.drawable.three_stars);
          }else if(item.stars.equals("2")){
          	holder.txtTilte.setCompoundDrawablesWithIntrinsicBounds( 0, 0, 0,R.drawable.two_stars);
          }else if(item.stars.equals("1")){
          	holder.txtTilte.setCompoundDrawablesWithIntrinsicBounds( 0, 0, 0,R.drawable.one_stars);
          	
          }
          else
        	  holder.txtTilte.setCompoundDrawablesWithIntrinsicBounds( 0, 0, 0,R.drawable.zero_stars);
        
          holder.txtTilte.setTextColor(Color.parseColor("#FF7C3F"));
          if(isevent)
          	holder.date.setText(item.date);
          else
          	holder.date.setVisibility(View.GONE);	

         return vi;
    	
    	/*ViewHolder holder;
    	ListViewItem item=items.get(position);
    	
    	View vi=convertView;
    
        if(convertView==null)
            vi = inflater.inflate(R.layout.category_list_item, null);
        holder = new ViewHolder();
        holder.imgthumbnail=(ImageView)vi.findViewById(R.id.imageView1);
       
        holder.txtTilte=(TextView)vi.findViewById(R.id.tv_title);
       
        holder.txtAddresse=(TextView)vi.findViewById(R.id.tv_address);
        holder.distance=(TextView)vi.findViewById(R.id.tv_distance);
        holder.date=(TextView)vi.findViewById(R.id.tv_date);
       
       
        //TextView txtDistance=(TextView)vi.findViewById(R.id.txtdistance);
        //TextView txtLocation=(TextView)vi.findViewById(R.id.txtLocation);
        //imgthumbnail.setImageResource(item.thumbnailResource);


        
       
        
		//UrlImageViewHelper.setUrlDrawable(imgthumbnail, item.thumbnailResource);
       // imgthumbnail.setImageBitmap(bmp);
        
       
       String ext="";
       if(item.thumbnailResource!="")
        ext=getFileExt(item.thumbnailResource);
       
        
       
        if(ext.contentEquals("jpg") || ext.contentEquals("png") || ext.contentEquals("jpeg") || ext.contentEquals("JPG") || ext.contentEquals("PNG") || ext.contentEquals("JPEG")){
    
        	holder.imgthumbnail.setScaleType(ScaleType.FIT_XY);
      //UrlImageViewHelper.setUrlDrawable(imgthumbnail,item.thumbnailResource,R.drawable.ic_launcher_large);
       
       imageLoader.displayImage(item.thumbnailResource,holder.imgthumbnail);
       ImageLoader.getInstance().displayImage(item.thumbnailResource, holder.imgthumbnail, options);
       

        
        
        }
        else if(item.image!=null){
        	//Bitmap thumb=Bitmap.createScaledBitmap(item.image, 100, 100, false); 
        	
        	holder.imgthumbnail.setImageBitmap(item.image);
        	
        	
        	
        	}
        else
        	holder.imgthumbnail.setImageResource(R.drawable.ic_launcher);
       
        if(lang.getLanguage().equals(("el"))){
        	holder.txtTilte.setText(item.name);
        	holder.txtAddresse.setText(item.address);
        	holder.distance.setText(item.distance +"Χλμ.");
        	}
		else{
			holder.txtTilte.setText(item.name_en);
			
			holder.txtAddresse.setText(item.address_en);
			holder.distance.setText(item.distance +"Km");
			}
        if(item.stars.equals("5")){
        	holder.txtTilte.setCompoundDrawablesWithIntrinsicBounds( 0, 0, 0,R.drawable.five_stars);
        }else if(item.stars.equals("4")){
        	holder.txtTilte.setCompoundDrawablesWithIntrinsicBounds( 0, 0, 0,R.drawable.four_stars);
        }else if(item.stars.equals("3")){
        	holder.txtTilte.setCompoundDrawablesWithIntrinsicBounds( 0, 0, 0,R.drawable.three_stars);
        }else if(item.stars.equals("2")){
        	holder.txtTilte.setCompoundDrawablesWithIntrinsicBounds( 0, 0, 0,R.drawable.two_stars);
        }else if(item.stars.equals("1")){
        	holder.txtTilte.setCompoundDrawablesWithIntrinsicBounds( 0, 0, 0,R.drawable.one_stars);
        }
      
        holder.txtTilte.setTextColor(Color.parseColor("#FF7C3F"));
        if(isevent)
        	holder.date.setText(item.date);
        else
        	holder.date.setVisibility(View.GONE);	
        	
       
       // txtSubTilte.setTypeface(mytf);
       // txtDistance.setText(Double.toString(item.distance)+"km");
       // txtLocation.setText(item.location);
       // txtDistance.setTypeface(mytf);
       // txtLocation.setTypeface(mytf);
        
        
        
        return vi;  */
        
        
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
    
    public static int calculateInSampleSize(BitmapFactory.Options options,
            int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;    
        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and
            // keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }
    
    public static Bitmap decodeSampledBitmapFromResource(String strPath,int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(strPath, options);
        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options,reqWidth,
                reqHeight);
        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(strPath, options);
}

public static String getFileExt(String FileName)
{       
     return FileName.substring((FileName.lastIndexOf(".") + 1), FileName.length());
}

/*public Filter getFilter() {

    return new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            final FilterResults oReturn = new FilterResults();
            final ArrayList<ListViewItem> results = new ArrayList<ListViewItem>();
            //if (orig == null)
                orig = orig_location;
          
            if (constraint != null) {
                if (orig != null && orig.size() > 0) {
                    for (final ListViewItem g : orig) {
                        if (g.name.toLowerCase().contains(constraint.toString())|| g.tags.toLowerCase().contains(constraint.toString()) )
                            results.add(g);
                        else if(constraint.equals(" "))
                        	results.addAll(orig_location);	
                    }
                }
                oReturn.values = results;
            }
            return oReturn;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            items = (ArrayList<ListViewItem>) results.values;
            notifyDataSetChanged();
        }
    };
}*/

//get the filter for the list view
public Filter filter_location() {

	 return new Filter() {

	        @Override
	        protected FilterResults performFiltering(CharSequence constraint) {
	            final FilterResults oReturn = new FilterResults();
	            final ArrayList<ListViewItem> results = new ArrayList<ListViewItem>();
	            if (orig == null)
	                orig = items;	            
	           
	            
	            if (constraint != null) {
	                if (orig != null && orig.size() > 0) {
	                    for (final ListViewItem g : orig) {
	                    	
	                    	 if(lang.getLanguage().equals(("el"))){
	                        if (g.location.toLowerCase().equals(constraint.toString().toLowerCase()) )
	                            results.add(g);
	                        else if(constraint.equals(" ") ||constraint.equals("all") ){
	                        	results.clear();
	                        	results.addAll(started_list);	
	                        	}
	                        }else{
	                        	if (g.location_en.toLowerCase().equals(constraint.toString().toLowerCase()) )
		                            results.add(g);
		                        else if(constraint.equals(" ") ||constraint.equals("all") ){
		                        	results.clear();
		                        	results.addAll(started_list);	
		                        	}
	                        	
	                        	
	                        }
	                    }
	                }
	                oReturn.values = results;
	                orig_location=results;
	                
	               
	            }
	            return oReturn;
	        }

	        @SuppressWarnings("unchecked")
	        @Override
	        protected void publishResults(CharSequence constraint,
	                                      FilterResults results) {
	            items = (ArrayList<ListViewItem>) results.values;
	            notifyDataSetChanged();
	        }
	    };
	
}

//get the filter for the list view
public Filter getFilter() {

	 return new Filter() {

	        @Override
	        protected FilterResults performFiltering(CharSequence constraint) {
	            final FilterResults oReturn = new FilterResults();
	            final ArrayList<ListViewItem> results = new ArrayList<ListViewItem>();
	            //if (orig == null)
	                orig = items;
	            
	            
	            if (constraint != null) {
	                if (orig != null && orig.size() > 0) {
	                    for (final ListViewItem g : orig) {
	                    	
	                    	 if(lang.getLanguage().equals(("el"))){
	                        if (g.name.toLowerCase().contains(constraint.toString().toLowerCase()) || g.tags.toLowerCase().contains(constraint.toString().toLowerCase()))
	                            results.add(g);
	                        else if(constraint.equals(" ") ||constraint.equals("all") ){
	                        	results.clear();
	                        	results.addAll(started_list);	
	                        	}
	                        }else{
	                        	if (g.name_en.toLowerCase().contains(constraint.toString().toLowerCase()) || g.tags.toLowerCase().contains(constraint.toString().toLowerCase()))
		                            results.add(g);
		                        else if(constraint.equals(" ") ||constraint.equals("all") ){
		                        	results.clear();
		                        	results.addAll(started_list);	
		                        	}
	                        	
	                        	
	                        }
	                    }
	                }
	                oReturn.values = results;
	                orig_location=results;
	               
	            }
	            return oReturn;
	        }

	        @SuppressWarnings("unchecked")
	        @Override
	        protected void publishResults(CharSequence constraint,
	                                      FilterResults results) {
	            items = (ArrayList<ListViewItem>) results.values;
	            notifyDataSetChanged();
	        }
	    };
	
}

public Filter general_Filter(String filter) {

	final String tmp_filter=filter;
	 return new Filter() {

	        @Override
	        protected FilterResults performFiltering(CharSequence constraint) {
	            final FilterResults oReturn = new FilterResults();
	            final ArrayList<ListViewItem> results = new ArrayList<ListViewItem>();
	            if (orig == null)
	                orig = items;
	            
	            
	            if (constraint != null) {
	                if (orig != null && orig.size() > 0) {
	                    for (final ListViewItem g : orig) {
	                    	
	                    	 if(lang.getLanguage().equals(("el"))){
	                    	     	if(!tmp_filter.toLowerCase().equals("all")){
	    	                        	if ((g.name.toLowerCase().contains(constraint.toString().toLowerCase()) || g.tags.toLowerCase().contains(constraint.toString().toLowerCase())) && g.location.toLowerCase().equals(tmp_filter.toLowerCase()) )
	    		                            results.add(g);
	    	                        	else if (constraint.equals(" ") ||constraint.equals("all") && g.location.toLowerCase().equals(tmp_filter.toLowerCase()) )
	    	                        		results.add(g);
	    		                        
	    	                        	
	    	                        	}else{
	    	                        		
	    	                        		if (g.name_en.toLowerCase().contains(constraint.toString().toLowerCase()) || g.tags.toLowerCase().contains(constraint.toString().toLowerCase()) )
	    			                            results.add(g);
	    			                        else if(constraint.equals(" ") ||constraint.equals("all") ){
	    			                        	results.clear();
	    			                        	results.addAll(started_list);	
	    			                        	}
	    	                        		
	    	                        		
	    	                        	}
	                        }else{
	                        	
	                        	if(!tmp_filter.toLowerCase().equals("all")){
	                        	if ((g.name_en.toLowerCase().contains(constraint.toString().toLowerCase()) || g.tags.toLowerCase().contains(constraint.toString().toLowerCase())) && g.location_en.toLowerCase().equals(tmp_filter.toLowerCase()) )
		                            results.add(g);
	                        	else if (constraint.equals(" ") ||constraint.equals("all") && g.location_en.toLowerCase().equals(tmp_filter.toLowerCase()) )
	                        		results.add(g);
		                        
	                        	
	                        	}else{
	                        		
	                        		if (g.name_en.toLowerCase().contains(constraint.toString().toLowerCase()) || g.tags.toLowerCase().contains(constraint.toString().toLowerCase()) )
			                            results.add(g);
			                        else if(constraint.equals(" ") ||constraint.equals("all") ){
			                        	results.clear();
			                        	results.addAll(started_list);	
			                        	}
	                        		
	                        		
	                        	}
	                        	
	                        	
	                        }
	                    }
	                }
	                oReturn.values = results;
	                orig_location=results;
	                
	               
	            }
	            return oReturn;
	        }

	        @SuppressWarnings("unchecked")
	        @Override
	        protected void publishResults(CharSequence constraint,FilterResults results) {
	            items = (ArrayList<ListViewItem>) results.values;
	            
	            notifyDataSetChanged();
	            
	        }
	    };
	
}

public Filter general_Filter_subcat(String filter,String filter2) {

	final String tmp_filter=filter;
	final String tmp_filter2=filter2;
	 return new Filter() {

	        @Override
	        protected FilterResults performFiltering(CharSequence constraint) {
	            final FilterResults oReturn = new FilterResults();
	            final ArrayList<ListViewItem> results = new ArrayList<ListViewItem>();
	            if (orig == null)
	                orig = items;
	           
	            
	            
	            if (constraint != null) {
	                if (orig != null && orig.size() > 0) {
	                    for (final ListViewItem g : orig) {
	                    	
	                    	 if(lang.getLanguage().equals(("el"))){
	                    	      	if(!tmp_filter.toLowerCase().equals("all")){
	    	                        	if ((g.name.toLowerCase().contains(constraint.toString().toLowerCase()) || g.tags.toLowerCase().contains(constraint.toString().toLowerCase())) && g.location.toLowerCase().equals(tmp_filter.toLowerCase()) )
	    		                            results.add(g);
	    	                        	else if (constraint.equals(" ") ||constraint.equals("all") && g.location.toLowerCase().equals(tmp_filter.toLowerCase()) )
	    	                        		results.add(g);
	    		                        
	    	                        	
	    	                        	}else{
	    	                        		
	    	                        		if (g.name.toLowerCase().contains(constraint.toString().toLowerCase()) || g.tags.toLowerCase().contains(constraint.toString().toLowerCase()) )
	    			                            results.add(g);
	    			                        else if(constraint.equals(" ") ||constraint.equals("all") ){
	    			                        	results.clear();
	    			                        	results.addAll(started_list);	
	    			                        	}
	    	                        		
	    	                        		
	    	                        	}
	                        }else{
	                        	
	                        	if(!tmp_filter.toLowerCase().equals("all")){
	                        	if ((g.name_en.toLowerCase().contains(constraint.toString().toLowerCase()) || g.tags.toLowerCase().contains(constraint.toString().toLowerCase())) && g.location_en.toLowerCase().equals(tmp_filter.toLowerCase()) )
		                            results.add(g);
	                        	else if (constraint.equals(" ") ||constraint.equals("all") && g.location_en.toLowerCase().equals(tmp_filter.toLowerCase()) )
	                        		results.add(g);
		                        
	                        	
	                        	}else{
	                        		
	                        		if (g.name_en.toLowerCase().contains(constraint.toString().toLowerCase()) || g.tags.toLowerCase().contains(constraint.toString().toLowerCase()) )
			                            results.add(g);
			                        else if(constraint.equals(" ") ||constraint.equals("all") ){
			                        	results.clear();
			                        	results.addAll(started_list);	
			                        	}
	                        		
	                        		
	                        	}
	                        	
	                        	
	                        }
	                    }
	                }
	                ArrayList<ListViewItem> orig2 = new ArrayList<ListViewItem>();
	                final ArrayList<ListViewItem> results2 = new ArrayList<ListViewItem>();
	               	                
	                orig2.addAll(results);
	                if (orig2 != null && orig2.size() > 0) {
	                    for (final ListViewItem g : orig2) {
	                    	
	                    	 if(lang.getLanguage().equals(("el"))){
	                        if (g.sub_category.trim().toLowerCase().equals(tmp_filter2.trim()))
	                            results2.add(g);
	                        else if(tmp_filter2.equals("all") ){
	                        	results2.clear();
	                        	results2.addAll(results);	
	                        	}
	                        }else{
	                        	
	                        	
	                        	 if (g.sub_category_en.trim().toLowerCase().equals(tmp_filter2.trim()))
	 	                            results2.add(g);
	 	                        else if(tmp_filter2.equals("all") ){
	 	                        	results2.clear();
	 	                        	results2.addAll(results);	
	 	                        	}
	                        	
	                        	
	                        }
	                    }
	                }
	               
	                
	                oReturn.values = results2;
	                orig_location=results2;
	              
	            }
	            return oReturn;
	        }

	        @SuppressWarnings("unchecked")
	        @Override
	        protected void publishResults(CharSequence constraint,
	                                      FilterResults results) {
	            items = (ArrayList<ListViewItem>) results.values;
	            notifyDataSetChanged();
	        }
	    };
	
}

public void notifyDataSetChanged() {
    super.notifyDataSetChanged();
}




  

    //get the filter for the list view
}