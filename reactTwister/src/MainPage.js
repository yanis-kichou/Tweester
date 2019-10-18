import React, { Component } from 'react';
import NavigationPannel from './NavigationPannel';
import Mur from './mur';
import Profile from './Profile';
import axios from 'axios';



export default class MainPage extends Component{
    constructor(props){
        super(props)
        this.state={
            current:"Connection",
            isConnected:false,
            log:"",
            pass:"",
            mail:"",
            tel:"",
            sexe:"",
            nom:"",
            prenom:"",
            id:0,
            clef:""
        }
        this.getNom=this.getNom.bind(this);
        this.getPrenom=this.getPrenom.bind(this);
        this.getLogin=this.getLogin.bind(this);
        this.getTel=this.getTel.bind(this);
        this.getSexe=this.getSexe.bind(this);
        this.getMail=this.getMail.bind(this);
        this.getConnected = this.getConnected.bind(this);
        this.setKey=this.setKey.bind(this);
        this.setLogout = this.setLogout.bind(this);
        this.getProfile = this.getProfile.bind(this);
        this.getMur= this.getMur.bind(this);
        this.getLoggedAfterSignin=this.getLoggedAfterSignin.bind(this);
    }  
    getConnected(logger,password,cl,myId){
        const url =new URLSearchParams();
        url.append("clef",cl);
        axios.get("http://127.0.0.1:8080/Tweester/myprofile?"+url).then(res=>{
           this.setState({current:"Mur",isConnected:true,log:logger,pass:password,clef:cl,id:myId,mail:res.data["mail"],nom:res.data["nom"],prenom:res.data["prenom"],tel:res.data["tel"],clef:res.data["key"],sexe:res.data["sexe"]});
        });
    }
    setLogout(){
        const url=new URLSearchParams();
        url.append("login",this.state.log);
        axios.get("http://127.0.0.1:8080/Tweester/logout?"+url)
        .then(res=>{
            if(Object.keys(res.data).length===2){
                this.setState({current:"Connection", isConnected:false ,log:"",pass:"",clef:"",mail:"",tel:"",sexe:"",id:0,
                listFriends:[]});
            }else{
                alert(res.data["Error!"]);                
            }
        });
    }
    getNom(){
        return this.state.nom;
    }

    getPrenom(){
        return this.state.prenom;
    }

    getLogin(){
        return this.state.log;
    }

    getMail(){
        return this.state.mail;
    }

    getTel(){
        return this.state.tel;
    }
    getSexe(){
        return this.state.sexe;
    }

    setKey(cl){
        this.setState({clef:cl});
    }

    getProfile(){
    this.setState({current:"Profile"});
}
    getMur(){
        this.setState({current:"Mur"});
    }
    getLoggedAfterSignin(logger,password,name,secondname,telephone,email,s){
        this.setState({log:logger,pass:password,mail:email,telephone,nom:name,secondname ,sexe:s})
        const url=new URLSearchParams();
        url.append("login",logger);
        url.append("password",password);
        axios.get("http://127.0.0.1:8080/Tweester/signin?"+url)
        .then(res=>{
            this.setState({current:"Mur",isConnected:true,log:logger,pass:password,clef:res.data["key"],id:res.data["idUser"],mail:email,tel:telephone,sexe:s});
        });
    }
    componentDidMount(){
        this.forceUpdate();
    }
    
    render(){
        return ( 
        <div>
            {this.state.current==="Connection" ?
            <NavigationPannel 
                getLoggedAfterSignin={this.getLoggedAfterSignin}
                getConnected={this.getConnected}
                logout={this.setLogout}
                clef={this.state.clef}
                isConnected={this.state.isConnected}
            >
            </NavigationPannel>:this.state.current==="Mur" ?
            <Mur  
                setKey={this.setKey} 
                clef={this.state.clef} 
                getProfile={this.getProfile} 
                setLogout={this.setLogout} 
                log={this.state.log}
            >
            </Mur>:
            <Profile
                clef={this.state.clef} 
                id={this.state.id}
                getMur={this.getMur} 
                setLogout={this.setLogout}
                getNom={this.getNom}
                getMail={this.getMail}
                getPrenom={this.getPrenom}
                getLogin={this.getLogin}
                getTel={this.getTel}
                getSexe={this.getSexe}
                setKey={this.setKey}
            >
            </Profile>}
        </div>)
    }

}
