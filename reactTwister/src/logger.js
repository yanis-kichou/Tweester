import React, { Component } from 'react';
export default class Logger extends Component{
constructor(props){
    super(props);
    this.state={
        nom:this.props.nom,
        prenom:this.props.prenom,
        id:this.props.id,
        login:this.props.login
    }
}


render(){
    return (
        <div>
            {this.state.nom}, 
                    {this.state.prenom}<span>@{this.state.login}</span>
                    <input type="submit"  value="+ add" onClick={(event)=>this.props.addFriend(this.props.id)} />
        </div>
    )
}
}