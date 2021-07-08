import React from 'react';
import {useState,useEffect} from "react";
import TableUsers from './TableUsers';
import TableRequests from './TableRequests'
import Profile from './Profile';
import axios from 'axios';

const Manager = props => {
    const domain = props.domain;
    
    const [profile,setProfile] = useState({});
    const [teamMate, setTeamMate] = useState([]);
    const [pendingRequestList, setPendingRequestList] = useState([]);
    const [unhired,setUnHired] = useState([]);
    
    useEffect(()=>{
        console.log(props);
        setProfile(props.data.profile);
        setTeamMate(props.data.teamMate);
        setUnHired(props.data.unhired);
        setPendingRequestList(props.data.pendingRequests)
    },props)
    
    const pendingTableButtons= [
        {
            "class" : "btn btn-success btn-sm",
            "buttonName" : "Approve",
            act : (e) => {
                console.log(e);
                axios.post(`${domain}/api/update?type=Request`,{
                    "userId" : profile.userId,
                    "requestId" : e,
                    "action" : "approve"
                })
                .then(res => console.log(res.data))
                .catch(err => console.log(err));
                window.location.reload(false);
            }
        },
        
        {
            "class": "btn btn-danger btn-sm",
            "buttonName" : "denny",
            act : (e) => {
                axios.post(`${domain}/api/update?type=Request`,{
                    "userId" : profile.userId,
                    "requestId" : e,
                    "action" : "denny"
                })
                .then(res => console.log(res.data))
                .catch(err => console.log(err));
                window.location.reload(false);
            }
        }

    ];

    return (
        <>
            <div className="row">
                <div className="col-md-7">
                    <TableRequests  requests={pendingRequestList}
                                    domain={domain}
                                    actions={pendingTableButtons}
                                    ></TableRequests>
                    <TableUsers users={teamMate}
                                domain={domain}
                                actions={[]}
                                ></TableUsers>
                    <TableUsers users={unhired}
                                domain={domain}
                                actions={[]}
                                ></TableUsers>
                </div>
                <div className="col-md-5">
                    <Profile    profile={profile}
                                domain={domain}
                                ></Profile>

                </div>
            </div>        
        
        </>    
    );
}

export default Manager