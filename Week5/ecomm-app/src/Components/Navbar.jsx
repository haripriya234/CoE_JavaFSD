import { Link } from "react-router-dom";
import { useCart } from "../Context/CartContext";

const Navbar = () => {
  const { cart } = useCart();

  return (
    <nav className="bg-gray-800 text-white p-4 flex justify-between">
      <div className="container mx-auto flex justify-between">
      <Link to="/" className="text-lg font-bold">E-Commerce</Link>
      <div>
        <Link to="/" className="mx-2">Home</Link>
        <Link to="/cart" className="mx-2">Cart ({cart.length})</Link>
      </div>
      </div>
    </nav>
  );
};

export default Navbar;
