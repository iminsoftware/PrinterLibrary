package com.sunmi.printerhelper.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.RemoteException;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.sunmi.peripheral.printer.ExceptionConst;
import com.sunmi.peripheral.printer.InnerLcdCallback;
import com.sunmi.peripheral.printer.InnerPrinterCallback;
import com.sunmi.peripheral.printer.InnerPrinterException;
import com.sunmi.peripheral.printer.InnerPrinterManager;
import com.sunmi.peripheral.printer.InnerResultCallback;
import com.sunmi.peripheral.printer.SunmiPrinterService;
import com.sunmi.peripheral.printer.WoyouConsts;
import com.sunmi.printerhelper.R;

/**
 * <pre>
 *      This class is used to demonstrate various printing effects
 *      Developers need to repackage themselves, for details please refer to
 *      http://sunmi-ota.oss-cn-hangzhou.aliyuncs.com/DOC/resource/re_cn/Sunmiprinter%E5%BC%80%E5%8F%91%E8%80%85%E6%96%87%E6%A1%A31.1.191128.pdf
 *  </pre>
 *
 * @author kaltin
 * @since create at 2020-02-14
 */
public class SunmiPrintHelper {

    public static int NoSunmiPrinter = 0x00000000;
    public static int CheckSunmiPrinter = 0x00000001;
    public static int FoundSunmiPrinter = 0x00000002;
    public static int LostSunmiPrinter = 0x00000003;

    /**
     *  sunmiPrinter means checking the printer connection status
     */
    public int sunmiPrinter = CheckSunmiPrinter;
    /**
     *  SunmiPrinterService for API
     */
    private SunmiPrinterService sunmiPrinterService;

    private static SunmiPrintHelper helper = new SunmiPrintHelper();

    private SunmiPrintHelper() {}

    public static SunmiPrintHelper getInstance() {
        return helper;
    }

    private InnerPrinterCallback innerPrinterCallback = new InnerPrinterCallback() {
        @Override
        protected void onConnected(SunmiPrinterService service) {
            sunmiPrinterService = service;
            checkSunmiPrinterService(service);
        }

        @Override
        protected void onDisconnected() {
            sunmiPrinterService = null;
            sunmiPrinter = LostSunmiPrinter;
        }
    };

    /**
     * init sunmi print service
     */
    public void initSunmiPrinterService(Context context){
        try {
            boolean ret =  InnerPrinterManager.getInstance().bindService(context,
                    innerPrinterCallback);
            Log.d("test_1_11", "ret ======>"+ret);
            if(!ret){
                sunmiPrinter = NoSunmiPrinter;
            }
        } catch (InnerPrinterException e) {
            e.printStackTrace();
        }
    }

    /**
     *  deInit sunmi print service
     */
    public void deInitSunmiPrinterService(Context context){
        try {
            if(sunmiPrinterService != null){
                InnerPrinterManager.getInstance().unBindService(context, innerPrinterCallback);
                sunmiPrinterService = null;
                sunmiPrinter = LostSunmiPrinter;
            }
        } catch (InnerPrinterException e) {
            e.printStackTrace();
        }
    }

    /**
     * Check the printer connection,
     * like some devices do not have a printer but need to be connected to the cash drawer through a print service
     */
    private void checkSunmiPrinterService(SunmiPrinterService service){
        boolean ret = false;
        try {
            ret = InnerPrinterManager.getInstance().hasPrinter(service);
        } catch (InnerPrinterException e) {
            e.printStackTrace();
        }
        sunmiPrinter = ret?FoundSunmiPrinter:NoSunmiPrinter;
    }

    /**
     *  Some conditions can cause interface calls to fail
     *  For example: the version is too low、device does not support
     *  You can see {@link ExceptionConst}
     *  So you have to handle these exceptions
     */
    private void handleRemoteException(RemoteException e){
        //TODO process when get one exception
    }

    /**
     * send esc cmd
     */
    public void sendRawData(byte[] data) {
        if(sunmiPrinterService == null){
            //TODO Service disconnection processing
            return;
        }
        try {
            sunmiPrinterService.sendRAWDataByteCallBack(data, null);
        } catch (RemoteException e) {
            handleRemoteException(e);
        }
    }

    /**
     *  Printer cuts paper and throws exception on machines without a cutter
     */
    public void cutpaper(){
        if(sunmiPrinterService == null){
            //TODO Service disconnection processing
            return;
        }
        try {
            sunmiPrinterService.cutPaper(null);
        } catch (RemoteException e) {
            handleRemoteException(e);
        }
    }

    /**
     *  Initialize the printer
     *  All style settings will be restored to default
     */
    public void initPrinter(){
        if(sunmiPrinterService == null){
            //TODO Service disconnection processing
            return;
        }
        try {
            sunmiPrinterService.printerInit(null);
        } catch (RemoteException e) {
            handleRemoteException(e);
        }
    }
    public Bitmap createBitmap(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            int offset = y * width;
            for (int x = 0; x < width; x++) {
                pixels[offset + x] = matrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF;
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

    /**
     *  paper feed three lines
     *  Not disabled when line spacing is set to 0
     */
    Bitmap bitmap = null;
    public void print3Line(){
        if(sunmiPrinterService == null){
            //TODO Service disconnection processing
            return;
        }

        try {
//            MultiFormatWriter multi = new MultiFormatWriter();
//            BitMatrix bitMatrix =
//                    multi.encode("11110AQ899015859344", BarcodeFormat.CODE_39, 1300, 320);
//            bitmap = createBitmap(bitMatrix);
//                                    if (bitmap.getWidth() > 384) {
//                                        bitmap = Bitmap.createScaledBitmap(bitmap,384,
//                                                bitmap.getHeight(), true);
//                                    }
////            String datas = "CgobMwAbKiEAAf////////////////////gAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAD/gAD/gAD/gAD/gAAfgAAfgAA/gAB/gAD/gAD/gAH/gAH/gAH/gAH/gBP/gH//gH//gH//gDv/gAH/gAH/gAH/gAH/gAD/gAD/gAB/gAB/gAA/gAAfgAA/gAD/gAD/gAD/gAB/gAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAP///////////////////wobKiEAAf///////////////////wAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADwAAH4AAH4AADwAAf8AD//4f//5/////////H4/8H4PwDwP4H4P8H4P+Dwc/Dw4f37wP//wD//wB//oj4H//wD//wD//wD//4HwB8PgD//wH//4P//4fn58/Dw/+Dwf4H4P4DwP4DwP8H4P/n55///4///wP//wB//gADwAADwAAH4AAH4AABgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAB///x///x///x///x///x/gAB/wAB//gB///AH//wAf/wAA/wAAPwAA/wAf/wP//x///B//gB/wAB/4AB///x///x///x///x///wAAAAAAAAAAABAAABwAAB4AAB+AAB/gAB/4AB/8AB//AAP/gAH//wB//wAf/wAf/wA//wD//wP/wA//AB/8AB/4AB/gAB+AAB4AABwAABAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAB///x///x///x///x///x///x///wAAAQAAAQAAAQAAAQAAAQAAAQAAAQAAAQAAAQAAAQAAAQAAAAAAAAADwAA//AD//wP//wf//wf//w///w/wDx/AAx+AAR8AAB8AAB8AAB8AAB+AAR+AAR/gBw///w///wf//wP//wH//wD//wAP8AAAAAAAAAAAAAAAAAA//AD//wH//wf//wf//w///w/4Hx/AAx+AAR+AAR8AAB8AAB8B8B8B8B+B8B/h8R/x8Q/x/w/h/wfh/wPB/wDB/wAB/wAAAAAAAAAAAAAAAAA//AD//wP//wf//wf//w///w/4Hx/gAx+AAR8AAB8AAB8AAB8AAB8AAB+AAR/gBw/8Pw///wf//wf//wH//wD//wA//AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAP///////////////////wobKiEAAf///////////////////wAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAOHwAPnwAP/wAP/wAP+AAD+AAH/AAP/gAP/wAPPwAOH4AMD4AAD4AAD4ABH8gP//4P//4P//4L/9wAD4AAD4AID4AMH4AOHwAPPwAP/gAH/gAH/AAH+AAP/AAP/wAPnwAPHwAIDgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAPgAAPgAAPgAAPgAAPgAAAAAAAAAAAAAAAAAAMAAAPgAAPgAAPgAAPgAAPgAAMAAAAAAAAAAAAAAAAAAAPgAAPgAAPgAAPgAAPgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAPgAAPgAAPgAAPgAAPgAAPgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAAgAAAAAAAAAAAAAAAAAAAAAAMAAAOAAAOAAAPAAAPAAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPAAAPAAAOAAAMAAAIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAMAAAOAAAOAAAPAAAPAAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPAAAPAAAPAAAOAAAOAAAMAAAIAAAAAAAAAAAAAAAAAAAAAAAAAAAMAAAOAAAOAAAPAAAPAAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPgAAPAAAPAAAOAAAOAAAIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAP///////////////////wobKiEAAf8AAP8AAP8AAP8AAP8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8AAB8A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/8D//8D//8D//8AD8D8D8D8D8D8D8D8D8D8D8D////////////////////////////////////////////////8D8D8D8D8D8D8D8D8D8D8D8D8D8AAD8AAD8AAD8AAD8AAD8AAH8AD///////////////////////8D//8D//8D//8D//8D//8D//8AAD8AAD8AAD8AAD8AAD8AAD8AD8AAD8AAD8AAD8AAD8AAD8D8D//4D//4D//4D//4D//8D/8D8D8D8D8D8D8D8D8D8D8D8D8AAAAAAAAAAAAAAAAAAAAAAAD///////////////////////8AAAAAAAAAAAAAAAAAAAAAAAD//8D//8D//8D//8D//8D//8D//8D//8D//8D//8D//8D//8D//8D//8D//8D//8D//8D//8AAAAAAAAAAAAAAAAAAAAAAAAAAAAD///////////////////8AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAKGyohyAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAD8AAD8AAD8AAD8AAD8AAD8AAD8AAD8AAD8AAD8AAD8AAD8AAD8D8D8D8D8D8D8D8D8D8D8AAD8AD/8AD/8AD/8AD/8AD/8AD/8D//8D//8D//8D//8D//8D//8D8D8D8D8D8D8D8D8D8D8D8D8D8D8D8D8D8D8D8D8D8D8D8AAAD8AAD8AAD8AAD8AAD8AAD/8D//8D//8D//8D//8D//8D/8D8AAD8AAD8AAD8AAD8AAD8AD//8D//8D//8D//8D//8D//8AAAAAAAAAAAAAAAAAAAAAAAAD///////////////////////8AD8AAD8AAD8AAD8AAD8AAD8AAD8D8D//8D//8D//8D//8D/8AAD8D8D8D8D8D8D8D8D8D8D8D8D/8D8D8D8D8D8D8D8D8D8D8D8AD8AAD8AAD8AAD8AAD8AAD8AD///////////////////////8AAD8AAD8AAD8AAD8AAD8AAD///8D//8D//8D//8D//8D//8AAD/8AD/8AD/8AD/8AD/8AD//8AD/8AD/8AD/8AD/8AD/8AD/8AAD8AAD8AAD8AAD8AAD8AAD8AAD8AAD8AAD8AAD8AAD8AAD8D//8D//8D//8D//8D//8D//8AAD8AAD8AAD8AAD8AAD8AAD8AAD8AAD8AAD8AAD8AAD8AAD8AAD8D//8D//8D//8D//8D/8AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAKGyohyAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAB8AB/8AD/8AD/8AD/8AD/8AD/8AB/8AAD8AAD8AAD8AAD8AAD8AAD//8D//8D//8D//8D//8D//8D/8AD/8AD/8AD/8AD/8AD/8AAD8AAD8AAD8AAD8AAD8AAD8AD8D8D8D8D8D8D8D8D8D8D8D8AD8AAD8AAD8AAD8AAD8AAD8AD///////////////////////8AD8AAD8AAD8AAD8AAD8AAB8AD/8AD/8AD/8AD/8AD/8AD/8D8D//8D//8D//8D//8D//8D/8AD8AAD8AAD8AAD8AAD8AAD8AAD8AAD8AAD8AAD8AAD8AAD8AAD8D8D8D8D8D8D8D8D8D8D8D8D8D//8D//8D//8D//8D//8D//8AD/8AD/8AD/8AD/8AD/8AD/8D/8D//8D//8D//8D//8D//8D/8D8D8D8D8D8D8D8D8D8D8D8AAD8AAD8AAD8AAD8AAD8AAD8AD8D8D8D8D8D8D8D8D8D8D8D8D8D8D8D8D8D8D8D8D8D8D8D8D//8D//8D//8D//8D//8D//8AD/8AD/8AD/8AD/8AD/8AD/8D8AAD8AAD8AAD8AAD8AAD8AD8AAD8AAD8AAD8AAD8AAD8AAD8AAD8AAD8AAD8AAD8AAD8AAD/8B//8D//8D//8D//8D//8D//8D/8AD/8AD/8AD/8AD/8AD8AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAKGyohyAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAB8B/98B/98B/98B/98B/8AB/8AB/8AB/8AB/8AB/8AB/8AB/98AB98AB98AB98AB98AB98AB8B+B8B+B8B+B8B+B8B+B8B+B98B+B8B+B8B+B8B+B8B+B8B+B8B+B8B+B8B+B8B+B8B+B8B+D8B+D8B+D8B+D8B+D8B+D8B+B/+AB/+AB/+AB/+AB/+AB/+AAAB8AAB+AAB+AAB+AAB+AAB+AAAB8AAB8AAB8AAB8AAB+AAB8AB8AAB+AAB+AAB+AAB+AAB+AB+AAB+AAB+AAB+AAB+AAB+AAD+AB/+AB/+AB/+AB/+AB/+AAAAAAAB/8AB/8AB/8AB/8AB/8AB/8D/8AB/+AB/+AB/+AB/+AB/8D8AAD8AAD8AAD8AAD8AAD8AAAAB/8AB/8AB/8AB/8AB/8AB/8AAAAAAAAAAAAAAAAAAAAAAACD/+AB/+AB/+AB/+AB/+AB/+AAB/8AB/8AB/8AB/8AB/8AB///////////////////////////+B//+B//+B//+B//+B//+B8AAAAAAAAAAAAAAAAAAAAAAAAAB/8AB/8AB/8AB/8AB/8AB/8AAB98AB98AB98AB98AB98AB//8B//+B//+B//+B//+B//+B//+B////////////////////8AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAKGyohyAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAB+B+B+B+B+B+B+B+B+B+AB+AAB+AAB+AAB+AAB+AAB+AAB+AD+B+D+B+D+B+D+B+D+B+D+AAD+AAD+AAD+AAD+AAD+AAD+AAD+AAD+AAD+AAD+AAD+AAD+AACB/+CB/+CB/+CB/+CB/+CB/+B+B+B+B+B+B+B+B+B+B+B+B+AB+AAB+AAB+AAB+AAB+AAB+AAAAB8AAB8AAB8AAB8AAB8AAB9+AAB+AAB+AAB+AAB+AAB+AAAB+AAB+AAB+AAB+AAB+AAB+ACAAACAAACAAACAAACAAACAAAB8B/9+B/9+B/9+B/9+B/9+B/9+B/9+B/9+B/9+B/9+B/9+B/9+AAB/+AB/+AB/+AB/+AB/+AAAAACAB+CAB+CAB+CAB+CAB+CAB+B+AAB+AAB+AAB+AAB+AAB+AAB/+AB/+AB/+AB/+AB/+AB/+AD+AB/+AB/+AB/+AB/+AB/+AB8B/+AB/+AB/+AB/+AB/+AB/+AB//8B//8B//8B//8B//8B///+B+D+B+D+B+D+B+D+B+D+B+CB/+CB/+CB/+CB/+CB/+CB/+D+B+D+B+D+B+D+B+D+B+D+B+B+B/9+B/9+B/9+B/9+B/9+B//+AAD+AAD+AAD+AAD+AAD+AAD+AAD+AAD+AAD+AAD+AAD+AAD///////////////////////////////////////////////8AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAKGyohyAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAB///9///9///9///9///9+AAB+AAB+AAB+AAB+AAB+AAB+AAB+B/9+B/9+B/9+B/9+B/9+B/9+B/9+B/9+B/9+B/9+B/9+B/9+B/9+B/9+B/9+B/9+B/9+B/9+AAB+AAB+AAB+AAB+AAB+AAB///9///9///9///9///9///8AAAAAAAAAAAAAAAAAAAAAAACB/+CB/+CB/+CB/+CB/+CB/+B//+B//+B//+B//+B//+B//+AAAB8AAB8AAB8AAB8AAB8AAB9/+B9/+B9/+B9/+B9/+B9/+B+AAB+AAB+AAB+AAB+AAB+AAB+B//+B//+B//+B//+B//+B//8B//8B//8B//8B//8B//8B//8AB+B+B+B+B+B+B+B+B+B+B+B+B+AAB+AAB+AAB+AAB+AAB+AAB+AAB+AAB+AAB+AAB+AAB+AAB+D+B+D+B+D+B+D+B+D+B+D+B+B+AAB+AAB+AAB+AAB+AAB+AAD///////////////////////8AB/8AB/8AB/8AB/8AB/8AB/9+B+B+B+B+B+B+B+B+B+B+B+AAB+AAB+AAB+AAB+AAB+AAB+D///////////////////////8AB+AAB+AAB+AAB+AAB+AAB+AAAAB+AAB+AAB+AAB+AAB+AAD//+D//+D//+D//+D//+D//+D//+D/+AD/+AD/+AD/+AD/+AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAKGyohyAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAD//+D//+D//+D//+D//+AAA+AAA+AAA+AAA+AAA+AAA+AAA+D+A+D+A+D+A+D+A+D+A+D+A+D+A+D+A+D+A+D+A+D+A+D+A+D+A+D+A+D+A+D+A+D+A+D+A+AAA+AAA+AAA+AAA+AAA+AAA+D//+D//+D//+D//+D//+D//+AAAAAAAAAAAAAAAAAAAAAAAAB+AAA+AAA+AAA+AAA+AAB+AAAAA+AAA+AAA+AAA+AAA+AAA+CB/+CB/+CB/+CB/+CB/+CB/+D+AAD+AAD+AAD+AAD+AAD+AAD//+D//+D//+D//+D//+D//+CB/ACB/ACB/ACB/ACB/ACB/ADAAAD+A+D+A+D+A+D+A+D+A+B/AAA//AA//AA//AA//AA//AA/+AA+A+A+A+A+A+A+A+B+A+A+A+AB/+AB/+AB/+AB/+AB/+AB/+AB+AAB/AAB/AAB/AAB/AAB/AB//+A//+A//+A//+A//+B//+CB/+CB/+CB/+CB/+CB/+CB/+CAA+CAA+CAA+CAA+CAA+CAA+AB/AAB/AAB/AAB/AAB/AAB/AB//AA//AA//AA//AA//AA//AD+A+D+A+D+A+D+A+D+A+D+A+AAA+AAA+AAA+AAA+AAA+AAA+AAAAB+AAA+AAA+AAA+AAB+AAAB/+AB/+AB/+AB/+AB/+AB/+AA/+B+A+A+A+A+A+A+A+B+A+AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAKGyohyAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAKGzIKCgodIQAbYQHV4srHtv7OrMLrw+jK9tXiyse2/s6swuvD6Mr21eLKx7b+zqzC68PoyvbV4srHtv7OrMLrw+jK9iAKCgoKCgoKHVYA";
////            byte[] content = Base64.decode(datas, Base64.DEFAULT);
////
////            sunmiPrinterService.sendRAWData(content, null);
//            sunmiPrinterService.printBitmap(bitmap,null);
            sunmiPrinterService.lineWrap(3, null);
        } catch (Exception e) {
            //handleRemoteException(e);
        }
    }

    /**
     * Get printer serial number
     */
    public String getPrinterSerialNo(){
        if(sunmiPrinterService == null){
            //TODO Service disconnection processing
            return "";
        }
        try {
            return sunmiPrinterService.getPrinterSerialNo();
        } catch (RemoteException e) {
            handleRemoteException(e);
            return "";
        }
    }

    /**
     * Get device model
     */
    public String getDeviceModel(){
        if(sunmiPrinterService == null){
            //TODO Service disconnection processing
            return "";
        }
        try {
            return sunmiPrinterService.getPrinterModal();
        } catch (RemoteException e) {
            handleRemoteException(e);
            return "";
        }
    }

    /**
     * Get firmware version
     */
    public String getPrinterVersion(){
        if(sunmiPrinterService == null){
            //TODO Service disconnection processing
            return "";
        }
        try {
            return sunmiPrinterService.getPrinterVersion();
        } catch (RemoteException e) {
            handleRemoteException(e);
            return "";
        }
    }

    /**
     * Get paper specifications
     */
    public String getPrinterPaper(){
        if(sunmiPrinterService == null){
            //TODO Service disconnection processing
            return "";
        }
        try {
            return sunmiPrinterService.getPrinterPaper() == 1?"58mm":"80mm";
        } catch (RemoteException e) {
            handleRemoteException(e);
            return "";
        }
    }

    /**
     * Get paper specifications
     */
    public void getPrinterHead(InnerResultCallback callbcak){
        if(sunmiPrinterService == null){
            //TODO Service disconnection processing
            return;
        }
        try {
             sunmiPrinterService.getPrinterFactory(callbcak);
        } catch (RemoteException e) {
            handleRemoteException(e);
        }
    }

    /**
     * Get printing distance since boot
     * Get printing distance through interface callback since 1.0.8(printerlibrary)
     */
    public void getPrinterDistance(InnerResultCallback callback){
        if(sunmiPrinterService == null){
            //TODO Service disconnection processing
            return;
        }
        try {
            sunmiPrinterService.getPrintedLength(callback);
        } catch (RemoteException e) {
            handleRemoteException(e);
        }
    }

    /**
     * Set printer alignment
     */
    public void setAlign(int align){
        if(sunmiPrinterService == null){
            //TODO Service disconnection processing
            return;
        }
        try {
            sunmiPrinterService.setAlignment(align, null);
        } catch (RemoteException e) {
            handleRemoteException(e);
        }
    }

    /**
     *  Due to the distance between the paper hatch and the print head,
     *  the paper needs to be fed out automatically
     *  But if the Api does not support it, it will be replaced by printing three lines
     */
    public void feedPaper(){
        if(sunmiPrinterService == null){
            //TODO Service disconnection processing
            return;
        }

        try {
            sunmiPrinterService.autoOutPaper(null);
        } catch (RemoteException e) {
            print3Line();
        }
    }

    /**
     * print text
     * setPrinterStyle:Api require V4.2.22 or later, So use esc cmd instead when not supported
     *  More settings reference documentation {@link WoyouConsts}
     * printTextWithFont:
     *  Custom fonts require V4.14.0 or later!
     *  You can put the custom font in the 'assets' directory and Specify the font name parameters
     *  in the Api.
     */
    public void printText(String content, float size, boolean isBold, boolean isUnderLine,
                          String typeface) {
        if(sunmiPrinterService == null){
            //TODO Service disconnection processing
            return;
        }

        try {
            try {
                sunmiPrinterService.setPrinterStyle(WoyouConsts.ENABLE_BOLD, isBold?
                        WoyouConsts.ENABLE:WoyouConsts.DISABLE);
            } catch (RemoteException e) {
                if (isBold) {
                    sunmiPrinterService.sendRAWDataByteCallBack(ESCUtil.boldOn(), null);
                } else {
                    sunmiPrinterService.sendRAWDataByteCallBack(ESCUtil.boldOff(), null);
                }
            }
            try {
                sunmiPrinterService.setPrinterStyle(WoyouConsts.ENABLE_UNDERLINE, isUnderLine?
                        WoyouConsts.ENABLE:WoyouConsts.DISABLE);
            } catch (RemoteException e) {
                if (isUnderLine) {
                    sunmiPrinterService.sendRAWDataByteCallBack(ESCUtil.underlineWithOneDotWidthOn(), null);
                } else {
                    sunmiPrinterService.sendRAWDataByteCallBack(ESCUtil.underlineOff(), null);
                }
            }
            sunmiPrinterService.printTextWithFont(content, typeface, size, null);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    /**29, 107, 73, 9, 123, 66, 49, 50, 67, 65, 97, 45, 45]
     * print Bar Code
     */
    public void printBarCode(String data, int symbology, int height, int width, int textposition) {
        if(sunmiPrinterService == null){
            //TODO Service disconnection processing
            return;
        }

        try {
            sunmiPrinterService.printBarCode(data, symbology, height, width, textposition, null);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * print Qr Code
     */
    public void printQr(String data, int modulesize, int errorlevel) {
        if(sunmiPrinterService == null){
            //TODO Service disconnection processing
            return;
        }

        try {
            sunmiPrinterService.printQRCode(data, modulesize, errorlevel, null);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Print a row of a table
     */
    public void printTable(String[] txts, int[] width, int[] align) {
        if(sunmiPrinterService == null){
            //TODO Service disconnection processing
            return;
        }

        try {
            Log.d("test_1_dddd","iiiii-----printTable  colsAlign==>"+ txts);

            sunmiPrinterService.printColumnsString(txts, width, align, null);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     *  Print pictures and text in the specified orde
     *  After the picture is printed,
     *  the line feed output needs to be called,
     *  otherwise it will be saved in the cache
     *  In this example, the image will be printed because the print text content is added
     */
    public void printBitmap(Bitmap bitmap, int orientation) {
        if(sunmiPrinterService == null){
            //TODO Service disconnection processing
            return;
        }

        try {
            if(orientation == 0){
                sunmiPrinterService.printBitmap(bitmap, null);
                sunmiPrinterService.printText("横向排列\n", null);
                sunmiPrinterService.printBitmap(bitmap, null);
                sunmiPrinterService.printText("横向排列\n", null);
            }else{
                sunmiPrinterService.printBitmap(bitmap, null);
                sunmiPrinterService.printText("\n纵向排列\n", null);
                sunmiPrinterService.printBitmap(bitmap, null);
                sunmiPrinterService.printText("\n纵向排列\n", null);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets whether the current printer is in black mark mode
     */
    public boolean isBlackLabelMode(){
        if(sunmiPrinterService == null){
            //TODO Service disconnection processing
            return false;
        }
        try {
            return sunmiPrinterService.getPrinterMode() == 1;
        } catch (RemoteException e) {
            return false;
        }
    }

    /**
     * Gets whether the current printer is in label-printing mode
     */
    public boolean isLabelMode(){
        if(sunmiPrinterService == null){
            //TODO Service disconnection processing
            return false;
        }
        try {
            return sunmiPrinterService.getPrinterMode() == 2;
        } catch (RemoteException e) {
            return false;
        }
    }

    /**
     *  Transaction printing:
     *  enter->print->exit(get result) or
     *  enter->first print->commit(get result)->twice print->commit(get result)->exit(don't care
     *  result)
     */
    public void printTrans(Context context, InnerResultCallback callbcak){
        if(sunmiPrinterService == null){
            //TODO Service disconnection processing
            return;
        }

        try {
            sunmiPrinterService.enterPrinterBuffer(true);
            printExample(context);
            sunmiPrinterService.exitPrinterBufferWithCallback(true, callbcak);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     *  Open cash box
     *  This method can be used on Sunmi devices with a cash drawer interface
     *  If there is no cash box (such as V1、P1) or the call fails, an exception will be thrown
     *
     *  Reference to https://docs.sunmi.com/general-function-modules/external-device-debug/cash-box-driver/}
     */
    public void openCashBox(){
        if(sunmiPrinterService == null){
            //TODO Service disconnection processing
            return;
        }

        try {
            sunmiPrinterService.openDrawer(null);
        } catch (RemoteException e) {
            handleRemoteException(e);
        }
    }

    /**
     * LCD screen control
     * @param flag 1 —— Initialization
     *             2 —— Light up screen
     *             3 —— Extinguish screen
     *             4 —— Clear screen contents
     */
    public void controlLcd(int flag){
        if(sunmiPrinterService == null){
            //TODO Service disconnection processing
            return;
        }

        try {
            sunmiPrinterService.sendLCDCommand(flag);
        } catch (RemoteException e) {
            handleRemoteException(e);
        }
    }

    /**
     * Display text SUNMI,font size is 16 and format is fill
     * sendLCDFillString(txt, size, fill, callback)
     * Since the screen pixel height is 40, the font should not exceed 40
     */
    public void sendTextToLcd(){
        if(sunmiPrinterService == null){
            //TODO Service disconnection processing
            return;
        }

        try {
            sunmiPrinterService.sendLCDFillString("SUNMI", 16, true, new InnerLcdCallback() {
                @Override
                public void onRunResult(boolean show) throws RemoteException {
                    //TODO handle result
                }

                @Override
                public void onReturnString(String result) throws RemoteException {

                }

                @Override
                public void onRaiseException(int code, String msg) throws RemoteException {

                }

                @Override
                public void onPrintResult(int code, String msg) throws RemoteException {

                }

                @Override
                public void callback(int status) throws RemoteException {

                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    /**
     * Display two lines and one empty line in the middle
     */
    public void sendTextsToLcd(){
        if(sunmiPrinterService == null){
            //TODO Service disconnection processing
            return;
        }

        try {
            String[] texts = {"SUNMI", null, "SUNMI"};
            int[] align = {2, 1, 2};
            sunmiPrinterService.sendLCDMultiString(texts, align, new InnerLcdCallback() {
                @Override
                public void onRunResult(boolean show) throws RemoteException {
                    //TODO handle result
                }

                @Override
                public void onReturnString(String result) throws RemoteException {

                }

                @Override
                public void onRaiseException(int code, String msg) throws RemoteException {

                }

                @Override
                public void onPrintResult(int code, String msg) throws RemoteException {

                }

                @Override
                public void callback(int status) throws RemoteException {

                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    /**
     * Display one 128x40 pixels and opaque picture
     */
    public void sendPicToLcd(Bitmap pic){
        if(sunmiPrinterService == null){
            //TODO Service disconnection processing
            return;
        }

        try {
            sunmiPrinterService.sendLCDBitmap(pic, new InnerLcdCallback() {
                @Override
                public void onRunResult(boolean show) throws RemoteException {
                    //TODO handle result
                }

                @Override
                public void onReturnString(String result) throws RemoteException {

                }

                @Override
                public void onRaiseException(int code, String msg) throws RemoteException {

                }

                @Override
                public void onPrintResult(int code, String msg) throws RemoteException {

                }

                @Override
                public void callback(int status) throws RemoteException {

                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    /**
     *  Sample print receipt
     */
    public void printExample(Context context){
        if(sunmiPrinterService == null){
            //TODO Service disconnection processing
            return ;
        }

        try {
            int paper = sunmiPrinterService.getPrinterPaper();
            sunmiPrinterService.printerInit(null);
            sunmiPrinterService.setAlignment(1, null);
            sunmiPrinterService.printText("测试样张\n", null);
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.sunmi);
            sunmiPrinterService.printBitmap(bitmap, null);
            sunmiPrinterService.lineWrap(1, null);
            sunmiPrinterService.setAlignment(0, null);
            try {
                sunmiPrinterService.setPrinterStyle(WoyouConsts.SET_LINE_SPACING, 0);
            } catch (RemoteException e) {
                sunmiPrinterService.sendRAWDataByteCallBack(new byte[]{0x1B, 0x33, 0x00}, null);
            }
            sunmiPrinterService.printTextWithFont("说明：这是一个自定义的小票样式例子,开发者可以仿照此进行自己的构建\n",
                    null, 12, null);
            if(paper == 1){
                sunmiPrinterService.printText("--------------------------------\n", null);
            }else{
                sunmiPrinterService.printText("------------------------------------------------\n",
                        null);
            }
            try {
                sunmiPrinterService.setPrinterStyle(WoyouConsts.ENABLE_BOLD, WoyouConsts.ENABLE);
            } catch (RemoteException e) {
                sunmiPrinterService.sendRAWDataByteCallBack(ESCUtil.boldOn(), null);
            }
            String txts[] = new String[]{"商品", "价格"};
            int width[] = new int[]{1, 1};
            int align[] = new int[]{0, 2};
            sunmiPrinterService.printColumnsString(txts, width, align, null);
            try {
                sunmiPrinterService.setPrinterStyle(WoyouConsts.ENABLE_BOLD, WoyouConsts.DISABLE);
            } catch (RemoteException e) {
                sunmiPrinterService.sendRAWDataByteCallBack(ESCUtil.boldOff(), null);
            }
            if(paper == 1){
                sunmiPrinterService.printText("--------------------------------\n", null);
            }else{
                sunmiPrinterService.printText("------------------------------------------------\n",
                        null);
            }
            txts[0] = "汉堡";
            txts[1] = "17¥";
            sunmiPrinterService.printColumnsString(txts, width, align, null);
            txts[0] = "可乐";
            txts[1] = "10¥";
            sunmiPrinterService.printColumnsString(txts, width, align, null);
            txts[0] = "薯条";
            txts[1] = "11¥";
            sunmiPrinterService.printColumnsString(txts, width, align, null);
            txts[0] = "炸鸡";
            txts[1] = "11¥";
            sunmiPrinterService.printColumnsString(txts, width, align, null);
            txts[0] = "圣代";
            txts[1] = "10¥";
            sunmiPrinterService.printColumnsString(txts, width, align, null);
            if(paper == 1){
                sunmiPrinterService.printText("--------------------------------\n", null);
            }else{
                sunmiPrinterService.printText("------------------------------------------------\n",
                        null);
            }
            sunmiPrinterService.printTextWithFont("总计:          59¥\b", null, 40, null);
            sunmiPrinterService.setAlignment(1, null);
            sunmiPrinterService.printQRCode("谢谢惠顾", 10, 0, null);
            sunmiPrinterService.setFontSize(36, null);
            sunmiPrinterService.printText("谢谢惠顾", null);
            sunmiPrinterService.autoOutPaper(null);
         } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Used to report the real-time query status of the printer, which can be used before each
     * printing
     */
    public void showPrinterStatus(Context context){
        if(sunmiPrinterService == null){
            //TODO Service disconnection processing
            return ;
        }
        String result = "Interface is too low to implement interface";
        try {
            int res = sunmiPrinterService.updatePrinterState();
            switch (res){
                case 1:
                    result = "printer is running";
                    break;
                case 2:
                    result = "printer found but still initializing";
                    break;
                case 3:
                    result = "printer hardware interface is abnormal and needs to be reprinted";
                    break;
                case 4:
                    result = "printer is out of paper";
                    break;
                case 5:
                    result = "printer is overheating";
                    break;
                case 6:
                    result = "printer's cover is not closed";
                    break;
                case 7:
                    result = "printer's cutter is abnormal";
                    break;
                case 8:
                    result = "printer's cutter is normal";
                    break;
                case 9:
                    result = "not found black mark paper";
                    break;
                case 505:
                    result = "printer does not exist";
                    break;
                default:
                    break;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }

    /**
     * Demo printing a label
     * After printing one label, in order to facilitate the user to tear the paper, call
     * labelOutput to push the label paper out of the paper hatch
     * 演示打印一张标签
     * 打印单张标签后为了方便用户撕纸可调用labelOutput,将标签纸推出纸舱口
     */
    public void printOneLabel() {
        if(sunmiPrinterService == null){
            //TODO Service disconnection processing
            return ;
        }
        try {
            sunmiPrinterService.labelLocate();
            printLabelContent();
            sunmiPrinterService.labelOutput();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Demo printing multi label
     *
     After printing multiple labels, choose whether to push the label paper to the paper hatch according to the needs
     * 演示打印多张标签
     * 打印多张标签后根据需求选择是否推出标签纸到纸舱口
     */
    public void printMultiLabel(int num) {
        if(sunmiPrinterService == null){
            //TODO Service disconnection processing
            return ;
        }
        try {
            for(int i = 0; i < num; i++){
                sunmiPrinterService.labelLocate();
                printLabelContent();
            }
            sunmiPrinterService.labelOutput();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     *  Custom label ticket content
     *  In the example, not all labels can be applied. In actual use, please pay attention to adapting the size of the label. You can adjust the font size and content position.
     *  自定义的标签小票内容
     *  例子中并不能适用所有标签纸，实际使用时注意要自适配标签纸大小，可通过调节字体大小，内容位置等方式
     */
    private void printLabelContent() throws RemoteException {
        sunmiPrinterService.setPrinterStyle(WoyouConsts.ENABLE_BOLD, WoyouConsts.ENABLE);
        sunmiPrinterService.lineWrap(1, null);
        sunmiPrinterService.setAlignment(0, null);
        sunmiPrinterService.printText("商品         豆浆\n", null);
        sunmiPrinterService.printText("到期时间         12-13  14时\n", null);
        sunmiPrinterService.printBarCode("{C1234567890123456",  8, 90, 2, 2, null);
        sunmiPrinterService.lineWrap(1, null);
    }

    public void printColumnsText(String[] colsTextArr, int[] colsWidthArr, int[] colsAlign, int[] size) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {

            Log.d("test_1_dddd","iiiii-----printTable  colsAlign==>"+ colsTextArr[0]+" "+colsTextArr[1]+" "+colsTextArr[2]);
            sunmiPrinterService.printColumnsText(colsTextArr, colsWidthArr, colsAlign,size);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
