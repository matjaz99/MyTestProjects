package si.matjazcerkvenik.test.minio;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import io.minio.errors.MinioException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class FileUploader {

    public static final String BUCKET_NAME = "test-bucket";

    public static void main(String[] args)
            throws IOException, NoSuchAlgorithmException, InvalidKeyException {

        try {
            // Create a minioClient with the MinIO server playground, its access key and secret key.
            MinioClient minioClient =
                    MinioClient.builder()
                            .endpoint("http://promvm:9000")
                            .credentials("myaccesskey", "mysecretkey")
                            .build();

            // Make 'asiatrip' bucket if not exist.
            boolean found =
                    minioClient.bucketExists(BucketExistsArgs.builder().bucket(BUCKET_NAME).build());
            if (!found) {
                // Make a new bucket called 'asiatrip'.
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(BUCKET_NAME).build());
            } else {
                System.out.println("Bucket already exists: " + BUCKET_NAME);
            }

            // Upload '/home/user/Photos/asiaphotos.zip' as object name 'asiaphotos-2015.zip' to bucket
            // 'asiatrip'.
            minioClient.uploadObject(
                    UploadObjectArgs.builder()
                            .bucket(BUCKET_NAME)
                            .object("testfile.object")
                            .filename("/Users/matjaz/Developer/IdeaProjects/MyTestProjects/minio/testfile.txt")
                            .build());
            System.out.println(
                    "'testfile.txt' is successfully uploaded as "
                            + "object 'testfile.object' to bucket " + BUCKET_NAME);
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
            System.out.println("HTTP trace: " + e.httpTrace());
        }

    }

}
