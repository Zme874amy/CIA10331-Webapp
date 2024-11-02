package com.catalog.model;

import java.util.List;
import java.util.Set;
import com.event.model.EventVO;

public class CatalogService {

	private CatalogDAO_interface dao;

	public CatalogService() {
		dao = new CatalogJDBCDAO();
	}

	public List<CatalogVO> getAll() {
		return dao.getAll();
	}

	public CatalogVO getOneCatalog(Integer id) {
		return dao.findByPrimaryKey(id);
	}

	public Set<EventVO> getEventsById(Integer id) {
		return dao.getEventsById(id);
	}

	public void deleteCatalog(Integer id) {
		dao.delete(id);
	}
}
