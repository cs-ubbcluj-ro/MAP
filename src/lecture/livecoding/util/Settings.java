package lecture.livecoding.util;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Settings {
    private String repoType;

    private String fileName;

    private Settings() {
    }

    public String getRepoType() {
        return repoType;
    }

    public String getFileName() {
        return fileName;
    }

    private static Settings instance;

    public static Settings getInstance() {
        if (instance == null) {
            // Citim fisierul de setari -- asta ruleaza o singura data
            Properties settings = new Properties();
            try {
                settings.load(new FileReader("settings.properties"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            instance = new Settings();
            instance.repoType = settings.getProperty("repo_type");
            instance.fileName = settings.getProperty("file_name");
        }
        return instance;
    }
}