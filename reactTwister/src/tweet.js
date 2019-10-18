import React, { Component } from 'react';
export default class tweet extends Component{
    constructor(props){
        super(props);
    
    }
    render(){
        return(
            <div class="t">
                <input type="text" placeholder="Write a tweet" id="tweet" ref="tweet"/>
                <button onClick={(event)=>this.props.addTweet(this.refs.tweet.value)}>share</button>
                
            </div>
        )
    }
}