package lecture.examples.lecture4.streams;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class IOReaderWriter {

    public static void readWithFileInputStream() {
        FileReader in = null;
        FileWriter out = null;

        try {
            in = new FileReader("src/lecture/examples/lecture4/streams/File1.txt");
            out = new FileWriter("src/lecture/examples/lecture4/streams/File2.txt");

            int c;
            while ((c = in.read()) != -1)
                out.write(c);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        readWithFileInputStream();
    }

}
