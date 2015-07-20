package com.example.qqlayoutdemo;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup.LayoutParams;


/**
 * 存储View节点属性信息
 * @author Think
 *
 */
public class ViewAttrs {
	private View view;
	private LayoutParams params;
	
	
	private int resImg;
	private boolean hasResImg;  //设置标志，判断是否设置resImg
	
	private int resText;
	private boolean hasResText;
	
	private String hint;
	private boolean hasHint;
	
	private float hintSize;
	private boolean hasHintSize;
	
	private float textSize;
	private boolean hasTextSize;
	
	private int backgroudColor;
	private boolean hasBackgroudColor;
	
	private Drawable resDrawable;
	private boolean hasResDrawable;
	
	private int offset;
	private boolean  hasOffset;
	
	private int id;
	private boolean hasId;
	
	private String name;
	private boolean  hasName;
	
	private int gravity;
	private boolean hasGravity;
	
	private float marginTop;	
	private float magrinLeft;
	private float marginRight;
	private float marginBottom;
	private boolean hasMargin;
	
	
	
	public ViewAttrs(View view){
		this.view=view;
	}

	public void setParams(LayoutParams params) {
		this.params = params;
	}



	public void setResImg(int resImg) {
		hasResImg=true;
		this.resImg = resImg;
	}



	public void setResText(int resText) {
		hasResText=true;
		this.resText = resText;
	}



	public void setBackgroudColor(int backgroudColor) {
		hasBackgroudColor=true;
		this.backgroudColor = backgroudColor;
	}



	public void setResDrawable(Drawable resDrawable) {
		hasResDrawable=true;
		this.resDrawable = resDrawable;
	}



	public void setOffset(int offset) {
		hasOffset=true;
		this.offset = offset;
	}



	public void setId(int id) {
		hasId=true;
		this.id = id;
	}



	public void setName(String name) {
		hasName=true;		
		this.name = name;
	}



	public void setHint(String hint) {
		hasHint=true;
		this.hint = hint;
	}


	public View getView() {
		return view;
	}

	public LayoutParams getParams() {
		return params;
	}

	public int getResImg() {
		return resImg;
	}

	public boolean isHasResImg() {
		return hasResImg;
	}

	public int getResText() {
		return resText;
	}

	public boolean isHasResText() {
		return hasResText;
	}

	public int getBackgroudColor() {
		return backgroudColor;
	}

	public boolean isHasBackgroudColor() {
		return hasBackgroudColor;
	}

	public Drawable getResDrawable() {
		return resDrawable;
	}

	public boolean isHasResDrawable() {
		return hasResDrawable;
	}

	public int getOffset() {
		return offset;
	}

	public boolean isHasOffset() {
		return hasOffset;
	}

	public int getId() {
		return id;
	}

	public boolean isHasId() {
		return hasId;
	}

	public String getName() {
		return name;
	}

	public boolean isHasName() {
		return hasName;
	}

	public String getHint() {
		return hint;
	}

	public boolean isHasHint() {
		return hasHint;
	}

	public float getMarginTop() {
		return marginTop;
	}



	public float getMagrinLeft() {
		return magrinLeft;
	}



	public float getMarginRight() {
		return marginRight;
	}



	public float getMarginBottom() {
		return marginBottom;
	}

	public void setMargin(float marginLeft,float marginRight,float marginTop,float marginBottom) {
		hasMargin=true;
		this.magrinLeft=marginLeft;
		this.marginRight=marginRight;
		this.marginTop=marginTop;
		this.marginBottom=marginBottom;
		
	}

	public boolean isHasMargin() {
		return hasMargin;
	}

	public float getHintSize() {
		return hintSize;
	}

	public boolean isHasHintSize() {
		return hasHintSize;
	}

	public float getTextSize() {
		return textSize;
	}

	public boolean isHasTextSize() {
		return hasTextSize;
	}

	public void setHintSize(float hintSize) {
		hasHintSize=true;
		this.hintSize = hintSize;
	}

	public void setTextSize(float textSize) {
		hasTextSize=true;
		this.textSize = textSize;
	}
	
	
	public void setGravity(int gravity) {
		hasGravity=true;
		this.gravity = gravity;
	}

	public int getGravity() {
		return gravity;
	}

	public boolean isHasGravity() {
		return hasGravity;
	}



}
