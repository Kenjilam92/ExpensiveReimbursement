import React from 'react';
import {useState,useEffect} from 'react';
import axios from 'axios';

const TableRequests = props => {

    const [requests, setRequest] = useState([]);
    const [buttons,setButtons] = useState([]);
    useEffect(()=>{
        setRequest(props.requests)
        setButtons(props.actions)
        console.log(props);
    },[props])

    return (
        <table className="table table-sm table-hover">
            <thead className="bg-success text-white ">
                <tr>
                    <th>#</th>
                    <th>Name</th>
                    <th>Requests</th>
                    <th>Cost</th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
            {
                requests.map( (e,i) => 
                    <tr key={i}>
                        <td>{i}</td>
                        <td>{e.authorFirstName} {e.authorLastName}</td>
                        <td>{e.content}</td>
                        <td>{e.cost}</td>
                        <td>{
                            e.isApproved? e.isDennied? "Error": "Approve" : e.isDennied ? "Dennied" : "Pending"    
                        }</td>
                        <td><nav>
                            {buttons.map( (a,j) =>
                                <button className={a.class}
                                        onClick={ () => a.act(e.requestId)}
                                        key={"button"+i+"."+j}
                                        >{a.buttonName}
                                </button>
                            )}
                        </nav></td>
                    </tr>
                )
            }    
            </tbody>
        </table>
    );
}
export default TableRequests