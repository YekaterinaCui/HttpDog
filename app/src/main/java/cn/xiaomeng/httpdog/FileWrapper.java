package cn.xiaomeng.httpdog;

import java.io.File;

import okhttp3.MediaType;

public class FileWrapper {
    public File file;
    public String fileName;
    public MediaType mediaType;
    private long fileSize;

    public FileWrapper(File file, MediaType mediaType) {
        this.file = file;
        this.fileName = file.getName();
        this.mediaType = mediaType;
        this.fileSize = file.length();
    }

    public String getFileName() {
        if (fileName != null) {
            return fileName;
        } else {
            return "file name dose not exist";
        }
    }

    public File getFile() {
        return file;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public long getFileSize() {
        return fileSize;
    }
}
