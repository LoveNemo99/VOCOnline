package light.mvc.pageModel.data;

import java.math.BigInteger;
import java.util.Date;

import light.mvc.pageModel.base.PageFilter;

public class ConditionData implements java.io.Serializable,Comparable<ConditionData> {

	private static final long serialVersionUID = 6159615613952892233L;

	private String code,psName;
	private BigInteger dataMainId;
	private String port;
	private String mn;
	private String field1, field2, field3, field4, field5, field6, field7, field8, field9, field10;
	private Date time,startTime,endTime;
	private PageFilter ph;
	
	
	public BigInteger getDataMainId() {
		return dataMainId;
	}
	public void setDataMainId(BigInteger dataMainId) {
		this.dataMainId = dataMainId;
	}
	public String getMn() {
		return mn;
	}
	public void setMn(String mn) {
		this.mn = mn;
	}
	public PageFilter getPh() {
		return ph;
	}
	public void setPh(PageFilter ph) {
		this.ph = ph;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPsName() {
		return psName;
	}
	public void setPsName(String psName) {
		this.psName = psName;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	
	public String getField1() {
		return field1;
	}
	public void setField1(String field1) {
		this.field1 = field1;
	}
	public String getField2() {
		return field2;
	}
	public void setField2(String field2) {
		this.field2 = field2;
	}
	public String getField3() {
		return field3;
	}
	public void setField3(String field3) {
		this.field3 = field3;
	}
	public String getField4() {
		return field4;
	}
	public void setField4(String field4) {
		this.field4 = field4;
	}
	public String getField5() {
		return field5;
	}
	public void setField5(String field5) {
		this.field5 = field5;
	}
	public String getField6() {
		return field6;
	}
	public void setField6(String field6) {
		this.field6 = field6;
	}
	public String getField7() {
		return field7;
	}
	public void setField7(String field7) {
		this.field7 = field7;
	}
	public String getField8() {
		return field8;
	}
	public void setField8(String field8) {
		this.field8 = field8;
	}
	public String getField9() {
		return field9;
	}
	public void setField9(String field9) {
		this.field9 = field9;
	}
	public String getField10() {
		return field10;
	}
	public void setField10(String field10) {
		this.field10 = field10;
	}
	@Override
	public int compareTo(ConditionData d) {
		if(ph==null){
			return 0;
		}
		if(ph.getSort()==null){
			return 0;
		}
		if(ph.getSort().equals("time") && ph.getOrder().equals("asc")){
			return this.time.compareTo(d.time);
		}
		if(ph.getSort().equals("time") && ph.getOrder().equals("desc")){
			return d.time.compareTo(this.time);
		}
		return 0;
	}
	@Override
	public String toString() {
		return "ConditionData [code=" + code + ", psName=" + psName
				+ ", dataMainId=" + dataMainId + ", port=" + port + ", mn="
				+ mn + ", field1=" + field1 + ", field2=" + field2
				+ ", field3=" + field3 + ", field4=" + field4 + ", field5="
				+ field5 + ", field6=" + field6 + ", field7=" + field7
				+ ", field8=" + field8 + ", field9=" + field9 + ", field10="
				+ field10 + ", time=" + time + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", ph=" + ph + "]";
	}
	
}
