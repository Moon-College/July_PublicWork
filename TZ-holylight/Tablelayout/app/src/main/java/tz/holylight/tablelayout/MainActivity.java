package tz.holylight.tablelayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TableLayout tableLayout=new TableLayout(this);

        setContentView(tableLayout);
        int[] colors={0xffff0000,0xff003300,0xff0000ff};
        for (int i = 0; i < 10; i++) {
            TableRow tr=new TableRow(this);
            for (int j = 0; j < 3; j++) {
                TextView textView=new TextView(this);
                TableRow.LayoutParams params=new TableRow.LayoutParams(0,-2,1);
                textView.setText("textview"+j);
                textView.setBackgroundColor(colors[j]);
                textView.setLayoutParams(params);
                tr.addView(textView);
            }
            tableLayout.addView(tr);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
