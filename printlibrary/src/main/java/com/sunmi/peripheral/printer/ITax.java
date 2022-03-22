package com.sunmi.peripheral.printer;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ITax extends IInterface {
    void onDataResult(byte[] data) throws RemoteException;

    public abstract static class Stub extends Binder implements ITax {
        private static final String DESCRIPTOR = "woyou.aidlservice.jiuiv5.ITax";
        static final int TRANSACTION_onDataResult = 1;

        public Stub() {
            this.attachInterface(this, "woyou.aidlservice.jiuiv5.ITax");
        }

        public static ITax asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            } else {
                IInterface iin = obj.queryLocalInterface("woyou.aidlservice.jiuiv5.ITax");
                return (ITax)(iin != null && iin instanceof ITax ? (ITax)iin : new ITax.Stub.Proxy(obj));
            }
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch(code) {
                case 1:
                    data.enforceInterface("woyou.aidlservice.jiuiv5.ITax");
                    byte[] _arg0 = data.createByteArray();
                    this.onDataResult(_arg0);
                    return true;
                case 1598968902:
                    reply.writeString("woyou.aidlservice.jiuiv5.ITax");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        private static class Proxy implements ITax {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return "woyou.aidlservice.jiuiv5.ITax";
            }

            public void onDataResult(byte[] data) throws RemoteException {
                Parcel _data = Parcel.obtain();

                try {
                    _data.writeInterfaceToken("woyou.aidlservice.jiuiv5.ITax");
                    _data.writeByteArray(data);
                    this.mRemote.transact(1, _data, (Parcel)null, 1);
                } finally {
                    _data.recycle();
                }

            }
        }
    }
}

