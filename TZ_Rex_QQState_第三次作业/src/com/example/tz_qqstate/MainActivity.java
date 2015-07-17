package com.example.tz_qqstate;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;

public class MainActivity extends Activity
{

    private ActionBar actionbar;
    private RadioButton msgButton;
    private RadioButton contactButton;
    private RadioButton stateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionbar = getActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);

        msgButton = (RadioButton) findViewById(R.id.message);
        contactButton = (RadioButton) findViewById(R.id.contact);
        stateButton = (RadioButton) findViewById(R.id.state);
        View.OnClickListener listener = new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                if (v == msgButton)
                {
                    msgButton.setChecked(true);
                    contactButton.setChecked(false);
                    stateButton.setChecked(false);
                }
                else if (v == contactButton)
                {
                    contactButton.setChecked(true);
                    msgButton.setChecked(false);
                    stateButton.setChecked(false);
                }
                else if (v == stateButton)
                {
                    stateButton.setChecked(true);
                    msgButton.setChecked(false);
                    contactButton.setChecked(false);
                }
            }
        };
        
        msgButton.setOnClickListener(listener);
        contactButton.setOnClickListener(listener);
        stateButton.setOnClickListener(listener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
