package cn.laoshengle.file.utils;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @description: 文件上传帮助类
 * @author: 龙逸
 * @createDate: 2020/05/24 14:04:40
 **/
public class FileUtil {

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();
    }
}
