
package light.mvc.model.biz;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
@Entity
@Table(name = "biz_ic_account_info")
public class TICAccountInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer psBaseId;
	private Integer psPiId;
	private Integer pollutantCodeId;
	private Float remainQuantity;
	private String mn;
	private String cardNum;
	private Date lastChangeTime;
	
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

	@Column(name = "remain_quantity")
	public Float getRemainQuantity() {
		return remainQuantity;
	}

	public void setRemainQuantity(Float remainQuantity) {
		this.remainQuantity = remainQuantity;
	}

	@Column(name = "mn")
	public String getMn() {
		return mn;
	}

	public void setMn(String mn) {
		this.mn = mn;
	}

	@Column(name = "card_num")
	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	@Column(name = "last_change_time")
	public Date getLastChangeTime() {
		return lastChangeTime;
	}

	public void setLastChangeTime(Date lastChangeTime) {
		this.lastChangeTime = lastChangeTime;
	}

	public TICAccountInfo() {
		super();
	}

	public TICAccountInfo(Integer id, Integer psBaseId, Integer psPiId,
			Integer pollutantCodeId, Float remainQuantity, String mn,
			String cardNum, Date lastChangeTime) {
		super();
		this.id = id;
		this.psBaseId = psBaseId;
		this.psPiId = psPiId;
		this.pollutantCodeId = pollutantCodeId;
		this.remainQuantity = remainQuantity;
		this.mn = mn;
		this.cardNum = cardNum;
		this.lastChangeTime = lastChangeTime;
	}

	@Override
	public String toString() {
		return "TICAccountInfo [id=" + id + ", psBaseId=" + psBaseId
				+ ", psPiId=" + psPiId + ", pollutantCodeId=" + pollutantCodeId
				+ ", remainQuantity=" + remainQuantity + ", mn=" + mn
				+ ", cardNum=" + cardNum + ", lastChangeTime=" + lastChangeTime
				+ "]";
	}

}
