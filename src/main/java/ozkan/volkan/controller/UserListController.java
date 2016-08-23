package ozkan.volkan.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import ozkan.volkan.config.JasperConnection;
import ozkan.volkan.model.Users;
import ozkan.volkan.service.UserService;

@Controller
public class UserListController {
	
	@Autowired
	UserService userService;

	@Autowired
	ServletContext context;
	
	// User List JSP
	@RequestMapping(value = "/userlist", method = RequestMethod.GET)
	public ModelAndView listUsersPage() {
		ModelAndView model = new ModelAndView();

		model.addObject("person", new Users());
		model.addObject("personList", this.userService.getAllUsers());
		model.setViewName("userlist");
		return model;
	}

	// Create PDF With Jasper
	@RequestMapping(value = "/createPDF", method = RequestMethod.POST)
	public String generateReport(HttpServletRequest request, HttpServletResponse response)
			throws JRException, SQLException {

		ServletContext context = request.getServletContext();
		String contextPath = context.getRealPath("/WEB-INF/jasper/JasperUser.jrxml");

		JasperConnection jasperConnection = new JasperConnection();
		Connection connection = jasperConnection.getConn();
		try {
			InputStream input = new FileInputStream(new File(contextPath));

			JasperDesign jasperDesign = JRXmlLoader.load(input);
			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, connection);

			String dir = HomeController.class.getResource("/").getFile();
			File file = new File(dir + "/Users.pdf");
			file.createNewFile();

			OutputStream output = new FileOutputStream(file);
			JasperExportManager.exportReportToPdfStream(jasperPrint, output);
			System.out.println("PDF Created In Here: " + file.getAbsolutePath());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return "redirect:/userlist";
	}
}
