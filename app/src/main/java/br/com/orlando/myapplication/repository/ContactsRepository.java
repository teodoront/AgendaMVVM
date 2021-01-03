package br.com.orlando.myapplication.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.orlando.myapplication.constants.DataBaseConstants;
import br.com.orlando.myapplication.model.ContactModel;

public class ContactsRepository {
    private DataBaseHelper mHelper;

    //SINGLETON

    //2º variável que vai guardar a instancia da classe
    private static ContactsRepository INSTANCE;


    //1º construtor fechado
    private ContactsRepository(Context context) {
        this.mHelper = new DataBaseHelper(context);
    }

    //3º um método que retorna a instância da classe
    public static ContactsRepository getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new ContactsRepository(context);
        }
        return INSTANCE;
    }


    //CREATE, READ, UPDATE and DELETE;

    //lista todos
    public List<ContactModel> getList() {
        List<ContactModel> list = new ArrayList<>();
        try {

            SQLiteDatabase db = this.mHelper.getReadableDatabase();

            String[] columns = {DataBaseConstants.CONTACTS.COLUMNS.ID,
                    DataBaseConstants.CONTACTS.COLUMNS.ID,
                    DataBaseConstants.CONTACTS.COLUMNS.NAME,
                    DataBaseConstants.CONTACTS.COLUMNS.EMAIL,
                    DataBaseConstants.CONTACTS.COLUMNS.TELEPHONE};


            Cursor cursor = db.query(DataBaseConstants.CONTACTS.TABLE_NAME, columns, null,
                    null, null, null, null);

            if (cursor != null && cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    int id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.CONTACTS.COLUMNS.ID));
                    String name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.CONTACTS.COLUMNS.NAME));
                    String email = cursor.getString(cursor.getColumnIndex(DataBaseConstants.CONTACTS.COLUMNS.EMAIL));
                    String telephone = cursor.getString(cursor.getColumnIndex(DataBaseConstants.CONTACTS.COLUMNS.TELEPHONE));

                    list.add(new ContactModel(id, name, email, telephone));
                }


            }
            if (cursor != null) {
                cursor.close();
            }

            return list;
        } catch (Exception e) {
            return list;
        }


    }

    //lista só um
    public ContactModel load(int id) {
        try {

            ContactModel contact = null;
            SQLiteDatabase db = this.mHelper.getReadableDatabase();

            String[] columns = {DataBaseConstants.CONTACTS.COLUMNS.ID,
                    DataBaseConstants.CONTACTS.COLUMNS.ID,
                    DataBaseConstants.CONTACTS.COLUMNS.NAME,
                    DataBaseConstants.CONTACTS.COLUMNS.EMAIL,
                    DataBaseConstants.CONTACTS.COLUMNS.TELEPHONE};

            String selection = DataBaseConstants.CONTACTS.COLUMNS.ID + " = ?";
            String[] selectionArgs = {String.valueOf(id)};

            Cursor cursor = db.query(DataBaseConstants.CONTACTS.TABLE_NAME, columns, selection,
                    selectionArgs, null, null, null);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                String name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.CONTACTS.COLUMNS.NAME));
                String email = cursor.getString(cursor.getColumnIndex(DataBaseConstants.CONTACTS.COLUMNS.EMAIL));
                String telephone = cursor.getString(cursor.getColumnIndex(DataBaseConstants.CONTACTS.COLUMNS.TELEPHONE));

                contact = new ContactModel(id, name, email, telephone);
            }
            if (cursor != null) {
                cursor.close();
            }
            db.close();
            return contact;


        } catch (Exception e) {
            return null;
        }

    }


    public boolean insert(ContactModel contact) {

        try {
            SQLiteDatabase db = this.mHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DataBaseConstants.CONTACTS.COLUMNS.NAME, contact.getName());
            values.put(DataBaseConstants.CONTACTS.COLUMNS.EMAIL, contact.getEmail());
            values.put(DataBaseConstants.CONTACTS.COLUMNS.TELEPHONE, contact.getTelephone());

            db.insert(DataBaseConstants.CONTACTS.TABLE_NAME, null, values);
            db.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public boolean update(ContactModel contact) {

        try {
            SQLiteDatabase db = this.mHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(DataBaseConstants.CONTACTS.COLUMNS.NAME, contact.getName());
            values.put(DataBaseConstants.CONTACTS.COLUMNS.EMAIL, contact.getEmail());
            values.put(DataBaseConstants.CONTACTS.COLUMNS.TELEPHONE, contact.getTelephone());

            String where = DataBaseConstants.CONTACTS.COLUMNS.ID + " = ?";
            String[] arguments = {String.valueOf(contact.getId())};

            db.update(DataBaseConstants.CONTACTS.TABLE_NAME, values, where, arguments);
            db.close();
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public boolean delete(int id) {
        try {
            SQLiteDatabase db = this.mHelper.getWritableDatabase();

            String where = DataBaseConstants.CONTACTS.COLUMNS.ID + " = ?";
            String[] arguments = {String.valueOf(id)};
            db.delete(DataBaseConstants.CONTACTS.TABLE_NAME, where, arguments);
            db.close();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

}
