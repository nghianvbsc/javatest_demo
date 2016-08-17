package vinaedu.javatest.model.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import vinaedu.javatest.common.objects.Level;
import vinaedu.javatest.model.objects.Question;

/**
 * Created by bscenter on 12/08/2016
 */
public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    private DatabaseAccess(Context context) {
        this.openHelper = new DBConfig(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    public List<Question> getGetQuestion() {
        List<Question> questions = new ArrayList<>();
        int limit = 10;
        Cursor cursor = database.rawQuery("SELECT * FROM question ORDER BY Random() LIMIT " + limit, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String question = cursor.getString(cursor.getColumnIndex("question"));
            String answerA = cursor.getString(cursor.getColumnIndex("answerA"));
            String answerB = cursor.getString(cursor.getColumnIndex("answerB"));
            String answerC = cursor.getString(cursor.getColumnIndex("answerC"));
            String answerD = cursor.getString(cursor.getColumnIndex("answerD"));
            String trueAnswer = cursor.getString(cursor.getColumnIndex("trueAnswer"));
            Question aQuestion = new Question(question, answerA, answerB, answerC, answerD, trueAnswer);
            questions.add(aQuestion);
            cursor.moveToNext();
        }
        cursor.close();
        return questions;
    }


}
