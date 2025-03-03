import {Link} from 'react-router-dom';
const Navbar=()=>{
    return(
        <div className='navbar'>
            <div className='Home'>
                <Link to="/">Home</Link>
            </div>
            
            <div className='cart'>
                <Link to="/CartPage">CART</Link>
            </div>
        </div>

    )

}
export default Navbar