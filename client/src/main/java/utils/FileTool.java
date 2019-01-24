package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class FileTool {
    public static String readAllLines(File file) {
        StringBuilder sb = new StringBuilder();
        String str;
        try {
            BufferedReader br =  new BufferedReader
                    (new InputStreamReader(new FileInputStream(file.getAbsolutePath())));
            while (null != (str = br.readLine())) {
                sb.append(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
