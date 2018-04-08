package light.mvc.pageModel.data;

import java.math.BigInteger;
import java.util.Date;

import light.mvc.pageModel.base.PageFilter;

public class StatisticsData implements java.io.Serializable,Comparable<StatisticsData> {

	private static final long serialVersionUID = 6159615613952892233L;

	private String code,pollutionSourceName;
	//private Integer pollutionSourceId;
	private BigInteger dataMainId;
	private String port;
	private String mn;
	private Boolean sewageTreatmentFactory,isOnline;
	private Float field1, field2, field3, field4, field5, field6, field7, field8, field9, field10;
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
	public String getPollutionSourceName() {
		return pollutionSourceName;
	}
	public void setPollutionSourceName(String pollutionSourceName) {
		this.pollutionSourceName = pollutionSourceName;
	}
//	public Integer getPollutionSourceId() {
//		return pollutionSourceId;
//	}
//	public void setPollutionSourceId(Integer pollutionSourceId) {
//		this.pollutionSourceId = pollutionSourceId;
//	}
	public Boolean getSewageTreatmentFactory() {
		return sewageTreatmentFactory;
	}
	public void setSewageTreatmentFactory(Boolean sewageTreatmentFactory) {
		this.sewageTreatmentFactory = sewageTreatmentFactory;
	}
	public Boolean getIsOnline() {
		return isOnline;
	}
	public void setIsOnline(Boolean isOnline) {
		this.isOnline = isOnline;
	}

	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public Float getField1() {
		return field1;
	}
	public void setField1(Float field1) {
		this.field1 = field1;
	}
	public Float getField2() {
		return field2;
	}
	public void setField2(Float field2) {
		this.field2 = field2;
	}
	public Float getField3() {
		return field3;
	}
	public void setField3(Float field3) {
		this.field3 = field3;
	}
	public Float getField4() {
		return field4;
	}
	public void setField4(Float field4) {
		this.field4 = field4;
	}
	public Float getField5() {
		return field5;
	}
	public void setField5(Float field5) {
		this.field5 = field5;
	}
	public Float getField6() {
		return field6;
	}
	public void setField6(Float field6) {
		this.field6 = field6;
	}
	public Float getField7() {
		return field7;
	}
	public void setField7(Float field7) {
		this.field7 = field7;
	}
	public Float getField8() {
		return field8;
	}
	public void setField8(Float field8) {
		this.field8 = field8;
	}
	public Float getField9() {
		return field9;
	}
	public void setField9(Float field9) {
		this.field9 = field9;
	}
	public Float getField10() {
		return field10;
	}
	public void setField10(Float field10) {
		this.field10 = field10;
	}
	@Override
	public int compareTo(StatisticsData d) {
		if(ph==null){
			return 0;
		}
		if(ph.getSort()==null){
			return 0;
		}
		if(ph.getSort().equals("field1") && ph.getOrder().equals("asc")){
			if(this.field1==null)return -1;if(d.field1==null)return 1;
			return Float.compare(this.field1, d.field1);
		}
		if(ph.getSort().equals("field1") && ph.getOrder().equals("desc")){
			if(d.field1==null)return -1;if(this.field1==null)return 1;
			return Float.compare(d.field1, this.field1);
		}
		if(ph.getSort().equals("field2") && ph.getOrder().equals("asc")){
			if(this.field2==null)return -1;if(d.field2==null)return 1;
			return Float.compare(this.field2, d.field2);
		}
		if(ph.getSort().equals("field2") && ph.getOrder().equals("desc")){
			if(d.field2==null)return -1;if(this.field2==null)return 1;
			return Float.compare(d.field2, this.field2);
		}
		if(ph.getSort().equals("field3") && ph.getOrder().equals("asc")){
			if(this.field3==null)return -1;if(d.field3==null)return 1;
			return Float.compare(this.field3, d.field3);
		}
		if(ph.getSort().equals("field3") && ph.getOrder().equals("desc")){
			if(d.field3==null)return -1;if(this.field3==null)return 1;
			return Float.compare(d.field3, this.field3);
		}
		if(ph.getSort().equals("field4") && ph.getOrder().equals("asc")){
			if(this.field4==null)return -1;if(d.field4==null)return 1;
			return Float.compare(this.field4, d.field4);
		}
		if(ph.getSort().equals("field4") && ph.getOrder().equals("desc")){
			if(d.field4==null)return -1;if(this.field4==null)return 1;
			return Float.compare(d.field4, this.field4);
		}
		if(ph.getSort().equals("field5") && ph.getOrder().equals("asc")){
			if(this.field5==null)return -1;if(d.field5==null)return 1;
			return Float.compare(this.field5, d.field5);
		}
		if(ph.getSort().equals("field5") && ph.getOrder().equals("desc")){
			if(d.field5==null)return -1;if(this.field5==null)return 1;
			return Float.compare(d.field5, this.field5);
		}
		if(ph.getSort().equals("field6") && ph.getOrder().equals("asc")){
			if(this.field6==null)return -1;if(d.field6==null)return 1;
			return Float.compare(this.field6, d.field6);
		}
		if(ph.getSort().equals("field6") && ph.getOrder().equals("desc")){
			if(d.field6==null)return -1;if(this.field6==null)return 1;
			return Float.compare(d.field6, this.field6);
		}
		if(ph.getSort().equals("field7") && ph.getOrder().equals("asc")){
			if(this.field7==null)return -1;if(d.field7==null)return 1;
			return Float.compare(this.field7, d.field7);
		}
		if(ph.getSort().equals("field7") && ph.getOrder().equals("desc")){
			if(d.field7==null)return -1;if(this.field7==null)return 1;
			return Float.compare(d.field7, this.field7);
		}
		if(ph.getSort().equals("field8") && ph.getOrder().equals("asc")){
			if(this.field8==null)return -1;if(d.field8==null)return 1;
			return Float.compare(this.field8, d.field8);
		}
		if(ph.getSort().equals("field8") && ph.getOrder().equals("desc")){
			if(d.field8==null)return -1;if(this.field8==null)return 1;
			return Float.compare(d.field8, this.field8);
		}
		if(ph.getSort().equals("field9") && ph.getOrder().equals("asc")){
			if(this.field9==null)return -1;if(d.field9==null)return 1;
			return Float.compare(this.field9, d.field9);
		}
		if(ph.getSort().equals("field9") && ph.getOrder().equals("desc")){
			if(d.field9==null)return -1;if(this.field9==null)return 1;
			return Float.compare(d.field9, this.field9);
		}
		if(ph.getSort().equals("field10") && ph.getOrder().equals("asc")){
			if(this.field10==null)return -1;if(d.field10==null)return 1;
			return Float.compare(this.field10, d.field10);
		}
		if(ph.getSort().equals("field10") && ph.getOrder().equals("desc")){
			if(d.field10==null)return -1;if(this.field10==null)return 1;
			return Float.compare(d.field10, this.field10);
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
		return "StatisticsData [code=" + code + ", pollutionSourceName="
				+ pollutionSourceName + ", pollutionSourceId="
				+ ", dataMainId=" + dataMainId + ", port="
				+ port + ", mn=" + mn + ", sewageTreatmentFactory="
				+ sewageTreatmentFactory + ", isOnline=" + isOnline
				+ ", field1=" + field1 + ", field2=" + field2 + ", field3="
				+ field3 + ", field4=" + field4 + ", field5=" + field5
				+ ", field6=" + field6 + ", field7=" + field7 + ", field8="
				+ field8 + ", field9=" + field9 + ", field10=" + field10
				+ ", time=" + time + ", startTime=" + startTime + ", endTime="
				+ endTime + ", ph=" + ph + "]";
	}
	
}
