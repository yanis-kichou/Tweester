import React, { Component } from 'react';

export default class Affiche extends Component{
    constructor(props){
        super(props);
    }

    render(){
        return (<div>
            {this.props.message}
            <span class="logger">"@"{this.props.login}
            </span>
                    <span class="date">
                        {this.props.date}
                    </span>

        </div>)
    }


}
