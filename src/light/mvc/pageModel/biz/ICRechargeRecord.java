
package light.mvc.pageModel.biz;

import java.io.Serializable;
import java.util.Date;
public class ICRechargeRecord implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer psBaseId;
	private String psBaseName;
	private Integer psPiId;
	private String psPiName;
	private Integer pollutantCodeId;
	private String pollutantName;
	private Integer rechargeTypeId;
	private Date rechargeTime;
	private Integer rechargeStateId;
	private String rechargeMonth;
	private String cardNum;
	private Float rechargeQuantity;
	private String remark;

	public String getPsBaseName() {
		return psBaseName;
	}

	public void setPsBaseName(String psBaseName) {
		this.psBaseName = psBaseName;
	}

	public String getPsPiName() {
		return psPiName;
	}

	public void setPsPiName(String psPiName) {
		this.psPiName = psPiName;
	}

	public String getPollutantName() {
		return pollutantName;
	}

	public void setPollutantName(String pollutantName) {
		this.pollutantName = pollutantName;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public Integer getPsBaseId() {
		return psBaseId;
	}

	public void setPsBaseId(Integer psBaseId) {
		this.psBaseId = psBaseId;
	}

	public Integer getPsPiId() {
		return psPiId;
	}

	public void setPsPiId(Integer psPiId) {
		this.psPiId = psPiId;
	}

	public Integer getPollutantCodeId() {
		return pollutantCodeId;
	}

	public void setPollutantCodeId(Integer pollutantCodeId) {
		this.pollutantCodeId = pollutantCodeId;
	}

	public Integer getRechargeTypeId() {
		return rechargeTypeId;
	}

	public void setRechargeTypeId(Integer rechargeTypeId) {
		this.rechargeTypeId = rechargeTypeId;
	}

	public Date getRechargeTime() {
		return rechargeTime;
	}

	public void setRechargeTime(Date rechargeTime) {
		this.rechargeTime = rechargeTime;
	}

	public Integer getRechargeStateId() {
		return rechargeStateId;
	}

	public void setRechargeStateId(Integer rechargeStateId) {
		this.rechargeStateId = rechargeStateId;
	}

	public String getRechargeMonth() {
		return rechargeMonth;
	}

	public void setRechargeMonth(String rechargeMonth) {
		this.rechargeMonth = rechargeMonth;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public Float getRechargeQuantity() {
		return rechargeQuantity;
	}

	public void setRechargeQuantity(Float rechargeQuantity) {
		this.rechargeQuantity = rechargeQuantity;
	}


	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public ICRechargeRecord() {
		super();
	}

	@Override
	public String toString() {
		return "ICRechargeRecord [id=" + id + ", psBaseId=" + psBaseId
				+ ", psBaseName=" + psBaseName + ", psPiId=" + psPiId
				+ ", psPiName=" + psPiName + ", pollutantCodeId="
				+ pollutantCodeId + ", pollutantName=" + pollutantName
				+ ", rechargeTypeId=" + rechargeTypeId + ", rechargeTime="
				+ rechargeTime + ", rechargeStateId=" + rechargeStateId
				+ ", rechargeMonth=" + rechargeMonth + ", cardNum=" + cardNum
				+ ", rechargeQuantity=" + rechargeQuantity + ", qn=" + remark + "]";
	}

	
}
