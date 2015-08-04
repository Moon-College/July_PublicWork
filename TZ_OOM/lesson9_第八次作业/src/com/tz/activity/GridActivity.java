package com.tz.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;

public class GridActivity extends BaseActivity {

	private GridView mGridView;

	private ListAdapter mAdapter;

	private Handler mHandler = new Handler();
	private boolean mFinishedStart = false;

	private Runnable mRequestFocus = new Runnable() {
		public void run() {
			mGridView.focusableViewAvailable(mGridView);
		}
	};

	
	protected void setGridAdapter(ListAdapter adapter) {
		synchronized (this) {
			ensureGrid();
			mAdapter = adapter;
			mGridView.setAdapter(adapter);
		}
	}

	@Override
	public void onContentChanged() {
		// TODO Auto-generated method stub
		super.onContentChanged();
		Log.d("GridActivity", "onContentChanged");
		/*mGridView = (GridView)findViewById(R.id.grid_view);
        if (mGridView == null) {
            throw new RuntimeException(
                    "Your content must have a ListView whose id attribute is " +
                    "'R.id.grid_view'");
        }
        if (emptyView != null) {
            mList.setEmptyView(emptyView);
        }
        mGridView.setOnItemClickListener(mOnClickListener);
        if (mFinishedStart) {
            setGridAdapter(mAdapter);
        }
        mHandler.post(mRequestFocus);
        mFinishedStart = true;*/
		
		
	}
	
	
	/**
	 * Get the ListAdapter associated with this activity's ListView.
	 */
	public ListAdapter getGridAdapter() {
		return mAdapter;
	}

	/**
	 * …Ë÷√grid¡– ˝
	 * 
	 * @param numColumns
	 */
	protected void setNumColumns(int numColumns) {
		ensureGrid();
		mGridView.setNumColumns(numColumns);
	}

	@Override
	protected void onRestoreInstanceState(Bundle state) {
		ensureGrid();
		super.onRestoreInstanceState(state);
	}

	protected GridView getGridView() {
		ensureGrid();
		return mGridView;
	}

	/**
	 * 
	 */
	private void ensureGrid() {
		Log.d("GridActivity", "ensureGrid");
		if (mGridView != null) {
			return;
		}
		
//		setContentView(R.layout.activity_grid);
		try {
			mGridView = (GridView) setContent(R.layout.activity_grid);
			if (mGridView == null) {
				throw new RuntimeException("NullPointException ");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("classCastException " + e.getMessage());
		}

		mGridView.setOnItemClickListener(mOnClickListener);
		if (mFinishedStart) {
			setGridAdapter(mAdapter);
		}
		mHandler.post(mRequestFocus);
		mFinishedStart = true;

	}

    /**
     * @see Activity#onDestroy()
     */
    @Override
    protected void onDestroy() {
        mHandler.removeCallbacks(mRequestFocus);
        super.onDestroy();
    }
    
    /**
     * Set the currently selected list item to the specified
     * position with the adapter's data
     *
     * @param position
     */
    public void setSelection(int position) {
        mGridView.setSelection(position);
    }

    /**
     * Get the position of the currently selected list item.
     */
    public int getSelectedItemPosition() {
        return mGridView.getSelectedItemPosition();
    }
    
    /**
     * Get the cursor row ID of the currently selected list item.
     */
    public long getSelectedItemId() {
        return mGridView.getSelectedItemId();
    }

    
	/**
	 * This method will be called when an item in the list is selected.
	 * Subclasses should override. Subclasses can call
	 * getListView().getItemAtPosition(position) if they need to access the data
	 * associated with the selected item.
	 *
	 * @param l
	 *            The ListView where the click happened
	 * @param v
	 *            The view that was clicked within the ListView
	 * @param position
	 *            The position of the view in the list
	 * @param id
	 *            The row id of the item that was clicked
	 */
	protected void onGridItemClick(GridView l, View v, int position, long id) {

	}

	private AdapterView.OnItemClickListener mOnClickListener = new AdapterView.OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, View v, int position,
				long id) {
			onGridItemClick((GridView) parent, v, position, id);
		}
	};

	
}
