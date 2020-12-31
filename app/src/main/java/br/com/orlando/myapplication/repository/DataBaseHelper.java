package br.com.orlando.myapplication.repository;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import br.com.orlando.myapplication.constants.DataBaseConstants;

public class DataBaseHelper extends SQLiteOpenHelper {
//Conecta e cria o banco de dados
private static final String DB_NAME = "contacts.db";
private static final int DB_VERSION = 1;

    private static final String CREATE_TABLE_CONTACTS = "create table " + DataBaseConstants.CONTACTS.TABLE_NAME + " ("
            + DataBaseConstants.CONTACTS.COLUMNS.ID + " integer primary key, "
            + DataBaseConstants.CONTACTS.COLUMNS.NAME + " text, "
            + DataBaseConstants.CONTACTS.COLUMNS.EMAIL + " text, "
            + DataBaseConstants.CONTACTS.COLUMNS.TELEPHONE + " text);";



    public DataBaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CONTACTS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
