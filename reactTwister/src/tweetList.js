import React, { Component } from 'react';
import Tweet from './tweet';
import Affiche from "./Affiche";
export default class tweetList extends Component{
    constructor(props){
        super(props);
        this.state={
            add:false            
        }
        this.addTweet=this.addTweet.bind(this);
}
    addTweet(txt){
        this.props.addTweet(txt);
     
        this.setState({add:!this.state.add})
    }
    render(){
        return (
        <div id="murBas">
            <Tweet addTweet={this.addTweet} ></Tweet>
            <div class="right">
                Tweestes:<br/>
            {
                this.props.listTweet.map((x)=>
                <li id="lesTweet">
                    {console.log(x)}
                    <Affiche 
                    message={x["message "]}
                    login={x["login"]}
                    date={x["date"]}
                    date={x["date"]}
                    ></Affiche>
                 
                </li>)
            }
            </div>
        </div>
            )
    }
}
