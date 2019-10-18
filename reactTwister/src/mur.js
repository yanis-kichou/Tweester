import React, { Component } from 'react';
import logo from "./tweester.png";
import Logout from './Logout';
import "./mur.css";
import TweetList from './tweetList';
import Loggers from "./Loggers";
import axios from 'axios';
export default class mur extends Component{
    constructor(props){
        super(props)
        this.state={
            listTweet:[],
            add:true
        }
        this.addTweet=this.addTweet.bind(this);
        this.addFriend=this.addFriend.bind(this);
    }
    addFriend(id){
        const url=new URLSearchParams();
        url.append("idFriend",id);
        url.append("key",this.props.clef); 
        axios.get("http://127.0.0.1:8080/Tweester/addfriend?"+url)
        .then(res=>{console.log(res.data);
        });
        this.state.add==true?this.setState({add:false}):this.setState({add:true});
    }
    addTweet(txt){
        const url=new URLSearchParams();
        url.append("text",txt);
        url.append("key",this.props.clef);
        axios.get("http://127.0.0.1:8080/Tweester/addcomment?"+url)
        .then(res=>{console.log(res.data);
            this.setState({add:!this.state.add});    
        });
        this.state.add==true?this.setState({add:false}):this.setState({add:true});
        this.forceUpdate();
    }
    componentDidMount(){
        const url=new URLSearchParams();
        url.append("key",this.props.clef);
        axios.get("http://127.0.0.1:8080/Tweester/posts?"+url)
        .then(res=>{
            this.setState({listTweet:res.data["Comments"]});
        })
    }
    render(){
        return (
        <div id="mur">
            <div class="tete">
                <img src={logo} alt ="logo"/>
                <input type="text" placeholder="Search..."/>
                <button class="login">
                    search 
                </button>
                <button class="login" onClick={(event)=>this.props.getProfile()}>
                    profile
                </button>
                <Logout setLogout={this.props.setLogout} clef={this.props.clef}></Logout>
            </div>
            <div class="fenetre">
                <div class="tweets">
                    <TweetList  setKey={this.setKey} addTweet={this.addTweet} listTweet={this.state.listTweet}  log={this.props.log}></TweetList>
                </div>
                
                <div class="friend">
                    <Loggers  setKey={this.setKey} addFriend={this.addFriend} clef={this.props.clef}></Loggers>
                </div>
            </div>
        </div>
             )
    }
}