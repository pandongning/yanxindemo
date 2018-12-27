package jsontoobject;

import java.io.Serializable;

/**
 * 64273 pdn @create
 * 2018-12-27-15:35
 * kafkatohdfs
 * jsontoobject
 */
public class HardWareInfoFourClass implements Serializable {
    private String	cpuCurFreq;
    private String	appVersion;
    private String	isEmulator;
    private String	Memory;
    private String	cpuArchitecture;
    private String	isRooted;
    private String	appSignatures;
    private String	BluetoothMAC;
    private String	Build;
    private String	NetworkType;
    private String	ExternalStorage;
    private String	ethIp;
    private String	USB;
    private String	appName;
    private String	cpuSerial;
    private String	ip;
    private String	Time;
    private String	AndroidId;
    private String	cpuName;
    private String	Siminformation;
    private String	SysFeatures;
    private String	cpuMaxFreq;
    private String	appPackageName;
    private String	Display;
    private String	cpuMinFreq;


    @Override
    public String toString() {
        return "HardWareInfoFourClass{" +
                "cpuCurFreq='" + cpuCurFreq + '\'' +
                ", appVersion='" + appVersion + '\'' +
                ", isEmulator='" + isEmulator + '\'' +
                ", Memory='" + Memory + '\'' +
                ", cpuArchitecture='" + cpuArchitecture + '\'' +
                ", isRooted='" + isRooted + '\'' +
                ", appSignatures='" + appSignatures + '\'' +
                ", BluetoothMAC='" + BluetoothMAC + '\'' +
                ", Build='" + Build + '\'' +
                ", NetworkType='" + NetworkType + '\'' +
                ", ExternalStorage='" + ExternalStorage + '\'' +
                ", ethIp='" + ethIp + '\'' +
                ", USB='" + USB + '\'' +
                ", appName='" + appName + '\'' +
                ", cpuSerial='" + cpuSerial + '\'' +
                ", ip='" + ip + '\'' +
                ", Time='" + Time + '\'' +
                ", AndroidId='" + AndroidId + '\'' +
                ", cpuName='" + cpuName + '\'' +
                ", Siminformation='" + Siminformation + '\'' +
                ", SysFeatures='" + SysFeatures + '\'' +
                ", cpuMaxFreq='" + cpuMaxFreq + '\'' +
                ", appPackageName='" + appPackageName + '\'' +
                ", Display='" + Display + '\'' +
                ", cpuMinFreq='" + cpuMinFreq + '\'' +
                '}';
    }

    public String getCpuCurFreq() {
        return cpuCurFreq;
    }

    public void setCpuCurFreq(String cpuCurFreq) {
        this.cpuCurFreq = cpuCurFreq;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getIsEmulator() {
        return isEmulator;
    }

    public void setIsEmulator(String isEmulator) {
        this.isEmulator = isEmulator;
    }

    public String getMemory() {
        return Memory;
    }

    public void setMemory(String memory) {
        Memory = memory;
    }

    public String getCpuArchitecture() {
        return cpuArchitecture;
    }

    public void setCpuArchitecture(String cpuArchitecture) {
        this.cpuArchitecture = cpuArchitecture;
    }

    public String getIsRooted() {
        return isRooted;
    }

    public void setIsRooted(String isRooted) {
        this.isRooted = isRooted;
    }

    public String getAppSignatures() {
        return appSignatures;
    }

    public void setAppSignatures(String appSignatures) {
        this.appSignatures = appSignatures;
    }

    public String getBluetoothMAC() {
        return BluetoothMAC;
    }

    public void setBluetoothMAC(String bluetoothMAC) {
        BluetoothMAC = bluetoothMAC;
    }

    public String getBuild() {
        return Build;
    }

    public void setBuild(String build) {
        Build = build;
    }

    public String getNetworkType() {
        return NetworkType;
    }

    public void setNetworkType(String networkType) {
        NetworkType = networkType;
    }

    public String getExternalStorage() {
        return ExternalStorage;
    }

    public void setExternalStorage(String externalStorage) {
        ExternalStorage = externalStorage;
    }

    public String getEthIp() {
        return ethIp;
    }

    public void setEthIp(String ethIp) {
        this.ethIp = ethIp;
    }

    public String getUSB() {
        return USB;
    }

    public void setUSB(String USB) {
        this.USB = USB;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getCpuSerial() {
        return cpuSerial;
    }

    public void setCpuSerial(String cpuSerial) {
        this.cpuSerial = cpuSerial;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getAndroidId() {
        return AndroidId;
    }

    public void setAndroidId(String androidId) {
        AndroidId = androidId;
    }

    public String getCpuName() {
        return cpuName;
    }

    public void setCpuName(String cpuName) {
        this.cpuName = cpuName;
    }

    public String getSiminformation() {
        return Siminformation;
    }

    public void setSiminformation(String siminformation) {
        Siminformation = siminformation;
    }

    public String getSysFeatures() {
        return SysFeatures;
    }

    public void setSysFeatures(String sysFeatures) {
        SysFeatures = sysFeatures;
    }

    public String getCpuMaxFreq() {
        return cpuMaxFreq;
    }

    public void setCpuMaxFreq(String cpuMaxFreq) {
        this.cpuMaxFreq = cpuMaxFreq;
    }

    public String getAppPackageName() {
        return appPackageName;
    }

    public void setAppPackageName(String appPackageName) {
        this.appPackageName = appPackageName;
    }

    public String getDisplay() {
        return Display;
    }

    public void setDisplay(String display) {
        Display = display;
    }

    public String getCpuMinFreq() {
        return cpuMinFreq;
    }

    public void setCpuMinFreq(String cpuMinFreq) {
        this.cpuMinFreq = cpuMinFreq;
    }
}
