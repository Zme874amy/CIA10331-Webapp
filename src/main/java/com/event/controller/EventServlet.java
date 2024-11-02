package com.event.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.event.model.*;

public class EventServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入活動編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/event/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer id = null;
				try {
					id = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.add("活動編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/event/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				EventService eventSvc = new EventService();
				EventVO eventVO = eventSvc.getOneEvent(id);
				if (eventVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/event/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("eventVO", eventVO); // 資料庫取出的eventVO物件,存入req
				String url = "/back-end/event/listOneEvent.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEvent.jsp
				successView.forward(req, res);
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEvent.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數****************************************/
				Integer id = Integer.valueOf(req.getParameter("id"));
				
				/***************************2.開始查詢資料****************************************/
				EventService eventSvc = new EventService();
				EventVO eventVO = eventSvc.getOneEvent(id);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("eventVO", eventVO);         // 資料庫取出的eventVO物件,存入req
				String url = "/back-end/event/update_event_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_event_input.jsp
				successView.forward(req, res);
		}
		
		
		if ("update".equals(action)) { // 來自update_event_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer id = Integer.valueOf(req.getParameter("id").trim());
				Integer businessId = Integer.valueOf(req.getParameter("businessId").trim());
				
				String name = req.getParameter("name");
				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (name == null || name.trim().length() == 0) {
					errorMsgs.add("活動名稱: 請勿空白");
				} else if(!name.trim().matches(nameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("活動名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				
				java.sql.Date startDate = null;
				try {
					startDate = java.sql.Date.valueOf(req.getParameter("startDate").trim());
				} catch (IllegalArgumentException e) {
					startDate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入起始日期!");
				}
				
				java.sql.Date endDate = null;
				try {
					endDate = java.sql.Date.valueOf(req.getParameter("endDate").trim());
				} catch (IllegalArgumentException e) {
					endDate=null;
					errorMsgs.add("請輸入結束日期!");
				}
				
				java.sql.Date delayDate = null;
				try {
					delayDate = java.sql.Date.valueOf(req.getParameter("delayDate").trim());
				} catch (IllegalArgumentException e) {
					delayDate=null;
				}

				Integer catalogId = Integer.valueOf(req.getParameter("catalogId").trim());
				
				Integer price = null;
				try {
					price = Integer.valueOf(req.getParameter("price").trim());
				} catch (NumberFormatException e) {
					price = 0;
					errorMsgs.add("票價請填數字.");
				}
				
				Integer status = Integer.valueOf(req.getParameter("status").trim());

				String description = req.getParameter("description").trim();
//				if (description == null || description.trim().length() == 0) {
//					errorMsgs.add("描述請勿空白");
//				}	

				Integer maximum = null;
				try {
					maximum = Integer.valueOf(req.getParameter("maximum").trim());
				} catch (NumberFormatException e) {
					maximum = null;
//					errorMsgs.add("報名人數上限請填數字.");
				}
				
				Integer minimum = null;
				try {
					minimum = Integer.valueOf(req.getParameter("minimum").trim());
				} catch (NumberFormatException e) {
					minimum = null;
//					errorMsgs.add("報名人數下限請填數字.");
				}
				
				Integer enrolled = null;
				try {
					enrolled = Integer.valueOf(req.getParameter("enrolled").trim());
				} catch (NumberFormatException e) {
					enrolled = 0;
//					errorMsgs.add("已報名人數請填數字.");
				}


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


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
req.setAttribute("eventVO", eventVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/event/update_event_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				EventService eventSvc = new EventService();
				eventVO = eventSvc.updateEvent(id, businessId, name, startDate, endDate, delayDate, catalogId, price, description, status, maximum, minimum, enrolled);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("eventVO", eventVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/event/listOneEvent.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
		}

        if ("insert".equals(action)) { // 來自addEvent.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			Integer businessId = null;
			try {
				businessId = Integer.valueOf(req.getParameter("businessId").trim());
			} catch (NumberFormatException e) {
				businessId = null;
				errorMsgs.add("商家編號請填數字.");
			}
					
			
			String name = req.getParameter("name");
			String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (name == null || name.trim().length() == 0) {
				errorMsgs.add("活動名稱: 請勿空白");
			} else if(!name.trim().matches(nameReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("活動名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
            }
			
			
			java.sql.Date startDate = null;
			try {
				startDate = java.sql.Date.valueOf(req.getParameter("startDate").trim());
			} catch (IllegalArgumentException e) {
				startDate=new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入起始日期!");
			}
			
			java.sql.Date endDate = null;
			try {
				endDate = java.sql.Date.valueOf(req.getParameter("endDate").trim());
			} catch (IllegalArgumentException e) {
				endDate=null;
				errorMsgs.add("請輸入結束日期!");
			}
			
			java.sql.Date delayDate = null;
//			try {
//				delayDate = java.sql.Date.valueOf(req.getParameter("delayDate").trim());
//			} catch (IllegalArgumentException e) {
//				delayDate=null;
//			}
			
			Integer catalogId = Integer.valueOf(req.getParameter("catalogId").trim());
			
			Integer price = null;
			try {
				price = Integer.valueOf(req.getParameter("price").trim());
			} catch (NumberFormatException e) {
				price = 0;
//				errorMsgs.add("票價請填數字.");
			}
			
			Integer status = null;
			try {
				status = Integer.valueOf(req.getParameter("status").trim());
			} catch (NumberFormatException e) {
				status = 0;
				errorMsgs.add("狀態請填數字.");
			}

			String description = req.getParameter("description").trim();
//			if (description == null || description.trim().length() == 0) {
//				errorMsgs.add("描述請勿空白");
//			}	

			Integer maximum = null;
			try {
				maximum = Integer.valueOf(req.getParameter("maximum").trim());
			} catch (NumberFormatException e) {
				maximum = null;
//				errorMsgs.add("報名人數上限請填數字.");
			}
			
			Integer minimum = null;
			try {
				minimum = Integer.valueOf(req.getParameter("minimum").trim());
			} catch (NumberFormatException e) {
				minimum = null;
//				errorMsgs.add("報名人數下限請填數字.");
			}
			
//			Integer enrolled = null;
//			try {
//				enrolled = Integer.valueOf(req.getParameter("enrolled").trim());
//			} catch (NumberFormatException e) {
//				enrolled = 0;
//				errorMsgs.add("已報名人數請填數字.");
//			}


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
//			eventVO.setEnrolled(enrolled);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
req.setAttribute("eventVO", eventVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/event/addEvent.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				EventService eventSvc = new EventService();
				eventVO = eventSvc.addEvent(businessId, name, startDate, endDate, delayDate, catalogId, price, description, status, maximum, minimum, null);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/event/listAllEvent.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
				/***************************1.接收請求參數***************************************/
				Integer id = Integer.valueOf(req.getParameter("id"));
				
				/***************************2.開始刪除資料***************************************/
				EventService eventSvc = new EventService();
				eventSvc.deleteEvent(id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/event/listAllEvent.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
		}
	}
}
