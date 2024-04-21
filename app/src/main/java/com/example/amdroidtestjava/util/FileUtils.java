package com.example.amdroidtestjava.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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
}
