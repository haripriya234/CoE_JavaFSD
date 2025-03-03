import { useContext } from "react";
import { CartContext } from "../Context/CartContext";

const Cart = () => {
  const { cart, removeFromCart } = useContext(CartContext);

  return (
    <div className="min-h-screen flex flex-col items-center justify-center bg-gray-100 p-6">
      <h1 className="text-4xl font-bold text-gray-700 mb-4">Your Cart</h1>
      
      {cart.length === 0 ? (
        <p className="text-gray-600 text-lg">You have no items in your cart.</p>
      ) : (
        <div className="w-full max-w-3xl bg-white shadow-md rounded-lg p-6">
          {cart.map((item, index) => (
            <div key={index} className="flex justify-between items-center border-b p-4">
              <div>
                <h2 className="text-lg font-semibold">{item.name}</h2>
                <p className="text-gray-500">${item.price}</p>
              </div>
              <button
                onClick={() => removeFromCart(item.id)}
                className="bg-red-500 text-white px-4 py-2 rounded hover:bg-red-600"
              >
                Remove
              </button>
            </div>
          ))}
          <div className="text-xl font-bold text-gray-700 mt-4">
            Total: ${cart.reduce((total, item) => total + item.price, 0)}
          </div>
        </div>
      )}
    </div>
  );
};

export default Cart;
