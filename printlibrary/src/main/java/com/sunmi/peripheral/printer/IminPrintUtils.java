package com.sunmi.peripheral.printer;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

public class IminPrintUtils {
    public static int NoPrinter = 0x00000000;
    public static int CheckPrinter = 0x00000001;
    public static int FoundPrinter = 0x00000002;
    public static int LostPrinter = 0x00000003;
    boolean initPrinter = false;
    /**
     * Printer means checking the printer connection status
     */
    public int Printer = CheckPrinter;
    Context mContext;
    /**
     * SunmiPrinterService for API
     */
    private SunmiPrinterService printerService;

    private static IminPrintUtils helper = new IminPrintUtils();

    private IminPrintUtils() {
    }

    public static IminPrintUtils getInstance() {
        return helper;
    }

    private InnerPrinterCallback innerPrinterCallback = new InnerPrinterCallback() {
        @Override
        protected void onConnected(SunmiPrinterService service) {
            printerService = service;
            checkPrinterService(service);
            initPrinter = false;
            Log.i("test_1_000000", "iiii   onConnected");
        }

        @Override
        protected void onDisconnected() {
            printerService = null;
            Printer = LostPrinter;
            initPrinter = false;
            Log.i("test_1_000000", "iiii   onDisconnected");
        }
    };

    /**
     *
     * init  print service
     */
    public void initPrinterService(Context context) {
        mContext = context;
        try {
            boolean ret = InnerPrinterManager.getInstance().bindService(context,
                    innerPrinterCallback);
            if (!ret) {
                Printer = NoPrinter;
            }
        } catch (InnerPrinterException e) {
            e.printStackTrace();
        }
    }

    /**
     * deInit  print service
     */
    public void deInitPrinterService(Context context) {
        mContext = context;
        try {
            if (printerService != null) {
                InnerPrinterManager.getInstance().unBindService(context, innerPrinterCallback);
                printerService = null;
                Printer = LostPrinter;
            }
        } catch (InnerPrinterException e) {
            e.printStackTrace();
        }
    }

    /**
     * Check the printer connection,
     * like some devices do not have a printer but need to be connected to the cash drawer through a print service
     */
    private void checkPrinterService(SunmiPrinterService service) {
        boolean ret = false;
        try {
            ret = InnerPrinterManager.getInstance().hasPrinter(service);
            int status = printerService.updatePrinterState();

            Log.i("test_1_000000", "iiii   checkPrinterService " + ret + "  status==  " + status + "  ttt " + printerService.getFirmwareStatus());

        } catch (Exception e) {
            e.printStackTrace();
        }
        Printer = ret ? FoundPrinter : NoPrinter;
    }

    /**
     * Some conditions can cause interface calls to fail
     * For example: the version is too low、device does not support
     * You can see {@link ExceptionConst}
     * So you have to handle these exceptions
     */
    private void handleException(Exception e) {
        //TODO process when get one exception
    }

    /**
     * send esc cmd
     */
    public void sendRawData(byte[] data) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.sendRAWData(data, null);
        } catch (Exception e) {
            handleException(e);
        }
    }

    /**
     * Printer cuts paper and throws exception on machines without a cutter
     */
    public void cutpaper() {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.cutPaper(null);
        } catch (Exception e) {
            handleException(e);
        }
    }

    /**
     * Initialize the printer
     * All style settings will be restored to default
     */
    public void initPrinter() {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.printerInit(null);
            initPrinter = true;
        } catch (Exception e) {
            handleException(e);
        }
    }

    /**
     * paper feed three lines
     * Not disabled when line spacing is set to 0
     */
    public void print3Line() {
        if (printerService == null || initPrinter == false) {
            return;
        }

        try {
//            for (int i = 0;i<100000;i++){
//
//            }
            printerService.lineWrap(3, null);
        } catch (Exception e) {
            handleException(e);
        }
    }

    /**
     * Get printer serial number
     */
    public String getPrinterSerialNo() {
        if (printerService == null || initPrinter == false) {
            
            return "";
        }
        try {
            return printerService.getPrinterSerialNo();
        } catch (Exception e) {
            handleException(e);
            return "";
        }
    }

    /**
     * Get device model
     */
    public String getDeviceModel() {
        if (printerService == null || initPrinter == false) {
            
            return "";
        }
        try {
            return printerService.getPrinterModal();
        } catch (Exception e) {
            handleException(e);
            return "";
        }
    }

    /**
     * Get firmware version
     */
    public String getPrinterVersion() {
        if (printerService == null|| initPrinter == false) {
            
            return "";
        }
        try {
            return printerService.getPrinterVersion();
        } catch (Exception e) {
            handleException(e);
            return "";
        }
    }

    /**
     * Get paper specifications
     */
    public void getPrinterFactory(InnerResultCallback callbcak) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.getPrinterFactory(callbcak);
        } catch (Exception e) {
            handleException(e);
        }
    }

    /**
     * Get printing distance since boot
     * Get printing distance through interface callback since 1.0.8(printerlibrary)
     */
    public void getPrinterDistance(InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.getPrintedLength(callback);
        } catch (Exception e) {
            handleException(e);
        }
    }

    /**
     * Set printer alignment
     */
    public void setAlign(int align) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.setAlignment(align, null);
        } catch (Exception e) {
            handleException(e);
        }
    }

    /**
     * Due to the distance between the paper hatch and the print head,
     * the paper needs to be fed out automatically
     * But if the Api does not support it, it will be replaced by printing three lines
     */
    public void feedPaper() {
        if (printerService == null || initPrinter == false) {
            return;
        }

        try {
            printerService.autoOutPaper(null);
        } catch (Exception e) {
            print3Line();
        }
    }

    /**
     * print text
     * setPrinterStyle Api require V4.2.22 or later, So use esc cmd instead when not supported
     * More settings reference documentation {@link WoyouConsts}
     */
    public void printText(String content, float size, boolean isBold, boolean isUnderLine) {
        if (printerService == null || initPrinter == false) {
            return;
        }

        try {
            try {
                printerService.setPrinterStyle(WoyouConsts.ENABLE_BOLD, isBold ?
                        WoyouConsts.ENABLE : WoyouConsts.DISABLE);
            } catch (Exception e) {
                if (isBold) {
                    printerService.sendRAWData(ESCUtil.boldOn(), null);
                } else {
                    printerService.sendRAWData(ESCUtil.boldOff(), null);
                }
            }
            try {
                printerService.setPrinterStyle(WoyouConsts.ENABLE_UNDERLINE, isUnderLine ?
                        WoyouConsts.ENABLE : WoyouConsts.DISABLE);
            } catch (Exception e) {
                if (isUnderLine) {
                    printerService.sendRAWData(ESCUtil.underlineWithOneDotWidthOn(), null);
                } else {
                    printerService.sendRAWData(ESCUtil.underlineOff(), null);
                }
            }
            printerService.printTextWithFont(content, null, size, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * print Bar Code
     */
    public void printBarCode(String data, int symbology, int height, int width, int textposition, InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }

        try {
            printerService.printBarCode(data, symbology, height, width, textposition, callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * print Qr Code
     */
    public void printQRCode(String data, int modulesize, int errorlevel, InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }

        try {
            printerService.printQRCode(data, modulesize, errorlevel, callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Print a row of a table
     */
    public void printColumnsString(String[] txts, int[] width, int[] align, InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.printColumnsString(txts, width, align, callback);
        } catch (Exception e) {
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
        if (printerService == null || initPrinter == false) {
            return;
        }

        try {
            if (orientation == 0) {
                printerService.printBitmap(bitmap, null);
                printerService.printText("横向排列\n", null);
                printerService.printBitmap(bitmap, null);
                printerService.printText("横向排列\n", null);
            } else {
                printerService.printBitmap(bitmap, null);
                printerService.printText("\n纵向排列\n", null);
                printerService.printBitmap(bitmap, null);
                printerService.printText("\n纵向排列\n", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets whether the current printer is in black mark mode
     */
    public boolean isBlackLabelMode() {
        if (printerService == null) {
            
            return false;
        }
        try {
            return printerService.getPrinterMode() == 1;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Gets whether the current printer is in label-printing mode
     */
    public boolean isLabelMode() {
        if (printerService == null) {
            
            return false;
        }
        try {
            return printerService.getPrinterMode() == 2;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * Open cash box
     * This method can be used on  devices with a cash drawer interface
     * If there is no cash box (such as V1、P1) or the call fails, an exception will be thrown
     * <p>
     * Reference to https://docs..com/general-function-modules/external-device-debug/cash-box-driver/}
     */
    public void openCashBox(InnerResultCallback callback) {
        if (printerService == null) {
            return;
        }

        try {
            printerService.openDrawer(callback);
        } catch (Exception e) {
            handleException(e);
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
        if (printerService == null) {
            return;
        }

        try {
            Log.d("test_1_dddd", "iiiii-----printTable  controlLcd==>" + flag);
            printerService.sendLCDCommand(flag);
        } catch (Exception e) {
            handleException(e);
        }
    }

    /**
     * Display text ,font size is 16 and format is fill
     * sendLCDFillString(txt, size, fill, callback)
     * Since the screen pixel height is 40, the font should not exceed 40
     */
    public void sendTextToLcd() {
        if (printerService == null) {
            return;
        }

        try {
            printerService.sendLCDFillString("", 16, true, new InnerLcdCallback() {
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

                @Override
                public void callback(int status) throws RemoteException {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Display two lines and one empty line in the middle
     */
    public void sendTextsToLcd() {
        if (printerService == null ) {
            return;
        }

        try {
            String[] texts = {"", "null", ""};
            int[] align = {2, 1, 2};
            printerService.sendLCDMultiString(texts, align, new InnerLcdCallback() {
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
        } catch (Exception e) {
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
        if (printerService == null || initPrinter == false) {
            return;
        }
        String result = "Interface is too low to implement interface";
        try {
            int res = printerService.updatePrinterState();
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
        } catch (Exception e) {
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
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.labelLocate();
            printLabelContent();
            printerService.labelOutput();
        } catch (Exception e) {
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
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            for (int i = 0; i < num; i++) {
                printerService.labelLocate();
                printLabelContent();
            }
            printerService.labelOutput();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Custom label ticket content
     * In the example, not all labels can be applied. In actual use, please pay attention to adapting the size of the label. You can adjust the font size and content position.
     * 自定义的标签小票内容
     * 例子中并不能适用所有标签纸，实际使用时注意要自适配标签纸大小，可通过调节字体大小，内容位置等方式
     */
    private void printLabelContent() throws Exception {
        printerService.setPrinterStyle(WoyouConsts.ENABLE_BOLD, WoyouConsts.ENABLE);
        printerService.lineWrap(1, null);
        printerService.setAlignment(0, null);
        printerService.printText("商品         豆浆\n", null);
        printerService.printText("到期时间         12-13  14时\n", null);
        printerService.printBarCode("{C1234567890123456", 8, 90, 2, 2, null);
        printerService.lineWrap(1, null);
    }

    public void updateFirmware() {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.updateFirmware();
        } catch (Exception e) {
            handleException(e);
        }
    }

    public int getFirmwareStatus() {
        if (printerService == null || initPrinter == false) {
            return 0;
        }
        try {
            return printerService.getFirmwareStatus();
        } catch (Exception e) {
            handleException(e);
        }
        return 0;
    }

    public String getServiceVersion() {
        if (printerService == null || initPrinter == false) {
            return "";
        }
        try {
            return printerService.getServiceVersion();
        } catch (Exception e) {
            handleException(e);
        }
        return "";
    }

    public void printerInit(InnerResultCallback callback) {
        if (printerService == null ) {
            return;
        }
        try {
            printerService.printerInit(callback);
            initPrinter = true;
        } catch (Exception e) {
            handleException(e);
        }
    }

    public void printerSelfChecking(InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.printerSelfChecking(callback);
        } catch (Exception e) {
            handleException(e);
        }
    }

    public String getPrinterModal() {
        if (printerService == null || initPrinter == false) {
            return "";
        }
        try {
            return printerService.getPrinterModal();
        } catch (Exception e) {
            handleException(e);
        }
        return "";
    }

    public void getPrintedLength(InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.getPrintedLength(callback);
        } catch (Exception e) {
            handleException(e);
        }
        return;
    }

    public void lineWrap(int n, InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }

        try {
            printerService.lineWrap(n, callback);
        } catch (Exception e) {
            handleException(e);
        }
    }

    public void setFontSize(float fontsize, InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }

        try {
            printerService.setFontSize(fontsize, callback);
        } catch (Exception e) {
            handleException(e);
        }
    }

    public void printText(String text, InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }

        try {
            printerService.printText(text, callback);
        } catch (Exception e) {
            handleException(e);
        }
    }

    public void printTextWithFont(String text, String typeface, float fontsize, InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.printTextWithFont(text, typeface, fontsize, callback);
        } catch (Exception e) {
            handleException(e);
        }
    }

    public void printColumnsText(String[] colsTextArr, int[] colsWidthArr, int[] colsAlign, InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.printColumnsText(colsTextArr, colsWidthArr, colsAlign, callback);
        } catch (Exception e) {
            handleException(e);
        }
    }

    public void printBitmap(Bitmap bitmap, InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.printBitmap(bitmap, callback);
        } catch (Exception e) {
            handleException(e);
        }
    }

    public void printOriginalText(String text, InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.printOriginalText(text, callback);
        } catch (Exception e) {
            handleException(e);
        }
    }

    public void commitPrint(TransBean[] transbean, InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.commitPrint(transbean, callback);
        } catch (Exception e) {
            handleException(e);
        }
    }

    public void commitPrinterBuffer() {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.commitPrinterBuffer();
        } catch (Exception e) {
            handleException(e);
        }
    }

    public void cutPaper(InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.cutPaper(callback);
        } catch (Exception e) {
            handleException(e);
        }
    }

    public int getCutPaperTimes() {
        if (printerService == null || initPrinter == false) {
            return 0;
        }
        try {
            return printerService.getCutPaperTimes();
        } catch (Exception e) {
            handleException(e);
        }
        return 0;
    }

    public int getOpenDrawerTimes() {
        if (printerService == null || initPrinter == false) {
            return 0;
        }
        try {
            return printerService.getOpenDrawerTimes();
        } catch (Exception e) {
            handleException(e);
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
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.enterPrinterBuffer(clean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exitPrinterBuffer(boolean commit) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.exitPrinterBuffer(commit);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tax(byte[] data, InnerTaxCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.tax(data, callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearBuffer() {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.clearBuffer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void commitPrinterBufferWithCallback(InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.commitPrinterBufferWithCallback(callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exitPrinterBufferWithCallback(boolean commit, InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.exitPrinterBufferWithCallback(commit, callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int updatePrinterState() {
        if (printerService == null || initPrinter == false) {
            return -1;
        }
        try {
            return printerService.updatePrinterState();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void sendLCDCommand(int flag) {
        if (printerService == null ) {
            return;
        }
        try {
            printerService.sendLCDCommand(flag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendLCDString(String string, InnerLcdCallback callback) {
        if (printerService == null ) {
            return;
        }
        try {
            printerService.sendLCDString(string, callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendLCDBitmap(Bitmap bitmap, InnerLcdCallback callback) {
        if (printerService == null) {
            return;
        }
        try {
            printerService.sendLCDBitmap(bitmap, callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getPrinterMode() {
        if (printerService == null || initPrinter == false) {
            return 0;
        }
        try {
            return printerService.getPrinterMode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getPrinterBBMDistance() {
        if (printerService == null || initPrinter == false) {
            return 0;
        }
        try {
            return printerService.getPrinterBBMDistance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void printBitmapCustom(Bitmap bitmap, int type, InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.printBitmapCustom(bitmap, type, callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getForcedDouble() {
        if (printerService == null) {
            return 0;
        }
        try {
            return printerService.getForcedDouble();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean isForcedAntiWhite() {
        if (printerService == null) {
            return false;
        }
        try {
            return printerService.isForcedAntiWhite();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isForcedBold() {
        if (printerService == null) {
            return false;
        }
        try {
            return printerService.isForcedBold();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isForcedUnderline() {
        if (printerService == null) {
            return false;
        }
        try {
            return printerService.isForcedUnderline();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getForcedRowHeight() {
        if (printerService == null) {
            return 0;
        }
        try {
            return printerService.getForcedRowHeight();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getFontName() {
        if (printerService == null) {
            return 0;
        }
        try {
            return printerService.getFontName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void sendLCDDoubleString(String topText, String bottomText, InnerLcdCallback callback) {
        if (printerService == null ) {
            return;
        }
        try {
            printerService.sendLCDDoubleString(topText, bottomText, callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get paper specifications
     */
    public String getPrinterPaper() {
        if (printerService == null || initPrinter == false) {
            return "";
        }
        try {
            return printerService.getPrinterPaper() == 1 ? "58mm" : "80mm";
        } catch (Exception e) {
            handleException(e);
            return "";
        }
    }

    public boolean getDrawerStatus() {
        if (printerService == null || initPrinter == false) {
            return false;
        }
        try {
            return printerService.getDrawerStatus();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void sendLCDFillString(String string, int size, boolean fill, InnerLcdCallback callback) {
        if (printerService == null ) {
            return;
        }
        try {
            printerService.sendLCDFillString(string, size, fill, callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendLCDMultiString(String[] text, int[] align, InnerLcdCallback callback) {
        if (printerService == null ) {
            return;
        }
        try {
            printerService.sendLCDMultiString(text, align, callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getPrinterDensity() {
        if (printerService == null || initPrinter == false) {
            return 0;
        }
        try {
            return printerService.getPrinterDensity();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void print2DCode(String data, int symbology, int modulesize, int errorlevel, InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.print2DCode(data, symbology, modulesize, errorlevel, callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void autoOutPaper(InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.autoOutPaper(callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setPrinterStyle(int key, int value) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.setPrinterStyle(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void labelLocate() {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.labelLocate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void labelOutput() {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.labelOutput();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initPrinterCallBack(int anInt, InnerResultCallback callback) {
        if (printerService == null ) {
            return;
        }
        try {
            printerService.initPrinterCallBack(anInt, callback);
            initPrinter = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initPrinter(int anInt) {
        if (printerService == null) {
            return;
        }
        try {
            printerService.initPrinter(anInt);
            initPrinter = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 0：打印机正常   -1：打印机未连接或未上电   3：打印头打开    7：纸尽    8：纸将尽
    // 99：其它错误    5：打印头过热     101: 电量不足   102：打印升级中    103.未找到打印机
    public int getPrinterStatusCallBack(int anInt, InnerResultCallback callback) {
        try {
            if (printerService == null){
                if (callback != null){
                    callback.callback(103);
                }
                return 103;
            }
            if (printerService == null || initPrinter == false) {
                if (callback != null){
                    callback.callback(-1);
                }
                return -1;
            }

            if (mContext != null){
                if (Utils.getElect(mContext) <5){
                    if (callback != null){
                        callback.callback(101);
                    }
                    return 101;
                }
            }
            return printerService.getPrinterStatusCallBack(anInt, callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (callback != null){
            try {
                callback.callback(-1);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public int getPrinterStatus(int anInt) {
        if (printerService == null){
            return 103;
        }
        if (printerService == null || initPrinter == false) {
            return -1;
        }
        if (mContext != null){
            if (Utils.getElect(mContext) <5){
                return 101;
            }
        }
        try {
            return printerService.getPrinterStatus(anInt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void printAndLineFeedCallBack(InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.printAndLineFeedCallBack(callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printAndLineFeed() {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.printAndLineFeed();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printAndFeedPaperCallBack(int anInt, InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.printAndFeedPaperCallBack(anInt, callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printAndFeedPaper(int anInt) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.printAndFeedPaper(anInt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void partialCutCallBack(InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.partialCutCallBack(callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void partialCut() {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.partialCut();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setAlignmentCallBack(int anInt, InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.setAlignmentCallBack(anInt, callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setAlignment(int anInt) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.setAlignment(anInt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setTextSizeCallBack(int anInt, InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.setTextSizeCallBack(anInt, callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setTextSize(int anInt) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.setTextSize(anInt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setTextTypefaceCallBack(int anInt, InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.setTextTypefaceCallBack(anInt, callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setTextTypeface(int anInt) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.setTextTypeface(anInt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setTextStyleCallBack(int anInt, InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.setTextStyleCallBack(anInt, callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setTextStyle(int anInt) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.setTextStyle(anInt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setTextLineSpacingCallBack(int anInt, InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.setTextLineSpacingCallBack(anInt, callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setTextLineSpacing(int anInt) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.setTextLineSpacing(anInt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setTextWidthCallBack(int anInt, InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.setTextWidthCallBack(anInt, callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setTextWidth(int anInt) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.setTextWidth(anInt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printTextCallBack(String text, InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.printTextCallBack(text, callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printText(String text) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.printText(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printTextWithAliCallBack(String text, int anInt, InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.printTextWithAliCallBack(text, anInt, callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printTextWithAli(String text, int anInt) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.printTextWithAli(text, anInt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //////////////////////////////////
    public void printColumnsTextCallBack(String[] colsTextArr, int[] colsWidthArr, int[] colsAlign, int[] size, InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.printColumnsTextCallBack(colsTextArr, colsWidthArr, colsAlign,size, callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printColumnsText(String[] colsTextArr, int[] colsWidthArr, int[] colsAlign, int[] size) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.printColumnsText(colsTextArr, colsWidthArr, colsAlign,size);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //////////////////////////////
    public void setBarCodeWidthCallBack(int anInt, InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.setBarCodeWidthCallBack(anInt, callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setBarCodeWidth(int anInt) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.setBarCodeWidth(anInt);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setBarCodeHeightCallBack(int anInt, InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.setBarCodeHeightCallBack(anInt, callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setBarCodeHeight(int anInt) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.setBarCodeHeight(anInt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setBarCodeContentPrintPosCallBack(int anInt, InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.setBarCodeContentPrintPosCallBack(anInt, callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setBarCodeContentPrintPos(int anInt) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.setBarCodeContentPrintPos(anInt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void printBarCodeCallBack(int anInt,String data, InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.printBarCodeCallBack(anInt,data, callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printBarCode(int anInt,String data) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.printBarCode(anInt,data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void printBarCodeWithAlignCallBack(int anInt,String data, int alignments, InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.printBarCodeWithAlignCallBack(anInt,data,alignments, callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printBarCodeWithAlign(int anInt,String data, int alignments) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.printBarCodeWithAlign(anInt,data,alignments);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setQrCodeSizeCallBack(int anInt, InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.setQrCodeSizeCallBack(anInt, callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setQrCodeSize(int anInt) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.setQrCodeSize(anInt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setQrCodeErrorCorrectionLevCallBack(int anInt, InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.setQrCodeErrorCorrectionLevCallBack(anInt, callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setQrCodeErrorCorrectionLev(int anInt) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.setQrCodeErrorCorrectionLev(anInt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setLeftMarginCallBack(int anInt, InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.setLeftMarginCallBack(anInt, callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setLeftMargin(int anInt) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.setLeftMargin(anInt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void printQrCodeCallBack(String anInt, InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.printQrCodeCallBack(anInt, callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printQrCode(String anInt) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.printQrCode(anInt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void printQrCodeWithAlignCallBack(String anInt, int alignments, InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.printQrCodeWithAlignCallBack(anInt, alignments, callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printQrCodeWithAlign(String anInt, int alignments) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.printQrCodeWithAlign(anInt, alignments);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setPageFormatCallBack(int anInt, InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.setPageFormatCallBack(anInt, callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setPageFormat(int anInt) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.setPageFormat(anInt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void printSingleBitmapCallBack(Bitmap bitmap, InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.printSingleBitmapCallBack(bitmap, callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printSingleBitmap(Bitmap bitmap) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.printSingleBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void printSingleBitmapWithAlignCallBack(Bitmap bitmap, int alignments, InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.printSingleBitmapWithAlignCallBack(bitmap,alignments, callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printSingleBitmapWithAlign(Bitmap bitmap, int alignments) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.printSingleBitmapWithAlign(bitmap,alignments);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void printMultiBitmapCallBack(List<Bitmap> bitmap, InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.printMultiBitmapCallBack(bitmap, callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printMultiBitmap(List<Bitmap> bitmap) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.printMultiBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void printMultiBitmapWithAlignCallBack(List<Bitmap> bitmap, int alignments, InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.printMultiBitmapWithAlignCallBack(bitmap,alignments, callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printMultiBitmapWithAlign(List<Bitmap> bitmap, int alignments) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.printMultiBitmapWithAlign(bitmap,alignments);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void resetDeviceCallBack(InnerResultCallback callback) {
        if (printerService == null ) {
            return;
        }
        try {
            printerService.resetDeviceCallBack(callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void resetDevice() {
        if (printerService == null ) {
            return;
        }
        try {
            printerService.resetDevice();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void fullCutCallBack(InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.fullCutCallBack(callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fullCut() {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.fullCut();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void sendRAWDataCallBack(int[] ints,InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.sendRAWDataCallBack(ints,callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendRAWData(int[] ints) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.sendRAWData(ints);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void sendRAWDataStringCallBack(String ints,InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.sendRAWDataStringCallBack(ints,callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendRAWDataString(String ints) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.sendRAWDataString(ints);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void sendRAWDataByteCallBack(byte[] ints,InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.sendRAWDataByteCallBack(ints,callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendRAWDataByte(byte[] ints) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.sendRAWDataByte(ints);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setUnderlineCallBack(boolean ints,InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.setUnderlineCallBack(ints,callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setUnderline(boolean ints) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.setUnderline(ints);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void sethaveBoldCallBack(boolean ints,InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.sethaveBoldCallBack(ints,callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sethaveBold(boolean ints) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.sethaveBold(ints);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setHaveLineHeightCallBack(float ints,InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.setHaveLineHeightCallBack(ints,callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setHaveLineHeight(float ints) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.setHaveLineHeight(ints);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void printerByteWithByteCallBack(byte[] ints,InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.printerByteWithByteCallBack(ints,callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printerByteWithByte(byte[] ints) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.printerByteWithByte(ints);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void printerByteCallBack(InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.printerByteCallBack(callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printerByte() {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.printerByte();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setDoubleQRSizeCallBack(float ints,InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.setDoubleQRSizeCallBack((int) ints,callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDoubleQRSize(float ints) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.setDoubleQRSize((int) ints);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setDoubleQR1MarginLeftCallBack(float ints,InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.setDoubleQR1MarginLeftCallBack((int) ints,callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDoubleQR1MarginLeft(float ints) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.setDoubleQR1MarginLeft((int) ints);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setDoubleQR2MarginLeftCallBack(float ints,InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.setDoubleQR2MarginLeftCallBack((int) ints,callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDoubleQR2MarginLeft(float ints) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.setDoubleQR2MarginLeft((int) ints);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setDoubleQR1LevelCallBack(int ints,InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.setDoubleQR1LevelCallBack(ints,callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDoubleQR1Level(int ints) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.setDoubleQR1Level(ints);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setDoubleQR2LevelCallBack(int ints,InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.setDoubleQR2LevelCallBack(ints,callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDoubleQR2Level(int ints) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.setDoubleQR2Level(ints);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setDoubleQR1VersionCallBack(int ints,InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.setDoubleQR1VersionCallBack(ints,callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDoubleQR1Version(int ints) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.setDoubleQR1Version(ints);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setDoubleQR2VersionCallBack(int ints,InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.setDoubleQR2VersionCallBack(ints,callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDoubleQR2Version(int ints) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.setDoubleQR2Version(ints);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void printDoubleQRCallBack(String qr1,String qr2,InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.printDoubleQRCallBack(qr1,qr2,callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void printDoubleQR(String qr1,String qr2) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.printDoubleQR(qr1,qr2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void initParamsCallBack(InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.initParamsCallBack(callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initParams() {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.initParams();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void initParamsIsClearCallBack(boolean isClearCache,InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.initParamsIsClearCallBack(isClearCache,callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initParamsIsClear(boolean isClearCache) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.initParamsIsClear(isClearCache);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int getForcedRowHeightCallBack(InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return 0;
        }
        try {
            return printerService.getForcedRowHeightCallBack(callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean isForcedBoldCallBack(InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return false;
        }
        try {
            return printerService.isForcedBoldCallBack(callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean isForcedUnderlineCallBack(InnerResultCallback callback) {
        if (printerService == null|| initPrinter == false) {
            return false;
        }
        try {
            return printerService.isForcedUnderlineCallBack(callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public int getPrinterDensityCallBack(InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return 100;
        }
        try {
            return printerService.getPrinterDensityCallBack(callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 100;
    }
    public void initBluePrinterCallBack(int anInt, BluetoothDevice device, InnerResultCallback callback) {
        if (printerService == null ) {
            return;
        }
        try {
            printerService.initBluePrinterCallBack(anInt,device,callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void initBluePrinter(int anInt, BluetoothDevice device) {
        if (printerService == null ) {
            return;
        }
        try {
            printerService.initBluePrinter(anInt,device);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void printBMPBitmapCallBack(Bitmap bitmap, InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.printBMPBitmapCallBack(bitmap,callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void printBMPBitmap(Bitmap bitmap) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.printBMPBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void printNvBitmapCallBack(int anInt,InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.printNvBitmapCallBack(anInt,callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void printNvBitmap(int anInt) {
        if (printerService == null || initPrinter == false) {
            return;
        }
        try {
            printerService.printNvBitmap(anInt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean setDownloadNvBmpCallBack(String loadPath,InnerResultCallback callback) {
        if (printerService == null || initPrinter == false) {
            return false;
        }
        try {
            return printerService.setDownloadNvBmpCallBack(loadPath,callback);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean setDownloadNvBmp(String loadPath) {
        if (printerService == null || initPrinter == false) {
            return false;
        }
        try {
           return printerService.setDownloadNvBmp(loadPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
