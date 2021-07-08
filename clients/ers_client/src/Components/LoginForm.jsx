import React from 'react';
import {useState,useEffect} from 'react';
import axios from 'axios';



const LoginForm = props =>{
    const [userName,setUserName] = useState("");
    const [pw, setPw] = useState("");
    const [showPass, setShowPass] = useState(false);
    const [errors,setErrors] = useState([]);
    const Login = e =>{
        e.preventDefault();
        const LoginUser = {
            "userName": userName,
            "password" : pw
        }
        axios.post(`${props.domain}/api/login`,LoginUser)
          .then(res=>{
            if (res.data.errors){   
              setErrors(res.data.errors);
            }
            else{
              console.log(res.data);
              props.finished(res.data);
            }
          })
          .catch(err=> console.log(err));

        //   axios.get(`${props.domain}/api/users?Id=4`)
        //   .then(res=>{
        //       console.log(res.data);
        //     //   let buffer = new Buffer(res.data,"binary");
        //     //   let textdata = buffer.toString();
        //     //   console.log(textdata);
        //     // const data = buffer.toJson();
        //     // console.log(data);
        //   })
        //   .catch(err=> console.log(err));
      }
    

    return(
        <>
        <form onSubmit={Login} className="col" >
        <h2 className="text-center">Login</h2>
            <label  htmlFor="userName"
                    className="row"
                    >User Name</label>
            <input  type="text" 
                    htmlFor="userName" 
                    value = {userName}
                    className="row w-100" 
                    onChange = {e=> setUserName(e.target.value)} />
            <label  htmlFor="pw"
                    className="row"
                    >Password</label>
            <input  type={showPass?"text":"password"} 
                    htmlFor="pw" 
                    value = {pw} 
                    className="row w-100"
                    onChange = {e=> setPw(e.target.value)} />
          
            <div className ="w-100 text-end">
                <small >
                <label htmlFor="showPass">Show pass</label>
                <input  type="checkbox"
                        htmlFor="showPass"
                        value={showPass}
                        className="form-check-input m-2"
                        onChange = {e=> setShowPass(e.target.value)}/>                        
                </small>
            </div>
         

            {errors? 
                errors.map( (e, i) => <p className="text-danger" key={i}> {e} </p>) 
                :
                null
            }
            <div className="d-flex justify-content-center w-100">
            
                <button type="submit" 
                        className="btn btn-success"
                        >Login
                </button>
            </div>
        </form>
        </>
    );
}

export default LoginForm;