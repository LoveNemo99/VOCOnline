
package light.mvc.model.biz;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
@Entity
@Table(name = "biz_recharge_record")
public class TRechargeRecord implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private int psBaseId;
	private int psPiId;
	private int pollutantCodeId;
	private int rechargeTypeId;
	private Date rechargeTime;
	private int rechargeStateId;
	private String rechargeMonth;
	private String cardNum;
	private Float rechargeQuantity;
	private String remark;

	public void setId(int id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	public int getId() {
		return id;
	}
	
	@Column(name = "ps_base_id")
	public int getPsBaseId() {
		return psBaseId;
	}

	public void setPsBaseId(int psBaseId) {
		this.psBaseId = psBaseId;
	}

	@Column(name = "ps_pi_id")
	public int getPsPiId() {
		return psPiId;
	}

	public void setPsPiId(int psPiId) {
		this.psPiId = psPiId;
	}

	@Column(name = "pollutant_code_id")
	public int getPollutantCodeId() {
		return pollutantCodeId;
	}

	public void setPollutantCodeId(int pollutantCodeId) {
		this.pollutantCodeId = pollutantCodeId;
	}

	@Column(name = "recharge_type_id")
	public int getRechargeTypeId() {
		return rechargeTypeId;
	}

	public void setRechargeTypeId(int rechargeTypeId) {
		this.rechargeTypeId = rechargeTypeId;
	}

	@Column(name = "recharge_time")
	public Date getRechargeTime() {
		return rechargeTime;
	}

	public void setRechargeTime(Date rechargeTime) {
		this.rechargeTime = rechargeTime;
	}

	@Column(name = "recharge_state_id")
	public int getRechargeStateId() {
		return rechargeStateId;
	}

	public void setRechargeStateId(int rechargeStateId) {
		this.rechargeStateId = rechargeStateId;
	}

	@Column(name = "recharge_month")
	public String getRechargeMonth() {
		return rechargeMonth;
	}

	public void setRechargeMonth(String rechargeMonth) {
		this.rechargeMonth = rechargeMonth;
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

	public TRechargeRecord() {
		super();
		// TODO Auto-generated constructor stub
	}

}
