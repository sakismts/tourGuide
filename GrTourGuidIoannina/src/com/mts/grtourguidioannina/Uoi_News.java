package com.mts.grtourguidioannina;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class Uoi_News extends Activity implements OnItemClickListener {

    private ListView mRssListView;
    private NewsFeedParser mNewsFeeder;
    private List<RSSFeed> mRssFeedList;
    private RssAdapter mRssAdap;
    Locale current;
    boolean internet_con=true;

    private static final String NEWSLINK ="http://www.uoi.gr/rss4/announcements/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_list);
        TextView Cat=(TextView)findViewById(R.id.tv_cat_label);
		current = getResources().getConfiguration().locale;
        if(current.getLanguage().equals(("el"))){
			Cat.setText("Ανακοινώσεις");
			
			}
		else{
			Cat.setText("News");
			
			}

        mRssListView = (ListView) findViewById(R.id.lv_news);
        mRssFeedList = new ArrayList<RSSFeed>();
        new DoRssFeedTask().execute(NEWSLINK);
        mRssListView.setOnItemClickListener(this);
    }

    private class RssAdapter extends ArrayAdapter<RSSFeed> {
        private List<RSSFeed> rssFeedLst;

        public RssAdapter(Context context, int textViewResourceId, List<RSSFeed> rssFeedLst) {
            super(context, textViewResourceId, rssFeedLst);
            this.rssFeedLst = rssFeedLst;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = convertView;
            RssHolder rssHolder = null;
            if (convertView == null) {
                view = View.inflate(Uoi_News.this, R.layout.rss_list_item, null);
                rssHolder = new RssHolder();
                rssHolder.rssTitleView = (TextView) view.findViewById(R.id.txtTitle);
                rssHolder.rssDateView = (TextView) view.findViewById(R.id.txtdate);
                view.setTag(rssHolder);
            } else {
                rssHolder = (RssHolder) view.getTag();
            }
            RSSFeed rssFeed = rssFeedLst.get(position);
            rssHolder.rssTitleView.setText(rssFeed.getTitle());
            rssHolder.rssDateView.setText(rssFeed.getPubDate());
            return view;
        }
    }

    static class RssHolder {
        public TextView rssTitleView;
        public TextView rssDateView;
    }

    public class DoRssFeedTask extends AsyncTask<String, Void, List<RSSFeed>> {
        ProgressDialog prog;
        String jsonStr = null;
        Handler innerHandler;

        @Override
        protected void onPreExecute() {
            prog = new ProgressDialog(Uoi_News.this);
            prog.setMessage("Loading....");
            prog.show();
        }

        @Override
        protected List<RSSFeed> doInBackground(String... params) {
        	  mNewsFeeder = new NewsFeedParser(params[0]);
              
              mRssFeedList = mNewsFeeder.parse();
              internet_con=true;
              return mRssFeedList;
          /* try{
                mNewsFeeder = new NewsFeedParser(params[0]);
            
            mRssFeedList = mNewsFeeder.parse();
            internet_con=true;
            return mRssFeedList;
            }catch
            (Exception e) {
            	System.out.println("no connected");
            	internet_con=false;
            	
				
				//DataSQL info=new DataSQL(MainActivity.this);
				DataSQL info=new DataSQL(Uoi_News.this);
				
				info.open();
				
				mRssFeedList=info.getItems_col_news(mRssFeedList);
				info.close();
				
				return mRssFeedList;
            	
            }*/
        }

        @Override
        protected void onPostExecute(List<RSSFeed> result) {
            prog.dismiss();
            System.out.println(result);
            if(result!=null){
            DataSQL entry_delete= new DataSQL(Uoi_News.this);
			entry_delete.open();
			 entry_delete.delete_Delivery("college_news");
			 entry_delete.close();
                	
                	for (int i=0;i<result.size();i++){
       				 try{
       				  DataSQL entry= new DataSQL(Uoi_News.this);				        
				        entry.open();				       
				        entry.createEntry_Col_News(result.get(i).getTitle(),result.get(i).getDescription(),result.get(i).getPubDate());
				        entry.close();				     
       				 	}
				        catch (Exception e ){
				        e.printStackTrace();	
				        }
                	}
                	
                    mRssAdap = new RssAdapter(Uoi_News.this, R.layout.rss_list_item,
                            mRssFeedList);
                    int count = mRssAdap.getCount();
                    if (count != 0 && mRssAdap != null) {
                        mRssListView.setAdapter(mRssAdap);
                    }
                    
                   
                    
                    }else{
                    	
                    	 DataSQL info=new DataSQL(Uoi_News.this);
         				
         				info.open();
         				List<RSSFeed> tmp=new ArrayList<RSSFeed>();
         				tmp=info.getItems_col_news(tmp);
         				info.close();        				
         				
        				mRssAdap = new RssAdapter(Uoi_News.this, R.layout.rss_list_item,
                    			 tmp);
                         int count = mRssAdap.getCount();
                         if (count != 0 && mRssAdap != null) {
                             mRssListView.setAdapter(mRssAdap);
                         }
                        	 
                         
                    	
                    }
               
           
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View v, int position, long id) {
    	
    	Intent article=new Intent(Uoi_News.this,Article.class);
    	article.putExtra("title", mRssFeedList.get(position).getTitle());
    	article.putExtra("article", mRssFeedList.get(position).getLink());
    	article.putExtra("desc", mRssFeedList.get(position).getDescription());
    	startActivity(article);
    	
    }
}
