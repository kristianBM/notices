import React from 'react'
import { Navigate, Outlet } from 'react-router-dom'
import { isAuthenticated } from './auth'


const useAuth = () => {
    const user = isAuthenticated();
    if (user) {
        return true
    } else {
        return false
    }
}
const ProtectedRoutes = (props: any) => {


    const auth = useAuth()


    return auth ? <Outlet/>: <Navigate to="/login"/>
}


export default ProtectedRoutes;;