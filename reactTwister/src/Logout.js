import React, { Component } from 'react';
 
export default class Logout extends Component{
    
    
    render(){
        return (
        <button onClick={(event)=>this.props.setLogout()}>
            logout
        </button>)
    }
}