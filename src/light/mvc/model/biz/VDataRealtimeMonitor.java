package light.mvc.model.biz;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
@Entity
@Table(name = "v_data_realtime_monitor")
public class VDataRealtimeMonitor implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String psCode;
	private String piCode;
	private String type;
	private String electricState;
	private String valveState;
	private String onlineState;
	private Date onlineStateTime;
	private Date valveStateTime;
	private Date dataTime;
	private Float waterInstant;
	private Float waterYear;
	private Float codInstant;
	private Float codYear;
	private Float zlInstant;
	private Float zlYear;
	private Float adInstant;
	private Float adYear;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "ps_code")
	public String getPsCode() {
		return psCode;
	}

	public void setPsCode(String psCode) {
		this.psCode = psCode;
	}

	@Column(name = "pi_code")
	public String getPiCode() {
		return piCode;
	}

	public void setPiCode(String piCode) {
		this.piCode = piCode;
	}

	@Column(name = "type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "electric_state")
	public String getElectricState() {
		return electricState;
	}

	public void setElectricState(String electricState) {
		this.electricState = electricState;
	}

	@Column(name = "valve_state")
	public String getValveState() {
		return valveState;
	}

	public void setValveState(String valveState) {
		this.valveState = valveState;
	}

	@Column(name = "online_state")
	public String getOnlineState() {
		return onlineState;
	}

	public void setOnlineState(String onlineState) {
		this.onlineState = onlineState;
	}

	@Column(name = "online_state_time")
	public Date getOnlineStateTime() {
		return onlineStateTime;
	}

	public void setOnlineStateTime(Date onlineStateTime) {
		this.onlineStateTime = onlineStateTime;
	}

	@Column(name = "valve_state_time")
	public Date getValveStateTime() {
		return valveStateTime;
	}

	public void setValveStateTime(Date valveStateTime) {
		this.valveStateTime = valveStateTime;
	}

	@Column(name = "data_time")
	public Date getDataTime() {
		return dataTime;
	}

	public void setDataTime(Date dataTime) {
		this.dataTime = dataTime;
	}

	@Column(name = "water_instant")
	public Float getWaterInstant() {
		return waterInstant;
	}

	public void setWaterInstant(Float waterInstant) {
		this.waterInstant = waterInstant;
	}

	@Column(name = "water_year")
	public Float getWaterYear() {
		return waterYear;
	}

	public void setWaterYear(Float waterYear) {
		this.waterYear = waterYear;
	}

	@Column(name = "cod_instant")
	public Float getCodInstant() {
		return codInstant;
	}

	public void setCodInstant(Float codInstant) {
		this.codInstant = codInstant;
	}

	@Column(name = "cod_year")
	public Float getCodYear() {
		return codYear;
	}

	public void setCodYear(Float codYear) {
		this.codYear = codYear;
	}

	@Column(name = "zl_instant")
	public Float getZlInstant() {
		return zlInstant;
	}

	public void setZlInstant(Float zlInstant) {
		this.zlInstant = zlInstant;
	}

	@Column(name = "zl_year")
	public Float getZlYear() {
		return zlYear;
	}

	public void setZlYear(Float zlYear) {
		this.zlYear = zlYear;
	}

	@Column(name = "ad_instant")
	public Float getAdInstant() {
		return adInstant;
	}

	public void setAdInstant(Float adInstant) {
		this.adInstant = adInstant;
	}

	@Column(name = "ad_year")
	public Float getAdYear() {
		return adYear;
	}

	public void setAdYear(Float adYear) {
		this.adYear = adYear;
	}
	
}
