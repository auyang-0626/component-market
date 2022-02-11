package com.yang.component.market.web;

import java.io.IOException;
import java.security.MessageDigest;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yang.component.market.manager.dto.Result;
import com.yang.component.market.manager.service.OssService;

import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * @author yangguangyue
 * Created on 2021-12-20
 */

@CrossOrigin(origins = "*")
@Tag(name = "应用文件接口文档")
@RequestMapping("file")
@RestController
public class FileController {

    private static final String LOGO_PATH = "logo";

    @Autowired
    private OssService ossService;

    @PostMapping("upload/logo")
    @ResponseBody
    public Result<String> uploadLogo(@RequestParam("file") MultipartFile file) throws Exception {

        byte[] data = file.getBytes();

        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(data);
        byte[] digest = md5.digest();
        String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();

        String extension = FilenameUtils.getExtension(file.getOriginalFilename());

        String filePath = String.format("%s/%s.%s", LOGO_PATH, myHash, extension);
        ossService.upload(data, filePath);

        return Result.ok(filePath);
    }


    @GetMapping(value = "/image", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getImage(@RequestParam("key") String key) throws IOException {
        return ossService.download(key);
    }
}
