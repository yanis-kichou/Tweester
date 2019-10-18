import React, { Component } from 'react';
import Logout from './Logout';
import Login from './Login';

export default class NavigationPannel extends Component{
    constructor(props){
        super(props);

    }
   
    render(){
        return(<nav>
            {this.props.isConnected ? <Logout setLogout={this.props.logout}></Logout>:<Login getLoggedAfterSignin={this.props.getLoggedAfterSignin} getConnected={this.props.getConnected} ></Login>} 
            </nav>
        )
    }

}