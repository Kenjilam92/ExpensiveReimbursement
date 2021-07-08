import React from 'react';
import {useState,useEffect} from 'react';
import axios from 'axios';

const Profile = props =>{

    const [userId,setUserId] = useState(0);
    const [userName,setUserName] = useState("");
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [email, setEmail] = useState("");
    const [pw, setPw] = useState("");
    const [showPass, setShowPass] = useState(false);
    
    useEffect(()=>{
        axios.get(`${props.domain}/api/users?Id=${props.profile.userId}`)
            .then(res =>{
                console.log(res)
                const temp = res.data.profile;
                setUserId(temp.userId);
                setUserName(temp.userName);
                setEmail(temp.email);
                setPw(temp.password);
                setFirstName(temp.firstName);
                setLastName(temp.lastName);
            })
            .catch(err=>console.log(err));

    },[props]);
    
    
    
    const update = e => {
        e.preventDefault();
        const newUser = {
            "userId": userId,
            "userName" : userName,
            "email" : email,
            "password" : pw,
            "firstName" : firstName,
            "lastName" : lastName
        }
        axios.post(`${props.domain}/api/update?type=User`,newUser)
            .then(res => {
                console.log(res.data);
            })
            .catch(err=>console.log(err));
        window.location.reload(false);
    }

    return (
        <>
        <form onSubmit={update} className="col" >
            <div className="d-flex w-100 justify-content-between">
                <h2 className="w-100">Profile</h2>
                <button type="submit" 
                        className="btn btn-success"
                        >Update
                </button>
            </div>
            <div className="row p-1 mt-1">
                <label htmlFor="firstName" className="col-3">First Name</label>
                <input  type="text" 
                        htmlFor="firstName" 
                        value = {firstName}
                        className="col-9"              
                        onChange = {e=> setFirstName(e.target.value)} />
            </div>
            
            <div className="row p-1 mt-1">
                <label htmlFor="lastName" className="col-3">Last Name</label>
                <input  type="text" 
                        htmlFor="lastName" 
                        value = {lastName}
                        className ="col-9" 
                        onChange = {e=> setLastName(e.target.value)} />
            </div> 
            
            <div className="row p-1 mt-1">
                <label htmlFor="userName" className="col-3">User Name</label>
                <input  type="text" 
                        htmlFor="userName" 
                        value = {userName} 
                        className ="col-9" 
                        onChange = {e=> setUserName(e.target.value)} />
            </div>
            
            <div className="row p-1 mt-1">
                <label htmlFor="email" className="col-3">Email</label>
                <input  type="text" 
                        htmlFor="email" 
                        value = {email}
                        className="col-9"      
                        onChange = {e=> setEmail(e.target.value)} />
            </div>
            
            <div className="row p-1 mt-1">
                <label htmlFor="pw" className="col-3">Password</label>         
                <input  type={showPass?"text":"password"} 
                        htmlFor="pw" 
                        value = {pw} 
                        className ="col-9" 
                        onChange = {e=> setPw(e.target.value)} />
            </div>          
    
            <div className ="w-100 d-flex justify-content-end">
                <small >
                <label htmlFor="showPass">Show pass</label>
                <input  type="checkbox"
                        htmlFor="showPass"
                        checked={showPass}
                        className="form-check-input m-2"
                        onChange = {e=> setShowPass(e.target.checked)}/>        
                </small>
            </div>
                        
            
            

        </form>
        </>
    );
}

export default Profile;