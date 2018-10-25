package com.example.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.example.form.UserForm;
import com.example.model.TblCompany;
import com.example.service.CompanyService;
import com.example.service.UserService;
import com.example.utils.Common;
import com.example.validate.ValidateUser;

@Controller
public class AddController {
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	UserService userService;
	@Autowired
	ValidateUser validateUser;

	/**
	 * Handles requests for adding insurance (Do Get)
	 *
	 *
	 * @param userForm get data userForm from the user operation
	 *
	 * @param model
	 *
	 * @return login
	 *
	 */
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addUser(@ModelAttribute(Constant.ATTRIBUTE_NAME_ADD) UserForm userForm, Model model) {
        userForm = this.setDefaultValue(userForm);

        this.setDataLogic(model);

		model.addAttribute(Constant.ATTRIBUTE_NAME_ADD, userForm);

        return "addInsurance";
	}
	/**
	 * Handles requests for adding insurance (Do Post)
	 *
	 *
	 * @param userForm get data userForm from the user operation
	 *
	 * @param model
	 *
	 * @return login
	 *
	 */
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String submitUser(@ModelAttribute(Constant.ATTRIBUTE_NAME_ADD) UserForm userForm, Model model)throws ParseException {
        this.setDataLogic(model);

        userForm = this.setDefaultValue(userForm);

        List<String> listError = new ArrayList<>();
			listError = validateUser.validateUserInfo(userForm);
		if (listError.size() != 0) {
			model.addAttribute(Constant.LIST_ERROR, listError);
			return "addInsurance";
		} else {
			userService.createUser(userForm);

			//model.addAttribute("userForm", userForm);
			return "redirect:" + "home";
		}
	}
	/**
	 * Set value for userForm on view
	 *
	 * @param userForm get data userForm from the user operation
	 *
	 * @return object UserForm
	 *
	 */
	
	public UserForm setDefaultValue(UserForm userForm) {
		userForm.setInsuranceNumber(Common.getValueIfNull(userForm.getInsuranceNumber()));
		userForm.setUserFullName(Common.getValueIfNull(userForm.getUserFullName()));
		userForm.setUserName(Common.getValueIfNull(userForm.getUserName()));
		userForm.setPassword(Common.getValueIfNull(userForm.getPassword()));
		userForm.setUserSexDivision(Common.getValueIfNull(userForm.getUserSexDivision()));
		userForm.setBirthDay(Common.getValueIfNull(userForm.getBirthDay()));
		userForm.setCompanyId(Common.getValueIfNull(userForm.getCompanyId()));
        userForm.setCompanyName(Common.getValueIfNull(userForm.getCompanyName()));
		userForm.setAddress(Common.getValueIfNull(userForm.getAddress()));
		userForm.setEmail(Common.getValueIfNull(userForm.getEmail()));
		userForm.setFlagCompany(Common.getValueIfNull(userForm.getFlagCompany()));
		userForm.setTel(Common.getValueIfNull(userForm.getTel()));
		userForm.setRegister(Common.getValueIfNull(userForm.getRegister()));
		userForm.setStartDate(Common.getValueIfNull(userForm.getStartDate()));
		userForm.setEndDate(Common.getValueIfNull(userForm.getEndDate()));
		return userForm;
	}
	/**
	 * Set value for CompanyName on view
	 *
	 * @param model model
	 *
	 *
	 */
	
	public void setDataLogic(Model model) {
		List<TblCompany> companyList = companyService.findAll();
		model.addAttribute("listCompany", companyList);
	}
	/**
	 * Get information of company by Id
	 *
	 * @param companyId get value of companyId on view
	 *
	 *@return information of company
	 */
	@GetMapping(value = "/getCompanyInformationById/{companyId}")
    @ResponseBody
    public TblCompany getCompanyInformationById(@PathVariable("companyId") int companyId) {
        return companyService.findCompanyById(companyId);
    }

}
