package util;

import java.io.*;

/**
 * @Auther: srd-czk
 * @Date: 2019/3/23
 * @Description: util
 * @version: 1.0
 */
public class ReadFile {
    public static void makeFile() {
        File file = new File("f:\\czk\\sfm\\lib\\ped01.txt");

        try (
                // 创建文件字符流
                FileWriter fw = new FileWriter(file);
                // 缓存流必须建立在一个存在的流的基础上
                PrintWriter pw = new PrintWriter(fw);
        ) {
            pw.println("garen kill teemo");
            pw.println("teemo revive after 1 minutes");
            pw.println("teemo try to garen, but killed again");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        makeFile();
    }
}
