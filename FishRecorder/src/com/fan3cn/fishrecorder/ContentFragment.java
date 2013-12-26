/**
 * 
 */
package com.fan3cn.fishrecorder;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @author Eric
 *
 */
public class ContentFragment extends Fragment {

	/**
	 * 
	 */
	public ContentFragment() {
		// TODO Auto-generated constructor stub
	}
	
	public static int layout;
	
	public static int menuId;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		layout = getArguments().getInt("layout");
		if(layout <= 0){
			layout = R.layout.frame_company;
		}
		menuId = getArguments().getInt("menuId");
		return inflater.inflate(layout, container, false);
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}	
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
	    // TODO Auto-generated method stub
	    super.onViewCreated(view, savedInstanceState);
	    
		switch (layout) {
		case R.layout.frame_company:
			handleCompanyEvent(getView());
			break;
		case R.layout.frame_ship:
			handleShipEvent(getView());
		default:
			break;
		}
	}
	
	/**
	 * 添加公司信息
	 * @param view
	 */
	private void handleCompanyEvent(final View view ){
		Button addButton = (Button)view.findViewById(R.id.button_add);
		addButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				EditText nameET = (EditText)view.findViewById(R.id.editText_com_name);
				EditText phoneET = (EditText)view.findViewById(R.id.editText_com_phone);
				EditText faxET = (EditText)view.findViewById(R.id.editText_com_fax);
				EditText emailET = (EditText)view.findViewById(R.id.editText_com_email);
				EditText addressET = (EditText)view.findViewById(R.id.editText_com_addr);
				CheckBox ckBox = (CheckBox)view.findViewById(R.id.checkBox_is_default);
				
				int isDefault = ckBox.isChecked() ? 1:0;
				
				String name = nameET.getText().toString();
				String phone = phoneET.getText().toString();
				String fax = faxET.getText().toString();
				String email = emailET.getText().toString();
				String address = addressET.getText().toString();
				
				
				
				if(name.isEmpty() || phone.isEmpty() || fax.isEmpty() || email.isEmpty() || address.isEmpty()){
					new AlertDialog.Builder(getActivity())
					.setTitle("提示")
					.setPositiveButton("确定", null)
					.setMessage("请填写完整！")
					.show();
					return;
				}
				SQLiteDatabase db =  MainActivity.getDbHelper().getWritableDatabase();
				
				Cursor cursor = db.query(Constants.table.get(menuId), null, "is_default=?", new String[]{1+""}, null, null, null);
				
				if(isDefault == 1 && cursor.getCount() >0){
					//把是默认的置为非默认
					ContentValues cv1 = new ContentValues();
					cv1.put("is_default", 0);
					db.update(Constants.table.get(menuId), cv1, "is_default=?", new String[]{1+""});
				}
				
				ContentValues cv = new ContentValues();
				cv.put("name", name);
				cv.put("tel", phone);
				cv.put("fax", fax);
				cv.put("email", email);
				cv.put("address", address);
				cv.put("is_default", isDefault);
				db.insert(Constants.table.get(menuId), null, cv);
		        //关闭当前数据库  
		        db.close();
		        
				Toast.makeText(getActivity(), "添加成功!"  , Toast.LENGTH_SHORT).show();
			}
		});
		
		Button clearButton = (Button) view.findViewById(R.id.button_clear);
		
		clearButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText nameET = (EditText)view.findViewById(R.id.editText_com_name);
				EditText phoneET = (EditText)view.findViewById(R.id.editText_com_phone);
				EditText faxET = (EditText)view.findViewById(R.id.editText_com_fax);
				EditText emailET = (EditText)view.findViewById(R.id.editText_com_email);
				EditText addressET = (EditText)view.findViewById(R.id.editText_com_addr);
				CheckBox ckBox = (CheckBox)view.findViewById(R.id.checkBox_is_default);
				
				nameET.setText("");
				phoneET.setText("");
				faxET.setText("");
				emailET.setText("");
				addressET.setText("");
				ckBox.setChecked(false);
			}
		});

	}

	/**
	 * 处理渔船信息
	 * @param view
	 */
	private void handleShipEvent(final View view ){
		Button addButton = (Button)view.findViewById(R.id.button_add);
		addButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				EditText nameET = (EditText)view.findViewById(R.id.editText_ship_name);
				EditText nationET = (EditText)view.findViewById(R.id.editText_ship_nation);
				EditText registerET = (EditText)view.findViewById(R.id.editText_ship_register);
				EditText emailET = (EditText)view.findViewById(R.id.editText_ship_email);
				EditText ffaET = (EditText)view.findViewById(R.id.editText_ship_ffa);
				EditText wcpfcET = (EditText)view.findViewById(R.id.editText_ship_wcpfc);
				EditText radioET = (EditText)view.findViewById(R.id.editText_ship_radio);
				EditText licenseET = (EditText)view.findViewById(R.id.editText_ship_license);
				
				CheckBox ckBox = (CheckBox)view.findViewById(R.id.checkBox_is_default);
				
				int isDefault = ckBox.isChecked() ? 1:0;
				
				String name = nameET.getText().toString();
				String nation = nationET.getText().toString();
				String register = registerET.getText().toString();
				String email = emailET.getText().toString();
				String ffa = ffaET.getText().toString();
				String wcpfc = wcpfcET.getText().toString();
				String radio = radioET.getText().toString();
				String license = licenseET.getText().toString();
				
				if(name.isEmpty() || nation.isEmpty() || register.isEmpty() || email.isEmpty() || ffa.isEmpty() 
						||wcpfc.isEmpty() || radio.isEmpty() || license.isEmpty()){
					new AlertDialog.Builder(getActivity())
					.setTitle("提示")
					.setPositiveButton("确定", null)
					.setMessage("请填写完整！")
					.show();
					return;
				}
				
				SQLiteDatabase db =  MainActivity.getDbHelper().getWritableDatabase();
				
				if(isDefault == 1){
					Cursor cursor = db.query(Constants.table.get(menuId), null, "is_default=?", new String[]{1+""}, null, null, null);
					if(cursor.getCount() >0){
						//把是默认的置为非默认
						ContentValues cv1 = new ContentValues();
						cv1.put("is_default", 0);
						db.update(Constants.table.get(menuId), cv1, "is_default=?", new String[]{1+""});
					}
				}
				int companyId = 0;
				//找到默认公司
				Cursor cursor = db.query("company", null, "is_default=?", new String[]{1+""}, null, null, null);
				if(cursor.getCount() > 0){
					 while(cursor.moveToNext()){
						 companyId = cursor.getInt(cursor.getColumnIndex("id"));
						 break;
					 }
				}
				
				ContentValues cv = new ContentValues();
				cv.put("name", name);
				cv.put("company_id", companyId);
				cv.put("nation", nation);
				cv.put("resgiter_no", register);
				cv.put("email", email);
				cv.put("ffa_no", ffa);
				cv.put("wcpfc_no", wcpfc);
				cv.put("radio_tel", radio);
				cv.put("license", license);
				cv.put("is_default", isDefault);
				db.insert(Constants.table.get(menuId), null, cv);
		        //关闭当前数据库  
		        db.close();
		        
				Toast.makeText(getActivity(), "添加成功!"  , Toast.LENGTH_SHORT).show();
			}
		});
		
		Button clearButton = (Button) view.findViewById(R.id.button_clear);
		
		clearButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText nameET = (EditText)view.findViewById(R.id.editText_ship_name);
				EditText nationET = (EditText)view.findViewById(R.id.editText_ship_nation);
				EditText registerET = (EditText)view.findViewById(R.id.editText_ship_register);
				EditText emailET = (EditText)view.findViewById(R.id.editText_ship_email);
				EditText ffaET = (EditText)view.findViewById(R.id.editText_ship_ffa);
				EditText wcpfcET = (EditText)view.findViewById(R.id.editText_ship_wcpfc);
				EditText radioET = (EditText)view.findViewById(R.id.editText_ship_radio);
				EditText licenseET = (EditText)view.findViewById(R.id.editText_ship_license);
				
				CheckBox ckBox = (CheckBox)view.findViewById(R.id.checkBox_is_default);
				
				nameET.setText("");
				nationET.setText("");
				registerET.setText("");
				emailET.setText("");
				ffaET.setText("");
				emailET.setText("");
				radioET.setText("");
				wcpfcET.setText("");
				licenseET.setText("");
				ckBox.setChecked(false);
			}
		});

	}
	
}
