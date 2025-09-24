import React, {useEffect,useState} from 'react'
import axios from 'axios'
import { useParams } from 'react-router-dom'
export default function ProductDetails(){ const {id}=useParams(); const [p,setP]=useState(null)
  useEffect(()=>{ axios.get((import.meta.env.VITE_API_BASE||'http://localhost:8080/api')+'/products/'+id).then(r=>setP(r.data)).catch(()=>{}); },[id])
  if(!p) return <div>Loading...</div>
  return <div className="container"><h2>{p.name}</h2><img src={p.imageUrl} alt=""/><p>{p.description}</p><div>₹{p.price}</div></div>
}
