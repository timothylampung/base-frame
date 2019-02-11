package my.spotit.asset.helper;

import java.awt.*;
import java.io.File;

public class FileHelper {

    public static boolean openFile = false;

    private FileHelper() {
    }


    public static void openFile(File f) {
        if (!openFile) return;
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (isWindows(os)) {
                Desktop.getDesktop().open(f);
            } else if (isMac(os)) {
                String cmd = "open " + f.getAbsolutePath();
                System.out.println(cmd);
                Runtime.getRuntime().exec(cmd);
            } else {
                String cmd = "xdg-open '" + f.getAbsolutePath() + "'";
                System.out.println(cmd);
                Runtime.getRuntime().exec(new String[]{"bash", "-c", cmd});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean isWindows(String os) {
        return (os.indexOf("win") >= 0);
    }

    private static boolean isMac(String os) {
        return (os.indexOf("mac") >= 0);
    }

}
