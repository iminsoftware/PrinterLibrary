package com.sunmi.peripheral.printer;

import android.util.Log;

public class LogUtils {
    //打印长内容
    public static void showLogCompletion(String log,String tag,int showCount){
        if(log.length() >showCount){
            String show = log.substring(0, showCount);
            Log.i(tag, "==print==log=====>  "+show+"");
            if((log.length() - showCount)>showCount){//剩下的文本还是大于规定长度
                String partLog = log.substring(showCount,log.length());
                showLogCompletion(partLog,tag, showCount);
            }else{
                String surplusLog = log.substring(showCount, log.length());
                Log.i(tag, "==print==log==eeee===>  "+surplusLog+"");
            }

        }else{
            Log.i(tag, "==print==log=====>  "+log+"");
        }
    }
}
