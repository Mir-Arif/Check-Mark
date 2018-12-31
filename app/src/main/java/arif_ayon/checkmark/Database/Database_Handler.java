package arif_ayon.checkmark.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import arif_ayon.checkmark.Model.Contact;

import static android.content.Context.MODE_PRIVATE;

public class Database_Handler extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="MyDatabase";


    public Database_Handler(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        String sql= "CREATE TABLE UserTable (USERNAME TEXT," + " PASSWORD TEXT," + " SEMESTER TEXT)";
        db.execSQL(sql);

        sql= "CREATE TABLE EventTable (EVENTNAME TEXT," + " ROOM TEXT," + " STIME TEXT," + " ETIME TEXT" + ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        String sql = "DROP TABLE IF EXISTS UserTable";
        db.execSQL(sql);
        onCreate(db);
    }

    public void adduser(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "INSERT INTO UserTable(USERNAME, PASSWORD, SEMESTER)" +
                "VALUES('"+contact.getUserName()+"','"+contact.getPassword()+"','"+contact.getSemester()+"')";
        db.execSQL(query);
        db.close();
    }

    public void addEvent(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "INSERT INTO EventTable(EVENTNAME, ROOM, STIME, ETIME)" +
                " VALUES('"+contact.getEvent()+"','"+contact.getRoom()+"','"+contact.getStime()+"','"+ contact.getEtime()+"')";
        db.execSQL(query);
        db.close();
    }

    public List<Contact> getAllEvent()
    {
        List<Contact> mycontactList = new ArrayList<Contact>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectquery = "SELECT * FROM EventTable";// where phoneno LIKE '017%'";

        Cursor cursor = db.rawQuery(selectquery, null);

        if(cursor.moveToFirst())
        {
            do
            {
                Contact contact= new Contact(cursor.getString(0),cursor.getString(1),
                        cursor.getString(2), cursor.getString(3));
                mycontactList.add(contact);
            }while(cursor.moveToNext());
        }
        return mycontactList;
    }


    public int CheckUser(Contact contact) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT PASSWORD FROM UserTable WHERE USERNAME='" + contact.getUserName() + "' AND PASSWORD ='" +
                contact.getPassword() + "'";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            cursor.close();
            db.close();
            return 1;
        }
        else {
            cursor.close();
            db.close();
            return 0;
        }
    }

    public void deleteEvent(String event) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE from EventTable WHERE EVENTNAME=" + event;
        db.execSQL(query);
        db.close();
    }
}