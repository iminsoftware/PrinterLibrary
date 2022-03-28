package com.sunmi.peripheral.printer;

import android.bluetooth.BluetoothDevice;
import android.graphics.Bitmap;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.SystemProperties;

import java.util.List;
import java.util.Locale;

public interface SunmiPrinterService extends IInterface {
    void updateFirmware() throws RemoteException;

    int getFirmwareStatus() throws RemoteException;

    String getServiceVersion() throws RemoteException;

    void printerInit(InnerResultCallback callback) throws RemoteException;

    void printerSelfChecking(InnerResultCallback callback) throws RemoteException;

    String getPrinterSerialNo() throws RemoteException;

    String getPrinterVersion() throws RemoteException;

    String getPrinterModal() throws RemoteException;

    void getPrintedLength(InnerResultCallback callback) throws RemoteException;

    void lineWrap(int n, InnerResultCallback callback) throws RemoteException;

    void sendRAWData(byte[] data, InnerResultCallback callback) throws RemoteException;

    void setAlignment(int alignment, InnerResultCallback callback) throws RemoteException;

    void setFontName(String typeface, InnerResultCallback callback) throws RemoteException;

    void setFontSize(float fontsize, InnerResultCallback callback) throws RemoteException;

    void printText(String text, InnerResultCallback callback) throws RemoteException;

    void printTextWithFont(String text, String typeface, float fontsize, InnerResultCallback callback) throws RemoteException;

    void printColumnsText(String[] colsTextArr, int[] colsWidthArr, int[] colsAlign, InnerResultCallback callback) throws RemoteException;

    void printBitmap(Bitmap bitmap, InnerResultCallback callback) throws RemoteException;

    void printBarCode(String data, int symbology, int height, int width, int textposition, InnerResultCallback callback) throws RemoteException;

    void printQRCode(String data, int modulesize, int errorlevel, InnerResultCallback callback) throws RemoteException;

    void printOriginalText(String text, InnerResultCallback callback) throws RemoteException;

    void commitPrint(TransBean[] transbean, InnerResultCallback callback) throws RemoteException;

    void commitPrinterBuffer() throws RemoteException;

    void cutPaper(InnerResultCallback callback) throws RemoteException;

    int getCutPaperTimes() throws RemoteException;

    void openDrawer(InnerResultCallback callback) throws RemoteException;

    int getOpenDrawerTimes() throws RemoteException;

    void enterPrinterBuffer(boolean clean) throws RemoteException;

    void exitPrinterBuffer(boolean commit) throws RemoteException;

    void tax(byte[] data, InnerTaxCallback callback) throws RemoteException;

    void getPrinterFactory(InnerResultCallback callback) throws RemoteException;

    void clearBuffer() throws RemoteException;

    void commitPrinterBufferWithCallback(InnerResultCallback callback) throws RemoteException;

    void exitPrinterBufferWithCallback(boolean commit, InnerResultCallback callback) throws RemoteException;

    void printColumnsString(String[] colsTextArr, int[] colsWidthArr, int[] colsAlign, InnerResultCallback callback) throws RemoteException;

    int updatePrinterState() throws RemoteException;

    void sendLCDCommand(int flag) throws RemoteException;

    void sendLCDString(String string, InnerLcdCallback callback) throws RemoteException;

    void sendLCDBitmap(Bitmap bitmap, InnerLcdCallback callback) throws RemoteException;

    int getPrinterMode() throws RemoteException;

    int getPrinterBBMDistance() throws RemoteException;

    void printBitmapCustom(Bitmap bitmap, int type, InnerResultCallback callback) throws RemoteException;

    int getForcedDouble() throws RemoteException;

    boolean isForcedAntiWhite() throws RemoteException;

    boolean isForcedBold() throws RemoteException;

    boolean isForcedUnderline() throws RemoteException;

    int getForcedRowHeight() throws RemoteException;

    int getFontName() throws RemoteException;

    void sendLCDDoubleString(String topText, String bottomText, InnerLcdCallback callback) throws RemoteException;

    int getPrinterPaper() throws RemoteException;

    boolean getDrawerStatus() throws RemoteException;

    void sendLCDFillString(String string, int size, boolean fill, InnerLcdCallback callback) throws RemoteException;

    void sendLCDMultiString(String[] text, int[] align, InnerLcdCallback callback) throws RemoteException;

    int getPrinterDensity() throws RemoteException;

    void print2DCode(String data, int symbology, int modulesize, int errorlevel, InnerResultCallback callback) throws RemoteException;

    void autoOutPaper(InnerResultCallback callback) throws RemoteException;

    void setPrinterStyle(int key, int value) throws RemoteException;

    void labelLocate() throws RemoteException;

    void labelOutput() throws RemoteException;

    /**
     * 1 初始化打印机
     */
    public void initPrinterCallBack(int anInt,InnerResultCallback callback) throws RemoteException;
    public void initPrinter(int anInt) throws RemoteException;
    /**
     * 2 获取打印机状态
     * 返回：
     */
    public int getPrinterStatusCallBack(int anInt, InnerResultCallback callback) throws RemoteException;
    /**
     *  获取打印机状态
     *
     */
    public int getPrinterStatus(int anInt) throws RemoteException;
    /**
     * 3 走纸
     * 返回：
     */
    public void printAndLineFeed() throws RemoteException;
    /**
     * 走纸
     * 返回：
     */
    public void printAndLineFeedCallBack(InnerResultCallback callback) throws RemoteException;
    /**
     * 4 走纸 指定高度
     * 返回：
     */
    public void printAndFeedPaperCallBack(int anInt, InnerResultCallback callback) throws RemoteException;
    /**
     * 走纸 指定高度
     * 返回：
     */
    public void printAndFeedPaper(int anInt) throws RemoteException;
    /**
     * 5 切纸
     * 返回：
     */
    public void partialCut() throws RemoteException;
    /**
     * 切纸
     * 返回：
     */
    public void partialCutCallBack(InnerResultCallback callback) throws RemoteException;
    /**
     * 6 设置文本对齐方式
     * 返回：
     */
    public void setAlignmentCallBack(int anInt, InnerResultCallback callback) throws RemoteException;
    /**
     * 设置文本对齐方式
     * 返回：
     */
    public void setAlignment(int anInt) throws RemoteException;
    /**
     * 7 设置文本字体大小
     * 返回：
     */
    public void setTextSizeCallBack(int anInt, InnerResultCallback callback) throws RemoteException;
    /**
     * 设置文本字体大小
     * 返回：
     */
    public void setTextSize(int anInt) throws RemoteException;
    /**
     * 8 设置字体
     * 返回：
     */
    public void setTextTypefaceCallBack(int anInt, InnerResultCallback callback) throws RemoteException;
    /**
     * 设置字体
     * 返回：
     */
    public void setTextTypeface(int anInt) throws RemoteException;
    /**
     * 9 设置字体样式
     * 返回：
     */
    public void setTextStyleCallBack(int anInt, InnerResultCallback callback) throws RemoteException;
    /**
     * 设置字体样式
     * 返回：
     */
    public void setTextStyle(int anInt) throws RemoteException;
    /**
     * 10 设置行间距
     * 返回：
     */
    public void setTextLineSpacingCallBack(int anInt, InnerResultCallback callback) throws RemoteException;
    /**
     * 设置行间距
     * 返回：
     */
    public void setTextLineSpacing(int anInt) throws RemoteException;
    /**
     * 11 设置打印宽度
     * 返回：
     */
    public void setTextWidthCallBack(int anInt, InnerResultCallback callback) throws RemoteException;
    /**
     * 设置打印宽度
     * 返回：
     */
    public void setTextWidth(int anInt) throws RemoteException;
    /**
     * 12 打印文本
     * 返回：
     */
    public void printTextCallBack(String text, InnerResultCallback callback) throws RemoteException;
    /**
     * 打印文本
     * 返回：
     */
    public void printText(String text) throws RemoteException;
    /**
     * 13 打印文本,对齐方式
     * 返回：
     */
    public void printTextWithAliCallBack(String text, int anInt, InnerResultCallback callback) throws RemoteException;
    /**
     * 打印文本 ,对齐方式
     * 返回：
     */
    public void printTextWithAli(String text, int anInt) throws RemoteException;
    /**
     * 14 打印表格的一行，可以指定列宽、对齐方式
     * colsTextArr：   各列文本字符串数组
     * colsWidthArr：  各列宽度数组(以英文字符计算, 每个中文字符占两个英文字符, 每个宽度大于0)
     * colsAlign：     各列对齐方式(0居左, 1居中, 2居右)
     * 备注: 三个参数的数组长度应该一致, 如果colsText[i]的宽度大于colsWidth[i], 则文本换行
     */
    public void printColumnsTextCallBack(String[] colsTextArr, int[] colsWidthArr, int[] colsAlign,int[] size, InnerResultCallback callback) throws RemoteException;
    /**
     * 打印表格的一行，可以指定列宽、对齐方式
     * colsTextArr：   各列文本字符串数组
     * colsWidthArr：  各列宽度数组(以英文字符计算, 每个中文字符占两个英文字符, 每个宽度大于0)
     * colsAlign：     各列对齐方式(0居左, 1居中, 2居右)
     * 备注: 三个参数的数组长度应该一致, 如果colsText[i]的宽度大于colsWidth[i], 则文本换行
     */
    public void printColumnsText(String[] colsTextArr, int[] colsWidthArr, int[] colsAlign,int[] size) throws RemoteException;
    /**
     * 15 设置条形码宽度
     * 返回：
     */
    public void setBarCodeWidthCallBack(int anInt, InnerResultCallback callback) throws RemoteException;
    /**
     * 设置条形码宽度
     * 返回：
     */
    public void setBarCodeWidth(int anInt) throws RemoteException;
    /**
     * 16 设置条形码的高度
     * 返回：
     */
    public void setBarCodeHeightCallBack(int anInt, InnerResultCallback callback) throws RemoteException;
    /**
     * 设置条形码的高度
     * 返回：
     */
    public void setBarCodeHeight(int anInt) throws RemoteException;
    /**
     * 17 打印条形码时，请选择HRI字符的打印位置
     * 返回：
     */
    public void setBarCodeContentPrintPosCallBack(int anInt, InnerResultCallback callback) throws RemoteException;
    /**
     * 打印条形码时，请选择HRI字符的打印位置
     * 返回：
     */
    public void setBarCodeContentPrintPos(int anInt) throws RemoteException;
    /**
     * 18 打印条形码
     * 返回：
     */
    public void printBarCodeCallBack(int anInt, String data, InnerResultCallback callback) throws RemoteException;
    /**
     * 打印条形码
     * 返回：
     */
    public void printBarCode(int anInt, String data) throws RemoteException;
    /**
     * 19 打印条形码,对齐方式
     * 返回：
     */
    public void printBarCodeWithAlignCallBack(int anInt, String data, int alignments, InnerResultCallback callback) throws RemoteException;
    /**
     * 打印条形码,对齐方式
     * 返回：
     */
    public void printBarCodeWithAlign(int anInt, String data, int alignments) throws RemoteException;
    /**
     * 20 设置二维码的大小
     * 返回：
     */
    public void setQrCodeSizeCallBack(int anInt, InnerResultCallback callback) throws RemoteException;
    /**
     * 设置二维码的大小
     * 返回：
     */
    public void setQrCodeSize(int anInt) throws RemoteException;
    /**
     * 21 设置二维码错误更正
     * 返回：
     */
    public void setQrCodeErrorCorrectionLevCallBack(int anInt, InnerResultCallback callback) throws RemoteException;
    /**
     * 设置二维码错误更正
     * 返回：
     */
    public void setQrCodeErrorCorrectionLev(int anInt) throws RemoteException;
    /**
     22 设置条形码和二维码的左边距
     * 返回：
     */
    public void setLeftMarginCallBack(int anInt, InnerResultCallback callback) throws RemoteException;
    /**
     * 设置条形码和二维码的左边距
     * 返回：
     */
    public void setLeftMargin(int anInt) throws RemoteException;
    /**
     23 打印机二维码
     * 返回：
     */
    public void printQrCodeCallBack(String data, InnerResultCallback callback) throws RemoteException;
    /**
     *打印机二维码
     * 返回：
     */
    public void printQrCode(String data) throws RemoteException;
    /**
     24 打印机二维码,对齐方式
     * 返回：
     */
    public void printQrCodeWithAlignCallBack(String data, int alignments, InnerResultCallback callback) throws RemoteException;
    /**
     *打印机二维码,对齐方式
     * 返回：
     */
    public void printQrCodeWithAlign(String data, int alignments) throws RemoteException;
    /**
     25 设定纸张规格
     * 返回：
     */
    public void setPageFormatCallBack(int anInt, InnerResultCallback callback) throws RemoteException;
    /**
     * 设定纸张规格
     * 返回：
     */
    public void setPageFormat(int anInt) throws RemoteException;
    /**
     26 打印机图片
     * 返回：
     */
    public void printSingleBitmapCallBack(Bitmap bitmap, InnerResultCallback callback) throws RemoteException;
    /**
     *打印机图片
     * 返回：
     */
    public void printSingleBitmap(Bitmap bitmap) throws RemoteException;
    /**
     27 打印机图片,对齐方式
     * 返回：
     */
    public void printSingleBitmapWithAlignCallBack(Bitmap bitmap, int alignments, InnerResultCallback callback) throws RemoteException;
    /**
     *打印机图片
     * 返回：
     */
    public void printSingleBitmapWithAlign(Bitmap bitmap, int alignments) throws RemoteException;
    /**
     28 打印多个位图
     * 返回：
     */
    public void printMultiBitmapCallBack(List<Bitmap> bitmaps, InnerResultCallback callback) throws RemoteException;
    /**
     *打印多个位图
     * 返回：
     */
    public void printMultiBitmap(List<Bitmap> bitmap) throws RemoteException;
    /**
     29 打印多个位图
     * 返回：
     */
    public void printMultiBitmapWithAlignCallBack(List<Bitmap> bitmaps, int alignments, InnerResultCallback callback) throws RemoteException;
    /**
     *打印多个位图
     * 返回：
     */
    public void printMultiBitmapWithAlign(List<Bitmap> bitmap, int alignments) throws RemoteException;
    /**
     30 重置连接
     * 返回：
     */
    public void resetDeviceCallBack(InnerResultCallback callback) throws RemoteException;
    /**
     *重置连接
     * 返回：
     */
    public void resetDevice() throws RemoteException;
    /**
     31 全切 切纸
     * 返回：
     */
    public void fullCutCallBack(InnerResultCallback callback) throws RemoteException;
    /**
     *全切 切纸
     * 返回：
     */
    public void fullCut() throws RemoteException;
    /**
     32  发送指令
     * 返回：
     */
    public void sendRAWDataCallBack(int[] ints, InnerResultCallback callback) throws RemoteException;
    /**
     *全切 切纸
     * 返回：
     */
    public void sendRAWData(int[] ints) throws RemoteException;
    /**
     33  发送指令
     * 返回：
     */
    public void sendRAWDataStringCallBack(String hex, InnerResultCallback callback) throws RemoteException;
    /**
     *全切 切纸
     * 返回：
     */
    public void sendRAWDataString(String hex) throws RemoteException;
    /**
     34  发送指令
     * 返回：
     */
    public void sendRAWDataByteCallBack(byte[] bytes, InnerResultCallback callback) throws RemoteException;
    /**
     *全切 切纸
     * 返回：
     */
    public void sendRAWDataByte(byte[] bytes) throws RemoteException;
    /**
     35  设置打印文字是否 下划线
     * 返回：
     */
    public void setUnderlineCallBack(boolean haveUnderline, InnerResultCallback callback) throws RemoteException;
    /**
     *全切 切纸
     * 返回：
     */
    public void setUnderline(boolean haveUnderline) throws RemoteException;
    /**
     35  设置打印文字是否 加粗
     * 返回：
     */
    public void sethaveBoldCallBack(boolean haveBold, InnerResultCallback callback) throws RemoteException;
    /**
     *全切 切纸
     * 返回：
     */
    public void sethaveBold(boolean haveBold) throws RemoteException;
    /**
     36  设置打印文字 行高
     * 返回：
     */
    public void setHaveLineHeightCallBack(float lineHeightRatio, InnerResultCallback callback) throws RemoteException;
    /**
     *全切 切纸
     * 返回：
     */
    public void setHaveLineHeight(float lineHeightRatio) throws RemoteException;
    /**
     37  发送指令
     * 返回：
     */
    public void printerByteWithByteCallBack(byte[] bytes, InnerResultCallback callback) throws RemoteException;
    /**
     *全切 切纸
     * 返回：
     */
    public void printerByteWithByte(byte[] bytes) throws RemoteException;
    /**
     37  发送指令
     * 返回：
     */
    public void printerByteCallBack(InnerResultCallback callback) throws RemoteException;
    /**
     *全切 切纸
     * 返回：
     */
    public void printerByte() throws RemoteException;
    /**
     38  双qr大小
     * 返回：
     */
    public void setDoubleQRSizeCallBack(int size, InnerResultCallback callback) throws RemoteException;
    /**
     * 双qr大小
     * 返回：
     */
    public void setDoubleQRSize(int size) throws RemoteException;
    /**
     39  双qr 左边边距
     * 返回：
     */
    public void setDoubleQR1MarginLeftCallBack(int left, InnerResultCallback callback) throws RemoteException;
    /**
     *双qr 左边边距
     * 返回：
     */
    public void setDoubleQR1MarginLeft(int left) throws RemoteException;
    /**
     40  双qr 左边边距
     * 返回：
     */
    public void setDoubleQR2MarginLeftCallBack(int left, InnerResultCallback callback) throws RemoteException;
    /**
     *双qr 左边边距
     * 返回：
     */
    public void setDoubleQR2MarginLeft(int left) throws RemoteException;
    /**
     41  双qr 误差
     * 返回：
     */
    public void setDoubleQR1LevelCallBack(int level, InnerResultCallback callback) throws RemoteException;
    /**
     * 双qr 误差
     * 返回：
     */
    public void setDoubleQR1Level(int level) throws RemoteException;
    /**
     42  双qr 误差
     * 返回：
     */
    public void setDoubleQR2LevelCallBack(int level, InnerResultCallback callback) throws RemoteException;
    /**
     * 双qr 误差
     * 返回：
     */
    public void setDoubleQR2Level(int level) throws RemoteException;
    /**
     43  双qr 版本
     * 返回：
     */
    public void setDoubleQR1VersionCallBack(int version, InnerResultCallback callback) throws RemoteException;
    /**
     * 双qr 版本
     * 返回：
     */
    public void setDoubleQR1Version(int version) throws RemoteException;
    /**
     44  双qr 版本
     * 返回：
     */
    public void setDoubleQR2VersionCallBack(int version, InnerResultCallback callback) throws RemoteException;
    /**
     * 双qr 版本
     * 返回：
     */
    public void setDoubleQR2Version(int version) throws RemoteException;
    /**
     45 打印双QR
     * 返回：
     */
    public void printDoubleQRCallBack(String qr1,String qr2, InnerResultCallback callback) throws RemoteException;
    /**
     * 打印双QR
     * 返回：
     */
    public void printDoubleQR(String qr1,String qr2) throws RemoteException;
    /**
     46 初始化参数
     * 返回：
     */
    public void initParamsCallBack(InnerResultCallback callback) throws RemoteException;
    /**
     * 初始化参数
     * 返回：
     */
    public void initParams() throws RemoteException;
    /**
     47 初始化参数
     * 返回：
     */
    public void initParamsIsClearCallBack(boolean isClearCache, InnerResultCallback callback) throws RemoteException;
    /**
     * 初始化参数
     * 返回：
     */
    public void initParamsIsClear(boolean isClearCache) throws RemoteException;
    /**
     * 48 获取全局⾏⾼设定值
     * 返回：
     */
    public int getForcedRowHeightCallBack(InnerResultCallback callback) throws RemoteException;

    /**
     * 49 获取全局字体加粗样式使能
     * 返回：
     */
    public boolean isForcedBoldCallBack(InnerResultCallback callback) throws RemoteException;

    /**
     * 50 获取全局字体下划线样式使能
     * 返回：
     */
    public boolean isForcedUnderlineCallBack(InnerResultCallback callback) throws RemoteException;

    /**
     * 51 获取打印浓度
     * 返回：
     */
    public int getPrinterDensityCallBack(InnerResultCallback callback) throws RemoteException;
    /**
     *  52蓝牙打印初始化
     *
     */
    public void initBluePrinterCallBack(int anInt, BluetoothDevice device, InnerResultCallback callback) throws RemoteException;
    /**
     * 53 蓝牙打印初始化
     *
     */
    public void initBluePrinter(int anInt, BluetoothDevice device) throws RemoteException;

    public void printBMPBitmap(Bitmap bitmap) throws android.os.RemoteException;
    public void printBMPBitmapCallBack(Bitmap bitmap,ICallback callback) throws android.os.RemoteException;

    public void printNvBitmap(int position) throws android.os.RemoteException;
    public void printNvBitmapCallBack(int position,ICallback callback) throws android.os.RemoteException;

    public boolean setDownloadNvBmp(String loadPath) throws android.os.RemoteException;
    public boolean setDownloadNvBmpCallBack(String loadPath,ICallback callback) throws android.os.RemoteException;

    public abstract static class Stub extends Binder implements SunmiPrinterService {
        private static final String DESCRIPTOR = "woyou.aidlservice.jiuiv5.IWoyouService";

        static final int[][] TRANSCTION_DATASHEET = new int[][]{{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9527, 9527, 9527, 9527, 9527, 9527, 9527, 5, 9527, 0, -9, -9, -9, -9, -9, -9, -9, 9527, -8, 9527, 9527, 9527, -14, -14, -13, -13, -12, -12}, {-1, 3, 3, 3, 9527, 9527, 8, 8, 1, 1, -10, -10, -10, -10, -3, -3, -3, -10, -10, 9527, -2, 9527, 9527, 9527, 9527, 9527, 9527, -10, -4, -10, -10, -10, -10, -10, -9, -9, 9527, 9527}, {0, 0, 0, 9527, 9527, 9527, 9527, 9527, -5, 7, 9527, 9527, 9527, 9527, 9527, 9527, 9527, 9527, 9527, 0, -15, -15, -15, -15, -15, -15, -15, 9527, -14, 9527, 9527, 9527, -20, -20, -19, -19, 9527, 9527}, {-1, 3, 3, 3, 9527, 9527, 5, 5, 1, 1, -10, -10, -10, -10, 9527, 9527, 9527, -10, -10, 9527, -6, 9527, 9527, 9527, 9527, 9527, 9527, 9527, -13, -13, 9527, 9527, -15, -15, 9527, -16, 9527, 9527}};
        static final int[][] TRANSCTION_DATASHEET_DEFAULT = new int[][]{{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};

        static final int TRANSACTION_STOP = 9527;
        static final int TRANSACTION_updateFirmware = 1;
        static final int TRANSACTION_getFirmwareStatus = 2;
        static final int TRANSACTION_getServiceVersion = 3;
        static final int TRANSACTION_printerInit = 4;
        static final int TRANSACTION_printerSelfChecking = 5;
        static final int TRANSACTION_getPrinterSerialNo = 6;
        static final int TRANSACTION_getPrinterVersion = 7;
        static final int TRANSACTION_getPrinterModal = 8;
        static final int TRANSACTION_getPrintedLength = 9;
        static final int TRANSACTION_lineWrap = 10;
        static final int TRANSACTION_sendRAWData = 11;
        static final int TRANSACTION_setAlignment = 12;
        static final int TRANSACTION_setFontName = 13;
        static final int TRANSACTION_setFontSize = 14;
        static final int TRANSACTION_printText = 15;
        static final int TRANSACTION_printTextWithFont = 16;
        static final int TRANSACTION_printColumnsText = 17;
        static final int TRANSACTION_printBitmap = 18;
        static final int TRANSACTION_printBarCode = 19;
        static final int TRANSACTION_printQRCode = 20;
        static final int TRANSACTION_printOriginalText = 21;
        static final int TRANSACTION_commitPrint = 22;
        static final int TRANSACTION_commitPrinterBuffer = 23;
        static final int TRANSACTION_enterPrinterBuffer = 24;
        static final int TRANSACTION_exitPrinterBuffer = 25;
        static final int TRANSACTION_tax = 26;
        static final int TRANSACTION_getPrinterFactory = 27;
        static final int TRANSACTION_clearBuffer = 28;
        static final int TRANSACTION_commitPrinterBufferWithCallback = 29;
        static final int TRANSACTION_exitPrinterBufferWithCallback = 30;
        static final int TRANSACTION_printColumnsString = 31;
        static final int TRANSACTION_updatePrinterState = 32;
        static final int TRANSACTION_cutPaper = 33;
        static final int TRANSACTION_getCutPaperTimes = 34;
        static final int TRANSACTION_openDrawer = 35;
        static final int TRANSACTION_getOpenDrawerTimes = 36;
        static final int TRANSACTION_sendLCDCommand = 37;
        static final int TRANSACTION_sendLCDString = 38;
        static final int TRANSACTION_sendLCDBitmap = 39;
        static final int TRANSACTION_getPrinterMode = 40;
        static final int TRANSACTION_getPrinterBBMDistance = 41;
        static final int TRANSACTION_printBitmapCustom = 42;
        static final int TRANSACTION_getForcedDouble = 43;
        static final int TRANSACTION_isForcedAntiWhite = 44;
        static final int TRANSACTION_isForcedBold = 45;
        static final int TRANSACTION_isForcedUnderline = 46;
        static final int TRANSACTION_getForcedRowHeight = 47;
        static final int TRANSACTION_getFontName = 48;
        static final int TRANSACTION_sendLCDDoubleString = 49;
        static final int TRANSACTION_getPrinterPaper = 50;
        static final int TRANSACTION_getDrawerStatus = 51;
        static final int TRANSACTION_sendLCDFillString = 52;
        static final int TRANSACTION_sendLCDMultiString = 53;
        static final int TRANSACTION_getPrinterDensity = 54;
        static final int TRANSACTION_print2DCode = 55;
        static final int TRANSACTION_autoOutPaper = 56;
        static final int TRANSACTION_setPrinterStyle = 57;
        static final int TRANSACTION_labelLocate = 58;
        static final int TRANSACTION_labelOutput = 59;

        static final int TRANSACTION_initPrinterCallBack = (IBinder.FIRST_CALL_TRANSACTION + 59);
        static final int TRANSACTION_initPrinter = (IBinder.FIRST_CALL_TRANSACTION + 60);
        static final int TRANSACTION_getPrinterStatusCallBack = (IBinder.FIRST_CALL_TRANSACTION + 61);
        static final int TRANSACTION_getPrinterStatus = (IBinder.FIRST_CALL_TRANSACTION + 62);
        static final int TRANSACTION_printAndLineFeed = (IBinder.FIRST_CALL_TRANSACTION + 63);
        static final int TRANSACTION_printAndLineFeedCallBack = (IBinder.FIRST_CALL_TRANSACTION + 64);
        static final int TRANSACTION_printAndFeedPaperCallBack = (IBinder.FIRST_CALL_TRANSACTION + 65);
        static final int TRANSACTION_printAndFeedPaper = (IBinder.FIRST_CALL_TRANSACTION + 66);
        static final int TRANSACTION_partialCut = (IBinder.FIRST_CALL_TRANSACTION + 67);
        static final int TRANSACTION_partialCutCallBack = (IBinder.FIRST_CALL_TRANSACTION + 68);
        static final int TRANSACTION_setAlignmentCallBack = (IBinder.FIRST_CALL_TRANSACTION + 69);
        static final int TRANSACTION_setTextSizeCallBack = (IBinder.FIRST_CALL_TRANSACTION + 70);
        static final int TRANSACTION_setTextSize = (IBinder.FIRST_CALL_TRANSACTION + 71);
        static final int TRANSACTION_setTextTypefaceCallBack = (IBinder.FIRST_CALL_TRANSACTION + 72);
        static final int TRANSACTION_setTextTypeface = (IBinder.FIRST_CALL_TRANSACTION + 73);
        static final int TRANSACTION_setTextStyleCallBack = (IBinder.FIRST_CALL_TRANSACTION + 74);
        static final int TRANSACTION_setTextStyle = (IBinder.FIRST_CALL_TRANSACTION + 75);
        static final int TRANSACTION_setTextLineSpacingCallBack = (IBinder.FIRST_CALL_TRANSACTION + 76);
        static final int TRANSACTION_setTextLineSpacing = (IBinder.FIRST_CALL_TRANSACTION + 77);
        static final int TRANSACTION_setTextWidthCallBack = (IBinder.FIRST_CALL_TRANSACTION + 78);
        static final int TRANSACTION_setTextWidth = (IBinder.FIRST_CALL_TRANSACTION + 79);
        static final int TRANSACTION_printTextCallBack = (IBinder.FIRST_CALL_TRANSACTION + 80);
        static final int TRANSACTION_printTextWithAliCallBack = (IBinder.FIRST_CALL_TRANSACTION + 81);
        static final int TRANSACTION_printTextWithAli = (IBinder.FIRST_CALL_TRANSACTION + 82);
        static final int TRANSACTION_printColumnsTextCallBack = (IBinder.FIRST_CALL_TRANSACTION + 83);
        static final int TRANSACTION_setBarCodeWidthCallBack = (IBinder.FIRST_CALL_TRANSACTION + 84);
        static final int TRANSACTION_setBarCodeWidth = (IBinder.FIRST_CALL_TRANSACTION + 85);
        static final int TRANSACTION_setBarCodeHeightCallBack = (IBinder.FIRST_CALL_TRANSACTION + 86);
        static final int TRANSACTION_setBarCodeHeight = (IBinder.FIRST_CALL_TRANSACTION + 87);
        static final int TRANSACTION_setBarCodeContentPrintPosCallBack = (IBinder.FIRST_CALL_TRANSACTION + 88);
        static final int TRANSACTION_setBarCodeContentPrintPos = (IBinder.FIRST_CALL_TRANSACTION + 89);
        static final int TRANSACTION_printBarCodeCallBack = (IBinder.FIRST_CALL_TRANSACTION + 90);
        static final int TRANSACTION_printBarCodeWithAlignCallBack = (IBinder.FIRST_CALL_TRANSACTION + 91);
        static final int TRANSACTION_printBarCodeWithAlign = (IBinder.FIRST_CALL_TRANSACTION + 92);
        static final int TRANSACTION_setQrCodeSizeCallBack = (IBinder.FIRST_CALL_TRANSACTION + 93);
        static final int TRANSACTION_setQrCodeSize = (IBinder.FIRST_CALL_TRANSACTION + 94);
        static final int TRANSACTION_setQrCodeErrorCorrectionLevCallBack = (IBinder.FIRST_CALL_TRANSACTION + 95);
        static final int TRANSACTION_setQrCodeErrorCorrectionLev = (IBinder.FIRST_CALL_TRANSACTION + 96);
        static final int TRANSACTION_setLeftMarginCallBack = (IBinder.FIRST_CALL_TRANSACTION + 97);
        static final int TRANSACTION_setLeftMargin = (IBinder.FIRST_CALL_TRANSACTION + 98);
        static final int TRANSACTION_printQrCodeCallBack = (IBinder.FIRST_CALL_TRANSACTION + 99);
        static final int TRANSACTION_printQrCode = (IBinder.FIRST_CALL_TRANSACTION + 100);
        static final int TRANSACTION_printQrCodeWithAlignCallBack = (IBinder.FIRST_CALL_TRANSACTION + 101);
        static final int TRANSACTION_printQrCodeWithAlign = (IBinder.FIRST_CALL_TRANSACTION + 102);
        static final int TRANSACTION_setPageFormatCallBack = (IBinder.FIRST_CALL_TRANSACTION + 103);
        static final int TRANSACTION_setPageFormat = (IBinder.FIRST_CALL_TRANSACTION + 104);
        static final int TRANSACTION_printSingleBitmapCallBack = (IBinder.FIRST_CALL_TRANSACTION + 105);
        static final int TRANSACTION_printSingleBitmap = (IBinder.FIRST_CALL_TRANSACTION + 106);
        static final int TRANSACTION_printSingleBitmapWithAlignCallBack = (IBinder.FIRST_CALL_TRANSACTION + 107);
        static final int TRANSACTION_printSingleBitmapWithAlign = (IBinder.FIRST_CALL_TRANSACTION + 108);
        static final int TRANSACTION_printMultiBitmapCallBack = (IBinder.FIRST_CALL_TRANSACTION + 109);
        static final int TRANSACTION_printMultiBitmap = (IBinder.FIRST_CALL_TRANSACTION + 110);
        static final int TRANSACTION_printMultiBitmapWithAlignCallBack = (IBinder.FIRST_CALL_TRANSACTION + 111);
        static final int TRANSACTION_printMultiBitmapWithAlign = (IBinder.FIRST_CALL_TRANSACTION + 112);
        static final int TRANSACTION_resetDeviceCallBack = (IBinder.FIRST_CALL_TRANSACTION + 113);
        static final int TRANSACTION_resetDevice = (IBinder.FIRST_CALL_TRANSACTION + 114);
        static final int TRANSACTION_fullCutCallBack = (IBinder.FIRST_CALL_TRANSACTION + 115);
        static final int TRANSACTION_fullCut = (IBinder.FIRST_CALL_TRANSACTION +116);
        static final int TRANSACTION_sendRAWDataCallBack = (IBinder.FIRST_CALL_TRANSACTION + 117);
        static final int TRANSACTION_sendRAWDataStringCallBack = (IBinder.FIRST_CALL_TRANSACTION + 118);
        static final int TRANSACTION_sendRAWDataString = (IBinder.FIRST_CALL_TRANSACTION + 119);
        static final int TRANSACTION_sendRAWDataByteCallBack = (IBinder.FIRST_CALL_TRANSACTION + 120);
        static final int TRANSACTION_sendRAWDataByte = (IBinder.FIRST_CALL_TRANSACTION + 121);
        static final int TRANSACTION_setUnderlineCallBack = (IBinder.FIRST_CALL_TRANSACTION + 122);
        static final int TRANSACTION_setUnderline = (IBinder.FIRST_CALL_TRANSACTION + 123);
        static final int TRANSACTION_sethaveBoldCallBack = (IBinder.FIRST_CALL_TRANSACTION + 124);
        static final int TRANSACTION_sethaveBold = (IBinder.FIRST_CALL_TRANSACTION + 125);
        static final int TRANSACTION_setHaveLineHeightCallBack = (IBinder.FIRST_CALL_TRANSACTION + 126);
        static final int TRANSACTION_setHaveLineHeight = (IBinder.FIRST_CALL_TRANSACTION + 127);
        static final int TRANSACTION_printerByteWithByteCallBack = (IBinder.FIRST_CALL_TRANSACTION + 128);
        static final int TRANSACTION_printerByteWithByte = (IBinder.FIRST_CALL_TRANSACTION + 129);
        static final int TRANSACTION_printerByteCallBack = (IBinder.FIRST_CALL_TRANSACTION + 130);
        static final int TRANSACTION_printerByte = (IBinder.FIRST_CALL_TRANSACTION + 131);
        static final int TRANSACTION_setDoubleQRSizeCallBack = (IBinder.FIRST_CALL_TRANSACTION + 132);
        static final int TRANSACTION_setDoubleQRSize = (IBinder.FIRST_CALL_TRANSACTION + 133);
        static final int TRANSACTION_setDoubleQR1MarginLeftCallBack = (IBinder.FIRST_CALL_TRANSACTION + 134);
        static final int TRANSACTION_setDoubleQR1MarginLeft = (IBinder.FIRST_CALL_TRANSACTION + 135);
        static final int TRANSACTION_setDoubleQR2MarginLeftCallBack = (IBinder.FIRST_CALL_TRANSACTION + 136);
        static final int TRANSACTION_setDoubleQR2MarginLeft = (IBinder.FIRST_CALL_TRANSACTION + 137);
        static final int TRANSACTION_setDoubleQR1LevelCallBack = (IBinder.FIRST_CALL_TRANSACTION + 138);
        static final int TRANSACTION_setDoubleQR1Level = (IBinder.FIRST_CALL_TRANSACTION + 139);
        static final int TRANSACTION_setDoubleQR2LevelCallBack = (IBinder.FIRST_CALL_TRANSACTION + 140);
        static final int TRANSACTION_setDoubleQR2Level = (IBinder.FIRST_CALL_TRANSACTION + 141);
        static final int TRANSACTION_setDoubleQR1VersionCallBack = (IBinder.FIRST_CALL_TRANSACTION + 142);
        static final int TRANSACTION_setDoubleQR1Version = (IBinder.FIRST_CALL_TRANSACTION + 143);
        static final int TRANSACTION_setDoubleQR2VersionCallBack = (IBinder.FIRST_CALL_TRANSACTION + 144);
        static final int TRANSACTION_setDoubleQR2Version = (IBinder.FIRST_CALL_TRANSACTION + 145);
        static final int TRANSACTION_printDoubleQRCallBack = (IBinder.FIRST_CALL_TRANSACTION + 146);
        static final int TRANSACTION_printDoubleQR = (IBinder.FIRST_CALL_TRANSACTION + 147);
        static final int TRANSACTION_initParamsCallBack = (IBinder.FIRST_CALL_TRANSACTION + 148);
        static final int TRANSACTION_initParams = (IBinder.FIRST_CALL_TRANSACTION + 149);
        static final int TRANSACTION_initParamsIsClearCallBack = (IBinder.FIRST_CALL_TRANSACTION + 150);
        static final int TRANSACTION_initParamsIsClear = (IBinder.FIRST_CALL_TRANSACTION + 151);
        static final int TRANSACTION_getForcedRowHeightCallBack = (IBinder.FIRST_CALL_TRANSACTION + 152);
        static final int TRANSACTION_isForcedBoldCallBack = (IBinder.FIRST_CALL_TRANSACTION + 153);
        static final int TRANSACTION_isForcedUnderlineCallBack = (IBinder.FIRST_CALL_TRANSACTION + 154);
        static final int TRANSACTION_getPrinterDensityCallBack = (IBinder.FIRST_CALL_TRANSACTION + 155);
        static final int TRANSACTION_initBluePrinterCallBack = (IBinder.FIRST_CALL_TRANSACTION + 156);
        static final int TRANSACTION_initBluePrinter = (IBinder.FIRST_CALL_TRANSACTION + 157);
        static final int TRANSACTION_printColumnsTextWithSize = (IBinder.FIRST_CALL_TRANSACTION + 158);
        static final int TRANSACTION_printBMPBitmap = (android.os.IBinder.FIRST_CALL_TRANSACTION + 159);
        static final int TRANSACTION_printBMPBitmapCallBack = (android.os.IBinder.FIRST_CALL_TRANSACTION + 160);
        static final int TRANSACTION_printNvBitmap = (android.os.IBinder.FIRST_CALL_TRANSACTION + 161);
        static final int TRANSACTION_printNvBitmapCallBack = (android.os.IBinder.FIRST_CALL_TRANSACTION + 162);
        static final int TRANSACTION_setDownloadNvBmp = (android.os.IBinder.FIRST_CALL_TRANSACTION + 163);
        static final int TRANSACTION_setDownloadNvBmpCallBack = (android.os.IBinder.FIRST_CALL_TRANSACTION + 164);

        public Stub() {
            this.attachInterface(this, DESCRIPTOR);
        }

        public static SunmiPrinterService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            } else {
                IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
                return (SunmiPrinterService) (iin != null && iin instanceof SunmiPrinterService ? (SunmiPrinterService) iin : new Proxy(obj));
            }
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
//            int _arg0;
//            int _arg1;
//            int _arg2;
//            int _arg3;
//            InnerResultCallback _arg0;
//            String _arg0;
//            String[] _arg0;
//            int[] _arg1;
//            boolean _result;
//            String _arg1;
//            InnerLcdCallback _arg2;
//            InnerLcdCallback _arg1;
//            int[] _arg2;
//            InnerResultCallback _arg3;
//            InnerResultCallback _arg1;
//            Bitmap _arg0;
//            byte[] _arg0;
            switch (code) {
                case TRANSACTION_updateFirmware:
                    data.enforceInterface(DESCRIPTOR);
                    this.updateFirmware();
                    reply.writeNoException();
                    return true;
                case TRANSACTION_getFirmwareStatus: {

                    data.enforceInterface(DESCRIPTOR);
                    int _arg0 = this.getFirmwareStatus();
                    reply.writeNoException();
                    reply.writeInt(_arg0);
                    return true;
                }
                case TRANSACTION_getServiceVersion:{
                    data.enforceInterface(DESCRIPTOR);
                    String _arg0 = this.getServiceVersion();
                    reply.writeNoException();
                    reply.writeString(_arg0);
                    return true;
                }

                case TRANSACTION_printerInit:
                {
                    data.enforceInterface(DESCRIPTOR);
                    InnerResultCallback _arg0 = (InnerResultCallback) ICallback.Stub.asInterface(data.readStrongBinder());
                    this.printerInit(_arg0);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_printerSelfChecking:
                {
                    data.enforceInterface(DESCRIPTOR);
                    InnerResultCallback _arg0 = (InnerResultCallback) ICallback.Stub.asInterface(data.readStrongBinder());
                    this.printerSelfChecking(_arg0);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_getPrinterSerialNo:
                {
                    data.enforceInterface(DESCRIPTOR);
                    String _arg0 = this.getPrinterSerialNo();
                    reply.writeNoException();
                    reply.writeString(_arg0);
                    return true;
                }
                case TRANSACTION_getPrinterVersion:
                {
                    data.enforceInterface(DESCRIPTOR);
                    String _arg0 = this.getPrinterVersion();
                    reply.writeNoException();
                    reply.writeString(_arg0);
                    return true;
                }
                case TRANSACTION_getPrinterModal:
                {
                    data.enforceInterface(DESCRIPTOR);
                    String _arg0 = this.getPrinterModal();
                    reply.writeNoException();
                    reply.writeString(_arg0);
                    return true;
                }
                case TRANSACTION_getPrintedLength:
                {
                    data.enforceInterface(DESCRIPTOR);
                    InnerResultCallback _arg0 = (InnerResultCallback) ICallback.Stub.asInterface(data.readStrongBinder());
                    this.getPrintedLength(_arg0);
                    int _result = 0;
                    reply.writeNoException();
                    reply.writeInt(_result);
                }

                    return true;
                case TRANSACTION_lineWrap:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0 = data.readInt();
                    InnerResultCallback _arg1 = (InnerResultCallback) ICallback.Stub.asInterface(data.readStrongBinder());
                    this.lineWrap(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_sendRAWData:
                {
                    data.enforceInterface(DESCRIPTOR);
                    byte[] _arg0 = data.createByteArray();
                    InnerResultCallback _arg1 = (InnerResultCallback) ICallback.Stub.asInterface(data.readStrongBinder());
                    this.sendRAWData(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_setAlignment:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0 = data.readInt();
                    InnerResultCallback _arg1 = (InnerResultCallback) ICallback.Stub.asInterface(data.readStrongBinder());
                    this.setAlignment(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                }

                case TRANSACTION_setFontName:{
                    data.enforceInterface(DESCRIPTOR);
                    String _arg0 = data.readString();
                    InnerResultCallback _arg1 = (InnerResultCallback) ICallback.Stub.asInterface(data.readStrongBinder());
                    this.setFontName(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                }

                case TRANSACTION_setFontSize:
                {
                    data.enforceInterface(DESCRIPTOR);
                    float _arg0 = data.readFloat();
                    InnerResultCallback _arg1 = (InnerResultCallback) ICallback.Stub.asInterface(data.readStrongBinder());
                    this.setFontSize(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_printText:
                {
                    data.enforceInterface(DESCRIPTOR);
                    String _arg0 = data.readString();
                    InnerResultCallback _arg1 = (InnerResultCallback) ICallback.Stub.asInterface(data.readStrongBinder());
                    this.printText(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_printTextWithFont:
                {
                    data.enforceInterface(DESCRIPTOR);
                    String _arg0 = data.readString();
                    String _arg1 = data.readString();
                    float _arg2 = data.readFloat();
                    InnerResultCallback _arg3 = (InnerResultCallback) ICallback.Stub.asInterface(data.readStrongBinder());
                    this.printTextWithFont(_arg0, _arg1, _arg2, _arg3);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_printColumnsText:
                {
                    data.enforceInterface(DESCRIPTOR);
                    String[] _arg0 = data.createStringArray();
                    int[] _arg1 = data.createIntArray();
                    int[] _arg2 = data.createIntArray();
                    InnerResultCallback _arg3 = (InnerResultCallback) ICallback.Stub.asInterface(data.readStrongBinder());
                    this.printColumnsText(_arg0, _arg1, _arg2, _arg3);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_printBitmap:
                {
                    Bitmap _arg0;
                    data.enforceInterface(DESCRIPTOR);
                    if (0 != data.readInt()) {
                        _arg0 = (Bitmap) Bitmap.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }

                    InnerResultCallback _arg1 = (InnerResultCallback) ICallback.Stub.asInterface(data.readStrongBinder());
                    this.printBitmap(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_printBarCode:
                {
                    data.enforceInterface(DESCRIPTOR);
                    String _arg0 = data.readString();
                    int _arg1 = data.readInt();
                    int _arg2 = data.readInt();
                    int _arg3 = data.readInt();
                    int _arg4 = data.readInt();
                    InnerResultCallback _arg5 = (InnerResultCallback) ICallback.Stub.asInterface(data.readStrongBinder());
                    this.printBarCode(_arg0, _arg1, _arg2, _arg3, _arg4, _arg5);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_printQRCode:
                {
                    data.enforceInterface(DESCRIPTOR);
                    String _arg0 = data.readString();
                    int _arg1 = data.readInt();
                    int _arg2 = data.readInt();
                    InnerResultCallback _arg3 = (InnerResultCallback) ICallback.Stub.asInterface(data.readStrongBinder());
                    this.printQRCode(_arg0, _arg1, _arg2, _arg3);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_printOriginalText:
                {
                    data.enforceInterface(DESCRIPTOR);
                    String _arg0 = data.readString();
                    InnerResultCallback _arg1 = (InnerResultCallback) ICallback.Stub.asInterface(data.readStrongBinder());
                    this.printOriginalText(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_commitPrint:
                {
                    data.enforceInterface(DESCRIPTOR);
                    TransBean[] _arg0 = (TransBean[]) data.createTypedArray(TransBean.CREATOR);
                    InnerResultCallback _arg1 = (InnerResultCallback) ICallback.Stub.asInterface(data.readStrongBinder());
                    this.commitPrint(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_commitPrinterBuffer:
                    data.enforceInterface(DESCRIPTOR);
                    this.commitPrinterBuffer();
                    reply.writeNoException();
                    return true;
                case TRANSACTION_enterPrinterBuffer:
                {
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result = 0 != data.readInt();
                    this.enterPrinterBuffer(_result);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_exitPrinterBuffer:
                {
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result = 0 != data.readInt();
                    this.exitPrinterBuffer(_result);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_tax:
                {
                    data.enforceInterface(DESCRIPTOR);
                    byte[] _arg0 = data.createByteArray();
                    InnerTaxCallback _arg1 = (InnerTaxCallback) ITax.Stub.asInterface(data.readStrongBinder());
                    this.tax(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_getPrinterFactory:
                {
                    data.enforceInterface(DESCRIPTOR);
                    InnerResultCallback _arg0 = (InnerResultCallback) ICallback.Stub.asInterface(data.readStrongBinder());
                    this.getPrinterFactory(_arg0);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_clearBuffer:
                    data.enforceInterface(DESCRIPTOR);
                    this.clearBuffer();
                    reply.writeNoException();
                    return true;
                case TRANSACTION_commitPrinterBufferWithCallback:
                {
                    data.enforceInterface(DESCRIPTOR);
                    InnerResultCallback _arg0 = (InnerResultCallback) ICallback.Stub.asInterface(data.readStrongBinder());
                    this.commitPrinterBufferWithCallback(_arg0);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_exitPrinterBufferWithCallback:
                {
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result = 0 != data.readInt();
                    InnerResultCallback _arg1 = (InnerResultCallback) ICallback.Stub.asInterface(data.readStrongBinder());
                    this.exitPrinterBufferWithCallback(_result, _arg1);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_printColumnsString:
                {
                    data.enforceInterface(DESCRIPTOR);
                    String[] _arg0 = data.createStringArray();
                    int[] _arg1 = data.createIntArray();
                    int[] _arg2 = data.createIntArray();
                    InnerResultCallback _arg3 = (InnerResultCallback) ICallback.Stub.asInterface(data.readStrongBinder());
                    this.printColumnsString(_arg0, _arg1, _arg2, _arg3);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_updatePrinterState:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0 = this.updatePrinterState();
                    reply.writeNoException();
                    reply.writeInt(_arg0);
                    return true;
                }
                case TRANSACTION_cutPaper:
                {
                    data.enforceInterface(DESCRIPTOR);
                    InnerResultCallback _arg0 = (InnerResultCallback) ICallback.Stub.asInterface(data.readStrongBinder());
                    this.cutPaper(_arg0);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_getCutPaperTimes:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0 = this.getCutPaperTimes();
                    reply.writeNoException();
                    reply.writeInt(_arg0);
                    return true;
                }
                case TRANSACTION_openDrawer:
                {
                    data.enforceInterface(DESCRIPTOR);
                    InnerResultCallback _arg0 = (InnerResultCallback) ICallback.Stub.asInterface(data.readStrongBinder());
                    this.openDrawer(_arg0);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_getOpenDrawerTimes:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0 = this.getOpenDrawerTimes();
                    reply.writeNoException();
                    reply.writeInt(_arg0);
                    return true;
                }
                case TRANSACTION_sendLCDCommand:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0 = data.readInt();
                    this.sendLCDCommand(_arg0);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_sendLCDString:
                {
                    data.enforceInterface(DESCRIPTOR);
                    String _arg0 = data.readString();
                    InnerLcdCallback _arg1 = (InnerLcdCallback) ILcdCallback.Stub.asInterface(data.readStrongBinder());
                    this.sendLCDString(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_sendLCDBitmap:
                {
                    data.enforceInterface(DESCRIPTOR);
                    Bitmap _arg0;
                    if (0 != data.readInt()) {
                        _arg0 = (Bitmap) Bitmap.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }

                    InnerLcdCallback _arg1 = (InnerLcdCallback) ILcdCallback.Stub.asInterface(data.readStrongBinder());
                    this.sendLCDBitmap(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_getPrinterMode:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0 = this.getPrinterMode();
                    reply.writeNoException();
                    reply.writeInt(_arg0);
                    return true;
                }
                case TRANSACTION_getPrinterBBMDistance:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0 = this.getPrinterBBMDistance();
                    reply.writeNoException();
                    reply.writeInt(_arg0);
                    return true;
                }
                case TRANSACTION_printBitmapCustom:
                {
                    data.enforceInterface(DESCRIPTOR);
                    Bitmap _arg0;
                    if (0 != data.readInt()) {
                        _arg0 = (Bitmap) Bitmap.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }

                    int _arg1 = data.readInt();
                    InnerResultCallback _arg2 = (InnerResultCallback) ICallback.Stub.asInterface(data.readStrongBinder());
                    this.printBitmapCustom(_arg0, _arg1, _arg2);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_getForcedDouble:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0 = this.getForcedDouble();
                    reply.writeNoException();
                    reply.writeInt(_arg0);
                    return true;
                }
                case TRANSACTION_isForcedAntiWhite:
                {
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result = this.isForcedAntiWhite();
                    reply.writeNoException();
                    reply.writeInt(_result ? 1 : 0);
                    return true;
                }
                case TRANSACTION_isForcedBold:
                {
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result = this.isForcedBold();
                    reply.writeNoException();
                    reply.writeInt(_result ? 1 : 0);
                    return true;
                }
                case TRANSACTION_isForcedUnderline:
                {
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result = this.isForcedUnderline();
                    reply.writeNoException();
                    reply.writeInt(_result ? 1 : 0);
                    return true;
                }
                case TRANSACTION_getForcedRowHeight:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0 = this.getForcedRowHeight();
                    reply.writeNoException();
                    reply.writeInt(_arg0);
                    return true;
                }
                case TRANSACTION_getFontName:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0 = this.getFontName();
                    reply.writeNoException();
                    reply.writeInt(_arg0);
                    return true;
                }
                case TRANSACTION_sendLCDDoubleString:
                {
                    data.enforceInterface(DESCRIPTOR);
                    String _arg0 = data.readString();
                    String _arg1 = data.readString();
                    InnerLcdCallback _arg2 = (InnerLcdCallback) ILcdCallback.Stub.asInterface(data.readStrongBinder());
                    this.sendLCDDoubleString(_arg0, _arg1, _arg2);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_getPrinterPaper:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0 = this.getPrinterPaper();
                    reply.writeNoException();
                    reply.writeInt(_arg0);
                    return true;
                }
                case TRANSACTION_getDrawerStatus:
                {
                    data.enforceInterface(DESCRIPTOR);
                    boolean _result = this.getDrawerStatus();
                    reply.writeNoException();
                    reply.writeInt(_result ? 1 : 0);
                    return true;
                }
                case TRANSACTION_sendLCDFillString:
                {
                    data.enforceInterface(DESCRIPTOR);
                    String _arg0 = data.readString();
                    int _arg1 = data.readInt();
                    boolean _arg2 = 0 != data.readInt();
                    InnerLcdCallback _arg3 = (InnerLcdCallback) ILcdCallback.Stub.asInterface(data.readStrongBinder());
                    this.sendLCDFillString(_arg0, _arg1, _arg2, _arg3);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_sendLCDMultiString:
                {
                    data.enforceInterface(DESCRIPTOR);
                    String[] _arg0 = data.createStringArray();
                    int[] _arg1 = data.createIntArray();
                    InnerLcdCallback _arg2 = (InnerLcdCallback) ILcdCallback.Stub.asInterface(data.readStrongBinder());
                    this.sendLCDMultiString(_arg0, _arg1, _arg2);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_getPrinterDensity:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0 = this.getPrinterDensity();
                    reply.writeNoException();
                    reply.writeInt(_arg0);
                    return true;
                }
                case TRANSACTION_print2DCode:
                {
                    data.enforceInterface(DESCRIPTOR);
                    String _arg0 = data.readString();
                    int _arg1 = data.readInt();
                    int _arg2 = data.readInt();
                    int _arg3 = data.readInt();
                    InnerResultCallback _arg4 = (InnerResultCallback) ICallback.Stub.asInterface(data.readStrongBinder());
                    this.print2DCode(_arg0, _arg1, _arg2, _arg3, _arg4);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_autoOutPaper:
                {
                    data.enforceInterface(DESCRIPTOR);
                    InnerResultCallback _arg0 = (InnerResultCallback) ICallback.Stub.asInterface(data.readStrongBinder());
                    this.autoOutPaper(_arg0);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_setPrinterStyle:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0 = data.readInt();
                    int _arg1 = data.readInt();
                    this.setPrinterStyle(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_labelLocate:
                {
                    data.enforceInterface(DESCRIPTOR);
                    this.labelLocate();
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_labelOutput:
                {
                    data.enforceInterface(DESCRIPTOR);
                    this.labelOutput();
                    reply.writeNoException();
                    return true;
                }
//////////////////////////////
                case TRANSACTION_initPrinterCallBack:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0;
                    _arg0 = data.readInt();
                    InnerResultCallback _arg1;
                    _arg1 = (InnerResultCallback)ICallback.Stub.asInterface(data.readStrongBinder());
                    this.initPrinterCallBack(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_initPrinter:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0;
                    _arg0 = data.readInt();
                    this.initPrinter(_arg0);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_getPrinterStatusCallBack:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0;
                    _arg0 = data.readInt();
                    InnerResultCallback _arg1;
                    _arg1 = (InnerResultCallback)ICallback.Stub.asInterface(data.readStrongBinder());
                    int _result = this.getPrinterStatusCallBack(_arg0, _arg1);
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                }
                case TRANSACTION_getPrinterStatus:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0;
                    _arg0 = data.readInt();
                    int _result = this.getPrinterStatus(_arg0);
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                }
                case TRANSACTION_printAndLineFeed:
                {
                    data.enforceInterface(DESCRIPTOR);
                    this.printAndLineFeed();
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_printAndLineFeedCallBack:
                {
                    data.enforceInterface(DESCRIPTOR);
                    InnerResultCallback _arg0;
                    _arg0 = (InnerResultCallback)ICallback.Stub.asInterface(data.readStrongBinder());
                    this.printAndLineFeedCallBack(_arg0);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_printAndFeedPaperCallBack:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0;
                    _arg0 = data.readInt();
                    InnerResultCallback _arg1;
                    _arg1 = (InnerResultCallback)ICallback.Stub.asInterface(data.readStrongBinder());
                    this.printAndFeedPaperCallBack(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_printAndFeedPaper:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0;
                    _arg0 = data.readInt();
                    this.printAndFeedPaper(_arg0);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_partialCut:
                {
                    data.enforceInterface(DESCRIPTOR);
                    this.partialCut();
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_partialCutCallBack:
                {
                    data.enforceInterface(DESCRIPTOR);
                    InnerResultCallback _arg0;
                    _arg0 = (InnerResultCallback)ICallback.Stub.asInterface(data.readStrongBinder());
                    this.partialCutCallBack(_arg0);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_setAlignmentCallBack:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0;
                    _arg0 = data.readInt();
                    InnerResultCallback _arg1;
                    _arg1 = (InnerResultCallback)ICallback.Stub.asInterface(data.readStrongBinder());
                    this.setAlignmentCallBack(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                }

                case TRANSACTION_setTextSizeCallBack:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0;
                    _arg0 = data.readInt();
                    InnerResultCallback _arg1;
                    _arg1 = (InnerResultCallback) ICallback.Stub.asInterface(data.readStrongBinder());
                    this.setTextSizeCallBack(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_setTextSize:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0;
                    _arg0 = data.readInt();
                    this.setTextSize(_arg0);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_setTextTypefaceCallBack:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0;
                    _arg0 = data.readInt();
                    InnerResultCallback _arg1;
                    _arg1 = (InnerResultCallback) ICallback.Stub.asInterface(data.readStrongBinder());
                    this.setTextTypefaceCallBack(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_setTextTypeface:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0;
                    _arg0 = data.readInt();
                    this.setTextTypeface(_arg0);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_setTextStyleCallBack:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0;
                    _arg0 = data.readInt();
                    InnerResultCallback _arg1;
                    _arg1 = (InnerResultCallback) ICallback.Stub.asInterface(data.readStrongBinder());
                    this.setTextStyleCallBack(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_setTextStyle:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0;
                    _arg0 = data.readInt();
                    this.setTextStyle(_arg0);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_setTextLineSpacingCallBack:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0;
                    _arg0 = data.readInt();
                    InnerResultCallback _arg1;
                    _arg1 = (InnerResultCallback) ICallback.Stub.asInterface(data.readStrongBinder());
                    this.setTextLineSpacingCallBack(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_setTextLineSpacing:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0;
                    _arg0 = data.readInt();
                    this.setTextLineSpacing(_arg0);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_setTextWidthCallBack:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0;
                    _arg0 = data.readInt();
                    InnerResultCallback _arg1;
                    _arg1 = (InnerResultCallback) ICallback.Stub.asInterface(data.readStrongBinder());
                    this.setTextWidthCallBack(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_setTextWidth:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0;
                    _arg0 = data.readInt();
                    this.setTextWidth(_arg0);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_printTextCallBack:
                {
                    data.enforceInterface(DESCRIPTOR);
                    String _arg0;
                    _arg0 = data.readString();
                    InnerResultCallback _arg1;
                    _arg1 = (InnerResultCallback) ICallback.Stub.asInterface(data.readStrongBinder());
                    this.printTextCallBack(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                }

                case TRANSACTION_printTextWithAliCallBack:
                {
                    data.enforceInterface(DESCRIPTOR);
                    String _arg0;
                    _arg0 = data.readString();
                    int _arg1;
                    _arg1 = data.readInt();
                    InnerResultCallback _arg2;
                    _arg2 = (InnerResultCallback) ICallback.Stub.asInterface(data.readStrongBinder());
                    this.printTextWithAliCallBack(_arg0, _arg1, _arg2);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_printTextWithAli:
                {
                    data.enforceInterface(DESCRIPTOR);
                    String _arg0;
                    _arg0 = data.readString();
                    int _arg1;
                    _arg1 = data.readInt();
                    this.printTextWithAli(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_printColumnsTextCallBack:
                {
                    data.enforceInterface(DESCRIPTOR);
                    String[] _arg0;
                    _arg0 = data.createStringArray();
                    int[] _arg1;
                    _arg1 = data.createIntArray();
                    int[] _arg2;
                    _arg2 = data.createIntArray();
                    int[] _arg3;
                    _arg3 = data.createIntArray();
                    InnerResultCallback _arg4;
                    _arg4 = (InnerResultCallback) ICallback.Stub.asInterface(data.readStrongBinder());
                    this.printColumnsTextCallBack(_arg0, _arg1, _arg2, _arg3,_arg4);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_printColumnsTextWithSize:
                {
                    data.enforceInterface(DESCRIPTOR);
                    String[] _arg0;
                    _arg0 = data.createStringArray();
                    int[] _arg1;
                    _arg1 = data.createIntArray();
                    int[] _arg2;
                    _arg2 = data.createIntArray();
                    int[] _arg3;
                    _arg3 = data.createIntArray();

                    this.printColumnsText(_arg0, _arg1, _arg2, _arg3);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_setBarCodeWidthCallBack:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0;
                    _arg0 = data.readInt();
                    InnerResultCallback _arg1;
                    _arg1 = (InnerResultCallback) ICallback.Stub.asInterface(data.readStrongBinder());
                    this.setBarCodeWidthCallBack(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_setBarCodeWidth:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0;
                    _arg0 = data.readInt();
                    this.setBarCodeWidth(_arg0);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_setBarCodeHeightCallBack:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0;
                    _arg0 = data.readInt();
                    InnerResultCallback _arg1;
                    _arg1 = (InnerResultCallback) ICallback.Stub.asInterface(data.readStrongBinder());
                    this.setBarCodeHeightCallBack(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_setBarCodeHeight:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0;
                    _arg0 = data.readInt();
                    this.setBarCodeHeight(_arg0);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_setBarCodeContentPrintPosCallBack:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0;
                    _arg0 = data.readInt();
                    InnerResultCallback _arg1;
                    _arg1 = (InnerResultCallback) ICallback.Stub.asInterface(data.readStrongBinder());
                    this.setBarCodeContentPrintPosCallBack(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_setBarCodeContentPrintPos:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0;
                    _arg0 = data.readInt();
                    this.setBarCodeContentPrintPos(_arg0);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_printBarCodeCallBack:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0;
                    _arg0 = data.readInt();
                    String _arg1;
                    _arg1 = data.readString();
                    InnerResultCallback _arg2;
                    _arg2 = (InnerResultCallback) ICallback.Stub.asInterface(data.readStrongBinder());
                    this.printBarCodeCallBack(_arg0, _arg1, _arg2);
                    reply.writeNoException();
                    return true;
                }

                case TRANSACTION_printBarCodeWithAlignCallBack:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0;
                    _arg0 = data.readInt();
                    String _arg1;
                    _arg1 = data.readString();
                    int _arg2;
                    _arg2 = data.readInt();
                    InnerResultCallback _arg3;
                    _arg3 = (InnerResultCallback) ICallback.Stub.asInterface(data.readStrongBinder());
                    this.printBarCodeWithAlignCallBack(_arg0, _arg1, _arg2, _arg3);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_printBarCodeWithAlign:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0;
                    _arg0 = data.readInt();
                    String _arg1;
                    _arg1 = data.readString();
                    int _arg2;
                    _arg2 = data.readInt();
                    this.printBarCodeWithAlign(_arg0, _arg1, _arg2);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_setQrCodeSizeCallBack:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0;
                    _arg0 = data.readInt();
                    InnerResultCallback _arg1;
                    _arg1 = (InnerResultCallback) ICallback.Stub.asInterface(data.readStrongBinder());
                    this.setQrCodeSizeCallBack(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_setQrCodeSize:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0;
                    _arg0 = data.readInt();
                    this.setQrCodeSize(_arg0);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_setQrCodeErrorCorrectionLevCallBack:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0;
                    _arg0 = data.readInt();
                    InnerResultCallback _arg1;
                    _arg1 = (InnerResultCallback) ICallback.Stub.asInterface(data.readStrongBinder());
                    this.setQrCodeErrorCorrectionLevCallBack(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_setQrCodeErrorCorrectionLev:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0;
                    _arg0 = data.readInt();
                    this.setQrCodeErrorCorrectionLev(_arg0);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_setLeftMarginCallBack:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0;
                    _arg0 = data.readInt();
                    InnerResultCallback _arg1;
                    _arg1 = (InnerResultCallback) ICallback.Stub.asInterface(data.readStrongBinder());
                    this.setLeftMarginCallBack(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_setLeftMargin:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0;
                    _arg0 = data.readInt();
                    this.setLeftMargin(_arg0);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_printQrCodeCallBack:
                {
                    data.enforceInterface(DESCRIPTOR);
                    String _arg0;
                    _arg0 = data.readString();
                    InnerResultCallback _arg1;
                    _arg1 = (InnerResultCallback) ICallback.Stub.asInterface(data.readStrongBinder());
                    this.printQrCodeCallBack(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_printQrCode:
                {
                    data.enforceInterface(DESCRIPTOR);
                    String _arg0;
                    _arg0 = data.readString();
                    this.printQrCode(_arg0);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_printQrCodeWithAlignCallBack:
                {
                    data.enforceInterface(DESCRIPTOR);
                    String _arg0;
                    _arg0 = data.readString();
                    int _arg1;
                    _arg1 = data.readInt();
                    InnerResultCallback _arg2;
                    _arg2 = (InnerResultCallback) ICallback.Stub.asInterface(data.readStrongBinder());
                    this.printQrCodeWithAlignCallBack(_arg0, _arg1, _arg2);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_printQrCodeWithAlign:
                {
                    data.enforceInterface(DESCRIPTOR);
                    String _arg0;
                    _arg0 = data.readString();
                    int _arg1;
                    _arg1 = data.readInt();
                    this.printQrCodeWithAlign(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_setPageFormatCallBack:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0;
                    _arg0 = data.readInt();
                    InnerResultCallback _arg1;
                    _arg1 = (InnerResultCallback) ICallback.Stub.asInterface(data.readStrongBinder());
                    this.setPageFormatCallBack(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_setPageFormat:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0;
                    _arg0 = data.readInt();
                    this.setPageFormat(_arg0);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_printSingleBitmapCallBack:
                {
                    data.enforceInterface(DESCRIPTOR);
                    Bitmap _arg0;
                    if ((0!=data.readInt())) {
                        _arg0 = Bitmap.CREATOR.createFromParcel(data);
                    }
                    else {
                        _arg0 = null;
                    }
                    InnerResultCallback _arg1;
                    _arg1 = (InnerResultCallback) ICallback.Stub.asInterface(data.readStrongBinder());
                    this.printSingleBitmapCallBack(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_printSingleBitmap:
                {
                    data.enforceInterface(DESCRIPTOR);
                    Bitmap _arg0;
                    if ((0!=data.readInt())) {
                        _arg0 = Bitmap.CREATOR.createFromParcel(data);
                    }
                    else {
                        _arg0 = null;
                    }
                    this.printSingleBitmap(_arg0);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_printSingleBitmapWithAlignCallBack:
                {
                    data.enforceInterface(DESCRIPTOR);
                    Bitmap _arg0;
                    if ((0!=data.readInt())) {
                        _arg0 = Bitmap.CREATOR.createFromParcel(data);
                    }
                    else {
                        _arg0 = null;
                    }
                    int _arg1;
                    _arg1 = data.readInt();
                    InnerResultCallback _arg2;
                    _arg2 = (InnerResultCallback) ICallback.Stub.asInterface(data.readStrongBinder());
                    this.printSingleBitmapWithAlignCallBack(_arg0, _arg1, _arg2);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_printSingleBitmapWithAlign:
                {
                    data.enforceInterface(DESCRIPTOR);
                    Bitmap _arg0;
                    if ((0!=data.readInt())) {
                        _arg0 = Bitmap.CREATOR.createFromParcel(data);
                    }
                    else {
                        _arg0 = null;
                    }
                    int _arg1;
                    _arg1 = data.readInt();
                    this.printSingleBitmapWithAlign(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_printMultiBitmapCallBack:
                {
                    data.enforceInterface(DESCRIPTOR);
                    List<Bitmap> _arg0;
                    _arg0 = data.createTypedArrayList(Bitmap.CREATOR);
                    InnerResultCallback _arg1;
                    _arg1 = (InnerResultCallback) ICallback.Stub.asInterface(data.readStrongBinder());
                    this.printMultiBitmapCallBack(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_printMultiBitmap:
                {
                    data.enforceInterface(DESCRIPTOR);
                    List<Bitmap> _arg0;
                    _arg0 = data.createTypedArrayList(Bitmap.CREATOR);
                    this.printMultiBitmap(_arg0);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_printMultiBitmapWithAlignCallBack:
                {
                    data.enforceInterface(DESCRIPTOR);
                    List<Bitmap> _arg0;
                    _arg0 = data.createTypedArrayList(Bitmap.CREATOR);
                    int _arg1;
                    _arg1 = data.readInt();
                    InnerResultCallback _arg2;
                    _arg2 = (InnerResultCallback) ICallback.Stub.asInterface(data.readStrongBinder());
                    this.printMultiBitmapWithAlignCallBack(_arg0, _arg1, _arg2);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_printMultiBitmapWithAlign:
                {
                    data.enforceInterface(DESCRIPTOR);
                    List<Bitmap> _arg0;
                    _arg0 = data.createTypedArrayList(Bitmap.CREATOR);
                    int _arg1;
                    _arg1 = data.readInt();
                    this.printMultiBitmapWithAlign(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_resetDeviceCallBack:
                {
                    data.enforceInterface(DESCRIPTOR);
                    InnerResultCallback _arg0;
                    _arg0 = (InnerResultCallback) ICallback.Stub.asInterface(data.readStrongBinder());
                    this.resetDeviceCallBack(_arg0);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_resetDevice:
                {
                    data.enforceInterface(DESCRIPTOR);
                    this.resetDevice();
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_fullCutCallBack:
                {
                    data.enforceInterface(DESCRIPTOR);
                    InnerResultCallback _arg0;
                    _arg0 = (InnerResultCallback) ICallback.Stub.asInterface(data.readStrongBinder());
                    this.fullCutCallBack(_arg0);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_fullCut:
                {
                    data.enforceInterface(DESCRIPTOR);
                    this.fullCut();
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_sendRAWDataCallBack:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int[] _arg0;
                    _arg0 = data.createIntArray();
                    InnerResultCallback _arg1;
                    _arg1 = (InnerResultCallback) ICallback.Stub.asInterface(data.readStrongBinder());
                    this.sendRAWDataCallBack(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                }

                case TRANSACTION_sendRAWDataStringCallBack:
                {
                    data.enforceInterface(DESCRIPTOR);
                    String _arg0;
                    _arg0 = data.readString();
                    InnerResultCallback _arg1;
                    _arg1 = (InnerResultCallback) ICallback.Stub.asInterface(data.readStrongBinder());
                    this.sendRAWDataStringCallBack(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_sendRAWDataString:
                {
                    data.enforceInterface(DESCRIPTOR);
                    String _arg0;
                    _arg0 = data.readString();
                    this.sendRAWDataString(_arg0);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_sendRAWDataByteCallBack:
                {
                    data.enforceInterface(DESCRIPTOR);
                    byte[] _arg0;
                    _arg0 = data.createByteArray();
                    InnerResultCallback _arg1;
                    _arg1 = (InnerResultCallback) ICallback.Stub.asInterface(data.readStrongBinder());
                    this.sendRAWDataByteCallBack(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_sendRAWDataByte:
                {
                    data.enforceInterface(DESCRIPTOR);
                    byte[] _arg0;
                    _arg0 = data.createByteArray();
                    this.sendRAWDataByte(_arg0);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_setUnderlineCallBack:
                {
                    data.enforceInterface(DESCRIPTOR);
                    boolean _arg0;
                    _arg0 = (0!=data.readInt());
                    InnerResultCallback _arg1;
                    _arg1 = (InnerResultCallback) ICallback.Stub.asInterface(data.readStrongBinder());
                    this.setUnderlineCallBack(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_setUnderline:
                {
                    data.enforceInterface(DESCRIPTOR);
                    boolean _arg0;
                    _arg0 = (0!=data.readInt());
                    this.setUnderline(_arg0);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_sethaveBoldCallBack:
                {
                    data.enforceInterface(DESCRIPTOR);
                    boolean _arg0;
                    _arg0 = (0!=data.readInt());
                    InnerResultCallback _arg1;
                    _arg1 = (InnerResultCallback) ICallback.Stub.asInterface(data.readStrongBinder());
                    this.sethaveBoldCallBack(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_sethaveBold:
                {
                    data.enforceInterface(DESCRIPTOR);
                    boolean _arg0;
                    _arg0 = (0!=data.readInt());
                    this.sethaveBold(_arg0);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_setHaveLineHeightCallBack:
                {
                    data.enforceInterface(DESCRIPTOR);
                    float _arg0;
                    _arg0 = data.readFloat();
                    InnerResultCallback _arg1;
                    _arg1 = (InnerResultCallback)ICallback.Stub.asInterface(data.readStrongBinder());
                    this.setHaveLineHeightCallBack(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_setHaveLineHeight:
                {
                    data.enforceInterface(DESCRIPTOR);
                    float _arg0;
                    _arg0 = data.readFloat();
                    this.setHaveLineHeight(_arg0);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_printerByteWithByteCallBack:
                {
                    data.enforceInterface(DESCRIPTOR);
                    byte[] _arg0;
                    _arg0 = data.createByteArray();
                    InnerResultCallback _arg1;
                    _arg1 = (InnerResultCallback)ICallback.Stub.asInterface(data.readStrongBinder());
                    this.printerByteWithByteCallBack(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_printerByteWithByte:
                {
                    data.enforceInterface(DESCRIPTOR);
                    byte[] _arg0;
                    _arg0 = data.createByteArray();
                    this.printerByteWithByte(_arg0);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_printerByteCallBack:
                {
                    data.enforceInterface(DESCRIPTOR);
                    InnerResultCallback _arg0;
                    _arg0 = (InnerResultCallback)ICallback.Stub.asInterface(data.readStrongBinder());
                    this.printerByteCallBack(_arg0);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_printerByte:
                {
                    data.enforceInterface(DESCRIPTOR);
                    this.printerByte();
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_setDoubleQRSizeCallBack:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0;
                    _arg0 = data.readInt();
                    InnerResultCallback _arg1;
                    _arg1 = (InnerResultCallback)ICallback.Stub.asInterface(data.readStrongBinder());
                    this.setDoubleQRSizeCallBack(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_setDoubleQRSize:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0;
                    _arg0 = data.readInt();
                    this.setDoubleQRSize(_arg0);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_setDoubleQR1MarginLeftCallBack:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0;
                    _arg0 = data.readInt();
                    InnerResultCallback _arg1;
                    _arg1 = (InnerResultCallback)ICallback.Stub.asInterface(data.readStrongBinder());
                    this.setDoubleQR1MarginLeftCallBack(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_setDoubleQR1MarginLeft:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0;
                    _arg0 = data.readInt();
                    this.setDoubleQR1MarginLeft(_arg0);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_setDoubleQR2MarginLeftCallBack:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0;
                    _arg0 = data.readInt();
                    InnerResultCallback _arg1;
                    _arg1 = (InnerResultCallback)ICallback.Stub.asInterface(data.readStrongBinder());
                    this.setDoubleQR2MarginLeftCallBack(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_setDoubleQR2MarginLeft:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0;
                    _arg0 = data.readInt();
                    this.setDoubleQR2MarginLeft(_arg0);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_setDoubleQR1LevelCallBack:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0;
                    _arg0 = data.readInt();
                    InnerResultCallback _arg1;
                    _arg1 = (InnerResultCallback)ICallback.Stub.asInterface(data.readStrongBinder());
                    this.setDoubleQR1LevelCallBack(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_setDoubleQR1Level:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0;
                    _arg0 = data.readInt();
                    this.setDoubleQR1Level(_arg0);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_setDoubleQR2LevelCallBack:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0;
                    _arg0 = data.readInt();
                    InnerResultCallback _arg1;
                    _arg1 = (InnerResultCallback)ICallback.Stub.asInterface(data.readStrongBinder());
                    this.setDoubleQR2LevelCallBack(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_setDoubleQR2Level:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0;
                    _arg0 = data.readInt();
                    this.setDoubleQR2Level(_arg0);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_setDoubleQR1VersionCallBack:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0;
                    _arg0 = data.readInt();
                    InnerResultCallback _arg1;
                    _arg1 = (InnerResultCallback)ICallback.Stub.asInterface(data.readStrongBinder());
                    this.setDoubleQR1VersionCallBack(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_setDoubleQR1Version:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0;
                    _arg0 = data.readInt();
                    this.setDoubleQR1Version(_arg0);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_setDoubleQR2VersionCallBack:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0;
                    _arg0 = data.readInt();
                    InnerResultCallback _arg1;
                    _arg1 = (InnerResultCallback)ICallback.Stub.asInterface(data.readStrongBinder());
                    this.setDoubleQR2VersionCallBack(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_setDoubleQR2Version:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0;
                    _arg0 = data.readInt();
                    this.setDoubleQR2Version(_arg0);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_printDoubleQRCallBack:
                {
                    data.enforceInterface(DESCRIPTOR);
                    String _arg0;
                    _arg0 = data.readString();
                    String _arg1 = data.readString();
                    InnerResultCallback _arg2;
                    _arg2 = (InnerResultCallback)ICallback.Stub.asInterface(data.readStrongBinder());
                    this.printDoubleQRCallBack(_arg0, _arg1,_arg2);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_printDoubleQR:
                {
                    data.enforceInterface(DESCRIPTOR);
                    String _arg0;
                    _arg0 = data.readString();
                    String _arg1 = data.readString();
                    this.printDoubleQR(_arg0,_arg1);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_initParamsCallBack:
                {
                    data.enforceInterface(DESCRIPTOR);
                    InnerResultCallback _arg0;
                    _arg0 = (InnerResultCallback)ICallback.Stub.asInterface(data.readStrongBinder());
                    this.initParamsCallBack(_arg0);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_initParams:
                {
                    data.enforceInterface(DESCRIPTOR);
                    this.initParams();
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_initParamsIsClearCallBack:
                {
                    data.enforceInterface(DESCRIPTOR);
                    boolean _arg0;
                    _arg0 = (0!=data.readInt());
                    InnerResultCallback _arg1;
                    _arg1 = (InnerResultCallback)ICallback.Stub.asInterface(data.readStrongBinder());
                    this.initParamsIsClearCallBack(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_initParamsIsClear:
                {
                    data.enforceInterface(DESCRIPTOR);
                    boolean _arg0;
                    _arg0 = (0!=data.readInt());
                    this.initParamsIsClear(_arg0);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_getForcedRowHeightCallBack:
                {
                    data.enforceInterface(DESCRIPTOR);
                    InnerResultCallback _arg0;
                    _arg0 = (InnerResultCallback)ICallback.Stub.asInterface(data.readStrongBinder());
                    int _result = this.getForcedRowHeightCallBack(_arg0);
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                }
                case TRANSACTION_isForcedBoldCallBack:
                {
                    data.enforceInterface(DESCRIPTOR);
                    InnerResultCallback _arg0;
                    _arg0 = (InnerResultCallback)ICallback.Stub.asInterface(data.readStrongBinder());
                    boolean _result = this.isForcedBoldCallBack(_arg0);
                    reply.writeNoException();
                    reply.writeInt(((_result)?(1):(0)));
                    return true;
                }
                case TRANSACTION_isForcedUnderlineCallBack:
                {
                    data.enforceInterface(DESCRIPTOR);
                    InnerResultCallback _arg0;
                    _arg0 = (InnerResultCallback)ICallback.Stub.asInterface(data.readStrongBinder());
                    boolean _result = this.isForcedUnderlineCallBack(_arg0);
                    reply.writeNoException();
                    reply.writeInt(((_result)?(1):(0)));
                    return true;
                }
                case TRANSACTION_getPrinterDensityCallBack:
                {
                    data.enforceInterface(DESCRIPTOR);
                    InnerResultCallback _arg0;
                    _arg0 = (InnerResultCallback)ICallback.Stub.asInterface(data.readStrongBinder());
                    int _result = this.getPrinterDensityCallBack(_arg0);
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                }
                case TRANSACTION_initBluePrinterCallBack:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0;
                    _arg0 = data.readInt();
                    BluetoothDevice _arg1;
                    if ((0!=data.readInt())) {
                        _arg1 = BluetoothDevice.CREATOR.createFromParcel(data);
                    }
                    else {
                        _arg1 = null;
                    }
                    InnerResultCallback _arg2;
                    _arg2 = (InnerResultCallback)ICallback.Stub.asInterface(data.readStrongBinder());
                    this.initBluePrinterCallBack(_arg0, _arg1, _arg2);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_initBluePrinter:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0;
                    _arg0 = data.readInt();
                    BluetoothDevice _arg1;
                    if ((0!=data.readInt())) {
                        _arg1 = BluetoothDevice.CREATOR.createFromParcel(data);
                    }
                    else {
                        _arg1 = null;
                    }
                    this.initBluePrinter(_arg0, _arg1);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_printBMPBitmapCallBack:
                {
                    data.enforceInterface(DESCRIPTOR);

                    Bitmap _arg1;
                    if ((0!=data.readInt())) {
                        _arg1 = Bitmap.CREATOR.createFromParcel(data);
                    }
                    else {
                        _arg1 = null;
                    }
                    InnerResultCallback _arg2;
                    _arg2 = (InnerResultCallback)ICallback.Stub.asInterface(data.readStrongBinder());
                    this.printBMPBitmapCallBack(_arg1, _arg2);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_printBMPBitmap:
                {
                    data.enforceInterface(DESCRIPTOR);
                    Bitmap _arg1;
                    if ((0!=data.readInt())) {
                        _arg1 = Bitmap.CREATOR.createFromParcel(data);
                    }
                    else {
                        _arg1 = null;
                    }
                    this.printBMPBitmap(_arg1);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_printNvBitmapCallBack:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0;
                    _arg0 = data.readInt();

                    InnerResultCallback _arg2;
                    _arg2 = (InnerResultCallback)ICallback.Stub.asInterface(data.readStrongBinder());
                    this.printNvBitmapCallBack(_arg0,_arg2);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_printNvBitmap:
                {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0;
                    _arg0 = data.readInt();

                    this.printNvBitmap(_arg0);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_setDownloadNvBmpCallBack:
                {
                    data.enforceInterface(DESCRIPTOR);
                    String _arg0;
                    _arg0 = data.readString();

                    InnerResultCallback _arg2;
                    _arg2 = (InnerResultCallback)ICallback.Stub.asInterface(data.readStrongBinder());
                    this.setDownloadNvBmpCallBack(_arg0,_arg2);
                    reply.writeNoException();
                    return true;
                }
                case TRANSACTION_setDownloadNvBmp:
                {
                    data.enforceInterface(DESCRIPTOR);
                    String _arg0;
                    _arg0 = data.readString();

                    this.setDownloadNvBmp(_arg0);
                    reply.writeNoException();
                    return true;
                }
//////////////////////////////
                case 1598968902:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        public static class Proxy implements SunmiPrinterService {
            private IBinder mRemote;
            private int[] transaction_table;

            Proxy(IBinder remote) {
                this.mRemote = remote;
                this.setTable();
            }

            private void setTable() {
                String model = SystemProperties.get("ro.sunmi.hardware").toUpperCase(Locale.ENGLISH);
                if(model.contains("V2") || model.contains("P2") || model.contains("P1")
                        || model.contains("V1S") || "QBAO_H1".equals(model)){
                    transaction_table = Stub.TRANSCTION_DATASHEET[0];
                }else if(model.contains("MINI") && (model.contains("T1") || model.contains("T2"))){
                    transaction_table = Stub.TRANSCTION_DATASHEET[1];
                }else if(model.contains("V1")){
                    transaction_table = Stub.TRANSCTION_DATASHEET[2];
                }else if(model.contains("T1") || model.contains("T2") || model.contains("S2")){
                    transaction_table = Stub.TRANSCTION_DATASHEET[3];
                }else if (model.contains("VSTC")){
                    this.transaction_table = Stub.TRANSCTION_DATASHEET[0];
                }
                else{
                    transaction_table = Stub.TRANSCTION_DATASHEET_DEFAULT[0];
                }
//                if (!model.contains("V2") && !model.contains("P2") && !model.contains("P1") && !model.contains("V1S")) {
//                    if (!model.contains("MINI") || !model.contains("T1") && !model.contains("T2")) {
//                        if (model.contains("V1")) {
//                            this.transaction_table = SunmiPrinterService.Stub.TRANSCTION_DATASHEET[2];
//                        } else if (!model.contains("T1") && !model.contains("T2") && !model.contains("S2")) {
//                            if (model.equals("VSTC")) {
//                                this.transaction_table = SunmiPrinterService.Stub.TRANSCTION_DATASHEET[0];
//                            } else {
//                                this.transaction_table = SunmiPrinterService.Stub.TRANSCTION_DATASHEET[3];
//                            }
//                        } else {
//                            this.transaction_table = SunmiPrinterService.Stub.TRANSCTION_DATASHEET[3];
//                        }
//                    } else {
//                        this.transaction_table = SunmiPrinterService.Stub.TRANSCTION_DATASHEET[1];
//                    }
//                } else {
//                    this.transaction_table = SunmiPrinterService.Stub.TRANSCTION_DATASHEET[0];
//                }

            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDESCRIPTOR() {
                return DESCRIPTOR;
            }

            public void updateFirmware() throws RemoteException {
                throw new InnerPrinterException("only system calls are supported");
            }

            public int getFirmwareStatus() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                int _result;
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    this.mRemote.transact(TRANSACTION_getFirmwareStatus, _data, _reply, 0);
                    _reply.readException();
                    _result = _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

                return _result;
            }

            public String getServiceVersion() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                String _result;
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    this.mRemote.transact(TRANSACTION_getServiceVersion, _data, _reply, 0);
                    _reply.readException();
                    _result = _reply.readString();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

                return _result;
            }

            public void printerInit(InnerResultCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(TRANSACTION_printerInit, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void printerSelfChecking(InnerResultCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _reply.readException();
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(TRANSACTION_printerSelfChecking, _data, _reply, 0);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public String getPrinterSerialNo() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                String _result;
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    this.mRemote.transact(TRANSACTION_getPrinterSerialNo, _data, _reply, 0);
                    _reply.readException();
                    _result = _reply.readString();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

                return _result;
            }

            public String getPrinterVersion() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                String _result;
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    this.mRemote.transact(TRANSACTION_getPrinterVersion, _data, _reply, 0);
                    _reply.readException();
                    _result = _reply.readString();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

                return _result;
            }

            public String getPrinterModal() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                String _result;
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    this.mRemote.transact(8, _data, _reply, 0);
                    _reply.readException();
                    _result = _reply.readString();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

                return _result;
            }

            public void getPrintedLength(InnerResultCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(9, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.dataAvail() == TRANSACTION_printerInit) {
                        int _result = _reply.readInt();
                        if (callback != null && _result != -1) {
                            callback.onReturnString(String.valueOf(_result));
                        }
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void lineWrap(int n, InnerResultCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(n);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(10, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void sendRAWData(byte[] data, InnerResultCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeByteArray(data);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(11, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void setAlignment(int alignment, InnerResultCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(alignment);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(12, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void setFontName(String typeface, InnerResultCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeString(typeface);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(13, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void setFontSize(float fontsize, InnerResultCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeFloat(fontsize);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(14, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void printText(String text, InnerResultCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeString(text);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(15, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void printTextWithFont(String text, String typeface, float fontsize, InnerResultCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeString(text);
                    _data.writeString(typeface);
                    _data.writeFloat(fontsize);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(16, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void printColumnsText(String[] colsTextArr, int[] colsWidthArr, int[] colsAlign, InnerResultCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeStringArray(colsTextArr);
                    _data.writeIntArray(colsWidthArr);
                    _data.writeIntArray(colsAlign);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(17, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void printBitmap(Bitmap bitmap, InnerResultCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    if (bitmap != null) {
                        _data.writeInt(1);
                        bitmap.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }

                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(18, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void printBarCode(String data, int symbology, int height, int width, int textposition, InnerResultCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeString(data);
                    _data.writeInt(symbology);
                    _data.writeInt(height);
                    _data.writeInt(width);
                    _data.writeInt(textposition);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(19, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void printQRCode(String data, int modulesize, int errorlevel, InnerResultCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeString(data);
                    _data.writeInt(modulesize);
                    _data.writeInt(errorlevel);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(20, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void printOriginalText(String text, InnerResultCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeString(text);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    this.mRemote.transact(TRANSACTION_printOriginalText, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void commitPrint(TransBean[] transbean, InnerResultCallback callback) throws RemoteException {
                if (this.transaction_table[19] == TRANSACTION_STOP) {
                    throw new InnerPrinterException("this model does not support this method!");
                } else {
                    Parcel _data = Parcel.obtain();
                    Parcel _reply = Parcel.obtain();

                    try {
                        _data.writeInterfaceToken(DESCRIPTOR);
                        _data.writeTypedArray(transbean, 0);
                        _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                        boolean res = this.mRemote.transact(TRANSACTION_commitPrint, _data, _reply, 0);
                        if (!res) {
                            throw new InnerPrinterException("this version does not support this method!");
                        }

                        _reply.readException();
                    } finally {
                        _reply.recycle();
                        _data.recycle();
                    }

                }
            }

            public void commitPrinterBuffer() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    boolean res = this.mRemote.transact(TRANSACTION_commitPrinterBuffer + this.transaction_table[0], _data, _reply, 0);
                    if (!res) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }

                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void cutPaper(InnerResultCallback callback) throws RemoteException {
                if (this.transaction_table[10] == TRANSACTION_STOP) {
                    throw new InnerPrinterException("this model does not support this method!");
                } else {
                    Parcel _data = Parcel.obtain();
                    Parcel _reply = Parcel.obtain();

                    try {
                        _data.writeInterfaceToken(DESCRIPTOR);
                        _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                        boolean res = this.mRemote.transact(TRANSACTION_cutPaper + this.transaction_table[10], _data, _reply, 0);
                        if (!res) {
                            throw new InnerPrinterException("this version does not support this method!");
                        }

                        _reply.readException();
                    } finally {
                        _reply.recycle();
                        _data.recycle();
                    }

                }
            }

            public int getCutPaperTimes() throws RemoteException {
                if (this.transaction_table[11] == TRANSACTION_STOP) {
                    throw new InnerPrinterException("this model does not support this method!");
                } else {
                    Parcel _data = Parcel.obtain();
                    Parcel _reply = Parcel.obtain();

                    int _result;
                    try {
                        _data.writeInterfaceToken(DESCRIPTOR);
                        boolean res = this.mRemote.transact(TRANSACTION_getCutPaperTimes + this.transaction_table[11], _data, _reply, 0);
                        if (!res) {
                            throw new InnerPrinterException("this version does not support this method!");
                        }

                        _reply.readException();
                        _result = _reply.readInt();
                    } finally {
                        _reply.recycle();
                        _data.recycle();
                    }

                    return _result;
                }
            }

            public void openDrawer(InnerResultCallback callback) throws RemoteException {
                if (this.transaction_table[12] == TRANSACTION_STOP) {
                    throw new InnerPrinterException("this model does not support this method!");
                } else {
                    Parcel _data = Parcel.obtain();
                    Parcel _reply = Parcel.obtain();

                    try {
                        _data.writeInterfaceToken(DESCRIPTOR);
                        _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                        boolean res = this.mRemote.transact(TRANSACTION_openDrawer + this.transaction_table[12], _data, _reply, 0);
                        if (!res) {
                            throw new InnerPrinterException("this version does not support this method!");
                        }

                        _reply.readException();
                    } finally {
                        _reply.recycle();
                        _data.recycle();
                    }

                }
            }

            public int getOpenDrawerTimes() throws RemoteException {
                if (this.transaction_table[13] == TRANSACTION_STOP) {
                    throw new InnerPrinterException("this model does not support this method!");
                } else {
                    Parcel _data = Parcel.obtain();
                    Parcel _reply = Parcel.obtain();

                    int _result;
                    try {
                        _data.writeInterfaceToken(DESCRIPTOR);
                        boolean res = this.mRemote.transact(TRANSACTION_getOpenDrawerTimes + this.transaction_table[13], _data, _reply, 0);
                        if (!res) {
                            throw new InnerPrinterException("this version does not support this method!");
                        }

                        _reply.readException();
                        _result = _reply.readInt();
                    } finally {
                        _reply.recycle();
                        _data.recycle();
                    }

                    return _result;
                }
            }

            public void enterPrinterBuffer(boolean clean) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(clean ? 1 : 0);
                    boolean res = this.mRemote.transact(TRANSACTION_enterPrinterBuffer + this.transaction_table[1], _data, _reply, 0);
                    if (!res) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }

                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void exitPrinterBuffer(boolean commit) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(commit ? 1 : 0);
                    boolean res = this.mRemote.transact(TRANSACTION_exitPrinterBuffer + this.transaction_table[2], _data, _reply, 0);
                    if (!res) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }

                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public void tax(byte[] data, InnerTaxCallback callback) throws RemoteException {
                if (this.transaction_table[3] == TRANSACTION_STOP) {
                    throw new InnerPrinterException("this model does not support this method!");
                } else {
                    Parcel _data = Parcel.obtain();
                    Parcel _reply = Parcel.obtain();

                    try {
                        _data.writeInterfaceToken(DESCRIPTOR);
                        _data.writeByteArray(data);
                        _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                        boolean res = this.mRemote.transact(TRANSACTION_tax + this.transaction_table[3], _data, _reply, 0);
                        if (!res) {
                            throw new InnerPrinterException("this version does not support this method!");
                        }

                        _reply.readException();
                    } finally {
                        _reply.recycle();
                        _data.recycle();
                    }

                }
            }

            public void getPrinterFactory(InnerResultCallback callback) throws RemoteException {
                if (this.transaction_table[4] == TRANSACTION_STOP) {
                    throw new InnerPrinterException("this model does not support this method!");
                } else {
                    Parcel _data = Parcel.obtain();
                    Parcel _reply = Parcel.obtain();

                    try {
                        _data.writeInterfaceToken(DESCRIPTOR);
                        _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                        boolean res = this.mRemote.transact(TRANSACTION_getPrinterFactory + this.transaction_table[4], _data, _reply, 0);
                        if (!res) {
                            throw new InnerPrinterException("this version does not support this method!");
                        }

                        _reply.readException();
                    } finally {
                        _reply.recycle();
                        _data.recycle();
                    }

                }
            }

            public void clearBuffer() throws RemoteException {
                if (this.transaction_table[5] == TRANSACTION_STOP) {
                    throw new InnerPrinterException("this model does not support this method!");
                } else {
                    Parcel _data = Parcel.obtain();
                    Parcel _reply = Parcel.obtain();

                    try {
                        _data.writeInterfaceToken(DESCRIPTOR);
                        boolean res = this.mRemote.transact(TRANSACTION_clearBuffer + this.transaction_table[5], _data, _reply, 0);
                        if (!res) {
                            throw new InnerPrinterException("this version does not support this method!");
                        }

                        _reply.readException();
                    } finally {
                        _reply.recycle();
                        _data.recycle();
                    }

                }
            }

            public void commitPrinterBufferWithCallback(InnerResultCallback callback) throws RemoteException {
                if (this.transaction_table[6] == TRANSACTION_STOP) {
                    throw new InnerPrinterException("this model does not support this method!");
                } else {
                    Parcel _data = Parcel.obtain();
                    Parcel _reply = Parcel.obtain();

                    try {
                        _data.writeInterfaceToken(DESCRIPTOR);
                        _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                        boolean res = this.mRemote.transact(TRANSACTION_commitPrinterBufferWithCallback + this.transaction_table[6], _data, _reply, 0);
                        if (!res) {
                            throw new InnerPrinterException("this version does not support this method!");
                        }

                        _reply.readException();
                    } finally {
                        _reply.recycle();
                        _data.recycle();
                    }

                }
            }

            public void exitPrinterBufferWithCallback(boolean commit, InnerResultCallback callback) throws RemoteException {
                if (this.transaction_table[6] == TRANSACTION_STOP) {
                    throw new InnerPrinterException("this model does not support this method!");
                } else {
                    Parcel _data = Parcel.obtain();
                    Parcel _reply = Parcel.obtain();

                    try {
                        _data.writeInterfaceToken(DESCRIPTOR);
                        _data.writeInt(commit ? 1 : 0);
                        _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                        boolean res = this.mRemote.transact(TRANSACTION_exitPrinterBufferWithCallback + this.transaction_table[7], _data, _reply, 0);
                        if (!res) {
                            throw new InnerPrinterException("this version does not support this method!");
                        }

                        _reply.readException();
                    } finally {
                        _reply.recycle();
                        _data.recycle();
                    }

                }
            }

            public void printColumnsString(String[] colsTextArr, int[] colsWidthArr, int[] colsAlign, InnerResultCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();

                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeStringArray(colsTextArr);
                    _data.writeIntArray(colsWidthArr);
                    _data.writeIntArray(colsAlign);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean res = this.mRemote.transact(TRANSACTION_printColumnsString + this.transaction_table[8], _data, _reply, 0);
                    if (!res) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }

                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }

            }

            public int updatePrinterState() throws RemoteException {
                if (this.transaction_table[9] == TRANSACTION_STOP) {
                    throw new InnerPrinterException("this model does not support this method!");
                } else {
                    Parcel _data = Parcel.obtain();
                    Parcel _reply = Parcel.obtain();

                    int _result;
                    try {
                        _data.writeInterfaceToken(DESCRIPTOR);
                        boolean res = this.mRemote.transact(TRANSACTION_updatePrinterState + this.transaction_table[9], _data, _reply, 0);
                        if (!res) {
                            throw new InnerPrinterException("this version does not support this method!");
                        }

                        _reply.readException();
                        _result = _reply.readInt();
                    } finally {
                        _reply.recycle();
                        _data.recycle();
                    }

                    return _result;
                }
            }

            public void sendLCDCommand(int flag) throws RemoteException {
                if (this.transaction_table[14] == TRANSACTION_STOP) {
                    throw new InnerPrinterException("this model does not support this method!");
                } else {
                    Parcel _data = Parcel.obtain();
                    Parcel _reply = Parcel.obtain();

                    try {
                        _data.writeInterfaceToken(DESCRIPTOR);
                        _data.writeInt(flag);
                        boolean res = this.mRemote.transact(TRANSACTION_sendLCDCommand + this.transaction_table[14], _data, _reply, 0);
                        if (!res) {
                            throw new InnerPrinterException("this version does not support this method!");
                        }

                        _reply.readException();
                    } finally {
                        _reply.recycle();
                        _data.recycle();
                    }

                }
            }

            public void sendLCDString(String string, InnerLcdCallback callback) throws RemoteException {
                if (this.transaction_table[15] == TRANSACTION_STOP) {
                    throw new InnerPrinterException("this model does not support this method!");
                } else {
                    Parcel _data = Parcel.obtain();
                    Parcel _reply = Parcel.obtain();

                    try {
                        _data.writeInterfaceToken(DESCRIPTOR);
                        _data.writeString(string);
                        _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                        boolean res = this.mRemote.transact(TRANSACTION_sendLCDString + this.transaction_table[15], _data, _reply, 0);
                        if (!res) {
                            throw new InnerPrinterException("this version does not support this method!");
                        }

                        _reply.readException();
                    } finally {
                        _reply.recycle();
                        _data.recycle();
                    }

                }
            }

            public void sendLCDBitmap(Bitmap bitmap, InnerLcdCallback callback) throws RemoteException {
                if (this.transaction_table[16] == TRANSACTION_STOP) {
                    throw new InnerPrinterException("this model does not support this method!");
                } else {
                    Parcel _data = Parcel.obtain();
                    Parcel _reply = Parcel.obtain();

                    try {
                        _data.writeInterfaceToken(DESCRIPTOR);
                        if (bitmap != null) {
                            _data.writeInt(1);
                            bitmap.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }

                        _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                        boolean res = this.mRemote.transact(TRANSACTION_sendLCDBitmap + this.transaction_table[16], _data, _reply, 0);
                        if (!res) {
                            throw new InnerPrinterException("this version does not support this method!");
                        }

                        _reply.readException();
                    } finally {
                        _reply.recycle();
                        _data.recycle();
                    }

                }
            }

            public int getPrinterMode() throws RemoteException {
                if (this.transaction_table[17] == TRANSACTION_STOP) {
                    throw new InnerPrinterException("this model does not support this method!");
                } else {
                    Parcel _data = Parcel.obtain();
                    Parcel _reply = Parcel.obtain();

                    int _result;
                    try {
                        _data.writeInterfaceToken(DESCRIPTOR);
                        boolean res = this.mRemote.transact(TRANSACTION_getPrinterMode + this.transaction_table[17], _data, _reply, 0);
                        if (!res) {
                            throw new InnerPrinterException("this version does not support this method!");
                        }

                        _reply.readException();
                        _result = _reply.readInt();
                    } finally {
                        _reply.recycle();
                        _data.recycle();
                    }

                    return _result;
                }
            }

            public int getPrinterBBMDistance() throws RemoteException {
                if (this.transaction_table[18] == TRANSACTION_STOP) {
                    throw new InnerPrinterException("this model does not support this method!");
                } else {
                    Parcel _data = Parcel.obtain();
                    Parcel _reply = Parcel.obtain();

                    int _result;
                    try {
                        _data.writeInterfaceToken(DESCRIPTOR);
                        boolean res = this.mRemote.transact(TRANSACTION_getPrinterBBMDistance + this.transaction_table[18], _data, _reply, 0);
                        if (!res) {
                            throw new InnerPrinterException("this version does not support this method!");
                        }

                        _reply.readException();
                        _result = _reply.readInt();
                    } finally {
                        _reply.recycle();
                        _data.recycle();
                    }

                    return _result;
                }
            }

            public void printBitmapCustom(Bitmap bitmap, int type, InnerResultCallback callback) throws RemoteException {
                if (this.transaction_table[20] == TRANSACTION_STOP) {
                    throw new InnerPrinterException("this model does not support this method!");
                } else {
                    Parcel _data = Parcel.obtain();
                    Parcel _reply = Parcel.obtain();

                    try {
                        _data.writeInterfaceToken(DESCRIPTOR);
                        if (bitmap != null) {
                            _data.writeInt(1);
                            bitmap.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }

                        _data.writeInt(type);
                        _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                        boolean res = this.mRemote.transact(TRANSACTION_printBitmapCustom + this.transaction_table[20], _data, _reply, 0);
                        if (!res) {
                            throw new InnerPrinterException("this version does not support this method!");
                        }

                        _reply.readException();
                    } finally {
                        _reply.recycle();
                        _data.recycle();
                    }

                }
            }

            public int getForcedDouble() throws RemoteException {
                if (this.transaction_table[21] == TRANSACTION_STOP) {
                    throw new InnerPrinterException("this model does not support this method!");
                } else {
                    Parcel _data = Parcel.obtain();
                    Parcel _reply = Parcel.obtain();

                    int _result;
                    try {
                        _data.writeInterfaceToken(DESCRIPTOR);
                        boolean res = this.mRemote.transact(TRANSACTION_getForcedDouble + this.transaction_table[21], _data, _reply, 0);
                        if (!res) {
                            throw new InnerPrinterException("this version does not support this method!");
                        }

                        _reply.readException();
                        _result = _reply.readInt();
                    } finally {
                        _reply.recycle();
                        _data.recycle();
                    }

                    return _result;
                }
            }

            public boolean isForcedAntiWhite() throws RemoteException {
                if (this.transaction_table[22] == TRANSACTION_STOP) {
                    throw new InnerPrinterException("this model does not support this method!");
                } else {
                    Parcel _data = Parcel.obtain();
                    Parcel _reply = Parcel.obtain();

                    boolean _result;
                    try {
                        _data.writeInterfaceToken(DESCRIPTOR);
                        boolean res = this.mRemote.transact(TRANSACTION_isForcedAntiWhite + this.transaction_table[22], _data, _reply, 0);
                        if (!res) {
                            throw new InnerPrinterException("this version does not support this method!");
                        }

                        _reply.readException();
                        _result = 0 != _reply.readInt();
                    } finally {
                        _reply.recycle();
                        _data.recycle();
                    }

                    return _result;
                }
            }

            public boolean isForcedBold() throws RemoteException {
                if (this.transaction_table[23] == TRANSACTION_STOP) {
                    throw new InnerPrinterException("this model does not support this method!");
                } else {
                    Parcel _data = Parcel.obtain();
                    Parcel _reply = Parcel.obtain();

                    boolean _result;
                    try {
                        _data.writeInterfaceToken(DESCRIPTOR);
                        boolean res = this.mRemote.transact(TRANSACTION_isForcedBold + this.transaction_table[23], _data, _reply, 0);
                        if (!res) {
                            throw new InnerPrinterException("this version does not support this method!");
                        }

                        _reply.readException();
                        _result = 0 != _reply.readInt();
                    } finally {
                        _reply.recycle();
                        _data.recycle();
                    }

                    return _result;
                }
            }

            public boolean isForcedUnderline() throws RemoteException {
                if (this.transaction_table[24] == TRANSACTION_STOP) {
                    throw new InnerPrinterException("this model does not support this method!");
                } else {
                    Parcel _data = Parcel.obtain();
                    Parcel _reply = Parcel.obtain();

                    boolean _result;
                    try {
                        _data.writeInterfaceToken(DESCRIPTOR);
                        boolean res = this.mRemote.transact(TRANSACTION_isForcedUnderline + this.transaction_table[24], _data, _reply, 0);
                        if (!res) {
                            throw new InnerPrinterException("this version does not support this method!");
                        }

                        _reply.readException();
                        _result = 0 != _reply.readInt();
                    } finally {
                        _reply.recycle();
                        _data.recycle();
                    }

                    return _result;
                }
            }

            public int getForcedRowHeight() throws RemoteException {
                if (this.transaction_table[25] == TRANSACTION_STOP) {
                    throw new InnerPrinterException("this model does not support this method!");
                } else {
                    Parcel _data = Parcel.obtain();
                    Parcel _reply = Parcel.obtain();

                    int _result;
                    try {
                        _data.writeInterfaceToken(DESCRIPTOR);
                        boolean res = this.mRemote.transact(TRANSACTION_getForcedRowHeight + this.transaction_table[25], _data, _reply, 0);
                        if (!res) {
                            throw new InnerPrinterException("this version does not support this method!");
                        }

                        _reply.readException();
                        _result = _reply.readInt();
                    } finally {
                        _reply.recycle();
                        _data.recycle();
                    }

                    return _result;
                }
            }

            public int getFontName() throws RemoteException {
                if (this.transaction_table[26] == TRANSACTION_STOP) {
                    throw new InnerPrinterException("this model does not support this method!");
                } else {
                    Parcel _data = Parcel.obtain();
                    Parcel _reply = Parcel.obtain();

                    int _result;
                    try {
                        _data.writeInterfaceToken(DESCRIPTOR);
                        boolean res = this.mRemote.transact(TRANSACTION_getFontName + this.transaction_table[26], _data, _reply, 0);
                        if (!res) {
                            throw new InnerPrinterException("this version does not support this method!");
                        }

                        _reply.readException();
                        _result = _reply.readInt();
                    } finally {
                        _reply.recycle();
                        _data.recycle();
                    }

                    return _result;
                }
            }

            public void sendLCDDoubleString(String topText, String bottomText, InnerLcdCallback callback) throws RemoteException {
                if (this.transaction_table[27] == TRANSACTION_STOP) {
                    throw new InnerPrinterException("this model does not support this method!");
                } else {
                    Parcel _data = Parcel.obtain();
                    Parcel _reply = Parcel.obtain();

                    try {
                        _data.writeInterfaceToken(DESCRIPTOR);
                        _data.writeString(topText);
                        _data.writeString(bottomText);
                        _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                        boolean res = this.mRemote.transact(TRANSACTION_sendLCDDoubleString + this.transaction_table[27], _data, _reply, 0);
                        if (!res) {
                            throw new InnerPrinterException("this version does not support this method!");
                        }

                        _reply.readException();
                    } finally {
                        _reply.recycle();
                        _data.recycle();
                    }

                }
            }

            public int getPrinterPaper() throws RemoteException {
                if (this.transaction_table[28] == TRANSACTION_STOP) {
                    throw new InnerPrinterException("this model does not support this method!");
                } else {
                    Parcel _data = Parcel.obtain();
                    Parcel _reply = Parcel.obtain();

                    int _result;
                    try {
                        _data.writeInterfaceToken(DESCRIPTOR);
                        boolean res = this.mRemote.transact(TRANSACTION_getPrinterPaper + this.transaction_table[28], _data, _reply, 0);
                        if (!res) {
                            throw new InnerPrinterException("this version does not support this method!");
                        }

                        _reply.readException();
                        _result = _reply.readInt();
                    } finally {
                        _reply.recycle();
                        _data.recycle();
                    }

                    return _result;
                }
            }

            public boolean getDrawerStatus() throws RemoteException {
                if (this.transaction_table[29] == TRANSACTION_STOP) {
                    throw new InnerPrinterException("this model does not support this method!");
                } else {
                    Parcel _data = Parcel.obtain();
                    Parcel _reply = Parcel.obtain();

                    boolean _result;
                    try {
                        _data.writeInterfaceToken(DESCRIPTOR);
                        boolean res = this.mRemote.transact(TRANSACTION_getDrawerStatus + this.transaction_table[29], _data, _reply, 0);
                        if (!res) {
                            throw new InnerPrinterException("this version does not support this method!");
                        }

                        _reply.readException();
                        _result = 0 != _reply.readInt();
                    } finally {
                        _reply.recycle();
                        _data.recycle();
                    }

                    return _result;
                }
            }

            public void sendLCDFillString(String string, int size, boolean fill, InnerLcdCallback callback) throws RemoteException {
                if (this.transaction_table[30] == TRANSACTION_STOP) {
                    throw new InnerPrinterException("this model does not support this method!");
                } else {
                    Parcel _data = Parcel.obtain();
                    Parcel _reply = Parcel.obtain();

                    try {
                        _data.writeInterfaceToken(DESCRIPTOR);
                        _data.writeString(string);
                        _data.writeInt(size);
                        _data.writeInt(fill ? 1 : 0);
                        _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                        boolean res = this.mRemote.transact(TRANSACTION_sendLCDFillString + this.transaction_table[30], _data, _reply, 0);
                        if (!res) {
                            throw new InnerPrinterException("this version does not support this method!");
                        }

                        _reply.readException();
                    } finally {
                        _reply.recycle();
                        _data.recycle();
                    }

                }
            }

            public void sendLCDMultiString(String[] text, int[] align, InnerLcdCallback callback) throws RemoteException {
                if (this.transaction_table[31] == TRANSACTION_STOP) {
                    throw new InnerPrinterException("this model does not support this method!");
                } else {
                    Parcel _data = Parcel.obtain();
                    Parcel _reply = Parcel.obtain();

                    try {
                        _data.writeInterfaceToken(DESCRIPTOR);
                        _data.writeStringArray(text);
                        _data.writeIntArray(align);
                        _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                        boolean res = this.mRemote.transact(TRANSACTION_sendLCDMultiString + this.transaction_table[31], _data, _reply, 0);
                        if (!res) {
                            throw new InnerPrinterException("this version does not support this method!");
                        }

                        _reply.readException();
                    } finally {
                        _reply.recycle();
                        _data.recycle();
                    }

                }
            }

            public int getPrinterDensity() throws RemoteException {
                if (this.transaction_table[32] == TRANSACTION_STOP) {
                    throw new InnerPrinterException("this model does not support this method!");
                } else {
                    Parcel _data = Parcel.obtain();
                    Parcel _reply = Parcel.obtain();

                    int _result;
                    try {
                        _data.writeInterfaceToken(DESCRIPTOR);
                        boolean res = this.mRemote.transact(TRANSACTION_getPrinterDensity + this.transaction_table[32], _data, _reply, 0);
                        if (!res) {
                            throw new InnerPrinterException("this version does not support this method!");
                        }

                        _reply.readException();
                        _result = _reply.readInt();
                    } finally {
                        _reply.recycle();
                        _data.recycle();
                    }

                    return _result;
                }
            }

            public void print2DCode(String data, int symbology, int modulesize, int errorlevel, InnerResultCallback callback) throws RemoteException {
                if (this.transaction_table[33] == TRANSACTION_STOP) {
                    throw new InnerPrinterException("this model does not support this method!");
                } else {
                    Parcel _data = Parcel.obtain();
                    Parcel _reply = Parcel.obtain();

                    try {
                        _data.writeInterfaceToken(DESCRIPTOR);
                        _data.writeString(data);
                        _data.writeInt(symbology);
                        _data.writeInt(modulesize);
                        _data.writeInt(errorlevel);
                        _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                        boolean res = this.mRemote.transact(TRANSACTION_print2DCode + this.transaction_table[33], _data, _reply, 0);
                        if (!res) {
                            throw new InnerPrinterException("this version does not support this method!");
                        }

                        _reply.readException();
                    } finally {
                        _reply.recycle();
                        _data.recycle();
                    }

                }
            }

            public void autoOutPaper(InnerResultCallback callback) throws RemoteException {
                if (this.transaction_table[34] == TRANSACTION_STOP) {
                    throw new InnerPrinterException("this model does not support this method!");
                } else {
                    Parcel _data = Parcel.obtain();
                    Parcel _reply = Parcel.obtain();

                    try {
                        _data.writeInterfaceToken(DESCRIPTOR);
                        _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                        boolean res = this.mRemote.transact(TRANSACTION_autoOutPaper + this.transaction_table[34], _data, _reply, 0);
                        if (!res) {
                            throw new InnerPrinterException("this version does not support this method!");
                        }

                        _reply.readException();
                    } finally {
                        _reply.recycle();
                        _data.recycle();
                    }

                }
            }

            public void setPrinterStyle(int key, int value) throws RemoteException {
                if (this.transaction_table[35] == TRANSACTION_STOP) {
                    throw new InnerPrinterException("this model does not support this method!");
                } else {
                    Parcel _data = Parcel.obtain();
                    Parcel _reply = Parcel.obtain();

                    try {
                        _data.writeInterfaceToken(DESCRIPTOR);
                        _data.writeInt(key);
                        _data.writeInt(value);
                        boolean res = this.mRemote.transact(TRANSACTION_setPrinterStyle + this.transaction_table[35], _data, _reply, 0);
                        if (!res) {
                            throw new InnerPrinterException("this version does not support this method!");
                        }

                        _reply.readException();
                    } finally {
                        _reply.recycle();
                        _data.recycle();
                    }

                }
            }

            public void labelLocate() throws RemoteException {
                if (this.transaction_table[36] == TRANSACTION_STOP) {
                    throw new InnerPrinterException("this model does not support this method!");
                } else {
                    Parcel _data = Parcel.obtain();
                    Parcel _reply = Parcel.obtain();

                    try {
                        _data.writeInterfaceToken(DESCRIPTOR);
                        boolean res = this.mRemote.transact(TRANSACTION_labelLocate + this.transaction_table[36], _data, _reply, 0);
                        if (!res) {
                            throw new InnerPrinterException("this version does not support this method!");
                        }

                        _reply.readException();
                    } finally {
                        _reply.recycle();
                        _data.recycle();
                    }

                }
            }

            public void labelOutput() throws RemoteException {
                if (this.transaction_table[37] == TRANSACTION_STOP) {
                    throw new InnerPrinterException("this model does not support this method!");
                } else {
                    Parcel _data = Parcel.obtain();
                    Parcel _reply = Parcel.obtain();

                    try {
                        _data.writeInterfaceToken(DESCRIPTOR);
                        boolean res = this.mRemote.transact(TRANSACTION_labelOutput + this.transaction_table[37], _data, _reply, 0);
                        if (!res) {
                            throw new InnerPrinterException("this version does not support this method!");
                        }

                        _reply.readException();
                    } finally {
                        _reply.recycle();
                        _data.recycle();
                    }

                }
            }
/////////////////////////////
            /**
             * 1 初始化打印机
             */
            @Override public void initPrinterCallBack(int anInt,InnerResultCallback callback) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(anInt);
                    _data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
                    boolean _status = mRemote.transact(Stub.TRANSACTION_initPrinterCallBack, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            @Override public void initPrinter(int anInt) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(anInt);
                    boolean _status = mRemote.transact(Stub.TRANSACTION_initPrinter, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            /**
             * 2 获取打印机状态
             * 返回：
             */
            @Override public int getPrinterStatusCallBack(int anInt, InnerResultCallback callback) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                int _result;
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(anInt);
                    _data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
                    boolean _status = mRemote.transact(Stub.TRANSACTION_getPrinterStatusCallBack, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                    _result = _reply.readInt();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
                return _result;
            }
            /**
             *  获取打印机状态
             *
             */
            @Override public int getPrinterStatus(int anInt) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                int _result;
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(anInt);
                    boolean _status = mRemote.transact(Stub.TRANSACTION_getPrinterStatus, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                    _result = _reply.readInt();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
                return _result;
            }
            /**
             * 3 走纸
             * 返回：
             */
            @Override public void printAndLineFeed() throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    boolean _status = mRemote.transact(Stub.TRANSACTION_printAndLineFeed, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             * 走纸
             * 返回：
             */
            @Override public void printAndLineFeedCallBack(InnerResultCallback callback) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
                    boolean _status = mRemote.transact(Stub.TRANSACTION_printAndLineFeedCallBack, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             * 4 走纸 指定高度
             * 返回：
             */
            @Override public void printAndFeedPaperCallBack(int anInt, InnerResultCallback callback) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(anInt);
                    _data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
                    boolean _status = mRemote.transact(Stub.TRANSACTION_printAndFeedPaperCallBack, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             * 走纸 指定高度
             * 返回：
             */
            @Override public void printAndFeedPaper(int anInt) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(anInt);
                    boolean _status = mRemote.transact(Stub.TRANSACTION_printAndFeedPaper, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             * 5 切纸
             * 返回：
             */
            @Override public void partialCut() throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    boolean _status = mRemote.transact(Stub.TRANSACTION_partialCut, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             * 切纸
             * 返回：
             */
            @Override public void partialCutCallBack(InnerResultCallback callback) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
                    boolean _status = mRemote.transact(Stub.TRANSACTION_partialCutCallBack, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             * 6 设置文本对齐方式
             * 返回：
             */
            @Override public void setAlignmentCallBack(int anInt, InnerResultCallback callback) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(anInt);
                    _data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
                    boolean _status = mRemote.transact(Stub.TRANSACTION_setAlignmentCallBack, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             * 设置文本对齐方式
             * 返回：
             */
            @Override public void setAlignment(int anInt) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(anInt);
                    boolean _status = mRemote.transact(Stub.TRANSACTION_setAlignment, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             * 7 设置文本字体大小
             * 返回：
             */
            @Override public void setTextSizeCallBack(int anInt, InnerResultCallback callback) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(anInt);
                    _data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
                    boolean _status = mRemote.transact(Stub.TRANSACTION_setTextSizeCallBack, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             * 设置文本字体大小
             * 返回：
             */
            @Override public void setTextSize(int anInt) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(anInt);
                    boolean _status = mRemote.transact(Stub.TRANSACTION_setTextSize, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             * 8 设置字体
             * 返回：
             */
            @Override public void setTextTypefaceCallBack(int anInt, InnerResultCallback callback) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(anInt);
                    _data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
                    boolean _status = mRemote.transact(Stub.TRANSACTION_setTextTypefaceCallBack, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             * 设置字体
             * 返回：
             */
            @Override public void setTextTypeface(int anInt) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(anInt);
                    boolean _status = mRemote.transact(Stub.TRANSACTION_setTextTypeface, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             * 9 设置字体样式
             * 返回：
             */
            @Override public void setTextStyleCallBack(int anInt, InnerResultCallback callback) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(anInt);
                    _data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
                    boolean _status = mRemote.transact(Stub.TRANSACTION_setTextStyleCallBack, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             * 设置字体样式
             * 返回：
             */
            @Override public void setTextStyle(int anInt) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(anInt);
                    boolean _status = mRemote.transact(Stub.TRANSACTION_setTextStyle, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             * 10 设置行间距
             * 返回：
             */
            @Override public void setTextLineSpacingCallBack(int anInt, InnerResultCallback callback) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(anInt);
                    _data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
                    boolean _status = mRemote.transact(Stub.TRANSACTION_setTextLineSpacingCallBack, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             * 设置行间距
             * 返回：
             */
            @Override public void setTextLineSpacing(int anInt) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(anInt);
                    boolean _status = mRemote.transact(Stub.TRANSACTION_setTextLineSpacing, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             * 11 设置打印宽度
             * 返回：
             */
            @Override public void setTextWidthCallBack(int anInt, InnerResultCallback callback) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(anInt);
                    _data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
                    boolean _status = mRemote.transact(Stub.TRANSACTION_setTextWidthCallBack, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             * 设置打印宽度
             * 返回：
             */
            @Override public void setTextWidth(int anInt) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(anInt);
                    boolean _status = mRemote.transact(Stub.TRANSACTION_setTextWidth, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             * 12 打印文本
             * 返回：
             */
            @Override public void printTextCallBack(String text, InnerResultCallback callback) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeString(text);
                    _data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
                    boolean _status = mRemote.transact(Stub.TRANSACTION_printTextCallBack, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             * 打印文本
             * 返回：
             */
            @Override public void printText(String text) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeString(text);
                    boolean _status = mRemote.transact(Stub.TRANSACTION_printText, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             * 13 打印文本,对齐方式
             * 返回：
             */
            @Override public void printTextWithAliCallBack(String text, int anInt, InnerResultCallback callback) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeString(text);
                    _data.writeInt(anInt);
                    _data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
                    boolean _status = mRemote.transact(Stub.TRANSACTION_printTextWithAliCallBack, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             * 打印文本 ,对齐方式
             * 返回：
             */
            @Override public void printTextWithAli(String text, int anInt) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeString(text);
                    _data.writeInt(anInt);
                    boolean _status = mRemote.transact(Stub.TRANSACTION_printTextWithAli, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             * 14 打印表格的一行，可以指定列宽、对齐方式
             * colsTextArr：   各列文本字符串数组
             * colsWidthArr：  各列宽度数组(以英文字符计算, 每个中文字符占两个英文字符, 每个宽度大于0)
             * colsAlign：     各列对齐方式(0居左, 1居中, 2居右)
             * 备注: 三个参数的数组长度应该一致, 如果colsText[i]的宽度大于colsWidth[i], 则文本换行
             */
            @Override public void printColumnsTextCallBack(String[] colsTextArr, int[] colsWidthArr, int[] colsAlign,int[] size, InnerResultCallback callback) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeStringArray(colsTextArr);
                    _data.writeIntArray(colsWidthArr);
                    _data.writeIntArray(colsAlign);
                    _data.writeIntArray(size);
                    _data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
                    boolean _status = mRemote.transact(Stub.TRANSACTION_printColumnsTextCallBack, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             * 打印表格的一行，可以指定列宽、对齐方式
             * colsTextArr：   各列文本字符串数组
             * colsWidthArr：  各列宽度数组(以英文字符计算, 每个中文字符占两个英文字符, 每个宽度大于0)
             * colsAlign：     各列对齐方式(0居左, 1居中, 2居右)
             * 备注: 三个参数的数组长度应该一致, 如果colsText[i]的宽度大于colsWidth[i], 则文本换行
             */
            @Override public void printColumnsText(String[] colsTextArr, int[] colsWidthArr, int[] colsAlign,int[] size) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeStringArray(colsTextArr);
                    _data.writeIntArray(colsWidthArr);
                    _data.writeIntArray(colsAlign);
                    _data.writeIntArray(size);
                    boolean _status = mRemote.transact(Stub.TRANSACTION_printColumnsTextWithSize, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             * 15 设置条形码宽度
             * 返回：
             */
            @Override public void setBarCodeWidthCallBack(int anInt, InnerResultCallback callback) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(anInt);
                    _data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
                    boolean _status = mRemote.transact(Stub.TRANSACTION_setBarCodeWidthCallBack, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             * 设置条形码宽度
             * 返回：
             */
            @Override public void setBarCodeWidth(int anInt) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(anInt);
                    boolean _status = mRemote.transact(Stub.TRANSACTION_setBarCodeWidth, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             * 16 设置条形码的高度
             * 返回：
             */
            @Override public void setBarCodeHeightCallBack(int anInt, InnerResultCallback callback) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(anInt);
                    _data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
                    boolean _status = mRemote.transact(Stub.TRANSACTION_setBarCodeHeightCallBack, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             * 设置条形码的高度
             * 返回：
             */
            @Override public void setBarCodeHeight(int anInt) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(anInt);
                    boolean _status = mRemote.transact(Stub.TRANSACTION_setBarCodeHeight, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             * 17 打印条形码时，请选择HRI字符的打印位置
             * 返回：
             */
            @Override public void setBarCodeContentPrintPosCallBack(int anInt, InnerResultCallback callback) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(anInt);
                    _data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
                    boolean _status = mRemote.transact(Stub.TRANSACTION_setBarCodeContentPrintPosCallBack, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             * 打印条形码时，请选择HRI字符的打印位置
             * 返回：
             */
            @Override public void setBarCodeContentPrintPos(int anInt) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(anInt);
                    boolean _status = mRemote.transact(Stub.TRANSACTION_setBarCodeContentPrintPos, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             * 18 打印条形码
             * 返回：
             */
            @Override public void printBarCodeCallBack(int anInt, String data, InnerResultCallback callback) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(anInt);
                    _data.writeString(data);
                    _data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
                    boolean _status = mRemote.transact(Stub.TRANSACTION_printBarCodeCallBack, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             * 打印条形码
             * 返回：
             */
            @Override public void printBarCode(int anInt, String data) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(anInt);
                    _data.writeString(data);
                    boolean _status = mRemote.transact(Stub.TRANSACTION_printBarCode, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             * 19 打印条形码,对齐方式
             * 返回：
             */
            @Override public void printBarCodeWithAlignCallBack(int anInt, String data, int alignments, InnerResultCallback callback) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(anInt);
                    _data.writeString(data);
                    _data.writeInt(alignments);
                    _data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
                    boolean _status = mRemote.transact(Stub.TRANSACTION_printBarCodeWithAlignCallBack, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             * 打印条形码,对齐方式
             * 返回：
             */
            @Override public void printBarCodeWithAlign(int anInt, String data, int alignments) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(anInt);
                    _data.writeString(data);
                    _data.writeInt(alignments);
                    boolean _status = mRemote.transact(Stub.TRANSACTION_printBarCodeWithAlign, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             * 20 设置二维码的大小
             * 返回：
             */
            @Override public void setQrCodeSizeCallBack(int anInt, InnerResultCallback callback) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(anInt);
                    _data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
                    boolean _status = mRemote.transact(Stub.TRANSACTION_setQrCodeSizeCallBack, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             * 设置二维码的大小
             * 返回：
             */
            @Override public void setQrCodeSize(int anInt) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(anInt);
                    boolean _status = mRemote.transact(Stub.TRANSACTION_setQrCodeSize, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             * 21 设置二维码错误更正
             * 返回：
             */
            @Override public void setQrCodeErrorCorrectionLevCallBack(int anInt, InnerResultCallback callback) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(anInt);
                    _data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
                    boolean _status = mRemote.transact(Stub.TRANSACTION_setQrCodeErrorCorrectionLevCallBack, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             * 设置二维码错误更正
             * 返回：
             */
            @Override public void setQrCodeErrorCorrectionLev(int anInt) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(anInt);
                    boolean _status = mRemote.transact(Stub.TRANSACTION_setQrCodeErrorCorrectionLev, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             22 设置条形码和二维码的左边距
             * 返回：
             */
            @Override public void setLeftMarginCallBack(int anInt, InnerResultCallback callback) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(anInt);
                    _data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
                    boolean _status = mRemote.transact(Stub.TRANSACTION_setLeftMarginCallBack, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             * 设置条形码和二维码的左边距
             * 返回：
             */
            @Override public void setLeftMargin(int anInt) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(anInt);
                    boolean _status = mRemote.transact(Stub.TRANSACTION_setLeftMargin, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             23 打印机二维码
             * 返回：
             */
            @Override public void printQrCodeCallBack(String data, InnerResultCallback callback) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeString(data);
                    _data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
                    boolean _status = mRemote.transact(Stub.TRANSACTION_printQrCodeCallBack, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             *打印机二维码
             * 返回：
             */
            @Override public void printQrCode(String data) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeString(data);
                    boolean _status = mRemote.transact(Stub.TRANSACTION_printQrCode, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             24 打印机二维码,对齐方式
             * 返回：
             */
            @Override public void printQrCodeWithAlignCallBack(String data, int alignments, InnerResultCallback callback) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeString(data);
                    _data.writeInt(alignments);
                    _data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
                    boolean _status = mRemote.transact(Stub.TRANSACTION_printQrCodeWithAlignCallBack, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             *打印机二维码,对齐方式
             * 返回：
             */
            @Override public void printQrCodeWithAlign(String data, int alignments) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeString(data);
                    _data.writeInt(alignments);
                    boolean _status = mRemote.transact(Stub.TRANSACTION_printQrCodeWithAlign, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             25 设定纸张规格
             * 返回：
             */
            @Override public void setPageFormatCallBack(int anInt, InnerResultCallback callback) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(anInt);
                    _data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
                    boolean _status = mRemote.transact(Stub.TRANSACTION_setPageFormatCallBack, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             * 设定纸张规格
             * 返回：
             */
            @Override public void setPageFormat(int anInt) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(anInt);
                    boolean _status = mRemote.transact(Stub.TRANSACTION_setPageFormat, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             26 打印机图片
             * 返回：
             */
            @Override public void printSingleBitmapCallBack(Bitmap bitmap, InnerResultCallback callback) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    if ((bitmap!=null)) {
                        _data.writeInt(1);
                        bitmap.writeToParcel(_data, 0);
                    }
                    else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
                    boolean _status = mRemote.transact(Stub.TRANSACTION_printSingleBitmapCallBack, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             *打印机图片
             * 返回：
             */
            @Override public void printSingleBitmap(Bitmap bitmap) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    if ((bitmap!=null)) {
                        _data.writeInt(1);
                        bitmap.writeToParcel(_data, 0);
                    }
                    else {
                        _data.writeInt(0);
                    }
                    boolean _status = mRemote.transact(Stub.TRANSACTION_printSingleBitmap, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             27 打印机图片,对齐方式
             * 返回：
             */
            @Override public void printSingleBitmapWithAlignCallBack(Bitmap bitmap, int alignments, InnerResultCallback callback) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    if ((bitmap!=null)) {
                        _data.writeInt(1);
                        bitmap.writeToParcel(_data, 0);
                    }
                    else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(alignments);
                    _data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
                    boolean _status = mRemote.transact(Stub.TRANSACTION_printSingleBitmapWithAlignCallBack, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             *打印机图片
             * 返回：
             */
            @Override public void printSingleBitmapWithAlign(Bitmap bitmap, int alignments) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    if ((bitmap!=null)) {
                        _data.writeInt(1);
                        bitmap.writeToParcel(_data, 0);
                    }
                    else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(alignments);
                    boolean _status = mRemote.transact(Stub.TRANSACTION_printSingleBitmapWithAlign, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             28 打印多个位图
             * 返回：
             */
            @Override public void printMultiBitmapCallBack(List<Bitmap> bitmaps, InnerResultCallback callback) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeTypedList(bitmaps);
                    _data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
                    boolean _status = mRemote.transact(Stub.TRANSACTION_printMultiBitmapCallBack, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             *打印多个位图
             * 返回：
             */
            @Override public void printMultiBitmap(List<Bitmap> bitmap) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeTypedList(bitmap);
                    boolean _status = mRemote.transact(Stub.TRANSACTION_printMultiBitmap, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             29 打印多个位图
             * 返回：
             */
            @Override public void printMultiBitmapWithAlignCallBack(List<Bitmap> bitmaps, int alignments, InnerResultCallback callback) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeTypedList(bitmaps);
                    _data.writeInt(alignments);
                    _data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
                    boolean _status = mRemote.transact(Stub.TRANSACTION_printMultiBitmapWithAlignCallBack, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             *打印多个位图
             * 返回：
             */
            @Override public void printMultiBitmapWithAlign(List<Bitmap> bitmap, int alignments) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeTypedList(bitmap);
                    _data.writeInt(alignments);
                    boolean _status = mRemote.transact(Stub.TRANSACTION_printMultiBitmapWithAlign, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             30 重置连接
             * 返回：
             */
            @Override public void resetDeviceCallBack(InnerResultCallback callback) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
                    boolean _status = mRemote.transact(Stub.TRANSACTION_resetDeviceCallBack, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             *重置连接
             * 返回：
             */
            @Override public void resetDevice() throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    boolean _status = mRemote.transact(Stub.TRANSACTION_resetDevice, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             31 全切 切纸
             * 返回：
             */
            @Override public void fullCutCallBack(InnerResultCallback callback) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
                    boolean _status = mRemote.transact(Stub.TRANSACTION_fullCutCallBack, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             *全切 切纸
             * 返回：
             */
            @Override public void fullCut() throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    boolean _status = mRemote.transact(Stub.TRANSACTION_fullCut, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             32  发送指令
             * 返回：
             */
            @Override public void sendRAWDataCallBack(int[] ints, InnerResultCallback callback) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeIntArray(ints);
                    _data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
                    boolean _status = mRemote.transact(Stub.TRANSACTION_sendRAWDataCallBack, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             *全切 切纸
             * 返回：
             */
            @Override public void sendRAWData(int[] ints) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeIntArray(ints);
                    boolean _status = mRemote.transact(Stub.TRANSACTION_sendRAWData, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             33  发送指令
             * 返回：
             */
            @Override public void sendRAWDataStringCallBack(String hex, InnerResultCallback callback) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeString(hex);
                    _data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
                    boolean _status = mRemote.transact(Stub.TRANSACTION_sendRAWDataStringCallBack, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             *全切 切纸
             * 返回：
             */
            @Override public void sendRAWDataString(String hex) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeString(hex);
                    boolean _status = mRemote.transact(Stub.TRANSACTION_sendRAWDataString, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             34  发送指令
             * 返回：
             */
            @Override public void sendRAWDataByteCallBack(byte[] bytes, InnerResultCallback callback) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeByteArray(bytes);
                    _data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
                    boolean _status = mRemote.transact(Stub.TRANSACTION_sendRAWDataByteCallBack, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             *全切 切纸
             * 返回：
             */
            @Override public void sendRAWDataByte(byte[] bytes) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeByteArray(bytes);
                    boolean _status = mRemote.transact(Stub.TRANSACTION_sendRAWDataByte, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             35  设置打印文字是否 下划线
             * 返回：
             */
            @Override public void setUnderlineCallBack(boolean haveUnderline, InnerResultCallback callback) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(((haveUnderline)?(1):(0)));
                    _data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
                    boolean _status = mRemote.transact(Stub.TRANSACTION_setUnderlineCallBack, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             *全切 切纸
             * 返回：
             */
            @Override public void setUnderline(boolean haveUnderline) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(((haveUnderline)?(1):(0)));
                    boolean _status = mRemote.transact(Stub.TRANSACTION_setUnderline, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             35  设置打印文字是否 加粗
             * 返回：
             */
            @Override public void sethaveBoldCallBack(boolean haveBold, InnerResultCallback callback) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(((haveBold)?(1):(0)));
                    _data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
                    boolean _status = mRemote.transact(Stub.TRANSACTION_sethaveBoldCallBack, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             *全切 切纸
             * 返回：
             */
            @Override public void sethaveBold(boolean haveBold) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(((haveBold)?(1):(0)));
                    boolean _status = mRemote.transact(Stub.TRANSACTION_sethaveBold, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             36  设置打印文字 行高
             * 返回：
             */
            @Override public void setHaveLineHeightCallBack(float lineHeightRatio, InnerResultCallback callback) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeFloat(lineHeightRatio);
                    _data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
                    boolean _status = mRemote.transact(Stub.TRANSACTION_setHaveLineHeightCallBack, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             *全切 切纸
             * 返回：
             */
            @Override public void setHaveLineHeight(float lineHeightRatio) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeFloat(lineHeightRatio);
                    boolean _status = mRemote.transact(Stub.TRANSACTION_setHaveLineHeight, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             37  发送指令
             * 返回：
             */
            @Override public void printerByteWithByteCallBack(byte[] bytes, InnerResultCallback callback) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeByteArray(bytes);
                    _data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
                    boolean _status = mRemote.transact(Stub.TRANSACTION_printerByteWithByteCallBack, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             *全切 切纸
             * 返回：
             */
            @Override public void printerByteWithByte(byte[] bytes) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeByteArray(bytes);
                    boolean _status = mRemote.transact(Stub.TRANSACTION_printerByteWithByte, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             37  发送指令
             * 返回：
             */
            @Override public void printerByteCallBack(InnerResultCallback callback) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
                    boolean _status = mRemote.transact(Stub.TRANSACTION_printerByteCallBack, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             *全切 切纸
             * 返回：
             */
            @Override public void printerByte() throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    boolean _status = mRemote.transact(Stub.TRANSACTION_printerByte, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             38  双qr大小
             * 返回：
             */
            @Override public void setDoubleQRSizeCallBack(int size, InnerResultCallback callback) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(size);
                    _data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
                    boolean _status = mRemote.transact(Stub.TRANSACTION_setDoubleQRSizeCallBack, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             * 双qr大小
             * 返回：
             */
            @Override public void setDoubleQRSize(int size) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(size);
                    boolean _status = mRemote.transact(Stub.TRANSACTION_setDoubleQRSize, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             39  双qr 左边边距
             * 返回：
             */
            @Override public void setDoubleQR1MarginLeftCallBack(int left, InnerResultCallback callback) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(left);
                    _data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
                    boolean _status = mRemote.transact(Stub.TRANSACTION_setDoubleQR1MarginLeftCallBack, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             *双qr 左边边距
             * 返回：
             */
            @Override public void setDoubleQR1MarginLeft(int left) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(left);
                    boolean _status = mRemote.transact(Stub.TRANSACTION_setDoubleQR1MarginLeft, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             40  双qr 左边边距
             * 返回：
             */
            @Override public void setDoubleQR2MarginLeftCallBack(int left, InnerResultCallback callback) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(left);
                    _data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
                    boolean _status = mRemote.transact(Stub.TRANSACTION_setDoubleQR2MarginLeftCallBack, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             *双qr 左边边距
             * 返回：
             */
            @Override public void setDoubleQR2MarginLeft(int left) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(left);
                    boolean _status = mRemote.transact(Stub.TRANSACTION_setDoubleQR2MarginLeft, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             41  双qr 误差
             * 返回：
             */
            @Override public void setDoubleQR1LevelCallBack(int level, InnerResultCallback callback) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(level);
                    _data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
                    boolean _status = mRemote.transact(Stub.TRANSACTION_setDoubleQR1LevelCallBack, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             * 双qr 误差
             * 返回：
             */
            @Override public void setDoubleQR1Level(int level) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(level);
                    boolean _status = mRemote.transact(Stub.TRANSACTION_setDoubleQR1Level, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             42  双qr 误差
             * 返回：
             */
            @Override public void setDoubleQR2LevelCallBack(int level, InnerResultCallback callback) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(level);
                    _data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
                    boolean _status = mRemote.transact(Stub.TRANSACTION_setDoubleQR2LevelCallBack, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             * 双qr 误差
             * 返回：
             */
            @Override public void setDoubleQR2Level(int level) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(level);
                    boolean _status = mRemote.transact(Stub.TRANSACTION_setDoubleQR2Level, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             43  双qr 版本
             * 返回：
             */
            @Override public void setDoubleQR1VersionCallBack(int version, InnerResultCallback callback) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(version);
                    _data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
                    boolean _status = mRemote.transact(Stub.TRANSACTION_setDoubleQR1VersionCallBack, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             * 双qr 版本
             * 返回：
             */
            @Override public void setDoubleQR1Version(int version) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(version);
                    boolean _status = mRemote.transact(Stub.TRANSACTION_setDoubleQR1Version, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             44  双qr 版本
             * 返回：
             */
            @Override public void setDoubleQR2VersionCallBack(int version, InnerResultCallback callback) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(version);
                    _data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
                    boolean _status = mRemote.transact(Stub.TRANSACTION_setDoubleQR2VersionCallBack, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             * 双qr 版本
             * 返回：
             */
            @Override public void setDoubleQR2Version(int version) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(version);
                    boolean _status = mRemote.transact(Stub.TRANSACTION_setDoubleQR2Version, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             45 打印双QR
             * 返回：
             */
            @Override public void printDoubleQRCallBack(String qr1,String qr2, InnerResultCallback callback) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeString(qr1);
                    _data.writeString(qr2);
                    _data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
                    boolean _status = mRemote.transact(Stub.TRANSACTION_printDoubleQRCallBack, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             * 打印双QR
             * 返回：
             */
            @Override public void printDoubleQR(String qr1,String qr2) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeString(qr1);
                    _data.writeString(qr2);
                    boolean _status = mRemote.transact(Stub.TRANSACTION_printDoubleQR, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             46 初始化参数
             * 返回：
             */
            @Override public void initParamsCallBack(InnerResultCallback callback) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
                    boolean _status = mRemote.transact(Stub.TRANSACTION_initParamsCallBack, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             * 初始化参数
             * 返回：
             */
            @Override public void initParams() throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    boolean _status = mRemote.transact(Stub.TRANSACTION_initParams, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             47 初始化参数
             * 返回：
             */
            @Override public void initParamsIsClearCallBack(boolean isClearCache, InnerResultCallback callback) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(((isClearCache)?(1):(0)));
                    _data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
                    boolean _status = mRemote.transact(Stub.TRANSACTION_initParamsIsClearCallBack, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             * 初始化参数
             * 返回：
             */
            @Override public void initParamsIsClear(boolean isClearCache) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(((isClearCache)?(1):(0)));
                    boolean _status = mRemote.transact(Stub.TRANSACTION_initParamsIsClear, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             * 48 获取全局⾏⾼设定值
             * 返回：
             */
            @Override public int getForcedRowHeightCallBack(InnerResultCallback callback) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                int _result;
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
                    boolean _status = mRemote.transact(Stub.TRANSACTION_getForcedRowHeightCallBack, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                    _result = _reply.readInt();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
                return _result;
            }
            /**
             *  获取全局⾏⾼设定值
             *
             */
//      @Override public int getForcedRowHeight() throws android.os.RemoteException
//      {
//        android.os.Parcel _data = android.os.Parcel.obtain();
//        android.os.Parcel _reply = android.os.Parcel.obtain();
//        int _result;
//        try {
//          _data.writeInterfaceToken(DESCRIPTOR);
//          boolean _status = mRemote.transact(Stub.TRANSACTION_getForcedRowHeight, _data, _reply, 0);
//          if (!_status ) {
//            throw new InnerPrinterException("this version does not support this method!");
//          }
//          _reply.readException();
//          _result = _reply.readInt();
//        }
//        finally {
//          _reply.recycle();
//          _data.recycle();
//        }
//        return _result;
//      }
            /**
             * 49 获取全局字体加粗样式使能
             * 返回：
             */
            @Override public boolean isForcedBoldCallBack(InnerResultCallback callback) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                boolean _result;
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
                    boolean _status = mRemote.transact(Stub.TRANSACTION_isForcedBoldCallBack, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                    _result = (0!=_reply.readInt());
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
                return _result;
            }
            /**
             *  获取全局⾏⾼设定值
             *
             */
//      @Override public boolean isForcedBold() throws android.os.RemoteException
//      {
//        android.os.Parcel _data = android.os.Parcel.obtain();
//        android.os.Parcel _reply = android.os.Parcel.obtain();
//        boolean _result;
//        try {
//          _data.writeInterfaceToken(DESCRIPTOR);
//          boolean _status = mRemote.transact(Stub.TRANSACTION_isForcedBold, _data, _reply, 0);
//          if (!_status ) {
//           throw new InnerPrinterException("this version does not support this method!");
//          }
//          _reply.readException();
//          _result = (0!=_reply.readInt());
//        }
//        finally {
//          _reply.recycle();
//          _data.recycle();
//        }
//        return _result;
//      }
            /**
             * 50 获取全局字体下划线样式使能
             * 返回：
             */
            @Override public boolean isForcedUnderlineCallBack(InnerResultCallback callback) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                boolean _result;
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
                    boolean _status = mRemote.transact(Stub.TRANSACTION_isForcedUnderlineCallBack, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                    _result = (0!=_reply.readInt());
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
                return _result;
            }
            /**
             *  获取全局字体下划线样式使能
             *
             */
//      @Override public boolean isForcedUnderline() throws android.os.RemoteException
//      {
//        android.os.Parcel _data = android.os.Parcel.obtain();
//        android.os.Parcel _reply = android.os.Parcel.obtain();
//        boolean _result;
//        try {
//          _data.writeInterfaceToken(DESCRIPTOR);
//          boolean _status = mRemote.transact(Stub.TRANSACTION_isForcedUnderline, _data, _reply, 0);
//          if (!_status ) {
//            return getDefaultImpl().isForcedUnderline();
//          }
//          _reply.readException();
//          _result = (0!=_reply.readInt());
//        }
//        finally {
//          _reply.recycle();
//          _data.recycle();
//        }
//        return _result;
//      }
            /**
             * 51 获取打印浓度
             * 返回：
             */
            @Override public int getPrinterDensityCallBack(InnerResultCallback callback) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                int _result;
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
                    boolean _status = mRemote.transact(Stub.TRANSACTION_getPrinterDensityCallBack, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                    _result = _reply.readInt();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
                return _result;
            }
            /**
             *  获取全局字体下划线样式使能
             *
             */
//      @Override public int getPrinterDensity() throws android.os.RemoteException
//      {
//        android.os.Parcel _data = android.os.Parcel.obtain();
//        android.os.Parcel _reply = android.os.Parcel.obtain();
//        int _result;
//        try {
//          _data.writeInterfaceToken(DESCRIPTOR);
//          boolean _status = mRemote.transact(Stub.TRANSACTION_getPrinterDensity, _data, _reply, 0);
//          if (!_status ) {
//            return getDefaultImpl().getPrinterDensity();
//          }
//          _reply.readException();
//          _result = _reply.readInt();
//        }
//        finally {
//          _reply.recycle();
//          _data.recycle();
//        }
//        return _result;
//      }
            /**
             *  蓝牙打印初始化
             *
             */
            @Override public void initBluePrinterCallBack(int anInt, BluetoothDevice device, InnerResultCallback callback) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(anInt);
                    if ((device!=null)) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    }
                    else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
                    boolean _status = mRemote.transact(Stub.TRANSACTION_initBluePrinterCallBack, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
            /**
             *  蓝牙打印初始化
             *
             */
            @Override public void initBluePrinter(int anInt, BluetoothDevice device) throws RemoteException
            {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(anInt);
                    if ((device!=null)) {
                        _data.writeInt(1);
                        device.writeToParcel(_data, 0);
                    }
                    else {
                        _data.writeInt(0);
                    }
                    boolean _status = mRemote.transact(Stub.TRANSACTION_initBluePrinter, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override
            public void printBMPBitmap(Bitmap bitmap) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);

                    if ((bitmap!=null)) {
                        _data.writeInt(1);
                        bitmap.writeToParcel(_data, 0);
                    }
                    else {
                        _data.writeInt(0);
                    }
                    boolean _status = mRemote.transact(Stub.TRANSACTION_printBMPBitmap, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override
            public void printBMPBitmapCallBack(Bitmap bitmap, ICallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    if ((bitmap!=null)) {
                        _data.writeInt(1);
                        bitmap.writeToParcel(_data, 0);
                    }
                    else {
                        _data.writeInt(0);
                    }
                    _data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
                    boolean _status = mRemote.transact(Stub.TRANSACTION_printBMPBitmapCallBack, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override
            public void printNvBitmap(int anInt) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(anInt);

                    boolean _status = mRemote.transact(Stub.TRANSACTION_printNvBitmap, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override
            public void printNvBitmapCallBack(int anInt, ICallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeInt(anInt);

                    _data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
                    boolean _status = mRemote.transact(Stub.TRANSACTION_printNvBitmapCallBack, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override
            public boolean setDownloadNvBmp(String loadPath) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                boolean _result;
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeString(loadPath);

                    boolean _status = mRemote.transact(Stub.TRANSACTION_setDownloadNvBmp, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                    _result = (0!=_reply.readInt());
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
                return _result;
            }

            @Override
            public boolean setDownloadNvBmpCallBack(String loadPath, ICallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                boolean _result;
                try {
                    _data.writeInterfaceToken(DESCRIPTOR);
                    _data.writeString(loadPath);

                    _data.writeStrongBinder((((callback!=null))?(callback.asBinder()):(null)));
                    boolean _status = mRemote.transact(Stub.TRANSACTION_setDownloadNvBmpCallBack, _data, _reply, 0);
                    if (!_status ) {
                        throw new InnerPrinterException("this version does not support this method!");
                    }
                    _reply.readException();
                    _result = (0!=_reply.readInt());
                }
                finally {
                    _reply.recycle();
                    _data.recycle();
                }
                return _result;
            }

////////////////////////////////

        }
    }
}