package com.example.controller;

import com.example.form.SearchForm;
import com.example.model.TblCompany;
import com.example.model.TblUser;
import com.example.service.CompanyService;
import com.example.service.UserService;
import com.example.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class HomeController {
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home(@ModelAttribute("searchForm") SearchForm searchForm) {
		ModelAndView modelAndView = new ModelAndView();
		List<TblCompany> tblCompanies = companyService.findAll();
		modelAndView.addObject("tblCompanies", tblCompanies);
		System.out.println("tblCompanies11111111111111111111111111111111111" + tblCompanies.get(0));
		
		// lay các param người dùng nhập (1)
		// gọi function của service lấy data từ DB truyền các param get vể ở bước 1 (2)
		List<TblUser> tblUsers = userService.findAll();
//        if(tblUsers.size()>0){
		modelAndView.addObject("tblUsers", tblUsers);
//        }
//        else{
		// modelAndView.addObject("messageEroor", Constant.messagError);
		//  }
		
		List<TblUser> tblUserList = userService.findByUserFullName("dangthanhtung");
		List<TblUser> tblUserList1 =
				userService.findByUserFullNameAndInsuranceNumberAndregister("dangthanhtung", "1900123434", "Phúc Thọ");
		System.out.println("11111111111" + tblUserList.get(0).getUserFullName());
		modelAndView.addObject("tblUserList1", tblUserList1);
		String userFullName = searchForm.getUserFullName();
		System.out.println("tung88888888888888"+userFullName);
		List<TblUser> tblUserList2= userService.search(searchForm.getUserFullName(),searchForm.getInsuranceNumber(),searchForm.getRegister());
		System.out.println("222222222222222"+searchForm.getUserFullName());
		modelAndView.addObject("tblUserList2",tblUserList2);
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
}
