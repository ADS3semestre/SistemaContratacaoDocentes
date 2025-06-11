package control;

public class CSVController {

    public CSVController() {
        super();
    }

    public static String getOS() {
        String os = System.getProperty("os.name");
        return os;
    }

}