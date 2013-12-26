package com.fan3cn.fishrecorder;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MenuListFragment extends ListFragment {
	
	public String [] fishTypes;
	
	public String [] menuList;
	
	public static int pos = -1;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Bundle bundle = getArguments();
		if(null != bundle){
			pos = bundle.getInt("pos");
		}
		return inflater.inflate(R.layout.list, null);
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		menuList = getResources().getStringArray(R.array.menuList);
		SampleAdapter adapter = new SampleAdapter(getActivity());
		
		for(int i=0; i<menuList.length;i++){
			adapter.add(new SampleItem(menuList[i], Constants.menuDrawable[i]));
		}
		
		setListAdapter(adapter);
	}

	private class SampleItem {
		public String tag;
		public int iconRes;
		public SampleItem(String tag, int iconRes) {
			this.tag = tag; 
			this.iconRes = iconRes;
		}
	}

	public class SampleAdapter extends ArrayAdapter<SampleItem> {

		public SampleAdapter(Context context) {
			super(context, 0);
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null ) {
				convertView = LayoutInflater.from(getContext()).inflate(R.layout.row, null);
			}
			ImageView icon = (ImageView) convertView.findViewById(R.id.row_icon);
			icon.setImageResource(getItem(position).iconRes);
			TextView title = (TextView) convertView.findViewById(R.id.row_title);
			title.setText(getItem(position).tag);
			
			if(position == pos && position != 0){
				convertView.setBackgroundColor(getResources().getColor(R.color.myBlue));
				title.setTextColor(getResources().getColor(R.color.white));
			}
			
			return convertView;
		}

	}
	
	
	@Override
	public void onListItemClick(ListView lv, View v, int position, long id) {
		Fragment newContent = new ContentFragment();
		Bundle bundle = new Bundle();
		
		if(position <0 || position > Constants.menuLayout.length-1)
			return;
		
		int layout = Constants.menuLayout[position];
		bundle.putInt("layout", layout);
		bundle.putInt("menuId", position +1);
		newContent.setArguments(bundle);
		switchFragment(newContent, menuList[position], position);
	}

	// the meat of switching the above fragment
	private void switchFragment(Fragment fragment, String title, int pos) {
		if (getActivity() == null)
			return;
		
		if (getActivity() instanceof MainActivity) {
			MainActivity ma = (MainActivity) getActivity();
			ma.switchContent(fragment, title, pos);
		}
	}	
}
