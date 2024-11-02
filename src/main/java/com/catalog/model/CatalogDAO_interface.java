package com.catalog.model;

import java.util.*;

import com.event.model.EventVO;


public interface CatalogDAO_interface {
    public void insert(CatalogVO catalogVO);
    public void update(CatalogVO catalogVO);
    public void delete(Integer deptno);
    public CatalogVO findByPrimaryKey(Integer id);
    public List<CatalogVO> getAll();
    //查詢某類別的活動(一對多)(回傳 Set)
    public Set<EventVO> getEventsById(Integer id);
}
