package com.hacket.filemanager.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hacket.filemanager.R;
import com.hacket.filemanager.bean.FileInfo;

/**
 * Created by zengfansheng on 2015/7/26 0026.
 */
public class FileManagerViewHolder extends BaseViewHolder<FileInfo> {

    private ImageView mIvFileIcon;
    private TextView mTvFileName;

    public FileManagerViewHolder(Context mContext) {
        super(mContext);
    }

    @Override
    public View newView(Context mContext) {
        final View view = View.inflate(mContext, R.layout.list_item_filemanager, null);
        mIvFileIcon = (ImageView) view.findViewById(R.id.iv_file_folder);
        mTvFileName = (TextView) view.findViewById(R.id.tv_filename);
        return view;
    }

    @Override
    public void bindView(FileInfo data, int position) {
        if (data != null) {

            if (data.isRootRir()) {
                this.getmRootView().setVisibility(View.GONE);
            }

            String filename = data.getFilename();
            boolean isFoolder = data.isFoolder();
            boolean imgType = data.isImgType();

            mTvFileName.setText(filename);

            if (isFoolder) {
                mIvFileIcon.setImageResource(R.drawable.folder);
            } else {
                if (imgType) {
                    // Bitmap bm = BitmapFactory.decodeFile(data.getFileurl());
//
//                    Bitmap bm = FileManagerUtil.sacleBitmap(data.getFileurl(), FileManagerUtil.dip2px(mContext, 60),
//                            FileManagerUtil.dip2px(mContext, 60));
                    mImageLoader.display(mIvFileIcon, data.getFileurl());
                } else {
                    mIvFileIcon.setImageResource(R.drawable.file);

                }
            }
        }
    }

}
