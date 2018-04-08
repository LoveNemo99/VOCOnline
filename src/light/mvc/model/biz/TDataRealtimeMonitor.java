package light.mvc.model.biz;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
@Entity
@Table(name = "data_realtime_monitor_main")
public class TDataRealtimeMonitor implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String psCode;
	private String piCode;
	private String piState;
	private String onlineState;
	private Date onlineStateTime;
	private Date dataTime;
	private Float data1;
	private Float data2;
	private Float data3;
	private Float data4;
	private Float data5;
	private Float data6;
	private Float data7;
	private Float data8;
	private Float data9;
	private Float data10;
	private Float data11;
	private Float data12;
	private Float data13;
	private Float data14;
	private Float data15;
	private Float data16;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "ps_code")
	public String getPsCode() {
		return psCode;
	}

	public void setPsCode(String psCode) {
		this.psCode = psCode;
	}

	@Column(name = "pi_code")
	public String getPiCode() {
		return piCode;
	}

	public void setPiCode(String piCode) {
		this.piCode = piCode;
	}

	@Column(name = "online_state")
	public String getOnlineState() {
		return onlineState;
	}

	public void setOnlineState(String onlineState) {
		this.onlineState = onlineState;
	}

	@Column(name = "online_state_time")
	public Date getOnlineStateTime() {
		return onlineStateTime;
	}

	public void setOnlineStateTime(Date onlineStateTime) {
		this.onlineStateTime = onlineStateTime;
	}

	@Column(name = "data_time")
	public Date getDataTime() {
		return dataTime;
	}

	public void setDataTime(Date dataTime) {
		this.dataTime = dataTime;
	}

	@Column(name = "pi_state")
	public String getPiState() {
		return piState;
	}

	public void setPiState(String piState) {
		this.piState = piState;
	}

	@Column(name = "data1")
	public Float getData1() {
		return data1;
	}

	public void setData1(Float data1) {
		this.data1 = data1;
	}

	@Column(name = "data2")
	public Float getData2() {
		return data2;
	}

	public void setData2(Float data2) {
		this.data2 = data2;
	}

	@Column(name = "data3")
	public Float getData3() {
		return data3;
	}

	public void setData3(Float data3) {
		this.data3 = data3;
	}

	@Column(name = "data4")
	public Float getData4() {
		return data4;
	}

	public void setData4(Float data4) {
		this.data4 = data4;
	}

	@Column(name = "data5")
	public Float getData5() {
		return data5;
	}

	public void setData5(Float data5) {
		this.data5 = data5;
	}

	@Column(name = "data6")
	public Float getData6() {
		return data6;
	}

	public void setData6(Float data6) {
		this.data6 = data6;
	}

	@Column(name = "data7")
	public Float getData7() {
		return data7;
	}

	public void setData7(Float data7) {
		this.data7 = data7;
	}

	@Column(name = "data8")
	public Float getData8() {
		return data8;
	}

	public void setData8(Float data8) {
		this.data8 = data8;
	}

	@Column(name = "data9")
	public Float getData9() {
		return data9;
	}

	public void setData9(Float data9) {
		this.data9 = data9;
	}

	@Column(name = "data10")
	public Float getData10() {
		return data10;
	}

	public void setData10(Float data10) {
		this.data10 = data10;
	}

	@Column(name = "data11")
	public Float getData11() {
		return data11;
	}

	public void setData11(Float data11) {
		this.data11 = data11;
	}

	@Column(name = "data12")
	public Float getData12() {
		return data12;
	}

	public void setData12(Float data12) {
		this.data12 = data12;
	}

	@Column(name = "data13")
	public Float getData13() {
		return data13;
	}

	public void setData13(Float data13) {
		this.data13 = data13;
	}

	@Column(name = "data14")
	public Float getData14() {
		return data14;
	}

	public void setData14(Float data14) {
		this.data14 = data14;
	}

	@Column(name = "data15")
	public Float getData15() {
		return data15;
	}

	public void setData15(Float data15) {
		this.data15 = data15;
	}

	@Column(name = "data16")
	public Float getData16() {
		return data16;
	}

	public void setData16(Float data16) {
		this.data16 = data16;
	}

}
