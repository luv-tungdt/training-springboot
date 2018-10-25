package com.example.controller;

import com.example.form.SearchForm;
import com.example.model.TblCompany;
import com.example.model.TblUser;
import com.example.service.CompanyService;
import com.example.service.UserService;
import com.example.utils.Common;
import com.example.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Controller
public class HomeController {
	
	private int DISPLAY_PER_PAGE = 3;
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	WebRequest webRequest;
	
	@Autowired
	HttpSession session;
	
	
	/**
	 * Handles requests for the application home page
	 *
	 *
	 * @param searchForm get data SearchFrom from the user operation
	 *
	 * @param model
	 *
	 * @return login
	 *
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(@ModelAttribute("searchForm") SearchForm searchForm, Model model) {

		int totalUser = getTotalUser(searchForm);
		
		setModelAtribute(webRequest, searchForm);
		addModelAttribute(model, searchForm, Constant.DEFAULT_OFFSET, totalUser, Constant.DEFAULT_CURRENT_PAGE);
		return "list";
	}
	
	/**
	 * Handles data from view
	 *
	 *
	 * @param page currentPage get form url
	 *
	 * @param model model
	 *
	 *
	 * @param webRequest Generic interface for a web request. Mainly intended for generic web
	 *
	 * @return login
	 *
	 */
	
	@RequestMapping(value = "/home/{page}", method = RequestMethod.GET)
	public String paging(@PathVariable("page") int page, Model model, WebRequest webRequest) {
		SearchForm searchForm = getModelAttribute(webRequest);
		
		int totalUser = getTotalUser(searchForm);
		int offset = Common.getOffset(page, DISPLAY_PER_PAGE);
		
		addModelAttribute(model, searchForm, offset, totalUser, page);
		return "list";
	}
	
	/**
	 * Export csv file fllowing conditions
	 *
	 * @param response object to get stream
	 *
	 * @throws IOException
	 */
	
	@RequestMapping(value = "/exportCSV", method = RequestMethod.GET)
	public void exportCSV(HttpServletResponse response) throws IOException {
		SearchForm searchForm = getModelAttribute(webRequest);
		
		response.setHeader("Content-type", "application/csv");
		response.setHeader("Content-disposition", "inline; filename=File.csv");
		
		StringBuilder dataCompany = new StringBuilder();
		
		dataCompany.append("Danh sách thông tin thẻ bảo hiểm " + "\n");
		dataCompany
			.append("Tên công ty,, " + companyService.findCompanyById(searchForm.getCompanyId()).getName() + "\n");
		dataCompany
			.append("Địa chỉ,, " + companyService.findCompanyById(searchForm.getCompanyId()).getAddress() + "\n");
		dataCompany.append("Email,, " + companyService.findCompanyById(searchForm.getCompanyId()).getEmail() + "\n");
		dataCompany
			.append("Số điện thoại,, " + companyService.findCompanyById(searchForm.getCompanyId()).getTel() + "\n");
		dataCompany.append("\n");
		
		List<TblUser> userList = userList(searchForm, 0, true);
		
		StringBuilder dataUser = new StringBuilder();
		String header =
				"Họ và tên, Giới tính, Ngày sinh, Mã số thẻ bảo hiểm, Ngày bắt đầu, Mã số thẻ bảo hiểm , Ngày bắt đầu, Ngày kết thúc, Nơi đăng ký KCB";
		dataUser.append(header + "\n");
		for (int i = 0; i < userList.size(); i++) {
			dataUser.append(userList.get(i).getUserFullName() + ", ");
			dataUser.append(userList.get(i).getUserSexDivision() + ", ");
			dataUser.append(userList.get(i).getDate() + ", ");
			dataUser.append(userList.get(i).getTblInsurance().getId() + ", ");
			dataUser.append(userList.get(i).getTblInsurance().getStartDate() + ", ");
			dataUser.append(userList.get(i).getTblInsurance().getEndDate() + ", ");
			dataUser.append(userList.get(i).getTblInsurance().getRegister() + ", ");
			dataUser.append("\n");
			
		}
		OutputStream outputStream = null;
			outputStream = response.getOutputStream();
			outputStream.write(239);
			outputStream.write(187);
			outputStream.write(191);
			outputStream.write(dataCompany.toString().getBytes("UTF-8"));
			outputStream.write(dataUser.toString().getBytes("UTF-8"));
			outputStream.flush();
			outputStream.close();
		
	}
	
	/**
	 * return view data
	 *
	 * @param model model
	 * @param  searchForm The value entered on the view
	 * @param  offset data location to get
	 * @param  totalUser totalUser
	 * @param  currentPage currentPage
	 *
	 *
	 */
	private void addModelAttribute(Model model, SearchForm searchForm, int offset, int totalUser, int currentPage) {
		model.addAttribute(Constant.MODEL_LIST_COMPANY, listCompany());
		model.addAttribute(Constant.ATTRIBUTE_NAME_SEARCH, searchForm);
		model.addAttribute(Constant.MODEL_LIST_USER, userList(searchForm, offset, false));
		model.addAttribute(Constant.MODEL_LIST_PAGE, Common.getListPaging(totalUser, DISPLAY_PER_PAGE, currentPage));
		model.addAttribute(Constant.END_PAGE, Common.getListPaging(totalUser, DISPLAY_PER_PAGE, currentPage).size());
		model.addAttribute(Constant.TOTAL_PAGE, Common.getTotalPage(totalUser, DISPLAY_PER_PAGE));
		model.addAttribute(Constant.CURRENT_PAGE, currentPage);
	}
	
	/**
	 Get the user parameters that were manipulated on the session
	 *
	 * @param webRequest Generic interface for a web request. Mainly intended for generic web
	 *
	 *@return SearchFrom searchForm
	 */
	private SearchForm getModelAttribute(WebRequest webRequest) {
		Object object = webRequest.getAttribute(Constant.ATTRIBUTE_NAME_SEARCH, WebRequest.SCOPE_SESSION);
		if (object == null) {
			SearchForm searchForm = new SearchForm();
			return searchForm;
		}
		return (SearchForm) object;
	}
	
	/**
	 * Get the user parameters already exploited on the interface and then save on
	 *
	 * @param webRequest Generic interface for a web request. Mainly intended for generic web
	 * @param  searchForm searchForm
	 *
	 *
	 */
	private void setModelAtribute(WebRequest webRequest, SearchForm searchForm) {
		webRequest.setAttribute(Constant.ATTRIBUTE_NAME_SEARCH, searchForm, WebRequest.SCOPE_SESSION);
	}
	
	/**
	
	 * Returns  list user according to conditions
	 *
	 * @param  searchForm searchForm
	 * @param  offset data location to get
	 * @param  isDownloadCSVFlag flags to distinguish file CSV call
	 *
	 *@return list User
	 */
	private List<TblUser> userList(SearchForm searchForm, int offset, boolean isDownloadCSVFlag) {
		
		String sortType = Common.getValueIfNull(webRequest.getParameter("sort"));
		int companyId = Integer.parseInt(String.valueOf(searchForm.getCompanyId()));
		String userFullName = Common.getValueIfNull(searchForm.getUserFullName());
		String insuranceNumber = Common.getValueIfNull(searchForm.getInsuranceNumber());
		String register = Common.getValueIfNull(searchForm.getRegister());
		
		if (isDownloadCSVFlag == true) {
			DISPLAY_PER_PAGE = 0;
		}
		
		return userService.getListUsers(companyId, userFullName, insuranceNumber, register, sortType, offset,
				DISPLAY_PER_PAGE);
	}
	
	/**
	 * The total company  displayed on the view
	 *
	 *
	 *@return list Company
	 */
	
	private List<TblCompany> listCompany() {
		
		return companyService.findAll();
	}
	
	/**
	 * Total user count by condition
	 *
	 * @param  searchForm searchform
	 *
	 *@return list User
	 */
	
	private int getTotalUser(SearchForm searchForm) {
		String userFullName = Common.getValueIfNull(searchForm.getUserFullName());
		String insuranceNumber = Common.getValueIfNull(searchForm.getInsuranceNumber());
		String register = Common.getValueIfNull(searchForm.getRegister());
		return userService.getTotalUser(userFullName, insuranceNumber, register);
	}
	
}
