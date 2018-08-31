package com.wangsun.android.imagecompresslib;


import android.Manifest;
import android.annotation.SuppressLint;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.io.IOException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class Main extends AppCompatActivity {
    RxPermissions rxPermissions;
    CompositeDisposable compositeDisposable;
    Button btn_submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rxPermissions = new RxPermissions(this);

        btn_submit=(Button)findViewById(R.id.id_submit);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                permission();
            }
        });

    }


    public void compressImage(){
//        File file =new File(Environment.getExternalStorageDirectory()+"/.BecSupport/Thumbnails/test1.jpg");
//
//        ImgCompressor compressor=new ImgCompressor(this);
//
////        try {
////            compressor
////                    .setMaxWidth(640)
////                    .setMaxHeight(480)
////                    .setQuality(75)
////                    .setCompressFormat(Bitmap.CompressFormat.WEBP)
////                    .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath())
////                    .compressToFile(file);
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
//
//        compositeDisposable = new CompositeDisposable();
//        Disposable disposable = compressor
//                .setMaxHeight(1000)
//                .setMaxWidth(1000)
//                .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath())
//                .compressToFileAsFlowable(file)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<File>() {
//                    @Override
//                    public void accept(File file) {
//                        Toast.makeText(Main.this,file.getName(),Toast.LENGTH_SHORT).show();
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) {
//                        throwable.printStackTrace();
//                        Toast.makeText(Main.this,"error",Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//        compositeDisposable.add(disposable);
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.dispose();

        super.onDestroy();
    }

    @SuppressLint("CheckResult")
    public void permission(){
        rxPermissions
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(granted -> {
                    if (granted) {
                        Toast.makeText(Main.this,"Granted.",Toast.LENGTH_SHORT).show();
                        compressImage();
                    } else {
                        Toast.makeText(Main.this,"You need to allow permission.",Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
