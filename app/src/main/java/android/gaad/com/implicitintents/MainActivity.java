package android.gaad.com.implicitintents;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity
{
    private EditText mWebsiteEditText;
    private EditText mLocationEditText;
    private EditText mShareEditText;
    private String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebsiteEditText = (EditText) findViewById(R.id.website_edittext);
        mLocationEditText = (EditText) findViewById(R.id.location_edittext);
        mShareEditText = (EditText) findViewById(R.id.share_editext);
    }

    public void openWebsite(View view)
    {
        String url = mWebsiteEditText.getText().toString();
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        Intent chooser = Intent.createChooser(intent, "Select an app");
        if(intent.resolveActivity(getPackageManager()) != null)
        {
            startActivity(chooser);
        }
        else
        {
            Log.d(LOG_TAG, "Can't handle this");
        }
    }

    public void shareText(View view)
    {
        String txt = mShareEditText.getText().toString();
        String mimeType = "text/plain";
        ShareCompat.IntentBuilder.from(this).setType(mimeType).setChooserTitle("Share this text with:").setText(txt).startChooser();
    }

    public void openLocation(View view)
    {
        String loc = mLocationEditText.getText().toString();
        Uri addressUri = Uri.parse("geo:0,0?q=" +loc);
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);
        //intent.setPackage("com.google.android.apps.maps");
        if(intent.resolveActivity(getPackageManager()) != null)
        {
            startActivity(intent);
        }
        else
        {
            Log.d(LOG_TAG, "Can't handle this!");
        }

    }
}
