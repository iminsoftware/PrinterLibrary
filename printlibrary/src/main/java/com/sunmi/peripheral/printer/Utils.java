package com.sunmi.peripheral.printer;

import android.os.Build;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Utils {
    public static ArrayList<String> spiDevicesList() {
        ArrayList<String> devicesList = new ArrayList<>();
        devicesList.add("M2-202");
        devicesList.add("M2-203");
        devicesList.add("M2-Pro");
        return devicesList;
    }

    public static ArrayList<String> doubleQRList() {
        ArrayList<String> devicesList = new ArrayList<>();
        devicesList.add("M2-203");
        devicesList.add("M2-Max");
        devicesList.add("M2-Pro");
        devicesList.add("D1");
        devicesList.add("D1-Pro");
        return devicesList;
    }
    //M2-203, M2 Pro, M2 Max, D1
    public static boolean isDoubleQRDev() {
        String model = getModel();
        if (TextUtils.isEmpty(model)) return false;

        if (doubleQRList().contains(model)) {
            return true;
        }
        return false;
    }

    public static String getModel() {

        /*if (Build.VERSION.SDK_INT >= 30) {
             return Build.MODEL;
        }*/
        String model = "";
        String plaform = getPlaform();

        if (!TextUtils.isEmpty(plaform) && plaform.startsWith("mt")) {
            model = getSystemProperties("ro.neostra.imin_model");
        } else if (!TextUtils.isEmpty(plaform) && plaform.startsWith("ums512")) {
            model = Build.MODEL;
        } else if (!TextUtils.isEmpty(plaform) && plaform.startsWith("sp9863a")) {
            model = Build.MODEL;
            if (model.equals("I22M01")) {
                model = "MS1-11";
            }
        } else {
            model = getSystemProperties("sys.neostra_oem_id");
            Log.d("TAG", "model " + model);
            if (!TextUtils.isEmpty(model) && model.length() > 4) {
                model = filterModel(model.substring(0, 5));
            } else {
                model = getSystemProperties("ro.neostra.imin_model");
            }
            if ("".equals(model)) {
                model = Build.MODEL;
                if (model.equals("I22D01")) {
                    model = "DS1-11";
                }
            }

        }
        return model;
    }
    public static String getPlaform() {
        return getSystemProperties("ro.board.platform");
    }

    public static String getSystemProperties(String property) {
        String value = "";
        try {
            Class clazz = Class.forName("android.os.SystemProperties");
            Method getter = clazz.getDeclaredMethod("get", String.class);
            value = (String) getter.invoke(null, property);
        } catch (Exception e) {
            Log.d("TAG", "Unable to read system properties");
        }
        return value;
    }
    private static String filterModel(String str) {
        switch (str) {
            case "W21XX":
                return "D1-501";
            case "W21MX":
                return "D1-502";
            case "W21DX":
                return "D1-503";
            case "W22XX":
                return "D1p-601";
            case "W22MX":
                return "D1p-602";
            case "W22DX":
                return "D1p-603";
            case "W22DC":
                return "D1p-604";
            case "W23XX":
                return "D1w-701";
            case "W23MX":
                return "D1w-702";
            case "W23DX":
                return "D1w-703";
            case "W23DC":
                return "D1w-704";
            case "V1GXX":
            case "V1GPX":
                return "D2-401";
            case "V1XXX":
            case "V1PXX":
                return "D2-402";
            case "V2BXX":
                return "D2 Pro";
            case "W26XX":
                return "D3-504";
            case "W26MX":
                return "D3-505";
            case "W27LX":
                return "D4-501";
            case "W27LD":
                return "D4-502";
            case "W27XX":
                return "D4-503";
            case "W27MX":
                return "D4-504";
            case "W27DX":
                return "D4-505";
            case "1824M":
                return "1824M";
            case "1824D":
                return "1824D";
            case "K21XX":
                return "K1-101";
            case "D20XX":
                return "R1-201";
            case "D20TX":
                return "R1-202";
            case "W17BX":
                return "S1-702";
            case "W17XX":
                return "S1-701";
            case "W26HX":
                return "D3-504";
            case "W26HM":
                return "D3-505";
            case "W26HD":
                return "D3-506";
            case "W26HG":
                return "K2-201";
            case "D224G":
                return "R2-301";//D224GM04SXXT3PXW3E1MXV110CDXXX
            case "D22XX":
                return "R2-301";// error ?
            case "D22TX":
                return "R2-302";
            case "W27DP":
                return "D4-505(Premium)";
            default:
                break;
        }
        return "";
    }

    public static boolean isSPIPrint() {
        if (TextUtils.equals("M2-202", Build.MODEL)) {
            return SystemProperties.getBoolean("persist.sys.isSPI", false);
        } else if (TextUtils.equals("M2-Pro", Build.MODEL)) {
            return true;
        }
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("/proc/neostra_hw_info"));
            String line;
            while ((line = reader.readLine()) != null) {
                Log.d("printlibrary", "hasSPI" + line.contains("SPI=ON"));
                return line.contains("SPI=ON") ? true : false;
            }
            reader.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block\r
            e.printStackTrace();
        }
        return false;
    }
}
