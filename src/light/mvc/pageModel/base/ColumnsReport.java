package light.mvc.pageModel.base;

import java.io.Serializable;

public class ColumnsReport implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String unitCode;
	private String unitSymbol;
	private String pollutantFactorCode;
	private Integer columnSort;
	private Integer columnWidth;
	private String columnName;
	private String dataType;
	private String type;

	public ColumnsReport() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPollutantFactorCode() {
		return pollutantFactorCode;
	}

	public void setPollutantFactorCode(String pollutantFactorCode) {
		this.pollutantFactorCode = pollutantFactorCode;
	}

	public Integer getColumnSort() {
		return columnSort;
	}

	public void setColumnSort(Integer columnSort) {
		this.columnSort = columnSort;
	}

	public Integer getColumnWidth() {
		return columnWidth;
	}

	public void setColumnWidth(Integer columnWidth) {
		this.columnWidth = columnWidth;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getUnitSymbol() {
		return unitSymbol;
	}

	public void setUnitSymbol(String unitSymbol) {
		this.unitSymbol = unitSymbol;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "ColumnsReport [id=" + id + ", unitCode=" + unitCode
				+ ", unitSymbol=" + unitSymbol + ", pollutantFactorCode="
				+ pollutantFactorCode + ", columnSort=" + columnSort
				+ ", columnWidth=" + columnWidth + ", columnName=" + columnName
				+ ", dataType=" + dataType + ", type=" + type + "]";
	}

}
