package com.house.baseactivity.common;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;

import com.house.baseactivity.R;

/**
 * 自定义GridView的Activity
 */
public class GridActivity extends Activity {
	protected ListAdapter mAdapter;

	private GridView gridView;
	private Handler mHandler = new Handler();
	private boolean mFinishedStart = false;

	private Runnable mRequestFocus = new Runnable() {
		public void run() {
			gridView.focusableViewAvailable(gridView);
		}
	};

	protected void onGridItemClick(GridView l, View v, int position, long id) {
	}

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
		mHandler.removeCallbacks(mRequestFocus);
		super.onDestroy();
	}

	private void ensureGrid() {
		if (gridView != null) {
			return;
		}
		setContentView(R.layout.activity_gridview);
	}

	@Override
	public void onContentChanged() {
		super.onContentChanged();
		// View emptyView = findViewById(com.android.internal.R.id.empty);
		gridView = (GridView) findViewById(R.id.gridview);
		
		if (gridView == null) {
			throw new RuntimeException(
					"Your content must have a ListView whose id attribute is "
							+ "'android.R.id.list'");
		}
		// if (emptyView != null) {
		// gridView.setEmptyView(emptyView);
		// }
		gridView.setOnItemClickListener(mOnClickListener);
		if (mFinishedStart) {
			setGridAdapter(mAdapter);
		}
		mHandler.post(mRequestFocus);
		mFinishedStart = true;
	}
	
	/**
	 * 设置列数
	 * @param numColumns
	 */
	protected void setNumColumns(int numColumns){
		gridView.setNumColumns(numColumns);
	}
	
	/**
	 * 设置间距
	 * @param width
	 */
	protected void setColumnsWidth(int width){
		gridView.setColumnWidth(width);
	}

	/**
	 * Provide the cursor for the list view.
	 */
	public void setGridAdapter(ListAdapter adapter) {
		synchronized (this) {
			ensureList();
			mAdapter = adapter;
			gridView.setAdapter(adapter);
		}
	}

	/**
	 * Set the currently selected list item to the specified position with the
	 * adapter's data
	 * 
	 * @param position
	 */
	public void setSelection(int position) {
		gridView.setSelection(position);
	}

	/**
	 * Get the position of the currently selected list item.
	 */
	public int getSelectedItemPosition() {
		return gridView.getSelectedItemPosition();
	}

	/**
	 * Get the cursor row ID of the currently selected list item.
	 */
	public long getSelectedItemId() {
		return gridView.getSelectedItemId();
	}

	/**
	 * Get the activity's list view widget.
	 */
	public GridView getListView() {
		ensureList();
		return gridView;
	}

	/**
	 * Get the ListAdapter associated with this activity's ListView.
	 */
	public ListAdapter getListAdapter() {
		return mAdapter;
	}

	private void ensureList() {
		if (gridView != null) {
			return;
		}
		setContentView(R.layout.activity_gridview);

	}

	private AdapterView.OnItemClickListener mOnClickListener = new AdapterView.OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, View v, int position,
				long id) {
			onGridItemClick((GridView) parent, v, position, id);
		}
	};
}
