package android.os;

import java.util.ArrayList;

public class SystemProperties {
    public static final String VERSION_CODE = "ro.version.woyou_versioncode";
    public static final String VERSION_NAME = "ro.version.woyou_versionname";
    public static final String VERSION_NAME_T1 = "ro.version.sunmi_versionname";
    public static final int PROP_NAME_MAX = 31;
    public static final int PROP_VALUE_MAX = 91;
    private static final ArrayList<Runnable> sChangeCallbacks = new ArrayList();

    public SystemProperties() {
    }

    private static native String native_get(String key);

    private static native String native_get(String key, String def);

    private static native int native_get_int(String key, int def);

    private static native long native_get_long(String key, long def);

    private static native boolean native_get_boolean(String key, boolean def);

    private static native void native_set(String key, String def);

    private static native void native_add_change_callback();

    public static String get(String key) {
        if (key.length() > 31) {
            throw new IllegalArgumentException("key.length > 31");
        } else {
            return native_get(key);
        }
    }

    public static String get(String key, String def) {
        if (key.length() > 31) {
            throw new IllegalArgumentException("key.length > 31");
        } else {
            return native_get(key, def);
        }
    }

    public static int getInt(String key, int def) {
        if (key.length() > 31) {
            throw new IllegalArgumentException("key.length > 31");
        } else {
            return native_get_int(key, def);
        }
    }

    public static long getLong(String key, long def) {
        if (key.length() > 31) {
            throw new IllegalArgumentException("key.length > 31");
        } else {
            return native_get_long(key, def);
        }
    }

    public static boolean getBoolean(String key, boolean def) {
        if (key.length() > 31) {
            throw new IllegalArgumentException("key.length > 31");
        } else {
            return native_get_boolean(key, def);
        }
    }

    public static void set(String key, String val) {
        if (key.length() > 31) {
            throw new IllegalArgumentException("key.length > 31");
        } else if (val != null && val.length() > 91) {
            throw new IllegalArgumentException("val.length > 91");
        } else {
            native_set(key, val);
        }
    }

    public static void addChangeCallback(Runnable callback) {
        synchronized(sChangeCallbacks) {
            if (sChangeCallbacks.size() == 0) {
                native_add_change_callback();
            }

            sChangeCallbacks.add(callback);
        }
    }

    static void callChangeCallbacks() {
        synchronized(sChangeCallbacks) {
            if (sChangeCallbacks.size() != 0) {
                ArrayList<Runnable> callbacks = new ArrayList(sChangeCallbacks);

                for(int i = 0; i < callbacks.size(); ++i) {
                    ((Runnable)callbacks.get(i)).run();
                }

            }
        }
    }

}
