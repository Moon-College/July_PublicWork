package tz.holyligt.fileexplorer;

import android.content.DialogInterface;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
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
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private String SDroot;
    private List<ExplorerItem> explorerItemList;
    private FileAdapter adapter;
    private PopupWindow popupWindow;
    private View root;
    private View delete;
    private View xiangqing;
    private View copy;
    private View move;


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
        root = findViewById(R.id.root);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               ExplorerItem backitem = explorerItemList.get(position);
               if (position == 0 && backitem.isback) {
                   getFilelist(backitem.parentpath, backitem);
               } else {

                   if (backitem.isDir) {
                       backitem.name = "返回";
                       backitem.isback = true;
                       getFilelist(backitem.uri, backitem);
                   } else {

                   }
               }
           }

       });

        final View popview = View.inflate(MainActivity.this, R.layout.poplayout, null);
        delete = popview.findViewById(R.id.delete_btn);
        xiangqing = popview.findViewById(R.id.xiangqi_btn);
        copy = popview.findViewById(R.id.copy_btn);
        move = popview.findViewById(R.id.move_btn);

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
          @Override
          public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
              ExplorerItem backitem = explorerItemList.get(position);
              if (position == 0 && backitem.isback) {
                  return false;
              } else {
                  int[] location = new int[2];
                  view.getLocationOnScreen(location);
                  int x = location[0];
                  int y = location[1];
                  if (popupWindow == null) {

                      popupWindow = new PopupWindow(MainActivity.this);
                      popupWindow.setWidth(300);
                      popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                      popupWindow.setFocusable(true);
                      popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg));
                      popupWindow.setContentView(popview);
                      move.setOnClickListener(new PopWinClickListener(position));
                      copy.setOnClickListener(new PopWinClickListener(position));
                      delete.setOnClickListener(new PopWinClickListener(position));
                      xiangqing.setOnClickListener(new PopWinClickListener(position));
                      popupWindow.showAsDropDown(view, 200, -100);
                  } else {
                      popupWindow.showAsDropDown(view, 200, -100);
                  }
                  return true;
              }
          }
      });

    }

    class PopWinClickListener implements View.OnClickListener{
        private final int postion;

        public  PopWinClickListener(int postion){
        this.postion=postion;

        }
        @Override
        public void onClick(View v) {
           switch (v.getId()){
               case R.id.delete_btn:
                 final ExplorerItem item=  explorerItemList.get(postion);
                   AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                   builder.setTitle("你确定要删除这个文件"+(item.isDir?"夹么":"么"));
                   builder.setNegativeButton("确认", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which) {
                           if (item.isDir){
                               delDir(item.uri,item);
                           }else {
                               delFile(item.uri,item);
                           }
                       }
                   });
                   builder.setNeutralButton("取消",null);
                   builder.show();
                   break;
               case R.id.xiangqi_btn:
                   break;
               case R.id.copy_btn:
                   break;
               case R.id.move_btn:
                   break;
           }
        }
    }



    public void delFile(String path, ExplorerItem item){
        File file=new File(path);
        if(file.exists()&&file.isFile()){
            file.delete();
            explorerItemList.remove(item);
            popupWindow.dismiss();
            adapter.notifyDataSetChanged();

        }

    }

    public void delDir(String path, ExplorerItem item){
        File dir=new File(path);
        if(dir.exists()){
            File[] tmp=dir.listFiles();
            for(int i=0;i<tmp.length;i++){
                if(tmp[i].isDirectory()){
                    delDir(path+"/"+tmp[i].getName(), item);
                }
                else{
                    tmp[i].delete();
                }
            }
            dir.delete();
            explorerItemList.remove(item);
            popupWindow.dismiss();
            adapter.notifyDataSetChanged();

        }
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
                viewHolder.fileicon.setTag(0);
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
