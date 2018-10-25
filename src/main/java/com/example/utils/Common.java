package com.example.utils;

import com.example.form.SearchForm;
import com.example.form.UserForm;
import com.example.model.TblCompany;
import com.example.model.TblInsurance;
import com.example.model.TblUser;
import org.springframework.web.context.request.WebRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Common {
	
	/**
	 * Calculate the total number of pages
	 *
	 * @param totalUser Total number of User
	 * @param limit display per page
	 *
	 * @return total page
	 */
	
	public static int getTotalPage(int totalUser, int limit) {
		int totalPage = Constant.DEFAULT_TOTAL_PAGE;
		if (totalUser % limit == 0) {
			totalPage = totalUser / limit;
		} else {
			totalPage = totalUser / limit + 1;
		}
		return totalPage;
	}
	
	/**
	 * Get data position to get
	 *
	 * @param currentPage current page
	 * @param limit display per page
	 * @return int vị trí cần lấy
	 *
	 */
	public static int getOffset(int currentPage, int limit) {
		int offset = (currentPage - 1) * limit;
		return offset;
	}
	
	/**
	 * The function calculates the logic to generate the page that needs to be displayed in the paging sequence
	 *  current page
	 *
	 * @param totalUser total User
	 * @param limit display per page
	 * @param currentPage current Page
	 *
	 * @return List<Integer> List of pages to display in the paging sequence under current page
	 *
	 */
	
	public static List<Integer> getListPaging(int totalUser, int limit, int currentPage) {
		List<Integer> listPage = new ArrayList<>();
		int totalPage = getTotalPage(totalUser, limit);
		int limitPage = Constant.DEFAULT_LIMIT_PAGE;
		int pagingFirst = getPagingFirst(currentPage, totalPage);
		int endPage = getEndPage(pagingFirst, limitPage, totalPage);
		for (int i = pagingFirst; i <= endPage; i++) {
			listPage.add(i);
		}
		return listPage;
	}
	
	/**
	 * Handles data from view
	 * 
	 * @param input data from view
	 *
	 * @return List<Integer> List of pages to display in the paging sequence under current page
	 *
	 */
	
	public static String getValueIfNull(String input) {
		return (input == null) ? "" : input;
	}
	
	/**
	 * get index of start page in list paging
	 *
	 * @param currentPage currentPage
	 * @param totalPage   totalPage
	 *
	 * @return index of start page
	 */
	
	public static int getPagingFirst(int currentPage, int totalPage) {
		if (totalPage > 5) {
			if (2 < currentPage && currentPage <= totalPage - 2) {
				return currentPage - 2;
			} else if (currentPage > totalPage - 2) {
				return currentPage / 2 - 1;
			}
		}
		return 1;
	}
	
	/**
	 * get index of end page
	 *
	 * @param startPage startPage
	 * @param limitPage limitPage
	 * @param totalPage totalPage
	 *
	 * @return index of end page
	 */
	public static int getEndPage(int startPage, int limitPage, int totalPage) {
		if (totalPage > 5) {
			return startPage + limitPage - 1;
		}
		return totalPage;
	}
	
	/**
	 * get total number of pages
	 *
	 * @param totalUser total user when search
	 * @param limit limit page
	 *
	 * @return total number of pages
	 */
	public static int totalPage(int totalUser, int limit) {
		int totalPage = 0;
		if (totalUser % limit == 0) {
			return totalPage = totalUser / limit; // tìm số trang, trường hợp chẵn
		} else {
			return totalPage = totalUser / limit + 1; // tìm số trang, trường hợp lẻ
		}
	}
	
	/**
	 * The function sets the data from the UserForm into TblUser to add new
	 *
	 *
	 * @param userForm userForm get on view
	 *
	 * @param tblUser set value for TblUser
	 *
	 */
	public static void setTblUser(UserForm userForm, TblUser tblUser) throws ParseException {
		tblUser.setUserFullName(userForm.getUserFullName());
        tblUser.setPassword(userForm.getPassword());
        tblUser.setUsername(userForm.getUserName());
		if (userForm.getUserSexDivision().equals("1")) {
			tblUser.setUserSexDivision('1');
		} else {
			tblUser.setUserSexDivision('2');
		}
		System.out.println(tblUser.getUserSexDivision());
			tblUser.setDate(convertStringToDate(userForm.getBirthDay()));

	}
	/**
	 * The function convert from a String to date format
	 *
	 *
	 * @param dateFormat string date to format
	 *
	 * @return format date
	 *
	 */

	
	public static Date convertStringToDate(String dateFormat) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		return formatter.parse(dateFormat);
	}
	
	/**
	 * The function sets the data from the UserForm into TblCompany to add new
	 *
	 *
	 *
	 * @param  userForm userForm get on view
	 *
	 * @param  tblCompany set value for TblUser
	 *
	 */
	public static void setTblCompany(UserForm userForm, TblCompany tblCompany) {
		tblCompany.setName(userForm.getCompanyName());
		tblCompany.setAddress(userForm.getAddress());
		tblCompany.setEmail(userForm.getEmail());
		tblCompany.setTel(userForm.getTel());
	}
	
	/**
	 * The function sets the data from the UserForm into TblInsurance to add new
	 *
	 *
	 *
	 * @param userForm userForm get on view
	 *
	 * @param tblInsurance set value for TblUser
	 *
	 */
	public static void setTblInsurance(UserForm userForm, TblInsurance tblInsurance) {
		tblInsurance.setNumberInsurance(userForm.getInsuranceNumber());
		tblInsurance.setRegister(userForm.getRegister());
		try {
			tblInsurance.setStartDate(convertStringToDate(userForm.getEndDate()));
			tblInsurance.setEndDate(convertStringToDate(userForm.getEndDate()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * Check format date
	 *
	 * @param date string date to format
	 * @return true if  Date Added is valid date and false if Date Added is invalid date
	 */
	public static boolean checkFormatDate(String date) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			formatter.parse(date);
		return true;
	}
}
