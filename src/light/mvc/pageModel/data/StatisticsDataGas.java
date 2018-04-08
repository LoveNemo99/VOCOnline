package light.mvc.pageModel.data;

import java.util.Date;

import light.mvc.pageModel.base.PageFilter;

public class StatisticsDataGas implements java.io.Serializable,Comparable<StatisticsDataGas> {

	private static final long serialVersionUID = 6159615613952892233L;

	String code,pollutionSourceName;
	Integer pollutionSourceId;
	Boolean sewageTreatmentFactory,isOnline;
	Float avg01, cou01, avg02, cou02, avg03, cou03, avgB02, couB02, avgS01, couS01, avgS03, couS03;
	Date time,startTime,endTime;
	PageFilter ph;
	
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
	public Integer getPollutionSourceId() {
		return pollutionSourceId;
	}
	public void setPollutionSourceId(Integer pollutionSourceId) {
		this.pollutionSourceId = pollutionSourceId;
	}
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
	
	public Float getAvg01() {
		return avg01;
	}
	public void setAvg01(Float avg01) {
		this.avg01 = avg01;
	}
	public Float getCou01() {
		return cou01;
	}
	public void setCou01(Float cou01) {
		this.cou01 = cou01;
	}
	public Float getAvg02() {
		return avg02;
	}
	public void setAvg02(Float avg02) {
		this.avg02 = avg02;
	}
	public Float getCou02() {
		return cou02;
	}
	public void setCou02(Float cou02) {
		this.cou02 = cou02;
	}
	public Float getAvg03() {
		return avg03;
	}
	public void setAvg03(Float avg03) {
		this.avg03 = avg03;
	}
	public Float getCou03() {
		return cou03;
	}
	public void setCou03(Float cou03) {
		this.cou03 = cou03;
	}
	public Float getAvgB02() {
		return avgB02;
	}
	public void setAvgB02(Float avgB02) {
		this.avgB02 = avgB02;
	}
	public Float getCouB02() {
		return couB02;
	}
	public void setCouB02(Float couB02) {
		this.couB02 = couB02;
	}
	public Float getAvgS01() {
		return avgS01;
	}
	public void setAvgS01(Float avgS01) {
		this.avgS01 = avgS01;
	}
	public Float getCouS01() {
		return couS01;
	}
	public void setCouS01(Float couS01) {
		this.couS01 = couS01;
	}
	public Float getAvgS03() {
		return avgS03;
	}
	public void setAvgS03(Float avgS03) {
		this.avgS03 = avgS03;
	}
	public Float getCouS03() {
		return couS03;
	}
	public void setCouS03(Float couS03) {
		this.couS03 = couS03;
	}
	@Override
	public int compareTo(StatisticsDataGas d) {
		// TODO Auto-generated method stub
		if(ph==null){
			return 0;
		}
		if(ph.getSort()==null){
			return 0;
		}
		if(ph.getSort().equals("cou01") && ph.getOrder().equals("asc")){
			if(this.cou01==null)return -1;if(d.cou01==null)return 1;
			return Float.compare(this.cou01, d.cou01);
		}
		if(ph.getSort().equals("cou01") && ph.getOrder().equals("desc")){
			if(d.cou01==null)return -1;if(this.cou01==null)return 1;
			return Float.compare(d.cou01, this.cou01);
		}
		if(ph.getSort().equals("avg01") && ph.getOrder().equals("asc")){
			if(this.avg01==null)return -1;if(d.avg01==null)return 1;
			return Float.compare(this.avg01, d.avg01);
		}
		if(ph.getSort().equals("avg01") && ph.getOrder().equals("desc")){
			if(d.avg01==null)return -1;if(this.avg01==null)return 1;
			return Float.compare(d.avg01, this.avg01);
		}
		if(ph.getSort().equals("avg02") && ph.getOrder().equals("asc")){
			if(this.avg02==null)return -1;if(d.avg02==null)return 1;
			return Float.compare(this.avg02, d.avg02);
		}
		if(ph.getSort().equals("avg02") && ph.getOrder().equals("desc")){
			if(d.avg02==null)return -1;if(this.avg02==null)return 1;
			return Float.compare(d.avg02, this.avg02);
		}
		if(ph.getSort().equals("avg03") && ph.getOrder().equals("asc")){
			if(this.avg03==null)return -1;if(d.avg03==null)return 1;
			return Float.compare(this.avg03, d.avg03);
		}
		if(ph.getSort().equals("avg03") && ph.getOrder().equals("desc")){
			if(d.avg03==null)return -1;if(this.avg03==null)return 1;
			return Float.compare(d.avg03, this.avg03);
		}
		if(ph.getSort().equals("avgB02") && ph.getOrder().equals("asc")){
			if(this.avgB02==null)return -1;if(d.avgB02==null)return 1;
			return Float.compare(this.avgB02, d.avgB02);
		}
		if(ph.getSort().equals("avgB02") && ph.getOrder().equals("desc")){
			if(d.avgB02==null)return -1;if(this.avgB02==null)return 1;
			return Float.compare(d.avgB02, this.avgB02);
		}
		if(ph.getSort().equals("avgS01") && ph.getOrder().equals("asc")){
			if(this.avgS01==null)return -1;if(d.avgS01==null)return 1;
			return Float.compare(this.avgS01, d.avgS01);
		}
		if(ph.getSort().equals("avgS01") && ph.getOrder().equals("desc")){
			if(d.avgS01==null)return -1;if(this.avgS01==null)return 1;
			return Float.compare(d.avgS01, this.avgS01);
		}
		if(ph.getSort().equals("avgS03") && ph.getOrder().equals("asc")){
			if(this.avgS03==null)return -1;if(d.avgS03==null)return 1;
			return Float.compare(this.avgS03, d.avgS03);
		}
		if(ph.getSort().equals("avgS03") && ph.getOrder().equals("desc")){
			if(d.avgS03==null)return -1;if(this.avgS03==null)return 1;
			return Float.compare(d.avgS03, this.avgS03);
		}
		if(ph.getSort().equals("time") && ph.getOrder().equals("asc")){
			return this.time.compareTo(d.time);
		}
		if(ph.getSort().equals("time") && ph.getOrder().equals("desc")){
			return d.time.compareTo(this.time);
		}
		return 0;
	}
	
}
