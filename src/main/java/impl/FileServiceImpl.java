package impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import services.FileService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service

public class FileServiceImpl implements FileService {
    @Value("${path.to.data.file}")
    private String dataFilePath;
    @Value("${name.of.data.file}")
    private String dataFileName;
    @Override

    public boolean saveToFile(String json) {
        try {
          cleanData();
            Files.writeString(Path.of(dataFilePath), json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
@Override
    public String readFromFile() {
        try {
            return Files.readString(Path.of(dataFilePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private boolean cleanData() {
        try {
            Path path = Path.of(dataFilePath);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
      } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}