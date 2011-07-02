package org.Giraffe;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static org.Giraffe.Constants.TABLE_NAME;
import static org.Giraffe.Constants.USER_NAME;
import static org.Giraffe.Constants.SCORE;
import static android.provider.BaseColumns._ID;

public class GiraffeScoresDB extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "giraffe.db";
	private static final int DATABASE_VERSION = 1;
	private static final String TAG = "GIRAFFE_DB";
	
	public GiraffeScoresDB(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase gdb) {
		gdb.execSQL("CREATE TABLE " + TABLE_NAME + " ("+_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ USER_NAME + " TEXT NOT NULL," + SCORE + " TEXT NOT NULL);");
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase gdb, int oldVersion, int newVersion) {
		gdb.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		
	}

}