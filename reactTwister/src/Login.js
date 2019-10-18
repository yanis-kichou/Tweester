import React, { Component } from 'react';
import Signin from './Signin';
import "./login.css";
import axios from "axios";
import logo from "./tweester.png";
export default class Login extends Component{
    constructor(props){
        super(props);
        
        this.connect=this.connect.bind(this);
    }
    connect(){
        const url=new URLSearchParams();
        url.append("login",this.refs.log.value);
        url.append("password",this.refs.pass.value);
        axios.get("http://127.0.0.1:8080/Tweester/signin?"+url)
        .then(res=>{
            if(Object.keys(res.data).length===3){
                this.props.getConnected(this.refs.log.value,this.refs.pass.value,res.data["key"],res.data["idUser"]);
            }else{
                alert(res.data["Error!"]);
            }})
        .catch(erreur=> console.log(erreur+"erreur"));
    }
    
    render(){
        return (
        
            <form class="log">
                <img src={logo} alt="logo"></img>
                <input  type="login" ref="log" placeholder="Login" id="email"/>
                <input  type="password" ref="pass" placeholder="Password" id="password"/>
                <input type="Submit" onClick={(event)=>{event.preventDefault();this.connect()}} value="Login" class="login"/> 
                
                <Signin getLoggedAfterSignin={this.props.getLoggedAfterSignin}></Signin>
            </form>

        )
    }
}