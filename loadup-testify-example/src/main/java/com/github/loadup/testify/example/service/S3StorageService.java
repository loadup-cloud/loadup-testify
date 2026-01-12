package com.github.loadup.testify.example.service;

import io.awspring.cloud.s3.S3Template;
import java.io.ByteArrayInputStream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class S3StorageService {

  private final S3Template s3Template;
  private final String BUCKET_NAME = "test-bucket";

  public void uploadFile(String key, String content) {
    s3Template.upload(BUCKET_NAME, key, new ByteArrayInputStream(content.getBytes()), null);
  }

  public String downloadFile(String key) {
    // Simple download as string for demo
    try {
      return s3Template
          .download(BUCKET_NAME, key)
          .getContentAsString(java.nio.charset.StandardCharsets.UTF_8);
    } catch (Exception e) {
      return null;
    }
  }

  public void createBucket() {
    s3Template.createBucket(BUCKET_NAME);
  }
}
