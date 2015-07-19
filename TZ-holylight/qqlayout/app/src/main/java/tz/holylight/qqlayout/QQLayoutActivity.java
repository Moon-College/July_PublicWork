package tz.holylight.qqlayout;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by Administrator on 2015/7/18.
 */
public class QQLayoutActivity extends Activity {

    private int SCREEN_WIDTH;
    private int SCREEN_HEIGHT;
    private int HorizontalMargin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        setContentView(root);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        SCREEN_WIDTH = dm.widthPixels;
        SCREEN_HEIGHT = dm.heightPixels;

        HorizontalMargin = DensityUtil.dip2px(this, 12);
        LinearLayout title = new LinearLayout(this);
        LinearLayout.LayoutParams titleparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(this, 48));
        title.setOrientation(LinearLayout.HORIZONTAL);
        title.setLayoutParams(titleparams);
        title.setBackgroundResource(R.color.lightbule);
        ImageView iv_usericon = new ImageView(this);
        iv_usericon.setImageResource(R.drawable.usericon);
        LinearLayout.LayoutParams userparams = new LinearLayout.LayoutParams(DensityUtil.dip2px(this, 36), DensityUtil.dip2px(this, 36));
        userparams.gravity = Gravity.CENTER_VERTICAL;
        userparams.setMargins(DensityUtil.dip2px(this, 12), 0, 0, 0);

        iv_usericon.setLayoutParams(userparams);
        title.addView(iv_usericon);
        root.addView(title);
        TextView tv_title = new TextView(this);
        tv_title.setText("动态");
        LinearLayout.LayoutParams titletxtparams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);

        tv_title.setLayoutParams(titletxtparams);
        tv_title.setTextSize(16);
        tv_title.setGravity(Gravity.CENTER);
        tv_title.setTextColor(getResources().getColor(R.color.whitle));
        title.addView(tv_title);
        TextView righttxt = new TextView(this);
        righttxt.setText("更多");
        LinearLayout.LayoutParams righttxtxtparams = new LinearLayout.LayoutParams(DensityUtil.dip2px(this, 44), -1);
        righttxtxtparams.setMargins(0, 0, DensityUtil.dip2px(this, 8), 0);

        righttxt.setLayoutParams(righttxtxtparams);

        righttxt.setTextSize(16);
        righttxt.setGravity(Gravity.CENTER_VERTICAL);
        righttxt.setTextColor(getResources().getColor(R.color.whitle));
        title.addView(righttxt);

        ScrollView sollview = new ScrollView(this);

        LinearLayout.LayoutParams sollviewparams = new LinearLayout.LayoutParams(-1, -1, 1);
        sollview.setLayoutParams(sollviewparams);
        root.addView(sollview);

        RadioGroup tabbar = new RadioGroup(this);
        tabbar.setOrientation(LinearLayout.HORIZONTAL);
        tabbar.setBackgroundColor(getResources().getColor(R.color.tabbarbg));
        LinearLayout.LayoutParams tabparams = new LinearLayout.LayoutParams(-1, DensityUtil.dip2px(this, 56));

        tabbar.setLayoutParams(tabparams);
        root.addView(tabbar);
        RelativeLayout smsrl = new RelativeLayout(this);
        int margin = (SCREEN_WIDTH - 3 * DensityUtil.dip2px(this, 56)) / 6;
        RadioGroup.LayoutParams rlparams = new RadioGroup.LayoutParams(DensityUtil.dip2px(this, 56), -1);
        rlparams.setMargins(margin, 0, margin, 0);


        RadioButton smsbtn = new RadioButton(this);

//        RelativeLayout.LayoutParams btnparams=new RelativeLayout.LayoutParams(DensityUtil.dip2px(this, 56),DensityUtil.dip2px(this, 56));
        smsbtn.setButtonDrawable(null);
//        btnparams.addRule(RelativeLayout.CENTER_IN_PARENT);
        smsbtn.setLayoutParams(rlparams);
        smsbtn.setBackgroundResource(R.drawable.smsselector);


//        smsrl.addView(smsbtn);
        tabbar.addView(smsbtn);
        RelativeLayout contactsrl = new RelativeLayout(this);
        contactsrl.setLayoutParams(rlparams);
        RadioButton contact = new RadioButton(this);
        contact.setLayoutParams(rlparams);
        contact.setButtonDrawable(null);
        contact.setBackgroundResource(R.drawable.contactselector);
//        contactsrl.addView(contact);
        tabbar.addView(contact);
        RelativeLayout pluginrl = new RelativeLayout(this);
        pluginrl.setLayoutParams(rlparams);
        RadioButton pluginbtn = new RadioButton(this);

        pluginbtn.setLayoutParams(rlparams);
        pluginbtn.setBackgroundResource(R.drawable.plactselector);
        pluginbtn.setButtonDrawable(null);
//        pluginrl.addView(pluginbtn);
        pluginbtn.setChecked(true);
        tabbar.addView(pluginbtn);

        LinearLayout content = new LinearLayout(this);
        content.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        content.setOrientation(LinearLayout.VERTICAL);
        content.setBackgroundResource(R.color.backgroudbgcolor);
        sollview.addView(content);
        LinearLayout searchbar = new LinearLayout(this);
        searchbar.setBackgroundResource(R.drawable.search_boder);
        LinearLayout.LayoutParams searchbarparam = new LinearLayout.LayoutParams(-1, DensityUtil.dip2px(this, 32));
        searchbarparam.setMargins(HorizontalMargin, DensityUtil.dip2px(this, 6), HorizontalMargin, DensityUtil.dip2px(this, 6));
        searchbar.setLayoutParams(searchbarparam);
        content.addView(searchbar);
        searchbar.setGravity(Gravity.CENTER);
        ImageView searchicon = new ImageView(this);
        searchicon.setImageResource(R.drawable.skin_searchbar_icon);
        TextView searchtxt = new TextView(this);
        searchtxt.setTextColor(getResources().getColor(R.color.textnormal));
        searchtxt.setText("优衣库");
        searchtxt.setTextSize(14);
        searchtxt.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams textlayoutparams = new LinearLayout.LayoutParams(-2, -1);
        searchtxt.setLayoutParams(textlayoutparams);
        LinearLayout.LayoutParams imglayoutparams = new LinearLayout.LayoutParams(-2, -2);
        searchicon.setLayoutParams(imglayoutparams);
        searchbar.addView(searchicon);
        searchbar.addView(searchtxt);
        View view = new View(this);
        view.setLayoutParams(new LinearLayout.LayoutParams(-1, 1));
        view.setBackgroundResource(R.color.bodercolor);
        content.addView(view);
        LinearLayout group = new LinearLayout(this);
        group.setOrientation(LinearLayout.HORIZONTAL);
        group.setLayoutParams(new LinearLayout.LayoutParams(-1, DensityUtil.dip2px(this, 84)));
        group.setBackgroundColor(getResources().getColor(R.color.whitle));
        content.addView(group);
        int[] ids = new int[]{R.id.img1, R.id.img2, R.id.img3};
        int[] drables = new int[]{R.drawable.igs, R.drawable.eqc, R.drawable.iei};
        int[] textres = new int[]{R.string.qqzone_text, R.string.fj, R.string.xqbl};
        for (int i = 0; i < 3; i++) {
            RelativeLayout grouprel = new RelativeLayout(this);
            grouprel.setLayoutParams(new LinearLayout.LayoutParams(0, -1, 1));
            ImageView imageView = new ImageView(this);
            RelativeLayout.LayoutParams imglp = new RelativeLayout.LayoutParams(-2, -2);
            imglp.addRule(RelativeLayout.CENTER_HORIZONTAL);
            imglp.setMargins(0, DensityUtil.dip2px(this, 12), 0, DensityUtil.dip2px(this, 4));
            imageView.setLayoutParams(imglp);
            imageView.setId(ids[i]);
            imageView.setImageResource(drables[i]);
            grouprel.addView(imageView);
            TextView text = new TextView(this);
            text.setText(textres[i]);
            text.setTextSize(14);
            RelativeLayout.LayoutParams textpl = new RelativeLayout.LayoutParams(-2, -2);
            textpl.addRule(RelativeLayout.CENTER_HORIZONTAL);
            textpl.addRule(RelativeLayout.BELOW, ids[i]);
            text.setLayoutParams(textpl);
            grouprel.addView(text);
            group.addView(grouprel);
        }
        view = new View(this);
        view.setLayoutParams(new LinearLayout.LayoutParams(-1, 1));
        view.setBackgroundResource(R.color.bodercolor);
        content.addView(view);

        View view1 = new View(this);
        LinearLayout.LayoutParams viewpl = new LinearLayout.LayoutParams(-1, 1);
        viewpl.setMargins(0, DensityUtil.dip2px(this, 24), 0, 0);
        view1.setLayoutParams(viewpl);
        view1.setBackgroundResource(R.color.bodercolor);
        content.addView(view1);
        int[] groupiconres=new int[]{ R.drawable.gameicon,R.drawable.shopingicon,R.drawable.readicon,R.drawable.musicicon,R.drawable.activity};
        int[] grouptextres=new int[]{R.string.game,R.string.shoping,R.string.readtext,R.string.musictext,R.string.rmhd};
        for (int i = 0; i < 5; i++) {
            LinearLayout group1Ly = new LinearLayout(this);
            group1Ly.setLayoutParams(new LinearLayout.LayoutParams(-1, DensityUtil.dip2px(this, 48)));
            group1Ly.setGravity(Gravity.CLIP_VERTICAL);
            group1Ly.setBackgroundColor(getResources().getColor(R.color.whitle));
            group1Ly.setPadding(DensityUtil.dip2px(this,12),0,0,0);
            ImageView groupicon=new ImageView(this);
            LinearLayout.LayoutParams groupiconlp=new LinearLayout.LayoutParams(DensityUtil.dip2px(this,36),DensityUtil.dip2px(this,36));
            groupiconlp.gravity= Gravity.CENTER_VERTICAL;
            groupicon.setLayoutParams(groupiconlp);
            groupicon.setImageResource(groupiconres[i]);
            group1Ly.addView(groupicon);
            TextView grouptext=new TextView(this);
            grouptext.setText(grouptextres[i]);
            LinearLayout.LayoutParams grouptextlp=new LinearLayout.LayoutParams(0,-1,1);
            grouptextlp.gravity=Gravity.CENTER_VERTICAL;
            grouptextlp.setMargins(HorizontalMargin,0,0,0);
            grouptext.setLayoutParams(grouptextlp);
            grouptext.setGravity(Gravity.CENTER_VERTICAL);
            group1Ly.addView(grouptext);
            ImageView grouparrow=new ImageView(this);
            grouparrow.setImageResource(R.drawable.skin_icon_arrow_right_normal);
            LinearLayout.LayoutParams grouparrowlp=new LinearLayout.LayoutParams(-2,-2);
            grouparrowlp.gravity=Gravity.CENTER_VERTICAL;
            grouparrowlp.setMargins(0,0,HorizontalMargin,0);
            grouparrow.setLayoutParams(grouparrowlp);
            group1Ly.addView(grouparrow);
            content.addView(group1Ly);
            view = new View(this);
            view.setLayoutParams(new LinearLayout.LayoutParams(-1, 1));
            view.setBackgroundResource(R.color.bodercolor);
            content.addView(view);
        }
        view1 = new View(this);
        view1.setLayoutParams(viewpl);
        view1.setBackgroundResource(R.color.bodercolor);
        content.addView(view1);
        int[] groupiconres1=new int[]{ R.drawable.fj,R.drawable.play,R.drawable.tc,R.drawable.zb};
        int[] grouptextres1=new int[]{R.string.fjtext,R.string.chihetext,R.string.tcfw,R.string.zbjtext};
        for (int i = 0; i < 4; i++) {
            LinearLayout group1Ly = new LinearLayout(this);
            group1Ly.setLayoutParams(new LinearLayout.LayoutParams(-1, DensityUtil.dip2px(this,48)));
            group1Ly.setGravity(Gravity.CLIP_VERTICAL);
            group1Ly.setBackgroundColor(getResources().getColor(R.color.whitle));
            group1Ly.setPadding(DensityUtil.dip2px(this,12),0,0,0);
            ImageView groupicon=new ImageView(this);
            LinearLayout.LayoutParams groupiconlp=new LinearLayout.LayoutParams(DensityUtil.dip2px(this,36),DensityUtil.dip2px(this,36));
            groupiconlp.gravity= Gravity.CENTER_VERTICAL;
            groupicon.setLayoutParams(groupiconlp);
            groupicon.setImageResource(groupiconres1[i]);
            group1Ly.addView(groupicon);
            TextView grouptext=new TextView(this);
            grouptext.setText(grouptextres1[i]);
            LinearLayout.LayoutParams grouptextlp=new LinearLayout.LayoutParams(0,-1,1);
            grouptextlp.gravity=Gravity.CENTER_VERTICAL;
            grouptextlp.setMargins(HorizontalMargin,0,0,0);
            grouptext.setLayoutParams(grouptextlp);
            grouptext.setGravity(Gravity.CENTER_VERTICAL);
            group1Ly.addView(grouptext);
            ImageView grouparrow=new ImageView(this);
            grouparrow.setImageResource(R.drawable.skin_icon_arrow_right_normal);
            LinearLayout.LayoutParams grouparrowlp=new LinearLayout.LayoutParams(-2,-2);
            grouparrowlp.gravity=Gravity.CENTER_VERTICAL;
            grouparrowlp.setMargins(0,0,HorizontalMargin,0);
            grouparrow.setLayoutParams(grouparrowlp);
            group1Ly.addView(grouparrow);
            content.addView(group1Ly);
            view = new View(this);
            view.setLayoutParams(new LinearLayout.LayoutParams(-1, 1));
            view.setBackgroundResource(R.color.bodercolor);
            content.addView(view);
        }
    }
}
