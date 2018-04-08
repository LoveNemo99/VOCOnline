package light.mvc.model.data;

import javax.persistence.Entity;
import javax.persistence.Table;

import light.mvc.model.sys.XAxis;

@Entity
@Table(name="xaxis_year")
public class YearXAxiasHistory extends XAxis {
	private static final long serialVersionUID = 1L;
}
