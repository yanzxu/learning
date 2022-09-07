package ex01;

import java.io.IOException;
import java.io.InputStream;

public class Request {
    private final InputStream inputStream;
    private String resourceName;

    public Request(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void parse() {
        StringBuilder stringBuilder = new StringBuilder(2048);
        int byteCount;
        byte[] buffer = new byte[2048];

        try {
            byteCount = inputStream.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
            byteCount = -1;
        }

        for (int j = 0; j < byteCount; j++) {
            stringBuilder.append((char) buffer[j]);
        }

        resourceName = parseUri(stringBuilder.toString());
    }

    public String getResourceName() {
        return resourceName;
    }

    // request请求行 "GET /index.html HTTP/1.1"
    private String parseUri(String request) {
        int index1, index2;
        index1 = request.indexOf(' ');
        if (index1 != -1){
            index2 = request.indexOf(' ', index1 + 1);
            if (index2 > index1) {
                return request.substring(index1 + 1, index2);
            }
        }

        return null;
    }
}
