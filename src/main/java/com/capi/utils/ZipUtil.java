package com.capi.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/** 
* 压缩文件工具类
*/
public class ZipUtil {
    
    public static void doCompress(String srcFile, String zipFile) throws Exception {
        doCompress(new File(srcFile), new File(zipFile));
    }
    
    /**
     * 文件压缩
     * @param srcFile  目录或者单个文件
     * @param destFile 压缩后的ZIP文件
     */
    public static void doCompress(File srcFile, File destFile) throws Exception {
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(destFile));
        if(srcFile.isDirectory()){
            File[] files = srcFile.listFiles();
            for(File file : files){
                doCompress(file, out);
            }
        }else {
            doCompress(srcFile, out);
        }
    }
    
    public static void doCompress(String pathname, ZipOutputStream out) throws IOException{
        doCompress(new File(pathname), out);
    }
    
    public static void doCompress(File file, ZipOutputStream out) throws IOException{
        if( file.exists() ){
            byte[] buffer = new byte[1024];
            FileInputStream fis = new FileInputStream(file);
            out.putNextEntry(new ZipEntry(file.getName()));
            int len = 0 ;
            // 读取文件的内容,打包到zip文件    
            while ((len = fis.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            out.flush();
            out.closeEntry();
            fis.close();
        }
    }
    
    /**
     * 用于删除缓存文件夹
     * @param dir
     * @return
     */
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }
}
