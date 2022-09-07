package ex01;

import java.io.IOException;
import java.io.InputStream;

public class Request {
    private final InputStream inputStream;
    private String uri;

    public Request(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void parse() {
        StringBuffer requestBuffer = new StringBuffer(2048);
        int byteCount;
        byte[] buffer = new byte[2048];

        try {
            byteCount = inputStream.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
            byteCount = -1;
        }

        for (int j = 0; j < byteCount; j++) {
            requestBuffer.append((char) buffer[j]);
        }

        uri = parseUri(requestBuffer.toString());
    }

    public String getUri() {
        return uri;
    }

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
