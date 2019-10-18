import React, { Component } from 'react';

export default class Comment extends Component{
    constructor(props){
        super(props);
        this.state={
            text:this.props.text,
            date:this.props.date,
            clef:this.props.clef
        }
    }

    render(){
        return (
            <div className="Comment">
                {this.props.text}<span>{this.props.date}</span>{this.state.nom},{this.state.prenom}<span>@{this.state.login}</span> <input type="submit"  value="delete" onClick={(event)=>this.props.deleteComment(this.state.clef)} />
            {console.log(this.state)}
            </div>
        )
    }
}