package controller;

import java.io.File;

public class CSVController {


    public static String getOS() {
        String os = System.getProperty("os.name");
        return os;
    }

    public static String getFileName(String fileName) throws Exception{
        String path = "";
        if (getOS().contains("Windows")) {
            path = ".\\files\\" + fileName;
        } else {
            path = "./files/" + fileName;
        }
        File dir = new File(path);
        if(dir.exists() && dir.isDirectory()){
            return path;
        } else{
            throw new Exception("Diretório inválido");
        }
    }
}