package com.jspxcms.plug.bean.event.revice;

public class ScancodePushMessage extends BaseMessage {
	private String ScanCodeInfo;// 扫描信息
	private String ScanType;// 扫描类型，一般是qrcode
	private String ScanResult;// 扫描结果，即二维码对应的字符串信息
	public String getScanCodeInfo() {
		return ScanCodeInfo;
	}
	public void setScanCodeInfo(String scanCodeInfo) {
		ScanCodeInfo = scanCodeInfo;
	}
	public String getScanType() {
		return ScanType;
	}
	public void setScanType(String scanType) {
		ScanType = scanType;
	}
	public String getScanResult() {
		return ScanResult;
	}
	public void setScanResult(String scanResult) {
		ScanResult = scanResult;
	}
	
	
}
