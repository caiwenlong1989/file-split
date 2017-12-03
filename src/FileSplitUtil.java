import java.io.IOException;
import java.util.List;

public class FileSplitUtil {

    public static void main(String[] args) {
        String path = "C:\\Users\\PC\\Desktop\\logs\\";
        String fileName = path + "lts-plateform-2017-11-28-0-164.log";
        int blockFileSize = 1024 * 1024 * 10;
        try {
            FileUtil fileUtil = new FileUtil();
            List<String> parts = fileUtil.splitBySize(fileName, blockFileSize);
            parts.forEach(part -> System.out.println(part));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
