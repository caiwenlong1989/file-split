import java.io.File;

public class FileDelete {

    public static void main(String[] args) {
        String pathname = "E:/P2P/netloan/";
        recursion(pathname);
    }
    
    public static void recursion(String pathname) {
        File dir = new File(pathname);
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                recursion(file.getAbsolutePath());
            } else {
                String fileName = file.getName();
                if (fileName.startsWith("._")) {
                    System.out.println(fileName);
                    file.delete();
                }
            }
        }
    }

}
