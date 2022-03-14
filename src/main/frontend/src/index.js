import React, { StrictMode } from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import * as Keycloak from 'keycloak-js';
import {Button } from "react-bootstrap"

let initOptions = {
  url: 'http://localhost:8081/auth/', 
  realm: 'rpc', 
  clientId: 'react-client', 
  onLoad: 'login-required'
}

let keycloak = Keycloak(initOptions);

keycloak.init({ onLoad: initOptions.onLoad }).success((auth) => {

  if (!auth) {
      window.location.reload();
  } else {
      console.info("Authenticated");
  }

  localStorage.setItem("bearer-token", keycloak.token);
  localStorage.setItem("refresh-token", keycloak.refreshToken);
  console.log(keycloak.token);

  setTimeout(() => {
      keycloak.updateToken(70).success((refreshed) => {
          if (refreshed) {
              console.debug('Token refreshed' + refreshed);
          } else {
              console.warn('Token not refreshed, valid for '
                  + Math.round(keycloak.tokenParsed.exp + keycloak.timeSkew - new Date().getTime() / 1000) + ' seconds');
          }
      }).error(() => {
          console.error('Failed to refresh token');
      });


  }, 60000)

}).error(() => {
  console.error("Authenticated Failed");
})

function LogOut(){
  const logout = ()=>{
      keycloak.logout();
  };

  return (
   <>
   <Button variant="warning" onClick={logout} style={{ marginLeft: "1300px", paddingRight:"50px",paddingLeft:"50px"}}>Logout</Button>
  </>

  );
}



ReactDOM.render(

  <StrictMode>
  <LogOut/>
  <App/>
  </StrictMode>,

document.getElementById('root')

);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
