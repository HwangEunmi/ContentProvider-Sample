package com.example.cncn6.todaytest;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cncn6.todaytest.manager.StudentProvider;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

// https://www.tutorialspoint.com/android/android_content_providers.htm
public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickAddName(View view) {
        // Add a new student record
        ContentValues values = new ContentValues();
        values.put(StudentProvider.NAME,
                ((EditText) findViewById(R.id.editText2)).getText().toString());

        values.put(StudentProvider.GRADE,
                ((EditText) findViewById(R.id.editText3)).getText().toString());

        // COLUMN추가 테스트용
        values.put(StudentProvider.TEST,
                ((EditText) findViewById(R.id.editText3)).getText().toString());

        Uri uri = getContentResolver().insert(
                StudentProvider.CONTENT_URI, values);

        Toast.makeText(getBaseContext(),
                uri.toString(), Toast.LENGTH_LONG).show();
    }

    public void onClickRetrieveStudents(View view) {
        // Retrieve student records
        String URL = "content://com.example.MyApplication.StudentsProvider";

        Uri students = Uri.parse(URL);
        Cursor c = managedQuery(students, null, null, null, "name");

        if (c.moveToFirst()) {
            do {
                Log.d("TEST", "id: " + c.getString(c.getColumnIndex(StudentProvider._ID)) + ", " +
                        "name: " + c.getString(c.getColumnIndex(StudentProvider.NAME)) + ", " +
                        "test: " + c.getString(c.getColumnIndex(StudentProvider.TEST)) + ", " +
                        "grade: " + c.getString(c.getColumnIndex(StudentProvider.GRADE)));

                Toast.makeText(this,
                        c.getString(c.getColumnIndex(StudentProvider._ID)) +
                                ", " + c.getString(c.getColumnIndex(StudentProvider.NAME)) +
                                ", " + c.getString(c.getColumnIndex(StudentProvider.TEST)) +
                                ", " + c.getString(c.getColumnIndex(StudentProvider.GRADE)),
                        Toast.LENGTH_SHORT).show();
            } while (c.moveToNext());
        }
    }
}
