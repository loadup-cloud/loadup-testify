package com.github.loadup.testify.example.test.s3;

import com.github.loadup.testify.containers.annotation.EnableContainer;
import com.github.loadup.testify.core.annotation.TestifyTest;
import com.github.loadup.testify.example.service.S3StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

@SpringBootTest
@EnableContainer(value = LocalStackContainer.class, image = "localstack/localstack:3.0")
public class S3StorageServiceTest extends AbstractTestNGSpringContextTests {

  @Autowired private S3StorageService s3StorageService;

  @BeforeClass
  public void setup() {
    s3StorageService.createBucket();
  }

  @TestifyTest
  public void uploadFile(String key, String content) {
    s3StorageService.uploadFile(key, content);
    String downloaded = s3StorageService.downloadFile(key);
    Assert.assertEquals(downloaded, content);
  }
}
