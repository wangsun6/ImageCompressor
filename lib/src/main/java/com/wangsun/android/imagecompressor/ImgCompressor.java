package com.wangsun.android.imagecompressor;


import android.graphics.Bitmap;
import android.os.Environment;
import java.io.File;
import java.util.concurrent.Callable;

import io.reactivex.Flowable;

public class ImgCompressor {
    private int maxWidth = 1280;
    private int maxHeight = 1280;
    private Bitmap.CompressFormat compressFormat = Bitmap.CompressFormat.JPEG;
    private int quality = 90;
    private String destinationDirectoryPath= Environment.getExternalStorageDirectory()+"/CompressorLib";
    private String fileName="";
    private boolean isGreyScale = false;


//    public ImgCompressor(Context context){
//        destinationDirectoryPath= context.getCacheDir().getPath() + File.separator + "images";
//    }

    public ImgCompressor setFileName(String name){
        this.fileName= name;
        return this;
    }

    public ImgCompressor setMaxWidth(int maxWidth){
        this.maxWidth = maxWidth;
        return this;
    }

    public ImgCompressor setMaxHeight(int maxHeight){
        this.maxHeight = maxHeight;
        return this;
    }

    public ImgCompressor setGreyScale(boolean value){
        this.isGreyScale = value;
        return this;
    }

    public ImgCompressor setCompressFormat(Bitmap.CompressFormat compressFormat){
        this.compressFormat = compressFormat;
        return this;
    }

    public ImgCompressor setQuality(int quality){
        this.quality = quality;
        return this;
    }

    public ImgCompressor setDestinationDirectoryPath(String destinationDirectoryPath){
        this.destinationDirectoryPath = destinationDirectoryPath;
        return this;
    }


    public File compressToFile(File inputImage){
        return compressToFile(inputImage, inputImage.getName());
    }


    public Flowable<File> compressToFileAsFlowable(File imageFile){

        if(fileName.equals("")){
            return compressToFileAsFlowable(imageFile, imageFile.getName());
        }
        else {
            return compressToFileAsFlowable(imageFile, fileName);
        }

    }


    public Flowable<File> compressToFileAsFlowable(final File imageFile,final String compressedFileName){
        return Flowable.defer(new Callable<Flowable<File>>(){
            @Override
            public Flowable<File> call() {
                return Flowable.just(compressToFile(imageFile, compressedFileName));
            }
        });
    }

    /***************************Final Method that communicate with ImageUtil***************************/
    public File compressToFile(File inputImage,String compressedFileName) {
        return ImgUtil.compressImage(maxWidth,maxHeight,quality,compressFormat,
                inputImage,destinationDirectoryPath+ File.separator + compressedFileName,isGreyScale);
    }
}
