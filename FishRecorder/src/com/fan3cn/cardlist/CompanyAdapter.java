package com.fan3cn.cardlist;

import com.fan3cn.fishrecorder.Constants;
import com.fan3cn.fishrecorder.R;

import net.londatiga.android.ActionItem;
import net.londatiga.android.QuickAction;
import android.app.Activity;
import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author Eric
 *
 */
public class CompanyAdapter extends ArrayAdapter<Company>{
	

	Context context; 
	
	int layoutResourceId;  
	
	private int mSelectedRow = 0;
	
	Company data[] = null;
	
	private CompanyActivity actionlistener;

	public CompanyAdapter(Context context, int layoutResourceId, Company data[]) {
		
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
	    this.data = data;
	}
	    
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
		ViewHolder holder = null;
		
		if(rowView == null){
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			rowView = inflater.inflate(layoutResourceId, parent, false);
			
			holder = new ViewHolder();
			
			holder.company_name = (TextView)rowView.findViewById(R.id.company_name);
			holder.company_phone = (TextView)rowView.findViewById(R.id.company_phone);
			holder.company_fax = (TextView)rowView.findViewById(R.id.company_fax);
			holder.company_address = (TextView)rowView.findViewById(R.id.company_address);
			holder.company_email = (TextView)rowView.findViewById(R.id.company_email);
			holder.company_is_default = (TextView)rowView.findViewById(R.id.company_is_default);
			holder.icon = (ImageView) rowView.findViewById(R.id.menu);		
			
            //Declare our action items
			ActionItem deleteItem = new ActionItem(Constants.ACTION_ITEM_ID_OPTION_1, 
					context.getResources().getString(R.string.action_item_menu_delete), 
					context.getResources().getDrawable(R.drawable.ic_action_discard));
			
			ActionItem modifyItem = new ActionItem(Constants.ACTION_ITEM_ID_OPTION_2, 
					context.getResources().getString(R.string.action_item_menu_modify), 
					context.getResources().getDrawable(R.drawable.ic_action_edit));	
			
			ActionItem defaultItem = new ActionItem(Constants.ACTION_ITEM_ID_OPTION_3, 
					context.getResources().getString(R.string.action_item_menu_default), 
					context.getResources().getDrawable(R.drawable.ic_action_accept));	
			
			final QuickAction mQuickAction 	= new QuickAction(context); 
			mQuickAction.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
				@Override
				public void onItemClick(QuickAction quickAction, int pos, int actionId) {
					/*** Set Listener to get selected quick action  and row number  ***/
					if(actionlistener !=null){
						actionlistener.onQuickItemSelected(actionId , mSelectedRow, data[position]);
					}
				}
	    	});
	       		
			mQuickAction.addActionItem(deleteItem);
			mQuickAction.addActionItem(modifyItem);
			mQuickAction.addActionItem(defaultItem);
			
			holder.icon.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View view) {
					mSelectedRow = position;
					mQuickAction.show(view);
				}
			});
			
			rowView.setTag(holder);
		}else{
			holder = (ViewHolder)rowView.getTag();
		}
		
		Company company = data[position];
		
		String namePrefix = rowView.getResources().getString(R.string.company_frame_hint_name)+ Constants.COLON +"    ";
		String phonePrefix = rowView.getResources().getString(R.string.company_frame_hint_tel)+ Constants.COLON + "   ";
		String faxPrefix = rowView.getResources().getString(R.string.company_frame_hint_fax)+ Constants.COLON + "   ";
		String emailPrefix = rowView.getResources().getString(R.string.company_frame_hint_email)+ Constants.COLON + "   ";
		String addressPrefix = rowView.getResources().getString(R.string.company_frame_hint_address)+ Constants.COLON + "   ";
		String isDefaultPrefix = rowView.getResources().getString(R.string.company_frame_is_default)+ Constants.COLON + "   ";
		
		
 	    Spannable nameSpan = new SpannableString(namePrefix + company.getName());
 	    Spannable phoneSpan = new SpannableString(phonePrefix+company.getPhone());
 	    Spannable faxSpan = new SpannableString(faxPrefix+company.getFax());
 	    Spannable emailSpan = new SpannableString(emailPrefix+company.getEmail());
 	    Spannable addressSpan = new SpannableString(addressPrefix+company.getAddress());
 	    Spannable isDefaultSpan = new SpannableString(isDefaultPrefix+company.getIs_default());
 	    
 	    nameSpan.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, namePrefix.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //粗体
 	    nameSpan.setSpan(new StyleSpan(android.graphics.Typeface.ITALIC), namePrefix.length(), nameSpan.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //粗体
 	    
 	    phoneSpan.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, phonePrefix.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //粗体
 	    phoneSpan.setSpan(new StyleSpan(android.graphics.Typeface.ITALIC), phonePrefix.length(), phoneSpan.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //粗体
 	    
 	    faxSpan.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, faxPrefix.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //粗体
 	    faxSpan.setSpan(new StyleSpan(android.graphics.Typeface.ITALIC), faxPrefix.length(), faxSpan.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //粗体
 	    
 	    emailSpan.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, emailPrefix.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //粗体
 	    emailSpan.setSpan(new StyleSpan(android.graphics.Typeface.ITALIC), emailPrefix.length(), emailSpan.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //粗体
 	    
 	    addressSpan.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, addressPrefix.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //粗体
 	    addressSpan.setSpan(new StyleSpan(android.graphics.Typeface.ITALIC), addressPrefix.length(), addressSpan.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //粗体
  	    
 	    isDefaultSpan.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, isDefaultPrefix.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //粗体
  	    isDefaultSpan.setSpan(new StyleSpan(android.graphics.Typeface.ITALIC), isDefaultPrefix.length(), isDefaultSpan.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //粗体
 	    
  	    holder.company_name.setText(nameSpan);
		holder.company_phone.setText(phoneSpan);
		holder.company_fax.setText(faxSpan);
		holder.company_email.setText(emailSpan);
		holder.company_address.setText(addressSpan);
		holder.company_is_default.setText(isDefaultSpan);
 	    
 	    
 	    return rowView;
	}
	    
	public CompanyActivity getActionlistener() {
		return actionlistener;
	}
	
	public void setActionlistener(CompanyActivity actionlistener) {
		this.actionlistener = actionlistener;
	}
	
	static class ViewHolder{
		
		TextView company_name;
        TextView company_phone;
        TextView company_fax;
        TextView company_email;
        TextView company_address;
        TextView company_is_default;
        ImageView icon;
	}
		
}
