package controller;

public class CSVController {

    public CSVController() {
        super();
    }

    public static String getOS() {
        String os = System.getProperty("os.name");
        return os;
    }

    public static String getFileName(String fileName){
        String path = "";
        if (getOS().contains("Windows")) {
            path = ".\\files\\" + fileName;
        } else {
            path = "./files/" + fileName;
        }
        return path;
    }
}