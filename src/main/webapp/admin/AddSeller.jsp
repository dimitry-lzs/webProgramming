<%@ include file="/common.jsp" %>
<% int ID = (int) request.getAttribute("ID"); %>
<% String username = (String) request.getAttribute("username"); %>
<% String password = (String) request.getAttribute("password"); %>

<html>

    <head>
        <title>Create Form</title>
        <link rel="stylesheet" href="./style.css">
        <script>
            function validateForm() {
                const newUsername = document.getElementById('newUsername').value;
                const newPassword = document.getElementById('newPassword').value;
                const newConfirmPassword = document.getElementById('newConfirmPassword').value;



                if(newPassword !== newConfirmPassword){
                    alert("Passwords do not match");
                    return false;
                }

                if (newUsername.trim() === "" || newPassword.trim() === ""){
                    alert("Username and password cannot be empty or spaces");
                    return false;
                }

                return true;
            }
        </script>
    </head>

    <body>
        <section> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span> <span></span> <span></span> <span></span> <span></span> <span></span> <span></span>
            <span></span>
            <div class="signin">
                <div class="content">
                    <h2>Create Seller</h2>
                    <form class="form" action="create" method="post" onsubmit="return validateForm()">
                        <div class="inputBox">
                            <input type="text" id="newName" name="newName" required>
                            <i>Name</i>
                        </div>
                        <div class="inputBox">
                            <input type="text" id="newSurname" name="newSurname" required>
                            <i>Surname</i>
                        </div>
                        <div class="inputBox">
                            <input type="text" id="newUsername" name="newUsername" required>
                            <i>Username</i>
                        </div>
                        <div class="inputBox">
                            <input type="password" id="newPassword" name="newPassword" required>
                            <i>Password</i>
                        </div>
                        <div class="inputBox">
                            <input type="password" id="newConfirmPassword" name="newConfirmPassword" required>
                            <i>Confirm Password</i>
                        </div>
                        <div class="inputBox"> 
                            <input type="hidden" id="username" name="username" value="<%= username%>">
                            <input type="hidden" id="password" name="password" value="<%= password%>">
                            <input type="hidden" id="type" name="type" value="ADMIN">
                            <input type="hidden" id="option" name="option" value="SELLER">
                            <input type="hidden" id="ID" name="ID" value="<%= ID%>">
                            <input type="submit" value="create"> 
                        
                        </div> 
                    </form>
                </div>
            </div>
        </section>
    </body>

</html>