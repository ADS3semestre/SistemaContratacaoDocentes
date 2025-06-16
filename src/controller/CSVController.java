package controller;

import java.io.File;

public class CSVController {


    public static String getOS() {
        String os = System.getProperty("os.name");
        return os;
    }

    public static String getFileName(String fileName) throws Exception{
        String path = System.getProperty("user.home") + File.separator + "SistemaDocentes";
        File dir = new File(path);
        if(!dir.exists()){
            dir.mkdir();
            path = path + File.separator + fileName;
            return path;
        } else{
            throw new Exception("Diretório inválido");
        }
    }
}