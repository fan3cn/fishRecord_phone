package com.fan3cn.fishrecorder;


import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.Toast;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.fan3cn.cardlist.CompanyActivity;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.CanvasTransformer;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class MainActivity extends SlidingFragmentActivity {

	//menuFragment
	protected ListFragment mFrag;
	
	//contentFragment
	protected Fragment cFrag;
	
	public static View view;
	
	private static DBHelper dbHelper;
	
	private CanvasTransformer mTransformer;
	
	private static int menuId;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setTitle(R.string.app_name);
		
		setDbHelper(new DBHelper(MainActivity.this));
		
		
		// set the Above View
		setContentView(R.layout.frame_content);

		if (savedInstanceState != null){
			cFrag = getSupportFragmentManager().getFragment(savedInstanceState, "mContent");
		}else{
			if (cFrag == null)
				cFrag = new ContentFragment();
			Bundle bundle = new Bundle();
			bundle.putInt("layout", R.layout.frame_company);
			cFrag.setArguments(bundle);
			menuId = 1;
		}
		
		
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.content_frame, cFrag)
		.commit();
		
		mTransformer = new CanvasTransformer(){
			@Override
			public void transformCanvas(Canvas canvas, float percentOpen) {
				float scale = (float) (percentOpen*0.25 + 0.75);
				canvas.scale(scale, scale, canvas.getWidth()/2, canvas.getHeight()/2);
			}
		};

		
		// set the Behind View
		setBehindContentView(R.layout.frame_menu);
		if (savedInstanceState == null) {
			FragmentTransaction t = this.getSupportFragmentManager().beginTransaction();
			mFrag = new MenuListFragment();
			t.replace(R.id.menu_frame, mFrag);
			t.commit();
		} else {
			mFrag = (ListFragment)this.getSupportFragmentManager().findFragmentById(R.id.menu_frame);
		}
		
		// customize the SlidingMenu
		SlidingMenu sm = getSlidingMenu();
		sm.setShadowWidthRes(R.dimen.shadow_width);
		sm.setShadowDrawable(R.drawable.shadow);
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		sm.setFadeDegree(0.35f);
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		setSlidingActionBarEnabled(false);
		sm.setBehindScrollScale(0.0f);
		sm.setBehindCanvasTransformer(mTransformer);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("layout", cFrag.getArguments().getInt("layout"));
		getSupportFragmentManager().putFragment(outState, "mContent", cFrag);
		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		final int mainId = android.R.id.home;
		Intent intent = new Intent(); 

		switch (id) {
		case mainId:
			toggle();
			break;
		case 0:
			intent.setClass(this, CompanyActivity.class);
			intent.putExtra("menuId", menuId);
			startActivity(intent); 
			//this.finish();
			//Toast.makeText(getApplicationContext(), "item 0"  , Toast.LENGTH_SHORT).show();
			break;
//		case 1:
//			intent.setClass(this, CompanyActivity.class);
//			startActivity(intent); 
//			Toast.makeText(getApplicationContext(), "item 1"  , Toast.LENGTH_SHORT).show();
//			break;
//		case 2:
//			Toast.makeText(getApplicationContext(), "item 2"  , Toast.LENGTH_SHORT).show();
//			break;
		}
		
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
        int NONE = com.actionbarsherlock.view.Menu.NONE;
		
		//boolean isLight = false;

//        menu.add(NONE, 0, NONE, "Save")
//            .setIcon(isLight ? R.drawable.ic_compose_inverse : R.drawable.ic_compose)
//            .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

        menu.add(NONE, 0, NONE, "显示已有记录")
            .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
 
//        menu.add(NONE, 2, NONE, "Clear")
//        	.setIcon(isLight ? R.drawable.ic_refresh_inverse : R.drawable.ic_refresh)
//        	.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
//        
        return true;
	}
	
	
	public void switchContent(Fragment fragment, String title, int pos) {
		cFrag = fragment;
		menuId = pos +1;
		this.setTitle(title);
		FragmentManager fragMan = getSupportFragmentManager();
		
		fragMan.beginTransaction()
		.replace(R.id.content_frame, fragment)
		.commit();
		
		getSlidingMenu().showContent();

		
		mFrag = new MenuListFragment();
		Bundle bundle = new Bundle();
		bundle.putInt("pos", pos);
		mFrag.setArguments(bundle);
		this.getSupportFragmentManager().beginTransaction().replace(R.id.menu_frame, mFrag).commit();
		
		getSlidingMenu().showMenu();
	}

	/**
	 * @return the dbHelper
	 */
	public static DBHelper getDbHelper() {
		return dbHelper;
	}

	/**
	 * @param dbHelper the dbHelper to set
	 */
	public static void setDbHelper(DBHelper dbHelper) {
		MainActivity.dbHelper = dbHelper;
	}
	
	
	
}
