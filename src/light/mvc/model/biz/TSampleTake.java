
package light.mvc.model.biz;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
@Entity
@Table(name = "biz_alarm_mode")
public class TSampleTake implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private int psBaseId;
	private int psPiId;
	private Date operateTime;
	private Date executeTime;
	private int executeItemId;
	private int resultMarkId;
	private int takeWayId;
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

	@Column(name = "take_way_id")
	public int getTakeWayId() {
		return takeWayId;
	}

	public void setTakeWayId(int takeWayId) {
		this.takeWayId = takeWayId;
	}

	@Column(name = "QN")
	public String getQn() {
		return qn;
	}

	public void setQn(String qn) {
		this.qn = qn;
	}

	public TSampleTake() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TSampleTake(int id, int psBaseId, int psPiId, Date operateTime,
			Date executeTime, int executeItemId, int resultMarkId,
			int takeWayId, String qn) {
		super();
		this.id = id;
		this.psBaseId = psBaseId;
		this.psPiId = psPiId;
		this.operateTime = operateTime;
		this.executeTime = executeTime;
		this.executeItemId = executeItemId;
		this.resultMarkId = resultMarkId;
		this.takeWayId = takeWayId;
		this.qn = qn;
	}

	@Override
	public String toString() {
		return "TSampleTake [id=" + id + ", psBaseId=" + psBaseId + ", psPiId="
				+ psPiId + ", operateTime=" + operateTime + ", executeTime="
				+ executeTime + ", executeItemId=" + executeItemId
				+ ", resultMarkId=" + resultMarkId + ", takeWayId=" + takeWayId
				+ ", qn=" + qn + "]";
	}

}
