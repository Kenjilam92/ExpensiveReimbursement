import React from 'react';
import {useState,useEffect} from 'react';

import axios from 'axios';

const TableUsers = props => {
    const [list,setList] = useState([]);
    useEffect( ()=> {
        setList(props.users);
        console.log(list);
    },[props]);
    return (
        <table className="table table-sm table-hover table-bordered">
            <thead className="bg-primary text-white text-center">
                <tr>
                    <th>#</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>User Name</th>
                    <th>Email</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
            {   
                
                list.map( (e,i) => 
                    <tr key={i}>
                        <td>{i}</td>
                        <td>{e.firstName}</td>
                        <td>{e.lastName}</td>
                        <td>{e.userName}</td>
                        <td>{e.email}</td>
                        <td className="text-center"><nav>
                           helo
                        </nav></td>
                    </tr>
                )
            
            }    
            </tbody>
        </table>
    );
}
export default TableUsers