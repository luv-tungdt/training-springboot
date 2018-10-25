package com.example.validate;

import com.example.form.UserForm;
import com.example.service.InsuranceService;
import com.example.service.UserService;
import com.example.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ValidateUser {
	
	@Autowired
	UserService userService;
	
	@Autowired
	InsuranceService insuranceService;
	
	
	public List<String> validateUserInfo(UserForm userForm) throws ParseException {
		List<String> listError = new ArrayList<>();

		String insuranceNumber = userForm.getInsuranceNumber();

		String userFullName = userForm.getUserFullName();

		String userName = userForm.getUserName();

		String password = userForm.getPassword();

		String birthDay = userForm.getBirthDay();

		String address = userForm.getAddress();

		String email = userForm.getEmail();

		String companyName = userForm.getCompanyName();

		String tel = userForm.getTel();

		String register = userForm.getRegister();

		String startDate = userForm.getStartDate();

		String endDate = userForm.getEndDate();

		String companyFlag = userForm.getFlagCompany();

		validateInsuranceNumber(insuranceNumber, listError);

		validateUserFullname(userFullName, listError);

		validateUserName(userName, listError);

		validatePassword(password, listError);

		validateFormatDate(birthDay, listError);

		validateCompany(companyFlag, companyName, address, email, tel, listError);

		validateRegister(register, listError);

		validateFormatDate(startDate, listError);

		validateFormatDate(endDate, listError);

		validateCompareDate(startDate, endDate, listError);

		return listError;
	}
    /**
     * Validate User Full Name
     *
     * @param userFullName userFullName
     * @param listError list Error
     *
     */
	
	private void validateUserFullname(String userFullName, List listError) {
		if (userFullName.isEmpty()) {
			listError.add("Không được để trống tên người sử dụng");
		} else if (userFullName.length() >= 50) {
			listError.add("Tên người sử dụng không quá 50 kí tự");
		}
	}
    /**
     * Validate user Name
     *
     * @param userName userName
     * @param listError list Error
     *
     */
	
	private void validateUserName(String userName, List listError) {
		if (userName.isEmpty()) {
			listError.add("Không được để trống tên đăng nhập");
		} else if (userName.length() >= 16) {
			listError.add("Tên người sử dụng không quá 16 kí tự");
		}
	}
    /**
     * Validate Company
     *
     * @param companyFlag user Name
     * @param companyName company Name
     * @param address address
     * @param email email
     * @param tel tel
     * @param listError list Error
     *
     */
	
	public static void validateCompany(String companyFlag, String companyName, String address, String email, String tel,
			List listError) {
		if (companyFlag.equals("new")) {
			if (companyName.isEmpty()) {
				listError.add("Không được để trống tên công ty  sử dụng");
			} else if (companyName.length() >= 50) {
				listError.add("Tên công ty  sử dụng không quá 50 kí tự");
			}
			
			if (address.isEmpty()) {
				listError.add("Không được để trống địa chỉ công ty  sử dụng");
			} else if (address.length() >= 100) {
				listError.add("Địa chỉ người sử dụng không quá 50 kí tự");
			}
			
			if (email.isEmpty()) {
				listError.add("Không được để trống email công ty  sử dụng");
			} else if (email.length() >= 50) {
				listError.add("Email người sử dụng không quá 50 kí tự");
			}
			
			if (tel.isEmpty()) {
				listError.add("Không được để trống Số điện thoại công ty  sử dụng");
			} else if (tel.length() >= 15) {
				listError.add("Số điện thoại không quá 50 kí tự");
			}
		}
	}
    /**
     * Validate insurance Number
     *
     * @param insuranceNumber insuranceNumber
     * @param listError list Error
     *
     */
	public void validateInsuranceNumber(String insuranceNumber, List listError) {
		if (insuranceNumber.isEmpty()) {
			listError.add("Không được để trông mã số thẻ bảo hiểm");
		} else if (insuranceNumber.length() != 10) {
			listError.add("Mã số thẻ bảo hiểm phải có độ dài 10 chữ số");
		} else if (insuranceService.checkExistInsuranceNumber(insuranceNumber) == false) {
			listError.add("Mã số thẻ bảo hiểm không trùng nhau");
		}
	}
    /**
     * Validate date
     *
     * @param date date
     * @param listError list Error
     *
     */
	public void validateFormatDate(String date, List listError)throws ParseException {
		if (date.isEmpty()) {
			listError.add("Không được để trống ngày sinh");
		} else if (Common.checkFormatDate(date) == false) {
			listError.add("Hãy nhập đúng định dạng ngày");
		}
	}
    /**
     * Validate endDate insurance number
     *
     * @param startDate startDate
     * @param endDate endDate
     * @param listError list Error
     *
     */
	public void validateCompareDate(String startDate, String endDate, List listError) throws ParseException {

			if (Common.convertStringToDate(startDate).compareTo(Common.convertStringToDate(endDate)) > 0) {
				listError.add("Ngày kết thúc thẻ bảo hiểm phải sau ngày bắt đầu thẻ bảo hiểm");
			}

	}
    /**
     * Validate register place register
     *
     * @param register place register
     * @param listError list Error
     *
     */
	
	public void validateRegister(String register, List listError) {
		if (register.isEmpty()) {
			listError.add("Không được để trống nơi đăng ký KCB");
		} else if (register.length() >= 50) {
			listError.add("Nơi đăng ký KCB không quá 50 kí tự");
		}
	}
    /**
     * Validate Password
     *
     * @param password password
     * @param listError list Error
     *
     */
	public void validatePassword(String password, List listError) {
		if (password.isEmpty()) {
			listError.add("Không được để trống mật khẩu");
		} else if (password.length() >= 16) {
			listError.add("Mật khẩu người sử dụng không quá 16 kí tự");
		}
	}
	
}
