import Navbar from "./Components/Navbar";
import './App.css';
import { Route,Routes } from 'react-router-dom';
import Home from './Pages/Home';
import CartPage from './Pages/CartPage';

const App=()=>{
  return(
    <>
    <Navbar></Navbar>
    <Routes>
    <Route path="/"  element={<Home/>} />
    <Route path="/"  element={<CartPage/>} />

    </Routes>
    </>
  )
}
export default App;
