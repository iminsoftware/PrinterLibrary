package com.sunmi.peripheral.printer;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ILcdCallback extends IInterface {
    void onRunResult(boolean show) throws RemoteException;

    public abstract static class Stub extends Binder implements ILcdCallback {
        private static final String DESCRIPTOR = "woyou.aidlservice.jiuiv5.ILcdCallback";
        static final int TRANSACTION_onRunResult = 1;

        public Stub() {
            this.attachInterface(this, "woyou.aidlservice.jiuiv5.ILcdCallback");
        }

        public static ILcdCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            } else {
                IInterface iin = obj.queryLocalInterface("woyou.aidlservice.jiuiv5.ILcdCallback");
                return (ILcdCallback)(iin != null && iin instanceof ILcdCallback ? (ILcdCallback)iin : new ILcdCallback.Stub.Proxy(obj));
            }
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch(code) {
                case 1:
                    data.enforceInterface("woyou.aidlservice.jiuiv5.ILcdCallback");
                    boolean _arg0 = 0 != data.readInt();
                    this.onRunResult(_arg0);
                    return true;
                case 1598968902:
                    reply.writeString("woyou.aidlservice.jiuiv5.ILcdCallback");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements ILcdCallback {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return "woyou.aidlservice.jiuiv5.ILcdCallback";
            }

            public void onRunResult(boolean show) throws RemoteException {
                Parcel _data = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("woyou.aidlservice.jiuiv5.ILcdCallback");
                    _data.writeInt(show ? 1 : 0);
                    this.mRemote.transact(1, _data, (Parcel)null, 1);
                } finally {
                    _data.recycle();
                }

            }
        }
    }
}

