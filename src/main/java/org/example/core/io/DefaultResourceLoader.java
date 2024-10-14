package org.example.core.io;

import java.net.MalformedURLException;
import java.net.URL;

public class DefaultResourceLoader implements ResourceLoader{

    public static final String CLASS_PATH_PREFIX = "classpath:";

    @Override
    public Resource getResource(String path) {
        if (path.toLowerCase().startsWith(CLASS_PATH_PREFIX)) { //忽略大小写
            return new ClassPathResource(path.substring(CLASS_PATH_PREFIX.length()));
        }
        try {
            URL url = new URL(path);
            return new UrlResource(url);
        }catch (MalformedURLException ex) {
            return new FileSystemResource(path);
        }
    }
}
