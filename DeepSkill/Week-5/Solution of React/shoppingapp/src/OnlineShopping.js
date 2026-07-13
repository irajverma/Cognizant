import React, { Component } from 'react';
import Cart from './Cart';
class OnlineShopping extends Component {
    render() {
        const items = [
            { itemname: 'Laptop', price: 999 },
            { itemname: 'Phone', price: 699 },
            { itemname: 'Headphones', price: 149 },
            { itemname: 'Mouse', price: 29 },
            { itemname: 'Keyboard', price: 59 }
        ];
        return (
            <div>
                <h2>Online Shopping Cart</h2>
                {items.map((item, index) => (
                    <Cart key={index} itemname={item.itemname} price={item.price} />
                ))}
            </div>
        );
    }
}
export default OnlineShopping;
