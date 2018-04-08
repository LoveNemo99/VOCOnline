package light.mvc.model.data;

import javax.persistence.Entity;
import javax.persistence.Table;

import light.mvc.model.sys.XAxis;

@Entity
@Table(name="xaxis_month")
public class MonthXAxiasHistory extends XAxis {
	private static final long serialVersionUID = 1L;
}
