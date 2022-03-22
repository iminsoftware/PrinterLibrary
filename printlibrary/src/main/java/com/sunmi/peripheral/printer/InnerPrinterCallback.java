package com.sunmi.peripheral.printer;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

public abstract class InnerPrinterCallback implements ServiceConnection {
    public InnerPrinterCallback() {
    }

    protected abstract void onConnected(SunmiPrinterService service);

    protected abstract void onDisconnected();

    public void onServiceConnected(ComponentName name, IBinder service) {
        if (service != null) {
            SunmiPrinterService proxy = SunmiPrinterService.Stub.asInterface(service);
            this.onConnected(proxy);
        }

    }

    public void onServiceDisconnected(ComponentName name) {
        this.onDisconnected();
    }
}

