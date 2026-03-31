package banner;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class BannerGrabber {

    public static String grabBanner(Socket socket, int port) {

        try {

            socket.setSoTimeout(2000);

            if (port == 80 || port == 8080 || port == 443) {

                OutputStream out = socket.getOutputStream();
                out.write("GET / HTTP/1.1\r\nHost: target\r\n\r\n".getBytes());
                out.flush();

            }

            InputStream in = socket.getInputStream();
            byte[] buffer = new byte[1024];

            int bytesRead = in.read(buffer);

            if (bytesRead > 0) {
                return new String(buffer, 0, bytesRead).trim();
            }

        } catch (Exception ignored) {}

        return "No Banner";
    }
}