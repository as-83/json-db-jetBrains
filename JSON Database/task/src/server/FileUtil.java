package server;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileUtil {



    public static byte[] getFile(String path, String fileName) throws IOException {
        File file = new File(path + File.separator + fileName);
        return  Files.readAllBytes(file.toPath());
    }

    public static boolean isFileExist(String fileName, String path ) {
        File file = new File(path + File.separator + fileName);
        return file.exists();

    }

    public static boolean fileHasContent(String fileName, String path) {
        File file = new File(path + File.separator + fileName);
        return file.length() > 0;
    }
}
