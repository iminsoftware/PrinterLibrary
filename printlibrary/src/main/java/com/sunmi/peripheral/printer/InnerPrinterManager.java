package com.sunmi.peripheral.printer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.RemoteException;
import android.util.Log;

import java.util.Locale;

public class InnerPrinterManager {
    private InnerPrinterManager() {
    }

    public static InnerPrinterManager getInstance() {
        return InnerPrinterManager.SingletonContainer.instance;
    }

    @SuppressLint("WrongConstant")
    public boolean bindService(Context mContext, InnerPrinterCallback callback) throws InnerPrinterException {
        if (mContext != null && callback != null) {
            Intent intent = new Intent();
            intent.setPackage("woyou.aidlservice.jiuiv5");
            intent.setAction("woyou.aidlservice.jiuiv5.IWoyouService");
            Log.d("test_1_11", "ret ======>绑定服务");
            return mContext.getApplicationContext().bindService(intent, callback, 1);
        } else {
            throw new InnerPrinterException("parameter must be not null!");
        }
    }

    public void unBindService(Context mContext, InnerPrinterCallback callback) throws InnerPrinterException {
        if (mContext != null && callback != null) {
            mContext.getApplicationContext().unbindService(callback);
        } else {
            throw new InnerPrinterException("parameter must be not null!");
        }
    }

    public boolean hasPrinter(SunmiPrinterService sunmiPrinterService) throws InnerPrinterException {
        if (sunmiPrinterService == null) {
            throw new InnerPrinterException("printer service does not exist!");
        } else {
            try {
                int status = sunmiPrinterService.updatePrinterState();
                Log.d("test_1_11", "客户端获取打印机状态-----》"+status);
                return status != 0 && status != 505;
            } catch (RemoteException var3) {
                return Build.SERIAL.toUpperCase(Locale.ENGLISH).contains("V1");
            }
        }
    }

    private static class SingletonContainer {
        private static InnerPrinterManager instance = new InnerPrinterManager();

        private SingletonContainer() {
        }
    }
}

