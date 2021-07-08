import React from 'react';
import {useState,useEffect} from 'react';
import axios from 'axios';

const TableUsers = props => {
    return (
        <table className="table table-striped">
            <thead className="thead-dark">
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
                props.users.map( (e,i) => {
                    <tr key={i}>
                        <td>{i}</td>
                        <td>{e.fistName}</td>
                        <td>{e.lastName}</td>
                        <td>{e.userName}</td>
                        <td>{e.email}</td>
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
export default TableUsers