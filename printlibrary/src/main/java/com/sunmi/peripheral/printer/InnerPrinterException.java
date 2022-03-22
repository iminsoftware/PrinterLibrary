package com.sunmi.peripheral.printer;

import android.os.RemoteException;

public class InnerPrinterException extends RemoteException {
    public InnerPrinterException(String message) {
        super(message);
    }
}
