package com.ssafy.sopy.service;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public interface UploadService {
    void uploadFile(String directory, String fileName, InputStream inputStream, ObjectMetadata objectMetadata);
    void uploadFile(String directory, String fileName, File file);
    String getFileUrl(String fileName);
    String getFileUrl(String directory, String fileName);
    S3Object getDir(String directory, String fileName);
    Object getText(String bucketName, String keyName) throws IOException;
}
