package tz.holyligt.fileexplorer;

import android.os.Environment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private String SDroot;
    private List<ExplorerItem> explorerItemList;
    private FileAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView list= (ListView) findViewById(R.id.listView);
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            SDroot=Environment.getExternalStorageDirectory().getAbsolutePath();
        }else {
            Toast.makeText(this,"SD卡加载失败！",Toast.LENGTH_SHORT).show();
        }
        explorerItemList = new ArrayList<ExplorerItem>();
        adapter = new FileAdapter();
        list.setAdapter(adapter);
        if (SDroot!=null){
            getFilelist(SDroot,null);
        }

       list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               ExplorerItem backitem = explorerItemList.get(position);
               if (position == 0 &&backitem.isback) {
                   getFilelist(backitem.parentpath,backitem);
               } else {

                   if (backitem.isDir) {
                       backitem.name = "返回";
                       backitem.isback=true;
                       getFilelist(backitem.uri, backitem);
                   } else {

                   }
               }
           }

       });
    }

    private void getFilelist(String path,ExplorerItem backitem) {
        explorerItemList.clear();
        File file=new File(path);
        File[] files = file.listFiles();
        if (backitem!=null &&!path.equals(SDroot)){
            backitem.parentpath=file.getParentFile().getAbsolutePath();
            explorerItemList.add(backitem);
        }
        for (File fileitem :files) {

            ExplorerItem item=new ExplorerItem();
            if (fileitem.isDirectory()){
                item.iocnid= R.drawable.folder_yellow;
                item.isDir=true;

            }else if (fileitem.isFile()){
                 if (fileitem.getName().endsWith(".png")||fileitem.getName().endsWith(".jpg")||fileitem.getName().endsWith(".jpeg")){
                     item.iocnid=0;
                     item.isDir=false;

                 }else if (fileitem.getName().endsWith(".apk")){
                     item.iocnid=R.drawable.apk;
                     item.isDir=false;

                 }else {
                     item.iocnid=R.drawable.unknown;
                     item.isDir=false;
                 }
            }

            item.name=fileitem.getName();
            item.uri=fileitem.getAbsolutePath();
            explorerItemList.add(item);
        }
        adapter.notifyDataSetChanged();
    }

    class FileAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return explorerItemList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
           ViewHolder viewHolder=null;
            if (convertView==null){
               viewHolder=new ViewHolder();
               convertView=View.inflate(MainActivity.this, R.layout.list_item, null);
                viewHolder.filename= (TextView) convertView.findViewById(R.id.filename);
                viewHolder.fileicon= (ImageView) convertView.findViewById(R.id.fileicon);
                convertView.setTag(viewHolder);
           }else {
                viewHolder= (ViewHolder) convertView.getTag();
           }
            ExplorerItem item=explorerItemList.get(position);
            if (item.iocnid!=0){
                viewHolder.fileicon.setImageResource(item.iocnid);
                viewHolder.fileicon.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            }else {
                ImageLoader.getInstance().placeholder(R.drawable.thumbnail);
                ImageLoader.getInstance().loadImage(viewHolder.fileicon, item.uri);
            }
            viewHolder.filename.setText(item.name);
            return convertView;
        }
        class  ViewHolder{
            ImageView fileicon;
            TextView filename;
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
