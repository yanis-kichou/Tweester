import React, { Component } from 'react';
import axios from "axios";
import Logger from './logger';
export default class Loggers extends Component{
   constructor(props){
       super(props);
       this.state={
           loggers:[],
           modif:false
       }
        this.addFriend=this.addFriend.bind(this);   
   }
   addFriend(id){
       this.props.addFriend(id);
       this.setState({modif:!this.state.modif});
    }
   componentDidMount(){
    const url=new URLSearchParams();
    url.append("clef",this.props.clef);
    axios.get("http://127.0.0.1:8080/Tweester/loggers?"+url)
    .then(res=>{
        this.setState({loggers:res.data["loggers"]});
  });
    console.log(this.props.clef);    
   }
    render(){
    return ( <div class="left">
        <ul>
            <h2 class="suggestion ">Do you know ? </h2>
            {this.state.loggers.map(x=>
                    <li>
                        <Logger addFriend={this.addFriend}nom={x["nom"]} prenom={x["prenom"]} id={x["id"]} login={x["login"]}></Logger>
                    </li>
                )
              }

        </ul>
    </div>)
    }

}