package interfaces;

import models.FileInfo;

import java.io.InputStream;
import java.io.OutputStream;

public interface FileService {
    void saveFileToStorage(InputStream file, String originalFileName, String contentType, Long size);
    void writeFileFromStorage(Long fileId, OutputStream outputStream);
    FileInfo getFileInfo(Long fileId);
}
