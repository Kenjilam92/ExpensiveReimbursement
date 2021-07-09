 $(document).ready( function(){
            $('#mainPage').hide();
            $('#editProfile').hide();
            $('#Manager').hide();
            $('#User').hide();
            $('#Employee').hide();
            $('#headline').hide();

            //// Handle cookies
            var usesN = readCookie('userName');
            var pass =  readCookie('password');
            if( usesN && pass){
                login(usesN,pass);
            }
            
            ///// Handle LogoutForm
            $('#logout').click( function () { logout() } );
            
            ////// Handle RegisterForm
            $(document).on('submit', '#RegisterForm', function(e) {               
                e.preventDefault();
                var uN = $("#registerUn").val();
                var Em = $("#registerEm").val();
                var Fn = $("#registerFn").val();
                var Ln = $("#registerLn").val();
                var Pw = $("#registerPw").val();
                
                register(uN,Em,Fn,Ln,Pw);
                
            return false;
            });

            
            ////// Handle LoginForm
            $(document).on('submit', '#LoginForm', function(e) {               
                e.preventDefault();
                var inp = $("#loginusername").val();
                var pw =$("#loginpassword").val();
                
                login(inp,pw);
                
            return false;
            });


            /////Handle update profile form
            $(document).on('submit', '#ProfileForm', function(e) {  
                
                e.preventDefault();
                var userNameP = $('#profileUserName').val();
                var emailP = $('#profileEmail').val();
                var firstNameP = $('#profileFirstName').val();
                var lastNameP = $('#profileLastName').val();
                var passwordP = $('#profilePassword').val();
                var userIdP =  $('#profileUserId').val();
                postProfile(userIdP,userNameP,lastNameP,firstNameP,emailP, passwordP);
                
                return false;
            });

            ////////Handle request form

            $(document).on('submit', '#RequestForm', function(e) {  
                
                e.preventDefault();
                var cost =  parseInt($('#requestCost').val());
                var content = $('#requestContent').val();
                var userId =  parseInt($('#profileUserId').val());
                creatRequest(userId,content,cost);
                
                return false;
            });

        });
        
        ////////////////////////////////////////////////////////////////////////////////////out of main field    
        
        /// post Profile
        function postProfile (userIdP,userNameP,lastNameP,firstNameP,emailP, passwordP){
            $.ajax({
                type: "POST",
                url: "http://localhost:9090/ExpenseReimbursement/api/update?type=User",
                data: JSON.stringify({
                    userName : userNameP,
                    password : passwordP,
                    firstName : firstNameP,
                    lastName : lastNameP,
                    email : emailP,
                    userId : userIdP
                }),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function(data)
                {
                    console.log(data);
                },
                error: function(data){
                    console.log(data);
                }
            });
        }
        
        
        
        
        /// update Profile 

        function updateProfile (profile){
            $('#welcome').text(`Welcome, ${profile.firstName}!`)
            $('#job-title').text(`Title : ${profile.title}`)
            $('#profileUserName').attr("value",`${profile.userName}`);
            $('#profileEmail').attr("value",`${profile.email}`);
            $('#profileFirstName').attr("value",`${profile.firstName}`);
            $('#profileLastName').attr("value",`${profile.lastName}`);
            $('#profilePassword').attr("value",`${profile.password}`);
            $('#profileUserId').attr("value",profile.userId);
        }

        ///// cookies
        function createCookie(name, value, days) {
            var expires;

            if (days) {
                var date = new Date();
                date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
                expires = "; expires=" + date.toGMTString();
            } else {
                expires = "";
            }
            document.cookie = encodeURIComponent(name) + "=" + encodeURIComponent(value) + expires + "; path=/";
        }

        function readCookie(name) {
            var nameEQ = encodeURIComponent(name) + "=";
            var ca = document.cookie.split(';');
            for (var i = 0; i < ca.length; i++) {
                var c = ca[i];
                while (c.charAt(0) === ' ')
                    c = c.substring(1, c.length);
                if (c.indexOf(nameEQ) === 0)
                    return decodeURIComponent(c.substring(nameEQ.length, c.length));
            }
            return null;
        }

        function eraseCookie(name) {
            createCookie(name, "", -1);
        }

        //// register
        function register(uN,eM,fN,lN,pw){
            $.ajax({
                type: "POST",
                url: "http://localhost:9090/ExpenseReimbursement/api/add?type=User",
                data: JSON.stringify({
                    userName : uN,
                    email : eM,
                    firstName : fN,
                    lastName : lN,
                    password : pw
                }),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function(data)
                {
                    console.log(data);
                    
                },
                error: function(data){
                    console.log(data);
                }
            });


        }


        //// logout
        function logout(){
            eraseCookie('userName');
            eraseCookie('password');
            window.location.reload(false)
        }

        //// login
        function login(a,b){
            const inp = a;
            const pw = b
            console.log(a,b);
                $.ajax({
                    type: "POST",
                    url: "http://localhost:9090/ExpenseReimbursement/api/login",
                    data: JSON.stringify({
						userName : inp,
                    	password : pw			
					}),
                    contentType: "application/json; charset=utf-8",
    				dataType: "json",
                    success: function(data)
                    {   
                        console.log(data);
                        if(!data.errors){
                            $('#login').hide();
                            $('#mainPage').show();
                            $('#editProfile').show();
                            $('#headline').show();
                            createCookie("userName",inp);
                            createCookie("password",pw);
                            console.log(data); // show response from the php script.
                            switch (data.profile.title){
                                case"Manager":{
                                    showManager(data);
                                    break;
                                }
                                case"Employee":{
                                    showEmployee(data);
                                    break;
                                }
                                case"User":{
                                    $(`#User`).show();
                                    break;
                                }
                            }
                            const profile = data.profile;
                            updateProfile(profile);
                        }
                        
                        
                    },
                    error: function(data){
                        console.log(data);
                    }
                });
               
            }

        //// create request

        function creatRequest(Id, cont, price){
            console.log(Id,cont,price);
            $.ajax({
                type: "POST",
                url: "http://localhost:9090/ExpenseReimbursement/api/add?type=Request",
                data: JSON.stringify({
                    cost : price,
                    content : cont,
                    userId : Id
                }),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function(data)
                {
                    console.log(data);
                },
                error: function(data){
                    console.log(data);
                }
            });
            window.location.reload(false);
        }

        //////approve denny request

        function approveRequest (uId,rId){
            
            $.ajax({
                type: "POST",
                url: "http://localhost:9090/ExpenseReimbursement/api/update?type=Request",
                data: JSON.stringify({
                    userId: uId,
                    requestId : rId,
                    action: "approve"
                }),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function(data)
                {
                    console.log(data);
                },
                error: function(data){
                    console.log(data);
                }
            });
            window.location.reload(false);
        }

        function dennyRequest (uId,rId){
            
            $.ajax({
                type: "POST",
                url: "http://localhost:9090/ExpenseReimbursement/api/update?type=Request",
                data: JSON.stringify({
                    userId: uId,
                    requestId : rId,
                    action: "denny"
                }),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function(data)
                {
                    console.log(data);
                },
                error: function(data){
                    console.log(data);
                }
            });
            window.location.reload(false);
        }

        ///////hire user and remove employee 

        function removeEmployee (uId){
            $.ajax({
                type: "POST",
                url: "http://localhost:9090/ExpenseReimbursement/api/delete?type=Employee",
                data: JSON.stringify({
                    userId: uId,

                }),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function(data)
                {
                    console.log(data);
                },
                error: function(data){
                    console.log(data);
                }
            });
            window.location.reload(false);
        }

        function hireUser (mId, uId){
            $.ajax({
                type: "POST",
                url: "http://localhost:9090/ExpenseReimbursement/api/add?type=Employee",
                data: JSON.stringify({
                    userId: uId,
                    managerId: mId
                }),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function(data)
                {
                    console.log(data);
                },
                error: function(data){
                    console.log(data);
                }
            });
            window.location.reload(false);
        }

        /// Render Manager details 
        function showManager (data){
            console.log(data.pendingRequests);
 
            $('#Manager').show();
            const requestTable = document.querySelector('#bodyRequests');
            while(requestTable.firstChild){
                requestTable.removeChild(requestTable.firstChild);
            };
            const employeeTable = document.querySelector('#bodyEmployee');
            while(employeeTable.firstChild){
                employeeTable.removeChild(employeeTable.firstChild);
            };
            const userTable = document.querySelector('#bodyUser');
            while(userTable.firstChild){
                userTable.removeChild(userTable.firstChild);
            };
            
            data.pendingRequests.forEach(e => {
                    const row = document.createElement('tr')
                    const authorFirstName = document.createElement('td');
                    authorFirstName.innerText = `${e.authorFirstName}`;
                    row.appendChild(authorFirstName)

                    const authorLastName = document.createElement('td');
                    authorLastName.innerText = `${e.authorLastName}`;
                    row.appendChild(authorLastName)
                    
                    const content = document.createElement('td');
                    content.innerText = `${e.content}`;
                    row.appendChild(content)
                    

                    const cost = document.createElement('td');
                    cost.innerText = `${e.cost}`;
                    row.appendChild(cost);
                    
                    const status = document.createElement('td');
                    status.innerText = `${e.isApproved? e.isDennied ? "Error" : "Approve" : e.isDennied?"Dennied": "Pending" }`;
                    row.appendChild(status);
                    ////////////// specical button
                    const actions = document.createElement('td');
                    const approveButton = document.createElement('button');
                    approveButton.setAttribute("class","btn btn-sm btn-success");
                    approveButton.onclick = () => approveRequest( parseInt($('#profileUserId').val()),e.requestId);
                    approveButton.innerText = "Approve"

                    const dennyButton = document.createElement('button');
                    dennyButton.setAttribute("class","btn btn-sm btn-danger");
                    dennyButton.onclick = () => dennyRequest( parseInt($('#profileUserId').val()),e.requestId);
                    dennyButton.innerText ="Denny"

                    actions.appendChild(approveButton);
                    actions.appendChild(dennyButton);
                    row.appendChild(actions);

                    /////////////////////////
                    requestTable.appendChild(row);
            });
            
            
            data.teamMate.forEach(e => {
                if(e.userId !== data.profile.userId ){
                    const row = document.createElement('tr') 
                    
                    const firstName = document.createElement('td');
                    firstName.innerText = `${e.firstName}`;
                    row.appendChild(firstName)
                    
                    const lastName = document.createElement('td');
                    lastName.innerText = `${e.lastName}`;
                    row.appendChild(lastName)
                    
                    const userName = document.createElement('td');
                    userName.innerText = `${e.userName}`;
                    row.appendChild(userName);

                    const email = document.createElement('td');
                    email.innerText = `${e.email}`;
                    row.appendChild(email)

                    ////////////////////////////special button
                    
                    const actions = document.createElement('td');
                    const removeButton = document.createElement('button');
                    removeButton.setAttribute("class","btn btn-sm btn-secondary");
                    removeButton.onclick = () => removeEmployee( e.userId);
                    removeButton.innerText = "Remove"
                    actions.appendChild(removeButton);
                    row.appendChild(actions)

                    /////////////////////////////

                    employeeTable.appendChild(row);
                }
            });

            
            data.unhired.forEach(e => {
                    const row = document.createElement('tr')
                    
                    const userName = document.createElement('td');
                    userName.innerText = `${e.userName}`;
                    row.appendChild(userName);
                    
                    const firstName = document.createElement('td');
                    firstName.innerText = `${e.firstName}`;
                    row.appendChild(firstName)
                    
                    const lastName = document.createElement('td');
                    lastName.innerText = `${e.lastName}`;
                    row.appendChild(lastName)
                    
                    const email = document.createElement('td');
                    email.innerText = `${e.email}`;
                    row.appendChild(email)
                    
                    ////////////////////////////special button
                    
                    const actions = document.createElement('td');
                    const hireButton = document.createElement('button');
                    hireButton.setAttribute("class","btn btn-sm btn-primary");
                    hireButton.onclick = () => hireUser( data.profile.userId  ,e.userId);
                    hireButton.innerText = "Hire"
                    actions.appendChild(hireButton);
                    row.appendChild(actions)

                    /////////////////////////////

                    userTable.appendChild(row);
            });
        }

        function showEmployee(data){
            $('#Employee').show();
            const requestTable = document.querySelector('#bodyRequests2');
            while(requestTable.firstChild){
                requestTable.removeChild(requestTable.firstChild);
            };
            
            
            data.requests.forEach(e => {
                    const row = document.createElement('tr')
                    const authorFirstName = document.createElement('td');
                    authorFirstName.innerText = `${e.authorFirstName}`;
                    row.appendChild(authorFirstName)

                    const authorLastName = document.createElement('td');
                    authorLastName.innerText = `${e.authorLastName}`;
                    row.appendChild(authorLastName)
                    
                    const content = document.createElement('td');
                    content.innerText = `${e.content}`;
                    row.appendChild(content)
                    

                    const cost = document.createElement('td');
                    cost.innerText = `${e.cost}`;
                    row.appendChild(cost);
                    
                    const status = document.createElement('td');
                    status.innerText = `${e.isApproved? e.isDennied ? "Error" : "Approve" : e.isDennied?"Dennied": "Pending" }`;
                    row.appendChild(status);

                    requestTable.appendChild(row);
            });

            const employeeTable = document.querySelector('#bodyEmployee2');
            while(employeeTable.firstChild){
                employeeTable.removeChild(employeeTable.firstChild);
            };
            
            data.teamMate.forEach(e => {
                    const row = document.createElement('tr') 
                    
                    const firstName = document.createElement('td');
                    firstName.innerText = `${e.firstName}`;
                    row.appendChild(firstName)
                    
                    const lastName = document.createElement('td');
                    lastName.innerText = `${e.lastName}`;
                    row.appendChild(lastName)
                    
                    const userName = document.createElement('td');
                    userName.innerText = `${e.userName}`;
                    row.appendChild(userName);

                    const email = document.createElement('td');
                    email.innerText = `${e.email}`;
                    row.appendChild(email)
                    
                    employeeTable.appendChild(row);
            });
        }
