import './App.css';
import React from 'react';
import {useState, useEffect} from 'react';
import axios from 'axios';
// import  {Router} from "@reach/router";
/////////////CSS
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
/////// Components
import LoginForm from "./Components/LoginForm"  ;
import RegisterForm from "./Components/RegisterForm";
import Manager from "./Components/Manager";
import { useCookies } from 'react-cookie';
import TableUsers from './Components/TableUsers';
function App() {
  const [cookies,setCookie] = useCookies(['login']);
  const [data,setData] = useState({});

  const domain = "http://localhost:9090/ExpenseReimbursement";
  
  

  const Logout = (e) =>{
    setCookie('key',{}, {path:'/'})
    setCookie('Data',{}, {path:'/'})
    setData({});
  };

  const Login = e => {
    const temp = {
      "userName": e.profile.userName,
      "password": e.profile.password
    }
    setCookie('key', temp, {path:'/'} )
    setData(e);
  };

  useEffect(()=>{
    if(cookies.key){
      const temp = cookies.key;
      axios.post(`${domain}/api/login`,temp)
        .then( res => setData(res.data))
        .catch( err => console.log(err));
    }
  
  },[window])

  return (
    <>
    {Object.keys(data).length!==0?
    <div className="container p-5">
        <div className="d-flex justify-content-between">
          <span className="h1">Welcome {data.profile.firstName}!</span>
          <button className="btn btn-danger "
                  onClick={Logout}
                  > Logout 
          </button>
        </div>
        <div className="row mb-1">
          <p>Job Title: <span className="text-danger">{data.profile.title}</span></p>
        </div>
        {data.profile.title==="Manager"?
           <Manager data= {data} 
                    domain={domain}
                    ></Manager>
        :null}
    </div>
    :
    <div className="main-wrapper d-flex  align-items-center w-100">
        <div className="row w-100 justify-content-around">
          <div className="col-md-5">
              <LoginForm  domain= {domain}
                          finished= {Login}
                          >

              </LoginForm >
          </div>
          <div className="col-md-5">
              <RegisterForm domain={domain}
                            finished={Login}
                            >

              </RegisterForm>
          </div>
        
        </div>
    </div>
    }
    </>
  );
}

export default App;
