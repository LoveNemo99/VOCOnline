package light.mvc.model.biz;

import java.io.Serializable;

import javax.persistence.*;
@Entity
@Table(name = "biz_alarm_device_fault")
public class TAlarmDeviceFault implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String faultMessage;
	private Boolean isHandle;
	private String faultHandle;
	private String handleResult;
	private int alarmOutlierDataTypeId;

	public void setId(int id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	public int getId() {
		return id;
	}

	public TAlarmDeviceFault() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TAlarmDeviceFault(int id, String faultMessage, Boolean isHandle,
			String faultHandle, String handleResult, int alarmOutlierDataTypeId) {
		super();
		this.id = id;
		this.faultMessage = faultMessage;
		this.isHandle = isHandle;
		this.faultHandle = faultHandle;
		this.handleResult = handleResult;
		this.alarmOutlierDataTypeId = alarmOutlierDataTypeId;
	}

	@Override
	public String toString() {
		return "TAlarmContactPsPi [id=" + id + ", faultMessage=" + faultMessage
				+ ", isHandle=" + isHandle + ", faultHandle=" + faultHandle
				+ ", handleResult=" + handleResult
				+ ", alarmOutlierDataTypeId=" + alarmOutlierDataTypeId + "]";
	}

	@Column(name = "fault_message")
	public String getFaultMessage() {
		return faultMessage;
	}

	public void setFaultMessage(String faultMessage) {
		this.faultMessage = faultMessage;
	}

	@Column(name = "is_handle")
	public Boolean getIsHandle() {
		return isHandle;
	}

	public void setIsHandle(Boolean isHandle) {
		this.isHandle = isHandle;
	}

	@Column(name = "fault_handle")
	public String getFaultHandle() {
		return faultHandle;
	}

	public void setFaultHandle(String faultHandle) {
		this.faultHandle = faultHandle;
	}

	@Column(name = "handle_result")
	public String getHandleResult() {
		return handleResult;
	}

	public void setHandleResult(String handleResult) {
		this.handleResult = handleResult;
	}

	@Column(name = "alarm_outlier_data_type_id")
	public int getAlarmOutlierDataTypeId() {
		return alarmOutlierDataTypeId;
	}

	public void setAlarmOutlierDataTypeId(int alarmOutlierDataTypeId) {
		this.alarmOutlierDataTypeId = alarmOutlierDataTypeId;
	}


}
