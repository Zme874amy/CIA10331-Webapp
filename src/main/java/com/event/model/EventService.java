package com.event.model;

import java.sql.Date;
import java.util.List;

public class EventService {

	private EventDAO_interface dao;

	public EventService() {
		dao = new EventJDBCDAO();
	}

	public EventVO addEvent(Integer businessId, String name,
			Date startDate, Date endDate, Date delayDate,Integer catalogId, 
			Integer price, String description, Integer status, Integer maximum, 
			Integer minimum, Integer enrolled) {

		EventVO eventVO = new EventVO();
		
		eventVO.setBusinessId(businessId);
		eventVO.setName(name);
		eventVO.setStartDate(startDate);
		eventVO.setEndDate(endDate);
		eventVO.setDelayDate(delayDate);
		eventVO.setCatalogId(catalogId);
		eventVO.setPrice(price);
		eventVO.setDescription(description);
		eventVO.setStatus(status);
		eventVO.setMaximum(maximum);
		eventVO.setMinimum(minimum);
		eventVO.setEnrolled(enrolled);

		dao.insert(eventVO);

		return eventVO;
	}

	public EventVO updateEvent(Integer id, Integer businessId, String name,
			Date startDate, Date endDate, Date delayDate,Integer catalogId, 
			Integer price, String description, Integer status, Integer maximum, 
			Integer minimum, Integer enrolled) {

		EventVO eventVO = new EventVO();

		eventVO.setId(id);
		eventVO.setBusinessId(businessId);
		eventVO.setName(name);
		eventVO.setStartDate(startDate);
		eventVO.setEndDate(endDate);
		eventVO.setDelayDate(delayDate);
		eventVO.setCatalogId(catalogId);
		eventVO.setPrice(price);
		eventVO.setDescription(description);
		eventVO.setStatus(status);
		eventVO.setMaximum(maximum);
		eventVO.setMinimum(minimum);
		eventVO.setEnrolled(enrolled);
		
		dao.update(eventVO);

		return eventVO;
	}

	public void deleteEvent(Integer id) {
		dao.delete(id);
	}

	public EventVO getOneEvent(Integer id) {
		return dao.findByPrimaryKey(id);
	}

	public List<EventVO> getAll() {
		return dao.getAll();
	}
}
