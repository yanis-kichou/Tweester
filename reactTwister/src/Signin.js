import React, { Component } from 'react';
import axios from "axios";
export default class Signin extends Component{
    

    getLoggedAfterSignin(){
        const url = new URLSearchParams();
        url.append("nom",this.refs.nom.value);
        url.append("prenom",this.refs.prenom.value);
        url.append("login",this.refs.login.value);
        url.append("password",this.refs.password.value);
        url.append("tel",this.refs.tel.value);
        url.append("mail",this.refs.mail.value);
        url.append("sexe","h");
        axios.get("http://127.0.0.1:8080/Tweester/create?"+url)
        .then(res=>{
            if(Object.keys(res.data).length===1){
                this.props.getLoggedAfterSignin(this.refs.login.value,this.refs.password.value,this.refs.nom.value,this.refs.prenom.value,this.refs.tel.value,this.refs.mail.value,"h");
                console.log(document.getElementsByName("sexe").value);
            }else{
                alert(res.data["Error!"])
            }
        }
        );
        
       
    }

    render(){
        return(
                <form class="signin">
                    <h2>WELCOME</h2>
                    <input type="text" ref ="nom" placeholder="Nom"/>
                    <input type="text" ref="prenom" placeholder="Prenom"/>
                    <input type="mail" ref="login" placeholder="login"/>
                    <input type="password" ref="password" placeholder="password"/>
                    <input type="mail" ref="mail" placeholder="e-mail"/>
                    <input type="tel" ref="tel" placeholder="telephone"/>
                    <span id="select">
                        <label for="homme">homme</label><input type="radio" name="sexe" id="homme"/>
                        <label for="femme">femme</label><input type="radio" name="sexe" id="femme"/>
                    </span>
                    <input type="Submit" value="Sign in" onClick={(event)=>{event.preventDefault();this.getLoggedAfterSignin()}} 
                     class="login"/>
                </form>
            )
    }
}