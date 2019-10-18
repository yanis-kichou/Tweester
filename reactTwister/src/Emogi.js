import React, { Component } from 'react';
export default class Emogi extends Component{
    constructor(props){
        super(props);
    }

    render(){
        return (<div>
            <select  id="emogi" onClick={(event)=>{
                        this.props.emogi(document.getElementById("emogi").value)
                }} 
             >
                {this.props.listEmogi.map(x=>
                    <option >{x}</option>
                )}
            </select>
            </div>)
    }
}

