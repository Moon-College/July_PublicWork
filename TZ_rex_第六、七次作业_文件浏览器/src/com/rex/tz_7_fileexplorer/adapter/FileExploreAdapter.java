package com.rex.tz_7_fileexplorer.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rex.tz_7_fileexplorer.R;
import com.rex.tz_7_fileexplorer.data.FileInfo;
import com.rex.tz_7_fileexplorer.util.LruCacheUtil;

public class FileExploreAdapter extends BaseAdapter
{
    Context context = null;
    List<FileInfo> fileInfos = new ArrayList<FileInfo>(0);

    public FileExploreAdapter(Context context, List<FileInfo> fileInfos)
    {
        this.context = context;
        this.fileInfos = fileInfos;
    }

    @Override
    public int getCount()
    {
        return fileInfos.size();
    }

    @Override
    public Object getItem(int position)
    {
        return fileInfos.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        FileInfo fileInfo = fileInfos.get(position);
        ViewHolder holder = new ViewHolder();
        LinearLayout layout = null;
        if (null == convertView)
        {
            layout = (LinearLayout) LayoutInflater.from(context).inflate(
                    R.layout.file_list_item, parent, false);
            holder.imageView = (ImageView) layout.findViewById(R.id.iv_icon);
            holder.textView = (TextView) layout.findViewById(R.id.tv_path);
            layout.setTag(holder);
            convertView = layout;
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        setBitMap(holder.imageView, fileInfo);
        holder.textView.setText(fileInfo.getName());

        return convertView;
    }

    /**
     * 设置ImageView的图片
     * 
     * @param imageView
     * @param fileInfo
     */
    private void setBitMap(ImageView imageView, FileInfo fileInfo)
    {
        // 为文件夹
        if (!fileInfo.isFile())
        {
            imageView.setImageBitmap(getBitmapByRes(R.drawable.dirs));
        }

        // 为普通的文件
        else if (!fileInfo.isPicture())
        {
            imageView.setImageBitmap(getBitmapByRes(R.drawable.file));
        }

        // 为图片
        else
        {
            // 设置压缩图片
            imageView.setImageBitmap(LruCacheUtil.get(fileInfo.getWholePath()));
        }
    }

    private Bitmap getBitmapByRes(int drawableId)
    {

        AsyncTask<Integer, Void, Bitmap> asyncTask = new AsyncTask<Integer, Void, Bitmap>()
        {

            @Override
            protected Bitmap doInBackground(Integer... params)
            {
                int drawableId = params[0];

                return BitmapFactory.decodeResource(context.getResources(),
                        drawableId);
            }
        };

        try
        {
            return asyncTask.execute(drawableId).get();
        }
        catch (InterruptedException | ExecutionException e)
        {
            Log.i("getBitmapByRes()：执行decode普通图片的异步任务", e.toString());
        }

        return null;
    }

    private static class ViewHolder
    {
        ImageView imageView = null;
        TextView textView = null;
    }
}
