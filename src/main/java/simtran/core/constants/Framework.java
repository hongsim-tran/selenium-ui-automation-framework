package simtran.core.constants;

public class Framework {

    public static final String ROOT_PATH = System.getProperty("user.dir");
    public static final String OS = getOS();
    public static final String TEST_DATA_PATH = getFolderPathWithSlash("test-data") + getFolderPathWithSlash("data.xlsx");

    private static String getOS() {
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("win")) {
            return "Windows";
        } else if (osName.contains("linux")) {
            return "Linux";
        } else if (osName.contains("mac")) {
            return "macOS";
        } else {
            return "Unknown";
        }
    }

    private static String getFolderPathWithSlash(String folder) {
        if (getOS().equals("Linux") || getOS().equals("macOS")) {
            folder = "/" + folder;
        } else if (getOS().equals("Windows")) {
            folder = "\\" + folder;
        } else {
            folder = null;
        }
        return folder;
    }
}
