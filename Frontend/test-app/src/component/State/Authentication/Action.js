import axios from "axios"
import { ADD_TO_FAVORITE_REQUEST, ADD_TO_FAVORITE_SUCCESS, GET_USER_REQUEST, LOGIN_REQUEST, LOGIN_SUCCESS, LOGOUT, REGISTER_REQUEST, REGISTER_SUCCESS } from "./ActionType"

export const registerUser=(reqData)=>async(dispatch)=>{
    dispatch({type:REGISTER_REQUEST})
    try{
        const {data}=await axios.post(`${API_URL}/auth/signup`,reqData.userData)
        if(data.jwt)localStorage.setItem("jwt",data.jwt);
        if(data.role==="ROLE_RESTAURANT_OWNER"){
            reqData.navigate("/admin/restaurant")
        }
        else{
            reqData.navigate("/")
        }
        dispatch({type:REGISTER_SUCCESS,payload:data.jwt})
         console.log("register success",data)
    } catch (error) {
        console.log("error",error)
    }
}

export const loginUser=(reqData)=>async(dispatch)=>{
    dispatch({type:LOGIN_REQUEST})
    try{
        const {data}=await axios.post(`${API_URL}/auth/signin`,reqData.userData)
        if(data.jwt)localStorage.setItem("jwt",data.jwt);
        if(data.role==="ROLE_RESTAURANT_OWNER"){
            reqData.navigate("/admin/restaurant")
        }
        else{
            reqData.navigate("/")
        }
        dispatch({type:LOGIN_SUCCESS,payload:data.jwt})
         console.log("login success",data)
    } catch (error) {
        console.log("error",error)
    }
}

export const getUser=(jwt)=>async(dispatch)=>{
    dispatch({type:GET_USER_REQUEST})
    try{
        const {data}=await api.get(`/auth/signup`,{
            headers:{
                Authorization:`Bearer ${jwt}`
            }
        })
        
        dispatch({type:LOGIN_SUCCESS,payload:data})
        console.log("user profile",data)
    } catch (error) {
        console.log("error",error)
    }
}

export const addToFavorite=({jwt,restaurantId})=>async(dispatch)=>{
    dispatch({type:ADD_TO_FAVORITE_REQUEST})
    try{
        const {data}=await api.put(`/api/restaurants/${restaurantId}/add-favorite`,{},{
            headers:{
                Authorization:`Bearer ${jwt}`
            }
        })
        
        dispatch({type:ADD_TO_FAVORITE_SUCCESS,payload:data})
        console.log("added to favorite",data)
    } catch (error) {
        console.log("error",error)
    }
}

export const logout=()=>async(dispatch)=>{
    dispatch({type:ADD_TO_FAVORITE_REQUEST})
    try{
        dispatch({type:LOGOUT})
        console.log("logout success")
    } catch (error) {
        console.log("error",error)
    }
}