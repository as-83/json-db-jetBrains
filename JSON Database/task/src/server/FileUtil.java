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

    public static String getAsString(String path, String dbFile) throws IOException {
        File file = new File(path + File.separator + dbFile);
        return Files.readString(file.toPath());
    }

    public static void saveToFile(String path, String dbFile, String content) throws IOException {
        File file = new File(path + File.separator + dbFile);
        Files.writeString(file.toPath(), content);
    }
}
