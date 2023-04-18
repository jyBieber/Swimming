package com.it.swim.util;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.channels.FileChannel;
import java.util.List;

/*
 * 文件处理工具类
 */
public class FileUtils {
    public static String FILENAME_PATTERN = "[a-zA-Z0-9_\\-\\|\\.\\u4e00-\\u9fa5]+";
    private static List<String> imgTypes;

    /*
     * 输出指定文件的byte数组
     * @param filePath 文件路径
     * @param os       输出流
     */
    public static void writeBytes(String filePath, OutputStream os) throws IOException {
        FileInputStream fis = null;
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                throw new FileNotFoundException(filePath);
            }
            fis = new FileInputStream(file);
            byte[] b = new byte[1024];
            int length;
            while ((length = fis.read(b)) > 0) {
                os.write(b, 0, length);
            }
        } catch (IOException e) {
            throw e;
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    /*
     * 删除文件
     * @param filePathName 文件
     */
    public static boolean deleteFile(String filePathName) {
        return deleteFile(new File(filePathName));
    }

    /*
     * 删除文件
     * @param file 文件
     */
    public static boolean deleteFile(File file) {
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            try {
                file.delete();
                return !file.exists();
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }

    /*
     * 文件名称验证
     * @param filename 文件名称
     * @return true 正常 false 非法
     */
    public static boolean isValidFilename(String filename) {
        return filename.matches(FILENAME_PATTERN);
    }

    public static boolean downLoad(String filePath, HttpServletResponse response, boolean isOnLine) throws Exception {
        filePath=PathUtil.getImgBasePath()+"/imageUpload/item" +filePath;
        System.out.println(filePath);
        File f = new File(filePath);
        if (!f.exists() || f.length() == 0) {
            response.sendError(404, "File not found!");
            return false;
        }
        BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
        byte[] buf = new byte[1024];
        int len = 0;
        response.reset(); //
        if (isOnLine) { // 图片
            response.setContentType("image/gif");
            response.setHeader("Content-Disposition", "inline; filename=" + f.getName());
        } else { // 视频
            response.setContentType("audio/mp4");
            response.setHeader("Content-Disposition", "inline; filename=" + f.getName());
        }
        OutputStream out = response.getOutputStream();
        while ((len = br.read(buf)) > 0)
            out.write(buf, 0, len);
        br.close();
        out.close();
        return true;
    }

    // 强制移动文件到指定位置
    public static boolean moveFile(File source, String destPath, String destName) {
        return moveFile(source, new File(destPath, destName));
    }

    // 强制移动文件到指定位置
    public static boolean moveFile(File source, File destFile) {
        //目标文件不存在,则不移动
        if (!source.exists()) {
            return false;
        }
        // 路径不存在则创建对应路径
        File path = new File(destFile.getPath());
        if (!path.exists()) {
            path.mkdirs();
        }
        try {
            //不是同一个文件且目标文件存在，则替换目标文件
            if (source.length() != destFile.length() && destFile.exists()) {
                destFile.delete();
            }
            // 移动附件
            source.renameTo(destFile);
            // 移动不成功则强制移动(复制后删除源文件)
            if (!destFile.exists() && source.exists()) {
                copyFileUsingFileChannels(source, destFile);
                deleteFile(source);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return destFile.exists();
    }

    // 移动文件到指定位置，若目标位置已存在，则不移动
    public static boolean moveFileHoldDest(File source, String destPath, String destName) {
        //目标文件不存在,则不移动
        if (!source.exists()) {
            return false;
        }
        File destFile = new File(destPath, destName);
        if (destFile.exists()) {
            return false;
        }
        // 路径不存在则创建对应路径
        File path = new File(destPath);
        if (!path.exists()) {
            path.mkdirs();
        }
        try {
            // 移动附件
            source.renameTo(destFile);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    // 复制文件
    private static void copyFileUsingFileChannels(File source, File dest) throws IOException {
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            inputChannel = new FileInputStream(source).getChannel();
            outputChannel = new FileOutputStream(dest).getChannel();
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
        } finally {
            inputChannel.close();
            outputChannel.close();
            inputChannel = null;
            outputChannel = null;
        }
    }
}
