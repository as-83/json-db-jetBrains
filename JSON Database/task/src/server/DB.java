package server;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DB {
    protected static final String PATH = System.getProperty("user.dir") +
            File.separator + "src" + File.separator + "server" + File.separator + "data";
    // protected static final String PATH = "D:" + File.separator + "sul"  + File.separator + "data" + File.separator;
    static Map<String, String> map = new ConcurrentHashMap<>();
    protected static final String DB_FILE  = "db.json";
    private static final Object object = new Object();
    private  static volatile DB instance;

    private DB() {
        initMap();
    }

    private void initMap() {
        if (FileUtil.isFileExist(DB_FILE, PATH) && FileUtil.fileHasContent(DB_FILE, PATH)) {
            try {
                map = ;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            map = ;
        }
    }

    public static DB getInstance() {
        if (instance != null) {
            return instance;
        }
        synchronized (object) {
            if ( instance == null) {
                instance = new DB();
            }
        }
        return instance;

    }


    public boolean containsKey(String key) {
        return map.containsKey(key);
    }

    public String get(String key) {
        return map.get(key);
    }

    public void save(String key, String value) {
        map.put(key, value);
    }

    public void remove(String key) {
        map.remove(key);
    }
}
