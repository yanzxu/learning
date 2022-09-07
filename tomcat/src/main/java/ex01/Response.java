package ex01;

import java.io.*;
import java.util.Arrays;

public class Response {
    private static final int BUFFER_SIZE = 1024;

    private final OutputStream outputStream;

    private Request request;

    public Response(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void sendStaticResource() {
        byte[] bytes = new byte[BUFFER_SIZE];
        FileInputStream fileInputStream = null;

        try {
            File file = new File(HttpServer.WEB_ROOT, request.getUri());
            if (file.exists()){
                outputStream.write("HTTP/1.1 200 OK\r\n".getBytes());
                outputStream.write(("ContentType: text/html\r\n").getBytes());
                outputStream.write("\r\n".getBytes());

                fileInputStream = new FileInputStream(file);
                int byteCount = fileInputStream.read(bytes, 0, BUFFER_SIZE);

                while (byteCount != -1){
                    outputStream.write(bytes, 0, byteCount);
                    System.out.println(new String(Arrays.copyOf(bytes, byteCount)));
                    byteCount = fileInputStream.read(bytes, 0, BUFFER_SIZE);
                }
            } else {
                String errorMessage = "HTTP/1.1 404 File Not Found\r\n" +
                        "Content-Type: text/html\r\n" +
                        "Content-Length: 23\r\n" +
                        "\r\n" +
                        "<h1>File Not Found</h1>";
                outputStream.write(errorMessage.getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
