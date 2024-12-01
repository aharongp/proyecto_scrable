import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class BuscarArchivo {

    public static List<String> getFileNamesWithExtension(File directory, String extension) {
        List<String> fileNames = new ArrayList<>();

        if (directory.isDirectory()) {
            File[] files = directory.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.toLowerCase().endsWith(extension.toLowerCase());
                }
            });
            for (File file : files) {
                fileNames.add(file.getName());
            }
        } else {
            System.out.println("The provided path is not a directory.");
        }
        return fileNames;
    }
}

