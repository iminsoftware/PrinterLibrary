package com.sunmi.peripheral.printer;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

public class IminPrintUtils {
    public static int NoSunmiPrinter = 0x00000000;
    public static int CheckSunmiPrinter = 0x00000001;
    public static int FoundSunmiPrinter = 0x00000002;
    public static int LostSunmiPrinter = 0x00000003;

    /**
     * sunmiPrinter means checking the printer connection status
     */
    public int sunmiPrinter = CheckSunmiPrinter;
    /**
     * SunmiPrinterService for API
     */
    private SunmiPrinterService sunmiPrinterService;

    private static IminPrintUtils helper = new IminPrintUtils();

    private IminPrintUtils() {
    }

    public static IminPrintUtils getInstance() {
        return helper;
    }

    private InnerPrinterCallback innerPrinterCallback = new InnerPrinterCallback() {
        @Override
        protected void onConnected(SunmiPrinterService service) {
            sunmiPrinterService = service;
            checkSunmiPrinterService(service);
            Log.i("test_1_000000", "iiii   onConnected");
        }

        @Override
        protected void onDisconnected() {
            sunmiPrinterService = null;
            sunmiPrinter = LostSunmiPrinter;
            Log.i("test_1_000000", "iiii   onDisconnected");
        }
    };

    /**
     *
     * init sunmi print service
     */
    public void initSunmiPrinterService(Context context) {
        try {
            boolean ret = InnerPrinterManager.getInstance().bindService(context,
                    innerPrinterCallback);
            if (!ret) {
                sunmiPrinter = NoSunmiPrinter;
            }
        } catch (InnerPrinterException e) {
            e.printStackTrace();
        }
    }

    /**
     * deInit sunmi print service
     */
    public void deInitSunmiPrinterService(Context context) {
        try {
            if (sunmiPrinterService != null) {
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
    private void checkSunmiPrinterService(SunmiPrinterService service) {
        boolean ret = false;
        try {
            ret = InnerPrinterManager.getInstance().hasPrinter(service);
            int status = sunmiPrinterService.updatePrinterState();

            Log.i("test_1_000000", "iiii   checkSunmiPrinterService " + ret + "  status==  " + status + "  ttt " + sunmiPrinterService.getFirmwareStatus());

        } catch (Exception e) {
            e.printStackTrace();
        }
        sunmiPrinter = ret ? FoundSunmiPrinter : NoSunmiPrinter;
    }

    /**
     * Some conditions can cause interface calls to fail
     * For example: the version is too low、device does not support
     * You can see {@link ExceptionConst}
     * So you have to handle these exceptions
     */
    private void handleRemoteException(RemoteException e) {
        //TODO process when get one exception
    }

    /**
     * send esc cmd
     */
    public void sendRawData(byte[] data) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.sendRAWData(data, null);
        } catch (RemoteException e) {
            handleRemoteException(e);
        }
    }

    /**
     * Printer cuts paper and throws exception on machines without a cutter
     */
    public void cutpaper() {
        if (sunmiPrinterService == null) {
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
     * Initialize the printer
     * All style settings will be restored to default
     */
    public void initPrinter() {
        if (sunmiPrinterService == null) {
            //TODO Service disconnection processing
            return;
        }
        try {
            sunmiPrinterService.printerInit(null);
        } catch (RemoteException e) {
            handleRemoteException(e);
        }
    }

    /**
     * paper feed three lines
     * Not disabled when line spacing is set to 0
     */
    public void print3Line() {
        if (sunmiPrinterService == null) {
            //TODO Service disconnection processing
            return;
        }

        try {
//            for (int i = 0;i<100000;i++){
//
//            }
            sunmiPrinterService.lineWrap(3, null);
        } catch (RemoteException e) {
            handleRemoteException(e);
        }
    }

    /**
     * Get printer serial number
     */
    public String getPrinterSerialNo() {
        if (sunmiPrinterService == null) {
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
    public String getDeviceModel() {
        if (sunmiPrinterService == null) {
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
    public String getPrinterVersion() {
        if (sunmiPrinterService == null) {
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
    public void getPrinterFactory(InnerResultCallback callbcak) {
        if (sunmiPrinterService == null) {
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
    public void getPrinterDistance(InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
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
    public void setAlign(int align) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.setAlignment(align, null);
        } catch (RemoteException e) {
            handleRemoteException(e);
        }
    }

    /**
     * Due to the distance between the paper hatch and the print head,
     * the paper needs to be fed out automatically
     * But if the Api does not support it, it will be replaced by printing three lines
     */
    public void feedPaper() {
        if (sunmiPrinterService == null) {
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
     * setPrinterStyle Api require V4.2.22 or later, So use esc cmd instead when not supported
     * More settings reference documentation {@link WoyouConsts}
     */
    public void printText(String content, float size, boolean isBold, boolean isUnderLine) {
        if (sunmiPrinterService == null) {
            //TODO Service disconnection processing
            return;
        }

        try {
            try {
                sunmiPrinterService.setPrinterStyle(WoyouConsts.ENABLE_BOLD, isBold ?
                        WoyouConsts.ENABLE : WoyouConsts.DISABLE);
            } catch (RemoteException e) {
                if (isBold) {
                    sunmiPrinterService.sendRAWData(ESCUtil.boldOn(), null);
                } else {
                    sunmiPrinterService.sendRAWData(ESCUtil.boldOff(), null);
                }
            }
            try {
                sunmiPrinterService.setPrinterStyle(WoyouConsts.ENABLE_UNDERLINE, isUnderLine ?
                        WoyouConsts.ENABLE : WoyouConsts.DISABLE);
            } catch (RemoteException e) {
                if (isUnderLine) {
                    sunmiPrinterService.sendRAWData(ESCUtil.underlineWithOneDotWidthOn(), null);
                } else {
                    sunmiPrinterService.sendRAWData(ESCUtil.underlineOff(), null);
                }
            }
            sunmiPrinterService.printTextWithFont(content, null, size, null);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    /**
     * print Bar Code
     */
    public void printBarCode(String data, int symbology, int height, int width, int textposition, InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }

        try {
            sunmiPrinterService.printBarCode(data, symbology, height, width, textposition, callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * print Qr Code
     */
    public void printQRCode(String data, int modulesize, int errorlevel, InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }

        try {
            sunmiPrinterService.printQRCode(data, modulesize, errorlevel, callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Print a row of a table
     */
    public void printColumnsString(String[] txts, int[] width, int[] align, InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.printColumnsString(txts, width, align, callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Print pictures and text in the specified orde
     * After the picture is printed,
     * the line feed output needs to be called,
     * otherwise it will be saved in the cache
     * In this example, the image will be printed because the print text content is added
     */
    public void printBitmap(Bitmap bitmap, int orientation) {
        if (sunmiPrinterService == null) {
            //TODO Service disconnection processing
            return;
        }

        try {
            if (orientation == 0) {
                sunmiPrinterService.printBitmap(bitmap, null);
                sunmiPrinterService.printText("横向排列\n", null);
                sunmiPrinterService.printBitmap(bitmap, null);
                sunmiPrinterService.printText("横向排列\n", null);
            } else {
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
    public boolean isBlackLabelMode() {
        if (sunmiPrinterService == null) {
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
    public boolean isLabelMode() {
        if (sunmiPrinterService == null) {
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
     * Open cash box
     * This method can be used on Sunmi devices with a cash drawer interface
     * If there is no cash box (such as V1、P1) or the call fails, an exception will be thrown
     * <p>
     * Reference to https://docs.sunmi.com/general-function-modules/external-device-debug/cash-box-driver/}
     */
    public void openCashBox(InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }

        try {
            sunmiPrinterService.openDrawer(callback);
        } catch (RemoteException e) {
            handleRemoteException(e);
        }
    }

    /**
     * LCD screen control
     *
     * @param flag 1 —— Initialization
     *             2 —— Light up screen
     *             3 —— Extinguish screen
     *             4 —— Clear screen contents
     */
    public void controlLcd(int flag) {
        if (sunmiPrinterService == null) {
            //TODO Service disconnection processing
            return;
        }

        try {
            Log.d("test_1_dddd", "iiiii-----printTable  controlLcd==>" + flag);
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
    public void sendTextToLcd() {
        if (sunmiPrinterService == null) {
            //TODO Service disconnection processing
            return;
        }

        try {
            sunmiPrinterService.sendLCDFillString("SUNMI", 16, true, new InnerLcdCallback() {
                @Override
                public void onRunResult(boolean show) throws RemoteException {
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
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    /**
     * Display two lines and one empty line in the middle
     */
    public void sendTextsToLcd() {
        if (sunmiPrinterService == null) {
            //TODO Service disconnection processing
            return;
        }

        try {
            String[] texts = {"SUNMI", "null", "SUNMI"};
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
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    /**
     * Display one 128x40 pixels and opaque picture
     */
    public void sendPicToLcd(Bitmap pic) {

    }

    /**
     * Used to report the real-time query status of the printer, which can be used before each
     * printing
     */
    public void showPrinterStatus(Context context) {
        if (sunmiPrinterService == null) {
            //TODO Service disconnection processing
            return;
        }
        String result = "Interface is too low to implement interface";
        try {
            int res = sunmiPrinterService.updatePrinterState();
            switch (res) {
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
        if (sunmiPrinterService == null) {
            //TODO Service disconnection processing
            return;
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
     * <p>
     * After printing multiple labels, choose whether to push the label paper to the paper hatch according to the needs
     * 演示打印多张标签
     * 打印多张标签后根据需求选择是否推出标签纸到纸舱口
     */
    public void printMultiLabel(int num) {
        if (sunmiPrinterService == null) {
            //TODO Service disconnection processing
            return;
        }
        try {
            for (int i = 0; i < num; i++) {
                sunmiPrinterService.labelLocate();
                printLabelContent();
            }
            sunmiPrinterService.labelOutput();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Custom label ticket content
     * In the example, not all labels can be applied. In actual use, please pay attention to adapting the size of the label. You can adjust the font size and content position.
     * 自定义的标签小票内容
     * 例子中并不能适用所有标签纸，实际使用时注意要自适配标签纸大小，可通过调节字体大小，内容位置等方式
     */
    private void printLabelContent() throws RemoteException {
        sunmiPrinterService.setPrinterStyle(WoyouConsts.ENABLE_BOLD, WoyouConsts.ENABLE);
        sunmiPrinterService.lineWrap(1, null);
        sunmiPrinterService.setAlignment(0, null);
        sunmiPrinterService.printText("商品         豆浆\n", null);
        sunmiPrinterService.printText("到期时间         12-13  14时\n", null);
        sunmiPrinterService.printBarCode("{C1234567890123456", 8, 90, 2, 2, null);
        sunmiPrinterService.lineWrap(1, null);
    }

    public void updateFirmware() {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.updateFirmware();
        } catch (RemoteException e) {
            handleRemoteException(e);
        }
    }

    public int getFirmwareStatus() {
        if (sunmiPrinterService == null) {
            return 0;
        }
        try {
            return sunmiPrinterService.getFirmwareStatus();
        } catch (RemoteException e) {
            handleRemoteException(e);
        }
        return 0;
    }

    public String getServiceVersion() {
        if (sunmiPrinterService == null) {
            return "";
        }
        try {
            return sunmiPrinterService.getServiceVersion();
        } catch (RemoteException e) {
            handleRemoteException(e);
        }
        return "";
    }

    public void printerInit(InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.printerInit(callback);
        } catch (RemoteException e) {
            handleRemoteException(e);
        }
    }

    public void printerSelfChecking(InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.printerSelfChecking(callback);
        } catch (RemoteException e) {
            handleRemoteException(e);
        }
    }

    public String getPrinterModal() {
        if (sunmiPrinterService == null) {
            return "";
        }
        try {
            return sunmiPrinterService.getPrinterModal();
        } catch (RemoteException e) {
            handleRemoteException(e);
        }
        return "";
    }

    public void getPrintedLength(InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.getPrintedLength(callback);
        } catch (RemoteException e) {
            handleRemoteException(e);
        }
        return;
    }

    public void lineWrap(int n, InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }

        try {
            sunmiPrinterService.lineWrap(n, callback);
        } catch (RemoteException e) {
            handleRemoteException(e);
        }
    }

    public void setFontSize(float fontsize, InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }

        try {
            sunmiPrinterService.setFontSize(fontsize, callback);
        } catch (RemoteException e) {
            handleRemoteException(e);
        }
    }

    public void printText(String text, InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }

        try {
            sunmiPrinterService.printText(text, callback);
        } catch (RemoteException e) {
            handleRemoteException(e);
        }
    }

    public void printTextWithFont(String text, String typeface, float fontsize, InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.printTextWithFont(text, typeface, fontsize, callback);
        } catch (RemoteException e) {
            handleRemoteException(e);
        }
    }

    public void printColumnsText(String[] colsTextArr, int[] colsWidthArr, int[] colsAlign, InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.printColumnsText(colsTextArr, colsWidthArr, colsAlign, callback);
        } catch (RemoteException e) {
            handleRemoteException(e);
        }
    }

    public void printBitmap(Bitmap bitmap, InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.printBitmap(bitmap, callback);
        } catch (RemoteException e) {
            handleRemoteException(e);
        }
    }

    public void printOriginalText(String text, InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.printOriginalText(text, callback);
        } catch (RemoteException e) {
            handleRemoteException(e);
        }
    }

    public void commitPrint(TransBean[] transbean, InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.commitPrint(transbean, callback);
        } catch (RemoteException e) {
            handleRemoteException(e);
        }
    }

    public void commitPrinterBuffer() {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.commitPrinterBuffer();
        } catch (RemoteException e) {
            handleRemoteException(e);
        }
    }

    public void cutPaper(InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.cutPaper(callback);
        } catch (RemoteException e) {
            handleRemoteException(e);
        }
    }

    public int getCutPaperTimes() {
        if (sunmiPrinterService == null) {
            return 0;
        }
        try {
            return sunmiPrinterService.getCutPaperTimes();
        } catch (RemoteException e) {
            handleRemoteException(e);
        }
        return 0;
    }

    public int getOpenDrawerTimes() {
        if (sunmiPrinterService == null) {
            return 0;
        }
        try {
            return sunmiPrinterService.getOpenDrawerTimes();
        } catch (RemoteException e) {
            handleRemoteException(e);
        }
        return 0;
    }

    /**
     * Transaction printing:
     * enter->print->exit(get result) or
     * enter->first print->commit(get result)->twice print->commit(get result)->exit(don't care
     * result)
     */
    public void enterPrinterBuffer(boolean clean) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.enterPrinterBuffer(clean);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void exitPrinterBuffer(boolean commit) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.exitPrinterBuffer(commit);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void tax(byte[] data, InnerTaxCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.tax(data, callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void clearBuffer() {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.clearBuffer();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void commitPrinterBufferWithCallback(InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.commitPrinterBufferWithCallback(callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void exitPrinterBufferWithCallback(boolean commit, InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.exitPrinterBufferWithCallback(commit, callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public int updatePrinterState() {
        if (sunmiPrinterService == null) {
            return -1;
        }
        try {
            return sunmiPrinterService.updatePrinterState();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void sendLCDCommand(int flag) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.sendLCDCommand(flag);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void sendLCDString(String string, InnerLcdCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.sendLCDString(string, callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void sendLCDBitmap(Bitmap bitmap, InnerLcdCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.sendLCDBitmap(bitmap, callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public int getPrinterMode() {
        if (sunmiPrinterService == null) {
            return 0;
        }
        try {
            return sunmiPrinterService.getPrinterMode();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getPrinterBBMDistance() {
        if (sunmiPrinterService == null) {
            return 0;
        }
        try {
            return sunmiPrinterService.getPrinterBBMDistance();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void printBitmapCustom(Bitmap bitmap, int type, InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.printBitmapCustom(bitmap, type, callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public int getForcedDouble() {
        if (sunmiPrinterService == null) {
            return 0;
        }
        try {
            return sunmiPrinterService.getForcedDouble();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean isForcedAntiWhite() {
        if (sunmiPrinterService == null) {
            return false;
        }
        try {
            return sunmiPrinterService.isForcedAntiWhite();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isForcedBold() {
        if (sunmiPrinterService == null) {
            return false;
        }
        try {
            return sunmiPrinterService.isForcedBold();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isForcedUnderline() {
        if (sunmiPrinterService == null) {
            return false;
        }
        try {
            return sunmiPrinterService.isForcedUnderline();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getForcedRowHeight() {
        if (sunmiPrinterService == null) {
            return 0;
        }
        try {
            return sunmiPrinterService.getForcedRowHeight();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getFontName() {
        if (sunmiPrinterService == null) {
            return 0;
        }
        try {
            return sunmiPrinterService.getFontName();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void sendLCDDoubleString(String topText, String bottomText, InnerLcdCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.sendLCDDoubleString(topText, bottomText, callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get paper specifications
     */
    public String getPrinterPaper() {
        if (sunmiPrinterService == null) {
            return "";
        }
        try {
            return sunmiPrinterService.getPrinterPaper() == 1 ? "58mm" : "80mm";
        } catch (RemoteException e) {
            handleRemoteException(e);
            return "";
        }
    }

    public boolean getDrawerStatus() {
        if (sunmiPrinterService == null) {
            return false;
        }
        try {
            return sunmiPrinterService.getDrawerStatus();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void sendLCDFillString(String string, int size, boolean fill, InnerLcdCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.sendLCDFillString(string, size, fill, callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void sendLCDMultiString(String[] text, int[] align, InnerLcdCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.sendLCDMultiString(text, align, callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public int getPrinterDensity() {
        if (sunmiPrinterService == null) {
            return 0;
        }
        try {
            return sunmiPrinterService.getPrinterDensity();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void print2DCode(String data, int symbology, int modulesize, int errorlevel, InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.print2DCode(data, symbology, modulesize, errorlevel, callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void autoOutPaper(InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.autoOutPaper(callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setPrinterStyle(int key, int value) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.setPrinterStyle(key, value);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void labelLocate() {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.labelLocate();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void labelOutput() {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.labelOutput();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void initPrinterCallBack(int anInt, InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.initPrinterCallBack(anInt, callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void initPrinter(int anInt) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.initPrinter(anInt);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public int getPrinterStatusCallBack(int anInt, InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return -1;
        }
        try {
            return sunmiPrinterService.getPrinterStatusCallBack(anInt, callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int getPrinterStatus(int anInt) {
        if (sunmiPrinterService == null) {
            return -1;
        }
        try {
            return sunmiPrinterService.getPrinterStatus(anInt);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void printAndLineFeedCallBack(InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.printAndLineFeedCallBack(callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void printAndLineFeed() {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.printAndLineFeed();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void printAndFeedPaperCallBack(int anInt, InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.printAndFeedPaperCallBack(anInt, callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void printAndFeedPaper(int anInt) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.printAndFeedPaper(anInt);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void partialCutCallBack(InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.partialCutCallBack(callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void partialCut() {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.partialCut();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setAlignmentCallBack(int anInt, InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.setAlignmentCallBack(anInt, callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setAlignment(int anInt) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.setAlignment(anInt);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setTextSizeCallBack(int anInt, InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.setTextSizeCallBack(anInt, callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setTextSize(int anInt) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.setTextSize(anInt);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setTextTypefaceCallBack(int anInt, InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.setTextTypefaceCallBack(anInt, callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setTextTypeface(int anInt) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.setTextTypeface(anInt);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setTextStyleCallBack(int anInt, InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.setTextStyleCallBack(anInt, callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setTextStyle(int anInt) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.setTextStyle(anInt);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setTextLineSpacingCallBack(int anInt, InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.setTextLineSpacingCallBack(anInt, callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setTextLineSpacing(int anInt) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.setTextLineSpacing(anInt);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setTextWidthCallBack(int anInt, InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.setTextWidthCallBack(anInt, callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setTextWidth(int anInt) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.setTextWidth(anInt);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void printTextCallBack(String text, InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.printTextCallBack(text, callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void printText(String text) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.printText(text);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void printTextWithAliCallBack(String text, int anInt, InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.printTextWithAliCallBack(text, anInt, callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void printTextWithAli(String text, int anInt) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.printTextWithAli(text, anInt);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    //////////////////////////////////
    public void printColumnsTextCallBack(String[] colsTextArr, int[] colsWidthArr, int[] colsAlign, int[] size, InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.printColumnsTextCallBack(colsTextArr, colsWidthArr, colsAlign,size, callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void printColumnsText(String[] colsTextArr, int[] colsWidthArr, int[] colsAlign, int[] size) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.printColumnsText(colsTextArr, colsWidthArr, colsAlign,size);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    //////////////////////////////
    public void setBarCodeWidthCallBack(int anInt, InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.setBarCodeWidthCallBack(anInt, callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setBarCodeWidth(int anInt) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.setBarCodeWidth(anInt);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    public void setBarCodeContentPrintPosCallBack(int anInt, InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.setBarCodeContentPrintPosCallBack(anInt, callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setBarCodeContentPrintPos(int anInt) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.setBarCodeContentPrintPos(anInt);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    public void printBarCodeCallBack(int anInt,String data, InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.printBarCodeCallBack(anInt,data, callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void printBarCode(int anInt,String data) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.printBarCode(anInt,data);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    public void printBarCodeWithAlignCallBack(int anInt,String data, int alignments, InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.printBarCodeWithAlignCallBack(anInt,data,alignments, callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void printBarCodeWithAlign(int anInt,String data, int alignments) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.printBarCodeWithAlign(anInt,data,alignments);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    public void setQrCodeSizeCallBack(int anInt, InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.setQrCodeSizeCallBack(anInt, callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setQrCodeSize(int anInt) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.setQrCodeSize(anInt);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    public void setQrCodeErrorCorrectionLevCallBack(int anInt, InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.setQrCodeErrorCorrectionLevCallBack(anInt, callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setQrCodeErrorCorrectionLev(int anInt) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.setQrCodeErrorCorrectionLev(anInt);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    public void setLeftMarginCallBack(int anInt, InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.setLeftMarginCallBack(anInt, callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setLeftMargin(int anInt) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.setLeftMargin(anInt);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    public void printQrCodeCallBack(String anInt, InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.printQrCodeCallBack(anInt, callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void printQrCode(String anInt) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.printQrCode(anInt);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    public void printQrCodeWithAlignCallBack(String anInt, int alignments, InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.printQrCodeWithAlignCallBack(anInt, alignments, callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void printQrCodeWithAlign(String anInt, int alignments) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.printQrCodeWithAlign(anInt, alignments);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    public void setPageFormatCallBack(int anInt, InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.setPageFormatCallBack(anInt, callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setPageFormat(int anInt) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.setPageFormat(anInt);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    public void printSingleBitmapCallBack(Bitmap bitmap, InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.printSingleBitmapCallBack(bitmap, callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void printSingleBitmap(Bitmap bitmap) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.printSingleBitmap(bitmap);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    public void printSingleBitmapWithAlignCallBack(Bitmap bitmap, int alignments, InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.printSingleBitmapWithAlignCallBack(bitmap,alignments, callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void printSingleBitmapWithAlign(Bitmap bitmap, int alignments) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.printSingleBitmapWithAlign(bitmap,alignments);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    public void printMultiBitmapCallBack(List<Bitmap> bitmap, InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.printMultiBitmapCallBack(bitmap, callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void printMultiBitmap(List<Bitmap> bitmap) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.printMultiBitmap(bitmap);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    public void printMultiBitmapWithAlignCallBack(List<Bitmap> bitmap, int alignments, InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.printMultiBitmapWithAlignCallBack(bitmap,alignments, callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void printMultiBitmapWithAlign(List<Bitmap> bitmap, int alignments) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.printMultiBitmapWithAlign(bitmap,alignments);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    public void resetDeviceCallBack(InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.resetDeviceCallBack(callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void resetDevice() {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.resetDevice();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    public void fullCutCallBack(InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.fullCutCallBack(callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void fullCut() {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.fullCut();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    public void sendRAWDataCallBack(int[] ints,InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.sendRAWDataCallBack(ints,callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void sendRAWData(int[] ints) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.sendRAWData(ints);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    public void sendRAWDataStringCallBack(String ints,InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.sendRAWDataStringCallBack(ints,callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void sendRAWDataString(String ints) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.sendRAWDataString(ints);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    public void sendRAWDataByteCallBack(byte[] ints,InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.sendRAWDataByteCallBack(ints,callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void sendRAWDataByte(byte[] ints) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.sendRAWDataByte(ints);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    public void setUnderlineCallBack(boolean ints,InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.setUnderlineCallBack(ints,callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setUnderline(boolean ints) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.setUnderline(ints);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    public void sethaveBoldCallBack(boolean ints,InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.sethaveBoldCallBack(ints,callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void sethaveBold(boolean ints) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.sethaveBold(ints);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    public void setHaveLineHeightCallBack(float ints,InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.setHaveLineHeightCallBack(ints,callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setHaveLineHeight(float ints) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.setHaveLineHeight(ints);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    public void printerByteWithByteCallBack(byte[] ints,InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.printerByteWithByteCallBack(ints,callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void printerByteWithByte(byte[] ints) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.printerByteWithByte(ints);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    public void printerByteCallBack(InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.printerByteCallBack(callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void printerByte() {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.printerByte();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    public void setDoubleQRSizeCallBack(float ints,InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.setDoubleQRSizeCallBack((int) ints,callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setDoubleQRSize(float ints) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.setDoubleQRSize((int) ints);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    public void setDoubleQR1MarginLeftCallBack(float ints,InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.setDoubleQR1MarginLeftCallBack((int) ints,callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setDoubleQR1MarginLeft(float ints) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.setDoubleQR1MarginLeft((int) ints);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    public void setDoubleQR2MarginLeftCallBack(float ints,InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.setDoubleQR2MarginLeftCallBack((int) ints,callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setDoubleQR2MarginLeft(float ints) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.setDoubleQR2MarginLeft((int) ints);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    public void setDoubleQR1LevelCallBack(int ints,InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.setDoubleQR1LevelCallBack(ints,callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setDoubleQR1Level(int ints) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.setDoubleQR1Level(ints);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    public void setDoubleQR2LevelCallBack(int ints,InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.setDoubleQR2LevelCallBack(ints,callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setDoubleQR2Level(int ints) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.setDoubleQR2Level(ints);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    public void setDoubleQR1VersionCallBack(int ints,InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.setDoubleQR1VersionCallBack(ints,callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setDoubleQR1Version(int ints) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.setDoubleQR1Version(ints);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    public void setDoubleQR2VersionCallBack(int ints,InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.setDoubleQR2VersionCallBack(ints,callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setDoubleQR2Version(int ints) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.setDoubleQR2Version(ints);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    public void printDoubleQRCallBack(String qr1,String qr2,InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.printDoubleQRCallBack(qr1,qr2,callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void printDoubleQR(String qr1,String qr2) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.printDoubleQR(qr1,qr2);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    public void initParamsCallBack(InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.initParamsCallBack(callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void initParams() {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.initParams();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    public void initParamsIsClearCallBack(boolean isClearCache,InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.initParamsIsClearCallBack(isClearCache,callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void initParamsIsClear(boolean isClearCache) {
        if (sunmiPrinterService == null) {
            return;
        }
        try {
            sunmiPrinterService.initParamsIsClear(isClearCache);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    public int getForcedRowHeightCallBack(InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return 0;
        }
        try {
            return sunmiPrinterService.getForcedRowHeightCallBack(callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean isForcedBoldCallBack(InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return false;
        }
        try {
            return sunmiPrinterService.isForcedBoldCallBack(callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean isForcedUnderlineCallBack(InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return false;
        }
        try {
            return sunmiPrinterService.isForcedUnderlineCallBack(callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }
    public int getPrinterDensityCallBack(InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return 100;
        }
        try {
            return sunmiPrinterService.getPrinterDensityCallBack(callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return 100;
    }
    public void initBluePrinterCallBack(int anInt, BluetoothDevice device, InnerResultCallback callback) {
        if (sunmiPrinterService == null) {
            return ;
        }
        try {
            sunmiPrinterService.initBluePrinterCallBack(anInt,device,callback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    public void initBluePrinter(int anInt, BluetoothDevice device) {
        if (sunmiPrinterService == null) {
            return ;
        }
        try {
            sunmiPrinterService.initBluePrinter(anInt,device);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
