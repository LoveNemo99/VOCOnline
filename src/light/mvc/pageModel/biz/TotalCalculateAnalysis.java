
package light.mvc.pageModel.biz;

import java.io.Serializable;
public class TotalCalculateAnalysis implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String psCode;
	private String code;
	private Float zt;
	private Float jw;
	private Float fjwzt;
	private Float ztAllow;
	private Float jwAllow;
	private Float fjwztAllow;

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPsCode() {
		return psCode;
	}
	public void setPsCode(String psCode) {
		this.psCode = psCode;
	}
	public Float getZtAllow() {
		return ztAllow;
	}
	public void setZtAllow(Float ztAllow) {
		this.ztAllow = ztAllow;
	}
	public Float getJwAllow() {
		return jwAllow;
	}
	public void setJwAllow(Float jwAllow) {
		this.jwAllow = jwAllow;
	}
	public Float getFjwztAllow() {
		return fjwztAllow;
	}
	public void setFjwztAllow(Float fjwztAllow) {
		this.fjwztAllow = fjwztAllow;
	}
	public Float getZt() {
		return zt;
	}
	public void setZt(Float zt) {
		this.zt = zt;
	}
	public Float getJw() {
		return jw;
	}
	public void setJw(Float jw) {
		this.jw = jw;
	}
	public Float getFjwzt() {
		return fjwzt;
	}
	public void setFjwzt(Float fjwzt) {
		this.fjwzt = fjwzt;
	}

	public TotalCalculateAnalysis(Integer id, String psCode, Float zt,
			Float jw, Float fjwzt, Float ztAllow, Float jwAllow,
			Float fjwztAllow) {
		super();
		this.id = id;
		this.psCode = psCode;
		this.zt = zt;
		this.jw = jw;
		this.fjwzt = fjwzt;
		this.ztAllow = ztAllow;
		this.jwAllow = jwAllow;
		this.fjwztAllow = fjwztAllow;
	}
	public TotalCalculateAnalysis() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
