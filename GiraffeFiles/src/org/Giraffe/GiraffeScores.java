package org.Giraffe;
import android.app.ListActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;
import static android.provider.BaseColumns._ID;
import static org.Giraffe.Constants.TABLE_NAME;
import static org.Giraffe.Constants.USER_NAME;
import static org.Giraffe.Constants.SCORE;

public class GiraffeScores extends ListActivity {
	   private static String[] FROM = { _ID,USER_NAME, SCORE};
	   private static String ORDER_BY = SCORE;
	   private static int[] TO = {  R.id.rowid,R.id.username, R.id.score,};
	   private GiraffeScoresDB scores;
	   @Override
	   public void onCreate(Bundle savedInstanceState) {
		   super.onCreate(savedInstanceState);
		      setContentView(R.layout.scorelist);
		      scores = new GiraffeScoresDB(this);
		      try {
		         addScore("Mike", "9001");
		         addScore("Anna", "8999");
		         Cursor cursor = getScores();
		         showScores(cursor);
		      } finally {
		         scores.close();
		      }
		   }

		   private void addScore(String name, String score) {
		      // Insert a new record into the Events data source.
		      // You would do something similar for delete and update.
		      SQLiteDatabase gdb = scores.getWritableDatabase();
		      ContentValues values = new ContentValues();
		      values.put(USER_NAME, name);
		      values.put(SCORE, score);
		      gdb.insertOrThrow(TABLE_NAME, null, values);
		   }

		   private Cursor getScores() {
		      // Perform a managed query. The Activity will handle closing
		      // and re-querying the cursor when needed.
		      SQLiteDatabase gdb = scores.getReadableDatabase();
		      Cursor cursor = gdb.query(TABLE_NAME, FROM, null, null, null,
		            null, ORDER_BY);
		      startManagingCursor(cursor);
		      return cursor;
		   }

		   private void showScores(Cursor cursor) {
		      // Set up data binding
		      SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
		            R.layout.scorex, cursor, FROM, TO);
		      setListAdapter(adapter);
		   }
		}
