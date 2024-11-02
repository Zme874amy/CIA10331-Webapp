package com.event.model;

import java.util.*;

public interface EventDAO_interface {
          public void insert(EventVO empVO);
          public void update(EventVO empVO);
          public void delete(Integer empno);
          public EventVO findByPrimaryKey(Integer empno);
          public List<EventVO> getAll();
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
