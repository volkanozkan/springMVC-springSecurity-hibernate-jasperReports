<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register Page</title>
<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-2.1.4.js" />"></script>
<script src="<c:url value="/resources/js/additional-methods.js" />"></script>
<script src="<c:url value="/resources/js/jquery.date-dropdowns.js" />"></script>
<script src="<c:url value="/resources/js/jquery.validate.js" />"></script>
<script>
			// Check If Any Inputs Are Blank.
            $(function () {
                $.validator.messages.required = '';
                function validation(fieldName, className) {
                    var name = $("#" + fieldName).val();
                    var null_name = $('.' + className).length;
                    if (name.length == 0 && null_name >= 1) {
                        if (className == 'null-username') {
                            $('.' + className).text('Username Cant Be Blank');
                        } else if (className == 'null-email') {
                            $('.' + className).text('Email Cant Be Blank');
                        } else if (className == 'null-password') {
                            $('.' + className).text('Password Cant Be Blank');
                        } else if (className == 'null-repassword') {
                            $('.' + className).text('Repassword Cant Be Blank');
                        }
                    } else {
                        $('.' + className).text('');
                        return true;
                    }
                }
                $("#registerForm").validate({
                    rules: {
                        repassword: {
                            equalTo: "#password"
                        }
                    }
                });

        function registerAjax() {
            var contextPath = "<%=request.getContextPath()%>";
			var username = $('#name').val();
			var email = $('#email').val();

			if (username.length > 0) {
				$.ajax({
					type : "POST",
					url : contextPath + "/registration",
					data : "username=" + username + "&email=" + email,
					success : function(response) {
						if (response.status == "success") {
							$('.exist-email').html("");
							$('.exist-username').html("");
							$("#submitButton").prop("disabled", false);
						} else if (response.status == "fail-email") {
							$("#submitButton").prop("disabled", true);
							$('.exist-username').html("Email exist!");
						} else {
							$("#submitButton").prop("disabled", true);
							$('.exist-email').html("Username exist!");
						}
					}
				});
			}
		}
		$('#name').on("blur", function() {
			console.log("blured -name!");
			validation("name", "null-username");
			registerAjax();
		});
		$('#email').on("blur", function() {
			validation("email", "null-email");
			registerAjax();
		});
		$('#password').on("blur", function() {
			validation("password", "null-password");
		});
		$('#repassword').on("blur", function() {
			validation("repassword", "null-repassword");
		});

	});
</script>
</head>
<body>
	<div id="register-panel">
		<b>Register</b>
		<form id="registerForm" method="post" action="adduser"
			commandName="users">
			<table>
				<tr>
					<td>Username:</td>
					<td><input id="name" type='text' required="required" name='username'></td>
				</tr>
				<tr>
					<td>Email</td>
					<td><input id="email" type='email' required="required"
						name='email' /></td>
				</tr>
				<tr>
					<td>Birth Day</td>
					<td><input type="date" id="birthday" required="required" name="birthday">
					</td>
				</tr>
				<tr>
					<td>Sex :</td>
					<td>Male <input type='radio' name='sex' value="0" required="required"
						checked="true" /> Female <input type='radio' name='sex' value="1" />
					</td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type='password' id="password" required="required"
						name='password' /></td>
				</tr>
				<tr>
					<td>RePassword</td>
					<td><input type='password' id="repassword" required="required"
						name='repassword' /></td>
				</tr>
				<tr>
					<td colspan='4'><input id="submitButton" type="submit"
						value='Register' />
				</tr>
				<tr>
				</tr>
			</table>
			<div id="error-panel">
				<p class="null-username"></p>
				<p class="null-email"></p>
				<p class="null-password"></p>
				<p class="null-repassword"></p>
				<p class="exist-username"></p>
				<p class="exist-email"></p>
			</div>
		</form>
	</div>
</body>
</html>