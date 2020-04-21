package com.example.function;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import androidx.annotation.Nullable;

public class DatabaseHandler extends SQLiteOpenHelper {
    Context context;
    private static String DATABASE_NAME="mydb.db";
    private static int DATABASE_VERSION=1;
    private static String createTableQuery="create table imageInfo (imageName TEXT"+ ",image BLOB)";
    private ByteArrayOutputStream objectByteArrayOutputStream;
    private byte[] imageInBytes;
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db){
            try {
                db.execSQL(createTableQuery);
                Toast.makeText(context,"Table created successfully",Toast.LENGTH_SHORT).show();
            }
            catch (Exception e){
                Toast.makeText(context,e.getMessage(), Toast.LENGTH_SHORT).show();

            }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
    public void storeImage(ModelClass objectModelClass){
        try {
            SQLiteDatabase objectSQLiteDatabase = this.getWritableDatabase();
            Bitmap imageToStoreBitmap = objectModelClass.getImage();

            objectByteArrayOutputStream = new ByteArrayOutputStream();
            imageToStoreBitmap.compress(Bitmap.CompressFormat.JPEG,100, objectByteArrayOutputStream);

            imageInBytes = objectByteArrayOutputStream.toByteArray();
            ContentValues objectContentValues = new ContentValues();

            objectContentValues.put("imageName",objectModelClass.getImageName());
            objectContentValues.put("image",imageInBytes);

            long checkIfQueryRuns = objectSQLiteDatabase.insert("imageInfo",null,objectContentValues);

            if (checkIfQueryRuns != -1){
                Toast.makeText(context,"Data added successfully",Toast.LENGTH_SHORT).show();
                objectSQLiteDatabase.close();
            }
            else {
                Toast.makeText(context,"Failed to add data",Toast.LENGTH_SHORT).show();
            }
        }
        catch (Exception e){
            Toast.makeText(context,e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public ArrayList<ModelClass> getAllImagesData()
    {
        try
        {
            SQLiteDatabase objectSQLiteDatabase = this.getReadableDatabase();
            ArrayList<ModelClass> objectModelClassList = new ArrayList<>();

            Cursor objectCursor = objectSQLiteDatabase.rawQuery("select * from imageInfo",null);
            if(objectCursor.getCount() != 0){
                while (objectCursor.moveToNext())
                {
                    String nameOfImage = objectCursor.getString(0);
                    byte [] imageBytes = objectCursor.getBlob(1);

                    Bitmap objectBitmap = BitmapFactory.decodeByteArray(imageBytes,0,imageBytes.length);
                    objectModelClassList.add(new ModelClass(nameOfImage,objectBitmap));

                }
                return objectModelClassList;
            }
            else {
                Toast.makeText(context,"No images on the database",Toast.LENGTH_SHORT).show();
                return null;
            }
        }
        catch (Exception e)
        {
            Toast.makeText(context,e.getMessage(), Toast.LENGTH_SHORT).show();
            return null;
        }
    }
}
