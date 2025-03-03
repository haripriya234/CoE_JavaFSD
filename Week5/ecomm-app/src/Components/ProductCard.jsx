import { useCart } from "../Context/CartContext";

const ProductCard = ({ product }) => {
  const { dispatch } = useCart();

  return (
    <div className="border p-4 shadow-md rounded-lg text-center">
      <h2 className="text-lg font-bold">{product.name}</h2>
      <p>${product.price}</p>
      <button
        className="bg-blue-500 text-white px-4 py-2 mt-2"
        onClick={() => dispatch({ type: "ADD_TO_CART", payload: product })}
      >
        Add to Cart
      </button>
    </div>
  );
};

export default ProductCard;
