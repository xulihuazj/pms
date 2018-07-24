package com.xulihuazj.pms.utils.image;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

/**
 * 将图片上传到 阿里云服务器
 */
public class UploadImageUtil {

    /**
     * 阿里云域名
     */
    @Value("${aliyun.endpoint}")
    private static String endpoint;

    /**
     * 阿里云bucketName
     */
    @Value("${aliyun.bucketname}")
    private static String bucketName;

    /**
     * 链接阿里云的key
     */
    @Value("${aliyun.accesskey.id}")
    private static String accesskeyId;

    /**
     * 阿里云加密秘钥
     */
    @Value("${aliyun.accesskey.secret}")
    private static String accessKeySecret;

    /**
     * 酒店图片存放的目录
     */
    @Value("${aliyun.hotel.folder}")
    private static String hotelFolder;

    private static final Logger logger = LogManager.getLogger(UploadImageUtil.class);

    private static volatile OSSClient ossClient = null;

    static {
        if (ossClient == null) {
            synchronized (logger) {
                if (ossClient == null) {
                    ossClient = new OSSClient(endpoint, accesskeyId, accessKeySecret);
                }
            }
        }
    }

    /**
     * bucketName  bucket名称
     * source     图片数据源
     * fileName   文件将名称
     * folder     存放在阿里云上面的文件夹名称
     */
    public String uploadSource(String bucketName, InputStream source, String fileName, String folder)
            throws IOException {
        InputStream inputStream = source;
        // 创建上传Object的Metadata
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(inputStream.available());
        objectMetadata.setCacheControl("no-cache");
        objectMetadata.setHeader("Pragma", "no-cache");
        // 图片类型
        objectMetadata.setContentType("image/jpeg");
        objectMetadata.setContentDisposition("inline;filename=" + fileName);
        // 上传文件
        PutObjectResult putResult = ossClient.putObject(bucketName, folder + fileName, inputStream, objectMetadata);
        Calendar currentCalendar = Calendar.getInstance();
        currentCalendar.add(Calendar.SECOND, 30);
        ossClient.generatePresignedUrl(bucketName, folder + fileName, currentCalendar.getTime());
        return putResult.getETag();
    }



}
