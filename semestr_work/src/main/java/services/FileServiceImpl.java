package services;

import interfaces.FileService;
import interfaces.FileRepository;
import models.FileInfo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

public class FileServiceImpl implements FileService {
    FileRepository fileRepository;

    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public long saveFileToStorage(InputStream file, String originalFileName, String contentType, Long size) {
        FileInfo fileInfo = FileInfo.builder()
                .originalFileName(originalFileName)
                .storageFileName(UUID.randomUUID().toString())
                .size(size)
                .type(contentType)
                .build();

        try {
            Files.copy(file, Paths.get("F:\\dev\\projects\\java\\2k1s_oris_semestr_work\\semestr_work\\src\\main\\webapp\\files" + fileInfo.getStorageFileName() + "." + fileInfo.getType().split("/")[1]));
            fileRepository.save(fileInfo);

            return fileRepository.findByStorageFileName(fileInfo.getStorageFileName()).getId();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void writeFileFromStorage(Long fileId, OutputStream outputStream) {
        FileInfo fileInfo = fileRepository.findById(fileId);
        File file = new File("F:\\dev\\projects\\java\\2k1s_oris_semestr_work\\semestr_work\\src\\main\\webapp\\files" + fileInfo.getStorageFileName() + "." + fileInfo.getType().split("/")[1]);

        try {
            Files.copy(file.toPath(), outputStream);
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public FileInfo getFileInfo(Long fileId) {
        return fileRepository.findById(fileId);
    }
}
