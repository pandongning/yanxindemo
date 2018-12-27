package fastJson

import com.alibaba.fastjson.{JSON, JSONObject}

/**
  * 64273 pdn @create 
  * 2018-12-26-18:08
  * kafkatohdfs
  * fastJson
  */
object TestFastJson {

  case class appeClass(app_info_four: String)

  case class appInfoClass(app_info_one: String, app_info_two: String, app_info_there: String, app: appeClass)


  case class hardWareInfoFourClass(cpuCurFreq: String, appVersion: String, isEmulator: String, Memory: String, cpuArchitecture: String, isRooted: String, appSignatures: String,
                                   BluetoothMAC: String, Build: String, NetworkType: String, ExternalStorage: String, ethIp: String, USB: String, appName: String,
                                   cpuSerial: String, ip: String, Time: String, AndroidId: String, cpuName: String, Siminformation: String, SysFeatures: String, cpuMaxFreq: String,
                                   appPackageName: String, Display: String, cpuMinFreq: String)

  case class hardWareInfoClass(hardware_info_one: String, hardware_info_two: String, hardware_info_there: String, hardware_info_four: String)

  case class dataClass(app_info: appInfoClass, hardware_info: hardWareInfoClass, uid: String, device_id: String)

  def main(args: Array[String]): Unit = {
    test2




  }




  private def test2: Unit = {
    val message = "{\n   \"data\": {\n      \"app_info\": \"2 | 3064715 | 867561035179947 | {\\\"app\\\":\\\"com.android.cts.priv.ctsshim,com.android.cts.priv.ctsshim,YouTube,SampleExtAuthService,Perfdump,Android Services Library,My Smartfren,Penyimpanan Ponsel dan Pesan,GFManager,Google,Penyimpanan Kalender,Penyimpanan Media,com.qti.service.colorservice,Google One Time Init,Android Shared Library,com.android.wallpapercropper,com.quicinc.cne.CNEService.CNEServiceApp,com.mi.setupwizardoverlay,com.qualcomm.qti.autoregistration,Galeri,Balls Bricks Breaker 2,Candy Crush Soda,File,Penyimpanan Eksternal,Penampil HTML,SVI Settings,PicsArt,RCSService,Companion Device Manager,MmsService,Pengelola Download,SampleAuthenticatorService,Message,CallEnhancement,Young.Live,SecProtect,com.qualcomm.qti.telephonyservice,FidoCryptoService,SUPER PINGER - Anti Lag,ConfigUpdater,Pembantu Akses Paket,Conference URI Dialer,Wps Wpa Tester,Download,Google Play Store,PacProcessor,Content Adaptive Backlight Settings,Pemasang Sertifikat,com.android.carrierconfig,TalkBack,org.codeaurora.bluetooth,Penyiapan perangkat,com.qti.qualcomm.datastatusnotification,Sistem Android,com.qualcomm.qti.callfeaturessetting,Wfd Service,TIX ID,Device Info,Keyboard Indic Google,Android Easter Egg,Host MTP,SIM Toolkit,Launcher3,com.android.backupconfirm,Dirac Control Service,fingerprint test,Jam,org.codeaurora.ims,Intent Filter Verification Service,Gmail,Duo,Dark,FactoryKit Test,YMusic,SecureExtAuthService,Penyiapan Android,com.qualcomm.qcrilmsgtunnel,Setelan Penyimpanan,com.android.sharedstoragebackup,Google Play Musik,Print Spooler,CLEANit,Lamunan Dasar,Snaptube,com.qualcomm.qti.qcom_accesslogkit,Mi Remote,Masukan,Perangkat Masukan,Telepon,SecureSampleAuthService,Layanan Cetak Default,com.qti.dpmserviceapp,Durango,MX Player,FingerprintExtensionService,Smart-Divert,RAR,MusicFX,Drive,Maps,Mobile Legends: Bang Bang,Siaran Seluler,Android System WebView,com.qualcomm.qti.simsettings,Kontak,Pengelolaan Panggilan Telepon,Sinkronisasi Google Kontak,Wajah Tepercaya,Key Chain,Kamera,Kalkulator,Chrome,Pemasang paket,Layanan Google Play,Kerangka Kerja Layanan Google,Carrier Services,Mesin Google Text-to-speech,com.qualcomm.qti.qtisystemservice,Call Log Backup/Restore,Penyiapan Mitra Google,Google Play Film,org.codeaurora.btmultisim,AplikasiDefaultOperator,Toolbox for Minecraft: PE,ANT HAL Service,Minecraft,ProxyHandler,Hago,CarrierLoadService,UC Browser,Spock,Agen Masukan Market,Print Service Recommendation Service,Mi Drop,Foto,Kalender,Piano Tiles 2,Penyiapan profil kerja,Sensor Test Tool,Juice Jam,Screensaver Foto,PAI,MyTelkomsel,com.android.providers.partnerbookmarks,com.android.smspush,AKULAKU,Pemilih Wallpaper Animasi,Assemble test,Lite,Masukan Korea Google,Terjemahan,Layanan Transfer Backup Google,CarrierAccessCacheService,Pengelola Penyimpanan,Bookmark Provider,Setelan,com.qualcomm.qti.ims,com.qualcomm.qti.lpa,com.qualcomm.qti.uim,Masukan Pinyin Google,Notepad cepat,Google Play Buku,LocationServices,SimContacts,Hibernation Manager,com.android.cts.ctsshim,Secure UI Service,VpnDialogs,Catatan Keep,Layanan Telepon,Kerangka,com.android.wallpaperbackup,Penyimpanan Nomor yang Diblokir,DuckDuckGo,Kamus Pengguna,Informasi darurat,TouchVPN,QMMI,Fused Location,Sistem UI,Bluetooth MIDI Service,ConfDialer,PowerOffAlarm,myXL,com.qualcomm.qti.networksetting,SiMontok,Aptoide,Mi Home,Google Play Game,Bluetooth,com.qualcomm.timeservice,com.android.wallpaperpicker,com.qualcomm.embms,Penyimpanan Kontak,CaptivePortalLogin,Marble Legend 2,Gboard,UseeTVGO\\\"}\",\n      \"hardware_info\": \"1 | -1 |  | {\\\"cpuCurFreq\\\":\\\"2016000\\\",\\\"appVersion\\\":\\\"2.6.0\\\",\\\"isEmulator\\\":\\\"GENUINE PHONE\\\",\\\"Memory\\\":\\\"total-23897904B-max-536870912B-dalvikPss-16064B\\\",\\\"cpuArchitecture\\\":\\\"qcom\\\",\\\"isRooted\\\":\\\"NOT ROOTED\\\",\\\"appSignatures\\\":\\\"552635161--\\\",\\\"BluetoothMAC\\\":\\\"02:00:00:00:00:00\\\",\\\"Build\\\":\\\"-BOARD-msm8953\\\\n-BOOTLOADER-MSM8953_TISSOT2.0_20180920175008\\\\n-BRAND-xiaomi\\\\n-CPU_ABI-armeabi-v7a\\\\n-CPU_ABI2-armeabi\\\\n-DEVICE-tissot_sprout\\\\n-DISPLAY-OPM1.171019.026.V9.6.6.0.ODHMIFE\\\\n-FINGERPRINT-xiaomi/tissot/tissot_sprout:8.1.0/OPM1.171019.026/V9.6.6.0.ODHMIFE:user/release-keys\\\\n-HARDWARE-qcom\\\\n-HOST-mi-server\\\\n-ID-OPM1.171019.026\\\\n-MANUFACTURER-Xiaomi\\\\n-MODEL-Mi A1\\\\n-PRODUCT-tissot\\\\n-SERIAL-2821a00f9805\\\\n-RADIO-.TA.2.3.c1-00723-8953_GEN_PACK-1\\\\n-TAGS-release-keys\\\\n-TIME-1537440461000\\\\n-TYPE-user\\\\n-VERSION.RELEASE-8.1.0\\\",\\\"NetworkType\\\":\\\"13\\\",\\\"External Storage\\\":\\\"total-54091MB-free-14516MB\\\",\\\"ethIp\\\":\\\"0.0.0.0\\\",\\\"USB\\\":\\\"\\\",\\\"appName\\\":\\\"AKULAKU\\\",\\\"cpuSerial\\\":\\\"0000000000000000\\\",\\\"ip\\\":\\\"10.98.87.157\\\",\\\"Time\\\\u0026Location\\\":\\\"Asia/Jakarta  null\\\",\\\"AndroidId\\\":\\\"0864935e68497097\\\",\\\"cpuName\\\":\\\"0\\\",\\\"Sim information\\\":\\\"simState:5\\\\nimsi:510104162839670\\\\nsimSerial:8962100541628396702\\\\nphoneNumber:\\\\nphoneType:1\\\\ncarrierMobileCountryCode:id\\\\ncarrierOperator:TELKOMSEL\\\\nsimCountryIso:id\\\\nmcc:510\\\\nmnc:2147483647\\\\nlac:2147483647\\\\ncid:2147483647\\\\nsimStrength:-97\\\",\\\"Sys Features\\\":\\\"-WIFI-1-LOCATION_GPS-1-TELEPHONY-1-NFC-0-NFC_HOST_CARD_EMULATION-0-BLUETOOTH-1-WIFI_DIRECT-1-USB_HOST-1-USB_ACCESSORY-1\\\",\\\"cpuMaxFreq\\\":\\\"2016000\\\",\\\"appPackageName\\\":\\\"io.silvrr.installment\\\",\\\"Display\\\":\\\"density-2.55-width-1080.0-height-1920.0\\\",\\\"cpuMinFreq\\\":\\\"652800\\\"}\",\n      \"uid\": \"3064715\",\n      \"device_id\": \"867561035179947\"\n   },\n   \"createTime\": 1539402104044\n}"

    val jsonMessage: JSONObject = JSON.parseObject(message)
    val appInfoString: String = jsonMessage.getJSONObject("data").getString("app_info")
    val hardwareInfoString: String = jsonMessage.getJSONObject("data").getString("hardware_info")
    val uid: String = jsonMessage.getJSONObject("data").getString("uid")
    val device_id: String = jsonMessage.getJSONObject("data").getString("device_id")

    //    对app_info进行更加详细的解析
    val app_info_strings: Array[String] = appInfoString.split("\\|", 5)
    val app_info_one: String = app_info_strings(0)
    val app_info_two: String = app_info_strings(1)
    val app_info_there: String = app_info_strings(2)
    //    app_info的第3个字段又是一个Json串
    val app_info_four: String = app_info_strings(3)
    val app_info_four_app: String = JSON.parseObject(app_info_four).getString("app")


    //    对hardware_info的信息进行更加详细的解析
    val hardware_info_strings: Array[String] = hardwareInfoString.split("\\|", 5)
    val hardware_info_one: String = hardware_info_strings(0)
    val hardware_info_two: String = hardware_info_strings(1)
    val hardware_info_there: String = hardware_info_strings(2)
    //   hardware_info又是一个Json串，里面有许多不同的字段
    val hardware_info_four: String = hardware_info_strings(3)


    val hardWareInfoFourClazz: hardWareInfoFourClass = JSON.parseObject(hardware_info_four,classOf[hardWareInfoFourClass])

    val app = appeClass(app_info_four_app)
    val app_info = appInfoClass(app_info_one, app_info_two, app_info_there, app)
    val hardware_info = hardWareInfoClass(hardware_info_one, hardware_info_two, hardware_info_there, hardware_info_four)
    val data = dataClass(app_info, hardware_info, uid, device_id)

    print(hardWareInfoFourClazz.cpuCurFreq)

  }


}
