package es.codeurjc.mca.practica_1_cloud_ordinaria_2021.image;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service("storageService")
@Profile("production")
public class S3ImageService implements ImageService {

  @Value("${cloud.aws.s3.bucket}")
  private String bucket;

  @Value("${amazon.s3.endpoint}")
  private String url;

  @Autowired
  private AmazonS3 s3;

  @Override
  public String createImage(MultipartFile file) {
    String fileName = "image_" + UUID.randomUUID() + "_" + file.getOriginalFilename();
    try {

      ObjectMetadata objectMetadata = new ObjectMetadata();
      objectMetadata.setContentType(file.getContentType());

      TransferManager transferManager = TransferManagerBuilder.defaultTransferManager();
      transferManager.upload(bucket, fileName, file.getInputStream(), objectMetadata);
    } catch (Exception ex) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can't save image on S3", ex);
    }
    return url + fileName;
  }

  @Override
  public void deleteImage(String image) {
    String[] tokens = image.split("/");
    String fileName = tokens[tokens.length - 1];
    s3.deleteObject(bucket, fileName);
  }

}

