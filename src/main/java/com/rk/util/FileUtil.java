package com.rk.util;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.UUID;

/**
 * Created by Qin_Yikai on 2018-09-30.
 */
public class FileUtil {
    private static final String UPLOAD_IMAGE_PATH = "/resources/upload/images/";

    public static String SaveImage(HttpServletRequest request, MultipartFile imgFile) throws IOException {
        if (!imgFile.isEmpty()) {
            byte[] imageData = imgFile.getBytes();
            byte[] compressFileData = compressPic(imageData, 1);

            //上传路径
            String path = request.getServletContext().getRealPath(UPLOAD_IMAGE_PATH);
            String fileName = UUID.randomUUID().toString().replace("-", "");

            File file = new File(path, fileName + ".jpg");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (file.exists()) {
                file.delete();
            }

            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(compressFileData, 0, compressFileData.length);
            fileOutputStream.flush();
            fileOutputStream.close();

            return (UPLOAD_IMAGE_PATH + fileName + ".jpg").replace("/resources", "");
        }
        return null;
    }

    /**
     * @param  quality:0-1    
     * @return byte[] * @throws
     * @Title: compressPic 
     * @Description: 压缩图片, 通过压缩图片质量，保持原图大小
     */
    private static byte[] compressPic(byte[] imageByte, float quality) throws IOException {
        byte[] inByte;
        ByteArrayInputStream byteInput = new ByteArrayInputStream(imageByte);
        Image img = ImageIO.read(byteInput);
        float newWidth = img.getWidth(null);
        float newHeight = img.getHeight(null);
        Image image = img.getScaledInstance((int) newWidth, (int) newHeight, Image.SCALE_SMOOTH);
        // 缩放图像
        BufferedImage tag = new BufferedImage((int) newWidth, (int) newHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = tag.createGraphics();
        g.drawImage(image, 0, 0, null);
        // 绘制缩小后的图
        g.dispose();
        ByteArrayOutputStream out = new ByteArrayOutputStream(imageByte.length);
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);
                /* 压缩质量 */
        jep.setQuality(quality, true);
        encoder.encode(tag, jep);
        inByte = out.toByteArray();
        out.close();
        return inByte;
    }


}
