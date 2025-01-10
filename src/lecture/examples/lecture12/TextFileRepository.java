package lecture.examples.lecture12;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class TextFileRepository {
    private ArrayList<WritableToCSV> elements = new ArrayList<>();
    private String filename;

    public TextFileRepository(String filename) {
        this.filename = filename;
        this.readFromFile();
    }

    private void readFromFile() {
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(filename));
            String line = null;
            while ((line = br.readLine()) != null) {
                String className = this.filename;
                // get only the filename and then exclude the extension from the filename
                int pos = className.lastIndexOf('/');
                className = className.substring(pos + 1);
                className = className.replaceFirst(".txt", "");
                Class currentClass = Class.forName("lecture.examples.lecture12." + className);
                Constructor constructorWithString =
                        currentClass.getConstructor(String.class);
                WritableToCSV newObj = (WritableToCSV)
                        constructorWithString.newInstance(line);
                this.elements.add(newObj);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            if (br != null)
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println("Error while closing the file " + e);
                }
        }
    }

    private void writeToFile() {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(filename));
            for (WritableToCSV o : this.elements) {
                bw.write(o.toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addElement(WritableToCSV elem) {
        this.elements.add(elem);
        this.writeToFile();
    }

    ArrayList<WritableToCSV> getAll() {
        return this.elements;
    }
}
