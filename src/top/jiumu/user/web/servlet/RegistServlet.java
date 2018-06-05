package top.jiumu.user.web.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import top.jiumu.user.domain.User;
import top.jiumu.user.service.UserException;
import top.jiumu.user.service.UserService;
import cn.itcast.commons.CommonUtils;

public class RegistServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		UserService userService = new UserService();
		
		/*
		 * 获取表单数据封装到User对象中
		 */
		User form = CommonUtils.toBean(request.getParameterMap(), User.class);
		
		/**
		 * 表单数据数据校验
		 * 1.创建一个Map,用来装在所有错误信息
		 * 	在校验是如果出现错误就以表单字段为key,像Map中添加错误信息
		 * 2.校验之后查看map长度是否为空和是否大于0
		 * 	如果大于0,说明有错误信息,那么就有错误,然后保存map到request域中,保存user到request域中用来回显
		 * 	如果为空,并且不大于0,说明没有错误,那么就不做处理,继续向下执行
		 */
		Map<String,String> errors = new HashMap<String,String>();//创建Map
		//获取Username
		String username  = form.getUsername();
		//校验Username是否符合要求
		if(username == null||username.trim().isEmpty()){
			errors.put("username", "用户名不能为空!");
		}else if(username.length()<3||username.length()>15){
			errors.put("username", "用户名长度应为3~15!");
		}
		//获取password
		String password = form.getPassword();
		//校验password是否符合要求
		if(password == null||password.trim().isEmpty()){
			errors.put("password", "密码不能为空!");
		}else if(password.length()<3||password.length()>15){
			errors.put("password", "密码长度应为3~15!");
		}
		
		
		//校验验证码是否符合要求
		String verifyCode = form.getVerifyCode();
		String sessionVerifyCode = (String) request.getSession().getAttribute("session_VerifyCode");
		if(verifyCode == null||verifyCode.trim().isEmpty()){
			errors.put("verifyCode", "验证码不能为空!");
		}else if(verifyCode.length()!=4){
			errors.put("verifyCode", "验证码长度应为4!");
		}else if(!verifyCode.equalsIgnoreCase(sessionVerifyCode)){
			errors.put("verifyCode", "验证码有误!");
		}
		
		/**
		 * 查看map的长度是否大于0
		 */
		if(errors != null&&errors.size()>0){//map大于0或者不为空
			request.setAttribute("errors",errors);//保存map到request域中
			request.setAttribute("user", form);//保存user到request域中,用来回显
			//转发到regist.jsp
			request.getRequestDispatcher("/user/regist.jsp").forward(request, response);
			return;
		}
		
		
		/*
		 *调用UserService的regist()方法完成添加 
		 */
		try {
			userService.regist(form);
			request.setAttribute("user",form);
			request.setAttribute("msg", "注册成功!");
			request.getRequestDispatcher("/user/login.jsp").forward(request, response);
		} catch (UserException e) {
			/*
			 * 获取异常信息保存到reuqest域中,转发到regist.jsp
			 */
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("user", form);//表单回显
			request.getRequestDispatcher("/user/regist.jsp").forward(request, response);
		}
	}

}
