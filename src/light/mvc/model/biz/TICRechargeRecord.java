
package light.mvc.model.biz;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
@Entity
@Table(name = "biz_ic_recharge_record")
public class TICRechargeRecord implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer psBaseId;
	private Integer psPiId;
	private Integer pollutantCodeId;
	private Date rechargeTime;
	private String mn;
	private String cardNum;
	private Float rechargeQuantity;
	private String remark;

	public void setId(Integer id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	public Integer getId() {
		return id;
	}

	@Column(name = "ps_base_id")
	public Integer getPsBaseId() {
		return psBaseId;
	}

	public void setPsBaseId(Integer psBaseId) {
		this.psBaseId = psBaseId;
	}

	@Column(name = "ps_pi_id")
	public Integer getPsPiId() {
		return psPiId;
	}

	public void setPsPiId(Integer psPiId) {
		this.psPiId = psPiId;
	}

	@Column(name = "pollutant_code_id")
	public Integer getPollutantCodeId() {
		return pollutantCodeId;
	}

	public void setPollutantCodeId(Integer pollutantCodeId) {
		this.pollutantCodeId = pollutantCodeId;
	}

	@Column(name = "recharge_time")
	public Date getRechargeTime() {
		return rechargeTime;
	}

	public void setRechargeTime(Date rechargeTime) {
		this.rechargeTime = rechargeTime;
	}

	@Column(name = "card_num")
	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	@Column(name = "recharge_quantity")
	public Float getRechargeQuantity() {
		return rechargeQuantity;
	}

	public void setRechargeQuantity(Float rechargeQuantity) {
		this.rechargeQuantity = rechargeQuantity;
	}

	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "mn")
	public String getMn() {
		return mn;
	}

	public void setMn(String mn) {
		this.mn = mn;
	}

	public TICRechargeRecord() {
		super();
	}

	public TICRechargeRecord(Integer id, Integer psBaseId, Integer psPiId,
			Integer pollutantCodeId, Date rechargeTime, String mn,
			String cardNum, Float rechargeQuantity, String remark) {
		super();
		this.id = id;
		this.psBaseId = psBaseId;
		this.psPiId = psPiId;
		this.pollutantCodeId = pollutantCodeId;
		this.rechargeTime = rechargeTime;
		this.mn = mn;
		this.cardNum = cardNum;
		this.rechargeQuantity = rechargeQuantity;
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "TICRechargeRecord [id=" + id + ", psBaseId=" + psBaseId
				+ ", psPiId=" + psPiId + ", pollutantCodeId=" + pollutantCodeId
				+ ", rechargeTime=" + rechargeTime + ", mn=" + mn
				+ ", cardNum=" + cardNum + ", rechargeQuantity="
				+ rechargeQuantity + ", remark=" + remark + "]";
	}

	
}
