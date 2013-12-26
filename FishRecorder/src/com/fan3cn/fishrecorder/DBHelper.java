/**
 * 
 */
package com.fan3cn.fishrecorder;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Eric
 *
 */
public class DBHelper extends SQLiteOpenHelper {
	
	private static final String dbName = "fish.db";
	private static final int version = 1;
	
	private static final String [] sqls ={
		"CREATE TABLE catch_log (v_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ship_id INTEGER, catch_type_id INTEGER, catch_date DATE, date1 TEXT, longitude_e_1 VARCHAR, longitude_w_1 VARCHAR, latitude_n_1 VARCHAR, latitude_s_1 VARCHAR, date2 TEXT, longitude_e_2 TEXT, longitude_w_2 TEXT, latitude_n_2 TEXT, latitude_s_2 TEXT, fish_type_id INTEGER, fish_num INTEGER, weight INTEGER, catch_times INTEGER)",
		
		"CREATE TABLE catch_type (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name VARCHAR, pos_num INTEGER, max_catch_num INTEGER, is_default INTEGER)",
		
		"CREATE TABLE company (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name VARCHAR, tel VARCHAR, fax VARCHAR, email VARCHAR, address VARCHAR, is_default BOOLEAN DEFAULT 0)",

		"CREATE TABLE crew (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ship_id INTEGER, name VARCHAR, position VARCHAR, email VARCHAR, address VARCHAR, is_captain INTEGER DEFAULT 0)",
		
		"CREATE TABLE fish_type (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name TEXT, start_weight INTEGER, end_weight INTEGER, is_target INTEGER DEFAULT 0)",
		
		"CREATE TABLE ship (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, company_id INTEGER, email VARCHAR, name VARCHAR, nation VARCHAR, resgiter_no VARCHAR, ffa_no VARCHAR, wcpfc_no VARCHAR, radio_tel VARCHAR, license VARCHAR, is_default INTEGER DEFAULT 0)",
		
		"CREATE TABLE ship_record (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ship_id INTEGER, start_port VARCHAR, start_time DATE, end_port VARCHAR, end_time DATE)",
		
		"CREATE TABLE uncatch_log (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ship_id INTEGER, reason VARCHAR, start_time DATE, end_time DATE)",
		
		"CREATE TABLE uncatch_reason (ship_id INTEGER, reason_cn TEXT, reason_en TEXT)"
	};
	

	/**
	 * @param context
	 * @param name
	 * @param factory
	 * @param version
	 */
	public DBHelper(Context context) {
		super(context, dbName, null, version);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 * @param name
	 * @param factory
	 * @param version
	 * @param errorHandler
	 */
	public DBHelper(Context context, String name, CursorFactory factory,
			int version, DatabaseErrorHandler errorHandler) {
		super(context, name, factory, version, errorHandler);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		for(int i=0; i< sqls.length; i++){
			db.execSQL(sqls[i]);
		}
	}

	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
