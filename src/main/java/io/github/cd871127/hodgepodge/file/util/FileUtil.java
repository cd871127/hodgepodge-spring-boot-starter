package io.github.cd871127.hodgepodge.file.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class FileUtil {

    public static void main(String[] args) {

    }

    public void write(String path, String fileName, String data) {
        if (!path.endsWith("/"))
            path += "/";
        File file = new File(path + fileName);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            FileChannel fileChannel = fos.getChannel();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void read() {

    }

}
