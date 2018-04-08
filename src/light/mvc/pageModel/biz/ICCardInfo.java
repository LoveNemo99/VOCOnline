
package light.mvc.pageModel.biz;

import java.io.Serializable;
import java.util.Date;
public class ICCardInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer psBaseId;
	private String psBaseName;
	private Integer psPiId;
	private String psPiName;
	private String cardNum;
	private String cardType;
	private String cardMan;
	private Date startTime;
	
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

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardMan() {
		return cardMan;
	}

	public void setCardMan(String cardMan) {
		this.cardMan = cardMan;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public ICCardInfo() {
		super();
	}

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

	@Override
	public String toString() {
		return "ICCardInfo [id=" + id + ", psBaseId=" + psBaseId
				+ ", psBaseName=" + psBaseName + ", psPiId=" + psPiId
				+ ", psPiName=" + psPiName + ", cardNum=" + cardNum
				+ ", cardType=" + cardType + ", cardMan=" + cardMan
				+ ", startTime=" + startTime + "]";
	}


}
