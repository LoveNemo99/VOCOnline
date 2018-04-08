package light.mvc.model.data;

import javax.persistence.Entity;
import javax.persistence.Table;

import light.mvc.model.sys.XAxis;

@Entity
@Table(name="xaxis_day")
public class DayXAxiasHistory extends XAxis {
	private static final long serialVersionUID = 1L;
}
