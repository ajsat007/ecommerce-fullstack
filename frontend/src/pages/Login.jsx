import React, {useState} from 'react'
import axios from 'axios'
export default function Login(){ const [email,setEmail]=useState(''); const [pw,setPw]=useState(''); const submit=async(e)=>{ e.preventDefault(); try{ const r=await axios.post((import.meta.env.VITE_API_BASE||'http://localhost:8080/api')+'/auth/login',{email,pw}); alert('Logged in: '+JSON.stringify(r.data)); }catch(e){ alert('Login failed') } }
  return <form onSubmit={submit} className="container"><h2>Login</h2><input value={email} onChange={e=>setEmail(e.target.value)} placeholder="email"/><input value={pw} onChange={e=>setPw(e.target.value)} placeholder="password" type="password"/><button type="submit">Login</button></form>
}
