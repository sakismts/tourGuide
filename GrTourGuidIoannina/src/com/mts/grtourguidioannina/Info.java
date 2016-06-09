package com.mts.grtourguidioannina;



import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

public class Info extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_LEFT_ICON);
		setContentView(R.layout.info);
		getWindow().setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.ic_launcher);
		Locale current = getResources().getConfiguration().locale;
		TextView info=(TextView)findViewById(R.id.tv_info);
		TextView com_label=(TextView)findViewById(R.id.textView1);
		info.setMovementMethod(LinkMovementMethod.getInstance());
		
		if(current.getLanguage().equals(("el"))){
			info.setText(Html.fromHtml("<b>Σας καλωσορίζουμε στον Τουριστικό Οδηγό Ιωαννίνων.</b><br><br>Μία εφαρμογή που σας καθοδηγεί και σας προτείνει λύσεις κατά τη διάρκεια της διαμονής σας στην πόλη των Ιωαννίνων. Μπορείτε να βρείτε Ξενοδοχεία, Εστιατόρια, Τσιπουράδικα, Καφετέριες, Ζαχαροπλαστεία, Μπαρ, Ταξιδιωτικά Γραφεία, Μαγαζιά για ψώνια, Kομμωτήρια και Iνστιτούτα ομορφιάς, Εταιρείες ενοικίασης αυτοκινήτων καθώς επίσης σας προτείνουμε προορισμούς και αξιοθέατα που αξίζει κάποιος να επισκεφτεί.Επίσης σας παρέχονται πληροφορίες για εκδηλώσεις και event που λαμβάνουν χώρα στην πόλη των Ιωαννίνων.Και επειδή η πόλη των Ιωαννίνων είναι μια φοιτητική πόλη δίνονται πληροφορίες σχετικά με τις  γραμματείες κάθε σχολής, τα κυλικεία του πανεπιστημίου, το πρόγραμμα σίτισης της λέσχης, τις τελευταίες ανακοινώσεις του πανεπιστημίου καθώς επίσης και για  τις φοιτητικές εκδηλώσεις."));
			com_label.setText("Στοιχεία Επικοινωνίας");
			}
		else{
			info.setText(Html.fromHtml("<b>Welcome to Ioannina Greek tour Guides.</b><br><br>Ioannina Greek Tour Guides is an application that guides you and proposes solutions during your stay in the city of Ioannina. You can find Hotels, Restaurants, Coffee Shops, Bars, Travel Agencies, Clothing Shops Hair salons and Beauty Institutes, Car rental companies. Also it recommends destinations and attractions that anyone deserves to visit .Furthermore it provides information about the events that are taking place in the city of Ioannina. Also for the college students provides information about the university departments communication, university coffee shops, the feeding program , the recent announcements of the university as well as for student events."));
			com_label.setText("Communication");
			}
		
		
	
		ImageButton fb=(ImageButton)findViewById(R.id.facebook);
		
		fb.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		
		case R.id.facebook:
			Intent fb=new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/pages/Ioannina-Greek-Tour-Guides/793926673982610"));
			startActivity(fb);
			break;
		
		}
		
	}

}
