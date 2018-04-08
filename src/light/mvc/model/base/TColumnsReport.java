package light.mvc.model.base;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "columns_report")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TColumnsReport implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String unitCode;
	private String pollutantFactorCode;
	private Integer columnSort;
	private Integer columnWidth;
	private String columnName;
	private String dataType;
	private String type;

	public TColumnsReport() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "column_sort")
	public Integer getColumnSort() {
		return columnSort;
	}

	public void setColumnSort(Integer columnSort) {
		this.columnSort = columnSort;
	}

	@Column(name = "column_width")
	public Integer getColumnWidth() {
		return columnWidth;
	}

	public void setColumnWidth(Integer columnWidth) {
		this.columnWidth = columnWidth;
	}

	@Column(name = "column_name")
	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	@Column(name = "data_type")
	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	@Column(name = "unit_code")
	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	@Column(name = "pollutant_factor_code")
	public String getPollutantFactorCode() {
		return pollutantFactorCode;
	}

	public void setPollutantFactorCode(String pollutantFactorCode) {
		this.pollutantFactorCode = pollutantFactorCode;
	}

	@Column(name = "type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
