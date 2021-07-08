import React from 'react';
import {useState,useEffect} from 'react';
import axios from 'axios';

const TablePendingRequests = props => {
    const [requests,setRequests] = useState([]);
    useEffect(()=>{
        setRequests(props.pendingRequests)
    },[props]);
    
    const [approve,setApprove] = useState(false);
    const [denny,setDenny] = useState(false);
    

    return (
        <table className="table">
            <thead className="thead-dark">
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
                props.requests.map( (e,i) => {
                    <tr key={i}>
                        <td>{i}</td>
                        <td>{e.authorFirstName} {e.authotLastName}</td>
                        <td>{e.content}</td>
                        <td>{e.cost}</td>
                        <td>{e.status}</td>
                        <td><nav>
                            <button className="btn btn-success"> action 1</button>
                            <button className="btn btn-warnning"> action 2</button>
                            <button className="btn btn-danger"> action 3</button>
                        </nav></td>
                    </tr>
                })
            }    
            </tbody>
        </table>
    );
}
export default TablePendingRequests