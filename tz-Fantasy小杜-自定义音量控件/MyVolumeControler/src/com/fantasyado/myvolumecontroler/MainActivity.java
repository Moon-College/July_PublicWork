package com.fantasyado.myvolumecontroler;

 


 
import android.app.Activity; 
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.PopupWindow;


 public class MainActivity extends  Activity {

    private View rl;
	private PopupWindow pWindow;
	private View volume;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
       getActionBar().setDisplayHomeAsUpEnabled(true);
       getActionBar().setLogo(R.drawable.home);
       setContentView(R.layout.activity_main);
       rl=findViewById(R.id.rl_layout);
       View view=View.inflate(this, R.layout.volume_layout, null);
		  volume = view.findViewById(R.id.volume);
		  pWindow = new PopupWindow(view  ,LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
		pWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.littlebg));
    }

	 @Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
			if(keyCode==KeyEvent.KEYCODE_VOLUME_DOWN||keyCode==KeyEvent.KEYCODE_VOLUME_UP)

			pWindow.showAsDropDown( volume, rl.getWidth()/2, rl.getHeight()/3);

		return super.onKeyDown(keyCode, event);
	}
	 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
         
        switch (item.getItemId()) {
		case R.id.volume:
			
			pWindow.showAsDropDown( volume, rl.getWidth()/2, rl.getHeight()/2);
			
			break;

		default:
			break;
		}
      
        
        return super.onOptionsItemSelected(item);
    }
    
	 
}
