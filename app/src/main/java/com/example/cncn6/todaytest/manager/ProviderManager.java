package com.example.cncn6.todaytest.manager;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;

/**
 * Created by cncn6 on 2017-05-22.
 */

public class ProviderManager extends ContentProvider {
    public static final String  AUTHORITY   = "arabiannight.tistory.com.contentproviderdataa";
    // 컨텐트 프로바이더에 접근할때는 "content://AUTHORITY" 를 사용해서 접근하면 됨
    // ex. "content://arabiannight.tistory.com.contentproviderdataa"
    // (AUTHORITY는 AndroidManifest.xml에 선언한 android:authorities와 동일하게 작성하면 됨)
    public static final Uri     CONTENT_URI  =
            Uri.parse("content://" + AUTHORITY);

    private String Auth_key = "K333KAAARABIANUUUU";

    public String getAuthkey() {
        return Auth_key;
    }

    public void setAuthkey(String authkey) {
        Auth_key = authkey;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    /**
     * ContentProvider 객체가 생성 되면 호출
     */
    @Override
    public boolean onCreate() {
        return false;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Log.d("PROVIDERT", "A_insert()");
        List<String> reqValue = uri.getPathSegments();
        if(reqValue.size() > 0) {
            String serviceType = reqValue.get(0);
            Log.d("PROVIDERT", "A_serviceType = " + serviceType);
            if(serviceType.equals("AUTH_GET")){
                return Uri.parse(CONTENT_URI + "/" + serviceType + "/" + getAuthkey());
            }
        }
        return uri;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        Log.d("PROVIDERT", "A_update()");
        List<String> reqValue = uri.getPathSegments();
        if(reqValue.size() > 0) {
            String serviceType = reqValue.get(0);
            Log.d("PROVIDERT", "A_serviceType = " + serviceType);
            if(serviceType.equals("AUTH_UPDATE")){
                String new_authkey = values.getAsString("new_authkey");
                Log.i("PROVIDERT", "update() new_authkey = " + new_authkey);
                setAuthkey(new_authkey);
            }
        }
        return 0;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }
}
