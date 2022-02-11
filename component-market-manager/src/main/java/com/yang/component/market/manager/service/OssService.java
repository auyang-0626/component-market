package com.yang.component.market.manager.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.COSObject;
import com.qcloud.cos.model.COSObjectInputStream;
import com.qcloud.cos.model.GetObjectRequest;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yangguangyue
 * Created on 2021-12-20
 */
@Slf4j
@Service
public class OssService {

    //...生成上传凭证，然后准备上传

    private static final String BUCKET = "app-factory-1308902035";

    private static final String SECRET_ID = "AKIDd5HCrFkyvc190atvf9LHvJRJuEH2XliO";
    private static final String SECRET_KEY = "QZWCbiY7PSFl0yf5E6h14qLZWCBtKUNz";

    private static final COSClient cosClient;

    static {

        COSCredentials cred = new BasicCOSCredentials(SECRET_ID, SECRET_KEY);
        // 2 设置 bucket 的地域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
        Region region = new Region("ap-nanjing");
        ClientConfig clientConfig = new ClientConfig(region);
        // 这里建议设置使用 https 协议
        // 从 5.6.54 版本开始，默认使用了 https
        clientConfig.setHttpProtocol(HttpProtocol.https);
        // 3 生成 cos 客户端。
        cosClient = new COSClient(cred, clientConfig);
    }


    public void upload(byte[] uploadBytes, String key) throws Exception {

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(uploadBytes.length);

        PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET, key, new ByteArrayInputStream(uploadBytes),
                objectMetadata);
        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);

        //解析上传成功的结果
        log.info("上传结果,key:{},res:{}", key, putObjectResult.toString());
    }

    public byte[] download(String key) throws IOException {
        GetObjectRequest getObjectRequest = new GetObjectRequest(BUCKET, key);
        COSObject cosObject = cosClient.getObject(getObjectRequest);
        try (COSObjectInputStream cosObjectInput = cosObject.getObjectContent()){
           return cosObjectInput.readAllBytes();
        }
    }
}
