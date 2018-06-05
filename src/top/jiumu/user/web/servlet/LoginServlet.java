package top.jiumu.user.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.commons.CommonUtils;

import top.jiumu.user.domain.User;
import top.jiumu.user.service.UserService;

public class LoginServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		/*
		 * 1.获取表单数据,封装到User对象中
		 * 2.调用UserService的login方法,获取数据库中的User对象
		 * 3.判断返回的User对象是否为空
		 * 	不为空:判断username和password是否相同
		 * 		相同,登录成功,重定向到welcome.jsp
		 * 		不同,登录失败,保存错误信息,转发到login.jsp
		 * 	为空:保存错误信息,转发到regist.jsp
		 */
		User form = CommonUtils.toBean(request.getParameterMap(), User.class);
		
		UserService userService = new UserService();
		User _user = userService.login(form);
		
		if(_user!=null){
			if(!form.getUsername().equalsIgnoreCase(_user.getUsername())||!form.getPassword().equalsIgnoreCase(_user.getPassword())){
				request.setAttribute("msg", "用户名或密码错误!");
				request.getRequestDispatcher("/user/login.jsp").forward(request, response);
				return;
			}else{
				request.getSession().setAttribute("user", form);
				//request.getRequestDispatcher("/user/welcome.jsp").forward(request, response);
				response.sendRedirect(request.getContextPath()+"/user/welcome.jsp");
			}
		}else{
			request.setAttribute("user", form);
			request.setAttribute("msg", "您还没有注册,请注册后登录!");
			request.getRequestDispatcher("/user/regist.jsp").forward(request, response);
			return;
		}
	}

}
