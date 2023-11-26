package models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileInfo {
    private long id;
    private String originalFileName;
    private String storageFileName;
    private long size;
    private String type;
}
