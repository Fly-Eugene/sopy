package com.ssafy.sopy.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.ssafy.sopy.domain.entity.S3Component;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.Buffer;

@RequiredArgsConstructor
@Component
public class AwsS3UploadService implements UploadService{

    private final AmazonS3 amazonS3;
    private final S3Component component;

    public Object getText(String directory, String fileName) throws IOException {
        S3Object o = getDir(directory, fileName);
        BufferedReader br = new BufferedReader(new InputStreamReader(o.getObjectContent()));
        StringBuilder res = new StringBuilder();
        String temp;
        while ((temp = br.readLine()) != null){
            res.append(temp).append("\n");
        }
        return res.toString();
    }
    @Override
    public S3Object getDir(String directory, String fileName){
        return amazonS3.getObject(new GetObjectRequest(component.getBucket()+"/"+directory, fileName));
    }

    @Override
    public void uploadFile(String directory, String fileName, InputStream inputStream, ObjectMetadata objectMetadata) {
        amazonS3.putObject(new PutObjectRequest(component.getBucket()+"/"+directory, fileName, inputStream, objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead));
    }

    @Override
    public void uploadFile(String directory, String fileName, File file) {
        amazonS3.putObject(new PutObjectRequest(component.getBucket()+"/"+directory, fileName, file).withCannedAcl(CannedAccessControlList.PublicRead));
    }

    @Override
    public String getFileUrl(String fileName) {
        return amazonS3.getUrl(component.getBucket(), fileName).toString();
    }

    @Override
    public String getFileUrl(String directory, String fileName) {
        return amazonS3.getUrl(component.getBucket()+"/"+directory, fileName).toString();
    }

}
