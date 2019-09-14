package com.joker.spring.core.io;

import com.joker.spring.utils.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * description:
 *
 * @author zgw
 * date 2019/9/14
 */
public class FileSystemResoure implements Resource {

    private final String path;

    private final File file;

    public FileSystemResoure(String path) {
        Assert.notNull(path, "path cannot be null");
        this.file = new File(path);
        this.path = path;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(this.file);
    }

    @Override
    public String getDescription() {
        return "file ["+ this.file.getAbsolutePath() + "]";
    }
}
