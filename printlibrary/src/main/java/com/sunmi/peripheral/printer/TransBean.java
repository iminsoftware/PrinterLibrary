package com.sunmi.peripheral.printer;

import android.os.Parcel;
import android.os.Parcelable;

public class TransBean implements Parcelable {
    private byte type = 0;
    private String text = "";
    private byte[] data = null;
    private int datalength = 0;
    public static Creator<TransBean> CREATOR = new Creator<TransBean>() {
        public TransBean createFromParcel(Parcel source) {
            return new TransBean(source);
        }

        public TransBean[] newArray(int size) {
            return new TransBean[size];
        }
    };

    public TransBean() {
        this.type = 0;
        this.data = null;
        this.text = "";
        this.datalength = 0;
    }

    public byte getType() {
        return this.type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public byte[] getData() {
        return this.data;
    }

    public void setData(byte[] data) {
        if (data != null) {
            this.datalength = data.length;
            this.data = new byte[this.datalength];
            System.arraycopy(data, 0, this.data, 0, this.datalength);
        }

    }

    public TransBean(Parcel source) {
        this.type = source.readByte();
        this.datalength = source.readInt();
        this.text = source.readString();
        if (this.datalength > 0) {
            this.data = new byte[this.datalength];
            source.readByteArray(this.data);
        }

    }

    public TransBean(byte type, String text, byte[] data) {
        this.type = type;
        this.text = text;
        if (data != null) {
            this.datalength = data.length;
            this.data = new byte[this.datalength];
            System.arraycopy(data, 0, this.data, 0, this.datalength);
        }

    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.type);
        dest.writeInt(this.datalength);
        dest.writeString(this.text);
        if (this.data != null) {
            dest.writeByteArray(this.data);
        }

    }
}

