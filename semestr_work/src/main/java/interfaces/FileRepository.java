package interfaces;

import models.FileInfo;

public interface FileRepository extends CrudRepository<FileInfo>{
    FileInfo findById(long id);
    FileInfo findByStorageFileName(String storageFileName);
}
