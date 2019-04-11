package netIO;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 比较nio和普通io的性能
 * IO时间：5777
 * NIO时间：72
 */
public class SimpleFileTransferTest {
    public static void main(String[] args) throws IOException {
        SimpleFileTransferTest simpleFileTransferTest = new SimpleFileTransferTest();
        File source = new File("D:\\1080资源\\真相.mp4");
        File os = new File("truth.mp4");
        File nio = new File("truthnio.mp4");

        long time = simpleFileTransferTest.transferFile(source, os);
        long nioTime = simpleFileTransferTest.transferFileWithNIO(source, nio);

        System.out.println("IO时间：" + time);
        System.out.println("NIO时间：" + nioTime);
    }

    private long transferFile(File source, File os) throws IOException {
        long startTime = System.currentTimeMillis();
        if (!os.exists())
            os.createNewFile();

        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(source));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(os));

        byte[] bytes = new byte[1024*1024];
        int len;
        while ((len = bis.read(bytes))!=-1)
            bos.write(bytes,0,len);
        long endTime = System.currentTimeMillis();
        return endTime-startTime;
    }

    private long transferFileWithNIO(File source, File des) throws IOException {
        long startTime = System.currentTimeMillis();

        if (!des.exists())
            des.createNewFile();

        RandomAccessFile read = new RandomAccessFile(source,"rw");
        RandomAccessFile write = new RandomAccessFile(des,"rw");

        FileChannel readChannel = read.getChannel();
        FileChannel writeChannel = write.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024*1024);//1M缓冲区

        while (readChannel.read(byteBuffer) > 0) {
            byteBuffer.flip();
            writeChannel.write(byteBuffer);
            byteBuffer.clear();
        }

        writeChannel.close();
        readChannel.close();
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
}
