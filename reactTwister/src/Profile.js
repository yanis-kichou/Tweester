import React, { Component } from 'react';
import user from "./imageUser/user1.jpeg";
import axios from "axios";
import Friend from "./Friend";
import Comment from "./Comment";
var url=new URLSearchParams();
export default class Profile extends Component{
    constructor(props){
        super(props);
        this.state={
            c:this.props.clef,
            comments:[],
            friends:[]
        }
        this.getComments=this.getComments.bind(this);
        this.getFriends=this.getFriends.bind(this);
        this.removeFriend=this.removeFriend.bind(this);
        this.deleteComment=this.deleteComment.bind(this);
    }
getComments(){
    url=new URLSearchParams();
    url.append("clef",this.state.c);
    url.append("id",this.props.id);
    axios.get("http://127.0.0.1:8080/Tweester/mycomments?"+url).then(res=>{
        if(Object.keys(res.data).length>1){
            this.setState({comments:res.data["comments"]});
        }
        console.log(res.data);
    });
    
}
removeFriend(id){
    url=new URLSearchParams();   
    url.append("idFriend",id);
    url.append("key",this.state.c);
    axios.get("http://127.0.0.1:8080/Tweester/removefriend?"+url).then(res=>{
        alert(res.data);
    });
}
deleteComment(commentKey){
    url=new URLSearchParams();   
    url.append("commentKey",commentKey);
    url.append("key",this.state.c);
    axios.get("http://127.0.0.1:8080/Tweester/deletecomment?"+url).then(res=>{
        console.log(res.data);
    });
}
getFriends(){
    url=new URLSearchParams();
    url.append("key",this.state.c);
        axios.get("http://127.0.0.1:8080/Tweester/myfriends?"+url).then(res=>{
            if(Object.keys(res.data).length>1){
                this.setState({friends:res.data["Friend"]});
                console.log(res.data);    
         } });
    }
componentDidMount(){
    this.getComments();
    this.getFriends();
    
}
    render(){
        return (
            <div class="profile" className="Profiles"> 
                <div class="teteProfile">
                    <h1>Profile</h1>
                    <button onClick={(event)=>this.props.getMur()} class="Button">
                        Mur
                    </button>
                    <button onClick={(event)=>this.props.setLogout() }class="Button">
                        Logout
                    </button>
                </div>
                <div class="centreProfile">
                    <div class="imageProfile">
                        <img src={user}/>
                    </div>
                    <div class="FriendsProfile">
                        <ul> <span>Friends:</span>
                           {
                              this.state.friends.map(x=>
                                  <li><Friend removeFriend={this.removeFriend}nom={x["nom"]}prenom={x["prenom"]} login={x["login"]} idUser={x["IdUSer"]}></Friend>
                                  </li>
                            )

                            }
                        </ul>
                    </div>

                </div>
                <div class="bottomProfilte">
                    <div class="infoProfile">
                        <ul>
                            <li>nom    : {this.props.getNom()}</li>
                            <li>prenom : {this.props.getPrenom()}</li>
                            <li>login  : {this.props.getLogin()}</li>
                            <li>tel    : {this.props.getTel()}</li>
                            <li>e-mail : {this.props.getMail()}</li>
                            <li>sexe   : {this.props.getSexe()}</li>
                        </ul>
                    </div>
                    <div class="CommentsProfile">
                        <ul><spans class="mycomment">My Comments:</spans>
                         {
                            this.state.comments.map(x=>
                                  <li><Comment deleteComment={this.deleteComment}text={x["message "]} date={x["date"]} clef={x["key"]}>{console.log("qsdfghj"+x["key"])}</Comment></li>
                            )

                            }
                        
                        </ul>
                    </div>
                </div>
            </div>
        )
    }

}