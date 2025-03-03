import Navbar from "./Components/Navbar.jsx";
import './App.css';
import { Route,Routes } from 'react-router-dom';
import Home from './Pages/Home.jsx';
import CartPage from './Pages/CartPage.jsx';

const App=()=>{
  return(
    <>
    <Navbar />
    <Routes>
    <Route path="/"  element={<Home/>} />
    <Route path="/CartPage"  element={<CartPage/>} />

    </Routes>
   
    </>
  )
}
export default App;
