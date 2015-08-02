package com.tz.gridactivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

public class GridActivity extends Activity {

		/**
		 * 适配器对象
		 */
	    protected ArrayAdapter mAdapter;

	    /**
	     * GridView控件
	     */
	    protected GridView mGrid;
	    

	    /**
	     * 当GridView被点击时
	     * @param g The GridView where the click happened
	     * @param v The view that was clicked within the GridView
	     * @param position The position of the view in the list
	     * @param id The row id of the item that was clicked
	     */
	    protected void onGridItemClick(GridView g, View v, int position, long id) {
	    }

	    /**
	     * Ensures the list view has been created before Activity restores all
	     * of the view states.
	     *
	     *@see Activity#onRestoreInstanceState(Bundle)
	     */
	    @Override
	    protected void onRestoreInstanceState(Bundle state) {
	        ensureGrid();
	        super.onRestoreInstanceState(state);
	    }

	    /**
	     * @see Activity#onDestroy()
	     */
	    @Override
	    protected void onDestroy() {
	        super.onDestroy();
	    }

	    /**
	     * Updates the screen state (current list and other views) when the
	     * content changes.
	     *
	     * @see Activity#onContentChanged()
	     */
	    @Override
	    public void onContentChanged() {
	        super.onContentChanged();
	        View emptyView = findViewById(android.R.id.empty);
	        mGrid = (GridView)findViewById(R.id.grid);
	        if (mGrid == null) {
	            throw new RuntimeException(
	                    "Your content must have a ListView whose id attribute is " +
	                    "'android.R.id.list'");
	        }
	        if (emptyView != null) {
	        	mGrid.setEmptyView(emptyView);
	        }
	        mGrid.setOnItemClickListener(mOnClickListener);
	    }

	    public void setGridAdapter(ArrayAdapter adapter) {
	        synchronized (this) {
	            ensureGrid();
	            mAdapter = adapter;
	            mGrid.setAdapter(adapter);
	        }
	    }

	    /**
	     * Set the currently selected Grid item to the specified
	     * position with the adapter's data
	     *
	     * @param position
	     */
	    public void setSelection(int position) {
	    	mGrid.setSelection(position);
	    }

	    /**
	     * Get the position of the currently selected Grid item.
	     */
	    public int getSelectedItemPosition() {
	        return mGrid.getSelectedItemPosition();
	    }

	    /**
	     * Get the cursor row ID of the currently selected Grid item.
	     */
	    public long getSelectedItemId() {
	        return mGrid.getSelectedItemId();
	    }

	    /**
	     * Get the activity's grid view widget.
	     */
	    public GridView getGridView() {
	    	ensureGrid();
	        return mGrid;
	    }

	    /**
	     * Get the ArrayAdapter associated with this activity's GridView.
	     */
	    public ArrayAdapter getGridAdapter() {
	        return mAdapter;
	    }

	    private void ensureGrid() {
	        if (mGrid != null) {
	            return;
	        }
	        //将GridView布局添进来
	        setContentView(R.layout.grid_content_simple);
	    }

	    private AdapterView.OnItemClickListener mOnClickListener = new AdapterView.OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id)
	        {
	            onGridItemClick((GridView)parent, v, position, id);
	        }
	    };
}
