package com.shixq.dexclassloaderdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.shixq.dexclassloaderdemo.utils.AssetsMultiDexLoader;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        BundleClassLoaderManager.install(getApplicationContext());
        AssetsMultiDexLoader.install(getApplicationContext());
        findViewById(R.id.btn_loadapk).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_loadapk:
                loadApk();
                break;
        }
    }

    private void loadApk() {
        try {
//            Class<?> clazz = BundleClassLoaderManager.loadClass(getApplicationContext(), "Decoder.BASE64Decoder");
            Class<?> clazz = Class.forName("Decoder.BASE64Decoder");
            Constructor<?> constructor = clazz.getConstructor();
            Object bundleUtils = constructor.newInstance();

            Method decodeMethod = clazz.getMethod("decodeBuffer", String.class);
            decodeMethod.setAccessible(true);
            byte data[] = (byte[]) decodeMethod.invoke(bundleUtils, "2fYbfM6G7+l5xcqzx1UJGw==");
            Log.e("MainActivity",new String(data,"utf-8"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
