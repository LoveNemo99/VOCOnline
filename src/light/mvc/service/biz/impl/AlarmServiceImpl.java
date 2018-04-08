package light.mvc.service.biz.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import light.mvc.dao.BaseDaoI;
import light.mvc.model.biz.TAlarmRecord;
import light.mvc.model.sys.TAlarmContact;
import light.mvc.model.sys.TAlarmType;
import light.mvc.pageModel.base.PageFilter;
import light.mvc.pageModel.base.Tree;
import light.mvc.pageModel.biz.AlarmRecord;
import light.mvc.service.biz.AlarmServiceI;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlarmServiceImpl implements AlarmServiceI {

	@Autowired
	private BaseDaoI<TAlarmRecord> recordDao;
	
	@Autowired
	private BaseDaoI<TAlarmType> typeDao;
	
	@Autowired
	private BaseDaoI<TAlarmContact> contactDao;

	/*-------------------------------------------------------------------------------------------------*/
	@Override
	public void add(AlarmRecord r) {
		TAlarmRecord t = new TAlarmRecord();
		BeanUtils.copyProperties(r, t);
		recordDao.save(t);
	}

	@Override
	public void delete(Integer id) {
		TAlarmRecord t = recordDao.get(TAlarmRecord.class, id);
		recordDao.delete(t);
	}

	@Override
	public void edit(AlarmRecord r) {
		TAlarmRecord t = recordDao.get(TAlarmRecord.class, r.getId());
		BeanUtils.copyProperties(r, t);
		recordDao.update(t);
	}

	@Override
	public AlarmRecord get(Long id) {
		TAlarmRecord t = recordDao.get(TAlarmRecord.class, id);
		AlarmRecord r = new AlarmRecord();
		BeanUtils.copyProperties(t, r);
		return r;
	}

	@Override
	public List<AlarmRecord> dataGrid(AlarmRecord demo, PageFilter ph) {
		List<AlarmRecord> ul = new ArrayList<AlarmRecord>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TAlarmRecord t ";
		List<TAlarmRecord> l = recordDao.find(hql + whereHql(demo, params) + orderHql(ph), params, ph.getPage(), ph.getRows());
		
		for (TAlarmRecord t : l) {
			AlarmRecord u = new AlarmRecord();
			BeanUtils.copyProperties(t, u);
			ul.add(u);
		}
		return ul;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public List<AlarmRecord> today(AlarmRecord demo, PageFilter ph) {
		if(demo==null){
			demo=new AlarmRecord();
		}
		if(ph==null){
			ph=new PageFilter();
		}
		ph.setPage(1);
		ph.setRows(50);
		ph.setOrder("desc");
		Date now=new Date();
		demo.setCreatDateTimeStart(new Date(now.getYear(),now.getMonth(),now.getDate()));
		demo.setCreatDateTimeEnd(now);
		List<AlarmRecord> ul = new ArrayList<AlarmRecord>();
		Map<String, Object> params = new HashMap<String, Object>();
		
		String hql = " from TAlarmRecord t ";
		List<TAlarmRecord> l = recordDao.find(hql + whereHql(demo, params) + orderHql(ph), params, ph.getPage(), ph.getRows());
		for (TAlarmRecord t : l) {
			//if(i++>50) break;
			AlarmRecord u = new AlarmRecord();
			BeanUtils.copyProperties(t, u);
			ul.add(u);
		}
		return ul;
	}

	
	@Override
	public Long count(AlarmRecord demo, PageFilter ph) {
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TAlarmRecord t ";
		return recordDao.count("select count(*) " + hql + whereHql(demo, params), params);
	}
	
	private String whereHql(AlarmRecord demo, Map<String, Object> params) {
		String hql = "";
		if (demo != null) {
			hql += " where 1=1 ";
			if (demo.getPsName() != null) {
				hql += " and t.psName like :name";
				params.put("name", "%%" + demo.getPsName() + "%%");
			}
			if (demo.getType() != null) {
				hql += " and t.type like :type";
				params.put("type", "%%" + demo.getType() + "%%");
			}
			if (demo.getCreatDateTimeStart() != null) {
				hql += " and t.time >= :createdatetimeStart";
				params.put("createDatetimeStart", demo.getCreatDateTimeStart());
			}
			if (demo.getCreatDateTimeEnd() != null) {
				hql += " and t.time <= :createdatetimeEnd";
				params.put("createDatetimeEnd", demo.getCreatDateTimeEnd());
			}
		}
		return hql;
	}
	
	/*-------------------------------------------------------------------------------------------------*/
	
	/*-------------------------------------------------------------------------------------------------*/
	private String orderHql(PageFilter ph) {
		String orderString = "";
		if ((ph.getSort() != null) && (ph.getOrder() != null)) {
			orderString = " order by t." + ph.getSort() + " " + ph.getOrder();
		}else if(ph.getOrder() != null){
			orderString = " order by t.id" + " " + ph.getOrder();
		}
		return orderString;
	}

	@Override
	public List<Tree> tree() {
		List<TAlarmType> l = null;
		List<Tree> lt = new ArrayList<Tree>();

		l = typeDao.find("select distinct t from TAlarmType t");
		if ((l != null) && (l.size() > 0)) {
			for (TAlarmType r : l) {
				Tree tree = new Tree();
				tree.setId(r.getId() + "");
				tree.setText(r.getType());
				//tree.setIconCls("icon-company");
				tree.setValue(r.getCode());
				lt.add(tree);
			}
		}
		return lt;
	}

}
