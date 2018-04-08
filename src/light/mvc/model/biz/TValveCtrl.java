
package light.mvc.model.biz;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
@Entity
@Table(name = "biz_valve_ctrl")
public class TValveCtrl implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private int psBaseId;
	private int psPiId;
	private Date operateTime;
	private Date executeTime;
	private String valveState;
	private int executeItemId;
	private int resultMarkId;
	private int ctrlWayId;
	private String qn;

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

	@Column(name = "operate_time")
	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	@Column(name = "execute_time")
	public Date getExecuteTime() {
		return executeTime;
	}

	public void setExecuteTime(Date executeTime) {
		this.executeTime = executeTime;
	}

	@Column(name = "valve_state")
	public String getValveState() {
		return valveState;
	}

	public void setValveState(String valveState) {
		this.valveState = valveState;
	}

	@Column(name = "execute_item_id")
	public int getExecuteItemId() {
		return executeItemId;
	}

	public void setExecuteItemId(int executeItemId) {
		this.executeItemId = executeItemId;
	}

	@Column(name = "result_mark_id")
	public int getResultMarkId() {
		return resultMarkId;
	}

	public void setResultMarkId(int resultMarkId) {
		this.resultMarkId = resultMarkId;
	}

	@Column(name = "ctrl_way_id")
	public int getCtrlWayId() {
		return ctrlWayId;
	}

	public void setCtrlWayId(int ctrlWayId) {
		this.ctrlWayId = ctrlWayId;
	}

	@Column(name = "QN")
	public String getQn() {
		return qn;
	}

	public void setQn(String qn) {
		this.qn = qn;
	}

	public TValveCtrl() {
		super();
	}

	public TValveCtrl(int id, int psBaseId, int psPiId, Date operateTime,
			Date executeTime, String valveState, int executeItemId,
			int resultMarkId, int ctrlWayId, String qn) {
		super();
		this.id = id;
		this.psBaseId = psBaseId;
		this.psPiId = psPiId;
		this.operateTime = operateTime;
		this.executeTime = executeTime;
		this.valveState = valveState;
		this.executeItemId = executeItemId;
		this.resultMarkId = resultMarkId;
		this.ctrlWayId = ctrlWayId;
		this.qn = qn;
	}

	@Override
	public String toString() {
		return "TValveCtrl [id=" + id + ", psBaseId=" + psBaseId + ", psPiId="
				+ psPiId + ", operateTime=" + operateTime + ", executeTime="
				+ executeTime + ", valveState=" + valveState
				+ ", executeItemId=" + executeItemId + ", resultMarkId="
				+ resultMarkId + ", ctrlWayId=" + ctrlWayId + ", qn=" + qn
				+ "]";
	}

}
