
package light.mvc.pageModel.biz;

import java.io.Serializable;
import java.util.Date;
public class ICAccountInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer psBaseId;
	private String psBaseName;
	private String psPiName;
	private String pollutantName;
	private Integer psPiId;
	private Integer pollutantCodeId;
	private Float remainQuantity;
	private String mn;
	private String cardNum;
	private Date lastChangeTime;
	
	public String getPollutantName() {
		return pollutantName;
	}

	public void setPollutantName(String pollutantName) {
		this.pollutantName = pollutantName;
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

	public Float getRemainQuantity() {
		return remainQuantity;
	}

	public void setRemainQuantity(Float remainQuantity) {
		this.remainQuantity = remainQuantity;
	}

	public String getMn() {
		return mn;
	}

	public void setMn(String mn) {
		this.mn = mn;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public Date getLastChangeTime() {
		return lastChangeTime;
	}

	public void setLastChangeTime(Date lastChangeTime) {
		this.lastChangeTime = lastChangeTime;
	}

	public ICAccountInfo() {
		super();
	}

	@Override
	public String toString() {
		return "ICAccountInfo [id=" + id + ", psBaseId=" + psBaseId
				+ ", psBaseName=" + psBaseName + ", psPiName=" + psPiName
				+ ", pollutantName=" + pollutantName + ", psPiId=" + psPiId
				+ ", pollutantCodeId=" + pollutantCodeId + ", remainQuantity="
				+ remainQuantity + ", mn=" + mn + ", cardNum=" + cardNum
				+ ", lastChangeTime=" + lastChangeTime + "]";
	}


}
