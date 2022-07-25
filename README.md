PrinterLibrary
==========
### 
Please be sure to read the documentation when using Demo and You can find 
developer documentation from(https://oss-sg.imin.sg/docs/en/PrinterSDK.html)
###
We strongly recommend developers to use Android Studio to develop.The demo for Android Studio has full functionality, such as printing barcodes, printing qr code, printing text, printing pictures and printing tables.  

## Library
    
    dependencies{
      implementation 'com.github.iminsoftware:PrinterLibrary:v1.0'
    }
    
Now we replaced AIDL integration with Sunmi Printer Interface Library.  
It is suitable for any Sunmi device.  

## Docs  
(https://oss-sg.imin.sg/docs/en/PrinterSDK.html)

## Instruction

* FunctionActivity——
Home page of each function module  
* AllActivity——
Print all ESC command supported by Sunmi Printer
* QrActivity——Demo call api to print Qr Code
* BarCodeActivity——Demo call api to print bar code
* TextActivity——Demo call api to print text
* TableActivity——Demo call api to print table， 
Bluetooth only plays table graphics
* BitmapActivity——Demo call api to print image,picture
* BufferActivity——Demo call api to print 
a simple small ticket content, and You can get the print result via transaction mode
* BlackLabelActivity——Demo call api to print on black label paper
* LabelActivity——Demo call api to print on "label paper"

## InnerPrinter Ble
Each function module can be used via Bluetooth, 
You can send the esc command through innerprinter ble.  
See it in each module:
    
    if (!BluetoothUtil.isBlueToothPrinter) {
        //Call API  
    } else {
        //ESC cmd via Ble
    }
    

