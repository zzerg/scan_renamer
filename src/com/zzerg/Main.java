package com.zzerg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public class Main {

//    private static String srcDir = "E:\\temp\\SCAN-Costituzione\\src\\";
//    private static String srcName = "DSC_%d.JPG";
//    private static int srcStart = 8348;     // inclusive
//    private static int srcEnd = 8436;       // inclusive
//    private static int srcStep = 1;
//    private static String dstDir = "E:\\temp\\SCAN-Costituzione\\n1\\";
//    private static String dstName = "%03d.jpg";

    private static String srcDir = "D:\\in_progress\\SCAN-Costituzione\\out\\";
    private static String srcName = "%04d.tif";
    private static int srcStart = 8;     // inclusive
    private static int srcEnd = 178;       // inclusive
    private static int srcStep = 2;

    private static String dstDir = "D:\\in_progress\\SCAN-Costituzione\\out3\\";
    private static String dstName = "%04d.tif";
    private static int dstStart = 10;
    private static int dstStep = 2;


    public static void main(String[] args) throws Exception {
        String inFilename;
        String outFilename;
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        int k = dstStart;

        for (int i = srcStart; i <= srcEnd; i += srcStep) {
            inFilename = srcDir + String.format(srcName, i);
            File f = new File(inFilename);
            if (!f.exists() || f.isDirectory()) {
                System.out.println("No file: " + inFilename);
                continue;
            }
            outFilename = dstDir + String.format(dstName, k);

            System.out.println(inFilename + " => " + outFilename);

            try {
                inChannel = new FileInputStream(inFilename).getChannel();
                outChannel = new FileOutputStream(outFilename).getChannel();
                outChannel.transferFrom(inChannel, 0, inChannel.size());
//            } catch (IOException ex) {
//                ex.printStackTrace();
//                System.out.println("Failed: " + ex.getMessage());
            } finally {
                if (inChannel != null) inChannel.close();
                if (outChannel != null) outChannel.close();
            }

            k += dstStep;
        }
    }

}
