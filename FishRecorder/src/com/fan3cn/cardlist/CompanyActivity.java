package com.fan3cn.cardlist;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.fan3cn.fishrecorder.Constants;
import com.fan3cn.fishrecorder.MainActivity;
import com.fan3cn.fishrecorder.R;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;



/**
 * @author Eric
 *
 */

public class CompanyActivity extends SherlockActivity {
	//listView
	private ListView listView;
	//adapter
	private CompanyAdapter adapter ;
	//company 对象数组
	private Company  [] model ;
	
	private static final String table = "company";
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_list);
        int menuId = getIntent().getIntExtra("menuId", 0);
        String table = Constants.table.get(menuId);
        SQLiteDatabase db =  MainActivity.getDbHelper().getReadableDatabase();
        Cursor cursor = db.query(table, null, null, null, null, null, null);
        model = new Company[cursor.getCount()]; 
        int i=0;
        while(cursor.moveToNext()){
        	int id = cursor.getInt(cursor.getColumnIndex("id"));
        	
        	String name = cursor.getString(cursor.getColumnIndex("name"));
        	String tel = cursor.getString(cursor.getColumnIndex("tel"));
        	String fax = cursor.getString(cursor.getColumnIndex("fax"));
        	String email = cursor.getString(cursor.getColumnIndex("email"));
        	String address = cursor.getString(cursor.getColumnIndex("address"));
        	String isDefault = cursor.getInt(cursor.getColumnIndex("is_default"))== 0 ? getResources().getString(R.string.no):getResources().getString(R.string.yes);
        	
        	Company company = new Company();
        	company.setId(id);
        	company.setName(name);
        	company.setPhone(tel);
        	company.setFax(fax);
        	company.setEmail(email);
        	company.setAddress(address);
        	company.setIs_default(isDefault);
        	
        	model[i] = company;
        	i++;
        }

        adapter = new CompanyAdapter(this,R.layout.list_row_company,model);
        adapter.setActionlistener(this);
        
        listView = (ListView)findViewById(R.id.list);
        listView.setAdapter(adapter);
	}

	public void onQuickItemSelected(int item, int row, Company company) {
		switch(item){
		//删除
		case Constants.ACTION_ITEM_ID_OPTION_1:
			deleteCompany(company);
			break;
		//修改
		case Constants.ACTION_ITEM_ID_OPTION_2:
			modifyCompany(company);
			break;
		//设为默认
		case Constants.ACTION_ITEM_ID_OPTION_3:
			setDefault(company);
			break;
		}
		
	}
	
	private void deleteCompany(Company company){
		SQLiteDatabase db =  MainActivity.getDbHelper().getWritableDatabase();
		db.delete(table, "id = ?", new String[]{company.getId() + ""});
		db.close();
		this.onCreate(null);
		
		Toast.makeText(getApplicationContext(), "删除成功", Toast.LENGTH_SHORT).show();
	}
	
	private void modifyCompany(Company company){
		Intent intent = new Intent();
		intent.setClass(this, CompanyModifyActivity.class);
		intent.putExtra("company", company);
		startActivity(intent);
	}
	
	private void setDefault(Company company){
		
		if(company.getIs_default().equals(getResources().getString(R.string.yes))){
			Toast.makeText(getApplicationContext(), "已经是默认公司，无需设置", Toast.LENGTH_SHORT).show();
			return;
		}else{
			SQLiteDatabase db =  MainActivity.getDbHelper().getWritableDatabase();

			//把是默认的置为非默认
			ContentValues cv1 = new ContentValues();
			cv1.put("is_default", 0);
			db.update(table, cv1, "is_default=?", new String[]{1+""});
			
			//置为默认值
			ContentValues cv2 = new ContentValues();
			cv2.put("is_default", 1);
			db.update(table, cv2, "id=?", new String[]{company.getId()+""});
			
			this.onCreate(null);
			Toast.makeText(getApplicationContext(), "设置成功", Toast.LENGTH_SHORT).show();
			db.close();
		}
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
        int NONE = com.actionbarsherlock.view.Menu.NONE;
		
        menu.add(NONE, 0, NONE, "Refresh")
            .setIcon(R.drawable.ic_action_refresh)
            .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

        menu.add(NONE, 1, NONE, "Search")
            .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
    
        return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		
		switch (id) {
		case 0:
			this.onCreate(null);
			break;
		case 1:
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}
}
