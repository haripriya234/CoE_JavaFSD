import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Navbar from "./Components/Navbar.jsx";
import Home from "./Pages/Home.jsx";
import CartPage from "./Pages/CartPage.jsx";
import { CartProvider } from "./Context/CartContext.jsx";
import "./App.css";

const App = () => {
  console.log("dsdsa")
  return (
    <CartProvider>
      
        <Navbar />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/cart" element={<CartPage />} />
        </Routes>
      
    </CartProvider>
  );
};

export default App;

