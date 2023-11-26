package interfaces;

import models.FileInfo;

public interface FilesRepository {
    void save(FileInfo entity);
    FileInfo findById(long id);
}
