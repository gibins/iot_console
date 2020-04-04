package exp.gibin.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CO_DEVICE")
public class Device {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="deviceName")
	private String deviceName;
	
	@Column(name="devDescri")
	private String devDescri;
	
	@Column(name="macAddress")
	private String macAddress;
	
	@Column(name="ipAddress")
	private String ipAddress;
	
	@Column(name="gatewayAddress")
	private String gatewayAddress;
	
	@Column(name="deviceStatus")
	private String deviceStatus;
	
	@Column(name="deviceSpecId")
	private int deviceSpecId;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getDevDescri() {
		return devDescri;
	}
	public void setDevDescri(String devDescri) {
		this.devDescri = devDescri;
	}
	public String getMacAddress() {
		return macAddress;
	}
	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getGatewayAddress() {
		return gatewayAddress;
	}
	public void setGatewayAddress(String gatewayAddress) {
		this.gatewayAddress = gatewayAddress;
	}
	
	
	

}
