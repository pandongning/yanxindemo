package jsontoobject;


import com.alibaba.fastjson.JSON;

/**
 * 64273 pdn @create
 * 2018-12-26-16:04
 * kafkatohdfs
 * PACKAGE_NAME
 */
public class T1 {
    public static void main(String[] args) {
        String jsonString = " {\"cpuCurFreq\":\"2016000\",\"appVersion\":\"2.6.0\",\"isEmulator\":\"GENUINE PHONE\",\"Memory\":\"total-23897904B-max-536870912B-dalvikPss-16064B\",\"cpuArchitecture\":\"qcom\",\"isRooted\":\"NOT ROOTED\",\"appSignatures\":\"552635161--\",\"BluetoothMAC\":\"02:00:00:00:00:00\",\"Build\":\"-BOARD-msm8953\\n-BOOTLOADER-MSM8953_TISSOT2.0_20180920175008\\n-BRAND-xiaomi\\n-CPU_ABI-armeabi-v7a\\n-CPU_ABI2-armeabi\\n-DEVICE-tissot_sprout\\n-DISPLAY-OPM1.171019.026.V9.6.6.0.ODHMIFE\\n-FINGERPRINT-xiaomi/tissot/tissot_sprout:8.1.0/OPM1.171019.026/V9.6.6.0.ODHMIFE:user/release-keys\\n-HARDWARE-qcom\\n-HOST-mi-server\\n-ID-OPM1.171019.026\\n-MANUFACTURER-Xiaomi\\n-MODEL-Mi A1\\n-PRODUCT-tissot\\n-SERIAL-2821a00f9805\\n-RADIO-.TA.2.3.c1-00723-8953_GEN_PACK-1\\n-TAGS-release-keys\\n-TIME-1537440461000\\n-TYPE-user\\n-VERSION.RELEASE-8.1.0\",\"NetworkType\":\"13\",\"External Storage\":\"total-54091MB-free-14516MB\",\"ethIp\":\"0.0.0.0\",\"USB\":\"\",\"appName\":\"AKULAKU\",\"cpuSerial\":\"0000000000000000\",\"ip\":\"10.98.87.157\",\"Time\\u0026Location\":\"Asia/Jakarta  null\",\"AndroidId\":\"0864935e68497097\",\"cpuName\":\"0\",\"Sim information\":\"simState:5\\nimsi:510104162839670\\nsimSerial:8962100541628396702\\nphoneNumber:\\nphoneType:1\\ncarrierMobileCountryCode:id\\ncarrierOperator:TELKOMSEL\\nsimCountryIso:id\\nmcc:510\\nmnc:2147483647\\nlac:2147483647\\ncid:2147483647\\nsimStrength:-97\",\"Sys Features\":\"-WIFI-1-LOCATION_GPS-1-TELEPHONY-1-NFC-0-NFC_HOST_CARD_EMULATION-0-BLUETOOTH-1-WIFI_DIRECT-1-USB_HOST-1-USB_ACCESSORY-1\",\"cpuMaxFreq\":\"2016000\",\"appPackageName\":\"io.silvrr.installment\",\"Display\":\"density-2.55-width-1080.0-height-1920.0\",\"cpuMinFreq\":\"652800\"}";


        HardWareInfoFourClass hardWareInfoFourClass = JSON.parseObject(jsonString, HardWareInfoFourClass.class);
        System.out.println(hardWareInfoFourClass.getCpuMaxFreq());


    }
}
