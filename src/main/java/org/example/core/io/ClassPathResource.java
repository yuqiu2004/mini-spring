package org.example.core.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ClassPathResource implements Resource{

    private final String classPath;

    public ClassPathResource(String classPath) {
        this.classPath = classPath;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(classPath);
        if (null == inputStream) throw new FileNotFoundException(classPath + " cannot not be found in class path");
        return inputStream;
    }
}
