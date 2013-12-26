/**
 * 
 */
package com.fan3cn.cardlist;

import com.actionbarsherlock.app.SherlockActivity;
import com.fan3cn.fishrecorder.MainActivity;
import com.fan3cn.fishrecorder.R;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @author Eric
 *
 */
public class CompanyModifyActivity extends SherlockActivity {

	private Company company;
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
        setContentView(R.layout.company_modify);
        company = (Company)getIntent().getSerializableExtra("company");
        
        EditText nameET = (EditText)findViewById(R.id.editText_com_name);
		EditText phoneET = (EditText)findViewById(R.id.editText_com_phone);
		EditText faxET = (EditText)findViewById(R.id.editText_com_fax);
		EditText emailET = (EditText)findViewById(R.id.editText_com_email);
		EditText addressET = (EditText)findViewById(R.id.editText_com_addr);
		CheckBox ckBox = (CheckBox)findViewById(R.id.checkBox_is_default);
		
        nameET.setText(company.getName());
        phoneET.setText(company.getPhone());
        faxET.setText(company.getFax());
        emailET.setText(company.getEmail());
        addressET.setText(company.getAddress());
        ckBox.setChecked(company.getIs_default() .equals(getResources().getString(R.string.yes) )? true:false);
        
		Button addButton = (Button)findViewById(R.id.button_add);
		
		addButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				EditText nameET = (EditText)findViewById(R.id.editText_com_name);
				EditText phoneET = (EditText)findViewById(R.id.editText_com_phone);
				EditText faxET = (EditText)findViewById(R.id.editText_com_fax);
				EditText emailET = (EditText)findViewById(R.id.editText_com_email);
				EditText addressET = (EditText)findViewById(R.id.editText_com_addr);
				CheckBox ckBox = (CheckBox)findViewById(R.id.checkBox_is_default);
				
				int isDefault = ckBox.isChecked() ? 1:0;
				
				String name = nameET.getText().toString();
				String phone = phoneET.getText().toString();
				String fax = faxET.getText().toString();
				String email = emailET.getText().toString();
				String address = addressET.getText().toString();
				
				
				
				if(name.isEmpty() || phone.isEmpty() || fax.isEmpty() || email.isEmpty() || address.isEmpty()){
					new AlertDialog.Builder(CompanyModifyActivity.this)
					.setTitle("提示")
					.setPositiveButton("确定", null)
					.setMessage("请填写完整！")
					.show();
					return;
				}
				
				SQLiteDatabase db =  MainActivity.getDbHelper().getWritableDatabase();
				ContentValues cv = new ContentValues();
				cv.put("name", name);
				cv.put("tel", phone);
				cv.put("fax", fax);
				cv.put("email", email);
				cv.put("address", address);
				cv.put("is_default", isDefault);
				db.update("company", cv, "id = ?", new String[]{company.getId() + ""});
		        //关闭当前数据库  
		        db.close();
				Toast.makeText(CompanyModifyActivity.this, "修改成功!"  , Toast.LENGTH_SHORT).show();
				finish();
			}
		});
		
		Button clearButton = (Button) findViewById(R.id.button_clear);
		
		clearButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText nameET = (EditText)findViewById(R.id.editText_com_name);
				EditText phoneET = (EditText)findViewById(R.id.editText_com_phone);
				EditText faxET = (EditText)findViewById(R.id.editText_com_fax);
				EditText emailET = (EditText)findViewById(R.id.editText_com_email);
				EditText addressET = (EditText)findViewById(R.id.editText_com_addr);
				CheckBox ckBox = (CheckBox)findViewById(R.id.checkBox_is_default);
				
				nameET.setText("");
				phoneET.setText("");
				faxET.setText("");
				emailET.setText("");
				addressET.setText("");
				ckBox.setChecked(false);
			}
		});
        
        
	}
	
}
