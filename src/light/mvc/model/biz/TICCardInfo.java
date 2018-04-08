
package light.mvc.model.biz;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
@Entity
@Table(name = "biz_ic_card_info")
public class TICCardInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer psBaseId;
	private Integer psPiId;
	private String cardNum;
	private String cardType;
	private String cardMan;
	private Date startTime;
	
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

	@Column(name = "card_num")
	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	@Column(name = "card_type")
	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	@Column(name = "card_man")
	public String getCardMan() {
		return cardMan;
	}

	public void setCardMan(String cardMan) {
		this.cardMan = cardMan;
	}

	@Column(name = "start_time")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public TICCardInfo() {
		super();
	}

	@Override
	public String toString() {
		return "TICCardInfo [id=" + id + ", psBaseId=" + psBaseId + ", psPiId="
				+ psPiId + ", cardNum=" + cardNum + ", cardType=" + cardType
				+ ", cardMan=" + cardMan + ", startTime=" + startTime + "]";
	}

}
