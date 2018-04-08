package light.mvc.pageModel.biz;

import java.io.Serializable;
public class AlarmOutlierDataType implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String typeName;
	private String managerGradeCode;
	private String code;
	private Boolean isSendPs;
	private Boolean isSendMaintence;
	private Boolean isSendManage;
	private Boolean isSendCenter;
	private Boolean isSendOther;
	private String isSendPsStr;
	private String isSendMaintenceStr;
	private String isSendManageStr;
	private String isSendCenterStr;
	private String isSendOtherStr;
	
	
	public AlarmOutlierDataType() {
		super();
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getManagerGradeCode() {
		return managerGradeCode;
	}

	public void setManagerGradeCode(String managerGradeCode) {
		this.managerGradeCode = managerGradeCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Boolean getIsSendPs() {
		return isSendPs;
	}

	public void setIsSendPs(Boolean isSendPs) {
		this.isSendPs = isSendPs;
	}

	public Boolean getIsSendMaintence() {
		return isSendMaintence;
	}

	public void setIsSendMaintence(Boolean isSendMaintence) {
		this.isSendMaintence = isSendMaintence;
	}

	public Boolean getIsSendManage() {
		return isSendManage;
	}

	public void setIsSendManage(Boolean isSendManage) {
		this.isSendManage = isSendManage;
	}

	public Boolean getIsSendCenter() {
		return isSendCenter;
	}

	public void setIsSendCenter(Boolean isSendCenter) {
		this.isSendCenter = isSendCenter;
	}

	public Boolean getIsSendOther() {
		return isSendOther;
	}

	public void setIsSendOther(Boolean isSendOther) {
		this.isSendOther = isSendOther;
	}

	public String getIsSendPsStr() {
		return isSendPsStr;
	}

	public void setIsSendPsStr(String isSendPsStr) {
		this.isSendPsStr = isSendPsStr;
	}

	public String getIsSendMaintenceStr() {
		return isSendMaintenceStr;
	}

	public void setIsSendMaintenceStr(String isSendMaintenceStr) {
		this.isSendMaintenceStr = isSendMaintenceStr;
	}

	public String getIsSendManageStr() {
		return isSendManageStr;
	}

	public void setIsSendManageStr(String isSendManageStr) {
		this.isSendManageStr = isSendManageStr;
	}

	public String getIsSendCenterStr() {
		return isSendCenterStr;
	}

	public void setIsSendCenterStr(String isSendCenterStr) {
		this.isSendCenterStr = isSendCenterStr;
	}

	public String getIsSendOtherStr() {
		return isSendOtherStr;
	}

	public void setIsSendOtherStr(String isSendOtherStr) {
		this.isSendOtherStr = isSendOtherStr;
	}


	public AlarmOutlierDataType(int id, String typeName,
			String managerGradeCode, String code, Boolean isSendPs,
			Boolean isSendMaintence, Boolean isSendManage,
			Boolean isSendCenter, Boolean isSendOther, String isSendPsStr,
			String isSendMaintenceStr, String isSendManageStr,
			String isSendCenterStr, String isSendOtherStr) {
		super();
		this.id = id;
		this.typeName = typeName;
		this.managerGradeCode = managerGradeCode;
		this.code = code;
		this.isSendPs = isSendPs;
		this.isSendMaintence = isSendMaintence;
		this.isSendManage = isSendManage;
		this.isSendCenter = isSendCenter;
		this.isSendOther = isSendOther;
		this.isSendPsStr = isSendPsStr;
		this.isSendMaintenceStr = isSendMaintenceStr;
		this.isSendManageStr = isSendManageStr;
		this.isSendCenterStr = isSendCenterStr;
		this.isSendOtherStr = isSendOtherStr;
	}

	@Override
	public String toString() {
		return "AlarmOutlierDataType [id=" + id + ", typeName=" + typeName
				+ ", managerGradeCode=" + managerGradeCode + ", code=" + code
				+ ", isSendPs=" + isSendPs + ", isSendMaintence="
				+ isSendMaintence + ", isSendManage=" + isSendManage
				+ ", isSendCenter=" + isSendCenter + ", isSendOther="
				+ isSendOther + ", isSendPsStr=" + isSendPsStr
				+ ", isSendMaintenceStr=" + isSendMaintenceStr
				+ ", isSendManageStr=" + isSendManageStr + ", isSendCenterStr="
				+ isSendCenterStr + ", isSendOtherStr=" + isSendOtherStr + "]";
	}


}
