package interfaces;

import models.FileInfo;

public interface FileRepository extends CrudRepository<FileInfo>{
    FileInfo findById(long id);
}
