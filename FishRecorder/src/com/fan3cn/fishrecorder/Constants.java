/**
 * 
 */
package com.fan3cn.fishrecorder;

import android.util.SparseArray;

/**
 * @author Eric
 *
 */
public class Constants {

	/** 左侧菜单编号 */
	public static final int MENUID_1 = 1;
	public static final int MENUID_2 = 2;
	public static final int MENUID_3 = 3;
	public static final int MENUID_4 = 4;
	public static final int MENUID_5 = 5;
	public static final int MENUID_6 = 6;
	public static final int MENUID_7 = 7;
	public static final int MENUID_8 = 8;
	public static final int MENUID_9 = 9;
	public static final int MENUID_10 = 10;
	
	/** 左侧菜单图标资源 */
	public static final  int[] menuDrawable ={
		R.drawable.user,
		R.drawable.user,
		R.drawable.user,
		R.drawable.user,
		R.drawable.user,
		R.drawable.user,
		R.drawable.user,
		R.drawable.user,
		R.drawable.user,
		R.drawable.user
	};
	
	/** 左侧菜单布局文件 */
	public static final int[] menuLayout = {
		R.layout.frame_company,
		R.layout.frame_ship,
		R.layout.frame_crew,
		R.layout.frame_fish_type,
		R.layout.frame_catch_type,
		R.layout.frame_uncatch_reason,
		R.layout.frame_port,
		R.layout.frame_port,
		R.layout.frame_port,
		R.layout.frame_port
	};
	
	/** key:menuId,value:table_name*/
	public static final SparseArray<String> table = new SparseArray<String>();;
	
	static{
		table.put(1, "company");
		table.put(2, "ship");
		table.put(3, "crew");
		table.put(4, "company");
		table.put(5, "company");
		table.put(5, "company");
		table.put(7, "company");
		table.put(8, "company");
		table.put(9, "company");
	}
	
	public static final String COLON = ":";
	
	
	public static final int ACTION_ITEM_ID_OPTION_1 = 0;
	public static final int ACTION_ITEM_ID_OPTION_2 = 1;
	public static final int ACTION_ITEM_ID_OPTION_3 = 2;
	

}
