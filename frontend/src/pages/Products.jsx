import React, {useEffect, useState} from 'react'
import axios from 'axios'
import { Link } from 'react-router-dom'
export default function Products(){
  const [products,setProducts]=useState([])
  useEffect(()=>{ axios.get((import.meta.env.VITE_API_BASE||'http://localhost:8080/api')+'/products').then(r=>setProducts(r.data.content||[])).catch(()=>{}); },[])
  return <div className="grid">{products.map(p=><Link key={p.id} to={'/products/'+p.id} className="card"><img src={p.imageUrl} alt=""/><h3>{p.name}</h3><p>₹{p.price}</p></Link>)}</div>
}
