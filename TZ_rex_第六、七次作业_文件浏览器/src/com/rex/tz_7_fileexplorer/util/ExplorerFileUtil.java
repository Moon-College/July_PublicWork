package com.rex.tz_7_fileexplorer.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.util.Log;

import com.rex.tz_7_fileexplorer.data.FileInfo;

public class ExplorerFileUtil
{

    public static void explorer(Context context, List<FileInfo> fileInfos,
            File file)
    {
        File[] files = file.listFiles();
        if (null != files && files.length > 0)
        {
            for (File temp : files)
            {
                FileInfo fileInfo = new FileInfo();
                fileInfo.setName(temp.getName());
                fileInfo.setWholePath(getAbsPath(temp));

                if (temp.isDirectory())
                {
                    fileInfo.setPicture(false);
                    fileInfo.setFile(false);
                }
                else
                {
                    fileInfo.setFile(true);

                    if (isPicture(temp))
                    {
                        fileInfo.setPicture(true);
                    }
                    else
                    {
                        fileInfo.setPicture(false);
                    }
                }

                fileInfos.add(fileInfo);
            }
        }

    }

    private static boolean isPicture(File file)
    {
        String path = file.getAbsolutePath();

        return path.endsWith(".jpg") || path.endsWith(".png")
                || path.endsWith(".jpeg");
    }

    public static String getAbsPath(File file)
    {
        String path = null;
        try
        {
            path = file.getCanonicalPath();
        }
        catch (IOException e)
        {
            path = file.getAbsolutePath();
            Log.i("[explorer():]", e.toString());
        }

        return path;
    }
}
