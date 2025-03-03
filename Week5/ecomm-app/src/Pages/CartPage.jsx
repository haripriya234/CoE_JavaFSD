import { useContext } from "react";
import { CartContext } from "../Context/CartContext";

const CartPage = () => {
  const { cart } = useContext(CartContext);

  return (
    <div className="min-h-screen flex flex-col items-center justify-center bg-gray-100">
      <h1 className="text-4xl font-bold text-gray-700">Your Cart</h1>
      {cart.length === 0 ? (
        <p className="mt-2 text-gray-600">Your cart is empty.</p>
      ) : (
        <div className="w-3/4 bg-white p-4 shadow-lg rounded-lg">
          {cart.map((item, index) => (
            <div key={index} className="flex justify-between p-2 border-b">
              <span className="text-lg">{item.name}</span>
              <span className="text-lg font-bold">${item.price}</span>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};
export default CartPage;
