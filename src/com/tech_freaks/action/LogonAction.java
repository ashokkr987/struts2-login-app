package com.tech_freaks.action;


import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;
import com.tech_freaks.utils.DBHelper;

public class LogonAction extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String LOGIN_SUCCESS = "success";
	public static final String LOGIN_FAILED = "error";
	
	private static final String LOGIN_QUERY = "select * from users where user_name=? and password=?";
	
	private String userName;
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String execute() throws Exception {
		ValueStack stack = ActionContext.getContext().getValueStack();
		String[] params = new String[] {userName, password};
		DBHelper dbHelper = new DBHelper();
		
		boolean isSuccess = dbHelper.containsResult(LOGIN_QUERY, params);
		if(isSuccess)
			return LOGIN_SUCCESS;
		else {
			Map<String, Object> context = new HashMap<String, Object>();
			context.put("errorMsg", new String("Invalid user name or password. Try again.")); 
			stack.push(context);
			return LOGIN_FAILED;
		}
	}

	@Override
	public void validate() {
		 if (userName == null || userName.trim().equals(""))
	      {
	         addFieldError("userName","Please enter a user name to login with.");
	      }
		 if (password == null || password.trim().equals(""))
	      {
	         addFieldError("password","Please enter a password.");
	      }
	}

	
}
