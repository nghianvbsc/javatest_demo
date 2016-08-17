package vinaedu.javatest.model.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by bscenter on 12/08/2016
 */
public class DBConfig extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "tracnghiemjava.db";
    private static final int DATABASE_VERSION = 1;

    public DBConfig(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
