package com.mts.grtourguidioannina;





import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Article extends Activity implements OnClickListener{
	String button_link;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.article_layout);
		setTitle("News");
		TextView Title=(TextView)findViewById(R.id.tvTitle);
		WebView Desc=(WebView)findViewById(R.id.webView_desc);
		ImageView image=(ImageView)findViewById(R.id.im_pic);
		Button link=(Button)findViewById(R.id.btn_link);
		link.setOnClickListener(this);
		button_link=getIntent().getStringExtra("article");
		Title.setText(Html.fromHtml(getIntent().getStringExtra("title")));
		//Title.setText(getIntent().getStringExtra("title"));
		String desc=getIntent().getStringExtra("desc");
		
		WebSettings settings = Desc.getSettings();
		settings.setDefaultTextEncodingName("utf-8");
		//webview.setBackgroundColor(0);
		String summary = "<html><head><title>Title of the document</title></head><body bgcolor=\"#f0f0f0\" style=\"background-color:#f0f0f0;\">"+desc+"</body></html>";
		Desc.loadData(summary,  "text/html; charset=utf-8", "utf-8");
				}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(button_link));
		
		startActivity(i);
	}
	

}
