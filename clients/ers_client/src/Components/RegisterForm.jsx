import React from 'react';
import {useState,useEffect} from 'react';
import axios from 'axios';

const RegisterForm = props =>{
    const [userName,setUserName] = useState("");
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [email, setEmail] = useState("");
    const [pw, setPw] = useState("");
    const [showPass, setShowPass] = useState(false);
    const [errors,setErrors] = useState([]);
    const Register = e => {
        e.preventDefault();
        const newUser = {
            "userName" : userName,
            "email" : email,
            "password" : pw,
            "firstName" : firstName,
            "lastName" : lastName
        }
        axios.post(`${props.domain}/api/add?type=user`,newUser)
            .then(res => {
                console.log(res.data);
                if(res.data.errors){
                    setErrors(res.data.errors);
                }
                clearForm();
                const login = {
                    "email" : newUser.email,
                    "password" : newUser.pw
                }
                axios.post(`${props.domain}/api/login`,login)
                    .then(res => props.finish(res.data) )
                    .catch(err=> console.log(err));
            })
            .catch(err=>console.log(err));
    }

    const clearForm = () => {
        setFirstName("");
        setLastName("");
        setPw("");
        setEmail("");
        setUserName("");
        setErrors([]);
    }

    return (
        <>
        <form onSubmit={Register} className="col" >
            <h2 className="w-100 text-center">Register</h2>
            <div className="row w-100">
                <div className="col-6">
                    <label htmlFor="firstName">First Name</label>
                    <input  type="text" 
                            htmlFor="firstName" 
                            value = {firstName}
                            className="w-100"              
                            onChange = {e=> setFirstName(e.target.value)} />
                </div>
                <div className="col-6 ">
                    <label htmlFor="lastName">Last Name</label>
                    <input  type="text" 
                            htmlFor="lastName" 
                            value = {lastName}
                            className ="w-100" 
                            onChange = {e=> setLastName(e.target.value)} />
                </div>
            </div>
            <div className="row w-100 d-flex justify-content-between">
                <div className="col-6">
                    <label htmlFor="userName">User Name</label>
                    <input  type="text" 
                            htmlFor="userName" 
                            value = {userName} 
                            className ="w-100" 
                            onChange = {e=> setUserName(e.target.value)} />
                </div>
                <div className="col-6">
                    <label htmlFor="pw">Password</label>         
                    <input  type={showPass?"text":"password"} 
                            htmlFor="pw" 
                            value = {pw} 
                            className ="w-100" 
                            onChange = {e=> setPw(e.target.value)} />
                </div>
            </div>
            <div className ="w-100 d-flex justify-content-between">
                <label htmlFor="email">Email</label>
                <small >
                <label htmlFor="showPass">Show pass</label>
                <input  type="checkbox"
                        htmlFor="showPass"
                        checked={showPass}
                        className="form-check-input m-2"
                        onChange = {e=> setShowPass(e.target.checked)}/>        
                </small>
            </div>
            <div className="row justify-content-center">
                <div className="col">
                    <input  type="text" 
                            htmlFor="email" 
                            value = {email}
                            className="col-12"      
                            onChange = {e=> setEmail(e.target.value)} />
                </div>
            </div>
            {errors? 
                errors.map( (e, i) => <p className="text-danger" key={i}> {e} </p>) 
                :
                null
            }
            
            <div className="d-flex w-100 justify-content-center">
                <button type="submit" 
                        className="btn btn-success m-3"
                        >Register
                </button>
            </div>
            

        </form>
        </>
    );
}

export default RegisterForm;