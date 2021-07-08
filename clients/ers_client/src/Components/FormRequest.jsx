import React from 'react';
import {useState, useEffect} from 'react';
import axios from 'axios';

const FormRequest = props => {
    const [userId, setUserId] = useState(props.userId);
    const [content, setContent] = setState("");
    const [cost, setCost] = useState(0);

    useEffect(()=>{
        setUserId(props.userId);
    },[props])
    const Sent = e => {
        e.preventDefault();
        const request = {
            "userId" : userId,
            "content" : content,
            "cost" : cost
        }
        axios.post(`${props.domain}/api/add?type=request`,request)
            .then ( res =>{
                console.log(res.data);
            })
            .catch( err=>{
                console.log(err);
            })
        window.location.reload(false);
    }
    return (
        <form onSubmit={Sent}>
            <h3>Expense Reimbrussement Request</h3>
            <div className="row">
                <div className="col-7">
                    <input type="text" value={content} onChange={e=> setContent(e.target.value)}/>       
                </div>
                <div className="col-3">
                    <input type="number" min="0.01" step="0.01" value={cost} onChange={e => setCost(e.target.value)}/>
                </div>
                <div className="col-2">
                    <button type="submit" className="btn btn-success">Submit</button>
                </div>
            </div>
        </form>
    );
}

export default FormRequest;