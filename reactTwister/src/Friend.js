import React, { Component } from 'react';

export default class Friend extends Component{
    constructor(props){
        super(props);
        this.state= {
            nom:this.props.nom,
            login:this.props.login,
            prenom:this.props.prenom,
            idUser:this.props.idUser
        }
    
    }
    render(){
        return (
            <div>
                {this.state.nom},{this.state.prenom}<span>@{this.state.login}</span> <input type="submit"  value="remove" onClick={(event)=>this.props.removeFriend(this.props.idUser)} />
            </div>

        )
    }
}