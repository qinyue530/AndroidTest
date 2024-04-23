package com.example.amdroidtestjava.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {

    /**
     * 存储数据文件信息
     * @param path
     * @return
     */
    public static void savText(String path , String txt){
        BufferedWriter bufferedWriter = null;
        try{
            bufferedWriter = new BufferedWriter(new FileWriter(path));
            bufferedWriter.write(txt);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(null != bufferedWriter){
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * 读取数据文件信息
     * @param path
     * @return
     */
    public static String  openText(String path){
        BufferedReader bufferedReader = null;
        StringBuilder stringBuilder = new StringBuilder();
        try{
            bufferedReader = new BufferedReader(new FileReader(path));
            String line = null;
            while ((line = bufferedReader.readLine())!=null){
                stringBuilder.append(line);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(null != bufferedReader){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 存储图片
     * @param path
     * @param bitmap
     */
    public static void saveImage(String path, Bitmap bitmap){
        FileOutputStream fileOutputStream = null;
        try{
            fileOutputStream = new FileOutputStream(path);
            //图片格式, 质量,输出流
            bitmap.compress(Bitmap.CompressFormat.PNG,100,fileOutputStream);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(null != fileOutputStream){
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取图片
     * @param path
     */
    public static Bitmap openImage(String path){
        Bitmap bitmap = null;
        FileInputStream fileInputStream = null;
        try{
            //输入流
            fileInputStream = new FileInputStream(path);
            //图片格式, 质量,输出流
            bitmap = BitmapFactory.decodeStream(fileInputStream);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(null != fileInputStream){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bitmap;
    }
}
